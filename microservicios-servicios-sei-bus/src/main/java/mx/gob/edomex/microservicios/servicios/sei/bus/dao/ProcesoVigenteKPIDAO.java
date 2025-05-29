package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.ProcesoKPIVigente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ProcesoVigenteKPIDAO{
	@PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
        //interface extends JpaRepository<ProcesoKPIVigente, String>
	//@Query(value = "SELECT * FROM dbo.EVALUACIONKPIVIGENTE(?)", nativeQuery = true)
	public List<Object[]> consultarProcesoVigente(String IdServidorPublico){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.EVALUACIONKPIVIGENTE("+ IdServidorPublico + ")";
            return entityManager.createNativeQuery(sql).getResultList();  
        }

}
