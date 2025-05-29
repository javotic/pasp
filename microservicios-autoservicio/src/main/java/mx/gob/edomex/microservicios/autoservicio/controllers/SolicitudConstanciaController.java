package mx.gob.edomex.microservicios.autoservicio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.autoservicio.models.entity.SolicitudConstancia;
import mx.gob.edomex.microservicios.autoservicio.services.ISolicitudConstanciaService;



//@CrossOrigin({ "http://localhost:4200" })
@RequestMapping("/solicitudConstancia")
@RestController
public class SolicitudConstanciaController {

	
	@Autowired
	private ISolicitudConstanciaService solicitudConstanciaService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody SolicitudConstancia solicitud) {
	
		SolicitudConstancia bi = solicitudConstanciaService.guardaSolicitudConstancia(solicitud);
		return new ResponseEntity<SolicitudConstancia>(bi, HttpStatus.OK);
	}
}
