package mx.gob.edomex.microservicios.autoservicio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.autoservicio.models.entity.Bitacora;
import mx.gob.edomex.microservicios.autoservicio.services.BitacoraService;

//@CrossOrigin({ "http://localhost:4200" })
@RequestMapping("/bitacora")
@RestController
public class BitacoraController {

	@Autowired
	private BitacoraService bitacoraService;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Bitacora bitacora) {
		Bitacora bi = bitacoraService.guardarBitacora(bitacora);
		return new ResponseEntity<Bitacora>(bi, HttpStatus.OK);
	}

}
