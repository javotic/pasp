package mx.gob.edomex.microservicios.serviciosreportes.service.impl;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonCartaAceptacionDTO;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.BusResponse;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.service.CartaAceptacionService;
import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaParticipacionService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class CartaAceptacionServiceImpl implements CartaAceptacionService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConstanciaParticipacionService service;

    @Value("${urlWsReportes}")
    private String urlReportes;

    @Value("${spring.profiles.active}")
    private String ambienteTrabajo;

    @Override
    public byte[] getReporteCartaAceptacion(String claveServidorPublico, String idProcesoVigente) {
        byte[] data = null;

        //ResponseEscalafonDatosCandidato datosServidor = service.escalafonDatosCandidato(claveServidorPublico, idPlaza);
        List<EscalafonCartaAceptacionDTO> datosCartaAceptacion = escalafonCartaAceptacion(claveServidorPublico, idProcesoVigente);
        HashMap<String, Object> params = new HashMap<String, Object>();

        DateTimeZone zone = DateTimeZone.forID("America/Mexico_City");
        DateTime dt = new DateTime(zone);
        int day = dt.getDayOfMonth();
        int year = dt.getYear();
        String mes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        int month = dt.getMonthOfYear();

        //String fechaActual = "Toluca de Lerdo, Estado de México a " + day + " de " + month + " " + year;
        params.put("NUMERO_DICTAMEN", datosCartaAceptacion.get(0).getnDictamen());
        params.put("NUMERO_CPDEPE", datosCartaAceptacion.get(0).getNcpdepe());

        params.put("V_NOMBRE_SERVIDOR", datosCartaAceptacion.get(0).getNombreServidor());
        params.put("V_CLAVE_SERVIDOR", datosCartaAceptacion.get(0).getClaveServidor());

        params.put("PARRAFO1", datosCartaAceptacion.get(0).getParrafo1());

        params.put("NOMBRE_GOBERNADOR", datosCartaAceptacion.get(0).getNombreGobernador());
        params.put("PARRAFO2", datosCartaAceptacion.get(0).getParrafo2());

        params.put("V_ID_PROCESO", datosCartaAceptacion.get(0).getProceso());
        params.put("V_NUMERO_PLAZA", datosCartaAceptacion.get(0).getClaveplaza());
        params.put("V_PUESTO_NOMINAL", datosCartaAceptacion.get(0).getPuesto());
        params.put("V_CATEGORIA", datosCartaAceptacion.get(0).getCategoria());
        params.put("V_ADSCRIPCION", RecuperarFirmas(datosCartaAceptacion.get(0).getAdscripcion()));

        params.put("FECHA", day + " de " + mes[month - 1] + " del " + year);

        params.put("FIRMA1", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma1()));
        params.put("FIRMA2", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma2()));
        params.put("FIRMA3", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma3()));
        params.put("FIRMA4", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma4()));
        params.put("FIRMA5", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma5()));
        params.put("FIRMA6", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma6()));
        params.put("FIRMA7", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma7()));
        params.put("FIRMA8", RecuperarFirmas(datosCartaAceptacion.get(0).getFirma8()));

        try {
            //Local
            String image_encabezado = resourceLoader.getResource("classpath:images/Escalafon-acepta-encabezado.png").getURI().getPath();
            String image_pie = resourceLoader.getResource("classpath:images/Escalafon-acepta-pie.png").getURI().getPath();

            String path = resourceLoader.getResource("classpath:reports/carta_aceptacion.jrxml").getURI().getPath();

            if ("prod".equals(ambienteTrabajo)) {
                // Servidor
                image_encabezado = "/home/images/Escalafon-acepta-encabezado.png";
                image_pie = "/home/images/Escalafon-acepta-pie.png";

                path = "/home/reports/carta_aceptacion.jrxml";
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(path);

            params.put("image_encabezado", image_encabezado);
            params.put("image_pie", image_pie);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            data = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public byte[] getReporteCartaAceptacionUsuario(String claveServidorPublico, String idProcesoVigente,
            boolean aceptoCarta, String idPlazaParticipacion, String lugarProceso, boolean actualizarRegistro) {
        byte[] data = null;

        List<EscalafonCartaAceptacionDTO> datosCartaAceptacion = escalafonCartaAceptacion(claveServidorPublico, idProcesoVigente);
        ResponseEscalafonDatosCandidato datosCanditato = escalafonDatosCandidato(claveServidorPublico, idPlazaParticipacion);
        HashMap<String, Object> params = new HashMap<String, Object>();

        DateTimeZone zone = DateTimeZone.forID("America/Mexico_City");
        DateTime dt = new DateTime(zone);
        int day = dt.getDayOfMonth();
        int year = dt.getYear();
        String mes[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        int month = dt.getMonthOfYear();

        params.put("FECHA_ACTUAL", "Toluca de Lerdo, Estado de México a " + day + " de " + mes[month - 1] + " " + year);

        //Plaza actual
        params.put("V_NOMBRE_SERVIDOR", datosCanditato.getResponse().get(0).getNombreCompletoSP());
        params.put("V_CLAVE_SERVIDOR", datosCanditato.getResponse().get(0).getClaveSP());
        params.put("V_PUESTO_NOMINAL", datosCanditato.getResponse().get(0).getPuestoNominal());
        String[] catetorialist = datosCanditato.getResponse().get(0).getCategoria().split("-");
        params.put("V_CATEGORIA", "Nivel " + catetorialist[0] + " - " + "Rango " + catetorialist[1]);
        params.put("V_NUMERO_PLAZA_ACTUAL", datosCanditato.getResponse().get(0).getNumPlazaActual());
        params.put("V_ADSCRIPCION", datosCanditato.getResponse().get(0).getAdscripcion().replace("\n", ", "));

        //Plaza Consursa
        params.put("V_NUMERO_PLAZA_CONCURSA", datosCartaAceptacion.get(0).getClaveplaza());
        params.put("V_PUESTO_CONCURSA", datosCanditato.getResponse().get(0).getPuestoConcursa());
        catetorialist = datosCanditato.getResponse().get(0).getCategoriaPlazaConcursa().split("-");
        params.put("V_CATEGORIA_CONCURSA", "Nivel " + catetorialist[0] + " - " + "Rango " + catetorialist[1]);
        params.put("V_ADSCRIPCION_CONCURSA", datosCanditato.getResponse().get(0).getAdscripcionPlazaConcursa().replace("\n", ", "));
        params.put("FECHA_PUBLICACION", datosCartaAceptacion.get(0).getFechaPublica());
        params.put("ACEPTO_CARTA", aceptoCarta ? "ACEPTO" : "NO ACEPTO");
        params.put("V_ID_PROCESO", datosCanditato.getResponse().get(0).getIdProcesoVigente());

        params.put("LUGAR_PROCESO", lugarProceso);

        try {
            //Local
            String path = resourceLoader.getResource("classpath:reports/carta_aceptacion_usuario.jrxml").getURI().getPath();

            if ("prod".equals(ambienteTrabajo)) {
                // Servidor
                path = "/home/reports/carta_aceptacion_usuario.jrxml";
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            data = JasperExportManager.exportReportToPdf(jasperPrint);

            //Si es necesario actualizamos la respuesta del usuario 
            if (actualizarRegistro) {
                String statusEjecucion = escalafonAceptaPlaza(claveServidorPublico, idProcesoVigente, aceptoCarta);
                if ("0".equals(statusEjecucion)) {
                    throw new Exception("No se pudieron guardar los datos.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return data;
    }

    public List<EscalafonCartaAceptacionDTO> escalafonCartaAceptacion(String claveServidorPublico, String idProcesoVigente) {
        String url = urlReportes + "/escalafonGeneral/escalafonCartaAceptacion/" + claveServidorPublico + "?idProcesoVigente=" + idProcesoVigente;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String account = response.getBody();
        Gson gson = new Gson();
        Type listType = new TypeToken<BusResponse<List<EscalafonCartaAceptacionDTO>>>() {
        }.getType();
        BusResponse<List<EscalafonCartaAceptacionDTO>> dataArray = gson.fromJson(account, listType);
        return dataArray.getResponse();
    }

    public ResponseEscalafonDatosCandidato escalafonDatosCandidato(String claveServidorPublico, String idPlaza) {
        String url = urlReportes + "/escalafonGeneral/escalafonDatosCandidato/" + claveServidorPublico + "/"
                + idPlaza;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String account = response.getBody();
        Gson gson = new Gson();
        return gson.fromJson(account, ResponseEscalafonDatosCandidato.class);
    }

    public String escalafonAceptaPlaza(String idServidorPublico, String idProcesoVigente, boolean estatus) {
        String url = urlReportes + "/escalafonGeneral/escalafonAceptaPlaza/" + idServidorPublico + "/" + estatus + "?idProcesoVigente=" + idProcesoVigente;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String account = response.getBody();
        Gson gson = new Gson();
        Type stringdata = new TypeToken<BusResponse<Integer>>() {
        }.getType();
        BusResponse<Integer> dataArray = gson.fromJson(account, stringdata);
        return dataArray.getResponse().toString();
    }

    public String RecuperarFirmas(String Firma) {
        String cadenaFirma = "";
        String[] firmaTrabajo = Firma.split("\\|");
        int iteracion = 1;
        for (String a : firmaTrabajo) {
            if (iteracion < firmaTrabajo.length) {
                cadenaFirma = cadenaFirma + a + "\n";
            } else {
                cadenaFirma = cadenaFirma + a;
            }
            iteracion++;
        }

        return cadenaFirma;
    }
}
