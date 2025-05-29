package mx.gob.edomex.microservicios.autoservicio.services;

import mx.gob.edomex.microservicios.autoservicio.models.entity.Parametros;

public interface ParametrosService {

	public Parametros findByClaveParametro(String ClaveParametro);

}
