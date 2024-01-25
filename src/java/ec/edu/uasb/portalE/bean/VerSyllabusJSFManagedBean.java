/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.bean;

import ec.edu.uasb.bean.NavigationJSFManagedBean;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.portalE.entities.AsignaturaModel;
import ec.edu.uasb.portalE.entities.Horario;
import ec.edu.uasb.portalE.session.AsignaturaFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "verSyllabus")
@ViewScoped
public class VerSyllabusJSFManagedBean {

    @EJB
    private AsignaturaFacadeLocal asignaturaFacade;
    private List<AsignaturaModel> asignaturas = new ArrayList<AsignaturaModel>();
    private AsignaturaModel asignaturaSelected;
    private String trimestre = null;
    private Matricula matri;
    private Date fechaCorte;

//    @ManagedProperty(value = "#{navigationMgmtBean}")
    private NavigationJSFManagedBean navigationJSFManagedBean;
//
//    public NavigationJSFManagedBean getNavigationJSFManagedBean() {
//        return navigationJSFManagedBean;
//    }
//
//    public void setNavigationJSFManagedBean(NavigationJSFManagedBean navigationJSFManagedBean) {
//        this.navigationJSFManagedBean = navigationJSFManagedBean;
//    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    //<editor-fold defaultstate="collapsed" desc="Propiedades">
    /**
     * @return the asignaturas
     */
    public List<AsignaturaModel> getAsignaturas() {
        return asignaturas;
    }

    /**
     * @param asignaturas the asignaturas to set
     */
    public void setAsignaturas(List<AsignaturaModel> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * @return the asignaturaSelected
     */
    public AsignaturaModel getAsignaturaSelected() {
        return asignaturaSelected;
    }

    /**
     * @param asignaturaSelected the asignaturaSelected to set
     */
    public void setAsignaturaSelected(AsignaturaModel asignaturaSelected) {
        this.asignaturaSelected = asignaturaSelected;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    //</editor-fold>
    /**
     * Creates a new instance of VerSyllabusJSFManagedBean
     */
    public VerSyllabusJSFManagedBean() {

        navigationJSFManagedBean = (NavigationJSFManagedBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("navigationMgmtBean");

    }

    @PostConstruct
    private void init() {
        Usuario usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
        DateTime jodaTime = new DateTime(asignaturaFacade.getSecuencial("SILABO", "FECHA_CORTE"), DateTimeZone.UTC);
        fechaCorte = jodaTime.toDate();
        asignaturas = asignaturaFacade.getListaAsignaturasxAnio(usr.getUsuCodigo(), matri.getAnio());
    }

    public void resetVariableReporte() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("params");
    }

    public void opcionesSilaboButton_processAction(ActionEvent ae) {
        Map<String, Object> params = new HashMap();
        String archivo = null;
        String[] pathRepositorio = null;
        RequestContext rc = RequestContext.getCurrentInstance();
        asignaturaSelected = (AsignaturaModel) ae.getComponent().getAttributes().get("asignatura");
        if (asignaturaSelected.getFechaCrea().compareTo(fechaCorte) < 0) {
            params.put("modulo", "Silabo");
            params.put("extArchivo", "jasper");
            params.put("formato", "pdf");
            archivo = asignaturaSelected.getAnio() <= 2014 ? "Syllabus2014" : "Syllabus2019";
            params.put("anio", asignaturaSelected.getAnio().toString());
            params.put("asig", asignaturaSelected.getAsignaturaModelPk().getCodigoMateria().toString());
            params.put("per", asignaturaSelected.getAsignaturaModelPk().getCodigoNivel().toString());
            params.put("par", asignaturaSelected.getAsignaturaModelPk().getGacCodnum().toString());
            params.put("prog", matri.getCodPrograma().toString());
        } else {
            String[] pathSilabo = {"Documentos", "PortalD", "silabos"};
            archivo = "SILABO_" + asignaturaSelected.getAnio().toString() + "_" + asignaturaSelected.getIdentifMateria() + "_" + asignaturaSelected.getNombreParalelo() + "_" + asignaturaSelected.getAsignaturaModelPk().getCodigoNivel();
            pathRepositorio = pathSilabo;
        }
        navigationJSFManagedBean.setPaginaMant("/pages/verDocumento");
//            super.nuevoButton_processAction(null);
        params.put("pathRepositorio", pathRepositorio);
        params.put("nombreArchivo", archivo.replace("Ú", "U"));
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("params", params);
        rc.update("mantDialog");
        rc.execute("mantWidget.show();");
    }

//    public void imprimirSp() {
////        String urlReporte = "tipo=pdf"
////                + "&anio=" + asignaturaSelected.getAnio()
////                + "&asig=" + asignaturaSelected.getAsignaturaModelPk().getCodigoMateria()
////                + "&per=" + asignaturaSelected.getAsignaturaModelPk().getCodigoNivel()
////                + "&par=" + asignaturaSelected.getAsignaturaModelPk().getGacCodnum()
////                + "&prog=" + matri.getCodPrograma();
////        System.out.println("urlRep "+urlReporte);
////        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        Map<String, Object> params = new HashMap();
//        RequestContext rc = RequestContext.getCurrentInstance();
//        navigationJSFManagedBean.setPaginaMant("/pages/verDocumento");
//        // if (asignaturaSelected.getAnio() <= 2021 || (asignaturaSelected.getAnio() == 2022 && asignaturaSelected.getAsignaturaModelPk().getCodigoNivel() == 1)) {
//        params.put("modulo", "Silabo");
//        params.put("extArchivo", "jasper");
//        params.put("formato", "pdf");
//        params.put("nombreArchivo", asignaturaSelected.getAnio() <= 2014 ? "Syllabus2014" : "Syllabus2019");
//        params.put("anio", asignaturaSelected.getAnio().toString());
//        params.put("asig", asignaturaSelected.getAsignaturaModelPk().getCodigoMateria().toString());
//        params.put("per", asignaturaSelected.getAsignaturaModelPk().getCodigoNivel().toString());
//        params.put("par", asignaturaSelected.getAsignaturaModelPk().getGacCodnum().toString());
//        params.put("prog", matri.getCodPrograma().toString());
////        } else {
////            String nombreParalelo = asignaturaFacade.getNombreParalelo(asignaturaSelected.getAnio(), asignaturaSelected.getAsignaturaModelPk().getCodigoMateria(),
////                    matri.getCodPrograma(),asignaturaSelected.getAsignaturaModelPk().getGacCodnum(), asignaturaSelected.getAsignaturaModelPk().getCodigoNivel());
////            String asignatura = "SILABO_" + asignaturaSelected.getAnio().toString() + "_"
////                    + asignaturaSelected.getIdentifMateria() + "_"
////                    + nombreParalelo + "_"
////                    + asignaturaSelected.getAsignaturaModelPk().getCodigoNivel();
////            String[] pathRepositorio = {"Documentos", "PortalD", "Silabos"};
////            params.put("pathRepositorio", pathRepositorio);
////            params.put("nombreArchivo", asignatura);
////        }
//        HttpSession session = (HttpSession) ec.getSession(false);
//        session.setAttribute("params", params);
//        rc.update("mantDialog");
//        rc.execute("mantWidget.show();mantWidget.initPosition();");
//    }
    public void imprimirHorario() {
        String urlReporte = "reporte=HorarioEstudiante"
                + "&tipo=pdf"
                + "&contexto=PortalE_OCU"
                + "&codAnio=" + matri.getEjercicio()
                + "&codTrimestre=" + trimestre
                + "&codEstudiante=" + matri.getCodAlumno();
//        System.out.println("urlRep " + urlReporte);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
    }

    public void verHorario() {
        List<Horario> horarios = asignaturaFacade.getHorarioPorAsig(matri.getEjercicio(), asignaturaSelected.getAsignaturaModelPk().getCodigoMateria(),
                asignaturaSelected.getAsignaturaModelPk().getVacCodnum(), asignaturaSelected.getAsignaturaModelPk().getGacCodnum());
        asignaturaSelected.setHorarios(horarios);
    }
}
