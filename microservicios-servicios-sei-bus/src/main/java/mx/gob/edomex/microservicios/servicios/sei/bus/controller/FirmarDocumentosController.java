package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.FirmarDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador que permita la firma de documentos de forma transversal.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 23/12/2021.
 */
@RestController
@RequestMapping("/firmarDocumentos")
public class FirmarDocumentosController {

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private FirmarDocumentoService firmarDocumentoService;
    //</editor-fold>

    //<editor-fold desc="Funciones POST" defaultstate="collapsed">
    /**
     * Firmar un documento con mecanismo de firma del SEI.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 23/12/2021.
     * @param cuts CUTS de usuario que realizara la firma,
     * @param contrasenia Contrasenia de CUTS para firmar.
     * @param documento Documento a firmar.
     * @return Respuesta de servicio con documento firmado.
     * @throws BusException de procesamiento general.
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/firmar")
    public ResponseEntity<byte[]> descargarSolicitud(
            @RequestPart(name = "cuts", required = true) String cuts,
            @RequestPart(name = "contrasenia", required = true) String contrasenia,
            @RequestPart(name = "documento", required = true) MultipartFile documento)
            throws BusException {
        byte[] documentoFirmado = null;
        try {
            documentoFirmado = this.firmarDocumentoService.firmarDocumento(
                    cuts,
                    contrasenia,
                    documento.getBytes()
            );
        } catch (Exception e) {
            Logger.getLogger(FirmarDocumentosController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(documentoFirmado, HttpStatus.OK);
    }
    //</editor-fold>
}
