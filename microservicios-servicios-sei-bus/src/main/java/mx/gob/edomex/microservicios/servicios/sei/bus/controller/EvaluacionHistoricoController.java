/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.controller;


import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComisionesAsignaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEDD;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EvaluacionHistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author smartinez
 */

@RestController
@RequestMapping("/evaluacionhistorico")
public class EvaluacionHistoricoController{
    	@Autowired
	private EvaluacionHistoricoService evaluacionHistoricoService;
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "getDetalleUnidades/idServidorPublico/{idServidorPublico}/idProceso/{idProceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<DetalleEDD>> getDetalleUnidades(
			@PathVariable("idServidorPublico") String idServidorPublico,
			@PathVariable("idProceso") String idProceso) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).replace("OK", ""), "OK",
				evaluacionHistoricoService.getDetalleUnidades(idServidorPublico, idProceso));
	}
        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "getServidoresedduaHt/{IdServidorPublico}/{IdUnidadAdministrativa}/{IdProcesoVigente}", 
	method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<DetalleEddeSP> 
		getServidoresedduaHt(@PathVariable("IdServidorPublico") String IdServidorPublico, 
				@PathVariable("IdUnidadAdministrativa") String IdUnidadAdministrativa, 
				@PathVariable("IdProcesoVigente") String IdProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				evaluacionHistoricoService.getServidoresedduaHt(IdServidorPublico,IdUnidadAdministrativa, IdProcesoVigente));
	}
        
}
