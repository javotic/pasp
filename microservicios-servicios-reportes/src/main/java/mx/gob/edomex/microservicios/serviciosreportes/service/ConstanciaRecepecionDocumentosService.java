package mx.gob.edomex.microservicios.serviciosreportes.service;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonDatosCandidato;

public interface ConstanciaRecepecionDocumentosService {

	byte[] getConstanciaRecepecionDocumentos(String claveServidorPublic, String idPlaza);
	
}
