/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEDD;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author smartinez
 */
@Component
public class EvaluacionHistoricoDAO{
        @PersistenceContext
        EntityManager entityManager;

        @Value("${edomex.database.evaluacion.schema}")
        private String schema; 
        
    	//@Query(value = "SELECT * FROM pevaluacion.dbo.DETALLEEDDUA(?,?)", nativeQuery = true)
	public List<Object[]> getDetalleUnidades(String IdServidorPublico, String IdProcesoVigente){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.DETALLEEDDUAHT("+ IdServidorPublico +","+ IdProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();   
        }
       
        
	public List<Object[]> getDetalleEdd_UaHT(String IdServidorPublico, String IdProcesoVigente, String IdUnidadAdministrativa){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            IdUnidadAdministrativa = IdUnidadAdministrativa== null?null: "'" +IdUnidadAdministrativa + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.DETALLEEDDUA_UAHT("+ IdServidorPublico + ","+ IdProcesoVigente + ", "+ IdUnidadAdministrativa +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
        
	public List<Object[]> getServidoresedduaHt(String IdServidorPublico, String IdUnidadAdministrativa, String IdProcesoVigente){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            IdUnidadAdministrativa = IdUnidadAdministrativa== null?null: "'" +IdUnidadAdministrativa + "'";
            IdProcesoVigente = IdProcesoVigente== null?null: "'" +IdProcesoVigente + "'";
            String sql = "SELECT * FROM "+ schema +".dbo.SERVIDORESPUBLICOSEDDUAHT("+ IdServidorPublico +","+ IdUnidadAdministrativa +","+ IdProcesoVigente + ") order by ESTATUSEVALUACION ";
            return entityManager.createNativeQuery(sql).getResultList();                    
        }        
        
}
