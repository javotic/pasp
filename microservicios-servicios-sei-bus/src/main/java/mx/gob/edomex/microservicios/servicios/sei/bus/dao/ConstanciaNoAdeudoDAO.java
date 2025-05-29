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
 * Objeto de acceso a datos de constancias de no adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 16/12/2021
 */
@Component
public interface ConstanciaNoAdeudoDAO extends JpaRepository<DatosServidorPublico, String> {

    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
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
     * @return Lista de motivos de solicitud de historial laboral.
     */
    @Query(value = "SELECT folio, idServidor, nServidor, fechaSolicitud, Estatus"
            + " FROM SolicitudConstanciaAdeudolLista("
            + ":claveServidor, :nombreServidor, :claveServidorBusqueda, :banderaBusqueda"
            + ")", nativeQuery = true)
    public List<Object[]> obtenerSolicitudes(
            @Param("claveServidor") String claveServidor,
            @Param("nombreServidor") String nombreServidor,
            @Param("claveServidorBusqueda") String claveServidorBusqueda,
            @Param("banderaBusqueda") int banderaBusqueda);

    /**
     * Obtener informacion para generacion de constancias de no adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 16/12/2021.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @param folio Folio solicitud de constancia de no adeudo.
     * @return Datos para genreacion de documento de constancia.
     */
    @Query(value = "select * from SolicitudConstanciaAdeudoPdf("
            + "+ :claveServidor, :folio"
            + ")", nativeQuery = true)
    public List<Object[]> obtenerInformacionConstancia(
            @Param("claveServidor") String claveServidor,
            @Param("folio") int folio);

    /**
     * Obtener datos de constancia de no adeudo de acuerdo con clave de servidor
     * publico y folio de solicitud.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 21/12/2021.
     * @param claveServidor Clave de servidor publico a buscar.
     * @param folio Folio de solicitud a buscar.
     * @return Lista de objetos con los resultados obtenidos.
     */
    @Query(value = "SELECT "
            + "idServidor, nServidor, adeudo, parrafo, parrafoNoAdeudo, "
            + "montoAdeudo, percepciones, deducciones, totPercepciones, "
            + "totDeducciones, adscripcion, ubicacion, categoria, dependencia, "
            + "corresponde, oficio, observaciones"
            + " FROM SolicitudConstanciaAdeudoPdf("
            + ":claveServidor, :folio"
            + ")", nativeQuery = true)
    public List<Object[]> obtenerDatosConstanciaNoAdeudo(
            @Param("claveServidor") String claveServidor,
            @Param("folio") int folio);

    /**
     * Obtener documento de constancia de no adeudo desde META para su descarga.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 10/01/2022.
     * @param idConsulta ID de consulta del documento que se obtendra
     * @return Lista de objetos con los resultados obtenidos.
     */
    @Query(value = "SELECT "
            + "cuts, contrasenia, documento"
            + " FROM documentoFirma("
            + ":idConsulta"
            + ")", nativeQuery = true)
    public List<Object[]> obtenerDocumentoConstanciaMeta(
            @Param("idConsulta") int idConsulta);
    //</editor-fold>
    
    //<editor-fold desc="Funciones INSERT" defaultstate="collapsed">
    /**
     * Insertar solicitud de constancia de no adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 16/12/2021.
     * @param claveServidor Clave del servidor publico que realiza la solicitud
     * @param comentarios Comentarios de solicitud.
     * @return Respuesta de ejecucion de procedimiento.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "EXEC SolicitudConstanciaAdeudo :claveServidor, :comentarios", nativeQuery = true)
    public int insertarSolicitud(
            @Param("claveServidor") String claveServidor,
            @Param("comentarios") String comentarios);
    //</editor-fold> 
}
