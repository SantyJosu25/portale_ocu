package ec.edu.uasb.portalE.entities;

import ec.edu.uasb.portalE.entities.AsignaturaModelPk;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(AsignaturaModel.class)
public class AsignaturaModel_ { 

    public static volatile SingularAttribute<AsignaturaModel, Long> anio;
    public static volatile SingularAttribute<AsignaturaModel, String> matnivEstado;
    public static volatile SingularAttribute<AsignaturaModel, Date> fechaCrea;
    public static volatile SingularAttribute<AsignaturaModel, String> estadoRegmat;
    public static volatile SingularAttribute<AsignaturaModel, BigDecimal> asistenciaNtciclo;
    public static volatile SingularAttribute<AsignaturaModel, String> nombreParalelo;
    public static volatile SingularAttribute<AsignaturaModel, String> siglaTmateria;
    public static volatile SingularAttribute<AsignaturaModel, String> nombreNivel;
    public static volatile SingularAttribute<AsignaturaModel, BigDecimal> notaNtciclo;
    public static volatile SingularAttribute<AsignaturaModel, String> identifMateria;
    public static volatile SingularAttribute<AsignaturaModel, Long> estadoPagEstampilla;
    public static volatile SingularAttribute<AsignaturaModel, String> estadoSyllabus;
    public static volatile SingularAttribute<AsignaturaModel, Long> ncredRegmat;
    public static volatile SingularAttribute<AsignaturaModel, Long> evaluacionRealizada;
    public static volatile SingularAttribute<AsignaturaModel, AsignaturaModelPk> asignaturaModelPk;
    public static volatile SingularAttribute<AsignaturaModel, String> estadoNtciclo;
    public static volatile SingularAttribute<AsignaturaModel, String> nombreMateria;

}