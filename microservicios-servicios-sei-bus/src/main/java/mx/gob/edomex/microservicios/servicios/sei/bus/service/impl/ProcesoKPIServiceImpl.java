package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.transform.ToListResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.EvaluacionKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.HistorialKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.InstruccionesKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.PreguntasKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ProcesoVigenteKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.RespuestasKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionEvaluacionKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionPreguntasKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.InstruccionesKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PreguntasKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ProcesoKPIVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.RespuestasKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.RespuestasEncuestaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ProcesoKPIService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ProcesoKPIServiceImpl implements ProcesoKPIService {
	private static final Logger LOG = LoggerFactory.getLogger(ProcesoKPIServiceImpl.class);

	@Autowired
	private ProcesoVigenteKPIDAO procesoVigenteKPIDAO;
	@Autowired
	private HistorialKPIDAO historialKPIDAO;
	@Autowired
	private InstruccionesKPIDAO instruccionesKPIDAO;
	@Autowired
	private EvaluacionKPIDAO evaluacionKPIDAO;
	@Autowired
	private PreguntasKPIDAO preguntasKPIDAO;
	@Autowired
	private RespuestasKPIDAO respuestasKPIDAO;

        @Value("${edomex.database.evaluacion.url}")
	private String urlDataSource;
        @Value("${edomex.database.evaluacion.user}")
	private String databaseUser;
        @Value("${edomex.database.evaluacion.pass}")
	private String databasePass;
        
	@Override
	public ProcesoKPIVigente consultarProcesoVigente(String IdServidorPublico) throws BusException {
		ProcesoKPIVigente procesoVig = new ProcesoKPIVigente();
		Utils utils = new Utils();
		try {
			List<Object[]> resul = procesoVigenteKPIDAO.consultarProcesoVigente(IdServidorPublico);
                            procesoVig.setCLAVEPROCESOEVALUACIONKPI(utils.objectIsNULL(resul.get(0)[0]));
                            procesoVig.setDESCRIPCIONPROCESOKPIVIGENTE(utils.objectIsNULL(resul.get(0)[4]));
                            procesoVig.setFECHAFINPROCESOGENERAL(utils.objectIsNULL(resul.get(0)[3]));
                            procesoVig.setFECHAINICIOPROCESOGENERAL(utils.objectIsNULL(resul.get(0)[2]));
                            procesoVig.setIDPROCESOVIGE(utils.objectIsNULL(resul.get(0)[5]));
                            procesoVig.setNOMBREPROCESOEVALUACIONKPI(utils.objectIsNULL(resul.get(0)[1]));
		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return procesoVig;
	}

	@Override
	public List<HistorialKPI> consultarHiscoricoKPI(String IdServidorPublico) throws BusException {
		Utils utils = new Utils();
		List<HistorialKPI> lstHistorial = new ArrayList<>();
		try {
			List<Object[]> lstResul = historialKPIDAO.consultarHiscoricoKPI(IdServidorPublico);
			lstResul.forEach(e -> {
				HistorialKPI hist = new HistorialKPI();
				hist.setCLAVEPROCESOKPI(utils.objectIsNULL(e[1]));
				hist.setFECHAFINPROCESOKPI(utils.objectIsNULL(e[4]));
				hist.setFECHAINICIOPROCESOKPI(utils.objectIsNULL(e[3]));
				hist.setIDESTATUS(utils.objectIsNULL(e[5]));
				hist.setIDPROCESOEVALUACIONKPI(utils.objectIsNULL(e[0]));
				hist.setNOMBREPROCESOEVALUACION(utils.objectIsNULL(e[2]));
                                hist.setFechaEval(utils.objectIsNULL(e[6]));
				lstHistorial.add(hist);
			});

		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return lstHistorial;
	}

	@Override
	public List<InstruccionesKPI> consultarInstruccionesKPI(String IdProcesoVigente) throws BusException {
		Utils utils = new Utils();
		List<InstruccionesKPI> lstInstru = new ArrayList<>();
		try {
			List<Object[]> lstResul = instruccionesKPIDAO.consultarInstruccionesKPI(IdProcesoVigente);
			lstResul.forEach(e -> {
				InstruccionesKPI ins = new InstruccionesKPI();
				ins.setINSTRUCCIONES(utils.objectIsNULL(e[0]));
				ins.setTIEMPO(utils.objectIsNULL(e[2]));
				ins.setTIEMPOLIMITE(utils.objectIsNULL(e[1]));
				// utils.objectIsNULL(e[])
				lstInstru.add(ins);
			});

		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return lstInstru;
	}

	@Override
	public EjecucionEvaluacionKPI ejecucionEvaluacionKPI(String IdServidorPublico, String IdProcesoVigente)
			throws BusException {
		Utils utils = new Utils();
		EjecucionEvaluacionKPI evaluacion = new EjecucionEvaluacionKPI();
		try {
			List<Object[]> lstResulEva = evaluacionKPIDAO.consultarEvaluacionKPI(IdServidorPublico, IdProcesoVigente);
			List<Object[]> lstResulPreg = preguntasKPIDAO.consultarComisionesa(IdServidorPublico, IdProcesoVigente);
			List<Object[]> lstResulResp = respuestasKPIDAO.consultarComisionesa(IdServidorPublico, IdProcesoVigente);
			List<RespuestasKPI> lstRespf = new ArrayList<>();
			lstResulResp.forEach(e -> {
				RespuestasKPI resp = new RespuestasKPI();
				resp.setIDPREGUNTA(utils.objectIsNULL(e[1]));
				resp.setIDPROCESOVIGENTE(utils.objectIsNULL(e[0]));
				resp.setIDRESPUESTA(utils.objectIsNULL(e[2]));
				resp.setRESPUESTA(utils.objectIsNULL(e[3]));
				resp.setSIGUIENTEPREGUNTA(utils.objectIsNULL(e[4]));
				resp.setSIGUIENTERESPUESTA(utils.objectIsNULL(e[5]));
				lstRespf.add(resp);
			});
			evaluacion.setIDPROCESOKPI(utils.objectIsNULL(lstResulEva.get(0)[1]));
			evaluacion.setIDPROCESOVIGENTE(utils.objectIsNULL(lstResulEva.get(0)[0]));
			evaluacion.setIDSERVIDORPUBLICO(utils.objectIsNULL(lstResulEva.get(0)[3]));
			evaluacion.setNOMBREPROCESOKPI(utils.objectIsNULL(lstResulEva.get(0)[2]));
			evaluacion.setNOMBRESERVIDORPUBLICO(utils.objectIsNULL(lstResulEva.get(0)[4]));
			evaluacion.setPREGUNTASKPI(new ArrayList<>());
			lstResulPreg.forEach(e -> {
				EjecucionPreguntasKPI preg = new EjecucionPreguntasKPI();
				preg.setDESCRIPCION(utils.objectIsNULL(e[2]));
				preg.setIDPREGUNTA(utils.objectIsNULL(e[1]));
				preg.setIDPROCESOVIGENTE(utils.objectIsNULL(e[0]));
                                BigDecimal maxRespuestas = new BigDecimal(utils.objectIsNULL(e[3]));
                                preg.setMAXRESPUESTA(maxRespuestas.intValue());
				preg.setRESPUESTASKPI(new ArrayList<>());
				List<RespuestasKPI> optResp = lstRespf.stream()
						.filter(ed -> ed.getIDPREGUNTA().equals(preg.getIDPREGUNTA())).collect(Collectors.toList());
				preg.getRESPUESTASKPI().addAll(optResp);
				evaluacion.getPREGUNTASKPI().add(preg);

			});

		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return evaluacion;
	}
        
        	public String envioRespuestasEncuesta(List<RespuestasEncuestaDTO> respuestasEncuesta) throws BusException {

		try {
                    SQLServerDataTable sourceDataTable = new SQLServerDataTable();
                    // Define metadata for the data table.  
                    sourceDataTable.addColumnMetadata("TIEMPO" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDPREGUNTA" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDRESPUESTA" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDSEVIDORPUBLICO" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDEVALUACIONKPI" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("IDFORMULARIO" ,java.sql.Types.NVARCHAR);

                    for(RespuestasEncuestaDTO respuesta : respuestasEncuesta) {                           
                        // Llevar tabla  
                        sourceDataTable.addRow(
                                respuesta.getTiempo(),
                                respuesta.getIdPregunta(),
                                respuesta.getIdRespuesta(),
                                respuesta.getIdServidorPublico(),
                                respuesta.getIdProcesoVigente());
                    }

                     System.out.println("database:" + databaseUser);
                     Connection conn =  DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
                     SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.ENVIORESPUESTASKPI ?, ? ").unwrap(SQLServerCallableStatement.class);
                     pStmt.setStructured(1, "dbo.ENVIORESPUESTASKPI_TABLE", sourceDataTable); 
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
