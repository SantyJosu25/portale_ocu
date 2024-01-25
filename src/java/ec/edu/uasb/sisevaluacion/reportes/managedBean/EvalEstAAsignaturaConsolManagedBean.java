/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.entities.MatriculaExt;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.MatriculaFacadeLocal;
//import ec.edu.uasb.sisevaluacion.entities.CicloAcademico;
//import ec.edu.uasb.sisevaluacion.entities.VPrograma;
//import ec.edu.uasb.sisevaluacion.session.ConsultaFacadeLocal;
//import ec.edu.uasb.sisevaluacion.session.VProgramaFacadeLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "EvalEstudAAsignaturaConsol")
@ViewScoped
public class EvalEstAAsignaturaConsolManagedBean implements Serializable {

    @EJB
    private MatriculaFacadeLocal matriculaFacade;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private Usuario usr;
    private Matricula matri;
    private Long anio;
    private String ls_reporte = null;
    private String paramrep = null;
    private String smciclo = null;
    private String smtipfiltro = "C";

    private String smtipo = null;
    private String smarea = null;
    private String smprograma = null;
    private String smtrimestre = null;
    private List<MatriculaExt> ciclos = new ArrayList<MatriculaExt>();
//    private List<VPrograma> lprograma = new ArrayList<VPrograma>();
    private List<String[]> listtrimestre = new ArrayList<String[]>();

//    @EJB
//    private VProgramaFacadeLocal programaFacade;
//    @EJB
//    private ConsultaFacadeLocal consultaFacade;
// <editor-fold defaultstate="collapsed" desc="DECLARACION VARIABLES">
    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public List<String[]> getListtrimestre() {
        return listtrimestre;
    }

    public void setListtrimestre(List<String[]> listtrimestre) {
        this.listtrimestre = listtrimestre;
    }

    public String getSmtipo() {
        return smtipo;
    }

    public void setSmtipo(String smtipo) {
        this.smtipo = smtipo;
    }

    public String getSmarea() {
        return smarea;
    }

    public void setSmarea(String smarea) {
        this.smarea = smarea;
    }

    public String getSmprograma() {
        return smprograma;
    }

    public void setSmprograma(String smprograma) {
        this.smprograma = smprograma;
    }

    public String getSmtrimestre() {
        return smtrimestre;
    }

    public void setSmtrimestre(String smtrimestre) {
        this.smtrimestre = smtrimestre;
    }

    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public String getParamrep() {
        return paramrep;
    }

    public void setParamrep(String paramrep) {
        this.paramrep = paramrep;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    public String getSmtipfiltro() {
        return smtipfiltro;
    }

    public void setSmtipfiltro(String smtipfiltro) {
        this.smtipfiltro = smtipfiltro;
    }

    public List<MatriculaExt> getCiclos() {
        return ciclos;
    }
    // </editor-fold> 

    public EvalEstAAsignaturaConsolManagedBean() {
    }

    @PostConstruct
    private void init() {
        Map<String, Object> varSession = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        usr = (Usuario) varSession.get("user");
        matri = (Matricula) varSession.get("matri");
        ciclos = matriculaFacade.getListaMatriculas(usr.getUsuCodigo());
        anio = matri.getAnio();
    }

// <editor-fold defaultstate="collapsed" desc="VER REPORTE">  
    public void verReporte(String tipo) {
        paramrep = armarparametros();
        paramrep = paramrep + " &titulo=EvalEstudAlAsignaturaConsol - " + anio;
//            modal1.verReporte(tipo, paramrep, ls_reporte);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc="ARMAR PARAMETROS">  
    private String armarparametros() {
            if (smtipfiltro.equalsIgnoreCase("C")) {
                if (Long.valueOf(smciclo) >= 2020) {
                    ls_reporte = "EvalDeEstudAAsignaturaConsol2020";
                } else {
                    ls_reporte = "EvalDeEstudAAsignaturaConsol";
                }

            }
            paramrep = "&anio=" + smciclo;
            if (smarea == null) {
                smarea = "T";
            }
            if (smprograma == null) {
                smprograma = "T";
            }
            if (smtrimestre == null) {
                smtrimestre = "T";
            }
            if (!smtipo.isEmpty()) {
                paramrep = paramrep + "&tipReporte=" + smtipo
                        + "&codArea=" + smarea
                        + "&codEsp=" + smprograma
                        + "&codTrim=" + smtrimestre;
            }
        return paramrep;
    }

    // </editor-fold> 
    public void ciclovalueChangeListener() {
        if (smciclo != null) {
//            listtrimestre = consultaFacade.trimestreAnio(Long.valueOf(smciclo));
        }
    }

    public void tipovalueChangeListener() {
        smciclo = null;
        smarea = null;
        smprograma = null;
        smtrimestre = null;
    }

    public void areavalueChangeListener() {
        smprograma = null;
//        lprograma.clear();
//        if (smarea != null && !smarea.equalsIgnoreCase("T")) {
//            if (smtipo != null && (smtipo.equalsIgnoreCase("P") || smtipo.equalsIgnoreCase("T"))) {
//                lprograma = programaFacade.findProgramaAnual(Long.valueOf(smciclo), smarea);
//            }
//        }
    }
}
