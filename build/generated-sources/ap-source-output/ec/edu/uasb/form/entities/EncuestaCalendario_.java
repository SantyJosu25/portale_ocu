package ec.edu.uasb.form.entities;

import ec.edu.uasb.form.entities.Encuesta;
import ec.edu.uasb.form.entities.EncuestaCalendarioPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(EncuestaCalendario.class)
public class EncuestaCalendario_ { 

    public static volatile SingularAttribute<EncuestaCalendario, String> tipoEncuesta;
    public static volatile SingularAttribute<EncuestaCalendario, Encuesta> encuesta;
    public static volatile SingularAttribute<EncuestaCalendario, Date> fechaFin;
    public static volatile SingularAttribute<EncuestaCalendario, Date> fFinCalificacion;
    public static volatile SingularAttribute<EncuestaCalendario, Date> fInicioCalificacion;
    public static volatile SingularAttribute<EncuestaCalendario, Date> fechaInicio;
    public static volatile SingularAttribute<EncuestaCalendario, Character> estadoEncuesta;
    public static volatile SingularAttribute<EncuestaCalendario, EncuestaCalendarioPK> encuestaCalendarioPK;

}