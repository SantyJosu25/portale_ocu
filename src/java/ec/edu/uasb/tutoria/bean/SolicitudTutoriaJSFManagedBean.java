/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.bean;

import ec.edu.uasb.bean.NavigationJSFManagedBean;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.tutoria.entities.EstudianteUltimamatricula;
import ec.edu.uasb.tutoria.entities.TutSolicitudTutoria;
import ec.edu.uasb.tutoria.entities.TutGrupoTutoria;
import ec.edu.uasb.tutoria.session.EstudianteUltimamatriculaFacadeLocal;
import ec.edu.uasb.tutoria.session.TesisMonografiaFacadeLocal;
import ec.edu.uasb.tutoria.session.TutGrupoTutoriaFacadeLocal;
import ec.edu.uasb.tutoria.session.TutInstanciaFacadeLocal;
import ec.edu.uasb.tutoria.session.TutSolicitudTutoriaFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import ec.edu.uasb.vinculacion.external.entities.Instalacion;
import ec.edu.uasb.vinculacion.external.session.InsAulaFacadeLocal;
import ec.edu.uasb.vinculacion.external.session.InstalacionFacadeLocal;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.context.RequestContext;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marjorie.fiallos
 */
@ManagedBean(name = "solicitudTutoriaForm")
@ViewScoped
public class SolicitudTutoriaJSFManagedBean implements Serializable {

    @EJB
    private TutSolicitudTutoriaFacadeLocal solicitudFacade;
    @EJB
    private TutGrupoTutoriaFacadeLocal grupoFacade;
    @EJB
    private EstudianteUltimamatriculaFacadeLocal estudianteFacade;
    @EJB
    private InstalacionFacadeLocal instalacionFacade;
    @EJB
    private InsAulaFacadeLocal insaulaFacade;
    @EJB
    private TutInstanciaFacadeLocal instancia;
    @EJB
    private TesisMonografiaFacadeLocal tesisMonografiaFacade;
    private TutSolicitudTutoria solicitudSelected;
    private LinkedHashMap<String, String> listTutoresbyfase = new LinkedHashMap<String, String>();
    private List<TutSolicitudTutoria> listSolicitud = new ArrayList<TutSolicitudTutoria>();
    private List<TutSolicitudTutoria> listSolicitudaux = new ArrayList<TutSolicitudTutoria>();
    private List<String[]> listaMaterias = new ArrayList<String[]>();
    private List<String[]> listaDocentes = new ArrayList<String[]>();
    private List<String[]> selectedgrupo = new ArrayList<String[]>();
    private List<String[]> listaTutores = new ArrayList<String[]>();
    private List<String[]> listaRoles = new ArrayList<String[]>();
    private List<Instalacion> listaEdificios = new ArrayList<Instalacion>();
    private List<String[]> listaPisos = new ArrayList<String[]>();
    private List<String[]> listaInstalacion = new ArrayList<String[]>();
    private List<String[]> listaEstudiantes = new ArrayList<String[]>();
    private List<String[]> listaTuto = new ArrayList<String[]>();
    private List v;
    private List r;
    private List v2;
    private List x;
    private List z;
    private TutSolicitudTutoria solicitud;
    private TutGrupoTutoria grupo;
    private Usuario usr;
    private Matricula matri;
    @ManagedProperty(value = "#{navigationMgmtBean}")
    private NavigationJSFManagedBean navJSFManagedBean;
    private Integer anioAcadEstudiante;
    private String programa;
    private String cedula;
    private String fase;
    private String area;
    private String estado;
    private String[] selectedOptions;
    /*CAMBIOS DE TIPOS*/
    private InputTextarea itxtema = new InputTextarea();
    private InputTextarea itxtobserv = new InputTextarea();
    private InputTextarea itxtlink = new InputTextarea();
    private InputTextarea itxtcorreo = new InputTextarea();
    private Date fecSolicitud;
    private Date hora;
    private Date fechaactual;
    private Date fechanow;
    private Date fechaSolicitud;
    private String horaIni;
    private String horafinal;
    private HtmlSelectOneMenu smtipogrupo = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smtiptutoria = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smtutorporfase = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smmateria = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smdocente = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smtutor = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smrol = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smmodalidad = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smedifcio = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smpiso = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu sminstalacion = new HtmlSelectOneMenu();
    private HtmlSelectOneMenu smDuracion = new HtmlSelectOneMenu();
    private EstudianteUltimamatricula mat = new EstudianteUltimamatricula();
    private boolean verPanelCompas = false;
    private boolean btutorporfase = false;
    private boolean bpresencial = false;
    private boolean bvirtual = false;
    private boolean bmensaje = false;
    private boolean aceptar = false;
    private boolean guardar = false;
    private boolean cancelar = false;
    private boolean anular = false;
    private boolean bdocente = false;
    private boolean btutor = false;
    private boolean beditar = false;
    private boolean verPanel = false;
    private boolean verLabel = false;
    private boolean isCheck = false;
    private SelectItem[] estados = {new SelectItem("", "Todos"), new SelectItem("S", "Solicitada"), new SelectItem("A", "Aprobada"), new SelectItem("O", "Aprobada por Solicitante"),
        new SelectItem("C", "Cambio"), new SelectItem("F", "Cambio no aceptado"),
        new SelectItem("N", "Anulada"), new SelectItem("T", "Impartida")};
    //<editor-fold defaultstate="collapsed" desc="Propiedades ">

    public Integer getAnioAcadEstudiante() {
        return anioAcadEstudiante;
    }

    public void setAnioAcadEstudiante(Integer anioAcadEstudiante) {
        this.anioAcadEstudiante = anioAcadEstudiante;
    }

    public TutSolicitudTutoria getSolicitudSelected() {
        return solicitudSelected;
    }

    public void setSolicitudSelected(TutSolicitudTutoria solicitudSelected) {
        this.solicitudSelected = solicitudSelected;
    }

    public List<TutSolicitudTutoria> getListSolicitud() {
        return listSolicitud;
    }

    public void setListSolicitud(List<TutSolicitudTutoria> listSolicitud) {
        this.listSolicitud = listSolicitud;
    }

    public NavigationJSFManagedBean getNavJSFManagedBean() {
        return navJSFManagedBean;
    }

    public void setNavJSFManagedBean(NavigationJSFManagedBean navJSFManagedBean) {
        this.navJSFManagedBean = navJSFManagedBean;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public List<Instalacion> getListaEdificios() {
        return listaEdificios;
    }

    public void setListaEdificios(List<Instalacion> listaEdificios) {
        this.listaEdificios = listaEdificios;
    }

    public List<String[]> getListaInstalacion() {
        return listaInstalacion;
    }

    public void setListaInstalacion(List<String[]> listaInstalacion) {
        this.listaInstalacion = listaInstalacion;
    }

    public TutGrupoTutoria getGrupo() {
        return grupo;
    }

    public void setGrupo(TutGrupoTutoria grupo) {
        this.grupo = grupo;
    }

    public EstudianteUltimamatricula getMat() {
        return mat;
    }

    public void setMat(EstudianteUltimamatricula mat) {
        this.mat = mat;
    }

    public HtmlSelectOneMenu getSmtipogrupo() {
        return smtipogrupo;
    }

    public void setSmtipogrupo(HtmlSelectOneMenu smtipogrupo) {
        this.smtipogrupo = smtipogrupo;
    }

    public InputTextarea getItxtema() {
        return itxtema;
    }

    public void setItxtema(InputTextarea itxtema) {
        this.itxtema = itxtema;
    }

    public Date getFecSolicitud() {
        return fecSolicitud;
    }

    public void setFecSolicitud(Date fecSolicitud) {
        this.fecSolicitud = fecSolicitud;
    }

    public HtmlSelectOneMenu getSmtiptutoria() {
        return smtiptutoria;
    }

    public void setSmtiptutoria(HtmlSelectOneMenu smtiptutoria) {
        this.smtiptutoria = smtiptutoria;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public boolean isVerPanelCompas() {
        return verPanelCompas;
    }

    public void setVerPanelCompas(boolean verPanelCompas) {
        this.verPanelCompas = verPanelCompas;
    }

    public LinkedHashMap<String, String> getListTutoresbyfase() {
        return listTutoresbyfase;
    }

    public void setListTutoresbyfase(LinkedHashMap<String, String> listTutoresbyfase) {
        this.listTutoresbyfase = listTutoresbyfase;
    }

    public TutSolicitudTutoria getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(TutSolicitudTutoria solicitud) {
        this.solicitud = solicitud;
    }

    public boolean isBtutorporfase() {
        return btutorporfase;
    }

    public void setBtutorporfase(boolean btutorporfase) {
        this.btutorporfase = btutorporfase;
    }

    public HtmlSelectOneMenu getSmtutorporfase() {
        return smtutorporfase;
    }

    public void setSmtutorporfase(HtmlSelectOneMenu smtutorporfase) {
        this.smtutorporfase = smtutorporfase;
    }

    public InputTextarea getItxtobserv() {
        return itxtobserv;
    }

    public void setItxtobserv(InputTextarea itxtobserv) {
        this.itxtobserv = itxtobserv;
    }

    public HtmlSelectOneMenu getSmmodalidad() {
        return smmodalidad;
    }

    public void setSmmodalidad(HtmlSelectOneMenu smmodalidad) {
        this.smmodalidad = smmodalidad;
    }

    public HtmlSelectOneMenu getSmedifcio() {
        return smedifcio;
    }

    public void setSmedifcio(HtmlSelectOneMenu smedifcio) {
        this.smedifcio = smedifcio;
    }

    public HtmlSelectOneMenu getSmpiso() {
        return smpiso;
    }

    public void setSmpiso(HtmlSelectOneMenu smpiso) {
        this.smpiso = smpiso;
    }

    public HtmlSelectOneMenu getSminstalacion() {
        return sminstalacion;
    }

    public void setSminstalacion(HtmlSelectOneMenu sminstalacion) {
        this.sminstalacion = sminstalacion;
    }

    public boolean isBpresencial() {
        return bpresencial;
    }

    public void setBpresencial(boolean bpresencial) {
        this.bpresencial = bpresencial;
    }

    public boolean isBvirtual() {
        return bvirtual;
    }

    public void setBvirtual(boolean bvirtual) {
        this.bvirtual = bvirtual;
    }

    public boolean isBmensaje() {
        return bmensaje;
    }

    public void setBmensaje(boolean bmensaje) {
        this.bmensaje = bmensaje;
    }

    public InputTextarea getItxtlink() {
        return itxtlink;
    }

    public void setItxtlink(InputTextarea itxtlink) {
        this.itxtlink = itxtlink;
    }

    public InputTextarea getItxtcorreo() {
        return itxtcorreo;
    }

    public void setItxtcorreo(InputTextarea itxtcorreo) {
        this.itxtcorreo = itxtcorreo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isAceptar() {
        return aceptar;
    }

    public void setAceptar(boolean aceptar) {
        this.aceptar = aceptar;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public boolean isAnular() {
        return anular;
    }

    public void setAnular(boolean anular) {
        this.anular = anular;
    }

    public List<String[]> getListaPisos() {
        return listaPisos;
    }

    public void setListaPisos(List<String[]> listaPisos) {
        this.listaPisos = listaPisos;
    }

    public List<String[]> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<String[]> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<String[]> getListaDocentes() {
        return listaDocentes;
    }

    public void setListaDocentes(List<String[]> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }

    public List<String[]> getListaTutores() {
        return listaTutores;
    }

    public void setListaTutores(List<String[]> listaTutores) {
        this.listaTutores = listaTutores;
    }

    public HtmlSelectOneMenu getSmmateria() {
        return smmateria;
    }

    public void setSmmateria(HtmlSelectOneMenu smmateria) {
        this.smmateria = smmateria;
    }

    public HtmlSelectOneMenu getSmdocente() {
        return smdocente;
    }

    public void setSmdocente(HtmlSelectOneMenu smdocente) {
        this.smdocente = smdocente;
    }

    public HtmlSelectOneMenu getSmtutor() {
        return smtutor;
    }

    public void setSmtutor(HtmlSelectOneMenu smtutor) {
        this.smtutor = smtutor;
    }

    public boolean isBdocente() {
        return bdocente;
    }

    public void setBdocente(boolean bdocente) {
        this.bdocente = bdocente;
    }

    public boolean isBtutor() {
        return btutor;
    }

    public void setBtutor(boolean btutor) {
        this.btutor = btutor;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }

    public boolean isBeditar() {
        return beditar;
    }

    public void setBeditar(boolean beditar) {
        this.beditar = beditar;
    }

    public List<TutSolicitudTutoria> getListSolicitudaux() {
        return listSolicitudaux;
    }

    public void setListSolicitudaux(List<TutSolicitudTutoria> listSolicitudaux) {
        this.listSolicitudaux = listSolicitudaux;
    }

    public HtmlSelectOneMenu getSmDuracion() {
        return smDuracion;
    }

    public void setSmDuracion(HtmlSelectOneMenu smDuracion) {
        this.smDuracion = smDuracion;
    }

    public List<String[]> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<String[]> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    public List<String[]> getListaTuto() {
        return listaTuto;
    }

    public void setListaTuto(List<String[]> listaTuto) {
        this.listaTuto = listaTuto;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public List<String[]> getSelectedgrupo() {
        return selectedgrupo;
    }

    public void setSelectedgrupo(List<String[]> selectedgrupo) {
        this.selectedgrupo = selectedgrupo;
    }

    public boolean isVerPanel() {
        return verPanel;
    }

    public void setVerPanel(boolean verPanel) {
        this.verPanel = verPanel;
    }

    public boolean isVerLabel() {
        return verLabel;
    }

    public void setVerLabel(boolean verLabel) {
        this.verLabel = verLabel;
    }

    public Date getFechaactual() {
        return fechaactual;
    }

    public void setFechaactual(Date fechaactual) {
        this.fechaactual = fechaactual;
    }

    public Matricula getMatri() {
        return matri;
    }

    public void setMatri(Matricula matri) {
        this.matri = matri;
    }

    public List<String[]> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<String[]> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public HtmlSelectOneMenu getSmrol() {
        return smrol;
    }

    public void setSmrol(HtmlSelectOneMenu smrol) {
        this.smrol = smrol;
    }

    public boolean isIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public SelectItem[] getEstados() {
        return estados;
    }

    public void setEstados(SelectItem[] estados) {
        this.estados = estados;
    }

    public Date getFechanow() {
        return fechanow;
    }

    public void setFechanow(Date fechanow) {
        this.fechanow = fechanow;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(String horafinal) {
        this.horafinal = horafinal;
    }

    //</editor-fold>
    /**
     * Creates a new instance of SolicitudTutoriaJSFManagedBean
     */
    public SolicitudTutoriaJSFManagedBean() {
        //this.setPaginaMant("/pages/tutoria/solicitudTutoria");
        solicitudSelected = null;
    }

    @PostConstruct
    public void init() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
        solicitud = new TutSolicitudTutoria();
        grupo = new TutGrupoTutoria();
        estado = null;
        selectedgrupo.clear();
        this.itxtema.setValue(null);
        this.smtipogrupo.setValue(null);
        listSolicitud.clear();
        obtenerCampos();
        recuperaSolicitud();
    }
// <editor-fold defaultstate="collapsed" desc="OBTENER CAMPOS POR DEFECTO">

    private void obtenerCampos() {
        mat = estudianteFacade.findStudent(usr.getUsuCodigo());
        anioAcadEstudiante = mat.getAnio().intValue();
        area = mat.getArea();
        programa = mat.getPrograma();
        cedula = usr.getUsuCedPass();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        fechaactual = calendar.getTime();
        StringBuilder sql = new StringBuilder();
        sql.append(" EXEC interfaseOcu.GESTIONACADEMICA.sp_certificado_notas  @cedula = ").append("'").append(cedula).append("'");
        v = solicitudFacade.ejecutaSqlList(sql.toString());
        if (!v.isEmpty()) {
            Object[] object = (Object[]) v.get(0);
            int a = 0;
            int b = 0;
            if (object[36] != null) {
                a = Math.round(Float.parseFloat(object[36].toString()));
            }
            if (object[37] != null) {
                b = Math.round(Float.parseFloat(object[37].toString()));
            }
            if (a == 0 && b == 0) {
                fase = "FASE DOCENCIA";
            } else {
                if (a <= b) {
                    fase = "FASE DE INVESTIGACIÓN";
                } else {
                    fase = "FASE DOCENCIA";
                }
            }
        } else {
            fase = "FASE DOCENCIA";
        }
        listaEdificios = instalacionFacade.getListaEdificios();

    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="RESET">  
    private void reset() {
        solicitud = new TutSolicitudTutoria();
        estado = null;
        grupo = new TutGrupoTutoria();
        solicitudSelected = null;
        anioAcadEstudiante = null;
        area = null;
        programa = null;
        isCheck = false;
        cedula = null;
        selectedgrupo.clear();
        hora = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 8);
        calendar.set(Calendar.MINUTE, 00);
        hora = calendar.getTime();
        fecSolicitud = null;
        smtipogrupo.setValue(null);
        smtiptutoria.setValue(null);
        smtutorporfase.setValue(null);
        smmateria.setValue(null);
        smdocente.setValue(null);
        smtutor.setValue(null);
        smrol.setValue(null);
        sminstalacion.setValue(null);
        smedifcio.setValue(null);
        smmodalidad.setValue(null);
        smpiso.setValue(null);
        smDuracion.setValue(null);
        itxtema.setValue(null);
        itxtobserv.setValue(null);
        itxtlink.setValue(null);
        itxtcorreo.setValue(null);
        listaTuto.clear();
        listaEstudiantes.clear();
        listTutoresbyfase.clear();
        listaInstalacion.clear();
        listaEdificios.clear();
        listaPisos.clear();
        listaDocentes.clear();
        listaMaterias.clear();
        listaTutores.clear();
        listaRoles.clear();
        btutorporfase = false;
        verPanelCompas = false;
        bpresencial = false;
        bvirtual = false;
        bmensaje = false;
        aceptar = false;
        anular = false;
        guardar = false;
        cancelar = false;
        bdocente = false;
        btutor = false;
        beditar = false;
        verLabel = false;
        verPanel = false;
        horaIni = "07";
        horafinal = "19";
        RequestContext.getCurrentInstance().execute("inlineTimeWidget.enable();");

    }

// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON NUEVO">  
    public void nuevoButton_processAction(ActionEvent ae) {
        reset();
        obtenerCampos();
        guardar = true;
        cancelar = true;
        beditar = false;
        bvirtual = false;

    }

// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON CANCELAR">  
// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON EDITAR">  
    public void editarButton_processAction(ActionEvent ae) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");

        solicitud = solicitudSelected;
        anioAcadEstudiante = (solicitud.getTstAnioEstudiante() == null ? null : solicitud.getTstAnioEstudiante());
        programa = (solicitud.getPrograma() == null ? null : solicitud.getPrograma());
        area = (solicitud.getArea() == null ? null : solicitud.getArea());
        fase = (solicitud.getTstFaseTutoria() == null ? null : solicitud.getTstFaseTutoria());
        itxtema.setValue(solicitud.getTstTema());
        fecSolicitud = (solicitud.getTstFechaTutoria() == null ? null : new java.sql.Date(solicitud.getTstFechaTutoria().getTime()));
        try {
            hora = (solicitud.getTstHora() == null ? null : sf.parse(solicitud.getTstHora()));
        } catch (ParseException ex) {
            Logger.getLogger(SolicitudTutoriaJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaEdificios = instalacionFacade.getListaEdificios();
        smtiptutoria.setValue(solicitud.getTstTipoTutoria());
        smtipogrupo.setValue(solicitud.getTstTipoGrupo());
        smmodalidad.setValue(solicitud.getTstModalidad());
        smedifcio.setValue(solicitud.getTstEdificio());
        smpiso.setValue(solicitud.getTstPiso());
        sminstalacion.setValue(solicitud.getTstInstalacion());
        smmateria.setValue(solicitud.getTstMateria());
        smdocente.setValue(solicitud.getTstCodProfesor());
        smDuracion.setValue(solicitud.getTstDuracion());
        itxtcorreo.setValue(solicitud.getTstCorreo());
        itxtlink.setValue(solicitud.getTstLink());
        itxtobserv.setValue(solicitud.getTstObservacion());
        if (solicitud.getTstCodProfesor() != null) {
            smtutorporfase.setValue("D");
            horaIni = "07";
            horafinal = "19";
        } else if (solicitud.getTstTutor() != null) {
            horaIni = "08";
            horafinal = "16";
            smtutorporfase.setValue("T");
        } else if (solicitud.getTstCodInstancia() != null) {
            horaIni = "08";
            horafinal = "16";
            smtutorporfase.setValue(solicitud.getTstCodInstancia().getTinCodigo());
        }
        if (smmodalidad.getValue().toString().equalsIgnoreCase("V")) {
            bvirtual = true;
        }
        smtutor.setValue(solicitud.getTstTutor());
        smrol.setValue(solicitud.getTstRolTesis());
        itxtlink.setValue(solicitud.getTstLink());
        mostrarProfeorTutor();
        classmatesList();
        if (solicitud.getTstTipoGrupo().equals("G")) {
            verPanel = true;
            verPanelCompas = false;
            verPanel = true;
            StringBuilder sql = new StringBuilder();
            sql.append("select TGT_CODIGO,TGT_CODIGO_ESTUDIANTE from interfaseOcu.GESTIONACADEMICA.TUT_GRUPO_TUTORIA where TST_CODIGO_SOLICITUD=").append(solicitud.getTstCodigo());
            v = solicitudFacade.ejecutaSqlList(sql.toString());
            if (v.size() > 0) {
                for (int i = 0; i < v.size(); i++) {
                    StringBuilder q = new StringBuilder();
                    Object[] object = (Object[]) v.get(i);
                    q.append("select top(1) e.cod_Estudiante,e.nom_Estudiante +' '+ e.apell_Estudiante from interfaseOcu.GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA as e where e.cod_Estudiante = ").append(Long.parseLong(object[1].toString())).append("order by anio desc");
                    z = solicitudFacade.ejecutaSqlList(q.toString());
                    if (z.size() > 0) {
                        for (int j = 0; j < z.size(); j++) {
                            Object[] o = (Object[]) z.get(j);
                            String[] estudiante;
                            estudiante = new String[2];
                            estudiante[0] = o[0].toString();
                            estudiante[1] = o[1].toString();
                            listaTuto.add(estudiante);
                        }
                    }

                }
            } else {
                listaTuto.clear();
            }
        }

        docenteByMateria();
        pisoByEdificio();
        instalcionByPiso();
        tipTutorporfasevalueChangeListener();
        modalidadvalueChangeListener();
        if (solicitud.getTstTutor() != null) {
            mostrarRolTutor();
        }
        RequestContext.getCurrentInstance().update("formMantTut:panelMant");
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        fechanow = s.parse(s.format(fechaactual));
        fechaSolicitud = s.parse(s.format(fecSolicitud));
        if (fechaSolicitud.before(fechanow)) {
            isCheck = true;
        }
    }
    // </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON SALVAR">  

    public void guardarButton_processAction(ActionEvent ae) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
            Calendar cal = Calendar.getInstance();
            StringBuilder mensaje;
            solicitud.setTstAnioEstudiante(anioAcadEstudiante);
            solicitud.setPrograma(programa);
            solicitud.setCodArea(mat.getCodigoFacultad().longValue());
            solicitud.setArea(area);
            solicitud.setTstFaseTutoria(fase);
            solicitud.setCodigoEsp(Long.parseLong(mat.getEstudianteUltimamatriculaPK().getCodigoEsp()));
            solicitud.setTstAnioSolicitud(cal.get(Calendar.YEAR));
            solicitud.setTstCodigoSolicitante(usr.getUsuCodigo());
            solicitud.setTstTipoSolicitante("ESTUDIANTE");
            solicitud.setTstTema(itxtema.getValue() == null ? null : itxtema.getValue().toString().toUpperCase());
            solicitud.setTstFechaTutoria(new java.sql.Date(fecSolicitud.getTime()));
            solicitud.setTstHora(sf.format(hora));
            solicitud.setTstDuracion(smDuracion.getValue() == null ? null : Integer.parseInt(smDuracion.getValue().toString()));
            solicitud.setTstTipoTutoria(smtiptutoria.getValue() == null ? null : smtiptutoria.getValue().toString());
            solicitud.setTstTutor(smtutor.getValue() == null ? null : Long.parseLong(smtutor.getValue().toString()));
            solicitud.setTstRolTesis(smrol.getValue() == null ? null : smrol.getValue().toString());
            solicitud.setTstCodProfesor(smdocente.getValue() == null && smdocente.getValue() == null ? null : Long.parseLong(smdocente.getValue().toString()));
            solicitud.setTstTipoGrupo(smtipogrupo.getValue() == null ? null : smtipogrupo.getValue().toString());
            solicitud.setTstModalidad(smmodalidad.getValue() == null ? null : smmodalidad.getValue().toString());
            //  solicitud.setTstEdificio(smedifcio.getValue() == null ? null : smedifcio.getValue().toString());
            //  solicitud.setTstPiso(smpiso.getValue() == null ? null : smpiso.getValue().toString());
            solicitud.setTstCorreo(itxtcorreo.getValue() == null ? null : itxtcorreo.getValue().toString());
            solicitud.setTstLink(itxtlink.getValue() == null ? null : itxtlink.getValue().toString());
            solicitud.setTstEstadoSolicitado("S");
            solicitud.setTstInstalacion(sminstalacion.getValue() == null ? null : sminstalacion.getValue().toString());
            solicitud.setTstMateria(smmateria.getValue() == null ? null : smmateria.getValue().toString());
            solicitud.setTstCodEjercicio(mat.getEjercicio().longValue());
            solicitud.setTstObservacion(itxtobserv.getValue() == null ? null : itxtobserv.getValue().toString().toUpperCase());
            if (smtutorporfase.getValue() != null) {
                if (!smtutorporfase.getValue().equals("D") && !smtutorporfase.getValue().equals("T")) {

                    solicitud.setTstCodInstancia(smtutorporfase.getValue() == null ? null : instancia.buscarCodigo(Long.parseLong(smtutorporfase.getValue().toString())));
                }
            }
            if (solicitud.getTstTipoGrupo().equalsIgnoreCase("G")) {
                if (solicitud.getTstCodigo() == null) {
                    if (!selectedgrupo.isEmpty()) {
                        if (selectedgrupo.size() > 0) {
                            solicitud.setTstUsuarioCrea(usr.getUsuCedPass());
                            solicitud.setTstFechaCrea(new Date());
                            solicitudFacade.create(solicitud);
                            for (int i = 0; i < selectedgrupo.size(); i++) {
                                Object[] object = selectedgrupo.get(i);
                                grupo.setTgtCodigoEstudiante(Long.parseLong(object[0].toString()));
                                grupo.setTstCodigoSolicitud(solicitud);
                                grupo.setTgtUsuarioCrea(usr.getUsuCedPass());
                                grupo.setTgtFechaCrea(new Date());
                                grupoFacade.create(grupo);

                            }
                            JsfUtil.addSuccessMessage(null, "Solicitud Guardada!");
                        }
                    } else {
                        JsfUtil.addErrorMessage(null, "Error al Guardar Solicitud! Listado de Compañeros:se requiere un valor");
                    }
                }
            } else {
                if (solicitud.getTstCodigo() == null) {
                    solicitud.setTstUsuarioCrea(usr.getUsuCedPass());
                    solicitud.setTstFechaCrea(new Date());
                    solicitudFacade.create(solicitud);
                    JsfUtil.addSuccessMessage(null, "Solicitud Guardada!");
                } else {
                    solicitud.setTstEstadoSolicitado(estado);
                    solicitud.setTstUsuarioModifica(usr.getUsuCedPass());
                    solicitud.setTstFechaModifica(new Date());
                    solicitudFacade.edit(solicitud);

                    JsfUtil.addSuccessMessage(null, "Solicitud Guardada!");
                }
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Guardar Solicitud");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            solicitudFacade.solicitudTutoriaEnviaCorreo(solicitud.getTstCodigo());
            solicitud = new TutSolicitudTutoria();
            estado = null;
            selectedgrupo = new ArrayList<String[]>();
            recuperaSolicitud();
        }
        RequestContext.getCurrentInstance().update("formSolicitudTuto:dataSolicitud");
        RequestContext.getCurrentInstance().execute("mantTutWidget.hide();");

    }

// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON RECHAZAR">  
    public void rechazarButton_processAction(ActionEvent ae) {
        try {
            solicitud.setTstEstadoSolicitado("F");
            solicitud.setTstAprobadoPor(usr.getUsuCedPass());
            solicitud.setTstUsuarioModifica(usr.getUsuCedPass());
            solicitud.setTstFechaModifica(new Date());
            solicitudFacade.edit(solicitud);
            JsfUtil.addSuccessMessage(null, "Solicitud Guardada!");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Rechazar Solicitud");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            solicitudFacade.solicitudTutoriaEnviaCorreo(solicitud.getTstCodigo());
            solicitud = new TutSolicitudTutoria();
            estado = null;
        }
        RequestContext.getCurrentInstance().update("formSolicitudTuto:dataSolicitud");
        RequestContext.getCurrentInstance().execute("mantTutWidget.hide();");

    }

// </editor-fold>   
// <editor-fold defaultstate="collapsed" desc="BOTON ACEPTAR">  
    public void aceptarButton_processAction(ActionEvent ae) {
        try {
            solicitud.setTstEstadoSolicitado("O");
            solicitud.setTstAprobadoPor(usr.getUsuCedPass());
            solicitud.setTstUsuarioModifica(usr.getUsuCedPass());
            solicitud.setTstFechaModifica(new Date());
            solicitudFacade.edit(solicitud);

            JsfUtil.addSuccessMessage(null, "Solicitud Guardada!");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Aceptar Solicitud");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            solicitudFacade.solicitudTutoriaEnviaCorreo(solicitud.getTstCodigo());
            solicitud = new TutSolicitudTutoria();
            estado = null;
        }
        RequestContext.getCurrentInstance().update("formSolicitudTuto:dataSolicitud");
        RequestContext.getCurrentInstance().execute("mantTutWidget.hide();");

    }

// </editor-fold> 
// <editor-fold defaultstate="collapsed" desc="BOTON NEGAR">  
    public void negarButton_processAction(ActionEvent ae) {
        try {
            solicitud.setTstEstadoSolicitado("N");
            solicitud.setTstAprobadoPor(usr.getUsuCedPass());
            solicitud.setTstUsuarioModifica(usr.getUsuCedPass());
            solicitud.setTstFechaModifica(new Date());
            solicitudFacade.edit(solicitud);
            JsfUtil.addSuccessMessage(null, "Solicitud Guardada!");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(null, "Error al Negar Solicitud");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            solicitudFacade.solicitudTutoriaEnviaCorreo(solicitud.getTstCodigo());
            solicitud = new TutSolicitudTutoria();
            estado = null;
        }
        RequestContext.getCurrentInstance().update("formSolicitudTuto:dataSolicitud");
        RequestContext.getCurrentInstance().execute("mantTutWidget.hide();");

    }

// </editor-fold>      
// <editor-fold defaultstate="collapsed" desc="VALUECHANGELISTENER">  VALUECHANGELISTENER
    public void mostrarRolTutor() {
        bdocente = false;
        btutor = true;
        listaRoles.clear();
        r = tesisMonografiaFacade.obtenerListaRoles(mat.getCodEstudiante(), mat.getAnioInicial().intValue(), mat.getEstudianteUltimamatriculaPK().getCodigoEsp(), Integer.parseInt(smtutor.getValue().toString()));
        for (int j = 0; j < r.size(); j++) {
            Object[] object = (Object[]) r.get(j);
            String[] rol;
            rol = new String[2];
            rol[0] = object[0].toString();
            rol[1] = object[1].toString();
            listaRoles.add(rol);
        }
    }

    public void mostrarProfeorTutor() {
        bdocente = false;
        btutor = false;
        listaMaterias.clear();
        listaTutores.clear();
        if (!beditar) {
            smmateria.setValue(null);
            smdocente.setValue(null);
            smtutor.setValue(null);
        }
        if (smtutorporfase.getValue() != null) {
            if (smtutorporfase.getValue().toString().equalsIgnoreCase("D")) {
                bdocente = true;

                StringBuilder sql = new StringBuilder();
                sql.append(" EXEC interfaseOcu.GESTIONACADEMICA.sp_getMatEstudiante  @codestudiante = ").append(usr.getUsuCodigo()).append(" ,@codEsp = ").append(mat.getEstudianteUltimamatriculaPK().getCodigoEsp());
                v = solicitudFacade.ejecutaSqlList(sql.toString());
                if (v.size() > 0) {
                    for (int i = 0; i < v.size(); i++) {
                        Object[] object = (Object[]) v.get(i);
                        String[] materia;
                        materia = new String[2];
                        materia[0] = object[0].toString();
                        materia[1] = object[1].toString();
                        listaMaterias.add(materia);
                    }
                }
            } else if (smtutorporfase.getValue().toString().equalsIgnoreCase("T")) {

                btutor = true;
                x = tesisMonografiaFacade.obtenerListaTutores(mat.getCodEstudiante(), mat.getAnioInicial().intValue(), mat.getEstudianteUltimamatriculaPK().getCodigoEsp());
                for (int j = 0; j < x.size(); j++) {
                    Object[] object = (Object[]) x.get(j);
                    String[] tutor;
                    tutor = new String[2];
                    tutor[0] = object[0].toString();
                    tutor[1] = object[1].toString();
                    listaTutores.add(tutor);
                }
            }
            if (smtutorporfase.getValue().toString().equalsIgnoreCase("D")) {
                hora = new Date();
                final Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.AM_PM, Calendar.AM);
                calendar.set(Calendar.HOUR, 7);
                calendar.set(Calendar.MINUTE, 00);
                hora = calendar.getTime();
                horaIni = "07";
                horafinal = "19";
            } else {
                hora = new Date();
                final Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.AM_PM, Calendar.AM);
                calendar.set(Calendar.HOUR, 8);
                calendar.set(Calendar.MINUTE, 30);
                hora = calendar.getTime();
                horaIni = "08";
                horafinal = "16";
            }
        }
    }

    public void docenteByMateria() {
        listaDocentes.clear();
        if (smmateria.getValue() != null) {
            StringBuilder sql = new StringBuilder();
            sql.append(" EXEC interfaseOcu.GESTIONACADEMICA.sp_getMatProfEstudiante  @codestudiante = ").append(usr.getUsuCodigo()).append(" ,@codEsp = ").append(mat.getEstudianteUltimamatriculaPK().getCodigoEsp());
            v = solicitudFacade.ejecutaSqlList(sql.toString());
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                String[] docentes;
                docentes = new String[2];
                if (smmateria.getValue().toString().equalsIgnoreCase(object[9].toString())) {
                    docentes[0] = object[4].toString();
                    docentes[1] = object[5].toString() + ' ' + object[6].toString();
                    listaDocentes.add(docentes);
                }
            }

        }
    }

    public void pisoByEdificio() {
        listaPisos.clear();
        if (smedifcio.getValue() != null) {
            listaPisos = insaulaFacade.getListaPisobyEdificio(smedifcio.getValue().toString());
        }
    }

    public void instalcionByPiso() {
        listaInstalacion.clear();
        if (smpiso.getValue() != null && smedifcio.getValue() != null) {
            listaInstalacion = insaulaFacade.getListaInstalcionbyPiso(smpiso.getValue().toString(), smedifcio.getValue().toString());
        }
    }

    public void classmatesList() {

        listaEstudiantes.clear();
        verPanelCompas = false;
        verLabel = false;
        if (smtipogrupo.getValue() != null) {
            if (smtipogrupo.getValue().toString().equalsIgnoreCase("G")) {
                verPanelCompas = true;
                verLabel = true;
                listaEstudiantes = estudianteFacade.obtenerListaCompañeros(mat.getEjercicio(), mat.getEstudianteUltimamatriculaPK().getCodigoEsp(), mat.getCodEstudiante(), mat.getAnioInicial().intValue());
            }
        }
    }

    public void tipTutorporfasevalueChangeListener() {
        listTutoresbyfase.clear();
        btutorporfase = true;
        btutor = false;
        bdocente = false;
        if (!beditar) {
            smdocente.setValue(null);
            smtutor.setValue(null);
            smmateria.setValue(null);
        }
        if (smtiptutoria.getValue() != null) {
            if (smtiptutoria.getValue().toString().equals("A") && fase.equals("FASE DOCENCIA")) {
                listTutoresbyfase.put("DOCENTE", "D");

            } else if (smtiptutoria.getValue().toString().equals("A") && fase.equals("FASE DE INVESTIGACIÓN")) {
                listTutoresbyfase.put("TUTOR", "T");
                listTutoresbyfase.put("DOCENTE", "D");

            }

            v = instancia.buscarCodigobyTipo(smtiptutoria.getValue().toString());
            for (int i = 0; i < v.size(); i++) {
                Object[] object = (Object[]) v.get(i);
                listTutoresbyfase.put(object[1].toString(), object[0].toString());
            }
        }

    }

    public void modalidadvalueChangeListener() {
        bpresencial = false;
        bvirtual = false;
        bmensaje = false;
        if (!beditar) {
            smedifcio.setValue(null);
            smpiso.setValue(null);
            sminstalacion.setValue(null);
            itxtlink.setValue(null);
            itxtcorreo.setValue(null);
        }
        if (smmodalidad.getValue() != null) {
            if (smmodalidad.getValue().toString().equalsIgnoreCase("P") && !beditar) {
                bpresencial = false;
            } else if (smmodalidad.getValue().toString().equalsIgnoreCase("V") && !beditar) {
                bvirtual = false;

            } else if (smmodalidad.getValue().toString().equalsIgnoreCase("C")) {
                bmensaje = true;
            } else if (smmodalidad.getValue().toString().equalsIgnoreCase("P") && beditar) {
                bpresencial = true;
            } else if (smmodalidad.getValue().toString().equalsIgnoreCase("V") && beditar) {
                bvirtual = true;

            }
        }

    }

    public void cambiarEstadoAceptar() {
        aceptar = true;
        guardar = false;
        cancelar = false;
        anular = false;
        beditar = true;

    }

    public void cambiarEstadoAnular() {
        anular = true;
        aceptar = false;
        guardar = false;
        cancelar = true;
        beditar = true;

    }

    public void cambiarEstadoR() {
        estado = null;
        estado = "R";
    }

// </editor-fold>    
// <editor-fold defaultstate="collapsed" desc="RECUPERALISTA">  RECUPERA LISTA
    private void recuperaSolicitud() {
        TutSolicitudTutoria soltaux = new TutSolicitudTutoria();
        listSolicitud = new ArrayList<TutSolicitudTutoria>();
        listSolicitudaux = new ArrayList<TutSolicitudTutoria>();
        listSolicitudaux.clear();
        Calendar cal = Calendar.getInstance();
        listSolicitudaux = solicitudFacade.findRequest(usr.getUsuCodigo(), cal.get(Calendar.YEAR));
        for (int i = 0; i < listSolicitudaux.size(); i++) {
            soltaux = listSolicitudaux.get(i);
            
            StringBuilder sql = new StringBuilder();
            StringBuilder sql2 = new StringBuilder();
            sql.append(" EXEC interfaseOcu.GESTIONACADEMICA.sp_getMatProfEstudiante "
                    + " @codestudiante = ").append(soltaux.getTstCodigoSolicitante())
                    .append(" ,@codEsp = ").append(soltaux.getCodigoEsp());
            v = solicitudFacade.ejecutaSqlList(sql.toString());
            if (v.size() > 0) {
                for (int j = 0; j < v.size(); j++) {
                    Object[] object = (Object[]) v.get(j);
                    if (soltaux.getTstCodProfesor() != null) {
                        if (soltaux.getTstCodProfesor() == Long.parseLong(object[4].toString())) {
                            soltaux.setDocente(object[6].toString() + ' ' + object[5].toString());
                        }
                    }
                }
            }
            if (soltaux.getTstTutor() != null) {
                sql2.append("SELECT DISTINCT(COD_PROFESOR) ,NOMBRES_PROFESOR "
                        + " from interfaseOcu.GESTIONACADEMICA.TESIS_MONOGRAFIA"
                        + " WHERE  COD_PROFESOR = ").append(soltaux.getTstTutor());
                v2 = solicitudFacade.ejecutaSqlList(sql2.toString());
                for (int k = 0; k < v2.size(); k++) {
                    Object[] object = (Object[]) v2.get(k);
                    soltaux.setTutor(object[1].toString());

                }
            }
            
                listSolicitud.add(i, soltaux);
                


        }
        //  RequestContext.getCurrentInstance().update("formSolicitudTuto:dataSolicitud");

    }

    public void cargarDatos() {
        recuperaSolicitud();
    }
// </editor-fold>

    public void verDocumentoButton_processAction() {
        Map<String, Object> params = new HashMap();
        RequestContext rc = RequestContext.getCurrentInstance();

    }

}
