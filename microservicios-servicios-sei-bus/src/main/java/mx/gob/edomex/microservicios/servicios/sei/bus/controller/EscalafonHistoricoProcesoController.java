package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonHistoricoProceso;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonHistoricoProcesoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EscalafonHistoricoProcesoService;

//@CrossOrigin({ "http://localhost:4200" })
@RestController
@RequestMapping("/miHistorico")
public class EscalafonHistoricoProcesoController {
	
	@Autowired
	private EscalafonHistoricoProcesoService service;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonHistoricoProcesos/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusRespuesta<List<EscalafonHistoricoProcesoDTO>> 
	escalafonHistoricoProcesos(@PathVariable("claveServidorPublico") String claveServidorPublico) throws BusException {
		return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
				service.escalafonHistoricoProcesos(claveServidorPublico));
	}
	
}
