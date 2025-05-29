package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.PreguntasEjecucion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PreguntasEjecucionDAO{
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.PREGUNTASEJECUCIONED (?,?)", nativeQuery = true)
	public List<Object[]> consultarPreguntasEDD(String procesoVig, String seccion){
            procesoVig = procesoVig== null?null: "'" +procesoVig + "'";
            seccion = seccion== null?null: "'" +seccion + "'";
            String sql = "SELECT * FROM " + schema + ".dbo.PREGUNTASEJECUCIONED ("+ procesoVig +", "+ seccion +")";
            return entityManager.createNativeQuery(sql).getResultList();          
        }
}
