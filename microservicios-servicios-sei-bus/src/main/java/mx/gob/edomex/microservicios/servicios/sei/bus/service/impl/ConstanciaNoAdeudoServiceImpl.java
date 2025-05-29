package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ConstanciaNoAdeudoDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConfiguracionHistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConstanciaNoAdeudoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudConstanciaNoAdeudoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ConstanciaNoAdeudoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.GenerarPDFService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de servicio de constancias de no adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 16/12/2021.
 */
@Service
public class ConstanciaNoAdeudoServiceImpl implements ConstanciaNoAdeudoService {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(ConstanciaNoAdeudoServiceImpl.class);
    public final String DOCUMENTO_EXPEDICION_FORMATO_FECHA = "'a' dd 'de' MMMM 'del' YYYY";
    public final String DOCUMENTO_FORMATO_FECHA = "dd/MM//YYYY";
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private ConstanciaNoAdeudoDAO constanciaNoAdeudoDAO;
    @Autowired
    private GenerarPDFService generarPDFService;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public List<SolicitudConstanciaNoAdeudoModel> obtenerSolicitudes(String claveServidor, String nombreServidor, String claveServidorBusqueda, int banderaBusqueda) throws BusException {
        List<SolicitudConstanciaNoAdeudoModel> lsSolicitudConstancia = new ArrayList<>();
        Utils utils = new Utils();
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        List<String> excepciones = new ArrayList<>();

        try {
            List<Object[]> lsResultado = constanciaNoAdeudoDAO.obtenerSolicitudes(
                    claveServidor,
                    nombreServidor,
                    claveServidorBusqueda,
                    banderaBusqueda
            );

            lsResultado.forEach(solicitud -> {
                try {
                    lsSolicitudConstancia.add(new SolicitudConstanciaNoAdeudoModel(
                            Integer.valueOf(utils.objectIsNULL(solicitud[0])),//Folio
                            utils.objectIsNULL(solicitud[1]),//Clave de servidor publico
                            utils.objectIsNULL(solicitud[2]),//Nombre de servidor publico
                            parseador.parse(utils.objectIsNULL(solicitud[3])),//Fecha de solicitud
                            utils.objectIsNULL(solicitud[4]),//Estatus
                            dateFormat.format( parseador.parse(utils.objectIsNULL(solicitud[3])))//Fecha de solicitud cadena
                    ));
                } catch (ParseException ex) {
                    excepciones.add("Error en solicitud con folio "
                            + utils.objectIsNULL(solicitud[0])
                            + ". Error: "
                            + ex.getMessage()
                    );
                }
            });

            //Si existe algun error, se notifica.
            if (excepciones.size() > 0) {

                StringBuilder builder = new StringBuilder();
                excepciones.forEach(e -> builder.append(e));

                throw new InternalServerErrorException(
                        Constantes.STATUS_FAILURE_SERVICE,
                        "Se han presentando errores al momento de "
                        + "obtener la información, favor de validar."
                        + builder.toString());
            }
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return lsSolicitudConstancia;
    }

    @Override
    public boolean insertarSolicitud(String claveServidor, String comentarios) throws BusException {
        int resultado = constanciaNoAdeudoDAO.insertarSolicitud(
                claveServidor,
                comentarios
        );
        return resultado == 1;
    }

    @Override
    public byte[] generarDocumento(String claveServidorPublico, int folio) throws BusException {
        byte[] documentoConstancia = null;
        try {
            //Obtener parametros configurbles de solicitud
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel
                    = this.generarPDFService.obtenerConfiguracionDocumento();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Generar documento
            Document documento = new Document(PageSize.LETTER, 30, 30, 10, 20);
            PdfWriter writer = PdfWriter.getInstance(documento, baos);
            //Iniciar con la creacion del documento
            documento.open();

            //Generar contenido de documento.
            this.generarContenido(
                    documento,
                    claveServidorPublico,
                    folio,
                    configuracionHistorialLaboralModel
            );

            //Cerrar documento y finalizar
            documento.close();
            writer.close();

            documentoConstancia = baos.toByteArray();
            IOUtils.closeQuietly(baos);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(ConstanciaNoAdeudoServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return documentoConstancia;
    }
    
    @Override
    public byte[] obtenerDocumentoMeta(int idConsulta) throws BusException {
        byte[] documento = null;

        //Realizar la consulta de la informacion de meta
        List<Object[]> lsResultado = constanciaNoAdeudoDAO.obtenerDocumentoConstanciaMeta(
                idConsulta
        );
        //Validar existencia de resultados para obtencion de documento
        if (lsResultado != null && lsResultado.size() > 0) {
            Object[] resultado = lsResultado.get(0);
            documento = (byte[]) resultado[2];
        }
        
        return documento;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones privadas" defaultstate="collapsed">
    /**
     * Generar contenido de documento.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 17/12/2021.
     * @param documento Documento a descargar.
     * @param claveServidorPublico Clave de servidor publico del que se obtendra
     * la informacion.
     * @param folio Folio de solicitud de constancia de no adeudo.
     * @param configuracionHistorialLaboralModel Configuracion general de armado
     * de documneto.
     * @throws InternalServerErrorException de error interno de procesamiento.
     * @throws IOException de error de lectura o escritura de documento.
     * @throws DocumentException de error generacion de documento.
     */
    private void generarContenido(Document documento, String claveServidorPublico,
            int folio, ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel)
            throws InternalServerErrorException, IOException, DocumentException, BusException {
        //Obtener datos desde BD
        ConstanciaNoAdeudoModel constanciaNoAdeudoModel = this.obtenerDatosConstanciaNoAdedudo(
                claveServidorPublico,
                folio
        );

        //Generar oficio
        this.generarOficio(
                documento,
                constanciaNoAdeudoModel,
                configuracionHistorialLaboralModel
        );

        //Agregar nueva pagina
        documento.newPage();

        //Identificar tipo de adeudo y generar documento
        if (constanciaNoAdeudoModel.getIdAdeudo() == 0) {
            //Generar constancia
            this.generarConstanciaSinAdeudo(
                    documento,
                    constanciaNoAdeudoModel,
                    configuracionHistorialLaboralModel
            );

        } else {
            //Generar constancia
            this.generarConstanciaConAdeudo(
                    documento,
                    constanciaNoAdeudoModel,
                    configuracionHistorialLaboralModel
            );
        }
    }

    /**
     * Obtener datos de constancia de no adeudo desde base de datos.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/12/2021.
     * @param claveServidorPublico Clave de servidor publico del que se obtendra
     * la informacion.
     * @param folio Folio de solicitud de constancia de no adeudo.
     * @throws InternalServerErrorException de error interno de procesamiento.
     */
    private ConstanciaNoAdeudoModel obtenerDatosConstanciaNoAdedudo(
            String claveServidorPublico, int folio) throws InternalServerErrorException {

        ConstanciaNoAdeudoModel constanciaNoAdeudoModel = new ConstanciaNoAdeudoModel();
        Utils utils = new Utils();

        try {
            //Obtener datos desde BD
            List<Object[]> lsResultado = constanciaNoAdeudoDAO.obtenerDatosConstanciaNoAdeudo(
                    claveServidorPublico,
                    folio
            );
            //Validar presencia de informacion
            if (lsResultado != null && lsResultado.size() > 0) {

                //Convertir objeto de datos a modelo
                Object[] constancia = lsResultado.get(0);

                constanciaNoAdeudoModel = new ConstanciaNoAdeudoModel(
                        utils.objectIsNULL(constancia[0]), //Clave de SP
                        utils.objectIsNULL(constancia[1]), //Nombre de SP
                        Integer.valueOf(utils.objectIsNULL(constancia[2])), //Id de tipo de adeudo
                        utils.objectIsNULL(constancia[3]), //Parrafo de oficio
                        utils.objectIsNULL(constancia[4]), //Parrafo de constancia
                        Double.valueOf(utils.objectIsNULL(constancia[5])), //Monto de adeudo
                        utils.objectIsNULL(constancia[6]),//Percepciones
                        utils.objectIsNULL(constancia[7]), //Deducciones
                        Double.valueOf(utils.objectIsNULL(constancia[8])), //Total percepciones
                        Double.valueOf(utils.objectIsNULL(constancia[9])), //Total deducciones
                        utils.objectIsNULL(constancia[10]), //Unidad administrativa
                        utils.objectIsNULL(constancia[11]), //Ubicacion
                        utils.objectIsNULL(constancia[12]), //Categoria
                        utils.objectIsNULL(constancia[13]), //Dependencia
                        utils.objectIsNULL(constancia[14]),//Corresponde
                        utils.objectIsNULL(constancia[15]),//folio
                        utils.objectIsNULL(constancia[16])//Observaciones
                );

            }
        } catch (Exception e) {
            LOG.error("ERROR: ", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return constanciaNoAdeudoModel;
    }

    /**
     * Generar hoja de oficio sin adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 21/12/2021.
     * @param documento Documento que se esta generando para su descarga.
     * @param constanciaNoAdeudoModel Datos de constancia de no adeudo desde
     * META.
     * @param configuracionHistorialLaboralModel Configuracion general de
     * encabezado y pie de pagina.
     * @throws IOException de lectura y escritura de informacion.
     * @throws DocumentException de armado de documento.
     * @throws BusException de procesamiento general.
     */
    private void generarOficio(Document documento,
            ConstanciaNoAdeudoModel constanciaNoAdeudoModel,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel)
            throws IOException, DocumentException, BusException {
        //Encabezado de documento
        this.generarPDFService.generarEncabezado(
                documento,
                configuracionHistorialLaboralModel
        );

        //Fecha
        this.generarOficioFecha(documento, constanciaNoAdeudoModel.getFolio());

        //Generar Solicitante
        this.generarOficioSolicitante(documento, constanciaNoAdeudoModel.getCorresponde());

        //Generar contenido
        this.generarOficioContenido(documento, constanciaNoAdeudoModel.getParrafoOficio());

        //Cierre
        this.generarOficioCierre(documento);

        //Pie de pagina de documento
        this.generarPDFService.generarPiePagina(
                documento,
                configuracionHistorialLaboralModel
        );
    }

    /**
     * Generar seccion de oficio de fecha de documento.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 21/12/2021.
     * @param documento Documento que se esta generando para su descarga.
     * @param oficio Numero de oficio.
     * @throws DocumentException de armado de documento.
     */
    private void generarOficioFecha(Document documento, String oficio) throws DocumentException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);

        //Numero de oficio
        Paragraph p = new Paragraph(new Chunk("Oficio núm: " + oficio, font));
        p.setAlignment(Element.ALIGN_RIGHT);
        documento.add(p);
        //Lugar
        p = new Paragraph(new Chunk("Toluca, Estado de México", font));
        p.setAlignment(Element.ALIGN_RIGHT);
        documento.add(p);
        //Fecha
        p = new Paragraph(new Chunk(
                new SimpleDateFormat(this.DOCUMENTO_EXPEDICION_FORMATO_FECHA, new Locale("es", "ES"))
                        .format(new Date()), font)
        );
        p.setAlignment(Element.ALIGN_RIGHT);
        documento.add(p);

        documento.add(Chunk.NEWLINE);
    }

    /**
     * Generar seccion de oficio del solicitante.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 21/12/2021.
     * @param documento Documento que se esta generando para su descarga.
     * @param datosCorresponde Datos de seccion de "Corresponde"
     * @throws DocumentException de armado de documento.
     * @throws BusException de errores de procesamiento generales.
     */
    private void generarOficioSolicitante(Document documento,
            String datosCorresponde) throws DocumentException,
            BusException {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
        //Si no existen datos en corresponde, se manda excepcion
        if (datosCorresponde == null || datosCorresponde.isEmpty()) {
            throw new InternalServerErrorException(Constantes.STATUS_FAILURE_SERVICE,
                    "Seccion de datos de correspondencia no "
                    + "existentes desde plataforma de nómina, favor de "
                    + "contactar al área de soporte.");
        }
        //Se divide por pipes
        String[] secciones = datosCorresponde.split("\\|");
        //Para cada registro existente, se genera su parrafo
        for (String seccion : secciones) {
            //Si la palabra es muy larga, se divide en palabras
            if (seccion.trim().length() > 40) {
                String[] palabras = seccion.trim().toUpperCase().split(" ");
                StringBuilder sb = new StringBuilder();
                for (String palabra : palabras) {
                    if (sb.length() + palabra.length() < 40) {
                        sb.append(palabra);
                        sb.append(" ");
                    } else {
                        Paragraph p = new Paragraph(new Chunk(sb.toString(), font));
                        documento.add(p);
                        sb = new StringBuilder(palabra);
                        sb.append(" ");
                    }
                }
                Paragraph p = new Paragraph(new Chunk(sb.toString(), font));
                documento.add(p);
            } else {
                Paragraph p = new Paragraph(new Chunk(seccion.trim().toUpperCase(), font));
                documento.add(p);
            }
        }

        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
    }

    /**
     * Generar seccion de oficio del contenido.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 21/12/2021.
     * @param documento Documento que se esta generando para su descarga.
     * @param datosParrafoOficio Datos del parrafo de oficio.
     * @throws DocumentException de armado de documento.
     * @throws BusException de errores de procesamiento generales.
     */
    private void generarOficioContenido(Document documento,
            String datosParrafoOficio) throws BusException, DocumentException {
        String[] parrafos;
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
        parrafos = datosParrafoOficio.split("\\|");
        if (parrafos == null || parrafos.length != 2) {
            throw new InternalServerErrorException(Constantes.STATUS_FAILURE_SERVICE,
                    "Seccion de datos de contenido de oficio no existentes"
                    + " o incompletos desde plataforma de nómina, favor de"
                    + " contactar al área de soporte.");
        }

        //Genear seccion de "En atencion"
        Paragraph p = new Paragraph(new Chunk(parrafos[0].trim().replaceAll("\\s+", " "), font));
        documento.add(p);

        //Espacio de documento
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);

        //Generar seccion de "Sin otro particular"
        p = new Paragraph(new Chunk(parrafos[1].trim().replaceAll("\\s+", " "), font));
        documento.add(p);
        documento.add(Chunk.NEWLINE);
        documento.add(Chunk.NEWLINE);
    }

    /**
     * Generar seccion de oficio del cierre.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 21/12/2021.
     * @param documento Documento que se esta generando para su descarga.
     * @throws DocumentException de armado de documento.
     */
    private void generarOficioCierre(Document documento) throws DocumentException {
        Paragraph parrafoCierre = new Paragraph(Constantes.DOCUMENTO_PIE_PAGINA_ATENTAMENTE);
        parrafoCierre.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafoCierre);
        documento.add(Chunk.NEWLINE);

        parrafoCierre = new Paragraph(Constantes.DOCUMENTO_PIE_PAGINA_JESUS_ACEVEDO);
        parrafoCierre.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafoCierre);

        parrafoCierre = new Paragraph(Constantes.DOCUMENTO_PIE_PAGINA_SUBDIRECTOR_CONTROL_PAGOS);
        parrafoCierre.setAlignment(Element.ALIGN_CENTER);
        documento.add(parrafoCierre);
    }

    /**
     * Generar seccion de constancia de no adeudo.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 22/12/2021.
     * @param documento Documento que se esta generando para su descarga.
     * @param constanciaNoAdeudoModel Datos de constancia de no adeudo.
     * @param configuracionHistorialLaboralModel Configuracion general de datos
     * de generacion de documentos.
     * @throws IOException de lectura y escritura de datos.
     * @throws DocumentException de armado de documento.
     * @throws BusException de errores de procesamiento generales.
     */
    private void generarConstanciaSinAdeudo(Document documento,
            ConstanciaNoAdeudoModel constanciaNoAdeudoModel,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel)
            throws IOException, DocumentException, BusException {
        Font fuenteContenido = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
        Font fuenteItalica = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 11, BaseColor.BLACK);
        Font fuenteNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.BLACK);

        //Encabezado de documento
        this.generarPDFService.generarEncabezado(
                documento,
                configuracionHistorialLaboralModel
        );

        String[] secciones = constanciaNoAdeudoModel.getParrafoConstancia().split("\\|");
        if (secciones == null || secciones.length != 6) {
            throw new InternalServerErrorException(Constantes.STATUS_FAILURE_SERVICE,
                    "Seccion de datos de contenido de constancia no existentes"
                    + " o incompletos desde plataforma de nómina, favor de"
                    + " contactar al área de soporte.");
        }

        //Genear Titulo
        this.generarParrafo(
                secciones[0],
                Element.ALIGN_CENTER,
                4,
                documento,
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK)
        );

        //Seccion de suscribe
        this.generarParrafo(
                secciones[1],
                Element.ALIGN_JUSTIFIED,
                2, documento, fuenteContenido
        );

        //Seccion de detalle de constancia
        this.generarParrafo(
                secciones[2],
                Element.ALIGN_JUSTIFIED,
                2, documento, fuenteContenido
        );

        //Seccion de supuestos
        this.generarParrafo(
                secciones[3].trim() + " " + secciones[4].trim() + " ",
                Element.ALIGN_JUSTIFIED,
                2, documento, fuenteItalica
        );

        //Seccion de expedicion
        this.generarParrafo(
                secciones[5].trim(),
                Element.ALIGN_JUSTIFIED,
                2, documento, fuenteNegrita
        );

        //Cierre
        this.generarOficioCierre(documento);

        //Pie de pagina de documento
        this.generarPDFService.generarPiePagina(
                documento,
                configuracionHistorialLaboralModel
        );
    }

    private void generarConstanciaConAdeudo(Document documento,
            ConstanciaNoAdeudoModel constanciaNoAdeudoModel,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel)
            throws IOException, DocumentException, BusException {
        //Encabezado de documento
        this.generarPDFService.generarEncabezado(
                documento,
                configuracionHistorialLaboralModel
        );

        //Generar primera seccion de constancia de adeudo
        this.generarConstanciaAdeudoEncabezado(documento, constanciaNoAdeudoModel);

        //Genear seccion de percepciones y deducciones
        this.generarConstanciaAdeudoConceptos(documento, constanciaNoAdeudoModel);

        //Cierre
        this.generarOficioCierre(documento);

        //Pie de pagina de documento
        this.generarPDFService.generarPiePagina(
                documento,
                configuracionHistorialLaboralModel
        );
    }

    /**
     * Generar nuevo parrafo para incluir en el texto.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 22/12/2021.
     * @param texto Texto a insertar.
     * @param alineacionTexto Alineacion de parrafo.
     * @param numeroEspacios Numero de espacios previos al terminar.
     * @param documento Documento al que se insertara el parrafo.
     * @param fuente Fuente del parrafo.
     * @throws DocumentException de armado de documento.
     */
    private void generarParrafo(String texto, int alineacionTexto, int numeroEspacios,
            Document documento, Font fuente) throws DocumentException {
        Paragraph p = new Paragraph(
                texto.trim(),
                fuente
        );
        p.setAlignment(alineacionTexto);
        documento.add(p);
        for (int i = 0; i < numeroEspacios; i++) {
            documento.add(Chunk.NEWLINE);
        }

    }

    private void generarConstanciaAdeudoEncabezado(Document documento,
            ConstanciaNoAdeudoModel constanciaNoAdeudoModel) throws DocumentException {

        Font fuenteTituloNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);
        Font fuenteContenidoNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.BLACK);

        //Tabla de fecha de emision 
        PdfPTable table = new PdfPTable(2);
        table.setTotalWidth(250);
        table.setLockedWidth(true);
        table.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(
                new Phrase("FECHA DE EMISION",
                        fuenteContenidoNegrita
                )
        );
        table.addCell(new Phrase(
                new SimpleDateFormat(this.DOCUMENTO_FORMATO_FECHA, new Locale("es", "ES"))
                        .format(new Date()),
                FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.BLACK))
        );
        documento.add(table);

        //Datos de servidor publico.
        //Nombre
        table = new PdfPTable(3);
        table.setLockedWidth(true);
        table.setTotalWidth(500);

        table.addCell(this.generarCelda(
                "NOMBRE DE SERVIDOR PÚBLICO",
                fuenteTituloNegrita,
                3, 1, 0, 10, 10, null)
        );
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getNombreServidorPublico().trim(),
                fuenteTituloNegrita,
                3, 1, 0, 10, 10, BaseColor.GRAY)
        );

        //Titulos clave sp y adscripcion
        table.addCell(this.generarCelda(
                "CLAVE DE SERVIDOR PÚBLICO",
                fuenteContenidoNegrita,
                0, 1, 0, 20, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "",
                fuenteContenidoNegrita,
                0, 1, 0, 20, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "ADSCRIPCIÓN",
                fuenteContenidoNegrita,
                0, 1, 0, 20, 5, BaseColor.WHITE)
        );
        //Valores de SP y adscripcion
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getClaveServidorPublico().trim(),
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                "",
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getUnidadAdministrativa().trim(),
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.GRAY)
        );

        //Titulos de ubicacion, categoria y dependencia
        table.addCell(this.generarCelda(
                "UBICACIÓN",
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "CATEGORIA",
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "DEPENDENCIA",
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.WHITE)
        );
        //Valores de ubicacion, categoria y dependencia
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getUbicacion().trim(),
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getCategoria().trim(),
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getDependencia().trim(),
                fuenteContenidoNegrita,
                0, 1, 0, 5, 5, BaseColor.GRAY)
        );

        table.addCell(this.generarCelda(
                " ",
                fuenteContenidoNegrita,
                3, 1, 0, 5, 5, BaseColor.WHITE)
        );

        documento.add(table);
    }

    private void generarConstanciaAdeudoConceptos(Document documento,
            ConstanciaNoAdeudoModel constanciaNoAdeudoModel) throws DocumentException, InternalServerErrorException {
        Font fuenteContenidoNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.BLACK);
        Font fuenteContenidoNormal = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.BLACK);
        Font fuenteTituloNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, BaseColor.BLACK);

        PdfPTable table = new PdfPTable(4);
        table.setLockedWidth(true);
        table.setTotalWidth(500);

        //Titulos de percepciones y deducciones
        table.addCell(this.generarCelda(
                "PERCEPCIONES",
                fuenteContenidoNegrita,
                2, 1, 0, 20, 20, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                "DEDUCCIONES",
                fuenteContenidoNegrita,
                2, 1, 0, 20, 20, BaseColor.GRAY)
        );
        //Titulos de clave e importes
        table.addCell(this.generarCelda(
                "CLAVE",
                fuenteContenidoNormal,
                1, 1, -1, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "IMPORTE",
                fuenteContenidoNormal,
                1, 1, -1, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "CLAVE",
                fuenteContenidoNormal,
                1, 1, -1, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                "IMPORTE",
                fuenteContenidoNormal,
                1, 1, -1, 5, 5, BaseColor.WHITE)
        );

        //Percepciones y deducciones
        this.generarConceptos(table, constanciaNoAdeudoModel, fuenteContenidoNegrita);

        //Totales
        table.addCell(this.generarCelda(
                "TOTAL PERCEPCIONES",
                fuenteContenidoNegrita,
                1, 1, -1, 5, 5, BaseColor.GRAY)
        );

        table.addCell(this.generarCelda(
                String.valueOf(NumberFormat.getCurrencyInstance(
                        new Locale("en", "US")).format(constanciaNoAdeudoModel.getTotalPercepciones())),
                fuenteContenidoNegrita,
                1, 1, -1, 5, 5, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                "TOTAL DEDUCCIONES",
                fuenteContenidoNegrita,
                1, 1, -1, 5, 5, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                String.valueOf(NumberFormat.getCurrencyInstance(
                        new Locale("en", "US")).format(constanciaNoAdeudoModel.getTotalDeducciones())),
                fuenteContenidoNegrita,
                1, 1, -1, 5, 5, BaseColor.GRAY)
        );

        //Total adeudo
        table.addCell(this.generarCelda(
                "TOTAL ADEUDO",
                fuenteContenidoNegrita,
                1, 1, -1, 5, 5, BaseColor.WHITE)
        );
        table.addCell(this.generarCelda(
                String.valueOf(NumberFormat.getCurrencyInstance(
                        new Locale("en", "US")).format(constanciaNoAdeudoModel.getMontoAdeudo())),
                fuenteTituloNegrita,
                3, 1, -1, 5, 5, BaseColor.WHITE)
        );

        //Observaciones
        table.addCell(this.generarCelda(
                "OBSERVACIONES",
                fuenteContenidoNegrita,
                1, 1, -1, 5, 5, BaseColor.GRAY)
        );
        table.addCell(this.generarCelda(
                constanciaNoAdeudoModel.getObservaciones(),
                fuenteContenidoNegrita,
                3, 1, -1, 5, 5, BaseColor.GRAY)
        );

        documento.add(table);

    }

    /**
     * Genear una celda de texto.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 23/12/2021.
     * @param texto Texto que se mostrara en la celda.
     * @param fuente Tipo de fuenta con la que se generara la celda.
     * @param colspan Cantidad de columnas a unir de la tabla.
     * @param alineacion Alineacion del texto dentro de la celda.
     * @param bordeCelda Borde de la celda.
     * @param paddingSuperior Padding superior de la celda.
     * @param paddingInferior Padding inferior de la celda.
     * @param colorFondo Color de fondo de la celda.
     * @return Celda generada a insertar en tabla.
     */
    private PdfPCell generarCelda(String texto, Font fuente, int colspan,
            int alineacion, int bordeCelda, int paddingSuperior,
            int paddingInferior, BaseColor colorFondo) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(alineacion);
        celda.setBorderColor(BaseColor.BLACK);
        celda.setPaddingTop(paddingSuperior);
        celda.setPaddingBottom(paddingInferior);
        if (colspan > 1) {
            celda.setColspan(colspan);
        }
        if (colorFondo != null) {
            celda.setBackgroundColor(colorFondo);
        }
        if (bordeCelda > -1) {
            celda.setBorder(bordeCelda);
        }
        return celda;
    }

    private void generarConceptos(PdfPTable table,
            ConstanciaNoAdeudoModel constanciaNoAdeudoModel,
            Font fuente) throws InternalServerErrorException {
        String[] percepciones = constanciaNoAdeudoModel.getPercepciones().split("\\|");
        String[] deducciones = constanciaNoAdeudoModel.getDeducciones().split("\\|");

        //Validar que tanto las percepciones como deducciones sean persistentes
        if (percepciones.length % 2 > 0) {
            throw new InternalServerErrorException(Constantes.STATUS_FAILURE_SERVICE,
                    "Las percepciones obtenidas no coinciden con clave y monto, "
                    + "favor de contactar al área de soporte.");
        }
        if (deducciones.length % 2 > 0) {
            throw new InternalServerErrorException(Constantes.STATUS_FAILURE_SERVICE,
                    "Las deducciones obtenidas no coinciden con clave y monto, "
                    + "favor de contactar al área de soporte.");
        }

        //Convertir arreglos a lista de forma mutable
        List<String> lsPercepciones = new ArrayList<>();
        List<String> lsDeducciones = new ArrayList<>();
        lsPercepciones.addAll(Arrays.asList(percepciones));
        lsDeducciones.addAll(Arrays.asList(deducciones));

        //Si hay mas percepciones que deducciones
        if (lsPercepciones.size() > lsDeducciones.size()) {
            for (int i = lsDeducciones.size(); i < lsPercepciones.size(); i++) {
                lsDeducciones.add("---");
            }
        } else if (lsPercepciones.size() < lsDeducciones.size()) {
            //Si hay mas deducciones que percepciones
            for (int i = lsPercepciones.size(); i < lsDeducciones.size(); i++) {
                lsPercepciones.add("---");
            }
        }

        //Obtener el valor de celdas, siendo estas el total de conceptos entre dos
        int totalCeldas = lsPercepciones.size() / 2;
        int indicePercepciones = 0;
        int indiceDeducciones = 0;
        String valorPercepcion = "";
        String valorDeduccion = "";

        for (int celdaActual = 0; celdaActual < totalCeldas; celdaActual++) {
            //Agregar clave de percepcion
            table.addCell(this.generarCelda(
                    lsPercepciones.get(indicePercepciones),
                    fuente,
                    1, 1, -1, 5, 5, BaseColor.WHITE)
            );
            indicePercepciones++;

            //Agregar monto de percepcion
            valorPercepcion = lsPercepciones.get(indicePercepciones).equals("---")
                    ? lsPercepciones.get(indicePercepciones)
                    : String.valueOf(NumberFormat.getCurrencyInstance(
                            new Locale("en", "US")).format(
                                    Double.parseDouble(lsPercepciones.get(indicePercepciones))
                            ));

            table.addCell(this.generarCelda(
                    valorPercepcion,
                    fuente,
                    1, 1, -1, 5, 5, BaseColor.WHITE)
            );
            indicePercepciones++;

            //Agregar clave de deduccion
            table.addCell(this.generarCelda(
                    lsDeducciones.get(indiceDeducciones),
                    fuente,
                    1, 1, -1, 5, 5, BaseColor.WHITE)
            );
            indiceDeducciones++;

            //Agregar monto de deduccion
            valorDeduccion = lsDeducciones.get(indiceDeducciones).equals("---")
                    ? lsDeducciones.get(indiceDeducciones)
                    : String.valueOf(NumberFormat.getCurrencyInstance(
                            new Locale("en", "US")).format(
                                    Double.parseDouble(lsDeducciones.get(indiceDeducciones))
                            ));
            table.addCell(this.generarCelda(
                    valorDeduccion,
                    fuente,
                    1, 1, -1, 5, 5, BaseColor.WHITE)
            );
            indiceDeducciones++;
        }

    }
    //</editor-fold>
}
