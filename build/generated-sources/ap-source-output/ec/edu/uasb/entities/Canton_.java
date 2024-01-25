package ec.edu.uasb.entities;

import ec.edu.uasb.entities.CantonPK;
import ec.edu.uasb.entities.Parroquia;
import ec.edu.uasb.entities.Provincia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Canton.class)
public class Canton_ { 

    public static volatile SingularAttribute<Canton, String> nombreCanton;
    public static volatile SingularAttribute<Canton, CantonPK> cantonPK;
    public static volatile CollectionAttribute<Canton, Parroquia> parroquiaCollection;
    public static volatile SingularAttribute<Canton, Provincia> provincia;

}