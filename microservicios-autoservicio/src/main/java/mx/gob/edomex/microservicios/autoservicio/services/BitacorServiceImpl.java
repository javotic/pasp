package mx.gob.edomex.microservicios.autoservicio.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.autoservicio.models.entity.Bitacora;
import mx.gob.edomex.microservicios.autoservicio.models.repository.BitacoraRepository;

@Service
public class BitacorServiceImpl implements BitacoraService {

	@Autowired
	private BitacoraRepository repo;

	@Override
	public Bitacora guardarBitacora(Bitacora bitacora) {
		bitacora.setFechaRegistro(new Date());
		return repo.save(bitacora);

	}

}
