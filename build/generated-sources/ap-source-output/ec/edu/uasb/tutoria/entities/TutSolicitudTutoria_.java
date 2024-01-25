package ec.edu.uasb.tutoria.entities;

import ec.edu.uasb.tutoria.entities.TutGrupoTutoria;
import ec.edu.uasb.tutoria.entities.TutInstancia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(TutSolicitudTutoria.class)
public class TutSolicitudTutoria_ { 

    public static volatile SingularAttribute<TutSolicitudTutoria, Date> tstFechaAprobado;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstPiso;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> tstCodigo;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> codArea;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstRolTesis;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> tstCodEjercicio;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstTipoSolicitante;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstTipoTutoria;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstTipoGrupo;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstLink;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> programa;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstObservacionEstudiante;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstUsuarioModifica;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> tstTutor;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstEstadoSolicitado;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> area;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstMateria;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> tstCodProfesor;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstHora;
    public static volatile SingularAttribute<TutSolicitudTutoria, Integer> tstDuracion;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> codigoEsp;
    public static volatile SingularAttribute<TutSolicitudTutoria, Long> tstCodigoSolicitante;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstAprobadoPor;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstObservacion;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstCorreo;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstEdificio;
    public static volatile ListAttribute<TutSolicitudTutoria, TutGrupoTutoria> tutGrupoTutoriaList;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstModalidad;
    public static volatile SingularAttribute<TutSolicitudTutoria, Date> tstFechaTutoria;
    public static volatile SingularAttribute<TutSolicitudTutoria, Integer> tstAnioSolicitud;
    public static volatile SingularAttribute<TutSolicitudTutoria, Date> tstFechaModifica;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstInstalacion;
    public static volatile SingularAttribute<TutSolicitudTutoria, Integer> tstAnioEstudiante;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstUsuarioCrea;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstFaseTutoria;
    public static volatile SingularAttribute<TutSolicitudTutoria, String> tstTema;
    public static volatile SingularAttribute<TutSolicitudTutoria, TutInstancia> tstCodInstancia;
    public static volatile SingularAttribute<TutSolicitudTutoria, Date> tstFechaCrea;

}