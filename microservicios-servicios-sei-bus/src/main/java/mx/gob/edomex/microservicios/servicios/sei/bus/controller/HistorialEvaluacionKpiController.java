package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.dto.InscripcionDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EvaluacionKpiVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.IntruccionesKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.EvaluacionKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.PreguntasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.PreguntasRespuestasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.RespuestasPreguntasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.HistorialEvaluacionServiceKPI;


//@CrossOrigin({ "http://localhost:4200" })
@RestController
@RequestMapping("/historialEvaluacionKPI")
public class HistorialEvaluacionKpiController{
	
	@Autowired
	private HistorialEvaluacionServiceKPI service;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "historial/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<HistorialEvaluacion>> 
		consultarHistorialEvaluacionKPI(@PathVariable("claveServidorPublico") String claveServidorPublico) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.consultarHistorialEvaluacionKPI(claveServidorPublico));
	}
	
	

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "evaluacionKpiVigente/{idProcesoVigente}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EvaluacionKpiVigente>> 
	evaluacionKpiVigente(@PathVariable("idProcesoVigente") String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.evaluacionKpiVigente(idProcesoVigente));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "instruccionesKpi/{idProcesoKPI}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<IntruccionesKpi>> 
	instruccionesKpi(@PathVariable("idProcesoKPI") String idProcesoKPI) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.instruccionesKpi(idProcesoKPI));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "evaluacionKpi/{claveServidorPublico}/{idProcesoKPI}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EvaluacionKpi>> 
	evaluacionKpi(
			@PathVariable("claveServidorPublico") String claveServidorPublico,
			@PathVariable("idProcesoKPI") String idProcesoKPI) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.evaluacionKpi(claveServidorPublico,idProcesoKPI));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "preguntasKpi/{claveServidorPublico}/{idProcesoKPI}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<PreguntasKpi>> 
	preguntasKpi(
			@PathVariable("claveServidorPublico") String claveServidorPublico,
			@PathVariable("idProcesoKPI") String idProcesoKPI) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.preguntasKpi(claveServidorPublico,idProcesoKPI));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "respuestasPreguntasKpi/{claveServidorPublico}/{idProcesoKPI}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<RespuestasPreguntasKpi>> 
	respuestasPreguntasKpi(
			@PathVariable("claveServidorPublico") String claveServidorPublico,
			@PathVariable("idProcesoKPI") String idProcesoKPI) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.respuestasPreguntasKpi(claveServidorPublico,idProcesoKPI));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "preguntasRespuestasKpi/{claveServidorPublico}/{idProcesoKPI}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<PreguntasRespuestasKpi>> 
	preguntasRespuestasKpi(
			@PathVariable("claveServidorPublico") String claveServidorPublico,
			@PathVariable("idProcesoKPI") String idProcesoKPI) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.preguntasRespuestasKpi(claveServidorPublico,idProcesoKPI));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "envioRespuestasKpi", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<String> envioRespuestasKpi(@RequestBody List<EnvioRespuestasKpi> 
	respuestasKPn)
			throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.envioRespuestasKpi(respuestasKPn).toString());
	}
	
}
