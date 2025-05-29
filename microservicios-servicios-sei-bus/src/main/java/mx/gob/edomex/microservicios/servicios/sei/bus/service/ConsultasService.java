package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas.ConstanciaNoAdeudoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas.IncidenciasTiempoModel;

/**
 *
 * @author smartinez
 */
public interface ConsultasService {
    List<IncidenciasTiempoModel> getIncidenciasTiempo(String idServidorPublico, String fechaInicio, String fechaFin) throws  BusException;
    List<ConstanciaNoAdeudoModel> getHistoricoConstanciaNoAdeudo(String idServidorPublico) throws  BusException;
}
