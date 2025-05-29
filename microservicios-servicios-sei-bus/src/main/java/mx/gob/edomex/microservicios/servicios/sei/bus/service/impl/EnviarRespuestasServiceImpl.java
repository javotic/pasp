package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.EnvioRespuestasDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ItemEnvioRespuestasDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EnviarRespuestasService;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EnviarRespuestasServiceImpl implements EnviarRespuestasService {

         @PersistenceContext
        public EntityManager entityManager;
          
	@Autowired
	private EnvioRespuestasDAO envioRespuestasDAO;
        
        @Value("${edomex.database.evaluacion.url}")
	private String urlDataSource;
        @Value("${edomex.database.evaluacion.user}")
	private String databaseUser;
        @Value("${edomex.database.evaluacion.pass}")
	private String databasePass;

	@Override
	public void enviarRespuestas(String Idpregunta, String Idrespuesta, String Idevaluacion,
			String Idsevidorpublicoevaluado, String Idsevidorpublicoevaluador, String Idunidadadministrativa,
			String Tiposeccion, String Puntajeobtenido) throws BusException {

		try {
			envioRespuestasDAO.saveRespuestas(Idpregunta, Idrespuesta, Idevaluacion, Idsevidorpublicoevaluado,
					Idsevidorpublicoevaluador, Idunidadadministrativa, Tiposeccion, Puntajeobtenido);
		} catch (Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

	}
        
        
	public String envioRespuestasEvaluacionDesempenio(List<ItemEnvioRespuestasDTO> competenciasaptitudinales,
                                                        List<ItemEnvioRespuestasDTO> competenciassociopersonales,
                                                        List<ItemEnvioRespuestasDTO> demeritos,
                                                        String tipoEnvio) throws BusException {

		try {
                    SQLServerDataTable sourceDataTable = new SQLServerDataTable();
  
                    // Define metadata for the data table.  
                    sourceDataTable.addColumnMetadata("IDPREGUNTA" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDRESPUESTA" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDEVALUACION" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDSEVIDORPUBLICOEVALUADO" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDSEVIDORPUBLICOEVALUADOR" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDUNIDADADMINISTRATIVA" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("TIPOSECCION" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("PUNTAJEOBTENIDO" ,java.sql.Types.INTEGER);
                    sourceDataTable.addColumnMetadata("EVCONCLUIDA" ,java.sql.Types.INTEGER);

                    for(ItemEnvioRespuestasDTO respuesta : competenciasaptitudinales) {                           
                        // Llevar tabla  
                        sourceDataTable.addRow(
                                respuesta.getIDPREGUNTA(),
                                respuesta.getIDRESPUESTA(),
                                respuesta.getIDEVALUACION(),
                                respuesta.getIDSERVIDORPUBLICOEVALUADO(),
                                respuesta.getIDSERVIDORPUBLICOEVALUADOR(),
                                respuesta.getIDUNIDADADMINISTRAR(),
                                respuesta.getTIPOSECCION(),
                                respuesta.getPUNTAJEOBTENIDO(),
                                tipoEnvio);
                    }
                    
                    for(ItemEnvioRespuestasDTO respuesta : competenciassociopersonales) {                           
                        // Llevar tabla  
                        sourceDataTable.addRow(
                                respuesta.getIDPREGUNTA(),
                                respuesta.getIDRESPUESTA(),
                                respuesta.getIDEVALUACION(),
                                respuesta.getIDSERVIDORPUBLICOEVALUADO(),
                                respuesta.getIDSERVIDORPUBLICOEVALUADOR(),
                                respuesta.getIDUNIDADADMINISTRAR(),
                                respuesta.getTIPOSECCION(),
                                respuesta.getPUNTAJEOBTENIDO(),
                                tipoEnvio);
                    }
                    
                    for(ItemEnvioRespuestasDTO respuesta : demeritos) {                           
                        // Llevar tabla  
                        sourceDataTable.addRow(
                                respuesta.getIDPREGUNTA(),
                                respuesta.getIDRESPUESTA(),
                                respuesta.getIDEVALUACION(),
                                respuesta.getIDSERVIDORPUBLICOEVALUADO(),
                                respuesta.getIDSERVIDORPUBLICOEVALUADOR(),
                                respuesta.getIDUNIDADADMINISTRAR(),
                                respuesta.getTIPOSECCION(),
                                respuesta.getPUNTAJEOBTENIDO(),
                                tipoEnvio);
                    }
                    /*
                    Session session = (Session)entityManager.getDelegate();
                    SessionImpl sessionImpl = (SessionImpl) session;
                    
                    Connection conn =  sessionImpl.connection();
                    */
                     //Connection conn =  DriverManager.getConnection("jdbc:sqlserver://10.10.48.33:1433;databaseName=pevaluacion", "pevaluacion", "pevaluacion2020$");
                     System.out.println("database:" + databaseUser);
                     Connection conn =  DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
                     SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.ENVIORESPUESTAS_2 ?, ? ").unwrap(SQLServerCallableStatement.class);
                     pStmt.setStructured(1, "dbo.ENVIORESPUESTAS_2_TABLE", sourceDataTable); 
                     pStmt.registerOutParameter(2, java.sql.Types.VARCHAR);  

                     pStmt.execute();    
                     String SalidaMensaje = pStmt.getNString(2);

                     //El procedimiento almacenado manda error
                     if(!"1".equals(SalidaMensaje)){
                         conn.close();
                         throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
                     }
                     conn.close();
                     return SalidaMensaje;

		} catch (Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

	}



                

            
}
