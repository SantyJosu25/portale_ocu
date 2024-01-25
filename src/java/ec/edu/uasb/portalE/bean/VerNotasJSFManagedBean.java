/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.bean;

import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.entities.MatriculaExt;

import ec.edu.uasb.portalE.entities.AsignaturaModel;
import ec.edu.uasb.portalE.session.AsignaturaFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.MatriculaFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "verNotasBean")
@ViewScoped
public class VerNotasJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120420L;
    @EJB
    private MatriculaFacadeLocal matriculaFacade;
    @EJB
    private AsignaturaFacadeLocal asignaturaFacade;
    private List<AsignaturaModel> asignaturas = new ArrayList<AsignaturaModel>();
    private List<PeriodoNota> periodoNotas = new ArrayList<PeriodoNota>();
    private AsignaturaModel asignaturaSelected;
    private Long anio;
    private Usuario usr;
    private List<MatriculaExt> ciclos = new ArrayList<MatriculaExt>();
    private Matricula matri;

    //<editor-fold defaultstate="collapsed" desc="Propiedades">
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

    public List<PeriodoNota> getPeriodoNotas() {
        return periodoNotas;
    }

    /**
     * @return the anio
     */
    public Long getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Long anio) {
        this.anio = anio;
    }

    /**
     * @return the ciclos
     */
    public List<MatriculaExt> getCiclos() {
        return ciclos;
    }

    /**
     * @param ciclos the ciclos to set
     */
    public void setCiclos(List<MatriculaExt> ciclos) {
        this.ciclos = ciclos;
    }
    //</editor-fold>

    /**
     * Creates a new instance of VerNotasJSFManagedBean
     */
    public VerNotasJSFManagedBean() {
    }

    @PostConstruct
    private void init() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
        ciclos = matriculaFacade.getListaMatriculas(usr.getUsuCodigo());
        anio = matri.getAnio();
        recuperarListados();

    }

    public String formatTexto(BigDecimal numero) {
        if (numero == null) {
            return null;
        }

//        if (pattern == null) {
//            throw new NullPointerException("pattern");
//        }

//        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return numero.setScale(2).toString();
    }    
    public class PeriodoNota {

        private String trimestre;
        private List<AsignaturaModel> asignaturas = new ArrayList<AsignaturaModel>();

        public String getTrimestre() {
            return trimestre;
        }

        public List<AsignaturaModel> getAsignaturas() {
            return asignaturas;
        }

        public PeriodoNota(String trimestre) {
            this.trimestre = trimestre;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 71 * hash + (this.trimestre != null ? this.trimestre.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PeriodoNota other = (PeriodoNota) obj;
            if ((this.trimestre == null) ? (other.trimestre != null) : !this.trimestre.equals(other.trimestre)) {
                return false;
            }
            return true;
        }
    }

    public void handleCicloChange() {
        recuperarListados();
    }

    private void recuperarListados() {
        String trimestre = null;
        
        asignaturas = asignaturaFacade.getListaAsignaturasxAnio(usr.getUsuCodigo(), anio);
        periodoNotas.clear();
        for (AsignaturaModel asig : asignaturas) {
            trimestre = asig.getNombreNivel();
            PeriodoNota p = new PeriodoNota(trimestre);
            if (periodoNotas.contains(p)) {
                periodoNotas.get(periodoNotas.lastIndexOf(p)).getAsignaturas().add(asig);
            } else {
                p.getAsignaturas().add(asig);
                periodoNotas.add(p);
            }
        }
    }
}
