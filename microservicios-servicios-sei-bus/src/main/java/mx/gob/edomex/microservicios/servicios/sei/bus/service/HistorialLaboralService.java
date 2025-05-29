package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudHistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudHistoricoLaboralModel;

/**
 *
 * @author smartinez
 */
public interface HistorialLaboralService {

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    List<SolicitudHistorialLaboralModel> getSolicitudesHistoricoLaboral(
            String idservidorpublico) throws BusException;

    /**
     * Obtener catalogo de motivos de solicitud por los que se puede requerir
     * una solicitud de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/11/2021.
     * @return Lista de motivos de solicitud de historial laboral.
     * @throws BusException de ejecucion de BUS.
     */
    List<Combo> obtenerCatalogoMotivosSolicitud() throws BusException;

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
     * @return Lista de solicitudes de historial laboral realizadas.
     * @throws BusException de ejecución de BUS.
     */
    List<SolicitudHistoricoLaboralModel> obtenerSolicitudes(String claveServidor,
            String nombreServidor, String claveServidorBusqueda,
            int banderaBusqueda) throws BusException;

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
    List<HistorialLaboralModel> obtenerHistorialLaboral(
            String claveServidor) throws BusException;

    /**
     * Insertar solicitud de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/11/2021.
     * @param claveServidor Clave del servidor publico que realiza la solicitud
     * @param idMotivo ID del motivio de solicitud.
     * @param comentarios Comentarios de solicitud.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    boolean insertarSolicitud(String claveServidor, int idMotivo,
            String comentarios) throws BusException;
    
    /**
     * Obtener sueldo integrado de historial laboral
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 24/11/2021.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @return Lista de historial laboral.
     * @throws BusException de ejecución de BUS.
     */
    HistorialLaboralModel obtenerSueldoIntegrado(String claveServidor) throws BusException;

    /**
     * Descargar documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 23/11/2021.
     * @param claveServidorPublico Clave del servidor publico del que se
     * descarga el historial laboral-
     * @throws BusException de ejecución de BUS.
     */
    byte[] generarDocumentoHistorialLaboral(String claveServidorPublico) throws BusException;
    //</editor-fold>
}
