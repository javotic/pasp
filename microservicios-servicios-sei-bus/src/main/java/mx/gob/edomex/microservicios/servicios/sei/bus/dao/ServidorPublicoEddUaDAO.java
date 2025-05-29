package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoEddUa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServidorPublicoEddUaDAO{
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.SERVIDORESPUBLICOSEDDUA(?,?,?)", nativeQuery = true)
	public List<Object[]> consultarServidoresEddUaI(String IdServidorPublico, String IdUnidadAdministrativa, String IdProcesoVigente){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdUnidadAdministrativa = IdUnidadAdministrativa== null?null: "'" +IdUnidadAdministrativa + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.SERVIDORESPUBLICOSEDDUA("+ IdServidorPublico +","+ IdUnidadAdministrativa +","+ IdProcesoVigente + ") order by ESTATUSEVALUACION";
            return entityManager.createNativeQuery(sql).getResultList();                    
        }

}
