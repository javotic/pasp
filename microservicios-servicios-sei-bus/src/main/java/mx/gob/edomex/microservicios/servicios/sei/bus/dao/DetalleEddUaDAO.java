package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddUa;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DetalleEddUaDAO {

        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
	//@Query(value = "SELECT * FROM pevaluacion.dbo.DETALLEEDDUA(?,?)", nativeQuery = true)
	public List<Object[]> consultarDetalleEddUa(String IdServidorPublico, String IdProcesoVigente){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.DETALLEEDDUA("+ IdServidorPublico + ","+ IdProcesoVigente + ")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
        
        //@Query(value = "SELECT * FROM pevaluacion.dbo.DETALLEEDDUA_UA(?,?,?)", nativeQuery = true)
	public List<Object[]> consultarDetalleEdd_Ua(String IdServidorPublico, String IdProcesoVigente, String IdUnidadAdministrativa){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            IdUnidadAdministrativa = IdUnidadAdministrativa== null?null: "'" +IdUnidadAdministrativa + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.DETALLEEDDUA_UA("+ IdServidorPublico + ","+ IdProcesoVigente + ", "+ IdUnidadAdministrativa +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
        
}
