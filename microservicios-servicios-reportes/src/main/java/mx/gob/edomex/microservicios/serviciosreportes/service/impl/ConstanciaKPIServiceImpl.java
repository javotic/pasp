package mx.gob.edomex.microservicios.serviciosreportes.service.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaKPIService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ConstanciaKPIServiceImpl implements ConstanciaKPIService {
	private static final Logger LOG = LoggerFactory.getLogger(ConstanciaKPIServiceImpl.class);
	@Autowired
	private ResourceLoader resourceLoader;
        
        @Value("${spring.profiles.active}")
	private String ambienteTrabajo;

	@Override
	public byte[] getReporteKPI(String fecha, String servidorPublico, String proceso) {
		byte[] data = null;
		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("fecha", fecha);
			params.put("nombreSP", servidorPublico);
			params.put("proceso", proceso);
                        
			String imageE = resourceLoader.getResource("classpath:images/ENCUESTA_ENCABEZADO.jpg").getURI().getPath();
                        String imageP = resourceLoader.getResource("classpath:images/ENCUESTA_PIE.jpg").getURI().getPath();
                        
                        String path = resourceLoader.getResource("classpath:reports/kpi_1.jrxml").getURI().getPath();

                        if("prod".equals(ambienteTrabajo)){
                        // Servidor
                            imageE = "/home/images/ENCUESTA_ENCABEZADO.jpg";
                            imageP = "/home/images/ENCUESTA_PIE.jpg";
                            
                            path = "/home/reports/kpi_1.jrxml";
                        }
                                                
                        params.put("imageE", imageE);
                        params.put("imageP", imageP);
                        JasperReport jasperReport = JasperCompileManager.compileReport(path);
                        
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
			data = JasperExportManager.exportReportToPdf(jasperPrint);

		} catch (Exception e) {
			LOG.error("Error {}", e);
		}

		return data;
	}

}
