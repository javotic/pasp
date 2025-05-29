package mx.gob.edomex.microservicios.hnomina.service;

import java.util.List;
import mx.gob.edomex.microservicios.hnomina.dto.PuntualidadAsistenciaDTO;
import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;

/**
 *
 * @author smartinez
 */
public interface PagosService {
    List<PuntualidadAsistenciaDTO> consultaDatosPuntyAsist(String claveServidor, String tpDato, String fecha1, String fecha2) throws BusException;
}
