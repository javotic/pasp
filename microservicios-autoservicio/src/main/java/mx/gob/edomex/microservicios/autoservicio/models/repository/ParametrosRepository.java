package mx.gob.edomex.microservicios.autoservicio.models.repository;

import org.springframework.data.repository.CrudRepository;

import mx.gob.edomex.microservicios.autoservicio.models.entity.Parametros;



public interface ParametrosRepository extends CrudRepository<Parametros, Long>{
	

	Parametros findByClaveParametro(String ClaveParametro);

}
