package com.pricing.dao;

import java.util.List;
import java.util.Map;

import com.pricing.model.CNroAccesos;

public class CNroAccesosDAO extends CConexion{
	public CNroAccesosDAO() {
		// TODO Auto-generated constructor stub
		super();
	}
	//======================
	public List insertarNroAcceso(String codPaquete)
	{
		Object[] values={codPaquete};
		return getEjecutorSQL().ejecutarProcedimiento("BAE_sp_ObtenerCodAcceso",values);
	}
	public long obtenerCodNroAcceso(List lista)
	{
		Map row=(Map)lista.get(0);
		long n=(long)row.get("codacceso");
		return n;
	}
	public List modificarNroAcceso(CNroAccesos a)
	{
		Object[] values={a.getCod(),a.isbNroPasajeros(),a.isbLlenadoPasajeros(),a.isbServicios(),
				a.isbTerminos(),a.isbPago(),a.isbReserva()};
		return getEjecutorSQL().ejecutarProcedimiento("BAE_sp_ModificarNroAccesos", values);
	}
	public boolean isCorrectOperation(List lista)
	{
		Map row=(Map)lista.get(0);
		boolean correcto=row.get("resultado").toString().equals("correcto");
		if(correcto)return true;
		else return false;
	}
}
