package ec.edu.uasb.form.entities;

import ec.edu.uasb.form.entities.RespuestaPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Respuesta.class)
public class Respuesta_ { 

    public static volatile SingularAttribute<Respuesta, RespuestaPK> respuestaPK;
    public static volatile SingularAttribute<Respuesta, String> texto;
    public static volatile SingularAttribute<Respuesta, Date> fecha;
    public static volatile SingularAttribute<Respuesta, Long> valor;

}