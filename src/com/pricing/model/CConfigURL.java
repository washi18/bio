package com.pricing.model;

public class CConfigURL 
{
	private int nConfigUrlCod;// int,
	private String urlReturnPaypal;// text,
	private String urlPaginaWeb;// text,
	private String urlLogoEmpresa;// text,
	private String logoEmpresa;// text,
	private String urlServletPagoParcialPaypal;// text,
	private String urlServletPagoParcialPaytoPeru;// text,
	private String urlTerminosYCondiciones;// text,
	private boolean editable;
	/****************/
	public int getnConfigUrlCod() {
		return nConfigUrlCod;
	}
	public void setnConfigUrlCod(int nConfigUrlCod) {
		this.nConfigUrlCod = nConfigUrlCod;
	}
	public String getUrlReturnPaypal() {
		return urlReturnPaypal;
	}
	public void setUrlReturnPaypal(String urlReturnPaypal) {
		this.urlReturnPaypal = urlReturnPaypal;
	}
	public String getUrlPaginaWeb() {
		return urlPaginaWeb;
	}
	public void setUrlPaginaWeb(String urlPaginaWeb) {
		this.urlPaginaWeb = urlPaginaWeb;
	}
	public String getUrlLogoEmpresa() {
		return urlLogoEmpresa;
	}
	public void setUrlLogoEmpresa(String urlLogoEmpresa) {
		this.urlLogoEmpresa = urlLogoEmpresa;
	}
	public String getLogoEmpresa() {
		return logoEmpresa;
	}
	public void setLogoEmpresa(String logoEmpresa) {
		this.logoEmpresa = logoEmpresa;
	}
	public String getUrlServletPagoParcialPaypal() {
		return urlServletPagoParcialPaypal;
	}
	public void setUrlServletPagoParcialPaypal(String urlServletPagoParcialPaypal) {
		this.urlServletPagoParcialPaypal = urlServletPagoParcialPaypal;
	}
	public String getUrlServletPagoParcialPaytoPeru() {
		return urlServletPagoParcialPaytoPeru;
	}
	public void setUrlServletPagoParcialPaytoPeru(String urlServletPagoParcialPaytoPeru) {
		this.urlServletPagoParcialPaytoPeru = urlServletPagoParcialPaytoPeru;
	}
	public String getUrlTerminosYCondiciones() {
		return urlTerminosYCondiciones;
	}
	public void setUrlTerminosYCondiciones(String urlTerminosYCondiciones) {
		this.urlTerminosYCondiciones = urlTerminosYCondiciones;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	/****************/
	public CConfigURL() {
		// TODO Auto-generated constructor stub
		this.urlReturnPaypal = "";
		this.urlPaginaWeb = "";
		this.urlLogoEmpresa = "";
		this.logoEmpresa = "/img/logo_default.png";
		this.urlServletPagoParcialPaypal = "";
		this.urlServletPagoParcialPaytoPeru = "";
		this.urlTerminosYCondiciones="";
		this.editable=false;
	}
	public CConfigURL(int nConfigUrlCod, String urlReturnPaypal,
			String urlPaginaWeb, String urlLogoEmpresa, String logoEmpresa,
			String urlServletPagoParcialPaypal, String urlServletPagoParcialPaytoPeru,
			String urlTerminosYCondiciones) {
		this.nConfigUrlCod = nConfigUrlCod;
		this.urlReturnPaypal = urlReturnPaypal;
		this.urlPaginaWeb = urlPaginaWeb;
		this.urlLogoEmpresa = urlLogoEmpresa;
		this.logoEmpresa = logoEmpresa;
		this.urlServletPagoParcialPaypal = urlServletPagoParcialPaypal;
		this.urlServletPagoParcialPaytoPeru = urlServletPagoParcialPaytoPeru;
		this.urlTerminosYCondiciones = urlTerminosYCondiciones;
		this.editable = false;
	}
}
