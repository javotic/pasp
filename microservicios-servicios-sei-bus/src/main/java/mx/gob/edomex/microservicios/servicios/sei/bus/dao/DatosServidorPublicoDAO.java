package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

public interface DatosServidorPublicoDAO extends JpaRepository<DatosServidorPublico, String> {

    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
    @Query(value = "SELECT * FROM  dbo.CONSULTA_DATOS_PERSONALES(?)", nativeQuery = true)
    List<Object[]> consultarDatosServidorPublico(String IdServidorPublico);

    @Query(value = "SELECT * FROM  dbo.CONSULTACATESTADO(?,?)", nativeQuery = true)
    List<Object[]> consultarEstados(String idPais, String idEstado);

    @Query(value = "SELECT * FROM  dbo.CONSULTACATMUNICIPIO(?,?,?)", nativeQuery = true)
    List<Object[]> consultarMunicipios(String idPais, String idEstado, String idMunicipio);

    @Query(value = "SELECT * FROM  dbo.CONSULTACATCOLONIAS(?,?,?,?)", nativeQuery = true)
    List<Object[]> consultarColonias(String idPais, String idEstado, String idMunicipio, String idColonia);

    @Query(value = "SELECT * FROM  dbo.CONSULTACATTITULOCARRERA(?)", nativeQuery = true)
    List<Object[]> consultarNivelEstudios(String idNivelEstudios);

    @Query(value = "SELECT * FROM  dbo.CONSULTACATGENERO()", nativeQuery = true)
    List<Object[]> consultarGenero();

    @Query(value = "SELECT * FROM  dbo.CONSULTACATESTADOCIVIL()", nativeQuery = true)
    List<Object[]> consultarEstadoCivil();

    /**
     * Obtener datos personales de un servidor publico determinado a traves de
     * su clave de servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 23/01/2022.
     * @param claveServidor Clave de servidor publico del que se realizara la
     * busqueda de informacion.
     * @return Lista de objetos con la informacion obtenida; se obtienen por lo
     * menos 2 elementos, uno con domicilio personal, y otro con domicilio
     * fiscal.
     */
    @Query(value = "SELECT IDORDINAL, CLAVESERVIDORPUBLICO, NOMBRE, PRIMERAPELLIDO, "
            + "SEGUNDOAPELLIDO, IDSEXO, GENERO, CURP, RFC, FECHANACIMIENTO, TELEFONO, "
            + "CORREOELECTRONICO, TIPO_ID_DIRECCION, TIPO_DIRECCION, CALLE, "
            + "ESTADO, NM_ESTADO, MUNICIPIO, NM_MUNICIPIO, COLONIA, NM_COLONIA, "
            + "NUMEXTERIOR, NUMINTERIOR, CODIGOPOSTAL, NOMBREPLAZA, "
            + "IDNIVELESTUDIO, IDESTADOCIVIL, IDPLAZA, ISSEMYN, ORDEN"
            + " FROM dbo.CONSULTA_DATOS_PERSONALES_FISCAL(:claveServidor)", nativeQuery = true)
    List<Object[]> obtenerDatosPersonalesPorClaveSP(
            @Param("claveServidor") String claveServidor
    );
    //</editor-fold>

    //<editor-fold desc="Funciones UPDATE" defaultstate="collapsed">
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DECLARE @RESPUESTA VARCHAR(100) \n "
            + "EXEC ACTUALIZA_DATOS_PERSONALES ?,?,?,?,?,?,?,?,?,?,?,?,?, @RESPUESTA OUTPUT \n", nativeQuery = true)
    public int updDatosPersonales(String CLAVESERVIDORPUBLICO, String IDDATOSPERSONALES, String TELEFONO, String CORREOELECTRONICO,
            String IDESTADO, String IDMUNICIPIO, String IDCOLONIA, String CALLE, String NUMEXTERIOR, String NUMINTERIOR,
            String CODIGOPOSTAL, String IDNIVELESTUDIOS, String IDESTADOCIVIL);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DECLARE @RESPUESTA VARCHAR(100) \n "
            + "EXEC EDITAR_AGREGAR_DATOS_PROFESIONALES ?,?,?,?,?,?,?,?,?,?,?, @RESPUESTA OUTPUT \n", nativeQuery = true)
    public Object[] gestionarDatosProfesionales(String IDDATOPROFESIONAL, String IDSERVIDORPUBLICO, String IDNIVELESTUDIOS, String FECHAEMISIONCERTIFICADO,
            String FECHAVIGENCIACERTIFICADO, String NOMBRECERTIFICADO, String IDTIPOCERTIFICADO, String NOCERTIFICADO,
            String IDEMISORCERTIFICADO, String DURACION, String TIPODURACION);

    
    /**
     * Actualizar datos personales de un servidor publico determinado de acuerdo
     * con sus datos personales, domicilio personal y domicilio fiscal.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 24/01/2022.
     * @param claveServidor Clave de servidor publico.
     * @param idDatosPersonales ID de datos personales.
     * @param idDatosFiscales ID de datos fiscales.
     * @param telefono Telefono de servidor publico.
     * @param mail Correo electronico de servidor publico
     * @param idNivelEstudios ID de nivel de etudios de servidor publico.
     * @param idEstadoCivil ID de estado civil de servidor publico.
     * @param idTipoDireccionPersonal ID de tipo de direccion personal.
     * @param idEstadoPersonal ID de estado de direccion personal.
     * @param idMunicipioPersonal ID de municipio de direccion personal.
     * @param idColoniaPersonal ID de colonia de direccion personal.
     * @param callePersonal Calle de direccion personal.
     * @param numeroExteriorPersonal Numero exterior de direccion personal.
     * @param numeroInteriorPersonal Numero interior de direccion personal.
     * @param codigoPostalPersonal Codigo postal de direccion personal.
     * @param idTipoDireccionFiscal ID de tipo de direccion fiscal.
     * @param idEstadoFiscal ID de estado de direccion fiscal.
     * @param idMunicipioFiscal ID de municipio de direccion fiscal.
     * @param idColoniaFiscal ID de colonia de direccion fiscal.
     * @param calleFiscal Calle de direccion fiscal.
     * @param numeroExteriorFiscal Numero exterior de direccion fiscal.
     * @param numeroInteriorFiscal Numero interior de direccion fiscal.
     * @param codigoPostalFiscal Codigo postal de direccion fiscal.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
            value
            = "DECLARE @ACTUALIZA_DATOS_PERSONALES as DATOSPERSONALES_TABLE\n"
            + "DECLARE @RESPUESTA VARCHAR(10)\n"
            + "INSERT @ACTUALIZA_DATOS_PERSONALES (CLAVESERVIDORPUBLICO, IDDATOSPERSONALES, TELEFONO, CORREOELECTRONICO, TIPO_DIRECCION, IDESTADO, IDMUNICIPIO, IDCOLONIA, CALLE, NUMEXTERIOR, NUMINTERIOR, CODIGOPOSTAL, IDNIVELESTUDIOS, IDESTADOCIVIL)\n"
            + "VALUES	(:claveServidor,:idDatosPersonales,:telefono,:mail,:idTipoDireccionPersonal,:idEstadoPersonal,:idMunicipioPersonal,:idColoniaPersonal,:callePersonal,:numeroExteriorPersonal,:numeroInteriorPersonal,:codigoPostalPersonal,:idNivelEstudios,:idEstadoCivil),\n"
            + "		(:claveServidor,:idDatosFiscales,:telefono,:mail,:idTipoDireccionFiscal,:idEstadoFiscal,:idMunicipioFiscal,:idColoniaFiscal,:calleFiscal,:numeroExteriorFiscal,:numeroInteriorFiscal,:codigoPostalFiscal,:idNivelEstudios,:idEstadoCivil)\n"
            + "EXEC ACTUALIZA_DATOS_PERSONALES_FISCAL @ACTUALIZA_DATOS_PERSONALES, @RESPUESTA = @RESPUESTA OUTPUT; ",
            nativeQuery = true)
    public int actualizarDatosGenerales(
            @Param("claveServidor") String claveServidor,
            @Param("idDatosPersonales") String idDatosPersonales,
            @Param("idDatosFiscales") String idDatosFiscales,
            @Param("telefono") String telefono,
            @Param("mail") String mail,
            @Param("idNivelEstudios") String idNivelEstudios,
            @Param("idEstadoCivil") String idEstadoCivil,
            @Param("idTipoDireccionPersonal") int idTipoDireccionPersonal,
            @Param("idEstadoPersonal") String idEstadoPersonal,
            @Param("idMunicipioPersonal") String idMunicipioPersonal,
            @Param("idColoniaPersonal") String idColoniaPersonal,
            @Param("callePersonal") String callePersonal,
            @Param("numeroExteriorPersonal") String numeroExteriorPersonal,
            @Param("numeroInteriorPersonal") String numeroInteriorPersonal,
            @Param("codigoPostalPersonal") String codigoPostalPersonal,
            @Param("idTipoDireccionFiscal") String idTipoDireccionFiscal,
            @Param("idEstadoFiscal") String idEstadoFiscal,
            @Param("idMunicipioFiscal") String idMunicipioFiscal,
            @Param("idColoniaFiscal") String idColoniaFiscal,
            @Param("calleFiscal") String calleFiscal,
            @Param("numeroExteriorFiscal") String numeroExteriorFiscal,
            @Param("numeroInteriorFiscal") String numeroInteriorFiscal,
            @Param("codigoPostalFiscal") String codigoPostalFiscal
    );
    //</editor-fold>

    //<editor-fold desc="Funciones DELETE" defaultstate="collapsed">
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DECLARE @RESPUESTA VARCHAR(100) \n "
            + "EXEC ELIMINAR_DATOS_PROFESIONALES ?,?, @RESPUESTA OUTPUT \n", nativeQuery = true)
    public Object[] eliminarDatosProfesionales(String IDSERVIDORPUBLICO, String IDREGISTRODATOPROFESIONAL);
    //</editor-fold>

}
