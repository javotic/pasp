package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestas;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestasDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ItemEnvioRespuestasDTO;
//import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestasDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EnviarRespuestasService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/enviarrespuestasedd")
public class EnviarRespuestasController {
	@Autowired
	private EnviarRespuestasService enviarRespuestasService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public BusResponse<?> consultarHistorialEvaluacionKPI(@RequestBody EnvioRespuestas envioRespuestas)
			throws BusException {
		try {
			enviarRespuestasService.enviarRespuestas(envioRespuestas.getIDPREGUNTA(), envioRespuestas.getIDRESPUESTA(),
					envioRespuestas.getIDEVALUACION(), envioRespuestas.getIDSEVIDORPUBLICOEVALUADO(),
					envioRespuestas.getIDSEVIDORPUBLICOEVALUADOR(), envioRespuestas.getIDUNIDADADMINISTRATIVA(),
					envioRespuestas.getTIPOSECCION(), envioRespuestas.getPUNTAJEOBTENIDO());
			return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK");
		} catch (Exception e) {
			return new BusResponse<>("error", String.valueOf(HttpStatus.BAD_REQUEST), "Fail",e);
		}

	}
        
        
        @ResponseStatus(HttpStatus.OK)
        @RequestMapping(value = "/envioRespuestasEvaluacionDesempenio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BusResponse<?> envioRespuestasEvaluacionDesempenio(@RequestBody EnvioRespuestasDTO respuestas
                                                                   )
                throws BusException {
		try {
                    
			String status = enviarRespuestasService.envioRespuestasEvaluacionDesempenio(respuestas.getCOMPETENCIASAPTITUDINALES(),
                                                                 respuestas.getCOMPETENCIASSOCIOPERSONALES(),
                                                                  respuestas.getDEMERITOS(),
                                                                  respuestas.getEVCONCLUIDA());
			return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", status);
		} catch (Exception e) {
			return new BusResponse<>("error", String.valueOf(HttpStatus.BAD_REQUEST), "Fail",e);
		}

	}

               
}
