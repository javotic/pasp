package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudConstanciaNoAdeudoModel;

/**
 * Interfaz de servicio de constancias de no adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 16/12/2021.
 */
public interface ConstanciaNoAdeudoService {
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    /**
     * Obtener listado de solicitudes de constancias de no adeudo realizadas.
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
     * @return Lista de solicitudes de historial laboral realizadas.
     * @throws BusException de ejecución de BUS.
     */
    List<SolicitudConstanciaNoAdeudoModel> obtenerSolicitudes(String claveServidor,
            String nombreServidor, String claveServidorBusqueda,
            int banderaBusqueda) throws BusException;
    
    /**
     * Insertar solicitud de constancia de no adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 16/12/2021.
     * @param claveServidor Clave del servidor publico que realiza la solicitud
     * @param comentarios Comentarios de solicitud.
     * @return Respuesta de ejecucion de procedimiento.
     * @throws BusException de ejecución de BUS.
     */
    boolean insertarSolicitud(String claveServidor, String comentarios) throws BusException;
    
    /**
     * Descargar documento de constancia.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 16/12/2021.
     * @param claveServidorPublico Clave del servidor publico del que se
     * generara el documento.
     * @param folio Folio de solicitud de la que se descargara la constancia.
     * @return Documento de constancia solicitado.
     * @throws BusException de ejecución de BUS.
     */
    byte[] generarDocumento(String claveServidorPublico, 
            int folio) throws BusException;
    
    /**
     * Descargar documento de constancia generado desde META.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 10/01/2022.
     * @param idConsulta ID de consulta del que se obtendra el documento.
     * @return Documento de constancia solicitado.
     * @throws BusException de ejecución de BUS.
     */
    byte[] obtenerDocumentoMeta(int idConsulta) throws BusException;
    //</editor-fold>
}
