package mx.gob.edomex.microservicios.autoservicio.models.repository;

import org.springframework.data.repository.CrudRepository;


import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu_rol;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu_rolPK;

public interface MenuRolRepository extends CrudRepository<Menu_rol, Menu_rolPK> {

	Iterable<Menu_rol> findByIdIdRolOrderByMenuNumOrdenAsc(Long IdRol);

}
