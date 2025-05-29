package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ResultadosEddPreg;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ResultadosEDDService;

@RestController
@RequestMapping("/resultadosedd")
public class ResultadosEDDController {
	
	@Autowired
	private ResultadosEDDService resultadosEDDService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "idServidorPublico/{idServidorPublico}/idSeccion/{idSeccion}/idProceso/{idProceso}", 
	method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<ResultadosEddPreg> 
		consultarServidoreseddua(@PathVariable("idServidorPublico") String idServidorPublico, 
				@PathVariable("idSeccion") String idSeccion, 
				@PathVariable("idProceso") String idProceso) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
				resultadosEDDService.consultarResultados(idServidorPublico,idSeccion, idProceso));
	}

	
}
