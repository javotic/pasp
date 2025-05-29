package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEDD;

public interface DetalleEDDService {
List<DetalleEDD> consultarDetalleEdd(String IdServidorPublico, String IdProcesoVigente) throws BusException;
}
