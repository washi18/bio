package com.pricing.model;

import java.util.Date;

public class CNroAccesos {
	private long cod;// bigint,
	private String cPaqueteCod;
	private boolean bNroPasajeros;// boolean,
	private boolean bLlenadoPasajeros;// boolean,
	private boolean bServicios;// boolean,
	private boolean bTerminos;// boolean,
	private boolean bPago;// boolean,
	private boolean bReserva;// boolean
	private Date dFecha;
	//===========================
	public long getCod() {
		return cod;
	}

	public void setCod(long cod2) {
		this.cod = cod2;
	}

	public boolean isbNroPasajeros() {
		return bNroPasajeros;
	}

	public void setbNroPasajeros(boolean bNroPasajeros) {
		this.bNroPasajeros = bNroPasajeros;
	}

	public boolean isbLlenadoPasajeros() {
		return bLlenadoPasajeros;
	}

	public void setbLlenadoPasajeros(boolean bLlenadoPasajeros) {
		this.bLlenadoPasajeros = bLlenadoPasajeros;
	}

	public boolean isbServicios() {
		return bServicios;
	}

	public void setbServicios(boolean bServicios) {
		this.bServicios = bServicios;
	}

	public boolean isbTerminos() {
		return bTerminos;
	}

	public void setbTerminos(boolean bTerminos) {
		this.bTerminos = bTerminos;
	}

	public boolean isbPago() {
		return bPago;
	}

	public void setbPago(boolean bPago) {
		this.bPago = bPago;
	}

	public boolean isbReserva() {
		return bReserva;
	}

	public void setbReserva(boolean bReserva) {
		this.bReserva = bReserva;
	}

	public String getcPaqueteCod() {
		return cPaqueteCod;
	}

	public void setcPaqueteCod(String cPaqueteCod) {
		this.cPaqueteCod = cPaqueteCod;
	}

	//======================
	public CNroAccesos() {
		// TODO Auto-generated constructor stub
		this.cPaqueteCod="";
		this.bNroPasajeros=false;
		this.bLlenadoPasajeros=false;
		this.bServicios=false;
		this.bTerminos=false;
		this.bPago=false;
		this.bReserva=false;
	}
}
