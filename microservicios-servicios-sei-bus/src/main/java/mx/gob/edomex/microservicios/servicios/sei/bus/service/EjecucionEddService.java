package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.CatSeccionesEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConsultarInstruccionesLlenadoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConsultarRespuestasCompetencias;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PreguntasEjecucion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SeccionesEvaluacion;

public interface EjecucionEddService {

	List<SeccionesEvaluacion> getSeccionesEvaluacion(String procesoVig, String evaluador, String auxiliar,
			String tipoResp, String idEvaluado) throws BusException;

	List<PreguntasEjecucion> getPreguntasEvaluacion(String procesoVig, String seccion) throws BusException;

	List<EjecucionEvaluacion> getEvaluacion(String procesoVig, String evaluador, String auxiliar, String tipoResp, String idEvaluado)
			throws BusException;

	List<ConsultarRespuestasCompetencias> getconsultarRespuestasCompetencias(String idProcesoActivo) throws BusException;        
        
        List<ConsultarInstruccionesLlenadoDTO> consultarInstruccionesllenadoEDD(String idProcesoActivo) throws BusException;
        
        List<CatSeccionesEvaluacion> getseccionesEjecucionEvaluacionDesempenio(String idProcesoActivo) throws BusException;
        
}
