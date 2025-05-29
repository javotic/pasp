package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface EscalafonGeneralService {
	
	List<EscalafonDatosCandidato> escalafonDatosCandidato(String claveServidorPublico,
			String idPlaza) throws BusException;
        
        
        List<EscalafonCursosDTO> escalafonEstadoInscripcion(String claveServidorPublico,
                String idProcesoVigente) throws BusException;
        
        List<EscalafonCertificadosDTO> escalafonCapacCertifComp(String claveServidorPublico,
        String idProcesoVigente) throws BusException;
        
        List<EscalafonDiplomadosDTO> escalafonDiplomados(String claveServidorPublico,
        String idProcesoVigente) throws BusException;       
        
        List<EscalafonEscolaridadDTO> escalafonEscolaridad(String claveServidorPublico,
        String idProcesoVigente) throws BusException; 
        
        List<EscalafonEficienciaDTO> escalafonEficiencia(String claveServidorPublico,
        String idProcesoVigente) throws BusException;         
                
        List<EscalafonAntiguedadDTO> escalafonAntiguedad(String claveServidorPublico,
        String idProcesoVigente) throws BusException;    
               
        List<EscalafonDatosRecepDocDTO> escalafonDatosRecepDoc(String claveServidorPublico,
        String idProcesoVigente) throws BusException;   
 
        List<EscalafonEficienciaDTO> escalafonDemeritos(String claveServidorPublico,
        String idProcesoVigente) throws BusException;   

        List<EscalafonCapacInduccion> escalafonCapacInduccion(String claveServidorPublico,
        String idProcesoVigente) throws BusException;           
        
        EscalafonTipoCandidatoDTO escalafonTipoCandidato(String claveServidorPublico,
        String idProcesoVigente, String idPlaza) throws BusException;     
        
        List<EscalafonCartaAceptacionDTO> escalafonCartaAceptacion(String claveServidorPublico,
        String idProcesoVigente) throws BusException;    
       
        Integer escalafonAceptaPlaza(String idServidorPublico, String idProcesoVigente, boolean estatus) throws BusException;  
       
        List<EscalafonEficienciaDTO> escalafonDemeritosTotales(String claveServidorPublico, String idProcesoVigente) throws BusException;          
}
