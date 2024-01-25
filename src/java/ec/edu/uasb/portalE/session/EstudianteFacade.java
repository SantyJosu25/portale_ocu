/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.portalE.entities.Estudiante;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author victor.barba
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
    public Estudiante finbyCodigo(Long codEstud) {
        Query q = em.createNamedQuery("Estudiante.findByCodEstudiante");
        q.setParameter("codEstudiante", codEstud);
        List<Estudiante> temp = q.setHint("eclipselink.refresh", true).getResultList();
        if (!temp.isEmpty()) {
            return (Estudiante) temp.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format) {
        Query query = em.createNativeQuery("{call msdb.dbo.sp_send_dbmail(?,?,?,?,?,?,?)}");
        query.setParameter(1, profile_name);
        query.setParameter(2, recipients);
        query.setParameter(3, copy_recipients);
        query.setParameter(4, blind_copy_recipients);
        query.setParameter(5, psubject);
        query.setParameter(6, pmensaje);
        query.setParameter(7, body_format);
        query.executeUpdate();
    }

    @Override
    public void edit(Estudiante estud, FacesContext fcontext) {
        StringBuilder mensaje;
        super.edit(estud);
        mensaje = this.genMensaje("estudiante", estud, fcontext);
        this.enviaCorreo("Soporte Servicios", estud.getEmail()+";"+estud.getEmailsecEstudiante(), "soporteservicios@uasb.edu.ec", "", "UASB - Datos personales actualizados", mensaje.toString(), "HTML");
//        this.enviaCorreo("Victor", "victor.barba@uasb.edu.ec", "soporteservicios@uasb.edu.ec", "", "UASB - Datos personales actualizados", mensaje.toString(), "HTML");
    }

    private StringBuilder genMensaje(String destino, Estudiante estud, FacesContext fcontext) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "EC"));
        StringBuilder sbHtml = new StringBuilder();
        if (destino.equals("estudiante")) {
            sbHtml.append(" Estimad@ estudiante <br>").append(estud.getNomEstudiante().toUpperCase()).append(" ").append(estud.getApellEstudiante().toUpperCase()).append("<br><br><br>");
            sbHtml.append(" El ").append(formatter.format(new Date())).append(" , usted ha realizado con exito cambios a su información personal a traves del 'Portal de Estudiante'").append("<br><br>");
            sbHtml.append(" Las modificaciones a la información personal a traves de este portal son de su exclusiva responsabilidad.<br><br>");
            sbHtml.append(" No entregue su clave personal a personas desconocidas. Cierre siempre su portal a traves de la opción 'Salir'").append("<br><br>");
        }
        sbHtml.append("Atentamente,<br><br><br>Secretaría General.<br>");
        sbHtml.append("Telef: (593 2) 299 3669 / (593 2) 299 3670<br>");
        sbHtml.append("Quito, Ecuador<br>");
        HttpServletRequest o = (HttpServletRequest) fcontext.getExternalContext().getRequest();
        String s = o.getRequestURL().toString();
        sbHtml.append("<p align='left'><img src='").append(s.substring(0, s.indexOf("/", 15))).append(fcontext.getExternalContext().getRequestContextPath()).append("/resources/images/logocolor.png' alt='logouasb' width='225' height='75' /></p>");
        sbHtml.append("</body>");
        return sbHtml;
    }
}
