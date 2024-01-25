package ec.edu.uasb.form.entities;

import ec.edu.uasb.form.entities.Encuesta;
import ec.edu.uasb.form.entities.PreguntaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Pregunta.class)
public class Pregunta_ { 

    public static volatile SingularAttribute<Pregunta, Long> peso;
    public static volatile SingularAttribute<Pregunta, PreguntaPK> preguntaPK;
    public static volatile SingularAttribute<Pregunta, Encuesta> encuesta;
    public static volatile SingularAttribute<Pregunta, Character> tipo;
    public static volatile SingularAttribute<Pregunta, String> descripcion;
    public static volatile SingularAttribute<Pregunta, Long> preguntaPadre;
    public static volatile SingularAttribute<Pregunta, String> formato;
    public static volatile SingularAttribute<Pregunta, Long> nivel;
    public static volatile SingularAttribute<Pregunta, String> referencia;

}