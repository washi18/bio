package com.pricing.model;

public class CProceso {
	private int nroproceso;
	private String descripcion;
	//get and set
	public int getNroproceso() {
		return nroproceso;
	}
	public void setNroproceso(int nroproceso) {
		this.nroproceso = nroproceso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	// procesos
	public CProceso(){
		nroproceso=1;
		descripcion="";
	}
}
