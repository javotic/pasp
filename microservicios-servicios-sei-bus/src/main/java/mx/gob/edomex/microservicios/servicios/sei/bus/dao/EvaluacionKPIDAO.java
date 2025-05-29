package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.EvaluacionKPI;

public interface EvaluacionKPIDAO  extends JpaRepository<EvaluacionKPI, String>{

	@Query(value = "SELECT * FROM dbo.EVALUACIONKPI(?,?)", nativeQuery = true)
	List<Object[]> consultarEvaluacionKPI(String IdServidorPublico, String IdProcesoVigente);
	
}
