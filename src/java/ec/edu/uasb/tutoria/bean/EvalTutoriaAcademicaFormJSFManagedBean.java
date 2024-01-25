/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.bean;

import ec.edu.uasb.bean.NavigationJSFManagedBean;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.form.FormField;
import ec.edu.uasb.form.datamodel.EncuTutoDispo;
import ec.edu.uasb.form.datamodel.EncuestaTutoDataModel;
import ec.edu.uasb.form.entities.Pregunta;
import ec.edu.uasb.form.session.PreguntaFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.tutoria.entities.RespuestaTutacad;
import ec.edu.uasb.tutoria.entities.RespuestaTutacadPK;
import ec.edu.uasb.tutoria.session.EncuestaRealizadaTutacadFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

/**
 *
 * @author marjorie.fiallos
 */
@ManagedBean(name = "evalTutoriaAcademicaForm")
@ViewScoped
public class EvalTutoriaAcademicaFormJSFManagedBean implements Serializable {

    @EJB
    private EncuestaRealizadaTutacadFacadeLocal encuestaRealizadaFacade;
    @EJB
    private PreguntaFacadeLocal preguntaFacade;
    private EncuestaTutoDataModel progEncuestaDataModel;
    private Object objSelected;
    private DynaFormModel modelo = new DynaFormModel();
    private List<SelectItem> opcRadio = new ArrayList<SelectItem>();
    private List<SelectItem> opcRadioSino = new ArrayList<SelectItem>();
    private List<SelectItem> opcTitulos = new ArrayList<SelectItem>();
    private List<Pregunta> preguntas;
    private List catalogo = new ArrayList();
    private Long codAlumno;
    private Matricula matri;
    @ManagedProperty(value = "#{navigationMgmtBean}")
    private NavigationJSFManagedBean navJSFManagedBean;

    //<editor-fold defaultstate="collapsed" desc="Propiedades ">
    /**
     * @return the objSelected
     */
    public Object getObjSelected() {
        return objSelected;
    }

    /**
     * @param objSelected the objSelected to set
     */
    public void setObjSelected(Object objSelected) {
        this.objSelected = objSelected;
    }

    /**
     * @return the modelo
     */
    public DynaFormModel getModelo() {
        return modelo;
    }

    /**
     * @param navJSFManagedBean the navJSFManagedBean to set
     */
    public void setNavJSFManagedBean(NavigationJSFManagedBean navJSFManagedBean) {
        this.navJSFManagedBean = navJSFManagedBean;
    }

    public EncuestaTutoDataModel getProgEncuestaDataModel() {
        return progEncuestaDataModel;
    }

    //</editor-fold>
    /**
     * Creates a new instance of ProgFormJSFManagedBean
     */
    public EvalTutoriaAcademicaFormJSFManagedBean() {
        opcRadio.add(new SelectItem(1, ""));
        opcRadio.add(new SelectItem(2, ""));
        opcRadio.add(new SelectItem(3, ""));
        opcRadio.add(new SelectItem(4, ""));
        opcRadio.add(new SelectItem(5, ""));
        opcTitulos.add(new SelectItem(1, "Deficiente"));
        opcTitulos.add(new SelectItem(2, "Regular"));
        opcTitulos.add(new SelectItem(3, "Bueno"));
        opcTitulos.add(new SelectItem(4, "Muy Bueno"));
        opcTitulos.add(new SelectItem(5, "Excelente"));
        opcRadioSino.add(new SelectItem(1, "Si"));
        opcRadioSino.add(new SelectItem(0, "No"));
    }

    @PostConstruct
    public void init() {
        Usuario usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
        codAlumno = usr.getUsuCodigo();
        catalogo = encuestaRealizadaFacade.evalTutoriaAcademica(codAlumno, matri.getCodPrograma());
        progEncuestaDataModel = new EncuestaTutoDataModel(catalogo);
        objSelected = null;
    }

    private void retrieveFormulario(long enc) {
        DynaFormRow fila;
        int j = 0;
        modelo = new DynaFormModel();
        preguntas = preguntaFacade.findByEncuesta(enc);
        int nroPreguntas = preguntas.size();
        DynaFormLabel label[] = new DynaFormLabel[nroPreguntas];
        DynaFormControl control[] = new DynaFormControl[nroPreguntas];
        for (int i = 0; i < nroPreguntas; i++) {
            Pregunta pregunta = preguntas.get(i);
            fila = modelo.createRegularRow();
            if (!pregunta.getTipo().equals('4')) {
                j++;
                if (!pregunta.getTipo().equals('5')) {
                    label[i] = fila.addLabel(j + ". " + pregunta.getDescripcion(), 1, 1);
                } else {
                    label[i] = fila.addLabel(pregunta.getDescripcion(), 1, 1);
                }
                if (pregunta.getTipo().equals('1')) {
                    control[i] = fila.addControl(new FormField("", pregunta, opcRadio, true), "radiochoice", 1, 1);
                } else if (pregunta.getTipo().equals('2')) {
                    control[i] = fila.addControl(new FormField(pregunta, false), "textarea", 1, 1);
                } else if (pregunta.getTipo().equals('3')) {
                    control[i] = fila.addControl(new FormField("", pregunta, opcRadioSino, true), "radiochoice", 1, 1);
                } else if (pregunta.getTipo().equals('5')) {
                    control[i] = fila.addControl(new FormField("", pregunta, opcTitulos, false), "radiochoiceTit", 1, 1);
                }
                label[i].setForControl(control[i]);
            } else {
                fila.addControl(pregunta.getDescripcion(), "separator", 3, 1);
                j = 0;
            }
        }
    }

    public void onRowSelect(SelectEvent se) {
        if (objSelected != null) {
            EncuTutoDispo encuTutoDispo = (EncuTutoDispo) se.getObject();
            navJSFManagedBean.setOpenPage("/pages/formulario");
            modelo = new DynaFormModel();
            if (!encuTutoDispo.getEstado().equals("1") && encuTutoDispo.getHabilitado().equals("S")) {
                retrieveFormulario(encuTutoDispo.getEncuTutoDispoPK().getCodigoEncuesta());
            }
        }
    }

    public void onRowUnselect(UnselectEvent se) {
        modelo = new DynaFormModel();
    }

    public void saveBtn_actionProccess(ActionEvent ae) {
        boolean ok = true;
        String msgError = null;
        EncuTutoDispo encuTutoDispo = (EncuTutoDispo) objSelected;
        List<RespuestaTutacad> respuestas = new ArrayList<RespuestaTutacad>();
        for (DynaFormControl dynaFormControl : modelo.getControls()) {
            if (dynaFormControl.getData() instanceof FormField) {
                FormField ff = (FormField) dynaFormControl.getData();
                if (!dynaFormControl.getType().equals("radiochoiceTit")) {
                    if (ff.isRequired() && (ff.getValue() == null || StringUtils.isBlank(ff.getValue().toString()))) {
                        ok = false;
                        msgError = "Por favor haga una revisión del formulario";
                        break;
                    } else if (ff.getValue() != null && StringUtils.isNotBlank(ff.getValue().toString())) {
//                        System.out.println(" dynaFormControl." + dynaFormControl.getKey() + " dynaFormControl.getRow() " + dynaFormControl.getRow() + " ff.getValue() " + ff.getValue() + "  " + dynaFormControl.getType());
                        RespuestaTutacad resp = new RespuestaTutacad();
                        RespuestaTutacadPK respPK = new RespuestaTutacadPK();
                        respPK.setAnio(encuTutoDispo.getEncuTutoDispoPK().getAnio());
                        respPK.setCiclo(encuTutoDispo.getCiclo());
                        respPK.setCodigoEncuesta(encuTutoDispo.getEncuTutoDispoPK().getCodigoEncuesta());
                        respPK.setCodigoMateria(new Long("-1"));
                        respPK.setCodigoProfesor(encuTutoDispo.getCodigoProfesor());
                        respPK.setCodigoPregunta(((Pregunta) ff.getKey()).getPreguntaPK().getCodigoPregunta());
                        respPK.setCodigoEsp(matri.getCodPrograma());
                        respPK.setCodigoNivel(Long.parseLong("-1"));
                        respPK.setCodigoParalelo(encuTutoDispo.getEncuTutoDispoPK().getCodigoParalelo());
                        respPK.setCodInstancia(encuTutoDispo.getCodigoInstancia());
                        resp.setRespuestaTutacadPK(respPK);
                        resp.setFecha(new Date());
                        if (!dynaFormControl.getType().equals("textarea")) {
                            resp.setValor(new Long(ff.getValue().toString()));
                        } else {
                            resp.setTexto(ff.getValue().toString());
                        }
                        respuestas.add(resp);
                    }
                }
            }
        }
        if (ok) {
            msgError = encuestaRealizadaFacade.createEncuesta(codAlumno, encuTutoDispo, respuestas, FacesContext.getCurrentInstance());
        } else {
            msgError = "No ha contestado todas las preguntas. " + msgError;
        }
        navJSFManagedBean.setMsgError(msgError);
        if (msgError == null) {
            RequestContext.getCurrentInstance().execute("window.location.reload();");
            respuestas = null;
        }
    }

    public String submitForm() {
        FacesMessage.Severity sev = FacesContext.getCurrentInstance().getMaximumSeverity();
        boolean hasErrors = (sev != null && (FacesMessage.SEVERITY_ERROR.compareTo(sev) >= 0));
        RequestContext.getCurrentInstance().addCallbackParam("isValid", !hasErrors && navJSFManagedBean.getMsgError() != null);
        return null;
    }
}
