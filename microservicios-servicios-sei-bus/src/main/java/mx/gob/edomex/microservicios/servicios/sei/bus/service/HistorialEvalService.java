package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluaciones;

public interface HistorialEvalService {

	List<HistorialEvaluaciones> consultarHistorialEvaluacion(String IdServidorPublico) throws BusException;

}
