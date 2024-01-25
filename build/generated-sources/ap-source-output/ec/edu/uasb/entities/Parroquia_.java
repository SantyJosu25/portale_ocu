package ec.edu.uasb.entities;

import ec.edu.uasb.entities.Canton;
import ec.edu.uasb.entities.ParroquiaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Parroquia.class)
public class Parroquia_ { 

    public static volatile SingularAttribute<Parroquia, ParroquiaPK> parroquiaPK;
    public static volatile SingularAttribute<Parroquia, String> nombreParroquia;
    public static volatile SingularAttribute<Parroquia, Canton> canton;

}