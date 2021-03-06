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

public class montoPaymentPaypalVM {
	private String textoPorcentaje;
	private String montoTotalPorcentual;
	private CPagos pagos;
	private String[] etiqueta;
	private String textoTaxPaypal;
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
	public String getTextoTaxPaypal() {
		return textoTaxPaypal;
	}
	public void setTextoTaxPaypal(String textoTaxPaypal) {
		this.textoTaxPaypal = textoTaxPaypal;
	}
	//================================
	@Init
	public void initVM()
	{
		this.pagos=new CPagos();
	}
	@GlobalCommand
	@NotifyChange({"textoPorcentaje","montoTotalPorcentual","etiqueta","pagos","textoTaxPaypal"})
	public void datosDePagoPaypal(@BindingParam("textoPorcentaje")String txtPorcentaje,
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
		this.textoTaxPaypal=etiqueta[100]+"("+impuestoDao.getoImpuesto().getImpuestoPaypal()+" %)";
	}
	@Command
	public void cerrarVentanaPago()
	{
		pagos.setPagoParcialPaypal(false);
		pagos.setPagoTotalPaypal(false);
		BindUtils.postNotifyChange(null, null, pagos, "pagoParcialPaypal");
		BindUtils.postNotifyChange(null, null, pagos, "pagoTotalPaypal");
	}
}
