package ec.edu.uasb.form.entities;

import ec.edu.uasb.form.entities.EncuestaCalendario;
import ec.edu.uasb.form.entities.Pregunta;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Encuesta.class)
public class Encuesta_ { 

    public static volatile CollectionAttribute<Encuesta, Pregunta> preguntaCollection;
    public static volatile SingularAttribute<Encuesta, String> titulo;
    public static volatile SingularAttribute<Encuesta, Long> codigoEncuesta;
    public static volatile SingularAttribute<Encuesta, String> descripcion;
    public static volatile CollectionAttribute<Encuesta, EncuestaCalendario> encuestaCalendarioCollection;
    public static volatile SingularAttribute<Encuesta, Character> uso;
    public static volatile SingularAttribute<Encuesta, String> referencia;

}