package mx.gob.edomex.microservicios.autoservicio.models.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import mx.gob.edomex.microservicios.autoservicio.models.entity.EvaluacionDesempenio;





public interface EvaluacionDesempenioRepository extends JpaRepository<EvaluacionDesempenio, Long> {

//	List<EvaluacionDesempenio> findByClaveprocesoAndSpevaluadorAndSpevaluadoAndClaveunidadadmin(String claveproceso,
//			String spevaluador, String spevaluado, String claveunidadadmin);
	
	List<EvaluacionDesempenio> findByClaveprocesoAndSpevaluadorAndSpevaluadoAndClaveunidadadmin(String claveproceso,
			String spevaluador, String spevaluado, String claveunidadadmin);

}

