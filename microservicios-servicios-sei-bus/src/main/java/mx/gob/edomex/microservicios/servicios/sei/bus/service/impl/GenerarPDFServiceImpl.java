package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.GenerarPdfDao;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConfiguracionHistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.GenerarPDFService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

/**
 * Implementacion de servicio que permite la generacion de PDFs de forma
 * general.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 17/12/2021.
 */
@Service("GenerarPDFService")
public class GenerarPDFServiceImpl implements GenerarPDFService {

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private GenerarPdfDao generarPdfDao;

    @Value("${spring.profiles.active}")
    private String ambienteTrabajo;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public void generarEncabezado(Document document,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel) throws BadElementException, IOException, DocumentException {

        try {
            String rutaAbsoluta = "";
            if (Constantes.PROPIEDAD_AMBIENTE_TRABAJO_PROD.equals(ambienteTrabajo)) {
                rutaAbsoluta = Constantes.PATH_IMAGEN_ENCABEZADO;
            } else {
                Path path = Paths.get(
                        resourceLoader.getResource(Constantes.DOCUMENTO_IMAGEN_ENCABEZADO).getURI().getPath()
                );
                rutaAbsoluta = path.toAbsolutePath().toString();
            }

            Image img = Image.getInstance(rutaAbsoluta);
            img.scaleAbsolute(document.getPageSize().getWidth(), 80);
            img.setAlignment(Element.ALIGN_CENTER);
            document.add(img);

            Paragraph encabezado = new Paragraph(configuracionHistorialLaboralModel.getFraseEncabezado());
            encabezado.setAlignment(Element.ALIGN_CENTER);
            document.add(encabezado);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        } catch (Exception e) {
            Logger.getLogger(GenerarPDFServiceImpl.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    @Override
    public void generarPiePagina(Document document,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel) throws BadElementException, IOException, DocumentException {

        try {
            String rutaAbsoluta = "";
            if (Constantes.PROPIEDAD_AMBIENTE_TRABAJO_PROD.equals(ambienteTrabajo)) {
                // Servidor
                rutaAbsoluta = Constantes.PATH_IMAGEN_PIE_PAGINA;
            } else {
                Path path = Paths.get(
                        resourceLoader.getResource(Constantes.DOCUMENTO_IMAGEN_PIE_PAGINA).getURI().getPath()
                );
                rutaAbsoluta = path.toAbsolutePath().toString();
            }

            Image img = Image.getInstance(rutaAbsoluta);
            img.scaleAbsolute(document.getPageSize().getWidth() - 20, 120);
            img.setAlignment(Element.ALIGN_CENTER);
            img.setAbsolutePosition(10, 20);
            document.add(img);
        } catch (Exception e) {
            Logger.getLogger(GenerarPDFServiceImpl.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
    }

    @Override
    public ConfiguracionHistorialLaboralModel obtenerConfiguracionDocumento() {
        ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel
                = new ConfiguracionHistorialLaboralModel();
        Utils utils = new Utils();

        List<Object[]> lsResultado = generarPdfDao.obtenerConfiguracion();

        if (lsResultado != null && lsResultado.size() > 0) {
            Object[] resultado = lsResultado.get(0);

            configuracionHistorialLaboralModel = new ConfiguracionHistorialLaboralModel(
                    utils.objectIsNULL(resultado[0]),//Frase encabezado
                    utils.objectIsNULL(resultado[1])//Nombre de firma
            );
        }

        return configuracionHistorialLaboralModel;
    }
    //</editor-fold>
}
