/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author smartinez
 */

@Component
public interface CatalogosDAO extends JpaRepository<DatosServidorPublico, String>{
    
    	@Query(value = "SELECT * FROM dbo.CONSULTA_EMISORCERT()", nativeQuery = true)
	List<Object[]> catEmisoresCertificado();
        
        @Query(value = "SELECT * FROM dbo.CONSULTAR_LISTA_PAGOS(?,?,?)", nativeQuery = true)
	List<Object[]> getListadoPagos(String idServidorPublico, String fechaInicio, String fechaFin);
        
        @Query(value = "SELECT * FROM dbo.CONSULTACATTIPOCERTIFICADO()", nativeQuery = true)
	List<Object[]> getTipoCertificados();
                
                
}
