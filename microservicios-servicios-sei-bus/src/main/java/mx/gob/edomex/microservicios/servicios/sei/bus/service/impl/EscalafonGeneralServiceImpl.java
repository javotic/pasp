package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.EscalafonGeneralDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonAntiguedadDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonCapacInduccion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonCartaAceptacionDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonCertificadosDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonCursosDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonDatosCandidato;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonDatosRecepDocDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonDiplomadosDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonEficienciaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonEscolaridadDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonTipoCandidatoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EscalafonGeneralService;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EscalafonGeneralServiceImpl implements EscalafonGeneralService {

	@Autowired
	private EscalafonGeneralDAO dao;
        
        @Value("${edomex.database.evaluacion.url}")
	private String urlDataSource;
        @Value("${edomex.database.evaluacion.user}")
	private String databaseUser;
        @Value("${edomex.database.evaluacion.pass}")
	private String databasePass;        

	@Override
	public List<EscalafonDatosCandidato> escalafonDatosCandidato(String claveServidorPublico, String idPlaza) throws BusException {
		List<EscalafonDatosCandidato> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonDatosCandidato(claveServidorPublico, idPlaza);
			data.forEach(x -> {
				EscalafonDatosCandidato cr = new EscalafonDatosCandidato();
				cr.setNombreCompletoSP(Objects.toString(x[0], ""));
				cr.setClaveSP(Objects.toString(x[1], ""));
				cr.setPuestoNominal(Objects.toString(x[2], ""));
				cr.setCategoria(Objects.toString(x[3], ""));
				cr.setAdscripcion(Objects.toString(x[4], ""));
				cr.setSecretaria(Objects.toString(x[5], ""));
				cr.setIdProcesoVigente(Objects.toString(x[6], ""));
				cr.setNumPlaza(Objects.toString(x[7], ""));
				cr.setPuestoConcursa(x[8]!=null?x[8].toString():"");
				cr.setCategoriaPlazaConcursa(x[9]!=null?x[9].toString():"");
				cr.setAdscripcionPlazaConcursa(x[10]!=null?x[10].toString():"");
				cr.setSecretariaPlazaConcursa(x[11]!=null?x[11].toString():"");
				cr.setNombreRepresentanteEscalafon(x[12]!=null?x[12].toString():"");
				
				cr.setPuestoRepresentanteEscalafon(x[13]!=null?x[13].toString():"");
				cr.setNumPlazaActual((x[14]!=null?x[14].toString():""));
                                
                                cr.setFirmaDerecha((x[15]!=null?x[15].toString():""));
                                cr.setFirmaCentral((x[16]!=null?x[16].toString():""));
                                cr.setFirmaIzquierda((x[17]!=null?x[17].toString():""));
				cr.setIdprocSecretaria((x[18]!=null?x[18].toString():""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}
        
        
        
        @Override
	public List<EscalafonCursosDTO> escalafonEstadoInscripcion(String claveServidorPublico, String idProcesoVigente) throws BusException {
		List<EscalafonCursosDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonEstadoInscripcion(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				EscalafonCursosDTO cr = new EscalafonCursosDTO();
				cr.setNombreCurso(Objects.toString(x[0], ""));
				cr.setFechaCurso(Objects.toString(x[1], ""));
				cr.setHoras(Objects.toString(x[2], ""));
				cr.setValidado(Objects.toString(x[3], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}
       
        @Override
	public  List<EscalafonCertificadosDTO> escalafonCapacCertifComp(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonCertificadosDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonCapacCertifComp(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				EscalafonCertificadosDTO cr = new EscalafonCertificadosDTO();
				cr.setNombreCertificado(Objects.toString(x[0], ""));
				cr.setFechaCurso(Objects.toString(x[1], ""));
				cr.setValidado(Objects.toString(x[2], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}
        
        @Override
	public  List<EscalafonDiplomadosDTO> escalafonDiplomados(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonDiplomadosDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonDiplomados(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				EscalafonDiplomadosDTO cr = new EscalafonDiplomadosDTO();
				cr.setNombreDiplomado(Objects.toString(x[0], ""));
				cr.setFechaDiplomado(Objects.toString(x[1], ""));
				cr.setDuracion(Objects.toString(x[2], ""));
                                cr.setTipoDuracion(Objects.toString(x[3], ""));
                                cr.setValidado(Objects.toString(x[4], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}        
        
        
        @Override
	public  List<EscalafonEscolaridadDTO> escalafonEscolaridad(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonEscolaridadDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonEscolaridad(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				EscalafonEscolaridadDTO cr = new EscalafonEscolaridadDTO();
                                cr.setGradoEscolaridad(Objects.toString(x[0], ""));
                                cr.setNivelEscolaridad(Objects.toString(x[1], ""));
                                cr.setEscolaridad(Objects.toString(x[2], ""));
                                cr.setPuntosEscolaridad(Objects.toString(x[3], ""));
                                cr.setValidado(Objects.toString(x[4], ""));
                                cr.setIdNivelEscolaridad(Objects.toString(x[5], ""));

				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}     
        
        @Override
	public  List<EscalafonEficienciaDTO> escalafonEficiencia(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonEficienciaDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonEficiencia(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonEficienciaDTO cr = new EscalafonEficienciaDTO();
                                cr.setAnio(Objects.toString(x[0], ""));
                                cr.setSemestre(Objects.toString(x[1], ""));
                                cr.setResultado(Objects.toString(x[2], ""));

				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}             
        
                @Override
	public  List<EscalafonAntiguedadDTO> escalafonAntiguedad(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonAntiguedadDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonAntiguedad(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonAntiguedadDTO cr = new EscalafonAntiguedadDTO();
                                cr.setAntiguedad(Objects.toString(x[0], ""));
                                cr.setRangoAntiguedad(Objects.toString(x[1], ""));
                                cr.setPuntajeAntiguedad(Objects.toString(x[2], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}     
      
        @Override
        public List<EscalafonDatosRecepDocDTO> escalafonDatosRecepDoc(String claveServidorPublico,
        String idProcesoVigente) throws BusException {
		List<EscalafonDatosRecepDocDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonDatosRecepDoc(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonDatosRecepDocDTO cr = new EscalafonDatosRecepDocDTO();
                                cr.setInduccionSP(Objects.toString(x[0], ""));
                                cr.setPuntosCursos(Objects.toString(x[1], ""));
                                cr.setPuntosCertificados(Objects.toString(x[2], ""));
                                cr.setPuntosDiplomados(Objects.toString(x[3], ""));
                                cr.setPuntosEficiencia(Objects.toString(x[4], ""));
                                cr.setPuntosDemeritos(Objects.toString(x[5], ""));
                                cr.setPuntajeTotal(Objects.toString(x[6], ""));
                                cr.setTextoResultado(Objects.toString(x[7], ""));
                                cr.setTextoCandidato(Objects.toString(x[8], ""));
                                cr.setTextoMotivo(Objects.toString(x[9], ""));
                                cr.setTextoAcreedor(Objects.toString(x[10], ""));
                                cr.setPuntajeSubTotal(Objects.toString(x[11], ""));
                                cr.setNregistro(Objects.toString(x[12], ""));

				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}     
        
        @Override
	public  List<EscalafonEficienciaDTO> escalafonDemeritos(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonEficienciaDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonDemeritos(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonEficienciaDTO cr = new EscalafonEficienciaDTO();
                                cr.setAnio(Objects.toString(x[0], ""));
                                cr.setSemestre(Objects.toString(x[1], ""));
                                cr.setResultado(Objects.toString(x[2], ""));

				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}  
        
        @Override
	public  List<EscalafonCapacInduccion> escalafonCapacInduccion(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonCapacInduccion> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonCapacInduccion(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonCapacInduccion cr = new EscalafonCapacInduccion();
                                cr.setInduccionSP(Objects.toString(x[0], ""));
                                cr.setValidado(Objects.toString(x[1], ""));

				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}  
        
        @Override
	public  EscalafonTipoCandidatoDTO escalafonTipoCandidato(String claveServidorPublico,
                String idProcesoVigente, String idPlaza) throws BusException {
		EscalafonTipoCandidatoDTO consultas = new EscalafonTipoCandidatoDTO();
		try {

			List<Object[]> data = dao.escalafonTipoCandidato(claveServidorPublico, idProcesoVigente, idPlaza);
                        //consultas.setTipoCandidato(Objects.toString(data.get(0).toString(), ""));
			data.forEach(x -> {
				
                                consultas.setTipoCandidato(Objects.toString(x[0], ""));
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}          

        @Override
	public List<EscalafonCartaAceptacionDTO> escalafonCartaAceptacion(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonCartaAceptacionDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonCartaAceptacion(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonCartaAceptacionDTO cr = new EscalafonCartaAceptacionDTO();
                                cr.setProceso(Objects.toString(x[0], ""));
                                cr.setnDictamen(Objects.toString(x[1], ""));
                                cr.setNcpdepe(Objects.toString(x[2], ""));
                                cr.setClaveServidor(Objects.toString(x[3], ""));
                                cr.setNombreServidor(Objects.toString(x[4], ""));
                                cr.setClaveplaza(Objects.toString(x[5], ""));
                                cr.setPuesto(Objects.toString(x[6], ""));
                                cr.setCategoria(Objects.toString(x[7], ""));
                                cr.setAdscripcion(Objects.toString(x[8], ""));
                                cr.setNombreGobernador(Objects.toString(x[9], ""));
                                cr.setFirma1(Objects.toString(x[10], ""));
                                cr.setFirma2(Objects.toString(x[11], ""));
                                cr.setFirma3(Objects.toString(x[12], ""));
                                cr.setFirma4(Objects.toString(x[13], ""));
                                cr.setFirma5(Objects.toString(x[14], ""));
                                cr.setFirma6(Objects.toString(x[15], ""));
                                cr.setFirma7(Objects.toString(x[16], ""));
                                cr.setFirma8(Objects.toString(x[17], ""));
                                cr.setParrafo1(Objects.toString(x[18], ""));
                                cr.setParrafo2(Objects.toString(x[19], ""));
                                cr.setFechaPublica(Objects.toString(x[20], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}  
        
        @Override
	public  Integer escalafonAceptaPlaza(String idServidorPublico, String idProcesoVigente, boolean estatus) throws BusException {
		Integer result = 0;
		try {

                    //data = dao.escalafonAceptaPlaza(idServidorPublico, idProcesoVigente, estatus);
                         
                     System.out.println("database:" + databaseUser);
                     Connection conn =  DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
                     SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.escalafonAceptaPlaza ?, ?, ?, ? ").unwrap(SQLServerCallableStatement.class);
                     pStmt.setString("idServidorPublico", idServidorPublico); 
                     pStmt.setString("idProceso", idProcesoVigente);
                     pStmt.setInt("estatus", (estatus?1:0));
                       
                     pStmt.registerOutParameter(4, java.sql.Types.BIT);  

                     pStmt.execute();    
                     result = pStmt.getInt(4);
                             
                     conn.close();
                         
                /*
                    @Modifying(clearAutomatically = true)
                    @Transactional
                    @Query(value = "DECLARE @res BIT \n "
                                   + "EXEC escalafonAceptaPlaza ?, ?, ?, @res OUTPUT \n", nativeQuery = true)
                    public Integer escalafonAceptaPlaza( String idServidorPublico, String idProcesoVigente, boolean estatus);   
               */
        
		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
                if("0".equals(result)){
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "ERROR interno en la fusión SQL.");
                }
		return result;
	}     
        
        @Override
	public  List<EscalafonEficienciaDTO> escalafonDemeritosTotales(String claveServidorPublico,
                String idProcesoVigente) throws BusException {
		List<EscalafonEficienciaDTO> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonDemeritosTotales(claveServidorPublico, idProcesoVigente);
			data.forEach(x -> {
				
				EscalafonEficienciaDTO cr = new EscalafonEficienciaDTO();
                                cr.setAnio(Objects.toString(x[0], ""));
                                cr.setSemestre(Objects.toString(x[1], ""));
                                cr.setResultado(Objects.toString(x[2], ""));

				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}          
        
        
}
