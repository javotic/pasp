package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.InstruccionesKPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InstruccionesKPIDAO  {

        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
        // interface extends JpaRepository<InstruccionesKPI, String>
	//@Query(value = "SELECT * FROM dbo.INSTRUCCIONESKPI(?)", nativeQuery = true)
	public List<Object[]> consultarInstruccionesKPI(String IdProcesoVigente){
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.INSTRUCCIONESKPI("+ IdProcesoVigente + ")";
            return entityManager.createNativeQuery(sql).getResultList();     
        }
        
        //@Query(value = "SELECT * FROM dbo.EVALUACIONKPI(?,?)", nativeQuery = true)
	public List<Object[]> evaluacionKpi(String claveServidorPublico, String idProcesoKPI){
            claveServidorPublico = claveServidorPublico == null?null: "'" +claveServidorPublico + "'";
            idProcesoKPI = idProcesoKPI== null?null: "'" +idProcesoKPI + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.EVALUACIONKPI("+ claveServidorPublico + ", " + idProcesoKPI + ")";
            return entityManager.createNativeQuery(sql).getResultList();   
        }
        

}
