package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.ResultadosPreguntas;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResultadosPreguntasDAO{
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.RESULTADOSPREGUNTASEDD(?,?,?)", nativeQuery = true)
	public List<Object[]> resultadosPregunta(String idServidorPublico , String idSeccion , String idProceso){
            idServidorPublico = idServidorPublico== null?null: "'" +idServidorPublico + "'";
            idSeccion = idSeccion== null?null: "'" +idSeccion + "'";
            idProceso = idProceso== null?null: "'" +idProceso + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.RESULTADOSPREGUNTASEDD("+ idServidorPublico +", "+ idSeccion +","+ idProceso +")";
            return entityManager.createNativeQuery(sql).getResultList();             
        }

}
