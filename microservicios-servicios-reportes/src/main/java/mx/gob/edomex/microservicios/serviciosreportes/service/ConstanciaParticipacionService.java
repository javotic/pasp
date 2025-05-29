package mx.gob.edomex.microservicios.serviciosreportes.service;

import java.util.List;

import mx.gob.edomex.microservicios.serviciosreportes.models.AsignacionPuntajeEscalafonario;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonPuntajePrevio;

public interface ConstanciaParticipacionService {

	byte[] getReporteConstanciaParticipacion(String claveServidorPublico, String idPlaza, String idProcesoVigente);
		
	ResponseEscalafonDatosCandidato escalafonDatosCandidato(String claveServidorPublico, String idPlaza) ;
	
	ResponseEscalafonPuntajePrevio puntajePrevio(String claveServidorPublico, String idProcesoVigente);
}
