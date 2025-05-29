package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluacion;

public interface HistorialEvaluacionDAOKPI extends JpaRepository<HistorialEvaluacion, String> {

        /*
	@Query(value = "SELECT * FROM dbo.CONSULTARHISTORIALEVALUACIONESKPI(?)", nativeQuery = true)
	List<Object[]> consultarHistorialEvaluacionKPI(String claveServidorPublico);
        */

	@Query(value = "SELECT * FROM dbo.EVALUACIONKPIVIGENTE(?)", nativeQuery = true)
	List<Object[]> evaluacionKpiVigente(String idProcesoVigente);

	@Query(value = "SELECT * FROM dbo.INSTRUCCIONESKPI(?)", nativeQuery = true)
	List<Object[]> instruccionesKpi(String idProcesoKPI);

        /*
	@Query(value = "SELECT * FROM dbo.EVALUACIONKPI(?,?)", nativeQuery = true)
	List<Object[]> evaluacionKpi(String claveServidorPublico, String idProcesoKPI);
        */
	
        /*
	@Query(value = "SELECT * FROM dbo.PREGUNTASKPI(?,?)", nativeQuery = true)
	List<Object[]> preguntasKpi(String claveServidorPublico, String idProcesoKPI);
	*/
        /*
	@Query(value = "SELECT * FROM dbo.RESPUESTASPREGUNTAKPI(?,?)", nativeQuery = true)
	List<Object[]> respuestasKpi(String claveServidorPublico, String idProcesoKPI);
	*/
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "DECLARE @ENVIORESPUESTASKPI as ENVIORESPUESTASKPI_TABLE \n "
			+ " DECLARE @RESPUESTA VARCHAR(1) "
			+ " INSERT @ENVIORESPUESTASKPI  "
			+ "(TIEMPO,IDPREGUNTA,IDRESPUESTA,IDSEVIDORPUBLICO,IDEVALUACIONKPI) \n"
			+ "VALUES (?,?,?,?,?)", nativeQuery = true)
	public void saveRespuestasKpi(String tiempo, String idPregunta, String idRespuesta,
			String idServidorPublico,
			String idProcesoVigente);

}
