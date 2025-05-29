package mx.gob.edomex.microservicios.autoservicio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.autoservicio.models.entity.Parametros;
import mx.gob.edomex.microservicios.autoservicio.models.repository.ParametrosRepository;
@Service
public class ParametrosServiceImpl  implements ParametrosService{
	@Autowired
	private ParametrosRepository parametrosRepository;
	@Override
	public Parametros findByClaveParametro(String ClaveParametro) {
		// TODO Auto-generated method stub
		return parametrosRepository.findByClaveParametro(ClaveParametro);
	}

}
