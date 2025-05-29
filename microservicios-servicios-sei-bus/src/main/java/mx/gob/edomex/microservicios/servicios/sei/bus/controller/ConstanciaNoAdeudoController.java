package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.SolicitudConstanciaNoAdeudoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudConstanciaNoAdeudoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ConstanciaNoAdeudoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.FirmarDocumentoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de peticiones para constancias de no adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 15/12/2021.
 */
@RestController
@RequestMapping("/constanciaNoAdeudo")
public class ConstanciaNoAdeudoController {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String estatus;
    private String mensaje;

    //Definir ruta de repositorio
    public static final String REPOSITORIO = System.getProperty("user.home") + "/Downloads/cna";
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private ConstanciaNoAdeudoService constanciaNoAdeudoService;
    @Autowired
    private FirmarDocumentoService firmarDocumentoService;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    /**
     * Obtener listado de solicitudes de constancia de no adeudo realizadas.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 16/12/2021.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @param nombreServidor Nombre del servidor publico del que se obtendran
     * los resultados.
     * @param claveServidorBusqueda Clave del servidor publico que realiza la
     * busqueda
     * @param banderaBusqueda Bandera indicando el tipo de busqueda a realizar;
     * 1 para busqueda de perfil SERVIDOR_PUBLICO y 2 para busqueda de perfil
     * COORDINADOR.
     * @return Lista de motivos de solicitud de historial laboral.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerSolicitudes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<SolicitudConstanciaNoAdeudoModel>> obtenerSolicitudes(
            @RequestParam("claveServidor") String claveServidor,
            @RequestParam("nombreServidor") String nombreServidor,
            @RequestParam("claveServidorBusqueda") String claveServidorBusqueda,
            @RequestParam("banderaBusqueda") int banderaBusqueda
    ) throws BusException {
        List<SolicitudConstanciaNoAdeudoModel> lsSolicitudes = new ArrayList<>();
        try {
            lsSolicitudes = this.constanciaNoAdeudoService.obtenerSolicitudes(
                    claveServidor, nombreServidor, claveServidorBusqueda,
                    banderaBusqueda
            );

            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                lsSolicitudes
        );
    }
    
    /**
     * Descargar documento de constancia de no adeudo desde META
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 10/01/2022.
     * @param idConsulta ID de consulta para descarga de documento.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "descargarDocumentoMeta", 
            method = RequestMethod.GET)
    public ResponseEntity<byte[]> descargarDocumentoMeta(
            @RequestParam("idConsulta") int idConsulta) throws BusException {
        byte[] documento = this.constanciaNoAdeudoService.obtenerDocumentoMeta(
                idConsulta
        );

        byte[] documentoFirmado = documento;
        try {
            documentoFirmado = this.firmarDocumentoService.firmarDocumento(
                    Constantes.CUTS_USUARIO,
                    Constantes.CUTS_CONTRASENIA,
                    documento
            );
        } catch (Exception e) {

        }
        return new ResponseEntity<>(documentoFirmado, HttpStatus.OK);
    }
    //</editor-fold>

    //<editor-fold desc="Funciones POST" defaultstate="collapsed">
    /**
     * Insertar solicitud de constancia de no adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/11/2021.
     * @param solicitudConstanciaNoAdeudoDTO Objeto de acceso a datos con la
     * claveServidorPublico y comentarios necesarios para la insercion de una
     * nueva solicitud.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "insertarSolicitud",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<String> insertarSolicitud(
            @RequestBody SolicitudConstanciaNoAdeudoDTO solicitudConstanciaNoAdeudoDTO
    ) throws BusException {
        String respuesta = "";
        try {
            if (this.constanciaNoAdeudoService.
                    insertarSolicitud(
                            solicitudConstanciaNoAdeudoDTO.getClaveServidorPublico(),
                            solicitudConstanciaNoAdeudoDTO.getComentarios()
                    )) {
                respuesta = "Solicitud de constancia de no adeudo insertada correctamente.";
            } else {
                respuesta = "La solicitud de constancia de no adeudo no ha podido procesarse, favor de contactar al área de soporte.";
            }
            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;

        } catch (Exception e) {
            return new BusRespuesta<>(
                    Constantes.STATUS_FAILURE,
                    String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR).split(" ")[0],
                    e.getMessage(),
                    Constantes.MESSAGE_ERROR
            );
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                respuesta
        );
    }

    /**
     * Descargar solicitud de constancia de no adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 21/12/2021.
     * @param claveServidor Clave de servidor publico a buscar.
     * @param folio Folio de solicitud de no adeudo a buscar.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "descargarSolicitud")
    public ResponseEntity<byte[]> descargarSolicitud(
            @RequestParam("claveServidor") String claveServidor,
            @RequestParam("folio") int folio) throws BusException {
        byte[] documento = this.constanciaNoAdeudoService.generarDocumento(
                claveServidor,
                folio
        );

        byte[] documentoFirmado = documento;
        try {
            documentoFirmado = this.firmarDocumentoService.firmarDocumento(
                    Constantes.CUTS_USUARIO,
                    Constantes.CUTS_CONTRASENIA,
                    documento
            );
        } catch (Exception e) {

        }
        return new ResponseEntity<>(documentoFirmado, HttpStatus.OK);
    }
    
     /**
     * Descargar solicitud de constancia de no adeudo sin mecanismo de firma.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 14/01/2022.
     * @param claveServidor Clave de servidor publico a buscar.
     * @param folio Folio de solicitud de no adeudo a buscar.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "descargarSolicitudSinFirma")
    public ResponseEntity<byte[]> descargarSolicitudSinFirma(
            @RequestParam("claveServidor") String claveServidor,
            @RequestParam("folio") int folio) throws BusException {
        byte[] documento = this.constanciaNoAdeudoService.generarDocumento(
                claveServidor,
                folio
        );

        return new ResponseEntity<>(documento, HttpStatus.OK);
    }
    //</editor-fold>
}
