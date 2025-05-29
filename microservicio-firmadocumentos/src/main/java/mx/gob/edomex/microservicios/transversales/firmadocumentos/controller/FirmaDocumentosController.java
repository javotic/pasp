package mx.gob.edomex.microservicios.transversales.firmadocumentos.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.edomex.microservicios.transversales.firmadocumentos.excepciones.ServiceExcepcion;
import mx.gob.edomex.microservicios.transversales.firmadocumentos.excepciones.service.FirmarDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador de firma de documentos
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 01/12/2021.
 */
@RestController
@RequestMapping("/firmarDocumentos")
public class FirmaDocumentosController {
    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private FirmarDocumentoService firmarDocumentoService;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Funciones GET">
    @RequestMapping("/")
    public String greeting() {
        return "Servicio de firma de documentos ejecutado correctamente.";
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Funciones POST">
    /**
     * Firmar un documento con mecanismo de firma del SEI.
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 01/12/2021.
     * @param cuts CUTS de usuario que realizara la firma,
     * @param contrasenia Contrasenia de CUTS para firmar.
     * @param documento Documento a firmar.
     * @throws ServiceExcepcion de procesamiento general
     */
    @PostMapping("/firmar")
    public ResponseEntity<byte[]> enrolar(
            @RequestPart(name = "cuts", required = true) String cuts,
            @RequestPart(name = "contrasenia", required = true) String contrasenia,
            @RequestPart(name = "documento", required = true) MultipartFile documento)
            throws ServiceExcepcion {
        byte[] documentoFirmado = null;
        try {
            byte[] archivo = documento.getBytes();
            
            documentoFirmado = this.firmarDocumentoService.firmarDocumento(
                    cuts, 
                    contrasenia, 
                    archivo
            );
        } catch (IOException ex) {
            Logger.getLogger(FirmaDocumentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ResponseEntity<>(documentoFirmado, HttpStatus.OK);
        
    }
    //</editor-fold>
}
