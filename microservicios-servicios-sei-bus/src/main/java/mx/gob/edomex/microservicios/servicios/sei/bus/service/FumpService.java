
package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.Date;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.FumpModel;

/**
 * Interface de definicion de procesos de FUMP.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 20/10/2021.
 */
public interface FumpService {
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    /**
     * Obtener listado de FUMPs a traves de un rango de fechas y de una clave de
     * servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/10/2021.
     * @param claveServidorPublico Clave de servidor publico a buscar.
     * @param fechaInicio Fecha de inicio para filtro de informacion
     * @param fechaTermino Fecha de termino para filtro de informacion
     * @return Lista de FUMPs coincidentes con los parametros de busqueda.
     * @throws BusException de ejecucion de BUS.
     */
    List<FumpModel> obtenerFumpPorFechaUsuario(String claveServidorPublico, 
            Date fechaInicio, Date fechaTermino) throws BusException;
    //</editor-fold>
}
