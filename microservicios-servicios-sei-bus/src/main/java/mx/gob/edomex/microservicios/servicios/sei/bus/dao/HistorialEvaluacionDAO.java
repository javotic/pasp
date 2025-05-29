package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluaciones;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HistorialEvaluacionDAO {
    
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.CONSULTARHISTORIALEVALUACIONES(?)", nativeQuery = true)
	public List<Object[]> consultarHistoricoEval(String IdServidorPublico){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            String sql = "SELECT * FROM "+ schema + ".dbo.CONSULTARHISTORIALEVALUACIONES("+ IdServidorPublico +")";
            return entityManager.createNativeQuery(sql).getResultList();            
        }
}
