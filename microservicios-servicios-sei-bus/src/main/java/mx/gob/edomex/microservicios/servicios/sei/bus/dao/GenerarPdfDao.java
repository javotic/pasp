package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 * Objeto de acceso a datos para generacion de PDF.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 17/12/2021.
 */
@Component
public interface GenerarPdfDao extends JpaRepository<DatosServidorPublico, String> {
    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
    /**
     * Obtener configuracion general de PDF.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @return Configuracion de historia laboral obtenida.
     */
    @Query(value = "SELECT frase, firma FROM SolicitudHistorialConfiguracion()", nativeQuery = true)
        public List<Object[]> obtenerConfiguracion();
    //</editor-fold>
}
