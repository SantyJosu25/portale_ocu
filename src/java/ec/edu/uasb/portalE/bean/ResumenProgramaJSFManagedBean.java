/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.bean;

import ec.edu.uasb.bean.NavigationJSFManagedBean;
import ec.edu.uasb.portalE.entities.CicloAcademico;
import ec.edu.uasb.portalE.session.CicloAcademicoFacadeLocal;
import ec.edu.uasb.portalE.session.ResumenProgramaFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author milton.valencia
 */
@ManagedBean(name = "resumenPrograma")
@ViewScoped
public class ResumenProgramaJSFManagedBean implements Serializable {

    @EJB
    private CicloAcademicoFacadeLocal cicloAcademicoFacade;
    @EJB
    private ResumenProgramaFacadeLocal resumenProgramaFacade;

    private Long periodo;
    private List<CicloAcademico> aniosAcademico;
    private List<String[]> listResumenProgramas = new ArrayList<String[]>();
    
      @ManagedProperty(value = "#{navigationMgmtBean}")
    private NavigationJSFManagedBean navJSFManagedBean;

    public NavigationJSFManagedBean getNavJSFManagedBean() {
        return navJSFManagedBean;
    }

    public void setNavJSFManagedBean(NavigationJSFManagedBean navJSFManagedBean) {
        this.navJSFManagedBean = navJSFManagedBean;
    }

    @PostConstruct
    public void postConstruc() {
        aniosAcademico = cicloAcademicoFacade.findAll();
        if (!aniosAcademico.isEmpty()) {
            periodo = aniosAcademico.get(0).getCicloAcademicoPK().getAnio();
            evtSeleccionarAnioAcademico();
        }

    }

    /**
     * @return the aniosAcademico
     */
    public List<CicloAcademico> getAniosAcademico() {
        return aniosAcademico;
    }

    /**
     * @param aniosAcademico the aniosAcademico to set
     */
    public void setAniosAcademico(List<CicloAcademico> aniosAcademico) {
        this.aniosAcademico = aniosAcademico;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }
    
    

    /**
     * @return the listResumenProgramas
     */
    public List<String[]> getListResumenProgramas() {
        return listResumenProgramas;
    }

    /**
     * @param listResumenProgramas the listResumenProgramas to set
     */
    public void setListResumenProgramas(List<String[]> listResumenProgramas) {
        this.listResumenProgramas = listResumenProgramas;
    }

    public void evtSeleccionarAnioAcademico() {
       buscarProgramas();
        System.out.println(periodo);
    }
//Creamos metodo

    private void buscarProgramas() {
        listResumenProgramas.clear();
        FacesContext fc = FacesContext.getCurrentInstance();
        Usuario user = (Usuario) fc.getExternalContext().getSessionMap().get("user");
        List<Object[]> listResumenProgramasTemp = resumenProgramaFacade.obtenerResumenPrograma(user.getUsuCodigo(), periodo);
        for (Object[] programaTemp : listResumenProgramasTemp) {
            String[] programa = new String[5];
            programa[0] = programaTemp[0].toString();
            programa[1] = programaTemp[1].toString();
            programa[2] = programaTemp[2].toString();
            programa[3] = programaTemp[3] == null ? "null" : programaTemp[3].toString();
            programa[4] = programaTemp[4].toString();
            listResumenProgramas.add(programa);
        }
    }

}
