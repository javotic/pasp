package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestas;

public interface EnvioRespuestasDAO extends JpaRepository<EnvioRespuestas, String> {

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "DECLARE @ENVIORESPUESTAS as ENVIORESPUESTAS_TABLE \n "
			+ "INSERT @ENVIORESPUESTAS (IDPREGUNTA,IDRESPUESTA,IDEVALUACION,IDSEVIDORPUBLICOEVALUADO,IDSEVIDORPUBLICOEVALUADOR,IDUNIDADADMINISTRATIVA,TIPOSECCION,PUNTAJEOBTENIDO) \n"
			+ "VALUES (?,?,?,?,?,?,?,?)", nativeQuery = true)
	public void saveRespuestas(String Idpregunta, String Idrespuesta, String Idevaluacion,
			String Idsevidorpublicoevaluado, String Idsevidorpublicoevaluador, String Idunidadadministrativa,
			String Tiposeccion, String Puntajeobtenido);
        
}
