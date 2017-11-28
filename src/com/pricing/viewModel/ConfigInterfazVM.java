package com.pricing.viewModel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.pricing.dao.CInterfazDAO;
import com.pricing.model.CInterfaz;
import com.pricing.model.CPaquete;
import com.pricing.util.ScannUtil;

public class ConfigInterfazVM 
{
	private CInterfaz oInterfaz;
	/*************/
	public CInterfaz getoInterfaz() {
		return oInterfaz;
	}
	public void setoInterfaz(CInterfaz oInterfaz) {
		this.oInterfaz = oInterfaz;
	}
	/***************/
	@Init
	public void initVM()
	{
		oInterfaz=new CInterfaz();
		CInterfazDAO interfazDao=new CInterfazDAO();
		interfazDao.asignarConfigInterfaz(interfazDao.recuperarConfigInterfazDB());
		setoInterfaz(interfazDao.getoInterfaz());
	}
	@Command
	public void mostrarConfigColoresPricing()
	{
		Window window = (Window)Executions.createComponents(
                "/configColoresPricing.zul", null, null);
        window.doModal();
	}
	@Command
	@NotifyChange({"oInterfaz"})
	public void selectModoLlenadoPax(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("1"))//Solo subir documentos
		{
			oInterfaz.setbSubirDocPax(true);
			oInterfaz.setbSubirDoc_O_llenarDatosPax(false);
			oInterfaz.setbSubirDoc_Y_llenarDatosPax(false);
			oInterfaz.setbLlenarDatosUnPax(false);
		}else if(opcion.toString().equals("2"))//Subir doc.. y llenar datos
		{
			oInterfaz.setbSubirDocPax(false);
			oInterfaz.setbSubirDoc_O_llenarDatosPax(false);
			oInterfaz.setbSubirDoc_Y_llenarDatosPax(true);
			oInterfaz.setbLlenarDatosUnPax(false);
		}else if(opcion.toString().equals("3"))//Llenar unicamente los datos de los pasajeros
		{
			oInterfaz.setbSubirDocPax(false);
			oInterfaz.setbSubirDoc_O_llenarDatosPax(true);
			oInterfaz.setbSubirDoc_Y_llenarDatosPax(false);
			oInterfaz.setbLlenarDatosUnPax(false);
		}else//Llenado de datos unicamente del pasajero que reserva
		{
			oInterfaz.setbSubirDocPax(false);
			oInterfaz.setbSubirDoc_O_llenarDatosPax(false);
			oInterfaz.setbSubirDoc_Y_llenarDatosPax(false);
			oInterfaz.setbLlenarDatosUnPax(true);
		}
	}
	@Command
	@NotifyChange({"oInterfaz"})
	public void selectConfigHoteles(@BindingParam("opcion")String opcion)
	{
		if(opcion.toString().equals("1"))//Hoteles con cama adicional
		{
			oInterfaz.setbHotelesConCamaAdicional(true);
			oInterfaz.setbHotelesSinCamaAdicional(false);
		}else//Hoteles sin cama adicional
		{
			oInterfaz.setbHotelesSinCamaAdicional(true);
			oInterfaz.setbHotelesConCamaAdicional(false);
		}
	}
	@Command
	@NotifyChange({"oInterfaz"})
	public void guardarCambiosModoPricing(@BindingParam("componente")Component comp)
	{
		CInterfazDAO interfazDao=new CInterfazDAO();
		boolean correcto=interfazDao.isOperationCorrect(interfazDao.insertarConfigInterfazFormaPricing(oInterfaz));
		if(correcto)
		{
			Clients.showNotification("Los cambios se guardaron correctamente",Clients.NOTIFICATION_TYPE_INFO,comp,"before_start",3000);
		}else
			Clients.showNotification("Los cambios no se guardaron correctamente",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
	}
	@Command
	public void uploadImg(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event,
			@BindingParam("tipo") final String tipo,
			@BindingParam("componente") final Component comp) {
		org.zkoss.util.media.Media[] listaMedias = event.getMedias();
		if (listaMedias != null) {
				for (Media media : listaMedias) {
					if (media instanceof org.zkoss.image.Image) {
						org.zkoss.image.Image img = (org.zkoss.image.Image) media;
						// Con este metodo(uploadFile) de clase guardo la imagen
						// en la ruta del servidor
						boolean b;
						if(tipo.equals("hoteles"))b=ScannUtil.uploadFileHoteles(img);
						else if(tipo.equals("servicios"))b=ScannUtil.uploadFileServicios(img);
						else if(tipo.equals("destinos"))b=ScannUtil.uploadFileDestinos(img);
						else if(tipo.equals("tours"))b = ScannUtil.uploadFilePaquetes(img);
						else if(tipo.equals("android"))b=ScannUtil.uploadFileAndroid(img);
						Clients.showNotification(img.getName() + " Se subio al servidor.",
								Clients.NOTIFICATION_TYPE_INFO, comp, "before_start", 2700);
					} else {
						Messagebox.show("No es una imagen: " + media, "Error", Messagebox.OK, Messagebox.ERROR);
						break; // not to show too many errors
					}
				}
			}
	}
	@Command
	@NotifyChange({"oInterfaz"})
	public void Cancelar()
	{
		CInterfazDAO interfazDao=new CInterfazDAO();
		interfazDao.asignarConfigInterfaz(interfazDao.recuperarConfigInterfazDB());
		setoInterfaz(interfazDao.getoInterfaz());
	}
}
