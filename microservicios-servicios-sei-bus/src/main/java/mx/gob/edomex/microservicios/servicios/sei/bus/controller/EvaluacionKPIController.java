package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComisionesAsignaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionEvaluacionKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.InstruccionesKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ProcesoKPIVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.RespuestasEncuestaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ProcesoKPIService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/evaluacionkpi")
public class EvaluacionKPIController {
	@Autowired
	private ProcesoKPIService procesoKPIService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "procesoVigente/idServidorPublico/{idServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<ProcesoKPIVigente> consultarProcesoVigente(
			@PathVariable("idServidorPublico") String idServidorPublico) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
				procesoKPIService.consultarProcesoVigente(idServidorPublico));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "historicokpi/idServidorPublico/{idServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<HistorialKPI>> consultarHiscoricoKPI(
			@PathVariable("idServidorPublico") String idServidorPublico) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
				procesoKPIService.consultarHiscoricoKPI(idServidorPublico));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "instruccioneskpi/idProceso/{idProceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<InstruccionesKPI>> consultarInstruccionesKPI(@PathVariable("idProceso") String idProceso)
			throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
				procesoKPIService.consultarInstruccionesKPI(idProceso));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "ejecucionkpi/idServidorPublico/{idServidorPublico}/idProceso/{idProceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<EjecucionEvaluacionKPI> ejecucionEvaluacionKPI(
			@PathVariable("idServidorPublico") String idServidorPublico, @PathVariable("idProceso") String idProceso)
			throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
				procesoKPIService.ejecucionEvaluacionKPI(idServidorPublico, idProceso));
	}
        
        
        @ResponseStatus(HttpStatus.OK)
        @RequestMapping(value = "/envioRespuestasEncuesta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<?> envioRespuestasEncuesta(@RequestBody List<RespuestasEncuestaDTO> respuestasEncuesta) throws BusException {
		try {
			String status = procesoKPIService.envioRespuestasEncuesta(respuestasEncuesta);
			return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", status);
		} catch (Exception e) {
			return new BusResponse<>("error", String.valueOf(HttpStatus.BAD_REQUEST), "Fail",e);
		}

	}

}
