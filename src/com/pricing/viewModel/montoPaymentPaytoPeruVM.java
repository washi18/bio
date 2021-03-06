package com.pricing.viewModel;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import com.pricing.dao.CImpuestoDAO;
import com.pricing.model.CPagos;

public class montoPaymentPaytoPeruVM {
	private String textoPorcentaje;
	private String montoTotalPorcentual;
	private CPagos pagos;
	private String[] etiqueta;
	private String textoTaxPaytoPeru;
	HttpSession seshttp;
	//===============================
	public String getTextoPorcentaje() {
		return textoPorcentaje;
	}
	public void setTextoPorcentaje(String textoPorcentaje) {
		this.textoPorcentaje = textoPorcentaje;
	}
	public String getMontoTotalPorcentual() {
		return montoTotalPorcentual;
	}
	public void setMontoTotalPorcentual(String montoTotalPorcentual) {
		this.montoTotalPorcentual = montoTotalPorcentual;
	}
	public String[] getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String[] etiqueta) {
		this.etiqueta = etiqueta;
	}
	public CPagos getPagos() {
		return pagos;
	}
	public void setPagos(CPagos pagos) {
		this.pagos = pagos;
	}
	public String getTextoTaxPaytoPeru() {
		return textoTaxPaytoPeru;
	}
	public void setTextoTaxPaytoPeru(String textoTaxPaytoPeru) {
		this.textoTaxPaytoPeru = textoTaxPaytoPeru;
	}
	//================================
	@Init
	public void initVM()
	{
		this.pagos=new CPagos();
	}
	@GlobalCommand
	@NotifyChange({"textoPorcentaje","montoTotalPorcentual","etiqueta","pagos","textoTaxPaytoPeru"})
	public void datosDePagoPaytoPeru(@BindingParam("textoPorcentaje")String txtPorcentaje,
			@BindingParam("montoTotalPorcentual")String montoTotalPorcentual,
			@BindingParam("pagos")CPagos pagos,
			@BindingParam("etiqueta")String[] etiqueta)
	{
		this.textoPorcentaje=txtPorcentaje;
		this.montoTotalPorcentual=montoTotalPorcentual;
		this.pagos=pagos;
		this.etiqueta=etiqueta;
		CImpuestoDAO impuestoDao=new CImpuestoDAO();
		impuestoDao.recuperarImpuestosBD();
		this.textoTaxPaytoPeru=etiqueta[235]+"("+impuestoDao.getoImpuesto().getImpuestoPaytoPeru()+" %)";
	}
	@Command
	public void cerrarVentanaPago()
	{
		pagos.setPagoParcialPaytoPeru(false);
		pagos.setPagoTotalPaytoPeru(false);
		BindUtils.postNotifyChange(null, null, pagos, "pagoParcialPaytoPeru");
		BindUtils.postNotifyChange(null, null, pagos, "pagoTotalPaytoPeru");
	}
}
