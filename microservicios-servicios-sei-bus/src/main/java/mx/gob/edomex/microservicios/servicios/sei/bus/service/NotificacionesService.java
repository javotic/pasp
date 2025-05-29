package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Notificaciones;

public interface NotificacionesService {
List<Notificaciones> consultarNotificaciones(String IdServidorPublico) throws BusException;;
}
