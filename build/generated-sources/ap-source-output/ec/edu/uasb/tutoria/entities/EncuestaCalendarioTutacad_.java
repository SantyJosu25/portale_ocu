package ec.edu.uasb.tutoria.entities;

import ec.edu.uasb.tutoria.entities.EncuestaCalendarioTutacadPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(EncuestaCalendarioTutacad.class)
public class EncuestaCalendarioTutacad_ { 

    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Integer> numRecordatorio;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Character> tipoEncuesta;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Date> fReapertura;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Date> fechaFin;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Integer> codigoInstancia;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, EncuestaCalendarioTutacadPK> encuestaCalendarioTutacadPK;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Date> fFinCalificacion;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Date> fInicioCalificacion;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Date> fechaInicio;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Character> estadoEncuesta;
    public static volatile SingularAttribute<EncuestaCalendarioTutacad, Integer> numRecordatorioReapertura;

}