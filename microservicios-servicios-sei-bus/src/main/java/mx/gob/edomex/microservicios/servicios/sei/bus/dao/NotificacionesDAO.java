package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.Notificaciones;

public interface NotificacionesDAO  extends JpaRepository<Notificaciones, String>{

	@Query(value = "SELECT * FROM dbo.NOTIFICACIONES(?)", nativeQuery = true)
	List<Object[]> consultarDetalleEddUa(String IdServidorPublico);
}
