package mx.gob.edomex.microservicios.autoservicio.models.repository;

import org.springframework.data.repository.CrudRepository;

import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {

}
