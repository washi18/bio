package com.pricing.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;

import pe.com.erp.crypto.Encryptar;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CImpuestoDAO;
import com.pricing.model.CEtiqueta;
import com.pricing.model.CImpuesto;

public class impuestoYcobrosVM 
{
	private CImpuesto oImpuesto;
	/****************/

	public CImpuesto getoImpuesto() {
		return oImpuesto;
	}

	public void setoImpuesto(CImpuesto oImpuesto) {
		this.oImpuesto = oImpuesto;
	}
	/*****************/
	@Init
	public void initVM()
	{
		oImpuesto=new CImpuesto();
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		impuestoDao.recuperarImpuestosBD();
		setoImpuesto(impuestoDao.getoImpuesto());
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void insert_update_impuesto(@BindingParam("componente")Component comp)
	{
		if(!validoParaInsert_Update(oImpuesto,comp))
			return;
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		boolean correcto=impuestoDao.isOperationCorrect(impuestoDao.insertarImpuesto(oImpuesto));
		if(correcto)
		{
			oImpuesto.setEditable(false);
			Clients.showNotification("Se guardaron los cambios de manera satisfactoria",Clients.NOTIFICATION_TYPE_INFO, comp,"before_start",3000);
		}else
			Clients.showNotification("No se pudieron guardar los cambios efectuados",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
	}
	public boolean validoParaInsert_Update(CImpuesto impuesto,Component comp)
	{
		boolean valido=true;
		if(impuesto.getImpuestoPaypal()==null || impuesto.getImpuestoPaytoPeru()==null)
		{
			valido=false;
			Clients.showNotification("Debe ingresar ambos impuestos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
		}
		return valido;
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void changeValorCobro(@BindingParam("valor")int valor,@BindingParam("componente")Component comp)
	{
		if(valor==1)
		{
			if(!isNumberDouble(oImpuesto.getImpuestoPaypal()))
			{
				oImpuesto.setImpuestoPaypal("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR, comp,"before_start",3000);
			}
		}else if(valor==3)
		{
			if(!isNumberDouble(oImpuesto.getImpuestoPaytoPeru()))
			{
				oImpuesto.setImpuestoPaytoPeru("0");
				Clients.showNotification("Digite valores numericos",Clients.NOTIFICATION_TYPE_ERROR,comp,"before_start",3000);
			}
		}
	}
	public boolean isNumberDouble(String cad)
	{
		try
		 {
		   Double.parseDouble(cad);
		   return true;
		 }
		 catch(NumberFormatException nfe)
		 {
		   return false;
		 }
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void Cancelar()
	{
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		impuestoDao.recuperarImpuestosBD();
		setoImpuesto(impuestoDao.getoImpuesto());
	}
	@Command
	@NotifyChange({"oImpuesto"})
	public void Editar()
	{
		oImpuesto.setEditable(true);
	}
}
