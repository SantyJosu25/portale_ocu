package ec.edu.uasb.portalE.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(Certificado.class)
public class Certificado_ { 

    public static volatile SingularAttribute<Certificado, Long> perCodigo;
    public static volatile SingularAttribute<Certificado, Short> anio;
    public static volatile SingularAttribute<Certificado, Date> cerFechaFirma;
    public static volatile SingularAttribute<Certificado, Long> codEstudiante;
    public static volatile SingularAttribute<Certificado, Date> cerFechaDescarga;
    public static volatile SingularAttribute<Certificado, String> cerTrimestre;
    public static volatile SingularAttribute<Certificado, Date> cerFechaCrea;
    public static volatile SingularAttribute<Certificado, String> cerNumero;
    public static volatile SingularAttribute<Certificado, String> cerUsuCrea;
    public static volatile SingularAttribute<Certificado, Long> matricula;
    public static volatile SingularAttribute<Certificado, String> cerEstado;
    public static volatile SingularAttribute<Certificado, BigDecimal> cerValor;
    public static volatile SingularAttribute<Certificado, String> cerTipoDoc;
    public static volatile SingularAttribute<Certificado, Date> cerFechaPago;
    public static volatile SingularAttribute<Certificado, Long> cerCodigo;
    public static volatile SingularAttribute<Certificado, String> cerTransaccion;

}