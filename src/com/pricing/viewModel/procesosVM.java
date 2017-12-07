package com.pricing.viewModel;

import java.util.ArrayList;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;

import com.itextpdf.text.List;
import com.pricing.dao.*;
import com.pricing.model.*;

public class procesosVM {
	private CProceso oProceso;
	private CProcesoDAO procesoDao;
	private ArrayList<CProceso> listaProceso;
	// get and set
	public CProceso getoProceso() {
		return oProceso;
	}
	public void setoProceso(CProceso oProceso) {
		this.oProceso = oProceso;
	}
	public CProcesoDAO getProcesoDao() {
		return procesoDao;
	}
	public void setProcesoDao(CProcesoDAO procesoDao) {
		this.procesoDao = procesoDao;
	}
	public ArrayList<CProceso> getListaProcesos() {
		return listaProceso;
	}
	public void setListaProcesos(ArrayList<CProceso> listaProcesos) {
		this.listaProceso = listaProcesos;
	}
	//procesos
	@Init
	public void initPro(){
		
	}
	public void recuperarProcesos(){
		List t=(List) procesoDao.obtenerDescripcionProceso();
		
	}
	
}
