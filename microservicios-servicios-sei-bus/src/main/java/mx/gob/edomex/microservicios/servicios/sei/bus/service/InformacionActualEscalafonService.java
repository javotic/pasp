package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.ArbolEscalafonarioResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonArbol;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuestoActual;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuntajePrevio;

public interface InformacionActualEscalafonService {

	
	List<EscalafonPuntajePrevio> escalafonPuntajePrevio(String claveServidorPublico,String idProceso ) throws BusException;
	
	List<EscalafonPuestoActual> escalafonPuestoActual(String claveServidorPublico ) throws BusException;
	
	List<ArbolEscalafonarioResponse> escalafonArbol(String claveServidorPublico ) throws BusException;
	
}
