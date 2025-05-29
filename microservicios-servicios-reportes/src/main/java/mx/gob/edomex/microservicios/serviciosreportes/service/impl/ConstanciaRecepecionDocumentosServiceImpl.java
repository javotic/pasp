package mx.gob.edomex.microservicios.serviciosreportes.service.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.stream.Collectors;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonAntiguedadDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonCapacInduccion;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonCertificadosDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonCursosDTO;

import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonDatosRecepDocDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonDiplomadosDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonEficienciaDTO;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonEscolaridadDTO;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.BusResponse;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonDatosCandidato;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseEscalafonPuntajePrevio;
import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaRecepecionDocumentosService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConstanciaRecepecionDocumentosServiceImpl implements ConstanciaRecepecionDocumentosService {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${urlWsReportes}")
	private String urlReportes;
	
	@Autowired
	private ResourceLoader resourceLoader;
        
        @Value("${spring.profiles.active}")
	private String ambienteTrabajo;        

	@Override
	public byte[] getConstanciaRecepecionDocumentos(String claveServidorPublic, String idPlaza) {
		byte[] data = null;

		HashMap<String, Object> params = new HashMap<String, Object>();
               
                try {
                
		ResponseEscalafonDatosCandidato datosCanditato = escalafonDatosCandidato(claveServidorPublic, idPlaza);
		 System.out.println(datosCanditato.getResponse().get(0).getNombreCompletoSP());
		if (!datosCanditato.getResponse().isEmpty()) {
                    
                    EscalafonDatosCandidato datos = datosCanditato.getResponse().get(0);

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

                     params.put("V_ID_PROC_SECRETARIA",datos.getIdprocSecretaria());
                    
                    List<EscalafonCursosDTO> cursos = escalafonEstadoInscripcion(claveServidorPublic,datos.getIdProcesoVigente());
                    cursos.sort(Comparator.comparing(EscalafonCursosDTO::getNombreCurso));
                    
                    List<EscalafonCertificadosDTO> certificaciones = escalafonCapacCertifComp(claveServidorPublic,datos.getIdProcesoVigente() );
                    List<EscalafonDiplomadosDTO> diplomados = escalafonDiplomados(claveServidorPublic,datos.getIdProcesoVigente() );
                    diplomados.sort(Comparator.comparing(EscalafonDiplomadosDTO::getNombreDiplomado));
                    
                    diplomados.forEach((diploma) -> {
                        if("N/A".equals(diploma.getNombreDiplomado())){
                            diploma.setFechaDiplomado("");
                        }else{
                            diploma.setFechaDiplomado(diploma.getFechaDiplomado() + " " + 
                                    diploma.getDuracion().subSequence(0, diploma.getDuracion().indexOf("."))
                                    + ("1".equals(diploma.getTipoDuracion())?" HORAS": " CREDITOS") );
                        }

                    });
                     
                    List<EscalafonEscolaridadDTO> escolaridad = escalafonEscolaridad(claveServidorPublic,datos.getIdProcesoVigente());
                    List<EscalafonEficienciaDTO> eficiencia = escalafonEficiencia(claveServidorPublic,datos.getIdProcesoVigente());
                    List<EscalafonAntiguedadDTO> antiguedad = escalafonAntiguedad(claveServidorPublic,datos.getIdProcesoVigente());
                    List<EscalafonDatosRecepDocDTO> receptDocumentos = escalafonDatosRecepDoc(claveServidorPublic,datos.getIdProcesoVigente());
                    List<EscalafonEficienciaDTO> demeritos = escalafonDemeritos(claveServidorPublic,datos.getIdProcesoVigente());
                    demeritos.sort(Comparator.comparing(EscalafonEficienciaDTO::getAnio)
                                   .thenComparing(EscalafonEficienciaDTO::getSemestre));
                    List<EscalafonCapacInduccion> induccion =  escalafonCapacInduccion(claveServidorPublic,datos.getIdProcesoVigente());
                                 
                    ResponseEscalafonPuntajePrevio puntajePrevio =  puntajePrevio(claveServidorPublic,datos.getIdProcesoVigente());
                       
                    params.put("DatosCursosDataset", new JRBeanCollectionDataSource(cursos));
                    
                    cursos.forEach((curs)->{
                        // + " " + $F{horas} + " HORAS"
                        if("N/A".equals(curs.getNombreCurso())){
                            curs.setFechaCurso("");
                        }else{
                         curs.setFechaCurso(curs.getFechaCurso() + " " + curs.getHoras() + " HORAS");
                        }
                    });
                    params.put("FechasCursosDataset", new JRBeanCollectionDataSource(cursos));
                    params.put("ValidadoCursosDataset", new JRBeanCollectionDataSource(cursos));

                    params.put("DatosCertificacionesDataset", new JRBeanCollectionDataSource(certificaciones));
                    params.put("DatosDiplomadosDataset", new JRBeanCollectionDataSource(diplomados));
                    params.put("FechasDiplomadosDataset", new JRBeanCollectionDataSource(diplomados));
                    params.put("ValidadosDiplomadosDataset", new JRBeanCollectionDataSource(diplomados));

                    params.put("DatosEscolaridadDataset", new JRBeanCollectionDataSource(escolaridad));

                    //Ordenamos por año
                    eficiencia.sort(Comparator.comparing(EscalafonEficienciaDTO::getAnio)
                                    .thenComparing(EscalafonEficienciaDTO::getSemestre));
                    
                    if(eficiencia.size() > 5){
                        for( int i = 0 ; i < eficiencia.size() - 5 ; i++){
                            eficiencia.remove(i);
                        }
                    }
                    
                    for( int i = 0 ; i < 5; i++){
                        if(i < eficiencia.size()){
                            params.put("EF_A_" +  (i + 1), eficiencia.get(i).getAnio());
                            params.put("EF_S_" +  (i + 1), eficiencia.get(i).getSemestre());
                            params.put("EF_R_" +  (i + 1), eficiencia.get(i).getResultado());
                        }else{
                            params.put("EF_A_" +  (i + 1), "");
                            params.put("EF_S_" +  (i + 1), "");
                            params.put("EF_R_" +  (i + 1), "");
                        }
                    }
                    
                    if(antiguedad != null && antiguedad.size() > 0){
                        params.put("ANYOS_ANTIGUEDAD" , antiguedad.get(0).getAntiguedad());
                        params.put("RANGO_ANTIGUEDAD", antiguedad.get(0).getRangoAntiguedad());
                        //params.put("P_ANTIG", antiguedad.get(0).getPuntajeAntiguedad());
                    }else{
                        params.put("ANYOS_ANTIGUEDAD" , "");
                        params.put("RANGO_ANTIGUEDAD", "");
                        //params.put("P_ANTIG", "");
                    }
                    
                    
                        if(receptDocumentos.get(0).getInduccionSP().equals("")){
                            params.put("FECHA_INDUCCION", "N/A");
                            params.put("P_CAPAC_IND" , "0 PUNTOS");
                            params.put("IND_VALID",  "");
                        }else{
                            params.put("FECHA_INDUCCION", receptDocumentos.get(0).getInduccionSP());
                            params.put("P_CAPAC_IND" , "1 PUNTO");
                            params.put("IND_VALID",  ((induccion.size() > 0) ? induccion.get(0).getValidado(): ""));
                           // params.put("P_CAPAC_IND" , receptDocumentos.get(0).);
                        }
                        
                        params.put("P_CAPAC_CUR", receptDocumentos.get(0).getPuntosCursos().
                                substring(0, receptDocumentos.get(0).getPuntosCursos().indexOf(".")));

                        params.put("P_CAPAC_CER", receptDocumentos.get(0).getPuntosCertificados().
                                                  substring(0, receptDocumentos.get(0).getPuntosCertificados().indexOf(".")));
                        params.put("P_CAPAC_DIP" , receptDocumentos.get(0).getPuntosDiplomados().
                                                  substring(0, receptDocumentos.get(0).getPuntosDiplomados().indexOf(".")));
                        params.put("P_SUBTOT", receptDocumentos.get(0).getPuntajeSubTotal());
                        
                        params.put("P_CAPAC", puntajePrevio.getResponse().get(0).getCAPACITACION());
                        params.put("P_ESCOL", puntajePrevio.getResponse().get(0).getESCOLARIDAD());
                        params.put("P_EFIC", puntajePrevio.getResponse().get(0).getEFICIENCIA());
                        params.put("P_ANTIG", puntajePrevio.getResponse().get(0).getANTIGUEDAD());
                        params.put("P_DEMER", puntajePrevio.getResponse().get(0).getDEMERITOS());
                        params.put("P_TOT", puntajePrevio.getResponse().get(0).getTOTAL());                    
                    
                        params.put("DEM_A_2", (demeritos.size() >0?demeritos.get(0).getAnio():""));
                        params.put("DEM_S_2", (demeritos.size() >0?demeritos.get(0).getSemestre():""));
                        params.put("DEM_R_2", (demeritos.size() >0?demeritos.get(0).getResultado():""));
                        
                        params.put("DEM_A_3", (demeritos.size() >1?demeritos.get(1).getAnio():""));
                        params.put("DEM_S_3", (demeritos.size() >1?demeritos.get(1).getSemestre():""));
                        params.put("DEM_R_3", (demeritos.size() >1?demeritos.get(1).getResultado():""));       
                        
                        params.put("DEM_A_4", (demeritos.size() >2?demeritos.get(2).getAnio():""));
                        params.put("DEM_S_4", (demeritos.size() >2?demeritos.get(2).getSemestre():""));
                        params.put("DEM_R_4", (demeritos.size() >2?demeritos.get(2).getResultado():""));  

                        params.put("V_DICTAMEN" , receptDocumentos.get(0).getTextoResultado());
                        params.put("TEXTO_CANDIDATO" , receptDocumentos.get(0).getTextoCandidato());
                        params.put("TEXTO_MOTIVO" , receptDocumentos.get(0).getTextoMotivo());
                        params.put("TEXTO_ACREEDOR" , receptDocumentos.get(0).getTextoAcreedor());
		}

                        //Local
                        String imageHead = resourceLoader.getResource("classpath:images/Escalafon-RecepDoc-encabezado.png").getURI().getPath();
			String imageFoot = resourceLoader.getResource("classpath:images/Escalafon-RecepDoc-pie.png").getURI().getPath();
                                
			String path = resourceLoader.getResource("classpath:reports/constancia_recepcion_documentos.jrxml").getURI().getPath();
			
                        if("prod".equals(ambienteTrabajo)){
                        // Servidor
                        imageHead = "/home/images/Escalafon-RecepDoc-encabezado.png";
                        imageFoot = "/home/images/Escalafon-RecepDoc-pie.png";
                        path = "/home/reports/constancia_recepcion_documentos.jrxml";
                        }
                        
                        JasperReport jasperReport = JasperCompileManager.compileReport(path);
                        
                        params.put("image_Head", imageHead);
			params.put("image_Foot", imageFoot);
		

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
			data = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public ResponseEscalafonDatosCandidato escalafonDatosCandidato(String claveServidorPublico,String idPlaza) {
		ResponseEntity<String> response = restTemplate
                                    .getForEntity(urlReportes+"/escalafonGeneral/escalafonDatosCandidato/"+claveServidorPublico + "/" + idPlaza, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		return gson.fromJson(account, ResponseEscalafonDatosCandidato.class);
	}
        
	public List<EscalafonCursosDTO> escalafonEstadoInscripcion(String claveServidorPublico, String idProcesoVigente) {
		String url = urlReportes+"/escalafonGeneral/escalafonCapacCursos/" + claveServidorPublico
				+ "?idProcesoVigente=" + idProcesoVigente;
		;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
                 Type listType = new TypeToken<BusResponse<List<EscalafonCursosDTO>>>() {}.getType();
                 BusResponse<List<EscalafonCursosDTO>> dataArray = gson.fromJson(account, listType);
                 return dataArray.getResponse();
	}
        
        public List<EscalafonCertificadosDTO> escalafonCapacCertifComp(String claveServidorPublico, String idProcesoVigente) {
        String url = urlReportes+"/escalafonGeneral/escalafonCapacCertifComp/" + claveServidorPublico
                        + "?idProcesoVigente=" + idProcesoVigente;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String account = response.getBody();
        Gson gson = new Gson();
         Type listType = new TypeToken<BusResponse<List<EscalafonCertificadosDTO>>>() {}.getType();
         BusResponse<List<EscalafonCertificadosDTO>> dataArray = gson.fromJson(account, listType);
         return dataArray.getResponse();
	}

        
        public List<EscalafonDiplomadosDTO> escalafonDiplomados(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonDiplomados/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonDiplomadosDTO>>>() {}.getType();
            BusResponse<List<EscalafonDiplomadosDTO>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}
	
        public List<EscalafonEscolaridadDTO> escalafonEscolaridad(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonEscolaridad/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonEscolaridadDTO>>>() {}.getType();
            BusResponse<List<EscalafonEscolaridadDTO>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}        
        

        public List<EscalafonEficienciaDTO> escalafonEficiencia(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonEficiencia/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonEficienciaDTO>>>() {}.getType();
            BusResponse<List<EscalafonEficienciaDTO>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}        
            
        public List<EscalafonAntiguedadDTO> escalafonAntiguedad(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonAntiguedad/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonAntiguedadDTO>>>() {}.getType();
            BusResponse<List<EscalafonAntiguedadDTO>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}       
          
        public List<EscalafonDatosRecepDocDTO> escalafonDatosRecepDoc(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonDatosRecepDoc/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonDatosRecepDocDTO>>>() {}.getType();
            BusResponse<List<EscalafonDatosRecepDocDTO>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}          
            
        public ResponseEscalafonPuntajePrevio puntajePrevio(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/informacionActualEscalafon/escalafonPuntajePrevio/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            return gson.fromJson(account, ResponseEscalafonPuntajePrevio.class);
	}
        
        public List<EscalafonEficienciaDTO> escalafonDemeritos(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonDemeritos/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonEficienciaDTO>>>() {}.getType();
            BusResponse<List<EscalafonEficienciaDTO>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}    
        
        public List<EscalafonCapacInduccion> escalafonCapacInduccion(String claveServidorPublico, String idProcesoVigente) {
            String url = urlReportes+"/escalafonGeneral/escalafonCapacInduccion/" + claveServidorPublico
                            + "?idProcesoVigente=" + idProcesoVigente;
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String account = response.getBody();
            Gson gson = new Gson();
            Type listType = new TypeToken<BusResponse<List<EscalafonCapacInduccion>>>() {}.getType();
            BusResponse<List<EscalafonCapacInduccion>> dataArray = gson.fromJson(account, listType);
            return dataArray.getResponse();
	}     
        
}
