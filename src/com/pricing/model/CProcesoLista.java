package com.pricing.model;

public class CProcesoLista {
	private int ncodlistaproceso;
	private int codetiqueta;
	private int nroproceso;
	//get and set
	public int getNcodlistaproceso() {
		return ncodlistaproceso;
	}
	public void setNcodlistaproceso(int ncodlistaproceso) {
		this.ncodlistaproceso = ncodlistaproceso;
	}
	public int getCodetiqueta() {
		return codetiqueta;
	}
	public void setCodetiqueta(int codetiqueta) {
		this.codetiqueta = codetiqueta;
	}
	public int getNroproceso() {
		return nroproceso;
	}
	public void setNroproceso(int nroproceso) {
		this.nroproceso = nroproceso;
	}
	/**************** METODOS ******************/
	public CProcesoLista(){
		ncodlistaproceso=0;
		codetiqueta=0;
		nroproceso=0;
	}
	public CProcesoLista(int ncod,int codet,int nropro){
		ncodlistaproceso=ncod;
		codetiqueta=codet;
		nroproceso=nropro;
	}

}
