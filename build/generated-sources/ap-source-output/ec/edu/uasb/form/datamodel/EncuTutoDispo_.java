package ec.edu.uasb.form.datamodel;

import ec.edu.uasb.form.datamodel.EncuTutoDispoPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(EncuTutoDispo.class)
public class EncuTutoDispo_ { 

    public static volatile SingularAttribute<EncuTutoDispo, EncuTutoDispoPK> EncuTutoDispoPK;
    public static volatile SingularAttribute<EncuTutoDispo, Date> fechaFin;
    public static volatile SingularAttribute<EncuTutoDispo, Long> codigoInstancia;
    public static volatile SingularAttribute<EncuTutoDispo, String> tipoTutoria;
    public static volatile SingularAttribute<EncuTutoDispo, String> referencia;
    public static volatile SingularAttribute<EncuTutoDispo, String> programa;
    public static volatile SingularAttribute<EncuTutoDispo, String> titulo;
    public static volatile SingularAttribute<EncuTutoDispo, String> nombres;
    public static volatile SingularAttribute<EncuTutoDispo, String> estado;
    public static volatile SingularAttribute<EncuTutoDispo, String> habilitado;
    public static volatile SingularAttribute<EncuTutoDispo, Long> codigoProfesor;
    public static volatile SingularAttribute<EncuTutoDispo, Long> ciclo;
    public static volatile SingularAttribute<EncuTutoDispo, Date> fechaInicio;
    public static volatile SingularAttribute<EncuTutoDispo, Long> codigoMateria;

}