package ec.edu.uasb.form.datamodel;

import ec.edu.uasb.form.datamodel.EncuProgDispoPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(EncuProgDispo.class)
public class EncuProgDispo_ { 

    public static volatile SingularAttribute<EncuProgDispo, EncuProgDispoPK> encuProgDispoPK;
    public static volatile SingularAttribute<EncuProgDispo, String> programa;
    public static volatile SingularAttribute<EncuProgDispo, String> titulo;
    public static volatile SingularAttribute<EncuProgDispo, String> nombres;
    public static volatile SingularAttribute<EncuProgDispo, String> estado;
    public static volatile SingularAttribute<EncuProgDispo, String> habilitado;
    public static volatile SingularAttribute<EncuProgDispo, Date> fechaFin;
    public static volatile SingularAttribute<EncuProgDispo, Long> codigoProfesor;
    public static volatile SingularAttribute<EncuProgDispo, Long> ciclo;
    public static volatile SingularAttribute<EncuProgDispo, Date> fechaInicio;
    public static volatile SingularAttribute<EncuProgDispo, String> referencia;

}