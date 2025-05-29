package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ResultadosEddPreg;

public interface ResultadosEDDService {

	ResultadosEddPreg consultarResultados(String idServidorPublico , String idSeccion , String idProceso) throws BusException;

}
