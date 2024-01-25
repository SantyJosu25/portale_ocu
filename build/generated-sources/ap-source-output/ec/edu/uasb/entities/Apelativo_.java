package ec.edu.uasb.entities;

import ec.edu.uasb.portalE.entities.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Apelativo.class)
public class Apelativo_ { 

    public static volatile SingularAttribute<Apelativo, String> nombreApelativo;
    public static volatile SingularAttribute<Apelativo, String> nombreFemApelativo;
    public static volatile CollectionAttribute<Apelativo, Estudiante> estudianteCollection;
    public static volatile SingularAttribute<Apelativo, String> siglasApelativo;
    public static volatile SingularAttribute<Apelativo, Long> codigoApelativo;
    public static volatile SingularAttribute<Apelativo, String> siglasFemApelativo;

}