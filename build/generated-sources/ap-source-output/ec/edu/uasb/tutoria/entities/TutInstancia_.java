package ec.edu.uasb.tutoria.entities;

import ec.edu.uasb.tutoria.entities.TutSolicitudTutoria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(TutInstancia.class)
public class TutInstancia_ { 

    public static volatile SingularAttribute<TutInstancia, Date> tinFechaModifica;
    public static volatile SingularAttribute<TutInstancia, Long> depCodigo;
    public static volatile CollectionAttribute<TutInstancia, TutSolicitudTutoria> tutSolicitudTutoriaCollection;
    public static volatile SingularAttribute<TutInstancia, String> tinEmail;
    public static volatile SingularAttribute<TutInstancia, String> tinNombre;
    public static volatile SingularAttribute<TutInstancia, String> tinEstado;
    public static volatile SingularAttribute<TutInstancia, String> tinDescripcion;
    public static volatile SingularAttribute<TutInstancia, Date> tinFechaCrea;
    public static volatile SingularAttribute<TutInstancia, Long> tinCodigo;
    public static volatile SingularAttribute<TutInstancia, String> tinResponsale;
    public static volatile SingularAttribute<TutInstancia, String> tinUsuarioModifica;
    public static volatile SingularAttribute<TutInstancia, String> tinUsuarioCrea;
    public static volatile SingularAttribute<TutInstancia, String> tinTipo;

}