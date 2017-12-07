package com.pricing.viewModel;
import java.util.ArrayList;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.pricing.dao.*;
import com.pricing.model.*;

public class vista_previaVM {
	private String ruta="";
	private CProcesoDAO procesoDAO;
	private CEtiqueta etiquetaDao;
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public CProcesoDAO getProcesoDAO() {
		return procesoDAO;
	}

	public void setProcesoDAO(CProcesoDAO procesoDAO) {
		this.procesoDAO = procesoDAO;
	}

	public CEtiqueta getEtiquetaDao() {
		return etiquetaDao;
	}

	public void setEtiquetaDao(CEtiqueta etiquetaDao) {
		this.etiquetaDao = etiquetaDao;
	}

	@Init
	public void initVM(){
		procesoDAO=new CProcesoDAO();
		etiquetaDao=new CEtiqueta();
		ruta="hola mundo";
	}
	
	@GlobalCommand
	@NotifyChange({"ruta"})
	public void recuperarImagendeProcesos(@BindingParam("proceso")  String nombre) {
		String codigo=nombre;
		if(nombre==null){
			return;
		}else{

			procesoDAO.asignarListaProceso(procesoDAO.buscarProcesoBD(nombre));
			
			ruta=procesoDAO.getListaProcesos().get(0).getImagen();
			System.out.println("Imagen : "+ruta);
		}
	}
}
