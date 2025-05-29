package mx.gob.edomex.microservicios.autoservicio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.autoservicio.services.ParametrosService;

//@CrossOrigin({ "http://localhost:4200" })
@RestController
public class ParametrosController {
	@Autowired
	private ParametrosService parametrosService;

	@GetMapping("/claveParametro/{clave}")
	public ResponseEntity<?> findByClaveParametro(@PathVariable String clave) {
		
		return ResponseEntity.ok().body(parametrosService.findByClaveParametro(clave));

	}

}
