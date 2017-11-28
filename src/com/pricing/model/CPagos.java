package com.pricing.model;

public class CPagos {
	private boolean pagoParcialPaypal;
	private boolean pagoTotalPaypal;
	private boolean pagoParcialPaytoPeru;
	private boolean pagoTotalPaytoPeru;
	private boolean selectPaypal;
	private boolean selectPaytoPeru;
	private String taxPaypal;
	private String taxPaytoPeru;
	private String totalConImpuestoPaypal;
	private String totalConImpuestoPaytoPeru;
	private String urlPaytoPeru;
	private String urlPaypal;
	/**************/
	public boolean isPagoParcialPaypal() {
		return pagoParcialPaypal;
	}
	public void setPagoParcialPaypal(boolean pagoParcialPaypal) {
		this.pagoParcialPaypal = pagoParcialPaypal;
	}
	public boolean isPagoTotalPaypal() {
		return pagoTotalPaypal;
	}
	public void setPagoTotalPaypal(boolean pagoTotalPaypal) {
		this.pagoTotalPaypal = pagoTotalPaypal;
	}
	public boolean isSelectPaypal() {
		return selectPaypal;
	}
	public void setSelectPaypal(boolean selectPaypal) {
		this.selectPaypal = selectPaypal;
	}
	public String getTaxPaypal() {
		return taxPaypal;
	}
	public void setTaxPaypal(String taxPaypal) {
		this.taxPaypal = taxPaypal;
	}
	public String getTotalConImpuestoPaypal() {
		return totalConImpuestoPaypal;
	}
	public void setTotalConImpuestoPaypal(String totalConImpuestoPaypal) {
		this.totalConImpuestoPaypal = totalConImpuestoPaypal;
	}
	public String getUrlPaypal() {
		return urlPaypal;
	}
	public void setUrlPaypal(String urlPaypal) {
		this.urlPaypal = urlPaypal;
	}
	public boolean isPagoParcialPaytoPeru() {
		return pagoParcialPaytoPeru;
	}
	public void setPagoParcialPaytoPeru(boolean pagoParcialPaytoPeru) {
		this.pagoParcialPaytoPeru = pagoParcialPaytoPeru;
	}
	public boolean isPagoTotalPaytoPeru() {
		return pagoTotalPaytoPeru;
	}
	public void setPagoTotalPaytoPeru(boolean pagoTotalPaytoPeru) {
		this.pagoTotalPaytoPeru = pagoTotalPaytoPeru;
	}
	public boolean isSelectPaytoPeru() {
		return selectPaytoPeru;
	}
	public void setSelectPaytoPeru(boolean selectPaytoPeru) {
		this.selectPaytoPeru = selectPaytoPeru;
	}
	public String getTaxPaytoPeru() {
		return taxPaytoPeru;
	}
	public void setTaxPaytoPeru(String taxPaytoPeru) {
		this.taxPaytoPeru = taxPaytoPeru;
	}
	public String getTotalConImpuestoPaytoPeru() {
		return totalConImpuestoPaytoPeru;
	}
	public void setTotalConImpuestoPaytoPeru(String totalConImpuestoPaytoPeru) {
		this.totalConImpuestoPaytoPeru = totalConImpuestoPaytoPeru;
	}
	public String getUrlPaytoPeru() {
		return urlPaytoPeru;
	}
	public void setUrlPaytoPeru(String urlPaytoPeru) {
		this.urlPaytoPeru = urlPaytoPeru;
	}
	/*****************/
	public CPagos() {
		// TODO Auto-generated constructor stub
		pagoParcialPaypal=false;
		pagoParcialPaytoPeru=false;
		pagoTotalPaypal=false;
		pagoTotalPaytoPeru=false;
		selectPaypal=false;
		selectPaytoPeru=false;
	}
	public void selectPaypal()
	{
		selectPaypal=true;
		selectPaytoPeru=false;
	}
	public void selectPaytoPeru()
	{
		selectPaypal=false;
		selectPaytoPeru=true;
	}
}
