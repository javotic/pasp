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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import mx.gob.edomex.microservicios.hnomina.dao.ComprobantesDAO;

import mx.gob.edomex.microservicios.hnomina.dto.DetalleDesglosePagoDTO;
import mx.gob.edomex.microservicios.hnomina.dto.DetalleReciboDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ReciboNominaQuincenalDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteListDTO;
import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;
import mx.gob.edomex.microservicios.hnomina.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.hnomina.service.ConsultaPagosService;
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
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class ConsultaPagosServiceImpl implements ConsultaPagosService {

    private static final Logger LOG = LoggerFactory.getLogger(ConsultaPagosServiceImpl.class);

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
    private final String PATH = "/Users/jalvarado/Documents/CAS/Proyectos/EDOMEX/PGO-EDOMEX-2021-PAS/Fase2-Desarrollo/SVN/trunk/Desarrollo_v1.2/microservicios-hnomina/src/main/resources/reports";

    @Override
    public byte[] generarPagoQuincenal(ServidorComprobanteListDTO spComprobante) throws BusException {
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
            SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.constanciaQuincenal ? ").unwrap(SQLServerCallableStatement.class);
            pStmt.setStructured(1, "dbo.reciboNominaTable", sourceDataTable);

            ResultSet resultData = pStmt.executeQuery();

            List<ReciboNominaQuincenalDTO> lstdatosQuincena = new ArrayList();

            while (resultData.next()) {
                ReciboNominaQuincenalDTO itemQuincena = new ReciboNominaQuincenalDTO();

                itemQuincena.setIdServidor(resultData.getString("idServidor"));
                itemQuincena.setFechaI(resultData.getString("fechaI"));
                itemQuincena.setFechaF(resultData.getString("fechaF"));
                itemQuincena.setDatoServidor(resultData.getString("datoServidor"));
                itemQuincena.setDatosPlazaP(resultData.getString("datosPlazaP"));
                itemQuincena.setTotPercepciones(resultData.getString("totPercepciones"));
                itemQuincena.setTotDeducciones(resultData.getString("totDeducciones"));
                itemQuincena.setTotalNeto(resultData.getString("totalNeto"));

                itemQuincena.setDatosDesglosePlazas(resultData.getString("datosDesglosePlazas"));

                lstdatosQuincena.add(itemQuincena);
            }

            conn.close();

            //Iteramos los datos recibidos para armar los PDF 
            for (ReciboNominaQuincenalDTO datosQuincena : lstdatosQuincena) {

                byte[] itemData = null;

                // NumberFormat currency = NumberFormat.getCurrencyInstance();
                DecimalFormat currency = new DecimalFormat("$###,###.###");
                Locale locale = new Locale("es", "MX");
                SimpleDateFormat DateFor = new SimpleDateFormat("dd MMMM yyyy", locale);

                //Iniciamos la construccion del formato
                String[] datosServidor = datosQuincena.getDatoServidor().split("\\|");

                params.put("ClaveSP", datosServidor[0]);
                params.put("Nombre", datosServidor[1]);
                params.put("RFC", datosServidor[2]);

                Date fechaPago_v = new SimpleDateFormat("yyyy/MM/dd").parse(datosServidor[3]);
                String stringFechaPago = DateFor.format(fechaPago_v);

                params.put("FechaPago", stringFechaPago);

                double totalPercepciones = Double.parseDouble(datosQuincena.getTotPercepciones());
                params.put("TotalPercepciones", currency.format(totalPercepciones));

                double totalDeducciones = Double.parseDouble(datosQuincena.getTotDeducciones());
                params.put("TotalDeducciones", currency.format(totalDeducciones));

                double totalNeto = Double.parseDouble(datosQuincena.getTotalNeto());
                params.put("TotalNeto", currency.format(totalNeto));

                //Calculo de plaza Principal
                String datosPlazaP = datosQuincena.getDatosPlazaP();
                String[] arrayDatosPlazaP = datosPlazaP.split("&&");

                //Datos Plaza
                String[] encabezadoPlazaP = arrayDatosPlazaP[0].split("\\|");
                params.put("Plaza", encabezadoPlazaP[0]);
                params.put("CodigoPuesto", encabezadoPlazaP[1]);
                params.put("Puesto", encabezadoPlazaP[2]);
                params.put("codigo", encabezadoPlazaP[3]);
                params.put("CCT", encabezadoPlazaP[4]);
                params.put("LPago", encabezadoPlazaP[5]);

                //Calculo de Percepciones
                String[] percepcionesP = new String[0];
                if (arrayDatosPlazaP.length > 1) {
                    percepcionesP = (("&&" + arrayDatosPlazaP[1] + "&&&").replace("&&PERCEPCION|", "")).replace("|&&&", "").split("\\|PERCEPCION\\|");
                }
                List<DetalleReciboDTO> percepcionesSP = new ArrayList();

                for (String itemPer : percepcionesP) {
                    DetalleReciboDTO percepcion = new DetalleReciboDTO();
                    String[] dataItemPer = itemPer.split("\\|");
                    double importeValue = Double.parseDouble(dataItemPer[1]);
                    percepcion.setCLAVE(dataItemPer[0]);
                    percepcion.setIMPORTE(currency.format(importeValue));

                    percepcionesSP.add(percepcion);
                }
                params.put("PercepcionesDataset", new JRBeanCollectionDataSource(percepcionesSP));

                //Calculo de Percepciones
                String[] deduccionesP = new String[0];
                if (arrayDatosPlazaP.length > 1) {
                    deduccionesP = (("&&" + arrayDatosPlazaP[2] + "&&&").replace("&&DEDUCCION|", "")).replace("|&&&", "").split("\\|DEDUCCION\\|");
                }
                List<DetalleReciboDTO> deduccionesSP = new ArrayList();

                for (String itemDed : deduccionesP) {
                    DetalleReciboDTO deduccion = new DetalleReciboDTO();
                    String[] dataItemDed = itemDed.split("\\|");
                    double importeValue = Double.parseDouble(dataItemDed[1]);
                    deduccion.setCLAVE(dataItemDed[0]);
                    deduccion.setIMPORTE(currency.format(importeValue));

                    deduccionesSP.add(deduccion);
                }
                params.put("DeduccionesDataset", new JRBeanCollectionDataSource(deduccionesSP));

                List<DetalleDesglosePagoDTO> desglosePago = new ArrayList();
                String desglosePlaza = datosQuincena.getDatosDesglosePlazas();
                desglosePago = taskDesglosePlaza(desglosePlaza);

                params.put("DesglosePagosDataSet", new JRBeanCollectionDataSource(desglosePago));

                //  params.put("DETALLEPERCEPCIONES", new JRBeanCollectionDataSource(percepcionesSP));
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

                String path = resourceLoader.getResource("classpath:reports/pago_quincenal.jrxml").getURI().getPath();
                String logo_edomex = resourceLoader.getResource("classpath:images/edomexgob.png").getURI().getPath();
                String img_escudo = resourceLoader.getResource("classpath:images/img_escudo.png").getURI().getPath();

//                if ("prod".equals(ambienteTrabajo)) {
//                    // Servidor
//                    path = "/home/reports/pago_quincenal.jrxml";
//                    logo_edomex = "/home/Images/edomexgob.png";
//                    img_escudo = "/home/Images/img_escudo.png";
//                }

                params.put("logo_edomex", logo_edomex);
                params.put("img_escudo", img_escudo);

                JasperReport jasperReport = JasperCompileManager.compileReport(path);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
                itemData = JasperExportManager.exportReportToPdf(jasperPrint);

                if (lstdatosQuincena.size() == 1) {
                    data = itemData;

                    IOUtils.closeQuietly(zipOutputStream);
                    IOUtils.closeQuietly(bufferedOutputStream);
                    IOUtils.closeQuietly(byteArrayOutputStream);
                } else {
                    zipOutputStream.putNextEntry(new ZipEntry("Quincenal_" + datosServidor[0] + "_" + datosServidor[3].replace("/", "-") + ".pdf"));
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
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }

        return data;
    }

    /*
    @Override
    public List<RecibosDTO> consultarComprobantes(String claveServidor, String nombreServidor, String fechainicio, String fechafin, String idServidorSesion, String individual) throws BusException{
         Utils utils = new Utils();
            HashMap<String, Object> params = new HashMap<String, Object>();
            List<RecibosDTO> lstRecibos = new ArrayList<>();
            try {

                    List<Object[]> lstResul = comprobantesDAO.consultarComprobantes(claveServidor, nombreServidor, fechainicio, fechafin,idServidorSesion, individual);
                  
                    lstResul.forEach(x -> {                     
                            RecibosDTO itemRecibo = new RecibosDTO();
         
                            itemRecibo.setIdServidor(utils.objectIsNULL(x[0]));
                            itemRecibo.setnServidor(utils.objectIsNULL(x[1]));
                            itemRecibo.setIdPaga(utils.objectIsNULL(x[2]));
                            itemRecibo.setnPaga(utils.objectIsNULL(x[3]));
                            itemRecibo.setFechaInicio(utils.objectIsNULL(x[4]));
                            itemRecibo.setFechaFin(utils.objectIsNULL(x[5]));
                            lstRecibos.add(itemRecibo);
                    });   
                    
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstRecibos;
    }

     */
    private List<DetalleDesglosePagoDTO> taskDesglosePlaza(String desglosePlaza) {
        DecimalFormat currency = new DecimalFormat("$###,###.###");
        List<DetalleDesglosePagoDTO> result = new ArrayList();

        String reservaTotales;
        Boolean esPrimeraPlaza = true;

        //Limpiamos el primer prefijo  PLAZA|
        desglosePlaza = "&|" + desglosePlaza;
        desglosePlaza = desglosePlaza.replace("&|PLAZA|", "");

        // Separamos las plazas con el prefijo |PLAZA|
        String[] itemPlaza = desglosePlaza.split("&&PLAZA\\|");

        for (String datosPlaza : itemPlaza) {
            result.add(insertRowDesglose(3, ""));
            //Insertamos titulos de encabezados segun formato
            result.add(insertRowDesglose(1, ""));
            //Separamos las secciones de la plaza 
            String[] seccionesPlaza = datosPlaza.split("&&");

            //Iniciamos agregando los datos de la primera seccion (datos de la plaza
            //Agregamos datos encabezado
            result.add(insertRowDesglose(2, seccionesPlaza[0]));
            reservaTotales = seccionesPlaza.length >= 4 ? seccionesPlaza[3] : "";
            //Ingresamos un salto de linea segun formato
            result.add(insertRowDesglose(3, ""));

            //Agragamos encabezados totales
            if (esPrimeraPlaza) {
                result.add(insertRowDesglose(4, ""));
                result.add(insertRowDesglose(5, ""));
                esPrimeraPlaza = false;
            }

            //Se limpia y separan los datos de deducciones y percepciones
            String percepcionesSeccion = (("&&&" + (seccionesPlaza.length >= 2 ? seccionesPlaza[1] : "") + "&&&").replace("&&&PERCEPCION|", "")).replace("|&&&", "");
            String deduccionesSeccion = (("&&&" + (seccionesPlaza.length >= 3 ? seccionesPlaza[2] : "") + "&&&").replace("&&&DEDUCCION|", "")).replace("&&&", "");
            String[] dataPercepciones = percepcionesSeccion.split("\\|PERCEPCION\\|");
            String[] dataDeducciones = deduccionesSeccion.split("\\|DEDUCCION\\|");

            int tamanioDetalle = dataDeducciones.length;

            if (dataPercepciones.length > tamanioDetalle) {
                tamanioDetalle = dataPercepciones.length;
            }

            for (int y = 0; y < tamanioDetalle; y++) {
                String detalleDed = "|";
                String detallePer = "|";

                if (dataDeducciones.length > y) {
                    String[] formatDeducciones = dataDeducciones[y].split("\\|");
                    double deduccionCurrency = Double.parseDouble((formatDeducciones.length >= 2 ? formatDeducciones[1] : "0.0"));
                    detalleDed = (formatDeducciones.length >= 1 ? formatDeducciones[0] : "0.0") + "|" + currency.format(deduccionCurrency);
                }

                if (dataPercepciones.length > y) {
                    String[] formatPercepciones = dataPercepciones[y].split("\\|");
                    double percepcionCurrency = Double.parseDouble((formatPercepciones.length >= 2 ? formatPercepciones[1] : "0.0"));
                    detallePer = (formatPercepciones.length >= 1 ? formatPercepciones[0] : "0.0") + "|" + currency.format(percepcionCurrency);
                }

                result.add(insertRowDesglose(6, detallePer + "|" + detalleDed));

            }
            result.add(insertRowDesglose(3, ""));
            String[] dataTotales = reservaTotales.split("\\|");
            double totalPer = Double.parseDouble((dataTotales.length >= 2 ? dataTotales[1] : "0.0"));
            double totalDed = Double.parseDouble((dataTotales.length >= 4 ? dataTotales[3] : "0.0"));
            double totalNeto = Double.parseDouble((dataTotales.length >= 6 ? dataTotales[5] : "0.0"));
            result.add(insertRowDesglose(7, currency.format(totalPer) + "|"
                    + currency.format(totalDed) + "|"
                    + currency.format(totalNeto) + "|" + ""));

        }

        return result;
    }

    private DetalleDesglosePagoDTO insertRowDesglose(int orden, @Nullable String columnas) {
        DetalleDesglosePagoDTO itemDetalle = new DetalleDesglosePagoDTO();
        String[] columns = columnas.split("\\|");

        itemDetalle.setORDEN(String.valueOf(orden));
        itemDetalle.setCOLUMN1(columns.length > 0 ? columns[0] : "");
        itemDetalle.setCOLUMN2(columns.length > 1 ? columns[1] : "");
        itemDetalle.setCOLUMN3(columns.length > 2 ? columns[2] : "");
        itemDetalle.setCOLUMN4(columns.length > 3 ? columns[3] : "");
        itemDetalle.setCOLUMN5(columns.length > 4 ? columns[4] : "");
        itemDetalle.setCOLUMN6(columns.length > 5 ? columns[5] : "");

        return itemDetalle;
    }

}
