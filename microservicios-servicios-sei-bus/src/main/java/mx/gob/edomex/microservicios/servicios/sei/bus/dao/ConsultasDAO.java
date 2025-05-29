
package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author smartinez
 */
@Component
public interface ConsultasDAO extends JpaRepository<DatosServidorPublico, String>{
        
    @Query(value = "SELECT * FROM dbo.CARGAR_INCIDENCIAS_TIEMPO(?,?,?)", nativeQuery = true)
    List<Object[]> getIncidenciasTiempo(String idServidorPublico, String fechaInicio, String fechaFin);
      
    @Query(value = "SELECT * FROM dbo.CONSULTA_SOLICITUDES_CONSTANCIA_NOADEUDO(?)", nativeQuery = true)
    List<Object[]> getHistoricoConstanciaNoAdeudo(String idServidorPublico);
        
        
}
