package ec.edu.uasb.entities;

import ec.edu.uasb.entities.Canton;
import ec.edu.uasb.entities.Provincia;
import ec.edu.uasb.entities.ProvinciaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile SingularAttribute<Provincia, String> nombreProvincia;
    public static volatile CollectionAttribute<Provincia, Canton> cantonCollection;
    public static volatile SingularAttribute<Provincia, Provincia> provincia;
    public static volatile SingularAttribute<Provincia, ProvinciaPK> provinciaPK;
    public static volatile SingularAttribute<Provincia, Provincia> provincia1;

}