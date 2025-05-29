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
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ComisionesAsignacionesService;

@RestController
@RequestMapping("/comisiones")
public class ComisionesController {

	@Autowired
	private ComisionesAsignacionesService comisionesAsignacionesService;

        /***
         * Consulta datos
         * @param idServidorPublico
         * @param idProceso
         * @return
         * @throws BusException 
         */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "idServidorPublico/{idServidorPublico}/idProceso/{idProceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<ComisionesAsignaciones>> consultarServidoreseddua(
			@PathVariable("idServidorPublico") String idServidorPublico,

			@PathVariable("idProceso") String idProceso) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).replace("OK", ""), "OK",
				comisionesAsignacionesService.consultarComisiones(idServidorPublico, idProceso));
	}

}
