package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.NotificacionesDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Notificaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.NotificacionesService;

@Service
public class NotificacionesServiceImpl implements NotificacionesService {
	private static final Logger LOG = LoggerFactory.getLogger(NotificacionesServiceImpl.class);

	@Autowired
	private NotificacionesDAO notificacionesDAO;

	@Override
	public List<Notificaciones> consultarNotificaciones(String IdServidorPublico) throws BusException {
		List<Notificaciones> lstNotif = new ArrayList<>();
		try {
			List<Object[]> lstResul = notificacionesDAO.consultarDetalleEddUa(IdServidorPublico);
			lstResul.forEach(e -> {
				Notificaciones notf = new Notificaciones();
				notf.setDESCRIPCION(e[2].toString());
				notf.setIDNOTIFICACION(e[0].toString());
				notf.setTIPONOFICIACION(e[1].toString());
				lstNotif.add(notf);

			});

		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return lstNotif;
	}

}
