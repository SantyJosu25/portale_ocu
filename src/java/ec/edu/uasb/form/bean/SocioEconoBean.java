/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.bean;

import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.form.entities.SeguimientoEgresados;
import ec.edu.uasb.form.entities.SeguimientoEgresadosPK;
import ec.edu.uasb.form.session.SeguimientoEgresadosFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;

/**
 *
 * @author johana.orozco
 */
@ManagedBean(name = "formulariohv")
@ViewScoped
public class SocioEconoBean implements Serializable {

    private Long codAlumno;
    private Long anio;
    private Long srautetnica;
    private String srestadcivil;
    private int inumhijos = 0;
    private HtmlInputText itxtautetnica = new HtmlInputText();
    private HtmlInputText itxtnumconadis = new HtmlInputText();
    private boolean bautetnica = false;
    private HtmlSelectOneMenu smingprommen = new HtmlSelectOneMenu();
    private HtmlSelectManyCheckbox smcbienes = new HtmlSelectManyCheckbox();
    private boolean bbfotrosvisible = false;
    private HtmlInputText itxtbfotroscuales = new HtmlInputText();
    private HtmlInputText itxtperscontacto = new HtmlInputText();
    private HtmlInputText itxttelperscontacto = new HtmlInputText();
    private HtmlInputText itxttitterniv1 = new HtmlInputText();
    private HtmlInputText itxttitterniv2 = new HtmlInputText();
    private HtmlInputText itxttitterniv3 = new HtmlInputText();
    private String ianiotitini1;
    private String ianiotitini2 = null;
    private String ianiotitini3;
    private String ianiotitfin1;
    private String ianiotitfin2;
    private String ianiotitfin3;
    private HtmlInputText itxttitcuartniv1 = new HtmlInputText();
    private HtmlInputText itxttitcuartniv2 = new HtmlInputText();
    private HtmlInputText itxttitcuartniv3 = new HtmlInputText();
    private String ianiotitcnini1;
    private String ianiotitcnini2;
    private String ianiotitcnini3;
    private String ianiotitcnfin1;
    private String ianiotitcnfin2;
    private String ianiotitcnfin3;
    private HtmlInputText itxtcurot = new HtmlInputText();
    private String sexplab;
    private boolean bexplab = false;
    private int inumaniocontrab = 0;
    private String strabremu;
    private String sbusqtrab;
    private int inummesbusqtrab = 0;
    private List<String> selectedCatocemp = new ArrayList<String>();
    private String snivsatis;
    private String scorocforma;
    private List<String> selectedRed = new ArrayList<String>();
    private List<String> selectedexpprof = new ArrayList<String>();
    private List<String> selectedbienfa = new ArrayList<String>();
    private HtmlInputText itxtinsttrab = new HtmlInputText();
    private Long srsetempresa;
    private boolean bsectempresa = false;
    private HtmlInputText itxtotrosecempresa = new HtmlInputText();
    private String srramempresa;
    private HtmlInputText itxtdirinstitucion = new HtmlInputText();
    private HtmlInputText itxtpagwebinstitu = new HtmlInputText();
    private String ianioinitrabajo;
    private HtmlInputText itxtcargdes = new HtmlInputText();
    private int inumperresponsabilidad = 0;
    private HtmlInputText itxtnomjefe = new HtmlInputText();
    private HtmlInputText itxtcargjefe = new HtmlInputText();
    private SeguimientoEgresados hojaVida = new SeguimientoEgresados();
    private boolean bestadoencuesta = false;
    @EJB
    private SeguimientoEgresadosFacadeLocal hojavidaFacade;

    // <editor-fold defaultstate="collapsed" desc="PROPIEDADES"> 
    public boolean isBestadoencuesta() {
        return bestadoencuesta;
    }

    public void setBestadoencuesta(boolean bestadoencuesta) {
        this.bestadoencuesta = bestadoencuesta;
    }

    public String getSrramempresa() {
        return srramempresa;
    }

    public void setSrramempresa(String srramempresa) {
        this.srramempresa = srramempresa;
    }

    public List<String> getSelectedbienfa() {
        return selectedbienfa;
    }

    public void setSelectedbienfa(List<String> selectedbienfa) {
        this.selectedbienfa = selectedbienfa;
    }

    public HtmlInputText getItxtcargjefe() {
        return itxtcargjefe;
    }

    public void setItxtcargjefe(HtmlInputText itxtcargjefe) {
        this.itxtcargjefe = itxtcargjefe;
    }

    public HtmlInputText getItxtnomjefe() {
        return itxtnomjefe;
    }

    public void setItxtnomjefe(HtmlInputText itxtnomjefe) {
        this.itxtnomjefe = itxtnomjefe;
    }

    public int getInumperresponsabilidad() {
        return inumperresponsabilidad;
    }

    public void setInumperresponsabilidad(int inumperresponsabilidad) {
        this.inumperresponsabilidad = inumperresponsabilidad;
    }

    public HtmlInputText getItxtcargdes() {
        return itxtcargdes;
    }

    public void setItxtcargdes(HtmlInputText itxtcargdes) {
        this.itxtcargdes = itxtcargdes;
    }

    public String getIanioinitrabajo() {
        return ianioinitrabajo;
    }

    public void setIanioinitrabajo(String ianioinitrabajo) {
        this.ianioinitrabajo = ianioinitrabajo;
    }

    public HtmlInputText getItxtpagwebinstitu() {
        return itxtpagwebinstitu;
    }

    public void setItxtpagwebinstitu(HtmlInputText itxtpagwebinstitu) {
        this.itxtpagwebinstitu = itxtpagwebinstitu;
    }

    public HtmlInputText getItxtdirinstitucion() {
        return itxtdirinstitucion;
    }

    public void setItxtdirinstitucion(HtmlInputText itxtdirinstitucion) {
        this.itxtdirinstitucion = itxtdirinstitucion;
    }

    public HtmlInputText getItxtotrosecempresa() {
        return itxtotrosecempresa;
    }

    public void setItxtotrosecempresa(HtmlInputText itxtotrosecempresa) {
        this.itxtotrosecempresa = itxtotrosecempresa;
    }

    public boolean isBsectempresa() {
        return bsectempresa;
    }

    public void setBsectempresa(boolean bsectempresa) {
        this.bsectempresa = bsectempresa;
    }

    public Long getSrsetempresa() {
        return srsetempresa;
    }

    public void setSrsetempresa(Long srsetempresa) {
        this.srsetempresa = srsetempresa;
    }

    public HtmlInputText getItxtinsttrab() {
        return itxtinsttrab;
    }

    public void setItxtinsttrab(HtmlInputText itxtinsttrab) {
        this.itxtinsttrab = itxtinsttrab;
    }

    public List<String> getSelectedexpprof() {
        return selectedexpprof;
    }

    public void setSelectedexpprof(List<String> selectedexpprof) {
        this.selectedexpprof = selectedexpprof;
    }

    public List<String> getSelectedRed() {
        return selectedRed;
    }

    public void setSelectedRed(List<String> selectedRed) {
        this.selectedRed = selectedRed;
    }

    public String getScorocforma() {
        return scorocforma;
    }

    public void setScorocforma(String scorocforma) {
        this.scorocforma = scorocforma;
    }

    public String getSnivsatis() {
        return snivsatis;
    }

    public void setSnivsatis(String snivsatis) {
        this.snivsatis = snivsatis;
    }

    public List<String> getSelectedCatocemp() {
        return selectedCatocemp;
    }

    public void setSelectedCatocemp(List<String> selectedCatocemp) {
        this.selectedCatocemp = selectedCatocemp;
    }

    public int getInummesbusqtrab() {
        return inummesbusqtrab;
    }

    public void setInummesbusqtrab(int inummesbusqtrab) {
        this.inummesbusqtrab = inummesbusqtrab;
    }

    public String getSbusqtrab() {
        return sbusqtrab;
    }

    public void setSbusqtrab(String sbusqtrab) {
        this.sbusqtrab = sbusqtrab;
    }

    public String getStrabremu() {
        return strabremu;
    }

    public void setStrabremu(String strabremu) {
        this.strabremu = strabremu;
    }

    public int getInumaniocontrab() {
        return inumaniocontrab;
    }

    public void setInumaniocontrab(int inumaniocontrab) {
        this.inumaniocontrab = inumaniocontrab;
    }

    public boolean isBexplab() {
        return bexplab;
    }

    public void setBexplab(boolean bexplab) {
        this.bexplab = bexplab;
    }

    public String getSexplab() {
        return sexplab;
    }

    public void setSexplab(String sexplab) {
        this.sexplab = sexplab;
    }

    public HtmlInputText getItxtcurot() {
        return itxtcurot;
    }

    public void setItxtcurot(HtmlInputText itxtcurot) {
        this.itxtcurot = itxtcurot;
    }

    public HtmlInputText getItxttitcuartniv1() {
        return itxttitcuartniv1;
    }

    public void setItxttitcuartniv1(HtmlInputText itxttitcuartniv1) {
        this.itxttitcuartniv1 = itxttitcuartniv1;
    }

    public HtmlInputText getItxttitcuartniv2() {
        return itxttitcuartniv2;
    }

    public void setItxttitcuartniv2(HtmlInputText itxttitcuartniv2) {
        this.itxttitcuartniv2 = itxttitcuartniv2;
    }

    public HtmlInputText getItxttitcuartniv3() {
        return itxttitcuartniv3;
    }

    public void setItxttitcuartniv3(HtmlInputText itxttitcuartniv3) {
        this.itxttitcuartniv3 = itxttitcuartniv3;
    }

    public String getIaniotitcnini1() {
        return ianiotitcnini1;
    }

    public void setIaniotitcnini1(String ianiotitcnini1) {
        this.ianiotitcnini1 = ianiotitcnini1;
    }

    public String getIaniotitcnini2() {
        return ianiotitcnini2;
    }

    public void setIaniotitcnini2(String ianiotitcnini2) {
        this.ianiotitcnini2 = ianiotitcnini2;
    }

    public String getIaniotitini1() {
        return ianiotitini1;
    }

    public void setIaniotitini1(String ianiotitini1) {
        this.ianiotitini1 = ianiotitini1;
    }

    public String getIaniotitini3() {
        return ianiotitini3;
    }

    public void setIaniotitini3(String ianiotitini3) {
        this.ianiotitini3 = ianiotitini3;
    }

    public String getIaniotitfin1() {
        return ianiotitfin1;
    }

    public void setIaniotitfin1(String ianiotitfin1) {
        this.ianiotitfin1 = ianiotitfin1;
    }

    public String getIaniotitfin2() {
        return ianiotitfin2;
    }

    public void setIaniotitfin2(String ianiotitfin2) {
        this.ianiotitfin2 = ianiotitfin2;
    }

    public String getIaniotitfin3() {
        return ianiotitfin3;
    }

    public void setIaniotitfin3(String ianiotitfin3) {
        this.ianiotitfin3 = ianiotitfin3;
    }

    public String getIaniotitcnini3() {
        return ianiotitcnini3;
    }

    public void setIaniotitcnini3(String ianiotitcnini3) {
        this.ianiotitcnini3 = ianiotitcnini3;
    }

    public String getIaniotitcnfin1() {
        return ianiotitcnfin1;
    }

    public void setIaniotitcnfin1(String ianiotitcnfin1) {
        this.ianiotitcnfin1 = ianiotitcnfin1;
    }

    public String getIaniotitcnfin2() {
        return ianiotitcnfin2;
    }

    public void setIaniotitcnfin2(String ianiotitcnfin2) {
        this.ianiotitcnfin2 = ianiotitcnfin2;
    }

    public String getIaniotitcnfin3() {
        return ianiotitcnfin3;
    }

    public void setIaniotitcnfin3(String ianiotitcnfin3) {
        this.ianiotitcnfin3 = ianiotitcnfin3;
    }

    public String getIaniotitini2() {
        return ianiotitini2;
    }

    public void setIaniotitini2(String ianiotitini2) {
        this.ianiotitini2 = ianiotitini2;
    }

    public HtmlInputText getItxttitterniv1() {
        return itxttitterniv1;
    }

    public void setItxttitterniv1(HtmlInputText itxttitterniv1) {
        this.itxttitterniv1 = itxttitterniv1;
    }

    public HtmlInputText getItxttitterniv2() {
        return itxttitterniv2;
    }

    public void setItxttitterniv2(HtmlInputText itxttitterniv2) {
        this.itxttitterniv2 = itxttitterniv2;
    }

    public HtmlInputText getItxttitterniv3() {
        return itxttitterniv3;
    }

    public void setItxttitterniv3(HtmlInputText itxttitterniv3) {
        this.itxttitterniv3 = itxttitterniv3;
    }

    public HtmlInputText getItxttelperscontacto() {
        return itxttelperscontacto;
    }

    public void setItxttelperscontacto(HtmlInputText itxttelperscontacto) {
        this.itxttelperscontacto = itxttelperscontacto;
    }

    public HtmlInputText getItxtperscontacto() {
        return itxtperscontacto;
    }

    public void setItxtperscontacto(HtmlInputText itxtperscontacto) {
        this.itxtperscontacto = itxtperscontacto;
    }

    public boolean isBbfotrosvisible() {
        return bbfotrosvisible;
    }

    public void setBbfotrosvisible(boolean bbfotrosvisible) {
        this.bbfotrosvisible = bbfotrosvisible;
    }

    public HtmlInputText getItxtbfotroscuales() {
        return itxtbfotroscuales;
    }

    public void setItxtbfotroscuales(HtmlInputText itxtbfotroscuales) {
        this.itxtbfotroscuales = itxtbfotroscuales;
    }

    public HtmlSelectManyCheckbox getSmcbienes() {
        return smcbienes;
    }

    public void setSmcbienes(HtmlSelectManyCheckbox smcbienes) {
        this.smcbienes = smcbienes;
    }

    public HtmlSelectOneMenu getSmingprommen() {
        return smingprommen;
    }

    public void setSmingprommen(HtmlSelectOneMenu smingprommen) {
        this.smingprommen = smingprommen;
    }

    public int getInumhijos() {
        return inumhijos;
    }

    public void setInumhijos(int inumhijos) {
        this.inumhijos = inumhijos;
    }

    public HtmlInputText getItxtnumconadis() {
        return itxtnumconadis;
    }

    public void setItxtnumconadis(HtmlInputText itxtnumconadis) {
        this.itxtnumconadis = itxtnumconadis;
    }

    public String getSrestadcivil() {
        return srestadcivil;
    }

    public void setSrestadcivil(String srestadcivil) {
        this.srestadcivil = srestadcivil;
    }

    public boolean isBautetnica() {
        return bautetnica;
    }

    public void setBautetnica(boolean bautetnica) {
        this.bautetnica = bautetnica;
    }

    public HtmlInputText getItxtautetnica() {
        return itxtautetnica;
    }

    public void setItxtautetnica(HtmlInputText itxtautetnica) {
        this.itxtautetnica = itxtautetnica;
    }

    public Long getSrautetnica() {
        return srautetnica;
    }

    public void setSrautetnica(Long srautetnica) {
        this.srautetnica = srautetnica;
    }

    // </editor-fold>
    /**
     * Creates a new instance of SocioEconoBean
     */
    @PostConstruct
    private void init() {
        Usuario usr = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Matricula matri = (Matricula) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("matri");
        codAlumno = usr.getUsuCodigo();
        anio = matri.getAnio();
        retrievDatos();
    }

    public SocioEconoBean() {
    }

    private void retrievDatos() {
        int estEncuesta = 0;
        String titterniv = null;
        String titcuartniv = null;

        estEncuesta = hojavidaFacade.veriEncRealizada(codAlumno, 9, anio, 1);
        titterniv = hojavidaFacade.titTerNivel(codAlumno);
        titcuartniv = hojavidaFacade.titCuarNivel(codAlumno);
        itxttitterniv1.setValue(titterniv);
        itxttitcuartniv1.setValue(titcuartniv);
        if (estEncuesta != 0) {
            bestadoencuesta = true;
        } else {
            bestadoencuesta = false;
            hojaVida = hojavidaFacade.findbyEstAnio(codAlumno, anio);
            if (hojaVida != null) {
                //CARGO LOS DATOS QUE TENGO PARA EL AÑO EN CURSO
                srautetnica = (hojaVida.getA31() == null ? null : hojaVida.getA31());
                itxtautetnica.setValue(hojaVida.getA311() == null ? null : hojaVida.getA311());
                srestadcivil = (hojaVida.getA32() == null ? null : hojaVida.getA32().toString());
                itxtnumconadis.setValue(hojaVida.getA18() == null ? null : hojaVida.getA18().toString());
                inumhijos = (hojaVida.getA33() == null ? 0 : Integer.parseInt(hojaVida.getA33().toString()));
                smingprommen.setValue(hojaVida.getA34() == null ? null : hojaVida.getA34());
                selectedbienfa.clear();
                if (hojaVida.getA351() != null) {
                    selectedbienfa.add("a3_5_1");
                }
                if (hojaVida.getA352() != null) {
                    selectedbienfa.add("a3_5_2");
                }
                if (hojaVida.getA353() != null) {
                    selectedbienfa.add("a3_5_3");
                    itxtbfotroscuales.setValue(hojaVida.getA354() == null ? 0 : hojaVida.getA354().toString());
                    bbfotrosvisible = true;
                } else {
                    bbfotrosvisible = false;
                }
                itxtperscontacto.setValue(hojaVida.getA36() == null ? null : hojaVida.getA36().toString());
                itxttelperscontacto.setValue(hojaVida.getA37() == null ? null : hojaVida.getA37().toString());
                itxttitterniv1.setValue(hojaVida.getA511() == null ? null : hojaVida.getA511().toString());
                ianiotitini1 = (hojaVida.getA512() == null ? null : hojaVida.getA512().toString());
                ianiotitfin1 = (hojaVida.getA513() == null ? null : hojaVida.getA513().toString());
                itxttitterniv2.setValue(hojaVida.getA514() == null ? null : hojaVida.getA514().toString());
                ianiotitini2 = (hojaVida.getA515() == null ? null : hojaVida.getA515().toString());
                ianiotitfin2 = (hojaVida.getA516() == null ? null : hojaVida.getA516().toString());
                itxttitterniv3.setValue(hojaVida.getA517() == null ? null : hojaVida.getA517().toString());
                ianiotitini3 = (hojaVida.getA518() == null ? null : hojaVida.getA518().toString());
                ianiotitfin3 = (hojaVida.getA519() == null ? null : hojaVida.getA519().toString());
                itxttitcuartniv1.setValue(hojaVida.getA521() == null ? null : hojaVida.getA521().toString());
                ianiotitcnini1 = (hojaVida.getA522() == null ? null : hojaVida.getA522().toString());
                ianiotitcnfin1 = (hojaVida.getA523() == null ? null : hojaVida.getA523().toString());
                itxttitcuartniv2.setValue(hojaVida.getA524() == null ? null : hojaVida.getA524().toString());
                ianiotitcnini2 = (hojaVida.getA525() == null ? null : hojaVida.getA525().toString());
                ianiotitcnfin2 = (hojaVida.getA526() == null ? null : hojaVida.getA526().toString());
                itxttitcuartniv3.setValue(hojaVida.getA527() == null ? null : hojaVida.getA527().toString());
                ianiotitcnini3 = (hojaVida.getA528() == null ? null : hojaVida.getA528().toString());
                ianiotitcnfin3 = (hojaVida.getA529() == null ? null : hojaVida.getA529().toString());
                itxtcurot.setValue(hojaVida.getA531() == null ? null : hojaVida.getA531().toString());
                sexplab = (hojaVida.getA70() == null ? "0" : hojaVida.getA70().toString());
                //Ingreso la información laboral
                if (hojaVida.getA70() == null || hojaVida.getA70().toString().equalsIgnoreCase("0")) {
                    bexplab = true;
                    inumaniocontrab = (hojaVida.getA61() == null ? 0 : Integer.parseInt(hojaVida.getA61().toString()));
                    strabremu = (hojaVida.getA62() == null ? "N" : hojaVida.getA62().toString());
                    sbusqtrab = (hojaVida.getA63() == null ? "N" : hojaVida.getA63().toString());
                    inummesbusqtrab = (hojaVida.getA64() == null ? 0 : Integer.parseInt(hojaVida.getA64().toString()));
                    if (hojaVida.getA651() != null) {
                        selectedCatocemp.add("a6_5_1");
                    }
                    if (hojaVida.getA652() != null) {
                        selectedCatocemp.add("a6_5_2");
                    }
                    if (hojaVida.getA653() != null) {
                        selectedCatocemp.add("a6_5_3");
                    }
                    if (hojaVida.getA654() != null) {
                        selectedCatocemp.add("a6_5_4");
                    }
                    if (hojaVida.getA655() != null) {
                        selectedCatocemp.add("a6_5_5");
                    }
                    if (hojaVida.getA656() != null) {
                        selectedCatocemp.add("a6_5_6");
                    }
                    snivsatis = (hojaVida.getA66() == null ? null : hojaVida.getA66().toString());
                    scorocforma = (hojaVida.getA67() == null ? null : hojaVida.getA67().toString());
                    if (hojaVida.getA681() != null) {
                        selectedRed.add("a6_8_1");
                    }
                    if (hojaVida.getA682() != null) {
                        selectedRed.add("a6_8_2");
                    }
                    if (hojaVida.getA683() != null) {
                        selectedRed.add("a6_8_3");
                    }
                    if (hojaVida.getA684() != null) {
                        selectedRed.add("a6_8_4");
                    }
                    if (hojaVida.getA711() != null) {
                        selectedexpprof.add("a7_1_1");
                    }
                    if (hojaVida.getA712() != null) {
                        selectedexpprof.add("a7_1_2");
                    }
                    if (hojaVida.getA713() != null) {
                        selectedexpprof.add("a7_1_3");
                    }
                    itxtinsttrab.setValue(hojaVida.getA73() == null ? null : hojaVida.getA73().toString());
                    srsetempresa = (hojaVida.getA74() == null ? null : Long.parseLong(hojaVida.getA74().toString()));
                    if (hojaVida.getA74() != null && hojaVida.getA74() == 6) {
                        bsectempresa = true;
                        itxtotrosecempresa.setValue(hojaVida.getA741() == null ? null : hojaVida.getA741().toString());
                    }
                    srramempresa = (hojaVida.getA75() == null ? null : hojaVida.getA75().toString());
                    itxtdirinstitucion.setValue(hojaVida.getA76() == null ? null : hojaVida.getA76().toString());
                    itxtpagwebinstitu.setValue(hojaVida.getA77() == null ? null : hojaVida.getA77().toString());
                    ianioinitrabajo = (hojaVida.getA78() == null ? null : hojaVida.getA78().toString());
                    itxtcargdes.setValue(hojaVida.getA79() == null ? null : hojaVida.getA79().toString());
                    inumperresponsabilidad = (hojaVida.getA710() == null ? 0 : Integer.parseInt(hojaVida.getA710().toString()));
                    itxtnomjefe.setValue(hojaVida.getA7111() == null ? null : hojaVida.getA7111().toString());
                    itxtcargjefe.setValue(hojaVida.getA7121() == null ? null : hojaVida.getA7121().toString());
                } else {
                    bexplab = false;
                }
            }
        }
    }

    public void autetnicavalueChangeListener() {
        if (srautetnica != null) {
            if (srautetnica == 6) {
                bautetnica = true;
            } else {
                bautetnica = false;
                this.itxtautetnica.setValue(null);
            }
        }
    }

    public void vexplabvalueChangeListener() {
        if (sexplab != null) {
            if (sexplab.equalsIgnoreCase("0")) {
                bexplab = true;
            } else {
                bexplab = false;

            }
        }
    }

    public void bfotrosvalueChangeListener() {
        String svalor = null;
        if (selectedbienfa.size() > 0) {
            for (int i = 0; i < selectedbienfa.size(); i++) {
                svalor = selectedbienfa.get(i);
                if (svalor.equalsIgnoreCase("a3_5_3")) {
                    bbfotrosvisible = true;
                    break;
                } else {
                    bbfotrosvisible = false;
                    this.itxtbfotroscuales.setValue(null);
                }
            }

        } else {
            bbfotrosvisible = false;
            this.itxtbfotroscuales.setValue(null);
        }
    }

    public void sectoempresavalueChangeListener() {
        if (srsetempresa != null) {
            if (srsetempresa == 6) {
                bsectempresa = true;
            } else {
                bsectempresa = false;
                this.itxtotrosecempresa.setValue(null);
            }
        }
    }

    public void btnAceptar() {
        SeguimientoEgresados hojavidaux = new   SeguimientoEgresados();
        SeguimientoEgresadosPK segPk = new SeguimientoEgresadosPK();
         if (hojaVida != null){
                if (hojaVida.getSeguimientoEgresadosPK()!= null){
                    hojavidaux = hojavidaFacade.find(hojaVida.getSeguimientoEgresadosPK());
                }
            }else{
             hojaVida = new SeguimientoEgresados();
         }
         try{
            //CARGO LOS DATOS QUE TENGO PARA EL AÑO EN CURSO  
            hojaVida.setA31(srautetnica==0 ? null :srautetnica);
            hojaVida.setA311(itxtautetnica.getValue() == null ? null : itxtautetnica.getValue().toString());
            hojaVida.setA32(srestadcivil ==null ? null :srestadcivil.toString().charAt(0));
            hojaVida.setA18(itxtnumconadis.getValue().toString().trim().length()==0 ||itxtnumconadis.getValue() == null ? null : itxtnumconadis.getValue().toString());
            hojaVida.setA33(inumhijos==0 ? 0 :Long.parseLong(String.valueOf(inumhijos)));
            hojaVida.setA34(smingprommen.getValue() ==null ? null :new BigDecimal(smingprommen.getValue().toString()));
            if (selectedbienfa.size()>0){
                for (int i = 0; i < selectedbienfa.size(); i++) {
                    if (selectedbienfa.get(i).equalsIgnoreCase("a3_5_1")){
                        hojaVida.setA351('1');
                    }else{
                        hojaVida.setA351('0');
                        if (selectedbienfa.get(i).equalsIgnoreCase("a3_5_2")){
                            hojaVida.setA352('1');
                        }else{
                             hojaVida.setA352('0');
                            if (selectedbienfa.get(i).equalsIgnoreCase("a3_5_3")){
                                hojaVida.setA353('1');
                                hojaVida.setA354(itxtbfotroscuales.getValue()== null ? null : itxtbfotroscuales.getValue().toString());
                            }else{
                                hojaVida.setA353('0');
                            }                        
                        }
                    }                 
                }
            }
            hojaVida.setA36(itxtperscontacto.getValue()== null ? null : itxtperscontacto.getValue().toString());
            hojaVida.setA37(itxttelperscontacto.getValue()== null ? null : itxttelperscontacto.getValue().toString());
            hojaVida.setA511(itxttitterniv1.getValue()== null ? null : itxttitterniv1.getValue().toString());
            hojaVida.setA512(ianiotitini1== null ? null : Long.parseLong(ianiotitini1));
            hojaVida.setA513(ianiotitfin1== null ? null : Long.parseLong(ianiotitfin1));
            hojaVida.setA514(itxttitterniv2.getValue().toString().trim().length()==0||itxttitterniv2.getValue()== null ? null : itxttitterniv2.getValue().toString());   
            hojaVida.setA515( ianiotitini2.trim().equalsIgnoreCase("")|| ianiotitini2 == null? null : Long.parseLong(ianiotitini2));
            hojaVida.setA516(ianiotitfin2.trim().equalsIgnoreCase("")||ianiotitfin2== null ? null : Long.parseLong(ianiotitfin2));
            hojaVida.setA517(itxttitterniv3.getValue().toString().trim().length()==0||itxttitterniv3.getValue()== null ? null : itxttitterniv3.getValue().toString());
            hojaVida.setA518(ianiotitini3.trim().equalsIgnoreCase("") ||ianiotitini3== null ? null : Long.parseLong(ianiotitini3));
            hojaVida.setA519(ianiotitfin3.trim().equalsIgnoreCase("") ||ianiotitfin3== null ? null : Long.parseLong(ianiotitfin3));
            hojaVida.setA521(itxttitcuartniv1.getValue().toString().trim().length()==0||itxttitcuartniv1.getValue()== null ? null : itxttitcuartniv1.getValue().toString());
            hojaVida.setA522(ianiotitcnini1.trim().equalsIgnoreCase("") ||ianiotitcnini1== null ? null : Long.parseLong(ianiotitcnini1));
            hojaVida.setA523(ianiotitcnfin1.trim().equalsIgnoreCase("")||ianiotitcnfin1== null ? null : Long.parseLong(ianiotitcnfin1));
            hojaVida.setA524(itxttitcuartniv2.getValue().toString().trim().length()==0||itxttitcuartniv2.getValue()== null ? null : itxttitcuartniv2.getValue().toString());
            hojaVida.setA525(ianiotitcnini2.trim().equalsIgnoreCase("") ||ianiotitcnini2== null ? null : Long.parseLong(ianiotitcnini2));
            hojaVida.setA526(ianiotitcnfin2.trim().equalsIgnoreCase("")||ianiotitcnfin2== null ? null : Long.parseLong(ianiotitcnfin2));
            hojaVida.setA527(itxttitcuartniv3.getValue().toString().trim().length()==0||itxttitcuartniv3.getValue()== null ? null : itxttitcuartniv3.getValue().toString());
            hojaVida.setA528(ianiotitcnini3.trim().equalsIgnoreCase("")||ianiotitcnini3== null ? null : Long.parseLong(ianiotitcnini3));
            hojaVida.setA528(ianiotitcnfin3.trim().equalsIgnoreCase("")||ianiotitcnfin3== null ? null : Long.parseLong(ianiotitcnfin3));        
            hojaVida.setA531(itxtcurot.getValue()== null ? null : itxtcurot.getValue().toString());
            hojaVida.setA70(sexplab== null ? '1' : sexplab.charAt(0));
            if(sexplab.equalsIgnoreCase("0")){
               hojaVida.setA61(inumaniocontrab== 0 ? 0 : Long.parseLong(String.valueOf(inumaniocontrab))); 
               hojaVida.setA62(strabremu== null ? 'N' : strabremu.charAt(0));
               hojaVida.setA63(sbusqtrab== null ? 'N' : sbusqtrab.charAt(0));
               hojaVida.setA64(inummesbusqtrab== 0 ? 0 : Long.parseLong(String.valueOf(inummesbusqtrab)));
               if (selectedCatocemp.size()>0){
                   for (int i = 0; i < selectedCatocemp.size(); i++) {
                     if(selectedCatocemp.get(i).equalsIgnoreCase("a6_5_1")){
                         hojaVida.setA651('1');
                     }
                     if(selectedCatocemp.get(i).equalsIgnoreCase("a6_5_2")){
                         hojaVida.setA652('1');
                     }
                     if(selectedCatocemp.get(i).equalsIgnoreCase("a6_5_3")){
                         hojaVida.setA653('1');
                     }
                     if(selectedCatocemp.get(i).equalsIgnoreCase("a6_5_4")){
                         hojaVida.setA654('1');
                     }
                     if(selectedCatocemp.get(i).equalsIgnoreCase("a6_5_5")){
                         hojaVida.setA655('1');
                     }
                     if(selectedCatocemp.get(i).equalsIgnoreCase("a6_5_6")){
                         hojaVida.setA656('1');
                     }
                   }
               }
               hojaVida.setA66(snivsatis== null ? null :Long.parseLong(snivsatis));
               hojaVida.setA67(scorocforma== null ? null :Long.parseLong(scorocforma));
               if (selectedRed.size()>0){
                   for (int i = 0; i < selectedRed.size(); i++) {
                       if(selectedRed.get(i).equalsIgnoreCase("a6_8_1")){
                          hojaVida.setA681('1'); 
                       }
                       if(selectedRed.get(i).equalsIgnoreCase("a6_8_2")){
                          hojaVida.setA682('1'); 
                       }
                       if(selectedRed.get(i).equalsIgnoreCase("a6_8_3")){
                          hojaVida.setA683('1'); 
                       }
                       if(selectedRed.get(i).equalsIgnoreCase("a6_8_4")){
                          hojaVida.setA684('1'); 
                       }
                   }
               }
                if (selectedexpprof.size()>0){
                    for (int i = 0; i < selectedexpprof.size(); i++) {
                        if(selectedexpprof.get(i).equalsIgnoreCase("a7_1_1")){
                          hojaVida.setA711('1'); 
                       }
                        if(selectedexpprof.get(i).equalsIgnoreCase("a7_1_2")){
                          hojaVida.setA712('1'); 
                       }
                        if(selectedexpprof.get(i).equalsIgnoreCase("a7_1_3")){
                          hojaVida.setA713('1'); 
                       }
                    }
                 
                }
                hojaVida.setA73(itxtinsttrab.getValue()== null ? null : itxtinsttrab.getValue().toString());
                hojaVida.setA74(srsetempresa==null  ? null : srsetempresa);
                if (srsetempresa!= null && srsetempresa== 6){
                   hojaVida.setA741( itxtotrosecempresa.getValue()== null ? null : itxtotrosecempresa.getValue().toString());
                }
                hojaVida.setA75(srramempresa==null  ? null : srramempresa.charAt(0));
                hojaVida.setA76(itxtdirinstitucion.getValue()== null ? null : itxtdirinstitucion.getValue().toString());
                hojaVida.setA77(itxtpagwebinstitu.getValue()== null ? null : itxtpagwebinstitu.getValue().toString());
                hojaVida.setA78(ianioinitrabajo.trim().equalsIgnoreCase("")||ianioinitrabajo== null ? 0 : Long.parseLong(ianioinitrabajo));
                hojaVida.setA79(itxtcargdes.getValue()== null ? null : itxtcargdes.getValue().toString());
                hojaVida.setA710(Long.parseLong(String.valueOf(inumperresponsabilidad)));
                hojaVida.setA7111(itxtnomjefe.getValue()== null ? null : itxtnomjefe.getValue().toString());
                hojaVida.setA7121(itxtcargjefe.getValue()== null ? null : itxtcargjefe.getValue().toString());
            }
           
         if (hojavidaux.getSeguimientoEgresadosPK()== null){
             segPk.setCodigoEstudiante(codAlumno);
             segPk.setAnio(anio);
             segPk.setCiclo(1);
             segPk.setFechaRealizacion(new Date());
             hojaVida.setSeguimientoEgresadosPK(segPk);
             hojavidaFacade.createEncuesta(codAlumno, Long.parseLong("9"), hojaVida, FacesContext.getCurrentInstance());
         }else{
             hojavidaFacade.edit(hojaVida);
         }
         bestadoencuesta = true;
         FacesMessage msg = new FacesMessage("Encuesta Realizada");    
         FacesContext.getCurrentInstance().addMessage(null, msg); 
         }catch (Exception e) {
             bestadoencuesta = false;
             Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
             FacesMessage msg = new FacesMessage("No se pudo Guardar la encuesta");    
             FacesContext.getCurrentInstance().addMessage(null, msg); 
                } finally {
                   hojaVida = new SeguimientoEgresados();
                }
      }
}
