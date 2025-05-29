package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ServidorPublicoIncrementoSalarialDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.CategoriaIncrementoSalarialModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TabuladorBurocrataModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TabuladorDocenteModel;

/**
 * Servicio de gestion del incremento salarial.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 19/07/2022.
 */
public interface IncrementoSalarialService {

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    /**
     * Obtener datos de servidor publico relacionado con el incremento salarial.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 19/07/2022.
     * @param claveServidor Clave del servidor publico del que se obtendra la
     * informacion.
     * @return Datos encontrados del servidor publico.
     * @throws BusException de ejecución de BUS.
     */
    ServidorPublicoIncrementoSalarialDTO obtenerServPubIncrSal(String claveServidor)
            throws BusException;

    /**
     * Obtener categorias de incremento salarial.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @return Lista con las diferentes categorias obtenidas.
     * @throws BusException de ejecución de BUS.
     */
    List<CategoriaIncrementoSalarialModel> obtenerCategoriasIncrSal() throws BusException;

    /**
     * Obtener tabulador docente de acuerdo a clave de servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @param claveServidorPublico Clave del servidor publico del que se
     * obtendra la informacion.
     * @return Lista con los datos del tabulador docente encontrado.
     * @throws BusException de ejecución de BUS.
     */
    List<TabuladorDocenteModel> obtenerTabuladorDocIncrSal(String claveServidorPublico)
            throws BusException;

    /**
     * Obtener tabulador burocrata de acuerdo a un codigo de puesto determinado.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @param codigoPuesto Codigo de puesto del que se obtendra el tabulador
     * burocrata.
     * @return Lista con los datos del tabulador burocrata encontrado.
     * @throws BusException de ejecución de BUS.
     */
    List<TabuladorBurocrataModel> obtenerTabuladorBurIncrSal(String codigoPuesto)
            throws BusException;
    //</editor-fold>
}
