package mx.gob.edomex.microservicios.autoservicio.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.autoservicio.models.entity.SolicitudConstancia;
import mx.gob.edomex.microservicios.autoservicio.models.repository.SolicituConstanciaRepository;

@Service
public class SolicitudConstanciaServiceImpl implements ISolicitudConstanciaService{

	@Autowired
	private SolicituConstanciaRepository dao;
	@Override
	public SolicitudConstancia guardaSolicitudConstancia(SolicitudConstancia solicitudConstancia) {
		solicitudConstancia.setFechaSolicitud(new Date());
		return dao.save(solicitudConstancia);
	}
	
	

}
