/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.bean;

import ec.edu.uasb.portalE.session.CertificadoFacadeLocal;
import java.io.Serializable;
import ec.edu.uasb.portalE.entities.Certificado;
import ec.edu.uasb.seg.entities.Usuario;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author santiago.anrango
 */
@Named(value = "validarCertBean")
@RequestScoped
public class ValidarCertifBean implements Serializable {

    @EJB
    private CertificadoFacadeLocal certificadoFacade;

    private final String cerNumero;
    private Certificado certificado;
    private Usuario estudiante;
    private String iconMensaje = "listo.jpg";
    private String mensaje = null;
    private String fecha = null; 

    public String getMensaje() {
        return mensaje;
    }

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
        this.estudiante = estudiante;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public ValidarCertifBean() {
        cerNumero = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
    }
    
    @PostConstruct
    public void checkCertificado() {
        mensaje = null;
        try {
            if (cerNumero != null && !cerNumero.isEmpty() ) {
                certificado = certificadoFacade.findByCerNumero(cerNumero);
                estudiante = certificado.getUsuario();
                mensaje = "CERTIFICADO DE "+certificado.getCerTipoDoc().replace("MATRICULA", "MATRÍCULA")+" VÁLIDO";
                SimpleDateFormat dateFormat = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
                fecha = dateFormat.format(certificado.getCerFechaFirma());
            } else {
                mensaje = "Parametros incorrectos !";
            }
        } catch (Exception e) {
            mensaje = "Certificado NO VALIDO";
            iconMensaje = "error.png";
        } finally {
            if (mensaje != null) {
                iconMensaje = "error.png";
            }
        }
    }

    public String getFecha() {
        return fecha;
    }
    


}
