/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.pagos.view;

import ec.edu.uasb.config.ConfigurarRepositorio;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.firma.FirmaElectronica;
import ec.edu.uasb.firma.FirmarPDF;
import ec.edu.uasb.firma.exception.FirmaException;
import ec.edu.uasb.portalE.entities.Certificado;
import ec.edu.uasb.portalE.session.CertificadoFacadeLocal;
import ec.edu.uasb.seg.entities.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "respuestaBean")
@RequestScoped
public class RespuestaJSFManagedBean {

    @EJB
    private CertificadoFacadeLocal certificadoFacade;
    private final String modulo;
    private final ConfigurarRepositorio configDoc;
    private final String nombreFirma;
    private Certificado certificado;

    public RespuestaJSFManagedBean() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        configDoc = new ConfigurarRepositorio(ec.getInitParameter("directorio.principal"), ec.getInitParameter("directorio.storage"));
        modulo = (String) ec.getInitParameter("modulo");
        nombreFirma = ec.getInitParameter("archivoFirma");
    }

 
    public void procesarRespuesta() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        try {
        Map<String, String> requestParamMap = ec.getRequestParameterMap();
        String respuestaTransac = requestParamMap.get("respuesta");
        Usuario user = (Usuario) ec.getSessionMap().get("user");
        Matricula matri = (Matricula) ec.getSessionMap().get("matri");
//            System.out.println("procesarRespuesta matri " + matri);
//            System.out.println("procesarRespuesta user " + user);
        System.out.println("procesarRespuesta() respuestaTransac " + respuestaTransac);
//            if (respuestaTransac != null) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                PayPhoneConfirmation payPhoneConfirmation = objectMapper.readValue(respuestaTransac, PayPhoneConfirmation.class);
//                if (payPhoneConfirmation.getTransactionStatus().equals("Approved")) {
//                    String[] transaccion = payPhoneConfirmation.getClientTransactionId().split("-");
//                    certificado = new Certificado();
//                    certificado.setCerTransaccion(payPhoneConfirmation.getClientTransactionId());
//                    certificado.setCerFechaPago(new Date());
//                    certificado.setCerTipoDoc(transaccion[0]);
//                    certificado.setAnio(matri.getAnio().shortValue());
//                    certificado.setCerFechaCrea(new Date());
//                    certificado.setCerUsuCrea(user.getUsuCedPass());
//                    certificado.setCerEstado("P");
//                    certificado.setCerValor(payPhoneConfirmation.getAmount());
//                    certificado.setMatricula(matri.getMatricula());
//                    certificado.setCodEstudiante(matri.getCodAlumno());
//                    certificado.setPerCodigo(certificadoFacade.findPersonaBy(user.getUsuCedPass()).longValue());
//                    certificadoFacade.create(certificado);
//                    generarCertificado(matri);
        RequestContext.getCurrentInstance().execute("window.parent._setIframe('recargar');");
//                }
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(RespuestaJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    private void firmar(String docAfirmar, String docFirmado, String clavefirma) throws FirmaException {
        String[] pathRepositorioFirma = {"certificados", "firma"};
        String pathFirma = configDoc.getPathDoc(pathRepositorioFirma);

        try {
            FirmaElectronica firmaElectronica = new FirmaElectronica(new FileInputStream(pathFirma + nombreFirma), clavefirma);
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
                firmar(pathDocAFirmar, pathDocFirmado, "1977valentina1977");
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
//        } finally {
//            if (error != null) {
////                navigationJSFManagedBean.setMsgError("ERROR: " + error + ".</br></br> Por favor comuniquese con la Unidad de Sitemas de Información. Gracias ");
//                RequestContext.getCurrentInstance().update("dialogMessage");
//                RequestContext.getCurrentInstance().execute("errorWidget.show();");
//            }
        }
    }

}
