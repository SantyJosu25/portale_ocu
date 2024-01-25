/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uasb.bean.NavigationJSFManagedBean;
import ec.edu.uasb.config.ConfigurarRepositorio;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.firma.FirmaElectronica;
import ec.edu.uasb.firma.FirmarPDF;
import ec.edu.uasb.firma.exception.FirmaException;
import ec.edu.uasb.payphone.PayPhoneConfirmation;
import ec.edu.uasb.portalE.entities.Certificado;
import ec.edu.uasb.portalE.session.CertificadoFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "certificadoBean")
@ViewScoped
public class CertificadoJSFManagedBean implements Serializable {

    @EJB
    private CertificadoFacadeLocal certificadoFacade;

    private static final long serialVersionUID = 20120888L;
    private final Usuario user;
    private Certificado certificado;
    private final String modulo;
    private final ConfigurarRepositorio configDoc;
    private final NavigationJSFManagedBean navigationJSFManagedBean;
    private List<Certificado> listaCertificados = new ArrayList<Certificado>();
    private final String archivoFirma;
    private final String claveFirma;

    public List<Certificado> getListaCertificados() {
        return listaCertificados;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public CertificadoJSFManagedBean() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        navigationJSFManagedBean = (NavigationJSFManagedBean) ec.getSessionMap().get("navigationMgmtBean");
        user = (Usuario) ec.getSessionMap().get("user");
        configDoc = new ConfigurarRepositorio(ec.getInitParameter("directorio.principal"), ec.getInitParameter("directorio.storage"));
        modulo = (String) ec.getInitParameter("modulo");
        archivoFirma = ec.getInitParameter("archivoFirma");
        claveFirma = ec.getInitParameter("claveFirma");
    }

    @PostConstruct
    public void recuperarListaCertificados() {
        listaCertificados = certificadoFacade.findAllBy(user);
    }

    public void procesarRequerimiento(AjaxBehaviorEvent event) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        long nroClientTransactionId = (long) (Math.random() * 45451245L);
        String urlResp = null;
        if (ec.getRequestServerPort() == 8080) {
            urlResp = "http://localhost:8080";
        } else {
            urlResp = "https://" + ec.getRequestServerName();
        }
        urlResp = urlResp + ec.getRequestContextPath();
        JsonObjectBuilder jsonObjBuilderSecretPAY = Json.createObjectBuilder()
                .add("amount", certificado.getCerValor().multiply(new BigDecimal(100)).longValue())
                .add("amountWithoutTax", certificado.getCerValor().multiply(new BigDecimal(100)).longValue())
                .add("clientTransactionID", certificado.getCerTipoDoc() + "-" + String.valueOf(nroClientTransactionId))
                .add("documentId", user.getUsuCedPass())
                .add("email", user.getUsuEmail())
                .add("reference", "PAGO POR CERTIFICADO DE " + certificado.getCerTipoDoc().replace("MATRICULA", "MATRÍCULA"))
                .add("responseUrl", urlResp + "/pago/respuesta.xhtml")
                .add("cancellationUrl", urlResp + "/pago/cancelacion.xhtml")
                .add("currency", "USD");
        String parametros = jsonObjBuilderSecretPAY.build().toString();
//        System.out.println("parametros " + parametros);
        String autoriz = ec.getInitParameter("AUTORIZATION_PAYPHONE_KEY");
        RequestContext.getCurrentInstance().execute("genBotonPayPhone(" + parametros + ", 'Bearer " + autoriz + "');");

    }

    //<editor-fold defaultstate="collapsed" desc="Procesar Pago">
    public void procesarRespuesta() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            Map<String, String> requestParamMap = ec.getRequestParameterMap();
            String respuestaTransac = requestParamMap.get("respuesta");
            Matricula matri = (Matricula) ec.getSessionMap().get("matri");
            if (respuestaTransac != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                PayPhoneConfirmation payPhoneConfirmation = objectMapper.readValue(respuestaTransac, PayPhoneConfirmation.class);
                if (payPhoneConfirmation.getTransactionStatus().equals("Approved")) {
                    String[] transaccion = payPhoneConfirmation.getClientTransactionId().split("-");
                    certificado = new Certificado();
                    certificado.setCerTransaccion(payPhoneConfirmation.getClientTransactionId());
                    certificado.setCerFechaPago(new Date());
                    certificado.setCerTipoDoc(transaccion[0]);
                    certificado.setAnio(matri.getAnio().shortValue());
                    certificado.setCerFechaCrea(new Date());
                    certificado.setCerUsuCrea(user.getUsuCedPass());
                    certificado.setCerEstado("P");
                    certificado.setCerValor(payPhoneConfirmation.getAmount());
                    certificado.setMatricula(matri.getMatricula());
                    certificado.setCodEstudiante(matri.getCodAlumno());
                    certificado.setPerCodigo(certificadoFacade.findPersonaBy(user.getUsuCedPass()).longValue());
                    certificadoFacade.create(certificado);
                    generarCertificado(matri);
                    RequestContext.getCurrentInstance().execute("window.parent._setIframe('recargar');");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

       public void procesarRespuestaAUX() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //try {
            Map<String, String> requestParamMap = ec.getRequestParameterMap();
            
            Matricula matri = (Matricula) ec.getSessionMap().get("matri");
            
                ObjectMapper objectMapper = new ObjectMapper();
             //   PayPhoneConfirmation payPhoneConfirmation = objectMapper.readValue(respuestaTransac, PayPhoneConfirmation.class);
                
                   // String[] transaccion = payPhoneConfirmation.getClientTransactionId().split("-");
                    certificado = new Certificado();
                    certificado.setCerTransaccion("REVISAR_1");
                    certificado.setCerFechaPago(new Date());
                    certificado.setCerTipoDoc("MATRICULA");
                    certificado.setAnio(matri.getAnio().shortValue());
                    certificado.setCerFechaCrea(new Date());
                    certificado.setCerUsuCrea(user.getUsuCedPass());
                    certificado.setCerEstado("P");
                    certificado.setCerValor(new BigDecimal("5"));
                    certificado.setMatricula(matri.getMatricula());
                    certificado.setCodEstudiante(matri.getCodAlumno());
                    certificado.setPerCodigo(Long.parseLong("152465"));
                    certificadoFacade.create(certificado);
                    generarCertificado(matri);
                    
                
//            
//        } catch (IOException ex) {
//            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//        }

    }

    
    private void firmar(String docAfirmar, String docFirmado, String clavefirma) throws FirmaException {
        String[] pathRepositorioFirma = {"Certificados", "firma"};
        String pathFirma = configDoc.getPathDoc(pathRepositorioFirma);

        try {
            FirmaElectronica firmaElectronica = new FirmaElectronica(new FileInputStream(pathFirma + archivoFirma), clavefirma);
            FirmarPDF.firmarDocumentoByArchivo(firmaElectronica,
                    docAfirmar, "SIGNED_BY_SECRETARIA", docFirmado, "Firma de certificado UASB");
            File file = new File(docFirmado);
            if (file.exists()) {
                File fileAfirmar = new File(docAfirmar);
                fileAfirmar.delete();
                //Actualizo los campos para indicar que el certificado ya esta firmado
                certificado.setCerFechaFirma(new Date());
                certificadoFacade.edit(certificado);
            }
        } catch (FirmaException fe) {
            throw new FirmaException(this.getClass().getSimpleName() + "</br></br> " + fe.getMessage());
        } catch (FileNotFoundException ex) {
            throw new FirmaException(this.getClass().getSimpleName() + " firmaDigital() FileNotFoundException : </br></br> " + ex.getMessage());
        }
    }

    private void generarCertificado(Matricula matri) {
        Map<String, Object> params = new HashMap<String, Object>();

        String reporteJasper = (certificado.getCerTipoDoc().equals("NOTAS") ? "cert_notas_asistencia"
                : certificado.getCerTipoDoc().equals("MATRICULA") ? "cert_matriculas" : "cert_horario");

        String[] pathRepositorio = {"Documentos", modulo, "certificados"};

        String pathDocAFirmar = configDoc.getPathDoc(pathRepositorio) + certificado.getCerCodigo() + "_" + certificado.getCerTipoDoc() + "_temp.pdf";
        String pathDocFirmado = configDoc.getPathDoc(pathRepositorio) + certificado.getCerCodigo() + "_" + certificado.getCerTipoDoc() + ".pdf";

        String error = null;
        try {
            File files = new File(pathDocAFirmar);
            if (!files.exists()) {
                params.put("REPORT_PATH_IMAGES", configDoc.getPathImage());
                params.put("pCodigo_Esp", matri.getCodPrograma().toString());
                params.put("pCedula", certificado.getCerUsuCrea());

                certificadoFacade.genDocuParaFirma(configDoc.getPathReportes(modulo) + reporteJasper + ".jasper",
                        pathDocAFirmar,
                        "https://sistemas.uasb.edu.ec/PortalE_OCU/validez_certificado.xhtml?codigo=",
                        params, certificado);
                firmar(pathDocAFirmar, pathDocFirmado, claveFirma);
            } else {
                error = "Documento ya generado ! ";
            }
        } catch (Exception ex) {
            if (ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getSimpleName().equals("FileNotFoundException")) {
                error = " . Archivo '" + reporteJasper + "' no encontrado !";
            } else {
                error = " " + ex.getCause().getMessage();
            }
            error = "No se ha podido generar el documento a firmar de la solicitud " + certificado.getCerCodigo() + error;
        } finally {
            if (error != null) {
//                navigationJSFManagedBean.setMsgError("ERROR: " + error + ".</br></br> Por favor comuniquese con la Unidad de Sitemas de Información. Gracias ");
                RequestContext.getCurrentInstance().update("dialogMessage");
                RequestContext.getCurrentInstance().execute("errorWidget.show();");
            }
        }
    }
//</editor-fold>

    public void descargar() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String urlResp = null;
        if (ec.getRequestServerPort() == 8080) {
            urlResp = "http://localhost:8080";
        } else {
            urlResp = "https://" + ec.getRequestServerName();
        }
        urlResp = urlResp + "/PrintService/resources/reporte/json";

        JsonObjectBuilder jsonObjBuilderParam = Json.createObjectBuilder()
                .add("url", urlResp).add("modulo", modulo).add("ubicacion", "certificados").add("nombreArchivo", certificado.getCerCodigo() + "_" + certificado.getCerTipoDoc());
        String parametros = jsonObjBuilderParam.build().toString();
        RequestContext.getCurrentInstance().execute("printServiceAjax(" + parametros + ");");
    }

    public void opcionesButton_processAction(ActionEvent ae) {
        RequestContext rc = RequestContext.getCurrentInstance();
        if (ae.getComponent().getId().equals("commandButtonDescargar")) {
            certificado = (Certificado) (ae.getComponent().getAttributes().get("certificado"));
            //        Actualizo los campos para indicar que el certificado ya se descargo
            certificado.setCerFechaDescarga(new Date());
            certificadoFacade.edit(certificado);
        } else {
            if (ae.getComponent().getId().equals("commandButtonCancelar")) {
                certificado = null;
            } else {
                certificado = new Certificado();
                certificado.setCerEstado("R");
                certificado.setCerValor(new BigDecimal("5"));
                certificado.setCodEstudiante(user.getUsuCodigo());
                navigationJSFManagedBean.setPaginaMant("/pages/regCertificado");
                navigationJSFManagedBean.setTituloMant("");
                rc.execute("mantWidget.show();");
                rc.update("mantDialog");
            }

        }
    }

}
