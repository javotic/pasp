package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

@Component
public class EscalafonGeneralDAO{

        @PersistenceContext
        EntityManager entityManager;
            
        @Value("${edomex.database.evaluacion.schema}")
        private String schema;
        
	//@Query(value = "SELECT * FROM dbo.escalafonDatosCandidato(?,?)", nativeQuery = true)
	public List<Object[]> escalafonDatosCandidato( String claveServidorPublico, String idPlaza){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idPlaza = idPlaza== null?null: "'" +idPlaza + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonDatosCandidato("+ claveServidorPublico +","+ idPlaza +")";
            return entityManager.createNativeQuery(sql).getResultList();
        }
        
        //@Query(value = "SELECT * FROM dbo.escalafonCapacCursos(?,?, null)", nativeQuery = true)
	public List<Object[]> escalafonEstadoInscripcion( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonCapacCursos("+ claveServidorPublico +","+ idProcesoVigente +", null)";
            return entityManager.createNativeQuery(sql).getResultList();                
        }
        
        //@Query(value = "SELECT * FROM dbo.escalafonCapacCertifComp(?,?, null)", nativeQuery = true)
	public List<Object[]> escalafonCapacCertifComp( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonCapacCertifComp("+ claveServidorPublico +","+ idProcesoVigente +", null)";
            return entityManager.createNativeQuery(sql).getResultList();            
        }
        
        //@Query(value = "SELECT * FROM dbo.escalafonDiplomados(?,?, null)", nativeQuery = true)
	public List<Object[]> escalafonDiplomados( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonDiplomados("+ claveServidorPublico +","+ idProcesoVigente +", null)";
            return entityManager.createNativeQuery(sql).getResultList();               
        }
        
        //@Query(value = "SELECT * FROM dbo.escalafonEscolaridad(?,?)", nativeQuery = true)
	public List<Object[]> escalafonEscolaridad( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonEscolaridad("+ claveServidorPublico +","+ idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();                 
        }
        
        //@Query(value = "SELECT * FROM dbo.escalafonEficiencia(?,?)", nativeQuery = true)
	public List<Object[]> escalafonEficiencia( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonEficiencia("+ claveServidorPublico +","+ idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();                
        }
              
        //@Query(value = "SELECT * FROM dbo.escalafonAntiguedad(?,?)", nativeQuery = true)
	public List<Object[]> escalafonAntiguedad( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonAntiguedad("+ claveServidorPublico +","+ idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();                
        }

        //@Query(value = "SELECT * FROM dbo.escalafonDatosRecepDoc(?,?)", nativeQuery = true)
	public List<Object[]> escalafonDatosRecepDoc( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonDatosRecepDoc("+ claveServidorPublico +","+ idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();              
        }      
        
        
        //@Query(value = "SELECT * FROM dbo.escalafonDemeritos(?,?)", nativeQuery = true)
	public List<Object[]> escalafonDemeritos( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonDemeritos("+ claveServidorPublico + "," +  idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();              
        }
        
        //@Query(value = "SELECT * FROM dbo.escalafonCapacInduccion(?,?, null)", nativeQuery = true)
	public List<Object[]> escalafonCapacInduccion( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonCapacInduccion("+ claveServidorPublico +","+ idProcesoVigente +", null)";
            return entityManager.createNativeQuery(sql).getResultList();               
        }        
        
        //@Query(value = "SELECT dbo.escalafonTipoCandidato(?,?,?)", nativeQuery = true)
	public List<Object[]> escalafonTipoCandidato( String claveServidorPublico, String idProcesoVigente, String idPlaza){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            idPlaza = idPlaza== null?null: "'" +idPlaza + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonTipoCandidato("+ claveServidorPublico + "," + idProcesoVigente +", " + idPlaza + ")";
            return entityManager.createNativeQuery(sql).getResultList();            
        }  
        
        //@Query(value = "SELECT * FROM dbo.escalafonCartaAceptacion(?,?)", nativeQuery = true)
	public List<Object[]> escalafonCartaAceptacion( String claveServidorPublico, String idProcesoVigente){
          claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonCartaAceptacion("+ claveServidorPublico +","+ idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();               
        }  
        
        /*
        @Modifying(clearAutomatically = true)
	@Transactional
        @Query(value = "DECLARE @res BIT \n "
                       + "EXEC escalafonAceptaPlaza ?, ?, ?, @res OUTPUT \n", nativeQuery = true)
	public Integer escalafonAceptaPlaza( String idServidorPublico, String idProcesoVigente, boolean estatus);   
        */

        //@Query(value = "SELECT * FROM dbo.escalafonDemeritosTotales(?,?)", nativeQuery = true)
	public List<Object[]> escalafonDemeritosTotales( String claveServidorPublico, String idProcesoVigente){
            claveServidorPublico = claveServidorPublico== null?null: "'" +claveServidorPublico + "'";
            idProcesoVigente = idProcesoVigente== null?null: "'" +idProcesoVigente + "'";
            
            String sql = "SELECT * FROM "+ schema +".dbo.escalafonDemeritosTotales("+ claveServidorPublico +","+ idProcesoVigente +")";
            return entityManager.createNativeQuery(sql).getResultList();     
        }        
        
}
