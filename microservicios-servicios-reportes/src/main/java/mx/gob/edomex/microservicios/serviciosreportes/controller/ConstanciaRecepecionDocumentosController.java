package mx.gob.edomex.microservicios.serviciosreportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaRecepecionDocumentosService;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/reporteRepecionDocumentos")
public class ConstanciaRecepecionDocumentosController {

	
	@Autowired
	private ConstanciaRecepecionDocumentosService service;
	
	@GetMapping(value = "/generarReporte/{claveServidorPublic}/{idPlaza}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte(
                @PathVariable("claveServidorPublic") String claveServidorPublic,
                @PathVariable("idPlaza") String idPlaza) {
		byte[] data = null;
		data = service.getConstanciaRecepecionDocumentos(claveServidorPublic , idPlaza);
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
}
