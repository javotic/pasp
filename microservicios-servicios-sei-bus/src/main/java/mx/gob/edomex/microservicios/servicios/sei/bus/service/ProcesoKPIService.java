package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionEvaluacionKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.InstruccionesKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ProcesoKPIVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.RespuestasEncuestaDTO;

public interface ProcesoKPIService {

	ProcesoKPIVigente consultarProcesoVigente(String IdServidorPublico) throws BusException;

	List<HistorialKPI> consultarHiscoricoKPI(String IdServidorPublico) throws BusException;

	List<InstruccionesKPI> consultarInstruccionesKPI(String IdProcesoVigente) throws BusException;

	EjecucionEvaluacionKPI ejecucionEvaluacionKPI(String IdServidorPublico, String IdProcesoVigente)
			throws BusException;

        String envioRespuestasEncuesta(List<RespuestasEncuestaDTO> respuestasEncuesta) throws BusException;
}
