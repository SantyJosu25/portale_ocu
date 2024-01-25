/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.bean;

import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.form.datamodel.EncuAsigDispo;
import ec.edu.uasb.form.datamodel.EncuProgDispo;
import ec.edu.uasb.form.datamodel.ParticipacionCeremGradDispo;
import ec.edu.uasb.form.session.EncuestaRealizadaFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "recallEvaluation")
@RequestScoped
public class RecallEvaluationBean {

    @EJB
    private EncuestaRealizadaFacadeLocal encuestaRealizadaFacade;
    private String msgRecall = null;
    private List catalogo = new ArrayList();
    private final Matricula matri;
    private final Usuario usr;

    public String getMsgRecall() {
        return msgRecall;
    }

    public void setMsgRecall(String msgRecall) {
        this.msgRecall = msgRecall;
    }

    /**
     * Creates a new instance of RecallEvaluationBean
     */
    public RecallEvaluationBean() {
        usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
    }

    @PostConstruct
    private void init() {
        StringBuilder sb = new StringBuilder();
        catalogo = encuestaRealizadaFacade.encuestasDisponibles(usr.getUsuCodigo(), matri.getAnio(), "EVAL_PROGRAMA");
        for (Iterator it = catalogo.iterator(); it.hasNext();) {
            EncuProgDispo epd = (EncuProgDispo) it.next();
//            if (epd.getHabilitado().equals("N") && epd.getEstado().equals("1")){
            if (epd.getHabilitado().equals("S") && epd.getEstado().equals("0")) {
                sb.append("<li>Ya evaluaste a tu programa ?</li>");
                break;
            }
        }
        catalogo = encuestaRealizadaFacade.encuestasDisponibles(usr.getUsuCodigo(), matri.getAnio(), "EVAL_ASIGNATURA");
        for (Iterator it = catalogo.iterator(); it.hasNext();) {
            EncuAsigDispo ead = (EncuAsigDispo) it.next();
//            if (ead.getHabilitado().equals("N") && ead.getEstado().equals("1")){
            if (ead.getHabilitado().equals("S") && ead.getEstado().equals("0")) {
                sb.append("<li>Ya evaluaste a tus docentes ?</li>");
                break;
            }
        }
        catalogo = encuestaRealizadaFacade.encuestasDisponibles(usr.getUsuCodigo(), matri.getAnio(), "PARTICIPACION_CEREMONIA");
        for (Iterator it = catalogo.iterator(); it.hasNext();) {
            ParticipacionCeremGradDispo p = (ParticipacionCeremGradDispo) it.next();
//            if (ead.getHabilitado().equals("N") && ead.getEstado().equals("1")){
            if (p.getHabilitado().equals("S") && p.getEstado().equals("0")) {
                sb.append("<li>Ya respondiste a tú participación a la ceremonia de graduación ?</li>");
                break;
            }
        }
//        catalogo = encuestaRealizadaFacade.encuestasDisponibles(usr.getUsuCodigo(), matri.getAnio(), "EVAL_SEGUIMIENTO");
//        for (Iterator it = catalogo.iterator(); it.hasNext();) {
//            EncuProgDispo epd = (EncuProgDispo) it.next();
////            if (epd.getHabilitado().equals("N") && epd.getEstado().equals("1")) {
//                if (epd.getHabilitado().equals("S") && epd.getEstado().equals("0")){
//                sb.append("<li>Ya evaluaste los servicios de la UASB ?</li>");
//                break;
//            }
//        }

        msgRecall = sb.toString();
    }
}
