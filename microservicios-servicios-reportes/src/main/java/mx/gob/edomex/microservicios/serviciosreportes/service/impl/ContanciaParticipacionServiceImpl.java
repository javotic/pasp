package mx.gob.edomex.microservicios.serviciosreportes.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

import mx.gob.edomex.microservicios.serviciosreportes.models.AsignacionPuntajeEscalafonario;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonCursosDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonTipoCandidatoDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonEstadoInscripcionDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonPlazasDisponiblesDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonPuntajePrevio;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.BusResponse;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonPuntajePrevio;
import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaParticipacionService; 
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ContanciaParticipacionServiceImpl implements ConstanciaParticipacionService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${urlWsReportes}")
	private String urlReportes;
        
        @Value("${spring.profiles.active}")
	private String ambienteTrabajo;
                
        

	@Override
	public byte[] getReporteConstanciaParticipacion(String claveServidorPublico, String idPlaza,
			String idProcesoVigente) {
		byte[] data = null;

		HashMap<String, Object> params = new HashMap<String, Object>();

		ResponseEscalafonDatosCandidato datosCanditato = escalafonDatosCandidato(claveServidorPublico, idPlaza);
		if (!datosCanditato.getResponse().isEmpty()) {
			EscalafonDatosCandidato datos = datosCanditato.getResponse().get(0);
                    
                    List<EscalafonEstadoInscripcionDTO> estadoInscripcion = escalafonEstadoInscripcion(claveServidorPublico,datos.getIdProcesoVigente());
                    List<EscalafonPlazasDisponiblesDTO>  plazasDisponibles = escalafonPlazasDisponibles(claveServidorPublico);
                    EscalafonPlazasDisponiblesDTO plazaDisponible =    plazasDisponibles.stream().filter(x -> (x.getIDESTATUSPLAZA().equals(idPlaza))).collect(Collectors.toList()).get(0);
                    
                    ResponseEscalafonPuntajePrevio puntaje = puntajePrevio(claveServidorPublico, idProcesoVigente);

                    EscalafonTipoCandidatoDTO tipoCandidato = escalafonTipoCandidato(claveServidorPublico, idProcesoVigente, idPlaza);
                            
                      //Datos del candidato
                    params.put("V_NOMBRE_SERVIDOR", datos.getNombreCompletoSP());
                    params.put("V_CLAVE_SERVIDOR",datos.getClaveSP());
                    params.put("V_PUESTO_NOMINAL", datos.getPuestoNominal());
                    String[] catetorialist = datos.getCategoria().split("-");
                    params.put("V_CATEGORIA", "Nivel " + catetorialist[0] + " - " + "Rango " + catetorialist[1]);
                    params.put("V_ADSCRIPCION", datos.getAdscripcion());
                    params.put("V_SECRETARIA", datos.getSecretaria());

                    //Datos de la plaza
                    params.put("V_ID_PROCESO",datos.getIdProcesoVigente());
                    params.put("V_NUMERO_PLAZA", datos.getNumPlaza());
                    params.put("V_PUESTO_CONCURSA", datos.getPuestoConcursa());
                    catetorialist = datos.getCategoriaPlazaConcursa().split("-");
                    params.put("V_CATEGORIA_CONCURSA", "Nivel " + catetorialist[0] + " - " + "Rango " + catetorialist[1]);
                    params.put("V_ADSCRIPCION_CONCURSA", datos.getAdscripcionPlazaConcursa());
                    params.put("V_SECRETARIA_CONCURSA", datos.getSecretariaPlazaConcursa());
                                                
                    params.put("V_NUMERO_REGISTRO", estadoInscripcion.get(0).getNumReg());
          
                    params.put("V_P_MINIMO", plazaDisponible.getPUNTAJEESCALAFONARIOMINIMO());
                    
                    params.put("V_P_ESCALAFONARIA",puntaje.getResponse().get(0).getTOTAL());
                    
                    params.put("V_DICTAMEN_SI_NO",estadoInscripcion.get(0).getDESCRIPCIONDICTAMEN());
                    params.put("V_DICTAMEN",estadoInscripcion.get(0).getDESCRIPCIONDICTAMEN().trim().toUpperCase().equals("SI")?"ACEPTADO": "NO ACEPTADO");
                    
                    params.put("V_GENERAL_NATURAL", tipoCandidato.getTipoCandidato().toUpperCase().equals("G")?"GENERAL": "NATURAL");
                    
                    params.put("DICTAMEN_FINAL", estadoInscripcion.get(0).getESTATUSDICTAMEN());
                    params.put("examen", estadoInscripcion.get(0).getCalificacion());
                    
                    Double calificacion = Double.parseDouble(estadoInscripcion.get(0).getCalificacion().equals("")?"0.00":estadoInscripcion.get(0).getCalificacion()) + 
                                          Double.parseDouble(puntaje.getResponse().get(0).getTOTAL());
                    
                    DecimalFormat df = new DecimalFormat("#.00");
                    df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

                    params.put("totalPuntuacionEscalafonario", df.format(calificacion));

		    params.put("asignacionPuntajeDataset", new JRBeanCollectionDataSource(getAsignacionPuntaje(puntaje)));
                    
                    //Se agregan las firmas configuradas para la ultima seccion.
                    params.put("firma_4", datos.getFirmaDerecha());
                    params.put("firma_5", datos.getFirmaCentral());
                    params.put("firma_6", datos.getFirmaIzquierda());
		}
		try {
                    //Datos Local
                    String image_encabezado = resourceLoader.getResource("classpath:images/Escalafon-participa-encabezado.png").getURI().getPath();
                    String image_pie = resourceLoader.getResource("classpath:images/Escalafon-participa-pie.png").getURI().getPath();
                    String img_firma_1 = resourceLoader.getResource("classpath:images/img_firma_1.png").getURI().getPath();
                    String img_firma_2 = resourceLoader.getResource("classpath:images/img_firma_2.png").getURI().getPath();
                    String img_firma_3 = resourceLoader.getResource("classpath:images/img_firma_3.png").getURI().getPath();
                    String fondo = resourceLoader.getResource("classpath:images/LOGO_EDOMEX_DEGRADADO.png").getURI().getPath();

                    String path = resourceLoader.getResource("classpath:reports/constancia_participacion.jrxml").getURI().getPath();

                    if("prod".equals(ambienteTrabajo)){
                    // Servidor
                    image_encabezado = "/home/images/Escalafon-participa-encabezado.png";
                    image_pie = "/home/images/Escalafon-participa-pie.png";
                    img_firma_1 = "/home/images/img_firma_1.png";
                    img_firma_2 = "/home/images/img_firma_2.png";
                    img_firma_3 = "/home/images/img_firma_3.png";
                    fondo = "/home/images/LOGO_EDOMEX_DEGRADADO.png";
                    path = "/home/reports/constancia_participacion.jrxml";
                    }
                    
                    JasperReport jasperReport = JasperCompileManager.compileReport(path);

                    params.put("image_encabezado", image_encabezado);
                    params.put("image_pie", image_pie);
                    params.put("img_firma_1", img_firma_1);
                    params.put("img_firma_2", img_firma_2);
                    params.put("img_firma_3", img_firma_3);
                    params.put("fondo", fondo);
                       
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
                    data = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	
	public List<AsignacionPuntajeEscalafonario> getAsignacionPuntaje(ResponseEscalafonPuntajePrevio puntaje) {
		List<AsignacionPuntajeEscalafonario> data = new ArrayList<>();
		if (!puntaje.getResponse().isEmpty()) {
			EscalafonPuntajePrevio p = puntaje.getResponse().get(0);
			AsignacionPuntajeEscalafonario a1 = new AsignacionPuntajeEscalafonario();
			a1.setDescripcion("CAPACITACIÓN");
			a1.setPuntos(p.getCAPACITACION());
			data.add(a1);

			AsignacionPuntajeEscalafonario a2 = new AsignacionPuntajeEscalafonario();
			a2.setDescripcion("ESCOLARIDAD");
			a2.setPuntos(p.getESCOLARIDAD());
			data.add(a2);

			AsignacionPuntajeEscalafonario a3 = new AsignacionPuntajeEscalafonario();
			a3.setDescripcion("EFICIENCIA");
			a3.setPuntos(p.getEFICIENCIA());
			data.add(a3);

			AsignacionPuntajeEscalafonario a4 = new AsignacionPuntajeEscalafonario();
			a4.setDescripcion("ANTIGÜEDAD");
			a4.setPuntos(p.getANTIGUEDAD());
			data.add(a4);

			AsignacionPuntajeEscalafonario a5 = new AsignacionPuntajeEscalafonario();
			a5.setDescripcion("SUBTOTAL");
			a5.setPuntos(p.getPuntajeSubTotal());
			data.add(a5);

			AsignacionPuntajeEscalafonario a6 = new AsignacionPuntajeEscalafonario();
			a6.setDescripcion("DEMÉRITOS");
			a6.setPuntos(p.getDEMERITOS());
			data.add(a6);

		}

		return data;
	}

	@Override
	public ResponseEscalafonDatosCandidato escalafonDatosCandidato(String claveServidorPublico, String idPlaza) {
		String url = urlReportes+"/escalafonGeneral/escalafonDatosCandidato/" + claveServidorPublico + "/"
				+ idPlaza;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		return gson.fromJson(account, ResponseEscalafonDatosCandidato.class);
	}

	@Override
	public ResponseEscalafonPuntajePrevio puntajePrevio(String claveServidorPublico, String idProcesoVigente) {
		String url = urlReportes+"/informacionActualEscalafon/escalafonPuntajePrevio/" + claveServidorPublico
				+ "?idProcesoVigente=" + idProcesoVigente;
		;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		return gson.fromJson(account, ResponseEscalafonPuntajePrevio.class);
	}
        
	public List<EscalafonEstadoInscripcionDTO> escalafonEstadoInscripcion(String claveServidorPublico, String idProcesoVigente) {
		String url = urlReportes+"/procesoEscalafonarioVigente/escalafonEstadoInscripcion/" + claveServidorPublico
				+ "?idProcesoVigente=" + idProcesoVigente;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
                 Type listType = new TypeToken<BusResponse<List<EscalafonEstadoInscripcionDTO>>>() {}.getType();
                 BusResponse<List<EscalafonEstadoInscripcionDTO>> dataArray = gson.fromJson(account, listType);
                 return dataArray.getResponse();
	}        
        
        
        
        public List<EscalafonPlazasDisponiblesDTO> escalafonPlazasDisponibles(String claveServidorPublico) {
            String url = urlReportes+"/procesoEscalafonarioVigente/escalafonPlazasDisponibles/" + claveServidorPublico + "/0";

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
             Type listType = new TypeToken<BusResponse<List<EscalafonPlazasDisponiblesDTO>>>() {}.getType();
             BusResponse<List<EscalafonPlazasDisponiblesDTO>> dataArray = gson.fromJson(account, listType);
             return dataArray.getResponse();
	}        
        
        
        
        public EscalafonTipoCandidatoDTO escalafonTipoCandidato(String claveServidorPublico, String idProcesoVigente, String idPlaza) {
            String url = urlReportes+"/escalafonGeneral/escalafonTipoCandidato/" + claveServidorPublico + "/" + idPlaza + "?idProcesoVigente=" + idProcesoVigente;

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
             Type listType = new TypeToken<BusResponse<EscalafonTipoCandidatoDTO>>() {}.getType();
             BusResponse<EscalafonTipoCandidatoDTO> dataArray = gson.fromJson(account, listType);
             return dataArray.getResponse();
	}        
        
                
        

}
