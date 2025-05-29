package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.Notificaciones;

public interface PagosDAO  extends JpaRepository<Notificaciones, String>{

	@Query(value = "SELECT * FROM dbo.CONSULTADATOSBANCARIOS(?)", nativeQuery = true)
	List<Object[]> consultarDatosBancarios(String IdServidorPublico);
        
        
        @Query(value = "SELECT * FROM dbo.consultaDatosPuntyAsist(?,?,?,?)", nativeQuery = true)
	List<Object[]> consultaDatosPuntyAsist(String claveServidor, String tpDato, String fecha1, String fecha2);
        
}
