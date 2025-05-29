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
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Notificaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ResultadosEddPreg;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.NotificacionesService;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionesController {

	@Autowired
	private NotificacionesService notificacionesService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "idServidorPublico/{idServidorPublico}", 
	method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<Notificaciones>> 
		consultarServidoreseddua(@PathVariable("idServidorPublico") String idServidorPublico 
				) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).replace(" OK", ""), "OK",
				notificacionesService.consultarNotificaciones(idServidorPublico));
	}
}
