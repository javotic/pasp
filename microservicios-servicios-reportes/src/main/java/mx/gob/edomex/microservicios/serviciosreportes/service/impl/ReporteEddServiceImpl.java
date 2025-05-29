package mx.gob.edomex.microservicios.serviciosreportes.service.impl;

import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import java.util.Locale;
import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarDatosPuestoServidorPublico;
import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarInstruccionesLlenadoEDD;
import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarRespuestasCompetencias;
import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarSeccionesEDD;
import mx.gob.edomex.microservicios.serviciosreportes.models.PreguntasEjecucionEDD;
import mx.gob.edomex.microservicios.serviciosreportes.models.ResultadosPreguntasEdd;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ReponsePreguntasEjecucionED;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseConsultarDatosPServidorPublico;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseConsultarInstruccionesLlenadoEDD;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseConsultarSeccionesEDD;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseResultadosEdd;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResposeConsultarRespuestasCompetencias;
import mx.gob.edomex.microservicios.serviciosreportes.service.ReporteEddService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ReporteEddServiceImpl implements ReporteEddService {

    //<editor-fold desc="Campos privados" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(ReporteEddServiceImpl.class);
    @Value("${urlWsReportes}")
    private String urlReportes;
    @Value("${spring.profiles.active}")
    private String ambienteTrabajo;
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private RestTemplate restTemplate;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public byte[] getReporteEdd(String proceso, String servidorPublico) {
        return this.generarReporte(proceso, servidorPublico, "test", false);
    }

    @Override
    public byte[] getReporteEddHT(String proceso, String servidorPublico) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        LOG.info("Generar reporte");
        ConsultarDatosPuestoServidorPublico datosServidor = consultarDatosPuestoServidorPublicoHT(servidorPublico,
                proceso).getResponse();

        String[] status_encuestas = datosServidor.getEstatus().split("|");

        boolean is_demeritos_contestado = true;

        if ("0".equals(status_encuestas[2])) {
            is_demeritos_contestado = false;
        }

        List<ConsultarInstruccionesLlenadoEDD> instruccionesLLenado = consultarInstruccionesLlenadoEDD(proceso)
                .getResponse();

        for (ConsultarInstruccionesLlenadoEDD instruccion : instruccionesLLenado) {
            instruccion.setValor(WordUtils.capitalizeFully(instruccion.getValor()));
            if ("01".equals(instruccion.getIdSeccion()) || "02".equals(instruccion.getIdSeccion())) {
                instruccion.setDescripcionCriterioEvaluacion("Equivale al 50 por ciento de la calificación total.");
            } else {
                instruccion.setDescripcionCriterioEvaluacion("");
            }
        }

        List<ConsultarRespuestasCompetencias> respuestas = consultarRespuestasCompetencias(proceso).getResponse();

        // ResponseResultadosEdd resultadoedd = consultarResultadosCompetencias();
        List<ConsultarSeccionesEDD> lstSeccion = consultarSeccionesEDD(proceso).getResponse();
        List<PreguntasEjecucionEDD> competenciasAptitudinales = preguntasEjecucionED(proceso,
                lstSeccion.get(0).getIdSeccion()).getResponse();

        List<PreguntasEjecucionEDD> competenciasSocioPersonales = preguntasEjecucionED(proceso,
                lstSeccion.get(1).getIdSeccion()).getResponse();

        List<PreguntasEjecucionEDD> demeritos = preguntasEjecucionED(proceso, lstSeccion.get(2).getIdSeccion())
                .getResponse();

        ResponseResultadosEdd resultadoeddAPt = consultarResultadosCompetencias(servidorPublico,
                lstSeccion.get(0).getIdSeccion(), proceso);
        ResponseResultadosEdd resultadoeddSoc = consultarResultadosCompetencias(servidorPublico,
                lstSeccion.get(1).getIdSeccion(), proceso);

        LOG.info("DatosServidor {}", datosServidor.toString());
        LOG.info("Instrucciones {}", instruccionesLLenado.size());
        LOG.info("Aptitudinales {}", competenciasAptitudinales.size());
        LOG.info("Sociop {}", competenciasSocioPersonales.size());
        LOG.info("Demeritos {}", demeritos.size());
        LOG.info("Respuesta {}", respuestas.size());

        competenciasAptitudinales.forEach(esa -> {
            Optional<ResultadosPreguntasEdd> opApt = resultadoeddAPt.getResponse().getRESULTADOSPREGUNTASEDDDTO()
                    .stream().filter(e -> e.getIdpregunta().equals(esa.getIdPregunta())).findFirst();
            Optional<ConsultarRespuestasCompetencias> optRes = respuestas.stream()
                    .filter(e -> e.getIdRespuesta().equals(opApt.get().getIdrespuesta())).findFirst();
            esa.setPuntaje(optRes.get().getPuntaje());
            params.put("calificacionPuntos", resultadoeddAPt.getResponse().getCALIFICACIONPARCIAL());
            params.put("porcentajeap", resultadoeddAPt.getResponse().getTOTALPUNTOSOBTENIDOS());

        });
        LOG.info("valores Socipersonales");
        competenciasSocioPersonales.forEach(es -> {
            Optional<ResultadosPreguntasEdd> opApt = resultadoeddSoc.getResponse().getRESULTADOSPREGUNTASEDDDTO()
                    .stream().filter(e -> e.getIdpregunta().equals(es.getIdPregunta())).findFirst();
            Optional<ConsultarRespuestasCompetencias> optRes = respuestas.stream()
                    .filter(e -> e.getIdRespuesta().equals(opApt.get().getIdrespuesta())).findFirst();

            es.setPuntajeSocioPersonal(optRes.get().getPuntaje());

        });
        LOG.info("valores Demeritos");

        if (is_demeritos_contestado) {

            ResponseResultadosEdd resultadoeddDem = consultarResultadosCompetencias(servidorPublico,
                    lstSeccion.get(2).getIdSeccion(), proceso);

            demeritos.forEach(es -> {
                Optional<ResultadosPreguntasEdd> opApt = resultadoeddDem.getResponse().getRESULTADOSPREGUNTASEDDDTO()
                        .stream().filter(e -> e.getIdpregunta().equals(es.getIdPregunta())).findFirst();
                LOG.info("puntaje {}", opApt.get().getIdrespuesta());
                es.setPuntajeDemeritos(opApt.get().getIdrespuesta());
                es.setPuntajeSocioPersonal("009");

            });

            params.put("calificacionFinalDemeritos", resultadoeddDem.getResponse().getCALIFICACIONPARCIAL());
        }
        byte[] data = null;
        LOG.info("Mapa=>>");
        params.put("nombreServidor", datosServidor.getNombre() + " " + datosServidor.getPrimerApellido() + " "
                + datosServidor.getSegundoApellido());
        params.put("nombreEvaluador", datosServidor.getNombrespEvaluador() + " "
                + datosServidor.getPrimerApellidoPEvaluador() + " " + datosServidor.getSegundoApellidoPEvaluador());
        params.put("claveServidorPublico", datosServidor.getClaveServidorPublico());
        params.put("periodoEvaluacion", "DEL :" + stringToDate(datosServidor.getFechaUno()) + " AL : " + stringToDate(datosServidor.getFechaDos()));

        params.put("puesto", datosServidor.getNombrePuesto());
        params.put("fechaIngresoPuesto", stringToDate(datosServidor.getFechaIngresoPuesto()));
        params.put("noPLaza", datosServidor.getNumPlaza());

        params.put("codigoPuesto", datosServidor.getCodigoPuesto());
        params.put("fechaIngresoGem", stringToDate(datosServidor.getFechaFechaIngresoGem()));
        params.put("unidadAdministrativa", datosServidor.getClaveUnidadAdministrativa());

        params.put("dependencia", datosServidor.getDependencia());
        params.put("subsecretariaGeneral", datosServidor.getSubsecretariaGeneral());
        params.put("subsecretaria", datosServidor.getSubsecretaria());

        params.put("coordinacion", datosServidor.getDepartamento());
        params.put("direccionGeneral", datosServidor.getDireccionGeneral());
        params.put("direccionArea", datosServidor.getDireccionArea());

        params.put("subDireccion", datosServidor.getSubdireccion());
        params.put("departamento", datosServidor.getDepartamento());
        params.put("espacioOrganizacional", datosServidor.getEspacioOrganizacional() == null ? "" : datosServidor.getEspacioOrganizacional());

        params.put("instruccionesLLenadoDataset", new JRBeanCollectionDataSource(instruccionesLLenado));
        params.put("competenciasAptitudinalesDataset", new JRBeanCollectionDataSource(competenciasAptitudinales));
        params.put("competenciasSocioPersonalesDataset", new JRBeanCollectionDataSource(competenciasSocioPersonales));
        params.put("demeritosDataset", new JRBeanCollectionDataSource(demeritos));
        params.put("consultarRespuestasCompetenciasDataset", new JRBeanCollectionDataSource(respuestas));
        params.put("calificacionFinal", datosServidor.getCalificacionFinal());

        params.put("puntossoc", resultadoeddSoc.getResponse().getTOTALPUNTOSOBTENIDOS());
        params.put("porcentajesoc", resultadoeddSoc.getResponse().getCALIFICACIONPARCIAL());

        // porcentajeap
        System.out.println("");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date3 = formatter.parse(datosServidor.getFechaTres());
            Calendar ftres = Calendar.getInstance();
            ftres.setTime(date3);
            LOG.info("dia {}", ftres.get(Calendar.DAY_OF_MONTH));
            LOG.info("mes {}", ftres.get(Calendar.MONTH));
            LOG.info("año {}", ftres.get(Calendar.YEAR));

            Date date = formatter.parse(datosServidor.getFechaUno());
            Calendar funo = Calendar.getInstance();
            funo.setTime(date);
            LOG.info("dia {}", funo.get(Calendar.DAY_OF_MONTH));
            LOG.info("mes {}", funo.get(Calendar.MONTH));
            LOG.info("año {}", funo.get(Calendar.YEAR));

            params.put("dfu", ftres.get(Calendar.DAY_OF_MONTH) + "");
            params.put("mfu", (ftres.get(Calendar.MONTH) + 1) + "");
            params.put("yfu", ftres.get(Calendar.YEAR) + "");
            Date date2 = formatter.parse(datosServidor.getFechaDos());
            Calendar fdos = Calendar.getInstance();
            funo.setTime(date2);
            LOG.info("dia {}", funo.get(Calendar.DAY_OF_MONTH));
            LOG.info("mes {}", funo.get(Calendar.MONTH));
            LOG.info("año {}", funo.get(Calendar.YEAR));
            params.put("dfd", ftres.get(Calendar.DAY_OF_MONTH) + "");
            params.put("mfd", (ftres.get(Calendar.MONTH) + 1) + "");
            params.put("yfd", ftres.get(Calendar.YEAR) + "");
            params.put("visibledemeritos", is_demeritos_contestado);

            String pathImg1 = resourceLoader.getResource("classpath:images/pleca-EDD.png")
                    .getURI().getPath();
            params.put("imagenOne", pathImg1);
            String pathImg2 = resourceLoader.getResource("classpath:images/pleca-EDD-ABAJO.png")
                    .getURI().getPath();
            params.put("imageTwo", pathImg2);
            String pathImg3 = resourceLoader.getResource("classpath:images/LOGO_EDOMEX_VERTICAL.png").getURI()
                    .getPath();
            params.put("imageThree", pathImg3);

            //Local
            //String path = resourceLoader.getResource("classpath:reports/test.jrxml").getURI().getPath();
            //JasperReport jasperReport = JasperCompileManager.compileReport(path);
            // Servidor
            params.put("imagenOne", "/home/images/pleca-EDD.png");
            params.put("imageTwo", "/home/images/pleca-EDD-ABAJO.png");
            params.put("imageThree", "/home/images/LOGO_EDOMEX_VERTICAL.png");
            JasperReport jasperReport = JasperCompileManager.compileReport("/home/reports/test.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            data = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;

    }

    @Override
    public ReponsePreguntasEjecucionED preguntasEjecucionED(String idProceso, String idSeccion) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        LOG.info("preguntasEjecucionED");
        map.put("funcion", "preguntasEjecucionED");
        map.put("IDPROCESO", idProceso);
        map.put("IDSECCIONED", idSeccion);

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("request", map);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());

        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/ejecucionEDD/preguntasEjecucionED/"
                        + idProceso + "/" + idSeccion, String.class);

        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("preguntasEjecucionED=>", gson.toString());
        return gson.fromJson(account, ReponsePreguntasEjecucionED.class);

    }

    @Override
    public ResponseConsultarInstruccionesLlenadoEDD consultarInstruccionesLlenadoEDD(String idProcesoActivo) {
        LOG.info("consultarInstruccionesllenadoEDD");
        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/ejecucionEDD/consultarInstruccionesllenadoEDD/"
                        + idProcesoActivo, String.class);
        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("consultarInstruccionesllenadoEDD=>", gson.toString());
        return gson.fromJson(account, ResponseConsultarInstruccionesLlenadoEDD.class);

    }

    @Override
    public ResponseConsultarDatosPServidorPublico consultarDatosPuestoServidorPublico(String servidorPublico,
            String proceso) {
        LOG.info("consultarDatosPuestoServidorPublico");
        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/servidorpublico/datosPuesto/IdServidorPublico/"
                        + servidorPublico + "/proceso/" + proceso, String.class);
        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("consultarDatosPuestoServidorPublico=>", gson.toString());
        return gson.fromJson(account, ResponseConsultarDatosPServidorPublico.class);
    }

    @Override
    public ResposeConsultarRespuestasCompetencias consultarRespuestasCompetencias(String idProcesoActivo) {
        LOG.info("consultarRespuestasCompetencias");
        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/ejecucionEDD/consultarRespuestasCompetencias/"
                        + idProcesoActivo, String.class);

        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("consultarRespuestasCompetencias=>");
        return gson.fromJson(account, ResposeConsultarRespuestasCompetencias.class);
    }

    @Override
    public ResponseConsultarSeccionesEDD consultarSeccionesEDD(String proceso) {
        LOG.info("seccionesEjecucionEvaluacionDesempenio");
        /*
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("funcion", "seccionesEjecucionEvaluacionDesempenio");
		map.put("IDPROCESO", proceso);

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate
				.postForEntity("https://bus.edomex.gob.mx/bussrv/sei/frwsr_LPSAUT_CONS_DAT2.php", entity, String.class);
		String account = response.getBody();
		System.out.println(account);
		Gson gson = new Gson();
		LOG.info("seccionesEjecucionEvaluacionDesempenio=>", gson.toString());
		return gson.fromJson(account, ResponseConsultarSeccionesEDD.class);
         */
        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/ejecucionEDD/seccionesEjecucionEvaluacionDesempenio/" + proceso, String.class);

        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("seccionesEjecucionEvaluacionDesempenio=>", gson.toString());
        return gson.fromJson(account, ResponseConsultarSeccionesEDD.class);

    }

    @Override
    public ResponseResultadosEdd consultarResultadosCompetencias(String servidorPublico, String seccion,
            String proceso) {
        LOG.info("resultadosedd");
        HashMap<String, Object> map = new HashMap<String, Object>();

        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/resultadosedd/idServidorPublico/"
                        + servidorPublico + "/idSeccion/" + seccion + "/idProceso/" + proceso, String.class);

        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("resultadosedd=>", gson.toString());
        return gson.fromJson(account, ResponseResultadosEdd.class);
    }

    private String stringToDate(String stringDate) {
        String dateFinal = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            date = formatter.parse(stringDate);
            Calendar funo = Calendar.getInstance();
            funo.setTime(date);
            Locale locale = new Locale("es", "MX");

            Format formatterFinal = new SimpleDateFormat("dd-MMMM-yyyy", locale);
            dateFinal = formatterFinal.format(date);
        } catch (ParseException e) {
            LOG.error("Error {}", e);
        }

        return dateFinal.toUpperCase();
    }

    private ResponseConsultarDatosPServidorPublico consultarDatosPuestoServidorPublicoHT(String servidorPublico,
            String proceso) {
        LOG.info("consultarDatosPuestoServidorPublico");
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("funcion", "consultarDatosPuestoServidorPublico");
        map.put("IdServidorPublico", servidorPublico);
        map.put("proceso", proceso);

        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("request", map);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());

        //Produccion
        ResponseEntity<String> response = restTemplate
                .getForEntity(urlReportes + "/servidorpublico/consultarDatosPuestoHT/IdServidorPublico/"
                        + servidorPublico + "/proceso/" + proceso, String.class);

        String account = response.getBody();
        System.out.println(account);
        Gson gson = new Gson();
        LOG.info("consultarDatosPuestoServidorPublicoHT=>", gson.toString());
        return gson.fromJson(account, ResponseConsultarDatosPServidorPublico.class);

    }

    @Override
    public byte[] generarReporteEdddemeritos(String proceso, String claveServidorPublico) {
        return this.generarReporte(proceso, claveServidorPublico, "EvaluacionDesempeno_v2", true);
    }
    //</editor-fold>

    //<editor-fold desc="Funciones privadas" defaultstate="collapsed">
    private byte[] generarReporte(String proceso, String servidorPublico, String nombrePlantilla,
            boolean puntosNombre) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        LOG.info("Generar reporte");
        ConsultarDatosPuestoServidorPublico datosServidor = consultarDatosPuestoServidorPublico(servidorPublico,
                proceso).getResponse();

        String[] status_encuestas = datosServidor.getEstatus().split("|");

        boolean is_demeritos_contestado = true;

        if ("0".equals(status_encuestas[2])) {
            is_demeritos_contestado = false;
        }

        List<ConsultarInstruccionesLlenadoEDD> instruccionesLLenado
                = consultarInstruccionesLlenadoEDD(proceso).getResponse();

        for (ConsultarInstruccionesLlenadoEDD instruccion : instruccionesLLenado) {
            instruccion.setValor(WordUtils.capitalizeFully(instruccion.getValor()));
            if ("01".equals(instruccion.getIdSeccion()) || "02".equals(instruccion.getIdSeccion())) {
                instruccion.setDescripcionCriterioEvaluacion("Equivale al 50 por ciento de la calificación total.");
            } else {
                instruccion.setDescripcionCriterioEvaluacion("");
            }
        }

        List<ConsultarRespuestasCompetencias> respuestas = consultarRespuestasCompetencias(proceso).getResponse();

        // ResponseResultadosEdd resultadoedd = consultarResultadosCompetencias();
        List<ConsultarSeccionesEDD> lstSeccion = consultarSeccionesEDD(proceso).getResponse();
        List<PreguntasEjecucionEDD> competenciasAptitudinales = preguntasEjecucionED(proceso,
                lstSeccion.get(0).getIdSeccion()).getResponse();
        List<PreguntasEjecucionEDD> competenciasSocioPersonales = preguntasEjecucionED(proceso,
                lstSeccion.get(1).getIdSeccion()).getResponse();
        List<PreguntasEjecucionEDD> demeritos = preguntasEjecucionED(proceso, lstSeccion.get(2).getIdSeccion())
                .getResponse();

        ResponseResultadosEdd resultadoeddAPt = consultarResultadosCompetencias(servidorPublico,
                lstSeccion.get(0).getIdSeccion(), proceso);
        ResponseResultadosEdd resultadoeddSoc = consultarResultadosCompetencias(servidorPublico,
                lstSeccion.get(1).getIdSeccion(), proceso);

        LOG.info("DatosServidor {}", datosServidor.toString());
        LOG.info("Instrucciones {}", instruccionesLLenado.size());
        LOG.info("Aptitudinales {}", competenciasAptitudinales.size());
        LOG.info("Sociop {}", competenciasSocioPersonales.size());
        LOG.info("Demeritos {}", demeritos.size());
        LOG.info("Respuesta {}", respuestas.size());

        competenciasAptitudinales.forEach(esa -> {
            Optional<ResultadosPreguntasEdd> opApt = resultadoeddAPt.getResponse().getRESULTADOSPREGUNTASEDDDTO()
                    .stream().filter(e -> e.getIdpregunta().equals(esa.getIdPregunta())).findFirst();
            Optional<ConsultarRespuestasCompetencias> optRes = respuestas.stream()
                    .filter(e -> e.getIdRespuesta().equals(opApt.get().getIdrespuesta())).findFirst();
            esa.setPuntaje(optRes.get().getPuntaje());
            params.put("calificacionPuntos", resultadoeddAPt.getResponse().getCALIFICACIONPARCIAL());
            params.put("porcentajeap", resultadoeddAPt.getResponse().getTOTALPUNTOSOBTENIDOS());

        });
        LOG.info("valores Socipersonales");
        competenciasSocioPersonales.forEach(es -> {
            Optional<ResultadosPreguntasEdd> opApt = resultadoeddSoc.getResponse().getRESULTADOSPREGUNTASEDDDTO()
                    .stream().filter(e -> e.getIdpregunta().equals(es.getIdPregunta())).findFirst();
            Optional<ConsultarRespuestasCompetencias> optRes = respuestas.stream()
                    .filter(e -> e.getIdRespuesta().equals(opApt.get().getIdrespuesta())).findFirst();

            es.setPuntajeSocioPersonal(optRes.get().getPuntaje());

        });
        LOG.info("valores Demeritos");

        if (is_demeritos_contestado) {

            ResponseResultadosEdd resultadoeddDem = consultarResultadosCompetencias(servidorPublico,
                    lstSeccion.get(2).getIdSeccion(), proceso);

            demeritos.forEach(es -> {
                Optional<ResultadosPreguntasEdd> opApt = resultadoeddDem.getResponse().getRESULTADOSPREGUNTASEDDDTO()
                        .stream().filter(e -> e.getIdpregunta().equals(es.getIdPregunta())).findFirst();
                LOG.info("puntaje {}", opApt.get().getIdrespuesta());
                es.setPuntajeDemeritos(opApt.get().getIdrespuesta());
                es.setPuntajeSocioPersonal("009");

            });

            params.put("calificacionFinalDemeritos", resultadoeddDem.getResponse().getCALIFICACIONPARCIAL());
        }
        byte[] data = null;
        LOG.info("Mapa=>>");
        params.put("nombreServidor", datosServidor.getNombre() + " " + datosServidor.getPrimerApellido() + " "
                + datosServidor.getSegundoApellido());
        params.put("nombreEvaluador", datosServidor.getNombrespEvaluador() + " "
                + datosServidor.getPrimerApellidoPEvaluador() + " " + datosServidor.getSegundoApellidoPEvaluador());
        params.put("claveServidorPublico", datosServidor.getClaveServidorPublico());
        params.put("periodoEvaluacion", "DEL :" + stringToDate(datosServidor.getFechaUno()) + " AL : " + stringToDate(datosServidor.getFechaDos()));

        params.put("puesto", datosServidor.getNombrePuesto());
        params.put("fechaIngresoPuesto", stringToDate(datosServidor.getFechaIngresoPuesto()));
        params.put("noPLaza", datosServidor.getNumPlaza());

        params.put("codigoPuesto", datosServidor.getCodigoPuesto());
        params.put("fechaIngresoGem", stringToDate(datosServidor.getFechaFechaIngresoGem()));
        params.put("unidadAdministrativa", datosServidor.getClaveUnidadAdministrativa());

        params.put("dependencia", datosServidor.getDependencia());
        params.put("subsecretariaGeneral", datosServidor.getSubsecretariaGeneral());
        params.put("subsecretaria", datosServidor.getSubsecretaria());

        params.put("coordinacion", datosServidor.getDepartamento());
        params.put("direccionGeneral", datosServidor.getDireccionGeneral());
        params.put("direccionArea", datosServidor.getDireccionArea());

        params.put("subDireccion", datosServidor.getSubdireccion());
        params.put("departamento", datosServidor.getDepartamento());
        params.put("espacioOrganizacional", datosServidor.getEspacioOrganizacional() == null ? "" : datosServidor.getEspacioOrganizacional());

        /**
         * Mecanismo que permite cambiar los valores de las respuestas por
         * texto.
         *
         * @author Javier Alvarado Rodriguez.
         * @version 1.0, 06/02/2023.
         */
        if (puntosNombre) {
            competenciasAptitudinales = this.convertirPuntosTextoAptitudinales(competenciasAptitudinales);
            competenciasSocioPersonales = this.convertirPuntosTextoSocioPersonales(competenciasSocioPersonales);
        }

        params.put("instruccionesLLenadoDataset", new JRBeanCollectionDataSource(instruccionesLLenado));
        params.put("competenciasAptitudinalesDataset", new JRBeanCollectionDataSource(competenciasAptitudinales));
        params.put("competenciasSocioPersonalesDataset", new JRBeanCollectionDataSource(competenciasSocioPersonales));
        params.put("demeritosDataset", new JRBeanCollectionDataSource(demeritos));
        params.put("consultarRespuestasCompetenciasDataset", new JRBeanCollectionDataSource(respuestas));
        params.put("calificacionFinal", datosServidor.getCalificacionFinal());

        params.put("puntossoc", resultadoeddSoc.getResponse().getTOTALPUNTOSOBTENIDOS());
        params.put("porcentajesoc", resultadoeddSoc.getResponse().getCALIFICACIONPARCIAL());

        // porcentajeap
        System.out.println("");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date3 = formatter.parse(datosServidor.getFechaTres());
            Calendar ftres = Calendar.getInstance();
            ftres.setTime(date3);
            LOG.info("dia {}", ftres.get(Calendar.DAY_OF_MONTH));
            LOG.info("mes {}", ftres.get(Calendar.MONTH));
            LOG.info("año {}", ftres.get(Calendar.YEAR));

            Date date = formatter.parse(datosServidor.getFechaUno());
            Calendar funo = Calendar.getInstance();
            funo.setTime(date);
            LOG.info("dia {}", funo.get(Calendar.DAY_OF_MONTH));
            LOG.info("mes {}", funo.get(Calendar.MONTH));
            LOG.info("año {}", funo.get(Calendar.YEAR));

            params.put("dfu", ftres.get(Calendar.DAY_OF_MONTH) + "");
            params.put("mfu", (ftres.get(Calendar.MONTH) + 1) + "");
            params.put("yfu", ftres.get(Calendar.YEAR) + "");
            Date date2 = formatter.parse(datosServidor.getFechaDos());
            Calendar fdos = Calendar.getInstance();
            funo.setTime(date2);
            LOG.info("dia {}", funo.get(Calendar.DAY_OF_MONTH));
            LOG.info("mes {}", funo.get(Calendar.MONTH));
            LOG.info("año {}", funo.get(Calendar.YEAR));
            params.put("dfd", ftres.get(Calendar.DAY_OF_MONTH) + "");
            params.put("mfd", (ftres.get(Calendar.MONTH) + 1) + "");
            params.put("yfd", ftres.get(Calendar.YEAR) + "");
            params.put("visibledemeritos", is_demeritos_contestado);

            String pathImg1 = resourceLoader.getResource("classpath:images/pleca-EDD.png").getURI().getPath();
            String pathImg2 = resourceLoader.getResource("classpath:images/pleca-EDD-ABAJO.png").getURI().getPath();
            String pathImg3 = resourceLoader.getResource("classpath:images/LOGO_EDOMEX_VERTICAL.png").getURI().getPath();

            String path = resourceLoader.getResource("classpath:reports/" + nombrePlantilla + ".jrxml").getURI().getPath();

            if ("prod".equals(ambienteTrabajo)) {
                // Servidor
                pathImg1 = "/home/images/pleca-EDD.png";
                pathImg2 = "/home/images/pleca-EDD-ABAJO.png";
                pathImg3 = "/home/images/LOGO_EDOMEX_VERTICAL.png";

                path = "/home/reports/" + nombrePlantilla + ".jrxml";
            }

            params.put("imagenOne", pathImg1);
            params.put("imageTwo", pathImg2);
            params.put("imageThree", pathImg3);

            JasperReport jasperReport = JasperCompileManager.compileReport(path);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            data = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    private HttpHeaders obtenerCabeceras() {
        String username = "seiusr";
        String password = "aguacate";
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setBasicAuth(username, password);
        headers2.setContentType(MediaType.APPLICATION_JSON);
        return headers2;
    }

    private List<PreguntasEjecucionEDD> convertirPuntosTextoAptitudinales(List<PreguntasEjecucionEDD> reactivos) {
        for (PreguntasEjecucionEDD p : reactivos) {
            switch(p.getPuntaje()){
                case "0":
                    p.setPuntaje("NUNCA");
                    break;
                case "1":
                    p.setPuntaje("CASI NUNCA");
                    break;
                case "2":
                    p.setPuntaje("RARA VEZ");
                    break;
                case "3":
                    p.setPuntaje("ALGUNAS VECES");
                    break;
                case "4":
                    p.setPuntaje("CON FRECUENCIA");
                    break;
                case "5":
                    p.setPuntaje("SIEMPRE");
                    break;
                default:
                    p.setPuntaje("SIN DEFINIR");
                    break;
            }

        }
        return reactivos;
    }
    
    private List<PreguntasEjecucionEDD> convertirPuntosTextoSocioPersonales(List<PreguntasEjecucionEDD> reactivos) {
        for (PreguntasEjecucionEDD p : reactivos) {
            switch(p.getPuntajeSocioPersonal()){
                case "0":
                    p.setPuntajeSocioPersonal("NUNCA");
                    break;
                case "1":
                    p.setPuntajeSocioPersonal("CASI NUNCA");
                    break;
                case "2":
                    p.setPuntajeSocioPersonal("RARA VEZ");
                    break;
                case "3":
                    p.setPuntajeSocioPersonal("ALGUNAS VECES");
                    break;
                case "4":
                    p.setPuntajeSocioPersonal("CON FRECUENCIA");
                    break;
                case "5":
                    p.setPuntajeSocioPersonal("SIEMPRE");
                    break;
                default:
                    p.setPuntajeSocioPersonal("SIN DEFINIR");
                    break;
            }

        }
        return reactivos;
    }
    //</editor-fold>
}
