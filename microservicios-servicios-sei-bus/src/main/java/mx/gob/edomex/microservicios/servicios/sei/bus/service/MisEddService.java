package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.MisEdd;

public interface MisEddService {
	List<MisEdd> obtenerMisEDD(String idServidorPublico) throws BusException;

}
