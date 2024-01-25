/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.seg.session;

import ec.edu.uasb.seg.constants.LoginStatus;
import ec.edu.uasb.seg.entities.Usuario;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario getPerson(String userName) {
        Usuario user = new Usuario();
        user.setLoginStatus(LoginStatus.INACTIVE);
        boolean isLoginSuccessful = false;
        Query query = em.createNativeQuery("select COD_ESTUDIANTE,CED_PAS_ESTUDIANTE,NOM_ESTUDIANTE,APELL_ESTUDIANTE,CLAVE,EMAIL,USULDAP,DN from ESTUDIANTE where USULDAP = ?",Usuario.class);
        query.setParameter(1, userName);
        try {
            user = (Usuario) query.getSingleResult();
            user.setUsuEstadoUsuario("A");
            user.setLoginStatus(LoginStatus.SUCCESSFUL);
            isLoginSuccessful = user.getLoginStatus().equals(LoginStatus.SUCCESSFUL);
            if (isLoginSuccessful) {
                user.setUsuFechaUltimoLogin(new Date());
            }
        } catch (NoResultException ex) {
        }
        return user;
    }

//    @Override
//    public Usuario login(String username, String password) {
//        Usuario user = new Usuario();
//        boolean isLoginSuccessful = false;
//        try {
////            Query query = em.createNamedQuery("Usuario.findByUsuNombreUsuario");
////            query.setParameter("usuNombreUsuario", username);
//            Query query = em.createNativeQuery("select COD_ESTUDIANTE,CED_PAS_ESTUDIANTE,NOM_ESTUDIANTE,APELL_ESTUDIANTE,CLAVE,EMAIL from ESTUDIANTE where CED_PAS_ESTUDIANTE = ? and BASE_DIR='A'", Usuario.class);
//            query.setParameter(1, username);
//            try {
//                user = (Usuario) query.getSingleResult();
////                user.setUsuNombreUsuario("admin");
////                user.setUsuClave("admin");
//                user.setUsuEstadoUsuario("A");
//                user.setLoginStatus(LoginStatus.SUCCESSFUL);
//                if (user.getUsuEstadoUsuario() == null || user.getUsuEstadoUsuario().equals("I")) {
//                    user.setLoginStatus(LoginStatus.INACTIVE);
//                }
//                if (user.getUsuClave() == null || (user.getUsuClave() != null && !user.getUsuClave().equals(password))) {
//                    user.setLoginStatus(LoginStatus.INVALID_USERNAME_OR_PASSWORD);
//                }
//            } catch (NoResultException ex) {
//                user.setLoginStatus(LoginStatus.INVALID_USERNAME_OR_PASSWORD);
//            }
//            isLoginSuccessful = user.getLoginStatus().equals(LoginStatus.SUCCESSFUL);
//            if (isLoginSuccessful) {
//                user.setUsuFechaUltimoLogin(new Date());
////                em.merge(user);
//            }
//
//        } catch (RuntimeException ex) {
//            throw ex;
//        }
//        return user;
//    }
}
