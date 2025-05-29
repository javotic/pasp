package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPuesto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatosPuestoDAO {
        @PersistenceContext
        EntityManager entityManager;
        
        @Value("${edomex.database.evaluacion.schema}")
        private String schema;        
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.CONSULTADATOSPUESTO(?,?)", nativeQuery = true)
	public List<Object[]> consultarDatosPuesto(String IdServidorPublico, String proceso){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            proceso = proceso== null?null: "'" +proceso + "'";
            String sql = "SELECT * FROM "+schema+".dbo.CONSULTADATOSPUESTO("+ IdServidorPublico +","+ proceso +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }

        //@Query(value = "SELECT * FROM pevaluacion.dbo.CONSULTADATOSPUESTOHT(?,?)", nativeQuery = true)
	public List<Object[]> consultarDatosPuestoHT(String IdServidorPublico, String proceso){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            proceso = proceso== null?null: "'" +proceso + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.CONSULTADATOSPUESTOHT("+ IdServidorPublico +","+ proceso +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
        
}
