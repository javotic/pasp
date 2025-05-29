package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DetalleEddDAO{
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.DETALLEEDDUA(?,?)", nativeQuery = true)
	public List<Object[]> consultarDetalleEdd(String IdServidorPublico, String IdProcesoVigente){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.DETALLEEDDUA("+ IdServidorPublico + ","+ IdProcesoVigente + ")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
}
