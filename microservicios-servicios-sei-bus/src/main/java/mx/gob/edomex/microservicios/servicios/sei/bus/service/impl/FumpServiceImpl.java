package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.FumpDao;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.FumpModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.FumpService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de interfaz de definicion de procesos de FUMP.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 20/10/2021.
 */
@Service
public class FumpServiceImpl implements FumpService {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(FumpServiceImpl.class);
    
    @Autowired
    private FumpDao fumpDao;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public List<FumpModel> obtenerFumpPorFechaUsuario(String claveServidorPublico, 
            Date fechaInicio, Date fechaTermino) throws BusException {
        Utils utils = new Utils();
        List<FumpModel> lsFump = new ArrayList<>();
        List<String> excepciones = new ArrayList<>();

        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");

        try {
            //Obtener datos
            List<Object[]> lstResul = fumpDao.obtenerFumpPorFechaUsuario(
                    claveServidorPublico,
                    formateador.format(fechaInicio),
                    formateador.format(fechaTermino)
            );
            //Parsear los datos a su tipo correspondiente
            lstResul.forEach(x -> {
                try {
                    lsFump.add(new FumpModel(
                            Long.valueOf(utils.objectIsNULL(x[0])),//Folio
                            utils.objectIsNULL(x[1]),//Clave de SP
                            utils.objectIsNULL(x[2]),//Clave UA
                            utils.objectIsNULL(x[3]),//Numero plaza
                            utils.objectIsNULL(x[4]),//CCT
                            utils.objectIsNULL(x[5]),//Tipo de movimiento
                            parseador.parse(utils.objectIsNULL(x[6])),//Fecha de firma
                            utils.objectIsNULL(x[7])//Fecha de firma
                    ));
                } catch (ParseException ex) {
                    excepciones.add("Error en FUMP con folio "
                            + utils.objectIsNULL(x[0])
                            + ". Error: "
                            + ex.getMessage()
                    );
                    java.util.logging.Logger.getLogger(FumpServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //Si existe algun error, se notifica.
            if (excepciones.size() > 0) {

                StringBuilder builder = new StringBuilder();
                excepciones.forEach(e -> builder.append(e));

                throw new Exception("Se han presentando errores al momento de "
                        + "obtener la información, favor de validar."
                        + builder.toString());
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        return lsFump;
    }
    //</editor-fold>
}
