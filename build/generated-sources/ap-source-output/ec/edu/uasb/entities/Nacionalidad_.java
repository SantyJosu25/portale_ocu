package ec.edu.uasb.entities;

import ec.edu.uasb.portalE.entities.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Nacionalidad.class)
public class Nacionalidad_ { 

    public static volatile SingularAttribute<Nacionalidad, String> nombreNacionalidad;
    public static volatile SingularAttribute<Nacionalidad, String> conesupNacionalidad;
    public static volatile CollectionAttribute<Nacionalidad, Estudiante> estudianteCollection;
    public static volatile SingularAttribute<Nacionalidad, Long> codigoNacionalidad;

}