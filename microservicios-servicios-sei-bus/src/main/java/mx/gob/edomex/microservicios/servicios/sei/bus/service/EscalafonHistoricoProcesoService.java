package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonHistoricoProcesoDTO;

public interface EscalafonHistoricoProcesoService {

	
	List<EscalafonHistoricoProcesoDTO> escalafonHistoricoProcesos(String claveServidorPublico) throws BusException;
}
