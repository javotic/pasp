package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPuesto;

public interface DatosPuestoService {

	DatosPuesto consultarDatosPuesto(String IdServidorPublico, String proceso) throws BusException;
        
        DatosPuesto consultarDatosPuestoHT(String IdServidorPublico, String proceso) throws BusException;
        
}
