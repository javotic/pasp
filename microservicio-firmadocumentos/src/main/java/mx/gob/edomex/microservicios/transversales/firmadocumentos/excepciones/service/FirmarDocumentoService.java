package mx.gob.edomex.microservicios.transversales.firmadocumentos.excepciones.service;

import mx.gob.edomex.microservicios.transversales.firmadocumentos.excepciones.ServiceExcepcion;

/**
 * Servicio de firma de documentos.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 01/12/2021.
 */
public interface FirmarDocumentoService {

    // <editor-fold defaultstate="collapsed" desc="Funciones publicas">
    /**
     * Firmar documento con sello digital.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 01/12/2021.
     * @param cuts CUTS del usuario que firmara el documento.
     * @param contrasenia Contrasenia de CUTS del usuario firmante.
     * @param documento Documento a firmar
     * @return byte[] con el documento firmado.
     * @throws ServiceExcepcion de procesamiento general.
     */
    public byte[] firmarDocumento(String cuts, String contrasenia,
            byte[] documento) throws ServiceExcepcion;
    //</editor-fold>
}
