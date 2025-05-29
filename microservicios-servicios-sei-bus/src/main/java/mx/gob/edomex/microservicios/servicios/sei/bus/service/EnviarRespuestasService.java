package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ItemEnvioRespuestasDTO;

public interface EnviarRespuestasService {

	void enviarRespuestas(String Idpregunta, String Idrespuesta, String Idevaluacion, String Idsevidorpublicoevaluado,
			String Idsevidorpublicoevaluador, String Idunidadadministrativa, String Tiposeccion, String Puntajeobtenido)
			throws BusException;
        
        
        
        String envioRespuestasEvaluacionDesempenio(List<ItemEnvioRespuestasDTO> competenciasaptitudinales,
                                                 List<ItemEnvioRespuestasDTO> competenciassociopersonales,
                                                 List<ItemEnvioRespuestasDTO> demeritos,
                                                 String tipoEnvio)
                throws BusException;



}
