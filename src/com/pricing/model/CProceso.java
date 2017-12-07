package com.pricing.model;

public class CProceso {
	private int nroproceso;
	private String descripcion;
	private String imagen;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	// procesos
	public CProceso(){
		nroproceso=1;
		descripcion="";
		imagen="";
	}
	public CProceso(int codProceso,String texto,String img){
		nroproceso=codProceso;
		descripcion=texto;
		imagen=img;
	}
}
