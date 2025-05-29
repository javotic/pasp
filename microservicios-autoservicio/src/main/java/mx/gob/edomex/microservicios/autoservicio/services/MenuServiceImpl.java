package mx.gob.edomex.microservicios.autoservicio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.edomex.microservicios.autoservicio.models.entity.EvaluacionDesempenio;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu_rol;
import mx.gob.edomex.microservicios.autoservicio.models.repository.EvaluacionDesempenioRepository;
import mx.gob.edomex.microservicios.autoservicio.models.repository.MenuRepository;
import mx.gob.edomex.microservicios.autoservicio.models.repository.MenuRolRepository;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuRolRepository menuRolRepository;
	@Autowired
	private  EvaluacionDesempenioRepository evaluacionRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Menu> findAll() {
		// TODO Auto-generated method stub
		return menuRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Menu_rol> findByIdRol(Long IdRol) {
		// TODO Auto-generated method stub
		return menuRolRepository.findByIdIdRolOrderByMenuNumOrdenAsc(IdRol);
	}


	@Override
	public List<EvaluacionDesempenio> findById(String claveproceso,
			String spevaluador, String spevaluado, String claveunidadadmin) {		
		return evaluacionRepository.findByClaveprocesoAndSpevaluadorAndSpevaluadoAndClaveunidadadmin(claveproceso, spevaluador, spevaluado, claveunidadadmin);
	}


	@Override
	public EvaluacionDesempenio savePreguntas(EvaluacionDesempenio desempenio) {		
		return evaluacionRepository.saveAndFlush(desempenio);
	}

}
