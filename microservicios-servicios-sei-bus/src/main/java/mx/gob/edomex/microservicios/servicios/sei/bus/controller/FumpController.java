package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.Date;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.FumpModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.FumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Controlador de peticiones de FUMP.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 20/10/2021.
 */
@RestController
@RequestMapping("/fump")
public class FumpController {

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private FumpService fumpService;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    /**
     * Obtener listado de FUMPs a traves de un rango de fechas y de una clave de
     * servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/10/2021.
     * @param claveServidorPublico Clave de servidor publico del que se obtendra
     * la informacion.
     * @param fechaInicio Fecha de inicio de obtencion de informacion.
     * @param fechaTermino Fecha de termino de obtencion de informacion.
     * @return Lista de FUMPs coincidentes con los parametros de busqueda.
     * @throws BusException de ejecucion de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerFumpPorFechaUsuario",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FumpModel> obtenerFumpPorFechaUsuario(
            @RequestParam("claveServidorPublico") String claveServidorPublico,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam("fechaTermino") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaTermino)
            throws BusException {
        return fumpService.obtenerFumpPorFechaUsuario(claveServidorPublico, fechaInicio, fechaTermino);
    }
    //</editor-fold>
}
