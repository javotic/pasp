package mx.gob.edomex.firma.fump;

import java.util.HashMap;
import java.util.Map;
import mx.gob.edomex.firma.fump.utils.Conexion;
import mx.gob.edomex.ws.firmamasiva.StringArray;

/**
 * Firma de documentos con mecanismo de sello digital.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0 02/12/2021.
 */
public class FirmarFump {

    // <editor-fold defaultstate="collapsed" desc="Propiedades de clase">
    public static String repositorio = "";
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructores">
    private FirmarFump() {
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funciones publicas">
    public static Map<String, Object> ejecutar(byte[] contenido, 
            String nombreArchivo, String CUTS, String PASS_SELLO, 
            String asunto) throws Exception {
        Conexion con = new Conexion();
        StringArray firmante = new StringArray();
        firmante.getItem().add(CUTS);
        firmante.getItem().add(PASS_SELLO);

        String hash = con.solicitudNueva(nombreArchivo, asunto, firmante, contenido);

        Map<String, Object> respuesta = new HashMap();
        respuesta.put("pdf", con.getPdf(hash));
        respuesta.put("xml", con.getXml(hash));
        
        return respuesta;
    }
    //</editor-fold>
}
