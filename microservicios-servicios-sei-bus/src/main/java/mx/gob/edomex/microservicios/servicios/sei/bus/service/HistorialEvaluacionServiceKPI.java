package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EvaluacionKpiVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.IntruccionesKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.EvaluacionKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.PreguntasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.PreguntasRespuestasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.RespuestasPreguntasKpi;

public interface HistorialEvaluacionServiceKPI {
	
	List<HistorialEvaluacion> consultarHistorialEvaluacionKPI(String claveServidorPublico) throws BusException;
	
	List<EvaluacionKpiVigente> evaluacionKpiVigente(String idProcesoVigente) throws BusException;
	
	
	List<IntruccionesKpi> instruccionesKpi(String idProcesoKPI) throws BusException;
	
	List<EvaluacionKpi> evaluacionKpi(String claveServidorPublico, String idProcesoKPI) throws BusException;
	
	
	List<PreguntasKpi> preguntasKpi(String claveServidorPublico, String idProcesoKPI) throws BusException;
	
	
	List<RespuestasPreguntasKpi> respuestasPreguntasKpi(String claveServidorPublico, String idProcesoKPI) throws BusException;
	
	List<PreguntasRespuestasKpi> preguntasRespuestasKpi(String claveServidorPublico, String idProcesoKPI)  throws BusException;
	
	String envioRespuestasKpi(List<EnvioRespuestasKpi> respuestasKPI)  throws BusException;

}
