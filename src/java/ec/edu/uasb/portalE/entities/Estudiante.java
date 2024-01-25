/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.entities;

import ec.edu.uasb.entities.Apelativo;
import ec.edu.uasb.entities.Nacionalidad;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "ESTUDIANTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByBaseDir", query = "SELECT e FROM Estudiante e WHERE e.estudiantePK.baseDir = :baseDir"),
    @NamedQuery(name = "Estudiante.findByCodEstudiante", query = "SELECT e FROM Estudiante e WHERE e.estudiantePK.codEstudiante = :codEstudiante"),
    @NamedQuery(name = "Estudiante.findByCodigoInst", query = "SELECT e FROM Estudiante e WHERE e.codigoInst = :codigoInst"),
    @NamedQuery(name = "Estudiante.findByCodigoFuente", query = "SELECT e FROM Estudiante e WHERE e.codigoFuente = :codigoFuente"),
    @NamedQuery(name = "Estudiante.findByCodigoEstado", query = "SELECT e FROM Estudiante e WHERE e.codigoEstado = :codigoEstado"),
    @NamedQuery(name = "Estudiante.findByCodigoTitulo", query = "SELECT e FROM Estudiante e WHERE e.codigoTitulo = :codigoTitulo"),
    @NamedQuery(name = "Estudiante.findByTipoCedPasEstudiante", query = "SELECT e FROM Estudiante e WHERE e.tipoCedPasEstudiante = :tipoCedPasEstudiante"),
    @NamedQuery(name = "Estudiante.findByCedPasEstudiante", query = "SELECT e FROM Estudiante e WHERE e.cedPasEstudiante = :cedPasEstudiante"),
    @NamedQuery(name = "Estudiante.findByEstadoCond", query = "SELECT e FROM Estudiante e WHERE e.estadoCond = :estadoCond"),
    @NamedQuery(name = "Estudiante.findByDescCondicion", query = "SELECT e FROM Estudiante e WHERE e.descCondicion = :descCondicion"),
    @NamedQuery(name = "Estudiante.findByCodPaisAcademico", query = "SELECT e FROM Estudiante e WHERE e.codPaisAcademico = :codPaisAcademico"),
    @NamedQuery(name = "Estudiante.findByCodPaisOrigen", query = "SELECT e FROM Estudiante e WHERE e.codPaisOrigen = :codPaisOrigen"),
    @NamedQuery(name = "Estudiante.findByCodCiudadOrigen", query = "SELECT e FROM Estudiante e WHERE e.codCiudadOrigen = :codCiudadOrigen"),
    @NamedQuery(name = "Estudiante.findByCodigoProvincia", query = "SELECT e FROM Estudiante e WHERE e.codigoProvincia = :codigoProvincia"),
    @NamedQuery(name = "Estudiante.findByCodigoCanton", query = "SELECT e FROM Estudiante e WHERE e.codigoCanton = :codigoCanton"),
    @NamedQuery(name = "Estudiante.findByCodigoParroquia", query = "SELECT e FROM Estudiante e WHERE e.codigoParroquia = :codigoParroquia"),
    @NamedQuery(name = "Estudiante.findByNomEstudiante", query = "SELECT e FROM Estudiante e WHERE e.nomEstudiante = :nomEstudiante"),
    @NamedQuery(name = "Estudiante.findByApellEstudiante", query = "SELECT e FROM Estudiante e WHERE e.apellEstudiante = :apellEstudiante"),
    @NamedQuery(name = "Estudiante.findByFechaNacimiento", query = "SELECT e FROM Estudiante e WHERE e.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Estudiante.findBySexo", query = "SELECT e FROM Estudiante e WHERE e.sexo = :sexo"),
    @NamedQuery(name = "Estudiante.findByDireccion", query = "SELECT e FROM Estudiante e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Estudiante.findByTelefono", query = "SELECT e FROM Estudiante e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Estudiante.findByCelular", query = "SELECT e FROM Estudiante e WHERE e.celular = :celular"),
    @NamedQuery(name = "Estudiante.findByEmail", query = "SELECT e FROM Estudiante e WHERE e.email = :email"),
    @NamedQuery(name = "Estudiante.findByEmailsecEstudiante", query = "SELECT e FROM Estudiante e WHERE e.emailsecEstudiante = :emailsecEstudiante"),
    @NamedQuery(name = "Estudiante.findByObsEstudiante", query = "SELECT e FROM Estudiante e WHERE e.obsEstudiante = :obsEstudiante"),
    @NamedQuery(name = "Estudiante.findByEstadoCivil", query = "SELECT e FROM Estudiante e WHERE e.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "Estudiante.findByTrabajo", query = "SELECT e FROM Estudiante e WHERE e.trabajo = :trabajo"),
    @NamedQuery(name = "Estudiante.findByCargo", query = "SELECT e FROM Estudiante e WHERE e.cargo = :cargo"),
    @NamedQuery(name = "Estudiante.findByDirecTrab", query = "SELECT e FROM Estudiante e WHERE e.direcTrab = :direcTrab"),
    @NamedQuery(name = "Estudiante.findByTelfTrab", query = "SELECT e FROM Estudiante e WHERE e.telfTrab = :telfTrab"),
    @NamedQuery(name = "Estudiante.findByEnvioCorrespondencia", query = "SELECT e FROM Estudiante e WHERE e.envioCorrespondencia = :envioCorrespondencia"),
    @NamedQuery(name = "Estudiante.findByCasillapostal", query = "SELECT e FROM Estudiante e WHERE e.casillapostal = :casillapostal"),
    @NamedQuery(name = "Estudiante.findByCasillapostalTrab", query = "SELECT e FROM Estudiante e WHERE e.casillapostalTrab = :casillapostalTrab"),
    @NamedQuery(name = "Estudiante.findByCiudadTrab", query = "SELECT e FROM Estudiante e WHERE e.ciudadTrab = :ciudadTrab"),
    @NamedQuery(name = "Estudiante.findByPaisTrab", query = "SELECT e FROM Estudiante e WHERE e.paisTrab = :paisTrab"),
    @NamedQuery(name = "Estudiante.findByCiudadDom", query = "SELECT e FROM Estudiante e WHERE e.ciudadDom = :ciudadDom"),
    @NamedQuery(name = "Estudiante.findByPaisDom", query = "SELECT e FROM Estudiante e WHERE e.paisDom = :paisDom"),
    @NamedQuery(name = "Estudiante.findByTipoDir", query = "SELECT e FROM Estudiante e WHERE e.tipoDir = :tipoDir"),
    @NamedQuery(name = "Estudiante.findByFax", query = "SELECT e FROM Estudiante e WHERE e.fax = :fax"),
    @NamedQuery(name = "Estudiante.findByWebsiteDir", query = "SELECT e FROM Estudiante e WHERE e.websiteDir = :websiteDir"),
    @NamedQuery(name = "Estudiante.findByInstitucionDir", query = "SELECT e FROM Estudiante e WHERE e.institucionDir = :institucionDir"),
    @NamedQuery(name = "Estudiante.findByNivelDir", query = "SELECT e FROM Estudiante e WHERE e.nivelDir = :nivelDir"),
    @NamedQuery(name = "Estudiante.findByUsuarioCrea", query = "SELECT e FROM Estudiante e WHERE e.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "Estudiante.findByUsuarioUltmodific", query = "SELECT e FROM Estudiante e WHERE e.usuarioUltmodific = :usuarioUltmodific"),
    @NamedQuery(name = "Estudiante.findByFechaCrea", query = "SELECT e FROM Estudiante e WHERE e.fechaCrea = :fechaCrea"),
    @NamedQuery(name = "Estudiante.findByFechaUltmodific", query = "SELECT e FROM Estudiante e WHERE e.fechaUltmodific = :fechaUltmodific"),
    @NamedQuery(name = "Estudiante.findByCodigoProfesor", query = "SELECT e FROM Estudiante e WHERE e.codigoProfesor = :codigoProfesor"),
    @NamedQuery(name = "Estudiante.findByTipoDato", query = "SELECT e FROM Estudiante e WHERE e.tipoDato = :tipoDato"),
    @NamedQuery(name = "Estudiante.findByTelfContRapido", query = "SELECT e FROM Estudiante e WHERE e.telfContRapido = :telfContRapido"),
    @NamedQuery(name = "Estudiante.findByPaisContRapido", query = "SELECT e FROM Estudiante e WHERE e.paisContRapido = :paisContRapido"),
    @NamedQuery(name = "Estudiante.findByCiudadContRapido", query = "SELECT e FROM Estudiante e WHERE e.ciudadContRapido = :ciudadContRapido"),
    @NamedQuery(name = "Estudiante.findByDireccionContRapido", query = "SELECT e FROM Estudiante e WHERE e.direccionContRapido = :direccionContRapido"),
    @NamedQuery(name = "Estudiante.findByTipoContRapido", query = "SELECT e FROM Estudiante e WHERE e.tipoContRapido = :tipoContRapido"),
    @NamedQuery(name = "Estudiante.findByImagen", query = "SELECT e FROM Estudiante e WHERE e.imagen = :imagen"),
    @NamedQuery(name = "Estudiante.findByEnvioInformacion", query = "SELECT e FROM Estudiante e WHERE e.envioInformacion = :envioInformacion"),
    @NamedQuery(name = "Estudiante.findByPrimeraLengua", query = "SELECT e FROM Estudiante e WHERE e.primeraLengua = :primeraLengua"),
    @NamedQuery(name = "Estudiante.findBySegundaLengua", query = "SELECT e FROM Estudiante e WHERE e.segundaLengua = :segundaLengua"),
    @NamedQuery(name = "Estudiante.findByTipoVivienda", query = "SELECT e FROM Estudiante e WHERE e.tipoVivienda = :tipoVivienda"),
    @NamedQuery(name = "Estudiante.findByNivelmaxAprobado", query = "SELECT e FROM Estudiante e WHERE e.nivelmaxAprobado = :nivelmaxAprobado"),
    @NamedQuery(name = "Estudiante.findByTituloAlcanzado", query = "SELECT e FROM Estudiante e WHERE e.tituloAlcanzado = :tituloAlcanzado"),
    @NamedQuery(name = "Estudiante.findByExperienciaDocente", query = "SELECT e FROM Estudiante e WHERE e.experienciaDocente = :experienciaDocente"),
    @NamedQuery(name = "Estudiante.findByExperienciaCapacitacion", query = "SELECT e FROM Estudiante e WHERE e.experienciaCapacitacion = :experienciaCapacitacion"),
    @NamedQuery(name = "Estudiante.findByFinicioCecm", query = "SELECT e FROM Estudiante e WHERE e.finicioCecm = :finicioCecm"),
    @NamedQuery(name = "Estudiante.findByFterminoCecm", query = "SELECT e FROM Estudiante e WHERE e.fterminoCecm = :fterminoCecm"),
    @NamedQuery(name = "Estudiante.findByZonaEscuela", query = "SELECT e FROM Estudiante e WHERE e.zonaEscuela = :zonaEscuela"),
    @NamedQuery(name = "Estudiante.findByCroquisEscuela", query = "SELECT e FROM Estudiante e WHERE e.croquisEscuela = :croquisEscuela"),
    @NamedQuery(name = "Estudiante.findByCodigoMecMed", query = "SELECT e FROM Estudiante e WHERE e.codigoMecMed = :codigoMecMed"),
    @NamedQuery(name = "Estudiante.findByNumeroMec", query = "SELECT e FROM Estudiante e WHERE e.numeroMec = :numeroMec"),
    @NamedQuery(name = "Estudiante.findByTamanoEscuela", query = "SELECT e FROM Estudiante e WHERE e.tamanoEscuela = :tamanoEscuela"),
    @NamedQuery(name = "Estudiante.findByNivelInicial", query = "SELECT e FROM Estudiante e WHERE e.nivelInicial = :nivelInicial"),
    @NamedQuery(name = "Estudiante.findByNivelPrimaria", query = "SELECT e FROM Estudiante e WHERE e.nivelPrimaria = :nivelPrimaria"),
    @NamedQuery(name = "Estudiante.findByNivelSecundaria", query = "SELECT e FROM Estudiante e WHERE e.nivelSecundaria = :nivelSecundaria"),
    @NamedQuery(name = "Estudiante.findByPrimerGrado", query = "SELECT e FROM Estudiante e WHERE e.primerGrado = :primerGrado"),
    @NamedQuery(name = "Estudiante.findBySegundoGrado", query = "SELECT e FROM Estudiante e WHERE e.segundoGrado = :segundoGrado"),
    @NamedQuery(name = "Estudiante.findByTercerGrado", query = "SELECT e FROM Estudiante e WHERE e.tercerGrado = :tercerGrado"),
    @NamedQuery(name = "Estudiante.findByCuartoGrado", query = "SELECT e FROM Estudiante e WHERE e.cuartoGrado = :cuartoGrado"),
    @NamedQuery(name = "Estudiante.findByQuintoGrado", query = "SELECT e FROM Estudiante e WHERE e.quintoGrado = :quintoGrado"),
    @NamedQuery(name = "Estudiante.findBySextoGrado", query = "SELECT e FROM Estudiante e WHERE e.sextoGrado = :sextoGrado"),
    @NamedQuery(name = "Estudiante.findByCaracteristicaDocente", query = "SELECT e FROM Estudiante e WHERE e.caracteristicaDocente = :caracteristicaDocente"),
    @NamedQuery(name = "Estudiante.findByJornadaManana", query = "SELECT e FROM Estudiante e WHERE e.jornadaManana = :jornadaManana"),
    @NamedQuery(name = "Estudiante.findByJornadaTarde", query = "SELECT e FROM Estudiante e WHERE e.jornadaTarde = :jornadaTarde"),
    @NamedQuery(name = "Estudiante.findByHoraComienzo", query = "SELECT e FROM Estudiante e WHERE e.horaComienzo = :horaComienzo"),
    @NamedQuery(name = "Estudiante.findByHoraTermino", query = "SELECT e FROM Estudiante e WHERE e.horaTermino = :horaTermino"),
    @NamedQuery(name = "Estudiante.findByTipoInfCett", query = "SELECT e FROM Estudiante e WHERE e.tipoInfCett = :tipoInfCett"),
    @NamedQuery(name = "Estudiante.findByTipoInstruccion", query = "SELECT e FROM Estudiante e WHERE e.tipoInstruccion = :tipoInstruccion"),
    @NamedQuery(name = "Estudiante.findByOtroTipoInst", query = "SELECT e FROM Estudiante e WHERE e.otroTipoInst = :otroTipoInst"),
    @NamedQuery(name = "Estudiante.findByTipoAupicio", query = "SELECT e FROM Estudiante e WHERE e.tipoAupicio = :tipoAupicio"),
    @NamedQuery(name = "Estudiante.findByOtroTipoAuspicio", query = "SELECT e FROM Estudiante e WHERE e.otroTipoAuspicio = :otroTipoAuspicio"),
    @NamedQuery(name = "Estudiante.findByCedValida", query = "SELECT e FROM Estudiante e WHERE e.cedValida = :cedValida"),
    @NamedQuery(name = "Estudiante.findByCodigoInstcap", query = "SELECT e FROM Estudiante e WHERE e.codigoInstcap = :codigoInstcap"),
    @NamedQuery(name = "Estudiante.findByCampo1", query = "SELECT e FROM Estudiante e WHERE e.campo1 = :campo1"),
    @NamedQuery(name = "Estudiante.findByCampo2", query = "SELECT e FROM Estudiante e WHERE e.campo2 = :campo2"),
    @NamedQuery(name = "Estudiante.findByCampo3", query = "SELECT e FROM Estudiante e WHERE e.campo3 = :campo3"),
    @NamedQuery(name = "Estudiante.findByCampo4", query = "SELECT e FROM Estudiante e WHERE e.campo4 = :campo4"),
    @NamedQuery(name = "Estudiante.findByCampo5", query = "SELECT e FROM Estudiante e WHERE e.campo5 = :campo5"),
    @NamedQuery(name = "Estudiante.findByCampo6", query = "SELECT e FROM Estudiante e WHERE e.campo6 = :campo6"),
    @NamedQuery(name = "Estudiante.findByCampo7", query = "SELECT e FROM Estudiante e WHERE e.campo7 = :campo7"),
    @NamedQuery(name = "Estudiante.findByCampo8", query = "SELECT e FROM Estudiante e WHERE e.campo8 = :campo8"),
    @NamedQuery(name = "Estudiante.findByCampo9", query = "SELECT e FROM Estudiante e WHERE e.campo9 = :campo9"),
    @NamedQuery(name = "Estudiante.findByCampo10", query = "SELECT e FROM Estudiante e WHERE e.campo10 = :campo10"),
    @NamedQuery(name = "Estudiante.findByCampo11", query = "SELECT e FROM Estudiante e WHERE e.campo11 = :campo11"),
    @NamedQuery(name = "Estudiante.findByCampo12", query = "SELECT e FROM Estudiante e WHERE e.campo12 = :campo12"),
    @NamedQuery(name = "Estudiante.findByCampo13", query = "SELECT e FROM Estudiante e WHERE e.campo13 = :campo13"),
    @NamedQuery(name = "Estudiante.findByCampo14", query = "SELECT e FROM Estudiante e WHERE e.campo14 = :campo14"),
    @NamedQuery(name = "Estudiante.findByCampo15", query = "SELECT e FROM Estudiante e WHERE e.campo15 = :campo15"),
    @NamedQuery(name = "Estudiante.findByClave", query = "SELECT e FROM Estudiante e WHERE e.clave = :clave"),
    @NamedQuery(name = "Estudiante.findByModalidad", query = "SELECT e FROM Estudiante e WHERE e.modalidad = :modalidad"),
    @NamedQuery(name = "Estudiante.findByInicioCecm", query = "SELECT e FROM Estudiante e WHERE e.inicioCecm = :inicioCecm"),
    @NamedQuery(name = "Estudiante.findByFechaFinalizacion", query = "SELECT e FROM Estudiante e WHERE e.fechaFinalizacion = :fechaFinalizacion"),
    @NamedQuery(name = "Estudiante.findByFechaCertificacion", query = "SELECT e FROM Estudiante e WHERE e.fechaCertificacion = :fechaCertificacion"),
    @NamedQuery(name = "Estudiante.findByFechaRetiro", query = "SELECT e FROM Estudiante e WHERE e.fechaRetiro = :fechaRetiro"),
    @NamedQuery(name = "Estudiante.findByMotivoRetiro", query = "SELECT e FROM Estudiante e WHERE e.motivoRetiro = :motivoRetiro"),
    @NamedQuery(name = "Estudiante.findByOtroMotivo", query = "SELECT e FROM Estudiante e WHERE e.otroMotivo = :otroMotivo"),
    @NamedQuery(name = "Estudiante.findByCodigoProvinciaTrabajo", query = "SELECT e FROM Estudiante e WHERE e.codigoProvinciaTrabajo = :codigoProvinciaTrabajo"),
    @NamedQuery(name = "Estudiante.findByFechaCreaCuenta", query = "SELECT e FROM Estudiante e WHERE e.fechaCreaCuenta = :fechaCreaCuenta"),
    @NamedQuery(name = "Estudiante.findByUsuarioCreaCuenta", query = "SELECT e FROM Estudiante e WHERE e.usuarioCreaCuenta = :usuarioCreaCuenta"),
    @NamedQuery(name = "Estudiante.findByEstadoCreaCuenta", query = "SELECT e FROM Estudiante e WHERE e.estadoCreaCuenta = :estadoCreaCuenta"),
    @NamedQuery(name = "Estudiante.findByNombreEquipo", query = "SELECT e FROM Estudiante e WHERE e.nombreEquipo = :nombreEquipo"),
    @NamedQuery(name = "Estudiante.findByAntivirus", query = "SELECT e FROM Estudiante e WHERE e.antivirus = :antivirus"),
    @NamedQuery(name = "Estudiante.findByWireless", query = "SELECT e FROM Estudiante e WHERE e.wireless = :wireless"),
    @NamedQuery(name = "Estudiante.findByObservacionesUi", query = "SELECT e FROM Estudiante e WHERE e.observacionesUi = :observacionesUi"),
    @NamedQuery(name = "Estudiante.findByValidaCreaCuenta", query = "SELECT e FROM Estudiante e WHERE e.validaCreaCuenta = :validaCreaCuenta"),
    @NamedQuery(name = "Estudiante.findByAnioIngresoTrabajo", query = "SELECT e FROM Estudiante e WHERE e.anioIngresoTrabajo = :anioIngresoTrabajo"),
    @NamedQuery(name = "Estudiante.findBySectorTrabajo", query = "SELECT e FROM Estudiante e WHERE e.sectorTrabajo = :sectorTrabajo"),
    @NamedQuery(name = "Estudiante.findByApelativoEmpleador", query = "SELECT e FROM Estudiante e WHERE e.apelativoEmpleador = :apelativoEmpleador"),
    @NamedQuery(name = "Estudiante.findByApellidoEmpleador", query = "SELECT e FROM Estudiante e WHERE e.apellidoEmpleador = :apellidoEmpleador"),
    @NamedQuery(name = "Estudiante.findByNombreEmpleador", query = "SELECT e FROM Estudiante e WHERE e.nombreEmpleador = :nombreEmpleador"),
    @NamedQuery(name = "Estudiante.findByCargoEmpleador", query = "SELECT e FROM Estudiante e WHERE e.cargoEmpleador = :cargoEmpleador"),
    @NamedQuery(name = "Estudiante.findByEmailEmpleador", query = "SELECT e FROM Estudiante e WHERE e.emailEmpleador = :emailEmpleador"),
    @NamedQuery(name = "Estudiante.findByTelefonoEmpleador", query = "SELECT e FROM Estudiante e WHERE e.telefonoEmpleador = :telefonoEmpleador"),
    @NamedQuery(name = "Estudiante.findByImpresionCarnet", query = "SELECT e FROM Estudiante e WHERE e.impresionCarnet = :impresionCarnet"),
    @NamedQuery(name = "Estudiante.findByEstadoEstudiante", query = "SELECT e FROM Estudiante e WHERE e.estadoEstudiante = :estadoEstudiante")})
public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstudiantePK estudiantePK;
    @Column(name = "CODIGO_INST")
    private Long codigoInst;
    @Column(name = "CODIGO_FUENTE")
    private Long codigoFuente;
    @Column(name = "CODIGO_ESTADO")
    private Long codigoEstado;
    @Column(name = "CODIGO_TITULO")
    private Long codigoTitulo;
    @Column(name = "TIPO_CED_PAS_ESTUDIANTE")
    private Character tipoCedPasEstudiante;
    @Size(max = 30)
    @Column(name = "CED_PAS_ESTUDIANTE")
    private String cedPasEstudiante;
    @Column(name = "ESTADO_COND")
    private Character estadoCond;
    @Size(max = 200)
    @Column(name = "DESC_CONDICION")
    private String descCondicion;
    @Size(max = 3)
    @Column(name = "COD_PAIS_ACADEMICO")
    private String codPaisAcademico;
    @Size(max = 3)
    @Column(name = "COD_PAIS_ORIGEN")
    private String codPaisOrigen;
    @Column(name = "COD_CIUDAD_ORIGEN")
    private Long codCiudadOrigen;
    @Size(max = 30)
    @Column(name = "CODIGO_PROVINCIA")
    private String codigoProvincia;
    @Size(max = 30)
    @Column(name = "CODIGO_CANTON")
    private String codigoCanton;
    @Size(max = 30)
    @Column(name = "CODIGO_PARROQUIA")
    private String codigoParroquia;
    @Size(max = 60)
    @Column(name = "NOM_ESTUDIANTE")
    private String nomEstudiante;
    @Size(max = 60)
    @Column(name = "APELL_ESTUDIANTE")
    private String apellEstudiante;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "SEXO")
    private Character sexo;
    @Size(max = 500)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 100)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 30)
    @Column(name = "CELULAR")
    private String celular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "EMAILSEC_ESTUDIANTE")
    private String emailsecEstudiante;
    @Size(max = 200)
    @Column(name = "OBS_ESTUDIANTE")
    private String obsEstudiante;
    @Column(name = "ESTADO_CIVIL")
    private Character estadoCivil;
    @Size(max = 200)
    @Column(name = "TRABAJO")
    private String trabajo;
    @Size(max = 100)
    @Column(name = "CARGO")
    private String cargo;
    @Size(max = 200)
    @Column(name = "DIREC_TRAB")
    private String direcTrab;
    @Size(max = 100)
    @Column(name = "TELF_TRAB")
    private String telfTrab;
    @Column(name = "ENVIO_CORRESPONDENCIA")
    private Character envioCorrespondencia;
    @Size(max = 30)
    @Column(name = "CASILLAPOSTAL")
    private String casillapostal;
    @Size(max = 30)
    @Column(name = "CASILLAPOSTAL_TRAB")
    private String casillapostalTrab;
    @Column(name = "CIUDAD_TRAB")
    private Long ciudadTrab;
    @Size(max = 3)
    @Column(name = "PAIS_TRAB")
    private String paisTrab;
    @Column(name = "CIUDAD_DOM")
    private Long ciudadDom;
    @Size(max = 3)
    @Column(name = "PAIS_DOM")
    private String paisDom;
    @Column(name = "TIPO_DIR")
    private Character tipoDir;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "FAX")
    private String fax;
    @Size(max = 100)
    @Column(name = "WEBSITE_DIR")
    private String websiteDir;
    @Size(max = 200)
    @Column(name = "INSTITUCION_DIR")
    private String institucionDir;
    @Column(name = "NIVEL_DIR")
    private Long nivelDir;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Size(max = 30)
    @Column(name = "USUARIO_ULTMODIFIC")
    private String usuarioUltmodific;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCrea;
    @Column(name = "FECHA_ULTMODIFIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltmodific;
    @Column(name = "CODIGO_PROFESOR")
    private Long codigoProfesor;
    @Column(name = "TIPO_DATO")
    private Character tipoDato;
    @Size(max = 100)
    @Column(name = "TELF_CONT_RAPIDO")
    private String telfContRapido;
    @Size(max = 3)
    @Column(name = "PAIS_CONT_RAPIDO")
    private String paisContRapido;
    @Column(name = "CIUDAD_CONT_RAPIDO")
    private Long ciudadContRapido;
    @Size(max = 1000)
    @Column(name = "DIRECCION_CONT_RAPIDO")
    private String direccionContRapido;
    @Column(name = "TIPO_CONT_RAPIDO")
    private Character tipoContRapido;
    @Size(max = 200)
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "ENVIO_INFORMACION")
    private Character envioInformacion;
    @Column(name = "PRIMERA_LENGUA")
    private Character primeraLengua;
    @Column(name = "SEGUNDA_LENGUA")
    private Character segundaLengua;
    @Column(name = "TIPO_VIVIENDA")
    private Character tipoVivienda;
    @Size(max = 3)
    @Column(name = "NIVELMAX_APROBADO")
    private String nivelmaxAprobado;
    @Size(max = 100)
    @Column(name = "TITULO_ALCANZADO")
    private String tituloAlcanzado;
    @Column(name = "EXPERIENCIA_DOCENTE")
    private Integer experienciaDocente;
    @Column(name = "EXPERIENCIA_CAPACITACION")
    private Integer experienciaCapacitacion;
    @Column(name = "FINICIO_CECM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finicioCecm;
    @Column(name = "FTERMINO_CECM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fterminoCecm;
    @Column(name = "ZONA_ESCUELA")
    private Character zonaEscuela;
    @Size(max = 200)
    @Column(name = "CROQUIS_ESCUELA")
    private String croquisEscuela;
    @Size(max = 20)
    @Column(name = "CODIGO_MEC_MED")
    private String codigoMecMed;
    @Size(max = 20)
    @Column(name = "NUMERO_MEC")
    private String numeroMec;
    @Column(name = "TAMANO_ESCUELA")
    private Character tamanoEscuela;
    @Column(name = "NIVEL_INICIAL")
    private Character nivelInicial;
    @Column(name = "NIVEL_PRIMARIA")
    private Character nivelPrimaria;
    @Column(name = "NIVEL_SECUNDARIA")
    private Character nivelSecundaria;
    @Column(name = "PRIMER_GRADO")
    private Character primerGrado;
    @Column(name = "SEGUNDO_GRADO")
    private Character segundoGrado;
    @Column(name = "TERCER_GRADO")
    private Character tercerGrado;
    @Column(name = "CUARTO_GRADO")
    private Character cuartoGrado;
    @Column(name = "QUINTO_GRADO")
    private Character quintoGrado;
    @Column(name = "SEXTO_GRADO")
    private Character sextoGrado;
    @Column(name = "CARACTERISTICA_DOCENTE")
    private Character caracteristicaDocente;
    @Column(name = "JORNADA_MANANA")
    private Character jornadaManana;
    @Column(name = "JORNADA_TARDE")
    private Character jornadaTarde;
    @Column(name = "HORA_COMIENZO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaComienzo;
    @Column(name = "HORA_TERMINO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaTermino;
    @Column(name = "TIPO_INF_CETT")
    private Character tipoInfCett;
    @Size(max = 3)
    @Column(name = "TIPO_INSTRUCCION")
    private String tipoInstruccion;
    @Size(max = 100)
    @Column(name = "OTRO_TIPO_INST")
    private String otroTipoInst;
    @Size(max = 30)
    @Column(name = "TIPO_AUPICIO")
    private String tipoAupicio;
    @Size(max = 100)
    @Column(name = "OTRO_TIPO_AUSPICIO")
    private String otroTipoAuspicio;
    @Column(name = "CED_VALIDA")
    private Character cedValida;
    @Column(name = "CODIGO_INSTCAP")
    private Long codigoInstcap;
    @Column(name = "CAMPO1")
    private Character campo1;
    @Column(name = "CAMPO2")
    private Character campo2;
    @Column(name = "CAMPO3")
    private Character campo3;
    @Column(name = "CAMPO4")
    private Character campo4;
    @Column(name = "CAMPO5")
    private Character campo5;
    @Column(name = "CAMPO6")
    private Character campo6;
    @Column(name = "CAMPO7")
    private Character campo7;
    @Column(name = "CAMPO8")
    private Character campo8;
    @Column(name = "CAMPO9")
    private Character campo9;
    @Column(name = "CAMPO10")
    private Character campo10;
    @Column(name = "CAMPO11")
    private Character campo11;
    @Column(name = "CAMPO12")
    private Character campo12;
    @Column(name = "CAMPO13")
    private Character campo13;
    @Column(name = "CAMPO14")
    private Character campo14;
    @Column(name = "CAMPO15")
    private Character campo15;
    @Size(max = 60)
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "MODALIDAD")
    private Character modalidad;
    @Column(name = "INICIO_CECM")
    private Integer inicioCecm;
    @Column(name = "FECHA_FINALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;
    @Column(name = "FECHA_CERTIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCertificacion;
    @Column(name = "FECHA_RETIRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetiro;
    @Column(name = "MOTIVO_RETIRO")
    private Character motivoRetiro;
    @Size(max = 200)
    @Column(name = "OTRO_MOTIVO")
    private String otroMotivo;
    @Size(max = 30)
    @Column(name = "CODIGO_PROVINCIA_TRABAJO")
    private String codigoProvinciaTrabajo;
    @Column(name = "FECHA_CREA_CUENTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreaCuenta;
    @Size(max = 30)
    @Column(name = "USUARIO_CREA_CUENTA")
    private String usuarioCreaCuenta;
    @Column(name = "ESTADO_CREA_CUENTA")
    private Character estadoCreaCuenta;
    @Size(max = 30)
    @Column(name = "NOMBRE_EQUIPO")
    private String nombreEquipo;
    @Size(max = 30)
    @Column(name = "ANTIVIRUS")
    private String antivirus;
    @Column(name = "WIRELESS")
    private Character wireless;
    @Size(max = 200)
    @Column(name = "OBSERVACIONES_UI")
    private String observacionesUi;
    @Column(name = "VALIDA_CREA_CUENTA")
    private Character validaCreaCuenta;
    @Column(name = "ANIO_INGRESO_TRABAJO")
    private Integer anioIngresoTrabajo;
    @Column(name = "SECTOR_TRABAJO")
    private Long sectorTrabajo;
    @Size(max = 60)
    @Column(name = "APELATIVO_EMPLEADOR")
    private String apelativoEmpleador;
    @Size(max = 60)
    @Column(name = "APELLIDO_EMPLEADOR")
    private String apellidoEmpleador;
    @Size(max = 60)
    @Column(name = "NOMBRE_EMPLEADOR")
    private String nombreEmpleador;
    @Size(max = 100)
    @Column(name = "CARGO_EMPLEADOR")
    private String cargoEmpleador;
    @Size(max = 60)
    @Column(name = "EMAIL_EMPLEADOR")
    private String emailEmpleador;
    @Size(max = 60)
    @Column(name = "TELEFONO_EMPLEADOR")
    private String telefonoEmpleador;
    @Column(name = "IMPRESION_CARNET")
    private Character impresionCarnet;
    @Column(name = "ESTADO_ESTUDIANTE")
    private Character estadoEstudiante;
    @JoinColumn(name = "CODIGO_NACIONALIDAD", referencedColumnName = "CODIGO_NACIONALIDAD")
    @ManyToOne
    private Nacionalidad nacionalidad;
    @OneToMany(mappedBy = "estudiante")
    private Collection<Estudiante> estudianteCollection;
    @JoinColumns({
        @JoinColumn(name = "BASE_CONTACTO_DIR", referencedColumnName = "BASE_DIR"),
        @JoinColumn(name = "CONTACTO_DIR", referencedColumnName = "COD_ESTUDIANTE")})
    @ManyToOne
    private Estudiante estudiante;
    @JoinColumn(name = "CODIGO_APELATIVO", referencedColumnName = "CODIGO_APELATIVO")
    @ManyToOne
    private Apelativo apelativo;

    public Estudiante() {
       
    }

    public Estudiante(EstudiantePK estudiantePK) {
        this.estudiantePK = estudiantePK;
    }

    public Estudiante(EstudiantePK estudiantePK, String usuarioCrea, Date fechaCrea) {
        this.estudiantePK = estudiantePK;
        this.usuarioCrea = usuarioCrea;
        this.fechaCrea = fechaCrea;
    }

    public Estudiante(char baseDir, long codEstudiante) {
        this.estudiantePK = new EstudiantePK(baseDir, codEstudiante);
    }

    public EstudiantePK getEstudiantePK() {
        return estudiantePK;
    }

    public void setEstudiantePK(EstudiantePK estudiantePK) {
        this.estudiantePK = estudiantePK;
    }

    public Long getCodigoInst() {
        return codigoInst;
    }

    public void setCodigoInst(Long codigoInst) {
        this.codigoInst = codigoInst;
    }

    public Long getCodigoFuente() {
        return codigoFuente;
    }

    public void setCodigoFuente(Long codigoFuente) {
        this.codigoFuente = codigoFuente;
    }

    public Long getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(Long codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Long getCodigoTitulo() {
        return codigoTitulo;
    }

    public void setCodigoTitulo(Long codigoTitulo) {
        this.codigoTitulo = codigoTitulo;
    }

    public Character getTipoCedPasEstudiante() {
        return tipoCedPasEstudiante;
    }

    public void setTipoCedPasEstudiante(Character tipoCedPasEstudiante) {
        this.tipoCedPasEstudiante = tipoCedPasEstudiante;
    }

    public String getCedPasEstudiante() {
        return cedPasEstudiante;
    }

    public void setCedPasEstudiante(String cedPasEstudiante) {
        this.cedPasEstudiante = cedPasEstudiante;
    }

    public Character getEstadoCond() {
        return estadoCond;
    }

    public void setEstadoCond(Character estadoCond) {
        this.estadoCond = estadoCond;
    }

    public String getDescCondicion() {
        return descCondicion;
    }

    public void setDescCondicion(String descCondicion) {
        this.descCondicion = descCondicion;
    }

    public String getCodPaisAcademico() {
        return codPaisAcademico;
    }

    public void setCodPaisAcademico(String codPaisAcademico) {
        this.codPaisAcademico = codPaisAcademico;
    }

    public String getCodPaisOrigen() {
        return codPaisOrigen;
    }

    public void setCodPaisOrigen(String codPaisOrigen) {
        this.codPaisOrigen = codPaisOrigen;
    }

    public Long getCodCiudadOrigen() {
        return codCiudadOrigen;
    }

    public void setCodCiudadOrigen(Long codCiudadOrigen) {
        this.codCiudadOrigen = codCiudadOrigen;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public String getCodigoParroquia() {
        return codigoParroquia;
    }

    public void setCodigoParroquia(String codigoParroquia) {
        this.codigoParroquia = codigoParroquia;
    }

    public String getNomEstudiante() {
        return nomEstudiante;
    }

    public void setNomEstudiante(String nomEstudiante) {
        this.nomEstudiante = nomEstudiante;
    }

    public String getApellEstudiante() {
        return apellEstudiante;
    }

    public void setApellEstudiante(String apellEstudiante) {
        this.apellEstudiante = apellEstudiante;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailsecEstudiante() {
        return emailsecEstudiante;
    }

    public void setEmailsecEstudiante(String emailsecEstudiante) {
        this.emailsecEstudiante = emailsecEstudiante;
    }

    public String getObsEstudiante() {
        return obsEstudiante;
    }

    public void setObsEstudiante(String obsEstudiante) {
        this.obsEstudiante = obsEstudiante;
    }

    public Character getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDirecTrab() {
        return direcTrab;
    }

    public void setDirecTrab(String direcTrab) {
        this.direcTrab = direcTrab;
    }

    public String getTelfTrab() {
        return telfTrab;
    }

    public void setTelfTrab(String telfTrab) {
        this.telfTrab = telfTrab;
    }

    public Character getEnvioCorrespondencia() {
        return envioCorrespondencia;
    }

    public void setEnvioCorrespondencia(Character envioCorrespondencia) {
        this.envioCorrespondencia = envioCorrespondencia;
    }

    public String getCasillapostal() {
        return casillapostal;
    }

    public void setCasillapostal(String casillapostal) {
        this.casillapostal = casillapostal;
    }

    public String getCasillapostalTrab() {
        return casillapostalTrab;
    }

    public void setCasillapostalTrab(String casillapostalTrab) {
        this.casillapostalTrab = casillapostalTrab;
    }

    public Long getCiudadTrab() {
        return ciudadTrab;
    }

    public void setCiudadTrab(Long ciudadTrab) {
        this.ciudadTrab = ciudadTrab;
    }

    public String getPaisTrab() {
        return paisTrab;
    }

    public void setPaisTrab(String paisTrab) {
        this.paisTrab = paisTrab;
    }

    public Long getCiudadDom() {
        return ciudadDom;
    }

    public void setCiudadDom(Long ciudadDom) {
        this.ciudadDom = ciudadDom;
    }

    public String getPaisDom() {
        return paisDom;
    }

    public void setPaisDom(String paisDom) {
        this.paisDom = paisDom;
    }

    public Character getTipoDir() {
        return tipoDir;
    }

    public void setTipoDir(Character tipoDir) {
        this.tipoDir = tipoDir;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsiteDir() {
        return websiteDir;
    }

    public void setWebsiteDir(String websiteDir) {
        this.websiteDir = websiteDir;
    }

    public String getInstitucionDir() {
        return institucionDir;
    }

    public void setInstitucionDir(String institucionDir) {
        this.institucionDir = institucionDir;
    }

    public Long getNivelDir() {
        return nivelDir;
    }

    public void setNivelDir(Long nivelDir) {
        this.nivelDir = nivelDir;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getUsuarioUltmodific() {
        return usuarioUltmodific;
    }

    public void setUsuarioUltmodific(String usuarioUltmodific) {
        this.usuarioUltmodific = usuarioUltmodific;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Date getFechaUltmodific() {
        return fechaUltmodific;
    }

    public void setFechaUltmodific(Date fechaUltmodific) {
        this.fechaUltmodific = fechaUltmodific;
    }

    public Long getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(Long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public Character getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Character tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getTelfContRapido() {
        return telfContRapido;
    }

    public void setTelfContRapido(String telfContRapido) {
        this.telfContRapido = telfContRapido;
    }

    public String getPaisContRapido() {
        return paisContRapido;
    }

    public void setPaisContRapido(String paisContRapido) {
        this.paisContRapido = paisContRapido;
    }

    public Long getCiudadContRapido() {
        return ciudadContRapido;
    }

    public void setCiudadContRapido(Long ciudadContRapido) {
        this.ciudadContRapido = ciudadContRapido;
    }

    public String getDireccionContRapido() {
        return direccionContRapido;
    }

    public void setDireccionContRapido(String direccionContRapido) {
        this.direccionContRapido = direccionContRapido;
    }

    public Character getTipoContRapido() {
        return tipoContRapido;
    }

    public void setTipoContRapido(Character tipoContRapido) {
        this.tipoContRapido = tipoContRapido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Character getEnvioInformacion() {
        return envioInformacion;
    }

    public void setEnvioInformacion(Character envioInformacion) {
        this.envioInformacion = envioInformacion;
    }

    public Character getPrimeraLengua() {
        return primeraLengua;
    }

    public void setPrimeraLengua(Character primeraLengua) {
        this.primeraLengua = primeraLengua;
    }

    public Character getSegundaLengua() {
        return segundaLengua;
    }

    public void setSegundaLengua(Character segundaLengua) {
        this.segundaLengua = segundaLengua;
    }

    public Character getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(Character tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getNivelmaxAprobado() {
        return nivelmaxAprobado;
    }

    public void setNivelmaxAprobado(String nivelmaxAprobado) {
        this.nivelmaxAprobado = nivelmaxAprobado;
    }

    public String getTituloAlcanzado() {
        return tituloAlcanzado;
    }

    public void setTituloAlcanzado(String tituloAlcanzado) {
        this.tituloAlcanzado = tituloAlcanzado;
    }

    public Integer getExperienciaDocente() {
        return experienciaDocente;
    }

    public void setExperienciaDocente(Integer experienciaDocente) {
        this.experienciaDocente = experienciaDocente;
    }

    public Integer getExperienciaCapacitacion() {
        return experienciaCapacitacion;
    }

    public void setExperienciaCapacitacion(Integer experienciaCapacitacion) {
        this.experienciaCapacitacion = experienciaCapacitacion;
    }

    public Date getFinicioCecm() {
        return finicioCecm;
    }

    public void setFinicioCecm(Date finicioCecm) {
        this.finicioCecm = finicioCecm;
    }

    public Date getFterminoCecm() {
        return fterminoCecm;
    }

    public void setFterminoCecm(Date fterminoCecm) {
        this.fterminoCecm = fterminoCecm;
    }

    public Character getZonaEscuela() {
        return zonaEscuela;
    }

    public void setZonaEscuela(Character zonaEscuela) {
        this.zonaEscuela = zonaEscuela;
    }

    public String getCroquisEscuela() {
        return croquisEscuela;
    }

    public void setCroquisEscuela(String croquisEscuela) {
        this.croquisEscuela = croquisEscuela;
    }

    public String getCodigoMecMed() {
        return codigoMecMed;
    }

    public void setCodigoMecMed(String codigoMecMed) {
        this.codigoMecMed = codigoMecMed;
    }

    public String getNumeroMec() {
        return numeroMec;
    }

    public void setNumeroMec(String numeroMec) {
        this.numeroMec = numeroMec;
    }

    public Character getTamanoEscuela() {
        return tamanoEscuela;
    }

    public void setTamanoEscuela(Character tamanoEscuela) {
        this.tamanoEscuela = tamanoEscuela;
    }

    public Character getNivelInicial() {
        return nivelInicial;
    }

    public void setNivelInicial(Character nivelInicial) {
        this.nivelInicial = nivelInicial;
    }

    public Character getNivelPrimaria() {
        return nivelPrimaria;
    }

    public void setNivelPrimaria(Character nivelPrimaria) {
        this.nivelPrimaria = nivelPrimaria;
    }

    public Character getNivelSecundaria() {
        return nivelSecundaria;
    }

    public void setNivelSecundaria(Character nivelSecundaria) {
        this.nivelSecundaria = nivelSecundaria;
    }

    public Character getPrimerGrado() {
        return primerGrado;
    }

    public void setPrimerGrado(Character primerGrado) {
        this.primerGrado = primerGrado;
    }

    public Character getSegundoGrado() {
        return segundoGrado;
    }

    public void setSegundoGrado(Character segundoGrado) {
        this.segundoGrado = segundoGrado;
    }

    public Character getTercerGrado() {
        return tercerGrado;
    }

    public void setTercerGrado(Character tercerGrado) {
        this.tercerGrado = tercerGrado;
    }

    public Character getCuartoGrado() {
        return cuartoGrado;
    }

    public void setCuartoGrado(Character cuartoGrado) {
        this.cuartoGrado = cuartoGrado;
    }

    public Character getQuintoGrado() {
        return quintoGrado;
    }

    public void setQuintoGrado(Character quintoGrado) {
        this.quintoGrado = quintoGrado;
    }

    public Character getSextoGrado() {
        return sextoGrado;
    }

    public void setSextoGrado(Character sextoGrado) {
        this.sextoGrado = sextoGrado;
    }

    public Character getCaracteristicaDocente() {
        return caracteristicaDocente;
    }

    public void setCaracteristicaDocente(Character caracteristicaDocente) {
        this.caracteristicaDocente = caracteristicaDocente;
    }

    public Character getJornadaManana() {
        return jornadaManana;
    }

    public void setJornadaManana(Character jornadaManana) {
        this.jornadaManana = jornadaManana;
    }

    public Character getJornadaTarde() {
        return jornadaTarde;
    }

    public void setJornadaTarde(Character jornadaTarde) {
        this.jornadaTarde = jornadaTarde;
    }

    public Date getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(Date horaComienzo) {
        this.horaComienzo = horaComienzo;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public Character getTipoInfCett() {
        return tipoInfCett;
    }

    public void setTipoInfCett(Character tipoInfCett) {
        this.tipoInfCett = tipoInfCett;
    }

    public String getTipoInstruccion() {
        return tipoInstruccion;
    }

    public void setTipoInstruccion(String tipoInstruccion) {
        this.tipoInstruccion = tipoInstruccion;
    }

    public String getOtroTipoInst() {
        return otroTipoInst;
    }

    public void setOtroTipoInst(String otroTipoInst) {
        this.otroTipoInst = otroTipoInst;
    }

    public String getTipoAupicio() {
        return tipoAupicio;
    }

    public void setTipoAupicio(String tipoAupicio) {
        this.tipoAupicio = tipoAupicio;
    }

    public String getOtroTipoAuspicio() {
        return otroTipoAuspicio;
    }

    public void setOtroTipoAuspicio(String otroTipoAuspicio) {
        this.otroTipoAuspicio = otroTipoAuspicio;
    }

    public Character getCedValida() {
        return cedValida;
    }

    public void setCedValida(Character cedValida) {
        this.cedValida = cedValida;
    }

    public Long getCodigoInstcap() {
        return codigoInstcap;
    }

    public void setCodigoInstcap(Long codigoInstcap) {
        this.codigoInstcap = codigoInstcap;
    }

    public Character getCampo1() {
        return campo1;
    }

    public void setCampo1(Character campo1) {
        this.campo1 = campo1;
    }

    public Character getCampo2() {
        return campo2;
    }

    public void setCampo2(Character campo2) {
        this.campo2 = campo2;
    }

    public Character getCampo3() {
        return campo3;
    }

    public void setCampo3(Character campo3) {
        this.campo3 = campo3;
    }

    public Character getCampo4() {
        return campo4;
    }

    public void setCampo4(Character campo4) {
        this.campo4 = campo4;
    }

    public Character getCampo5() {
        return campo5;
    }

    public void setCampo5(Character campo5) {
        this.campo5 = campo5;
    }

    public Character getCampo6() {
        return campo6;
    }

    public void setCampo6(Character campo6) {
        this.campo6 = campo6;
    }

    public Character getCampo7() {
        return campo7;
    }

    public void setCampo7(Character campo7) {
        this.campo7 = campo7;
    }

    public Character getCampo8() {
        return campo8;
    }

    public void setCampo8(Character campo8) {
        this.campo8 = campo8;
    }

    public Character getCampo9() {
        return campo9;
    }

    public void setCampo9(Character campo9) {
        this.campo9 = campo9;
    }

    public Character getCampo10() {
        return campo10;
    }

    public void setCampo10(Character campo10) {
        this.campo10 = campo10;
    }

    public Character getCampo11() {
        return campo11;
    }

    public void setCampo11(Character campo11) {
        this.campo11 = campo11;
    }

    public Character getCampo12() {
        return campo12;
    }

    public void setCampo12(Character campo12) {
        this.campo12 = campo12;
    }

    public Character getCampo13() {
        return campo13;
    }

    public void setCampo13(Character campo13) {
        this.campo13 = campo13;
    }

    public Character getCampo14() {
        return campo14;
    }

    public void setCampo14(Character campo14) {
        this.campo14 = campo14;
    }

    public Character getCampo15() {
        return campo15;
    }

    public void setCampo15(Character campo15) {
        this.campo15 = campo15;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Character getModalidad() {
        return modalidad;
    }

    public void setModalidad(Character modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getInicioCecm() {
        return inicioCecm;
    }

    public void setInicioCecm(Integer inicioCecm) {
        this.inicioCecm = inicioCecm;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Date getFechaCertificacion() {
        return fechaCertificacion;
    }

    public void setFechaCertificacion(Date fechaCertificacion) {
        this.fechaCertificacion = fechaCertificacion;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public Character getMotivoRetiro() {
        return motivoRetiro;
    }

    public void setMotivoRetiro(Character motivoRetiro) {
        this.motivoRetiro = motivoRetiro;
    }

    public String getOtroMotivo() {
        return otroMotivo;
    }

    public void setOtroMotivo(String otroMotivo) {
        this.otroMotivo = otroMotivo;
    }

    public String getCodigoProvinciaTrabajo() {
        return codigoProvinciaTrabajo;
    }

    public void setCodigoProvinciaTrabajo(String codigoProvinciaTrabajo) {
        this.codigoProvinciaTrabajo = codigoProvinciaTrabajo;
    }

    public Date getFechaCreaCuenta() {
        return fechaCreaCuenta;
    }

    public void setFechaCreaCuenta(Date fechaCreaCuenta) {
        this.fechaCreaCuenta = fechaCreaCuenta;
    }

    public String getUsuarioCreaCuenta() {
        return usuarioCreaCuenta;
    }

    public void setUsuarioCreaCuenta(String usuarioCreaCuenta) {
        this.usuarioCreaCuenta = usuarioCreaCuenta;
    }

    public Character getEstadoCreaCuenta() {
        return estadoCreaCuenta;
    }

    public void setEstadoCreaCuenta(Character estadoCreaCuenta) {
        this.estadoCreaCuenta = estadoCreaCuenta;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(String antivirus) {
        this.antivirus = antivirus;
    }

    public Character getWireless() {
        return wireless;
    }

    public void setWireless(Character wireless) {
        this.wireless = wireless;
    }

    public String getObservacionesUi() {
        return observacionesUi;
    }

    public void setObservacionesUi(String observacionesUi) {
        this.observacionesUi = observacionesUi;
    }

    public Character getValidaCreaCuenta() {
        return validaCreaCuenta;
    }

    public void setValidaCreaCuenta(Character validaCreaCuenta) {
        this.validaCreaCuenta = validaCreaCuenta;
    }

    public Integer getAnioIngresoTrabajo() {
        return anioIngresoTrabajo;
    }

    public void setAnioIngresoTrabajo(Integer anioIngresoTrabajo) {
        this.anioIngresoTrabajo = anioIngresoTrabajo;
    }

    public Long getSectorTrabajo() {
        return sectorTrabajo;
    }

    public void setSectorTrabajo(Long sectorTrabajo) {
        this.sectorTrabajo = sectorTrabajo;
    }

    public String getApelativoEmpleador() {
        return apelativoEmpleador;
    }

    public void setApelativoEmpleador(String apelativoEmpleador) {
        this.apelativoEmpleador = apelativoEmpleador;
    }

    public String getApellidoEmpleador() {
        return apellidoEmpleador;
    }

    public void setApellidoEmpleador(String apellidoEmpleador) {
        this.apellidoEmpleador = apellidoEmpleador;
    }

    public String getNombreEmpleador() {
        return nombreEmpleador;
    }

    public void setNombreEmpleador(String nombreEmpleador) {
        this.nombreEmpleador = nombreEmpleador;
    }

    public String getCargoEmpleador() {
        return cargoEmpleador;
    }

    public void setCargoEmpleador(String cargoEmpleador) {
        this.cargoEmpleador = cargoEmpleador;
    }

    public String getEmailEmpleador() {
        return emailEmpleador;
    }

    public void setEmailEmpleador(String emailEmpleador) {
        this.emailEmpleador = emailEmpleador;
    }

    public String getTelefonoEmpleador() {
        return telefonoEmpleador;
    }

    public void setTelefonoEmpleador(String telefonoEmpleador) {
        this.telefonoEmpleador = telefonoEmpleador;
    }

    public Character getImpresionCarnet() {
        return impresionCarnet;
    }

    public void setImpresionCarnet(Character impresionCarnet) {
        this.impresionCarnet = impresionCarnet;
    }

    public Character getEstadoEstudiante() {
        return estadoEstudiante;
    }

    public void setEstadoEstudiante(Character estadoEstudiante) {
        this.estadoEstudiante = estadoEstudiante;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Apelativo getApelativo() {
        return apelativo;
    }

    public void setApelativo(Apelativo apelativo) {
        this.apelativo = apelativo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudiantePK != null ? estudiantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.estudiantePK == null && other.estudiantePK != null) || (this.estudiantePK != null && !this.estudiantePK.equals(other.estudiantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "estudiantePK=" + estudiantePK + ", codigoInst=" + codigoInst + ", codigoFuente=" + codigoFuente + 
                ", codigoEstado=" + codigoEstado + ", codigoTitulo=" + codigoTitulo + ", tipoCedPasEstudiante=" + tipoCedPasEstudiante +
                ", cedPasEstudiante=" + cedPasEstudiante + ", estadoCond=" + estadoCond + ", descCondicion=" + descCondicion + 
                ", codPaisAcademico=" + codPaisAcademico + ", codPaisOrigen=" + codPaisOrigen + ", codCiudadOrigen=" + codCiudadOrigen + 
                ", codigoProvincia=" + codigoProvincia + ", codigoCanton=" + codigoCanton + ", codigoParroquia=" + codigoParroquia +
                ", nomEstudiante=" + nomEstudiante + ", apellEstudiante=" + apellEstudiante + ", fechaNacimiento=" + fechaNacimiento +
                ", sexo=" + sexo + ", direccion=" + direccion + ", telefono=" + telefono + ", celular=" + celular + 
                ", email=" + email + ", emailsecEstudiante=" + emailsecEstudiante + ", obsEstudiante=" + obsEstudiante +
                ", estadoCivil=" + estadoCivil + ", trabajo=" + trabajo + ", cargo=" + cargo + ", direcTrab=" + direcTrab + 
                ", telfTrab=" + telfTrab + ", envioCorrespondencia=" + envioCorrespondencia + 
                ", casillapostal=" + casillapostal + ", casillapostalTrab=" + casillapostalTrab + 
                ", ciudadTrab=" + ciudadTrab + ", paisTrab=" + paisTrab + ", ciudadDom=" + ciudadDom + 
                ", paisDom=" + paisDom + ", tipoDir=" + tipoDir + ", fax=" + fax + ", websiteDir=" + websiteDir + 
                ", institucionDir=" + institucionDir + ", nivelDir=" + nivelDir + ", usuarioCrea=" + usuarioCrea +
                ", usuarioUltmodific=" + usuarioUltmodific + ", fechaCrea=" + fechaCrea + ", fechaUltmodific=" + 
                fechaUltmodific + ", codigoProfesor=" + codigoProfesor + ", tipoDato=" + tipoDato +
                ", telfContRapido=" + telfContRapido + ", paisContRapido=" + paisContRapido +
                ", ciudadContRapido=" + ciudadContRapido + ", direccionContRapido=" + direccionContRapido +
                ", tipoContRapido=" + tipoContRapido + ", imagen=" + imagen + ", envioInformacion=" + envioInformacion + 
                ", primeraLengua=" + primeraLengua + ", segundaLengua=" + segundaLengua +
                ", tipoVivienda=" + tipoVivienda + ", nivelmaxAprobado=" + nivelmaxAprobado + 
                ", tituloAlcanzado=" + tituloAlcanzado + ", experienciaDocente=" + experienciaDocente + 
                ", experienciaCapacitacion=" + experienciaCapacitacion + ", finicioCecm=" + finicioCecm +
                ", fterminoCecm=" + fterminoCecm + ", zonaEscuela=" + zonaEscuela + 
                ", croquisEscuela=" + croquisEscuela + ", codigoMecMed=" + codigoMecMed + ", numeroMec=" + numeroMec + 
                ", tamanoEscuela=" + tamanoEscuela + ", nivelInicial=" + nivelInicial + ", nivelPrimaria=" + nivelPrimaria + 
                ", nivelSecundaria=" + nivelSecundaria + ", primerGrado=" + primerGrado + ", segundoGrado=" + segundoGrado + 
                ", tercerGrado=" + tercerGrado + ", cuartoGrado=" + cuartoGrado + ", quintoGrado=" + quintoGrado + 
                ", sextoGrado=" + sextoGrado + ", caracteristicaDocente=" + caracteristicaDocente + 
                ", jornadaManana=" + jornadaManana + ", jornadaTarde=" + jornadaTarde + ", horaComienzo=" + horaComienzo + 
                ", horaTermino=" + horaTermino + ", tipoInfCett=" + tipoInfCett + ", tipoInstruccion=" + tipoInstruccion + 
                ", otroTipoInst=" + otroTipoInst + ", tipoAupicio=" + tipoAupicio + ", otroTipoAuspicio=" + otroTipoAuspicio +
                ", cedValida=" + cedValida + ", codigoInstcap=" + codigoInstcap + ", campo1=" + campo1 + ", campo2=" + campo2 + 
                ", campo3=" + campo3 + ", campo4=" + campo4 + ", campo5=" + campo5 + ", campo6=" + campo6 + ", campo7=" + campo7 +
                ", campo8=" + campo8 + ", campo9=" + campo9 + ", campo10=" + campo10 + ", campo11=" + campo11 + 
                ", campo12=" + campo12 + ", campo13=" + campo13 + ", campo14=" + campo14 + ", campo15=" + campo15 + 
                ", clave=" + clave + ", modalidad=" + modalidad + ", inicioCecm=" + inicioCecm + 
                ", fechaFinalizacion=" + fechaFinalizacion + ", fechaCertificacion=" + fechaCertificacion + 
                ", fechaRetiro=" + fechaRetiro + ", motivoRetiro=" + motivoRetiro + ", otroMotivo=" + otroMotivo +
                ", codigoProvinciaTrabajo=" + codigoProvinciaTrabajo + ", fechaCreaCuenta=" + fechaCreaCuenta +
                ", usuarioCreaCuenta=" + usuarioCreaCuenta + ", estadoCreaCuenta=" + estadoCreaCuenta + 
                ", nombreEquipo=" + nombreEquipo + ", antivirus=" + antivirus + ", wireless=" + wireless + 
                ", observacionesUi=" + observacionesUi + ", validaCreaCuenta=" + validaCreaCuenta +
                ", anioIngresoTrabajo=" + anioIngresoTrabajo + ", sectorTrabajo=" + sectorTrabajo + 
                ", apelativoEmpleador=" + apelativoEmpleador + ", apellidoEmpleador=" + apellidoEmpleador + 
                ", nombreEmpleador=" + nombreEmpleador + ", cargoEmpleador=" + cargoEmpleador + 
                ", emailEmpleador=" + emailEmpleador + ", telefonoEmpleador=" + telefonoEmpleador + 
                ", impresionCarnet=" + impresionCarnet + ", estadoEstudiante=" + estadoEstudiante + 
                ", nacionalidad=" + nacionalidad + ", estudianteCollection=" + estudianteCollection +
                ", estudiante=" + estudiante + ", apelativo=" + apelativo + '}';
    }


    
}
