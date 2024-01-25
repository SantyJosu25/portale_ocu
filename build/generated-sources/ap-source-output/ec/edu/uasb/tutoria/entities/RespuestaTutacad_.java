package ec.edu.uasb.tutoria.entities;

import ec.edu.uasb.tutoria.entities.RespuestaTutacadPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(RespuestaTutacad.class)
public class RespuestaTutacad_ { 

    public static volatile SingularAttribute<RespuestaTutacad, String> texto;
    public static volatile SingularAttribute<RespuestaTutacad, Date> fecha;
    public static volatile SingularAttribute<RespuestaTutacad, Long> valor;
    public static volatile SingularAttribute<RespuestaTutacad, RespuestaTutacadPK> respuestaTutacadPK;

}