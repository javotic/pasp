
package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.TipoCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas.ConstanciaNoAdeudoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas.IncidenciasTiempoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ConsultasService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.HistorialLaboralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author smartinez
 */
@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    
    @Autowired
    private ConsultasService consultasService;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarIncidenciasTiempo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<IncidenciasTiempoModel>> getIncidenciasTiempo(
            @RequestParam("IDSERVIDORPUBLICO") String idServidorPublico,
            @RequestParam("FECHAINICIO") String fechaInicio,
            @RequestParam("FECHAFIN") String fechaFin
    ) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            consultasService.getIncidenciasTiempo(idServidorPublico, fechaInicio, fechaFin));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarHistoricoConstanciaNoAdeudo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<ConstanciaNoAdeudoModel>> getIncidenciasTiempo(
            @RequestParam("IDSERVIDORPUBLICO") String idServidorPublico
    )throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            consultasService.getHistoricoConstanciaNoAdeudo(idServidorPublico));
    }
}
