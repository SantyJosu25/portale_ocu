package ec.edu.uasb.entities;

import ec.edu.uasb.entities.CiudadPK;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ { 

    public static volatile SingularAttribute<Ciudad, CiudadPK> ciudadPK;
    public static volatile SingularAttribute<Ciudad, String> nomCiudad;
    public static volatile SingularAttribute<Ciudad, BigDecimal> puTransp;

}