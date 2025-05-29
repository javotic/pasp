package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ProcesoVigente;

public interface ProcesoVigenteService {
	List<ProcesoVigente> consultarProcesoVigente(String IdServidorPublico) throws BusException;

}
