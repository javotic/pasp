package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.MisEdd;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MisEddDAO{
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.HISTORICOEDD(?)", nativeQuery = true)
	public List<Object[]> consultarMisEdd(String IdServidorPublico){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            String sql = "SELECT * FROM " + schema +".dbo.HISTORICOEDD("+ IdServidorPublico + ") ORDER BY 2 DESC";
            return entityManager.createNativeQuery(sql).getResultList();              
        }
}
