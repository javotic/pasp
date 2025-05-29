package mx.gob.edomex.microservicios.serviciosreportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaParticipacionService;

@RestController
@RequestMapping("/reportesConstancia")
public class ConstanciaParticipacionController {

	@Autowired
	private ConstanciaParticipacionService service;
	
	@GetMapping(value = "/generarReporte/{claveServidorPublico}/{idPlaza}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte(@PathVariable("claveServidorPublico") String claveServidorPublico,
			@PathVariable("idPlaza") String idPlaza,
			@RequestParam String idProcesoVigente) {
		byte[] data = null;
		data = service.getReporteConstanciaParticipacion(claveServidorPublico,idPlaza,idProcesoVigente);
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
}
