package ec.edu.uasb.tutoria.entities;

import ec.edu.uasb.tutoria.entities.EstudianteUltimamatriculaPK;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-01-16T09:25:55")
@StaticMetamodel(EstudianteUltimamatricula.class)
public class EstudianteUltimamatricula_ { 

    public static volatile SingularAttribute<EstudianteUltimamatricula, BigInteger> anio;
    public static volatile SingularAttribute<EstudianteUltimamatricula, Integer> codEstudiante;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> apellEstudiante;
    public static volatile SingularAttribute<EstudianteUltimamatricula, BigInteger> inicio;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> cedPasEstudiante;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> fin;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> programa;
    public static volatile SingularAttribute<EstudianteUltimamatricula, Short> ejercicio;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> area;
    public static volatile SingularAttribute<EstudianteUltimamatricula, EstudianteUltimamatriculaPK> estudianteUltimamatriculaPK;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> codigoNiveacad;
    public static volatile SingularAttribute<EstudianteUltimamatricula, String> nomEstudiante;
    public static volatile SingularAttribute<EstudianteUltimamatricula, Integer> ciclo;
    public static volatile SingularAttribute<EstudianteUltimamatricula, BigInteger> anioInicial;
    public static volatile SingularAttribute<EstudianteUltimamatricula, Integer> codigoFacultad;

}