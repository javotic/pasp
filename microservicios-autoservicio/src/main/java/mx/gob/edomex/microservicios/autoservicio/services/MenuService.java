package mx.gob.edomex.microservicios.autoservicio.services;



import java.util.List;

import mx.gob.edomex.microservicios.autoservicio.models.entity.EvaluacionDesempenio;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu_rol;

public interface MenuService {
	
	public Iterable<Menu> findAll();
	public Iterable<Menu_rol> findByIdRol(Long IdRol);
	public List<EvaluacionDesempenio>findById(String claveproceso,
			String spevaluador, String spevaluado, String claveunidadadmin);
	public EvaluacionDesempenio savePreguntas(EvaluacionDesempenio desempenio);

}
