package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.PreguntasEjecucionDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.SeccionEvaluacionDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.CatSeccionesEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConsultarInstruccionesLlenadoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConsultarRespuestasCompetencias;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EjecucionEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PreguntasEjecucion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SeccionesEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EjecucionEddService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class EjecucionEddServiceImpl implements EjecucionEddService {

    private static final Logger LOG = LoggerFactory.getLogger(EjecucionEddServiceImpl.class);

    @Autowired
    private PreguntasEjecucionDAO preguntasEjecucionDAO;

    @Autowired
    private SeccionEvaluacionDAO seccionEvaluacionDAO;

    @Override
    public List<SeccionesEvaluacion> getSeccionesEvaluacion(String procesoVig, String evaluador, String auxiliar,
            String tipoRespo, String idEvaluado) throws BusException {
        List<SeccionesEvaluacion> lstSecciones = new ArrayList<>();
        Utils utils = new Utils();
        try {
            SeccionesEvaluacion secciones = new SeccionesEvaluacion();
            List<Object[]> data = seccionEvaluacionDAO.consultarSeccionesEDD(procesoVig, evaluador, auxiliar,
                    tipoRespo, idEvaluado);
            data.forEach(x -> {
                secciones.setIDPROCESOVIGENTE(utils.objectIsNULL(x[0]));
                secciones.setIDSECCION(utils.objectIsNULL(x[1]));
                secciones.setNSECCION(utils.objectIsNULL(x[2]));
                secciones.setDESCSECCION(utils.objectIsNULL(x[3]));
                secciones.setCOLORSECCION(utils.objectIsNULL(x[4]));
                lstSecciones.add(secciones);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return lstSecciones;
    }

    @Override
    public List<PreguntasEjecucion> getPreguntasEvaluacion(String procesoVig, String seccion) throws BusException {
        List<PreguntasEjecucion> lstPreguntas = new ArrayList<>();
        Utils utils = new Utils();
        try {

            List<Object[]> data = preguntasEjecucionDAO.consultarPreguntasEDD(procesoVig, seccion);
            data.forEach(x -> {
                PreguntasEjecucion preguntas = new PreguntasEjecucion();
                preguntas.setIDPROCESOVIGENTE(utils.objectIsNULL(x[0]));
                preguntas.setIDSECCION(utils.objectIsNULL(x[1]));
                preguntas.setIDPREGUNTA(utils.objectIsNULL(x[2]));
                preguntas.setDESCRIPCIONPREGUNTA(utils.objectIsNULL(x[3]));
                preguntas.setTIPOCAMPO(utils.objectIsNULL(x[4]));
                preguntas.setPUNTOS(utils.objectIsNULL(x[5]));
                lstPreguntas.add(preguntas);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return lstPreguntas;
    }

    @Override
    public List<EjecucionEvaluacion> getEvaluacion(String procesoVig, String evaluador, String auxiliar,
            String tipoResp, String idEvaluado) throws BusException {

        List<EjecucionEvaluacion> lstSecciones = new ArrayList<>();
        Utils utils = new Utils();
        try {
            if (idEvaluado == "0") {
                idEvaluado = "";
            }
            List<Object[]> data = seccionEvaluacionDAO.consultarSeccionesEDD(procesoVig, evaluador, auxiliar, tipoResp, idEvaluado);
            LOG.info("consultarSeccionesEDD");
            data.forEach(x -> {
                EjecucionEvaluacion secciones = new EjecucionEvaluacion();
                secciones.setIDPROCESOVIGENTE(utils.objectIsNULL(x[0]));
                secciones.setIDSECCION(utils.objectIsNULL(x[1]));
                secciones.setNSECCION(utils.objectIsNULL(x[2]));
                secciones.setDESCSECCION(utils.objectIsNULL(x[3]));
                secciones.setCOLORSECCION(utils.objectIsNULL(x[4]));
                secciones.setPreguntasEjecucion(new ArrayList<>());
                List<Object[]> datap = preguntasEjecucionDAO.consultarPreguntasEDD(procesoVig,
                        secciones.getIDSECCION());
                datap.forEach(y -> {
                    PreguntasEjecucion preguntas = new PreguntasEjecucion();
                    preguntas.setIDPROCESOVIGENTE(utils.objectIsNULL(y[0]));
                    preguntas.setIDSECCION(utils.objectIsNULL(y[1]));
                    preguntas.setIDPREGUNTA(utils.objectIsNULL(y[2]));
                    preguntas.setDESCRIPCIONPREGUNTA(utils.objectIsNULL(y[3]));
                    preguntas.setTIPOCAMPO(utils.objectIsNULL(y[4]));
                    preguntas.setPUNTOS(utils.objectIsNULL(y[5]));
                    secciones.getPreguntasEjecucion().add(preguntas);
                });
                lstSecciones.add(secciones);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return lstSecciones;
    }

    @Override
    public List<ConsultarRespuestasCompetencias> getconsultarRespuestasCompetencias(String idProcesoActivo) throws BusException {
        List<ConsultarRespuestasCompetencias> lstRespuestas = new ArrayList<>();
        Utils utils = new Utils();
        try {
            List<Object[]> data = seccionEvaluacionDAO.getconsultarRespuestasCompetencias(idProcesoActivo);
            data.forEach(x -> {
                ConsultarRespuestasCompetencias respuestas = new ConsultarRespuestasCompetencias();

                respuestas.setIDRESPUESTA(utils.objectIsNULL(x[0]));
                respuestas.setRESPUESTA(utils.objectIsNULL(x[1]));
                respuestas.setPUNTAJE(utils.objectIsNULL(x[2]));
                lstRespuestas.add(respuestas);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return lstRespuestas;
    }

    @Override
    public List<ConsultarInstruccionesLlenadoDTO> consultarInstruccionesllenadoEDD(String idProcesoActivo) throws BusException {
        List<ConsultarInstruccionesLlenadoDTO> lstRespuestas = new ArrayList<>();
        Utils utils = new Utils();
        try {
            List<Object[]> data = seccionEvaluacionDAO.consultarInstruccionesllenadoEDD(idProcesoActivo);
            data.forEach(x -> {
                ConsultarInstruccionesLlenadoDTO respuestas = new ConsultarInstruccionesLlenadoDTO();
                respuestas.setIDSECCION(utils.objectIsNULL(x[0]));
                respuestas.setVALOR(utils.objectIsNULL(x[1]));
                respuestas.setDESCRIPCIONVALOR(utils.objectIsNULL(x[2]));
                lstRespuestas.add(respuestas);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return lstRespuestas;
    }

    @Override
    public List<CatSeccionesEvaluacion> getseccionesEjecucionEvaluacionDesempenio(String idProcesoActivo) throws BusException {
        List<CatSeccionesEvaluacion> lstRespuestas = new ArrayList<>();
        Utils utils = new Utils();
        try {
            List<Object[]> data = seccionEvaluacionDAO.getseccionesEjecucionEvaluacionDesempenio(idProcesoActivo);
            data.forEach(x -> {
                CatSeccionesEvaluacion respuestas = new CatSeccionesEvaluacion();

                respuestas.setIDPROCESOVIGENTE(utils.objectIsNULL(x[0]));
                respuestas.setIDSECCION(utils.objectIsNULL(x[1]));
                respuestas.setNOMBRESECCION(utils.objectIsNULL(x[2]));
                lstRespuestas.add(respuestas);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return lstRespuestas;
    }

}
