package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonEstadoInscripcion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonTipoCandidatoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EscalafonGeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

//@CrossOrigin({ "http://localhost:4200" })
@RestController
@RequestMapping("/escalafonGeneral")
public class EscalafonGeneralController {
	
	@Autowired
	private EscalafonGeneralService service;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonDatosCandidato/{claveServidorPublico}/{idPlaza}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonDatosCandidato>> 
	escalafonDatosCandidato(@PathVariable("claveServidorPublico") String claveServidorPublico,
			@PathVariable("idPlaza") String idPlaza) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonDatosCandidato(claveServidorPublico,idPlaza));
	}
        
        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonCapacCursos/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonCursosDTO>> 
	escalafonEstadoInscripcion(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonEstadoInscripcion(claveServidorPublico,idProcesoVigente));
        }
        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonCapacCertifComp/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonCertificadosDTO>> 
	escalafonCapacCertifComp(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonCapacCertifComp(claveServidorPublico,idProcesoVigente));
        }
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonDiplomados/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonDiplomadosDTO>> 
	escalafonDiplomados(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonDiplomados(claveServidorPublico,idProcesoVigente));
        }        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonEscolaridad/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonEscolaridadDTO>> 
	escalafonEscolaridad(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonEscolaridad(claveServidorPublico,idProcesoVigente));
        }        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonEficiencia/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonEficienciaDTO>> 
	escalafonEficiencia(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonEficiencia(claveServidorPublico,idProcesoVigente));
        }            
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonAntiguedad/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonAntiguedadDTO>> 
	escalafonAntiguedad(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonAntiguedad(claveServidorPublico,idProcesoVigente));
        }   
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonDatosRecepDoc/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonDatosRecepDocDTO>> 
	escalafonDatosRecepDoc(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonDatosRecepDoc(claveServidorPublico,idProcesoVigente));
        }   
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonDemeritos/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonEficienciaDTO>> 
	escalafonDemeritos(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonDemeritos(claveServidorPublico,idProcesoVigente));
        }      
        
      @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonCapacInduccion/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonCapacInduccion>> 
	escalafonCapacInduccion(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonCapacInduccion(claveServidorPublico,idProcesoVigente));
        }    
        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonTipoCandidato/{claveServidorPublico}/{idPlaza}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<EscalafonTipoCandidatoDTO> 
	escalafonTipoCandidato(@PathVariable("claveServidorPublico") String claveServidorPublico, @PathVariable("idPlaza") String idPlaza,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonTipoCandidato(claveServidorPublico,idProcesoVigente, idPlaza));
        }    
        
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonCartaAceptacion/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonCartaAceptacionDTO>> 
	escalafonCartaAceptacion(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonCartaAceptacion(claveServidorPublico,idProcesoVigente));
        }    
        
         
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonAceptaPlaza/{idServidorPublico}/{estatus}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<Integer> 
	escalafonAceptaPlaza(@PathVariable("idServidorPublico") String idServidorPublico, @PathVariable("estatus") boolean estatus, @RequestParam String idProcesoVigente) throws BusException{
            return new BusResponse<>("success", String.valueOf(HttpStatus.OK), "success",
				service.escalafonAceptaPlaza(idServidorPublico,idProcesoVigente, estatus));
        }    
        
        @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "escalafonDemeritosTotales/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<List<EscalafonEficienciaDTO>> 
	escalafonDemeritosTotales(@PathVariable("claveServidorPublico") String claveServidorPublico,
                                   @RequestParam String idProcesoVigente) throws BusException {
		return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				service.escalafonDemeritosTotales(claveServidorPublico,idProcesoVigente));
        }
       
}
