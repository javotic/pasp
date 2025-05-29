package mx.gob.edomex.microservicios.hnomina.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import mx.gob.edomex.microservicios.hnomina.dao.ComprobantesDAO;

import mx.gob.edomex.microservicios.hnomina.dto.DetalleReciboDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ReciboNominaDTO;
import mx.gob.edomex.microservicios.hnomina.dto.RecibosDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteListDTO;
import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;
import mx.gob.edomex.microservicios.hnomina.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.hnomina.service.ComprobantesService;
import mx.gob.edomex.microservicios.hnomina.utils.Utils;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class ComprobantesServiceImpl implements ComprobantesService {

    private static final Logger LOG = LoggerFactory.getLogger(ComprobantesServiceImpl.class);

    @Autowired
    private ComprobantesDAO comprobantesDAO;

    @Value("${spring.profiles.active}")
    private String ambienteTrabajo;

    @Value("${spring.datasource.url}")
    private String urlDataSource;
    @Value("${spring.datasource.username}")
    private String databaseUser;
    @Value("${spring.datasource.password}")
    private String databasePass;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public byte[] generarComprobantes(ServidorComprobanteListDTO spComprobante) throws BusException {
        Utils utils = new Utils();
        List<String> lstIncidencias = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<String, Object>();
        byte[] data = null;

        try {

            ByteArrayOutputStream byteArrayOutputStreamZip = new ByteArrayOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStreamZip);
            ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

            SQLServerDataTable sourceDataTable = new SQLServerDataTable();

            // Define metadata for the data table.  
            sourceDataTable.addColumnMetadata("idServidorPublico", java.sql.Types.NVARCHAR);
            sourceDataTable.addColumnMetadata("fecha1", java.sql.Types.NVARCHAR);
            sourceDataTable.addColumnMetadata("fecha2", java.sql.Types.NVARCHAR);

            //String[] lstservidores = claveServidor.split(",");
            for (ServidorComprobanteDTO itemServidor : spComprobante.getLSTSERVIDORCOMPROBANTE()) {
                sourceDataTable.addRow(
                        itemServidor.getCLAVESP(),
                        itemServidor.getFECHAINICIO(),
                        itemServidor.getFECHAFIN()
                );
            }

            // Realizamos la consulta de datos
            Connection conn = DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
            SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.reciboNomina ? ").unwrap(SQLServerCallableStatement.class);
            pStmt.setStructured(1, "dbo.reciboNominaTable", sourceDataTable);

            ResultSet resultData = pStmt.executeQuery();

            List<ReciboNominaDTO> lstdatosRecibo = new ArrayList();

            while (resultData.next()) {
                ReciboNominaDTO itemRecibo = new ReciboNominaDTO();

                itemRecibo.setIdServidor(resultData.getString("idServidor"));
                itemRecibo.setFechaI(resultData.getString("fechaI"));
                itemRecibo.setFechaF(resultData.getString("fechaF"));
                itemRecibo.setDatoServidor(resultData.getString("datoServidor"));
                itemRecibo.setPercepciones(resultData.getString("percepciones"));
                itemRecibo.setDeducciones(resultData.getString("deducciones"));
                itemRecibo.setTotPercepciones(resultData.getString("totPercepciones"));
                itemRecibo.setTotDeducciones(resultData.getString("totDeducciones"));
                itemRecibo.setTotalNeto(resultData.getString("totalNeto"));

                itemRecibo.setNumRecibo(resultData.getString("numRecibo"));
                itemRecibo.setNumCuenta(resultData.getString("numCuenta"));
                itemRecibo.setFechaAbono(resultData.getString("fechaAbono"));
                itemRecibo.setMensaje(resultData.getString("mensaje"));

                lstdatosRecibo.add(itemRecibo);
            }

            conn.close();

            //Iteramos los datos recibidos para armar los PDF armados
            for (ReciboNominaDTO datosRecibo : lstdatosRecibo) {

                byte[] itemData = null;
                //ReciboNominaDTO datosRecibo = new ReciboNominaDTO();

                // NumberFormat currency = NumberFormat.getCurrencyInstance();
                DecimalFormat currency = new DecimalFormat("$###,###.###");
                Locale locale = new Locale("es", "MX");
                SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy", locale);

                //Iniciamos la construccion del formato
                String[] datosServidor = datosRecibo.getDatoServidor().split("\\|");
                params.put("Nombre", datosServidor[0]);
                params.put("curp", datosServidor[2]);
                params.put("Dependencia", datosServidor[6]);
                params.put("codigo", datosServidor[5]);
                params.put("unidadAdmin", datosServidor[7]);
                params.put("Plaza", datosServidor[8]);
                params.put("CodigoPuesto", datosServidor[9]);
                params.put("Puesto", datosServidor[10]);

                params.put("ClaveSP", datosServidor[1]);
                params.put("RFC", datosServidor[3]);
                params.put("ClaveISSEMyM", datosServidor[4]);
                params.put("CCT", datosServidor[11]);
                params.put("LPago", datosServidor[12]);

                Date fechaPago_v = new SimpleDateFormat("yyyy/MM/dd").parse(datosServidor[15]);
                String stringFechaPago = DateFor.format(fechaPago_v);

                params.put("FechaPago", stringFechaPago);

                Date periodo1_v = new SimpleDateFormat("yyyy/MM/dd").parse(datosServidor[13]);
                String stringPeriodo1 = DateFor.format(periodo1_v);

                Date periodo2_v = new SimpleDateFormat("yyyy/MM/dd").parse(datosServidor[14]);
                String stringPerido2 = DateFor.format(periodo2_v);

                params.put("PeriodoPago", stringPeriodo1.substring(0, 2) + " - " + stringPerido2);

                double totalNeto = Double.parseDouble(datosRecibo.getTotalNeto());
                params.put("TotalNeto", currency.format(totalNeto));

                List<DetalleReciboDTO> percepcionesSP = ProcesarDetalle(datosRecibo.getPercepciones());

                for (DetalleReciboDTO itemPer : percepcionesSP) {
                    double importeValue = Double.parseDouble(itemPer.getIMPORTE());
                    itemPer.setIMPORTE(currency.format(importeValue));
                }

                List<DetalleReciboDTO> deduccionesSP = ProcesarDetalle(datosRecibo.getDeducciones());
                for (DetalleReciboDTO itemDed : deduccionesSP) {
                    double importeValue = Double.parseDouble(itemDed.getIMPORTE());
                    itemDed.setIMPORTE(currency.format(importeValue));
                }

                params.put("PercepcionesDataset", new JRBeanCollectionDataSource(percepcionesSP));
                params.put("DeduccionesDataset", new JRBeanCollectionDataSource(deduccionesSP));

                double totalPercepciones = Double.parseDouble(datosRecibo.getTotPercepciones());
                params.put("TotalPercepciones", currency.format(totalPercepciones));

                double totalDeducciones = Double.parseDouble(datosRecibo.getTotDeducciones());
                params.put("TotalDeducciones", currency.format(totalDeducciones));

                params.put("NoRecibo", datosRecibo.getNumRecibo());
                params.put("NoCuenta", datosRecibo.getNumCuenta());

                String stringFEchaAbono = "";
                if (!"".equals(datosRecibo.getFechaAbono())) {
                    Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(datosRecibo.getFechaAbono());
                    stringFEchaAbono = DateFor.format(date1);
                }
                params.put("FechaPagoRecibo", stringFEchaAbono);
                params.put("Mensaje", datosRecibo.getMensaje());

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                Document document = new Document(PageSize.LETTER);
                document.setMargins(0, 0, 0, 0);
                // PdfWriter.getInstance(document, new FileOutputStream("FileFormat.pdf"));
                PdfWriter.getInstance(document, byteArrayOutputStream);
                // Open document
                document.open();
                // Add pargraph
                document.add(new Paragraph("FileFormat Developer Guide"));

                // Close document
                document.close();
                itemData = byteArrayOutputStream.toByteArray();

                String path = resourceLoader.getResource("classpath:reports/comprobante_quincenal.jrxml").getURI().getPath();
                String logo_edomex = resourceLoader.getResource("classpath:images/edomexgob.png").getURI().getPath();
                String img_escudo = resourceLoader.getResource("classpath:images/img_escudo.png").getURI().getPath();

                if ("prod".equals(ambienteTrabajo)) {
                    // Servidor
                    path = "/home/reports/comprobante_quincenal.jrxml";
                    logo_edomex = "/home/Images/edomexgob.png";
                    img_escudo = "/home/Images/img_escudo.png";
                }

                params.put("logo_edomex", logo_edomex);
                params.put("img_escudo", img_escudo);

                JasperReport jasperReport = JasperCompileManager.compileReport(path);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
                itemData = JasperExportManager.exportReportToPdf(jasperPrint);

                if (lstdatosRecibo.size() == 1) {
                    data = itemData;

                    IOUtils.closeQuietly(zipOutputStream);
                    IOUtils.closeQuietly(bufferedOutputStream);
                    IOUtils.closeQuietly(byteArrayOutputStream);
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry(datosServidor[1] + "_" + datosServidor[13].replace("/", "-") + ".pdf"));
                    IOUtils.copy(new ByteArrayInputStream(itemData), zipOutputStream);
                }

            }

            //Termina la iteracion 
            if (spComprobante.getLSTSERVIDORCOMPROBANTE().size() > 1) {
                zipOutputStream.finish();
                zipOutputStream.flush();
                IOUtils.closeQuietly(zipOutputStream);

                IOUtils.closeQuietly(bufferedOutputStream);
                IOUtils.closeQuietly(byteArrayOutputStreamZip);
                data = byteArrayOutputStreamZip.toByteArray();
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        return data;
    }

    @Override
    public List<RecibosDTO> consultarComprobantes(String claveServidor, String nombreServidor, String fechainicio, String fechafin, String idServidorSesion, String individual) throws BusException {
        Utils utils = new Utils();
        HashMap<String, Object> params = new HashMap<String, Object>();
        List<RecibosDTO> lstRecibos = new ArrayList<>();
        try {

            List<Object[]> lstResul = comprobantesDAO.consultarComprobantes(
                    claveServidor, nombreServidor, fechainicio, fechafin, 
                    idServidorSesion, individual
            );

            lstResul.forEach(x -> {
                RecibosDTO itemRecibo = new RecibosDTO();

                itemRecibo.setIdServidor(utils.objectIsNULL(x[0]));
                itemRecibo.setnServidor(utils.objectIsNULL(x[1]));
                itemRecibo.setIdPaga(utils.objectIsNULL(x[2]) + (utils.objectIsNULL(x[6]).equals("1") ? " (Cancelada)" : ""));
                itemRecibo.setnPaga(utils.objectIsNULL(x[3]));
                itemRecibo.setFechaInicio(utils.objectIsNULL(x[4]));
                itemRecibo.setFechaFin(utils.objectIsNULL(x[5]));
                itemRecibo.setCancelado(utils.objectIsNULL(x[6]).equals("1"));
                
                lstRecibos.add(itemRecibo);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstRecibos;
    }

    private List<DetalleReciboDTO> ProcesarDetalle(String detalle) {
        List<DetalleReciboDTO> result = new ArrayList();
        if (!"".equals(detalle)) {
            String[] dtPercepcion = detalle.split(Pattern.quote("||"));
            for (String percepcion : dtPercepcion) {

                String[] itemPer = percepcion.split("\\|");
                DetalleReciboDTO addItem = new DetalleReciboDTO();

                addItem.setCLAVE(itemPer[0]);
                addItem.setCONCEPTO(itemPer[1]);
                addItem.setIMPORTE(itemPer[2]);
                result.add(addItem);
            }
        }
        return result;
    }

}
