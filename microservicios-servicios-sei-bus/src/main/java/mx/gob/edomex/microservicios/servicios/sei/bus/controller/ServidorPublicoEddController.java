package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoEddUa;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ServidorPublicoEddUaService;

@RestController
@RequestMapping("/servidoreseddua")
public class ServidorPublicoEddController {
	@Autowired
	private ServidorPublicoEddUaService servidorPublicoEddUaService;
	
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "IdServidorPublico/{IdServidorPublico}/IdUnidadAdministrativa/{IdUnidadAdministrativa}/IdProcesoVigente/{IdProcesoVigente}", 
	method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<DetalleEddeSP> 
		consultarServidoreseddua(@PathVariable("IdServidorPublico") String IdServidorPublico, 
				@PathVariable("IdUnidadAdministrativa") String IdUnidadAdministrativa, 
				@PathVariable("IdProcesoVigente") String IdProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				servidorPublicoEddUaService.consultarServidorPublicoEdd(IdServidorPublico,IdUnidadAdministrativa, IdProcesoVigente));
	}
}
