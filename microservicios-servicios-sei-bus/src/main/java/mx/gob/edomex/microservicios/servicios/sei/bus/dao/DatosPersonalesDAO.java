package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonales;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public class DatosPersonalesDAO /*extends JpaRepository<DatosPersonales, String>*/ {
        @PersistenceContext
        EntityManager entityManager;
        
        @Value("${edomex.database.evaluacion.schema}")
        private String schema;

        //@Query(value = "SELECT * FROM dbo.CONSULTADATOSPERSONALES (?,?,?,?)", nativeQuery = true)
	//List<Object[]> consultarDatosPersonales(String IdServidorPublico, String nombre, String apellidoPat, String apellidoMat);

	//@Query(value = "SELECT * FROM  pevaluacion.dbo.CONSULTADATOSPERSONALES (?,?,?,?)", nativeQuery = true)
	public List<Object[]> consultarDatosPersonales(String IdServidorPublico, String nombre, String apellidoPat, String apellidoMat){
            IdServidorPublico = IdServidorPublico== null?null: "'" +IdServidorPublico + "'";
            nombre = nombre== null?null: "'" +nombre + "'";
            apellidoPat = apellidoPat== null?null: "'" +apellidoPat + "'";
            apellidoMat = apellidoMat== null?null: "'" +apellidoMat + "'";
            String sql = "SELECT * FROM  "+ schema + ".dbo.CONSULTADATOSPERSONALES ("+IdServidorPublico+","+ nombre +","+apellidoPat+","+ apellidoMat +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }


}
