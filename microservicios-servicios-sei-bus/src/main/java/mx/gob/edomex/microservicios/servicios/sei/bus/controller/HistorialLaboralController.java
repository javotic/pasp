package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.SolicitudHistorialLaboralDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudHistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudHistoricoLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.FirmarDocumentoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.HistorialLaboralService;
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
 *
 * @author smartinez
 */
@RestController
@RequestMapping("/historialLaboral")
public class HistorialLaboralController {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String estatus;
    private String mensaje;
    public static final String REPOSITORIO = System.getProperty("user.home") + "/Downloads/HistorialLaboral";
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private HistorialLaboralService historialLaboralService;
    @Autowired
    private FirmarDocumentoService firmarDocumentoService;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "cargarSolicitudesHistoricoLaboral", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<SolicitudHistorialLaboralModel>> getSolicitudesHistoricoLaboral(@RequestParam("IDSERVIDORPUBLICO") String idservidorpublico) throws BusException {
        return new BusRespuesta<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "Succes",
                historialLaboralService.getSolicitudesHistoricoLaboral(idservidorpublico));
    }

    /**
     * Obtener catalogo de motivos de solicitud por los que se puede requerir
     * una solicitud de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/11/2021.
     * @return Lista de motivos de solicitud de historial laboral.
     * @throws BusException de ejecucion de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerCatalogoMotivosSolicitud",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Combo>> obtenerCatalogoMotivosSolicitud() throws BusException {
        List<Combo> lsMotivos = new ArrayList<>();
        try {
            lsMotivos = this.historialLaboralService.obtenerCatalogoMotivosSolicitud();
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
                lsMotivos
        );
    }

    /**
     * Obtener listado de solicitudes de historicos laborales realizados.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/11/2021.
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
    public BusRespuesta<List<SolicitudHistoricoLaboralModel>> obtenerSolicitudes(
            @RequestParam("claveServidor") String claveServidor,
            @RequestParam("nombreServidor") String nombreServidor,
            @RequestParam("claveServidorBusqueda") String claveServidorBusqueda,
            @RequestParam("banderaBusqueda") int banderaBusqueda
    ) throws BusException {
        List<SolicitudHistoricoLaboralModel> lsSolicitudes = new ArrayList<>();
        try {
            lsSolicitudes = this.historialLaboralService.obtenerSolicitudes(
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
     * Obtener listado de historial laboral
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/11/2021.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @return Lista de historial laboral.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerHistorialLaboral",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<HistorialLaboralModel>> obtenerHistorialLaboral(
            @RequestParam("claveServidor") String claveServidor
    ) throws BusException {
        List<HistorialLaboralModel> lsHistorialLaboral = new ArrayList<>();
        try {
            lsHistorialLaboral = this.historialLaboralService.
                    obtenerHistorialLaboral(claveServidor);

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
                lsHistorialLaboral
        );
    }
    //</editor-fold>

    //<editor-fold desc="Funciones POST" defaultstate="collapsed">
    /**
     * Insertar solicitud de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/11/2021.
     * @param solicitudHistorialLaboralDTO Objeto de acceso a datos con la
     * claveServidorPublico, idMotivo y comentarios necesarios para la insercion
     * de una nueva solicitud.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "insertarSolicitud",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<String> insertarSolicitud(
            @RequestBody SolicitudHistorialLaboralDTO solicitudHistorialLaboralDTO
    ) throws BusException {
        String respuesta = "";
        try {
            if (this.historialLaboralService.
                    insertarSolicitud(
                            solicitudHistorialLaboralDTO.getClaveServidorPublico(),
                            solicitudHistorialLaboralDTO.getIdMotivo(),
                            solicitudHistorialLaboralDTO.getComentarios()
                    )) {
                respuesta = "Solicitud de historial laboral insertada correctamente.";
            } else {
                respuesta = "La solicitud de historial laboral no ha podido procesarse, favor de contactar al área de soporte.";
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
     * Descargar solicitud de historial laboral firmado.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 12/11/2022.
     * @param claveServidor Clave de servidor publico a buscar.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "descargarSolicitud")
    public ResponseEntity<byte[]> descargarSolicitud(
            @RequestParam("claveServidor") String claveServidor) throws BusException {
        byte[] documento = this.historialLaboralService.
                generarDocumentoHistorialLaboral(
                        claveServidor
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
     * Descargar solicitud de historial laboral sin mecanismo de firma.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 14/01/2022.
     * @param claveServidor Clave de servidor publico a buscar.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "descargarSolicitudSinFirma")
    public ResponseEntity<byte[]> descargarSolicitudSinFirma(
            @RequestParam("claveServidor") String claveServidor) throws BusException {
        byte[] documento = this.historialLaboralService.
                generarDocumentoHistorialLaboral(
                        claveServidor
                );
        
        return new ResponseEntity<>(documento, HttpStatus.OK);
    }
    //</editor-fold>
}
