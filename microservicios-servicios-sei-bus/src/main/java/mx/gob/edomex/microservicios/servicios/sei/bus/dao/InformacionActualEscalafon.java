package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuntajePrevio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InformacionActualEscalafon{

        @PersistenceContext
        EntityManager entityManager;
            
        @Value("${edomex.database.evaluacion.schema}")
        private String schema;
        
	//@Query(value = "SELECT * from dbo.escalafonPuntajePrevio(?,?)", nativeQuery = true)
	public List<Object[]> escalafonPuntajePrevio( String idServidorPublico,String idProceso  ){
            idServidorPublico = idServidorPublico== null?null: "'" +idServidorPublico + "'";
            idProceso = idProceso== null?null: "'" +idProceso + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonPuntajePrevio("+ idServidorPublico +","+ idProceso +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
	
	//@Query(value = "SELECT * from dbo.escalafonPuestoActual(?)", nativeQuery = true)
	public List<Object[]> escalafonPuestoActual( String idServidorPublico  ){
            idServidorPublico = idServidorPublico== null?null: "'" +idServidorPublico + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonPuestoActual("+ idServidorPublico +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
	
	
	//@Query(value = "SELECT * from dbo.escalafonArbol(?)", nativeQuery = true)
	public List<Object[]> escalafonArbol( String idServidorPublico  ){
            idServidorPublico = idServidorPublico== null?null: "'" +idServidorPublico + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonArbol("+ idServidorPublico +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
}
