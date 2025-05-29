package mx.gob.edomex.microservicios.hnomina.service;

import java.util.List;

import mx.gob.edomex.microservicios.hnomina.dto.RecibosDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteListDTO;
import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;

/**
 *
 * @author smartinez
 */
public interface ComprobantesService {
    byte[] generarComprobantes(ServidorComprobanteListDTO spComprobante) throws BusException;
    List<RecibosDTO> consultarComprobantes(String claveServidor, String nombreServidor, String fechainicio, String fechafin, String idServidorSesion, String individual) throws BusException;
}
