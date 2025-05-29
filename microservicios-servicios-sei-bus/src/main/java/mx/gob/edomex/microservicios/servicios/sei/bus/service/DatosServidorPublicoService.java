package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonales;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonalesCatalogosDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TituloCarreraModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.MsgRespuesta;

public interface DatosServidorPublicoService {

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    DatosServidorPublico consultarDatosServidorPublico(String IdServidorPublico) throws BusException;

    DatosPersonales consultarDatosPersonales(String IdServidorPublico, String nombre, String apellidoPat,
            String apellidoMat) throws BusException;

    DatosPersonalesCatalogosDTO consultarDatosPersonalesConCatalogos(String IdServidorPublico) throws BusException;

    List<TituloCarreraModel> getTituloCarrera(String idTipoCarrera) throws BusException;

    List<MsgRespuesta> updDatosPersonales(String CLAVESERVIDORPUBLICO, String IDDATOSPERSONALES, String TELEFONO, String CORREOELECTRONICO,
            String IDESTADO, String IDMUNICIPIO, String IDCOLONIA, String CALLE, String NUMEXTERIOR, String NUMINTERIOR,
            String CODIGOPOSTAL, String IDNIVELESTUDIOS, String IDESTADOCIVIL) throws BusException;

    List<MsgRespuesta> gestionarDatosProfesionales(String IDDATOPROFESIONAL, String IDSERVIDORPUBLICO, String IDNIVELESTUDIOS, String FECHAEMISIONCERTIFICADO,
            String FECHAVIGENCIACERTIFICADO, String NOMBRECERTIFICADO, String IDTIPOCERTIFICADO, String NOCERTIFICADO,
            String IDEMISORCERTIFICADO, String DURACION, String TIPODURACION) throws BusException;

    Integer eliminarDatosProfesionales(String IDSERVIDORPUBLICO, String IDREGISTRODATOPROFESIONAL) throws BusException;

    /**
     * Obtener datos personales de un servidor publico determinado a traves de
     * su clave de servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 23/01/2022.
     * @param claveServidor Clave de servidor publico del que se realizara la
     * busqueda de informacion.
     * @return Datos de servidor publico obtenidos.
     * @throws BusException de procesamiento general.
     */
    ServidorPublicoModel obtenerDatosPersonalesPorClaveSP(String claveServidor) throws BusException;

    /**
     * Actualizar datos personales de un servidor publico determinado de acuerdo
     * con sus datos personales, domicilio personal y domicilio fiscal.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 24/01/2022.
     * @param claveServidor Clave de servidor publico.
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
     * @return Listado con mensajes de respuesta.
     * @throws BusException de procesamiento general.
     */
    List<MsgRespuesta> actualizarDatosGenerales(String claveServidor, String telefono,
            String mail, String idNivelEstudios, String idEstadoCivil, int idTipoDireccionPersonal,
            String idEstadoPersonal, String idMunicipioPersonal, String idColoniaPersonal,
            String callePersonal, String numeroExteriorPersonal, String numeroInteriorPersonal,
            String codigoPostalPersonal, String idTipoDireccionFiscal, String idEstadoFiscal,
            String idMunicipioFiscal, String idColoniaFiscal, String calleFiscal,
            String numeroExteriorFiscal, String numeroInteriorFiscal, String codigoPostalFiscal) throws BusException;
    //</editor-fold>

}
