package com.pricing.model;

public class CImpuesto 
{
	  private int codImpuesto;// int,
	  private String impuestoPaypal;// varchar(5),
	  private String impuestoPaytoPeru;
	  private boolean editable;
	  /********************/
	public int getCodImpuesto() {
		return codImpuesto;
	}
	public void setCodImpuesto(int codImpuesto) {
		this.codImpuesto = codImpuesto;
	}
	public String getImpuestoPaypal() {
		return impuestoPaypal;
	}
	public void setImpuestoPaypal(String impuestoPaypal) {
		this.impuestoPaypal = impuestoPaypal;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public String getImpuestoPaytoPeru() {
		return impuestoPaytoPeru;
	}
	public void setImpuestoPaytoPeru(String impuestoPaytoPeru) {
		this.impuestoPaytoPeru = impuestoPaytoPeru;
	}
	/*****************************/
	public CImpuesto() {
		// TODO Auto-generated constructor stub
		this.impuestoPaypal ="0";
		this.impuestoPaytoPeru="0";
		this.editable=false;
	}
	public CImpuesto(int codImpuesto, String impuestoPaypal,String impuestoPaytoPeru) {
		this.codImpuesto = codImpuesto;
		this.impuestoPaypal = impuestoPaypal;
		this.impuestoPaytoPeru=impuestoPaytoPeru;
		/*************/
		this.editable=false;
	}
}
