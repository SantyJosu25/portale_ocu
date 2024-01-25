package ec.edu.uasb.portalE.entities;

import ec.edu.uasb.portalE.entities.CicloAcademicoPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(CicloAcademico.class)
public class CicloAcademico_ { 

    public static volatile SingularAttribute<CicloAcademico, CicloAcademicoPK> cicloAcademicoPK;
    public static volatile SingularAttribute<CicloAcademico, Date> fFinal;
    public static volatile SingularAttribute<CicloAcademico, Long> nrInscripcion;
    public static volatile SingularAttribute<CicloAcademico, Long> nrFormulario;
    public static volatile SingularAttribute<CicloAcademico, Character> estadoCiclo;
    public static volatile SingularAttribute<CicloAcademico, Date> fInicio;
    public static volatile SingularAttribute<CicloAcademico, String> nombreCiclo;
    public static volatile SingularAttribute<CicloAcademico, Long> nrMatricula;

}