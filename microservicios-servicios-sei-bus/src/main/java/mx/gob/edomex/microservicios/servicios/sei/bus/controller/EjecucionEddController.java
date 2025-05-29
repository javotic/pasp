package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.CatSeccionesEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConsultarInstruccionesLlenadoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConsultarRespuestasCompetencias;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PreguntasEjecucion;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EjecucionEddService;

@RestController
@RequestMapping("/ejecucionEDD")
public class EjecucionEddController {

    @Autowired
    private EjecucionEddService ejecucionEddService;
    @Autowired
    private ApplicationContext contex;

    // String procesoVig, String evaluador, String unidad
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "procesoVig/{procesoVig}/evaluador/{evaluador}/auxiliar/{auxiliar}/tipoResp/{tipoRespo}/idEvaluado/{idEvaluado}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<EjecucionEvaluacion>> consultarEjecucuion(@PathVariable("procesoVig") String procesoVig,
            @PathVariable("evaluador") String evaluador, @PathVariable("auxiliar") String auxiliar, @PathVariable("tipoRespo") String tipoRespo, @PathVariable("idEvaluado") String idEvaluado) throws BusException {

        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                ejecucionEddService.getEvaluacion(procesoVig, evaluador, auxiliar, tipoRespo, idEvaluado));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "infoTomcatSpringboot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<Object> consultarInfoTomcat() throws BusException {

        TomcatServletWebServerFactory wsf = contex.getBean(TomcatServletWebServerFactory.class);
        ServerProperties prp = contex.getBean(ServerProperties.class);

        System.out.println("MaxConnections: " + prp.getTomcat().getMaxConnections());
        System.out.println("MaxSwallowSize:" + prp.getTomcat().getMaxSwallowSize());
        System.out.println("MaxThreads:" + prp.getTomcat().getMaxThreads());
        System.out.println("MaxThreads:" + prp.getTomcat().getMaxThreads());
        System.out.println("ProcessorCache:" + prp.getTomcat().getProcessorCache());
        System.out.println("MinSpareThreads:" + prp.getTomcat().getMinSpareThreads());

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(prp.getTomcat());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", jsonInString);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "preguntasEjecucionED/{idProceso}/{idSeccion}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<PreguntasEjecucion>> preguntasEjecucionED(@PathVariable("idProceso") String idProceso,
            @PathVariable("idSeccion") String idSeccion
    ) throws BusException {

        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                ejecucionEddService.getPreguntasEvaluacion(idProceso, idSeccion));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarRespuestasCompetencias/{idProcesoActivo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<ConsultarRespuestasCompetencias>> getconsultarRespuestasCompetencias(@PathVariable("idProcesoActivo") String idProcesoActivo
    ) throws BusException {

        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                ejecucionEddService.getconsultarRespuestasCompetencias(idProcesoActivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarInstruccionesllenadoEDD/{idProcesoActivo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<ConsultarInstruccionesLlenadoDTO>> consultarInstruccionesllenadoEDD(
            @PathVariable("idProcesoActivo") String idProcesoActivo
    ) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                ejecucionEddService.consultarInstruccionesllenadoEDD(idProcesoActivo));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "seccionesEjecucionEvaluacionDesempenio/{idProcesoActivo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<CatSeccionesEvaluacion>> getseccionesEjecucionEvaluacionDesempenio(@PathVariable("idProcesoActivo") String idProcesoActivo
    ) throws BusException {

        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                ejecucionEddService.getseccionesEjecucionEvaluacionDesempenio(idProcesoActivo));
    }

}
