package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.ArbolEscalafonarioResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonArbol;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuestoActual;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuntajePrevio;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.InformacionActualEscalafonService;

//@CrossOrigin({ "http://localhost:4200" })
@RestController
@RequestMapping("/informacionActualEscalafon")
public class InformacionActualEscalafonController {
	
	@Autowired
	private InformacionActualEscalafonService service;


	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonPuntajePrevio/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonPuntajePrevio>> 
	escalafonPuntajePrevio(@PathVariable("claveServidorPublico") String claveServidorPublico,
			@RequestParam  String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonPuntajePrevio(claveServidorPublico,idProcesoVigente));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonPuestoActual/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonPuestoActual>> 
	escalafonPuestoActual(@PathVariable("claveServidorPublico") String claveServidorPublico) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonPuestoActual(claveServidorPublico));
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonArbol/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<ArbolEscalafonarioResponse>> 
	escalafonArbol(@PathVariable("claveServidorPublico") String claveServidorPublico) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonArbol(claveServidorPublico));
	}

}
