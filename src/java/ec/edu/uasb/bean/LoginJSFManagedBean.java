/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.bean;

import ec.edu.uasb.bean.exceptions.MatriculaException;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.recaptchav3.ReCAPTCHAv3Exception;
import ec.edu.uasb.recaptchav3.ReCAPTCHAv3Response;
import ec.edu.uasb.recaptchav3.ReCAPTCHAv3Utils;
import ec.edu.uasb.session.MatriculaFacadeLocal;
import ec.edu.uasb.seg.constants.LoginStatus;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.seg.session.UsuarioFacadeLocal;
import ec.edu.uasb.utils.JsfUtil;
import ec.edu.uasb.utils.LDAPAuthentication;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.AuthenticationException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author victor.barba
 */
@ManagedBean(name = "loginMgmtBean")
@RequestScoped
public class LoginJSFManagedBean implements Serializable {

    private static final long serialVersionUID = 20120423L;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private MatriculaFacadeLocal matriculaFacade;

    private String iconMensaje = "visto.jpg";
    private String mensaje = null;
    private String userName;
    private String password;
    private final ExternalContext ec;
    //  1.0 is very likely a good interaction
    //  0.0 is very likely a bot
    private final static double SCORES_LEVEL = 0.7;

    //<editor-fold defaultstate="collapsed" desc="Propiedades ">
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getIconMensaje() {
        return iconMensaje;
    }

    public void setIconMensaje(String iconMensaje) {
        this.iconMensaje = iconMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //</editor-fold>

    /**
     * Creates a new instance of LoginJSFManagedBean
     */
    public LoginJSFManagedBean() {
        ec = FacesContext.getCurrentInstance().getExternalContext();
    }

    public String login() {
//        RequestContext rc = RequestContext.getCurrentInstance();
//        try {
//            ReCAPTCHAv3Response response = ReCAPTCHAv3Utils.request(ec.getInitParameter("PRIVATE_CAPTCHA_KEY"), ec.getRequestParameterMap().get("token"), null);
//            if (response.getSuccess()) {
//                if (response.getScore() >= SCORES_LEVEL) {
                    return setLogin();
//                } else {
//                    JsfUtil.addErrorMessage(null, "COMPORTAMIENTO EXTRAÑO DETECTADO. POSIBLE ROBOT");
//                }
//            } else {
//                this.setIconMensaje("error.png");
//                 this.setMensaje("Token incorrecto, se recargará la página !");
//                rc.update("dialogMensaje");
//                rc.execute("mensajeWidget.show();");
//            }
//        } catch (ReCAPTCHAv3Exception ex) {
//            Logger.getLogger(LoginJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
    }

    private String setLogin() {
        java.util.List<String> msgs = new ArrayList<String>();
        boolean loggedIn = false;
        boolean ok;
        try {
            LDAPAuthentication lDAPAuthentication = new LDAPAuthentication();
            ok = lDAPAuthentication.authenticateLDAPUser(getUserName(), getPassword(), "uasb.edu.ec", "172.16.21.43:389", "dc=uasb,dc=edu,dc=ec");
            if (ok) {
                Matricula m = null;
                Usuario user = usuarioFacade.getPerson(getUserName());
                if (user.getLoginStatus().equals(LoginStatus.SUCCESSFUL)) {
                    m = matriculaFacade.getMatricula(user.getUsuCodigo());
                    loggedIn = true;
                ec.getSessionMap().put("logined", true);
                ec.getSessionMap().put("user", user);
                    ec.getSessionMap().put("matri", m);
                ec.getFlash().setKeepMessages(true);
                JsfUtil.addSuccessMessage(null, "BIENVENIDO(A)");
                    return "/principal?faces-redirect=true";
                } else {
                    msgs.add("Cuenta Desabilitada en OCU: por favor, contacte a su administrador.");
                }
            } else {
                msgs.add("Acceso Fallido: por favor, compruebe su nombre de usuario/contraseña y vuelva a intentarlo.");
                msgs.add("Verifique si su teclado esta en Mayúsculas ó Minúsculas");
            }
        } catch (MatriculaException ex) {
            msgs.add("MatriculaException " + ex.getMessage());
        } catch (Exception ex) {
            msgs.add(ex.getMessage());
            if (ex.getClass().equals(AuthenticationException.class)) {
                msgs.add("Verifique si su teclado esta en Mayúsculas ó Minúsculas");
            }
            userName = "";
            password = "";
        } finally {
            JsfUtil.addErrorMessages(null, msgs);

        }
        RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedIn);
        return null;
    }

    public String logout() {
        ec.getSessionMap().clear();
        ec.invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String cancelar() {
        userName = null;
        password = null;
        return null;
    }

    /**
     * Implementación de Seguridad
     */
    public void outapp() {
//        RequestContext rc = RequestContext.getCurrentInstance();
//        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Object flag = ec.getSessionMap().get("logined");
        if (flag == null || !(Boolean) flag) {
            try {
                ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//<editor-fold defaultstate="collapsed" desc="loginXCedula">

//    public String loginXCedula() {
//        java.util.List<String> msgs = new ArrayList<String>();
//        boolean loggedIn = false;
//        RequestContext rc = RequestContext.getCurrentInstance();
//        FacesContext fc = FacesContext.getCurrentInstance();
//        Usuario user = usuarioFacade.login(getUserName(), getPassword());
//        if (user.getLoginStatus().equals(LoginStatus.SUCCESSFUL)) {
//            Matricula m = null;
//            try {
//                m = matriculaFacade.getMatricula(user.getUsuCodigo());
//            } catch (MatriculaException ex) {
////                Logger.getLogger(LoginJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
//                msgs.add("Error: " + ex.getMessage());
//                JsfUtil.addErrorMessages(null, msgs);
//                return null;
//            }
//            loggedIn = true;
//            fc.getExternalContext().getSessionMap().put("logined", loggedIn);
//            fc.getExternalContext().getSessionMap().put("user", user);
//            fc.getExternalContext().getSessionMap().put("matri", m);
//            fc.getExternalContext().getFlash().setKeepMessages(true);
//            JsfUtil.addSuccessMessage(null, "Bienvenido@");
//            return "/principal?faces-redirect=true";
//        } else {
//            if (user.getLoginStatus().equals(LoginStatus.INACTIVE)) {
//                msgs.add("Cuenta Desabilitada: por favor, contacte a su administrador.");
//            } else {
//                msgs.add("Acceso Fallido: por favor, compruebe su nombre de usuario/contraseña y vuelva a intentarlo.");
//                msgs.add("Verifique si su teclado esta en Mayúsculas ó Minúsculas");
//            }
//            JsfUtil.addErrorMessages(null, msgs);
//        }
//
//        rc.addCallbackParam("loggedIn", loggedIn);
//        return null;
//    }
//</editor-fold>
}
