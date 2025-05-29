package mx.gob.edomex.microservicios.serviciosreportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.serviciosreportes.service.CartaAceptacionService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/getReporteCartaAceptacion")
public class CartaAceptacionController {

    @Autowired
    private CartaAceptacionService service;

    @GetMapping(value = "/generarReporte/{claveServidorPublico}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporte(@PathVariable("claveServidorPublico") String claveServidorPublico,
            @RequestParam String idProcesoVigente) {
        byte[] data = null;
        data = service.getReporteCartaAceptacion(claveServidorPublico, idProcesoVigente);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/generarReporteUsuario/{claveServidorPublico}/{aceptoCarta}/{actualizarRegistro}/{idPlazaParticipacion}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporteUsuario(@PathVariable("claveServidorPublico") String claveServidorPublico,
            @PathVariable("aceptoCarta") boolean aceptoCarta,
            @PathVariable("actualizarRegistro") boolean actualizarRegistro,
            @PathVariable("idPlazaParticipacion") String idPlazaParticipacion,
            @RequestParam String idProcesoVigente,
            @RequestParam String lugarProceso) throws Exception {
        byte[] data = null;
        data = service.getReporteCartaAceptacionUsuario(claveServidorPublico, idProcesoVigente, aceptoCarta, idPlazaParticipacion, lugarProceso, actualizarRegistro);

        if (data == null) {
            throw new Exception("No se a obtenido el documento");
        } else {
            return new ResponseEntity<byte[]>(data, HttpStatus.OK);
        }

    }
}
