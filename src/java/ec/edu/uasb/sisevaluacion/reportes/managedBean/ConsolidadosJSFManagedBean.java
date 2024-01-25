/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.sisevaluacion.reportes.managedBean;

import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.entities.MatriculaExt;

import ec.edu.uasb.portalE.entities.AsignaturaModel;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.MatriculaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "RepEvalConsoBean")
@ViewScoped
public class ConsolidadosJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    @EJB
    private MatriculaFacadeLocal matriculaFacade;

    private Matricula matri;
    private Usuario usr;
    private String reporte;
    private String paramrep = null;

    private Long anio;
    private List<MatriculaExt> ciclos = new ArrayList<MatriculaExt>();

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public List<MatriculaExt> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<MatriculaExt> ciclos) {
        this.ciclos = ciclos;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    /**
     * Creates a new instance of VerNotasJSFManagedBean
     */
    public ConsolidadosJSFManagedBean() {
    }

    @PostConstruct
    private void init() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
        ciclos = matriculaFacade.getListaMatriculas(usr.getUsuCodigo());
    }

    public void resetValor() {
        anio = null;
    }

    public void imprimir() {
        String urlReporte = "&titulo=Evaluacion - " + (reporte.equals("EvalDeEstudAlProgramaConsol2020") ? matri.getAnioInicial() : anio)
                + "&tipo=pdf"
                + "&tipReporte=P"
                + "&anio=" + (reporte.equals("EvalDeEstudAlProgramaConsol2020") ? matri.getAnioInicial() : anio)
                + "&reporte=" + reporte
                + "&contexto=PortalSisEval"
                + "&codArea=" + matri.getCodArea()
                + "&codEsp=" + matri.getCodPrograma()
                + "&codTrim=T";

        System.out.println("urlRep " + urlReporte);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", urlReporte);
        RequestContext.getCurrentInstance().execute("pdfWidget.show();");

    }

}
