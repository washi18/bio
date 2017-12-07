package com.pricing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pricing.model.CEtiquetaIdiomas;
import com.pricing.model.CProceso;

public class CProcesoDAO extends CConexion{
	private CProceso proceso;
	private ArrayList<CProceso> listaProcesos;
	public CProceso getProceso() {
		return proceso;
	}
	public void setProceso(CProceso proceso) {
		this.proceso = proceso;
	}
	public ArrayList<CProceso> getListaProcesos() {
		return listaProcesos;
	}
	public void setListaProcesos(ArrayList<CProceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}
	public List obtenerDescripcionProceso(){
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_ObtenerProceso");
	}
	public List buscarProcesoBD(String nombre){
		int aux=Integer.parseInt(nombre);
		Object [] values={aux};
		return getEjecutorSQL().ejecutarProcedimiento("Pricing_sp_BuscarProceso",values);
	}
	
	public void asignarListaProceso(List lista)
	{
		listaProcesos=new ArrayList<CProceso>();
		for(int i=0;i<lista.size();i++)
		{
			Map row=(Map)lista.get(i);
			listaProcesos.add(new CProceso((int)row.get("nroproceso"),
					(String)row.get("descripcion"),
					(String)row.get("imagen")));
		}
	}
}
