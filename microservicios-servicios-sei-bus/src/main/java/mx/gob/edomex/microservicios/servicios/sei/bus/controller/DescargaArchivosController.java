package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import com.jcraft.jsch.JSchException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DescargaArchivosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ProcesoEscalafonarioVigenteService;
import org.springframework.http.ResponseEntity;

@RestController
    @RequestMapping("/descarga_archivos")
public class DescargaArchivosController {

	@Autowired
	private DescargaArchivosService service;
	

        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/GuiaEstudio/{idGuiaPuesto}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<byte[]> 
	descargaGuiaEstudio(@PathVariable("idGuiaPuesto") String idGuiaPuesto) throws BusException, JSchException {
    		return new ResponseEntity<byte[]>(service.descargaGuiaEstudio(idGuiaPuesto), HttpStatus.OK);
	}
        
        
        
        
	

}
