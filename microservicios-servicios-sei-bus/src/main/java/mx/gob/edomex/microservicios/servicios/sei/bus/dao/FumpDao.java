package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 * Clase de acceso a datos para procesos de FUMP.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 21/10/2021.
 */
@Component
public class FumpDao {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @PersistenceContext
    EntityManager entityManager;
    //</editor-fold>
    
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    /**
     * Obtener listado de FUMPs a traves de un rango de fechas y de una clave de
     * servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/10/2021.
     * @param claveServidorPublico Clave de servidor publico a buscar.
     * @param fechaInicio Fecha de inicio para filtro de informacion
     * @param fechaTermino Fecha de termino para filtro de informacion
     * @return Lista de FUMPs coincidentes con los parametros de busqueda.
     */
    public List<Object[]> obtenerFumpPorFechaUsuario(String claveServidorPublico, 
            String fechaInicio, String fechaTermino) {
        String sql = 
                "SELECT "
                + " NUFOLIO, DSCLAVESP, DSUA, DSPLAZA, DSCCT, "
                + " DSTIPOMOVIMIENTO, FCFIRMA, DSNOMBREARCHIVO"
                + " FROM FNC_OBTENERFUMPBYUSUARIOFECHA('"
                + claveServidorPublico
                + "', '"
                + fechaInicio
                + "', '"
                + fechaTermino
                + "')";
        return entityManager.createNativeQuery(sql).getResultList();
    }
    //</editor-fold>
}
