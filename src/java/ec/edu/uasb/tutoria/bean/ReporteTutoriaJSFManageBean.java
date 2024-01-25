/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.bean;


import ec.edu.uasb.portalE.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


/**
 *
 * @author marjorie.fiallos
 */
@ManagedBean(name = "reporteTutoriaForm")
@ViewScoped
public class ReporteTutoriaJSFManageBean {

    @EJB
    private CicloAcademicoFacadeLocal ciclo;
    private String smTipo = null;
    private String smciclo = null;
    private String titulo = null;
    private String ls_reporte = null;
    private Usuario usr;

    public String getSmTipo() {
        return smTipo;
    }

    public void setSmTipo(String smTipo) {
        this.smTipo = smTipo;
    }

    public String getLs_reporte() {
        return ls_reporte;
    }

    public void setLs_reporte(String ls_reporte) {
        this.ls_reporte = ls_reporte;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSmciclo() {
        return smciclo;
    }

    public void setSmciclo(String smciclo) {
        this.smciclo = smciclo;
    }

    @PostConstruct
    public void init() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        smciclo = String.valueOf(ciclo.findByEstado('A').get(0).getCicloAcademicoPK().getAnio());
    }

    public void tipoListener() {
        if (smTipo.equalsIgnoreCase("D")) {
            ls_reporte = "InfTutoAcadDet";
            titulo = " Detalle Tutorías académicas- " + smciclo;

        } else {
            ls_reporte = "InfTutoAcadCuant";
            titulo = "Cuantitativo Tutorías académicas" + smciclo;
        }

    }

    public void verReporte(String tipo) {
        Calendar cal = Calendar.getInstance();
        String codEstudiante = usr.getUsuCodigo().toString();
        String url = "&codEstudiante=" + codEstudiante
                + "&anio=" + smciclo
                + "&tipo=" + tipo
                + "&titulo=" + titulo
                + "&reporte=" + ls_reporte
                + "&contexto=PortalE_OCU";
        

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("urlRep", url);
        RequestContext.getCurrentInstance().execute("pdfWidget.show();");

    }

}
