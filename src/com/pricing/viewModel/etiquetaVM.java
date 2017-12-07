package com.pricing.viewModel;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Window;

import pe.com.erp.crypto.Encryptar;
import sun.text.normalizer.NormalizerBase;

import com.pricing.dao.CEtiquetaDAO;
import com.pricing.dao.CPaisDAO;
import com.pricing.dao.CProcesoDAO;
import com.pricing.model.*;
import com.sun.mail.handlers.message_rfc822;

public class etiquetaVM {
	/**
	 * ATRIBUTOS
	 */
	private CEtiqueta oEtiqueta;
	private CProcesoDAO procesoDAO;
	private CEtiquetaDAO etiquetaDao;
	private CProceso oProceso;
	private ArrayList<CEtiqueta> listaEtiquetas;
	private ArrayList<CProceso> listaProcesos;
	private ArrayList<CProcesoLista> listadoEtiquetaProcesos;
	private boolean visibleEspaniol = false;
	private boolean visiblePortugues = false;
	private boolean visibleIngles = false;
	private boolean visibleIdioma4 = false;
	private boolean visibleIdioma5 = false;
	private boolean visiblecmbIdiomas = false;
	private boolean visiblebtnActualizar = false;
	private boolean visibleEditarRespons = false;
	private boolean vistaprevia=false;
	private String ruta;

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void setVistaprevia(boolean vistaprevia) {
		this.vistaprevia = vistaprevia;
	}

	/**
	 * GETTER AND SETTER
	 */

	
	public ArrayList<CProcesoLista> getListadoEtiquetaProcesos() {
		return listadoEtiquetaProcesos;
	}

	public CProceso getoProceso() {
		return oProceso;
	}

	public void setoProceso(CProceso oProceso) {
		this.oProceso = oProceso;
	}

	public void setListadoEtiquetaProcesos(ArrayList<CProcesoLista> listadoEtiquetaProcesos) {
		this.listadoEtiquetaProcesos = listadoEtiquetaProcesos;
	}

	public boolean isVistaprevia() {
		return vistaprevia;
	}
	public CEtiqueta getoEtiqueta() {
		return oEtiqueta;
	}

	public CProcesoDAO getProcesoDAO() {
		return procesoDAO;
	}

	public void setProcesoDAO(CProcesoDAO procesoDAO) {
		this.procesoDAO = procesoDAO;
	}

	public boolean isVisibleEditarRespons() {
		return visibleEditarRespons;
	}

	public void setVisibleEditarRespons(boolean visibleEditarRespons) {
		this.visibleEditarRespons = visibleEditarRespons;
	}

	public boolean isVisiblebtnActualizar() {
		return visiblebtnActualizar;
	}

	public void setVisiblebtnActualizar(boolean visiblebtnActualizar) {
		this.visiblebtnActualizar = visiblebtnActualizar;
	}

	public boolean isVisiblecmbIdiomas() {
		return visiblecmbIdiomas;
	}

	public void setVisiblecmbIdiomas(boolean visiblecmbIdiomas) {
		this.visiblecmbIdiomas = visiblecmbIdiomas;
	}

	public boolean isVisibleEspaniol() {
		return visibleEspaniol;
	}

	public void setVisibleEspaniol(boolean visibleEspaniol) {
		this.visibleEspaniol = visibleEspaniol;
	}

	public boolean isVisiblePortugues() {
		return visiblePortugues;
	}

	public void setVisiblePortugues(boolean visiblePortugues) {
		this.visiblePortugues = visiblePortugues;
	}

	public boolean isVisibleIngles() {
		return visibleIngles;
	}

	public void setVisibleIngles(boolean visibleIngles) {
		this.visibleIngles = visibleIngles;
	}

	public boolean isVisibleIdioma4() {
		return visibleIdioma4;
	}

	public void setVisibleIdioma4(boolean visibleIdioma4) {
		this.visibleIdioma4 = visibleIdioma4;
	}

	public boolean isVisibleIdioma5() {
		return visibleIdioma5;
	}

	public void setVisibleIdioma5(boolean visibleIdioma5) {
		this.visibleIdioma5 = visibleIdioma5;
	}

	public void setoEtiqueta(CEtiqueta oEtiqueta) {
		this.oEtiqueta = oEtiqueta;
	}

	public ArrayList<CEtiqueta> getListaEtiquetas() {
		return listaEtiquetas;
	}

	public ArrayList<CProceso> getListaProcesos() {
		return listaProcesos;
	}

	public void setListaProcesos(ArrayList<CProceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}

	public void setListaEtiquetas(ArrayList<CEtiqueta> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}

	public CEtiquetaDAO getEtiquetaDao() {
		return etiquetaDao;
	}

	public void setEtiquetaDao(CEtiquetaDAO etiquetaDao) {
		this.etiquetaDao = etiquetaDao;
	}

	/**
	 * METODOS Y FUNCIONES DE LA CLASE
	 */
	@Init
	public void initVM() {
		/** Inicializando los objetos **/
		oEtiqueta = new CEtiqueta();
		etiquetaDao = new CEtiquetaDAO();
		procesoDAO =new CProcesoDAO();
		listaEtiquetas = new ArrayList<CEtiqueta>();
		listaProcesos = new ArrayList<CProceso>();
		vistaprevia=false;
		ruta="";
		/*****************************/
		Encryptar encrip = new Encryptar();
		// System.out.println("Aqui esta la contraseña
		// desencriptada-->"+encrip.decrypt("cyS249O3OHZTsG0ww1rYrw=="));
		inicierProcesos();
	}

	@Command
	public void inicierProcesos(){
		procesoDAO.asignarListaProceso(procesoDAO.obtenerDescripcionProceso());
		listaProcesos=procesoDAO.getListaProcesos();
//		for(int i=0;i<listaProcesos.size();i++){
//			System.out.println(listaProcesos.get(i).getNroproceso()+" "+listaProcesos.get(i).getDescripcion()+" "+listaProcesos.get(i).getImagen());
//		}
	}
	@GlobalCommand
	public void recuperarEtiquetas() {
		HttpSession ses = (HttpSession) Sessions.getCurrent().getNativeSession();
		String user = (String) ses.getAttribute("usuario");
		String pas = (String) ses.getAttribute("clave");
		if (user != null && pas != null) {
			/** Obtencion de las etiquetas de la base de datos **/
			etiquetaDao.asignarListaEtiquetas(etiquetaDao.recuperarEtiquetasBD());
			/** Asignacion de las etiquetas a la listaEtiquetas **/
			setListaEtiquetas(etiquetaDao.getListaEtiquetas());
		}
		BindUtils.postNotifyChange(null, null, this, "listaEtiquetas");
	}

	@Command
	public void buscarEtiquetas(@BindingParam("nombre") String nombre) {

		etiquetaDao.asignarListaEtiquetas(etiquetaDao.buscarEtiquetasBD(nombre));
		setListaEtiquetas(etiquetaDao.getListaEtiquetas());
		BindUtils.postNotifyChange(null, null, this, "listaEtiquetas");
	}

	@Command
	public void buscarEtiquetasxProceso(@BindingParam("nombre") String nombre) {	
		//System.out.println("El codigo del proceso es "+nombre);
		
		procesoDAO.asignarListaProceso(procesoDAO.buscarProcesoBD(nombre));
		
		ruta=procesoDAO.getListaProcesos().get(0).getImagen();
		System.out.println("la ruta de la imagen es "+ruta);
		
		etiquetaDao.asignarListaEtiquetas(etiquetaDao.buscarEtiquetasxProceso(nombre));
		
		ArrayList<CEtiqueta> aux1=etiquetaDao.getListaEtiquetas();
		
		
		CEtiquetaDAO etiquetasDAOaux=new CEtiquetaDAO();
		etiquetasDAOaux.asignarListaEtiquetas(etiquetasDAOaux.recuperarEtiquetasBD());
		ArrayList<CEtiqueta> aux=etiquetasDAOaux.getListaEtiquetas();
//		
//		System.out.println("Procesos "+aux1.get(0).getCodEtiqueta()+" "+aux1.get(0).getcIdioma1());
//		
//		System.out.println("Total Etiquetas "+aux.get(0).getCodEtiqueta()+" "+aux1.get(0).getcIdioma1());
		
		setListaEtiquetas(etiquetaDao.getListaEtiquetas());
//		for(int i=0;i<listaEtiquetas.size();i++){
//			System.out.println("Etiquetas : "+listaEtiquetas.get(i).getCodEtiqueta());
//		}
		BindUtils.postNotifyChange(null, null, this, "listaEtiquetas");
	}

	@Command
	@NotifyChange({ "visibleEspaniol", "visibleIngles", "visiblePortugues", "visiblebtnActualizar", "visibleIdioma4",
			"visibleIdioma5", "visiblecmbIdiomas" })
	public void actualizarEtiqueta(@BindingParam("etiqueta") CEtiqueta etiqueta) {
		visiblecmbIdiomas = visiblebtnActualizar = visibleEspaniol = visibleIngles = visiblePortugues = visibleIdioma4 = visibleIdioma5 = false;
		etiqueta.setEditable(false);
		refrescaFilaTemplate(etiqueta);
		/** Actualizar datos de la etiqueta en la BD **/
		boolean b = etiquetaDao.isOperationCorrect(etiquetaDao.modificarEtiqueta(etiqueta));
		// initVM();
		System.out.println("-->" + etiqueta.getCodEtiqueta());
		etiqueta.setColor(etiqueta.COLOR_NO_SELECT);
	}

	@Command
	@NotifyChange({ "oEtiqueta", "visiblecmbIdiomas", "visiblebtnActualizar", "visibleEditarRespons" })
	public void Editar(@BindingParam("etiqueta") CEtiqueta e) {
		visiblecmbIdiomas = visiblebtnActualizar = visibleEditarRespons = true;
		oEtiqueta.setEditable(false);
		refrescaFilaTemplate(oEtiqueta);
		oEtiqueta = e;
		// le damos estado editable
		e.setEditable(!e.isEditable());
		e.setColor(e.COLOR_SELECT);
		// lcs.setEditingStatus(!lcs.getEditingStatus());
		refrescaFilaTemplate(e);
	}

	@Command
	@NotifyChange({ "visibleEspaniol", "visibleIngles", "visiblePortugues", "visibleIdioma4", "visibleIdioma5" })
	public void EditarIdiomas(@BindingParam("idioma") String idIdioma) {
		if (idIdioma.equals("cmbEspañol")) {
			visibleEspaniol = true;
			visibleIngles = visiblePortugues = visibleIdioma4 = visibleIdioma5 = false;
		} else if (idIdioma.equals("cmbIngles")) {
			visibleIngles = true;
			visibleEspaniol = visiblePortugues = visibleIdioma4 = visibleIdioma5 = false;
		} else if (idIdioma.equals("cmbPortugues")) {
			visiblePortugues = true;
			visibleIngles = visibleEspaniol = visibleIdioma4 = visibleIdioma5 = false;
		} else if (idIdioma.equals("cmbIdioma4")) {
			visibleIdioma4 = true;
			visibleIngles = visiblePortugues = visibleEspaniol = visibleIdioma5 = false;
		} else if (idIdioma.equals("cmbIdioma5")) {
			visibleIdioma5 = true;
			visibleIngles = visiblePortugues = visibleIdioma4 = visibleEspaniol = false;
		}
	}

	@Command
	public void cambioIdiomas(@BindingParam("idioma") String idIdioma, @BindingParam("etiqueta") CEtiqueta etiqueta) {
		if (idIdioma.equals("Espanol")) {
			etiqueta.setVisibleEspanol(true);
			etiqueta.setVisibleIngles(false);
			etiqueta.setVisiblePortugues(false);
		} else if (idIdioma.equals("Ingles")) {
			etiqueta.setVisibleEspanol(false);
			etiqueta.setVisibleIngles(true);
			etiqueta.setVisiblePortugues(false);
		} else if (idIdioma.equals("Portugues")) {
			etiqueta.setVisibleEspanol(false);
			etiqueta.setVisibleIngles(false);
			etiqueta.setVisiblePortugues(true);
		}
		BindUtils.postNotifyChange(null, null, etiqueta, "visibleEspanol");
		BindUtils.postNotifyChange(null, null, etiqueta, "visibleIngles");
		BindUtils.postNotifyChange(null, null, etiqueta, "visiblePortugues");
	}

	public void refrescaFilaTemplate(CEtiqueta e) {
		BindUtils.postNotifyChange(null, null, e, "editable");
		BindUtils.postNotifyChange(null, null, e, "color");
	}
	@Command
	public void mostrarImagenProceso(@BindingParam("nombre")String nombre,@BindingParam("componente")Component comp)
	{
		if (nombre!=null) {
			Popup pop = (Popup)Executions.createComponents(
	                "/vista_previa.zul", null, null);
	        //pop.open(comp,"after_start");
	        pop.open(300,100);
	        System.out.println("abre popup");
		}
	}
}
