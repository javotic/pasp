package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonHistoricoProceso;

public interface EscalafonHistoricoProcesoDAO  extends JpaRepository<EscalafonHistoricoProceso, String>{

	
	@Query(value = "SELECT * FROM dbo.escalafonHistoricoProcesos(?)", nativeQuery = true)
	List<Object[]> escalafonHistoricoProcesos( String claveServidorPublico);
}
