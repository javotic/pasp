package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.edomex.firma.fump.FirmarFump;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.FirmarDocumentoService;
import org.springframework.stereotype.Service;

/**
 * Implementacion de servicio de firma de documentos.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 01/12/2021.
 */
@Service
public class FirmarDocumentoServiceImpl implements FirmarDocumentoService {
    // <editor-fold defaultstate="collapsed" desc="Constantes">
    public static final String REPOSITORIO = System.getProperty("user.home") + "/Downloads/firmar/";
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funciones publicas">
    @Override
    public byte[] firmarDocumento(String cuts, String contrasenia, 
            byte[] documento) throws BusException {
        byte[] documentoFirmado = null;
        try {
            
            Logger.getLogger(FirmarDocumentoServiceImpl.class.getName()).log(
                    Level.INFO, null, "Entrando a servicio de firma");
            
            Map<String, Object> respuesta = FirmarFump.ejecutar(
                    documento,
                    UUID.randomUUID().toString() + ".pdf",
                    cuts,
                    contrasenia,
                    "DocumentoFirmaMicroservicio"
            );
            
            Logger.getLogger(FirmarDocumentoServiceImpl.class.getName()).log(
                    Level.INFO, null, "Saliendo de servicio de firma");

            documentoFirmado = (byte[]) respuesta.get("pdf");
        } catch (Exception ex) {
            Logger.getLogger(FirmarDocumentoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documentoFirmado;
    }
    //</editor-fold>
}
