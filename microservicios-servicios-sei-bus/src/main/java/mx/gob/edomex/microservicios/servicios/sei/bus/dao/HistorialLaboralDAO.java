/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.transaction.Transactional;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 *
 * @author smartinez
 */
@Component
public interface HistorialLaboralDAO extends JpaRepository<DatosServidorPublico, String> {

    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
    @Query(value = "SELECT * FROM dbo.CONSULTAR_SOLICITUDES_HISTORIAL_LABORAL(?)", nativeQuery = true)
    List<Object[]> getSolicitudesHistoricoLaboral(String idservidorpublico);

    /**
     * Obtener catalogo de motivos de solicitud por los que se puede requerir
     * una solicitud de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/11/2021.
     * @return Lista de motivos de solicitud de historial laboral.
     */
    @Query(value = "SELECT idMotivo, nmMotivo FROM SolicitudHistorialLaboralMotivos()", nativeQuery = true)
    public List<Object[]> obtenerCatalogoMotivosSolicitud();

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
     */
    @Query(value = "SELECT folio, idServidor, nServidor, fechaSolicitud, Motivo, Estatus"
            + " FROM SolicitudHistorialLaboralLista("
            + ":claveServidor, :nombreServidor, :claveServidorBusqueda, :banderaBusqueda"
            + ")", nativeQuery = true)
    public List<Object[]> obtenerSolicitudes(
            @Param("claveServidor") String claveServidor,
            @Param("nombreServidor") String nombreServidor,
            @Param("claveServidorBusqueda") String claveServidorBusqueda,
            @Param("banderaBusqueda") int banderaBusqueda);
    
    /**
     * Obtener listado de historial laboral
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/11/2021.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @return Lista de motivos de solicitud de historial laboral.
     */
    @Query(value = "SELECT "
            + "idServidor, nServidor, fechaInicio, fechaFin, Movimiento, Puesto, Secretaria "
            + "FROM SolicitudHistorialLaboralPdf(:claveServidor)", nativeQuery = true)
    public List<Object[]> obtenerHistorialLaboral(
            @Param("claveServidor") String claveServidor);
    
    /**
     * Obtener sueldo integrado de historial laboral
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 22/11/2021.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @return Lista de motivos de solicitud de historial laboral.
     */
    @Query(value = "SELECT "
            + "idServidor, nServidor, fechaInicio, fechaFin, Puesto, Sueldo "
            + "FROM SolicitudHistorialLaboralSueldo(:claveServidor)", nativeQuery = true)
    public List<Object[]> obtenerSueldoIntegrado(
            @Param("claveServidor") String claveServidor);
    //</editor-fold>
    
    //<editor-fold desc="Funciones INSERT" defaultstate="collapsed">
    /**
     * Insertar solicitud de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/11/2021.
     * @param claveServidor Clave del servidor publico que realiza la solicitud
     * @param idMotivo ID del motivio de solicitud.
     * @param comentarios Comentarios de solicitud.
     * @return Respuesta de ejecucion de procedimiento.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "EXEC SolicitudHistorialLaboral :claveServidor, :idMotivo, :comentarios", nativeQuery = true)
    public int insertarSolicitud(
            @Param("claveServidor") String claveServidor, 
            @Param("idMotivo") int idMotivo, 
            @Param("comentarios") String comentarios);
    //</editor-fold>
}
