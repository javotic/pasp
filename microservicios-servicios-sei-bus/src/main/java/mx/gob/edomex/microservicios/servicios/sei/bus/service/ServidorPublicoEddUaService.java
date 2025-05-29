package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoEddUa;

public interface ServidorPublicoEddUaService {
	DetalleEddeSP consultarServidorPublicoEdd(String IdServidorPublico, String IdUnidadAdministrativa, String IdProcesoVigente) throws BusException;
}
