package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ServidorPublicoIncrementoSalarialDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.CategoriaIncrementoSalarialModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TabuladorBurocrataModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TabuladorDocenteModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.IncrementoSalarialService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Controlador del incremento salarial.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 18/07/2022.
 */
@RestController
@RequestMapping("/incrementoSalarial")
public class IncrementoSalarialController {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String estatus;
    private String mensaje;
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private IncrementoSalarialService incrementoSalarialService;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    /**
     * Obtener datos de servidor publico relacionado con el incremento salarial.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 19/07/2022.
     * @param claveServidor Clave del servidor publico del que se obtendra la
     * informacion.
     * @return Datos encontrados del servidor publico.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerServPubIncrSal",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<ServidorPublicoIncrementoSalarialDTO> obtenerServPubIncrSal(
            @RequestParam(name = "claveServidor", required = true) String claveServidor
    ) throws BusException {
        ServidorPublicoIncrementoSalarialDTO servidorPublicoIncrementoSalarialDTO
                = new ServidorPublicoIncrementoSalarialDTO();
        try {
            servidorPublicoIncrementoSalarialDTO = this.incrementoSalarialService.obtenerServPubIncrSal(claveServidor);
            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                servidorPublicoIncrementoSalarialDTO
        );
    }

    /**
     * Obtener categorias de incremento salarial.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @return Lista con las diferentes categorias obtenidas.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerCategoriasIncrSal",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<CategoriaIncrementoSalarialModel>> obtenerCategoriasIncrSal()
            throws BusException {
        List<CategoriaIncrementoSalarialModel> lsCategoriaIncrementoSalarialModel = new ArrayList<>();
        try {
            lsCategoriaIncrementoSalarialModel = this.incrementoSalarialService.obtenerCategoriasIncrSal();
            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                lsCategoriaIncrementoSalarialModel
        );
    }

    /**
     * Obtener tabulador docente de acuerdo a clave de servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @param claveServidorPublico Clave del servidor publico del que se
     * obtendran los resultados.
     * @return Lista con los datos del tabulador docente encontrado.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerTabuladorDocIncrSal",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<TabuladorDocenteModel>> obtenerTabuladorDocIncrSal(
            @RequestParam(name = "claveServidorPublico", required = true) String claveServidorPublico
    ) throws BusException {
        List<TabuladorDocenteModel> lsTabuladorDocenteModel = new ArrayList<>();
        try {
            lsTabuladorDocenteModel = this.incrementoSalarialService.obtenerTabuladorDocIncrSal(claveServidorPublico);
            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                lsTabuladorDocenteModel
        );
    }

    /**
     * Obtener tabulador burocrata de acuerdo a un codigo de puesto determinado.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @param codigoPuesto Codigo de puesto del que se obtendra el tabulador
     * burocrata.
     * @return Lista de motivos de solicitud de historial laboral.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerTabuladorBurIncrSal",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<TabuladorBurocrataModel>> obtenerTabuladorBurIncrSal(
            @RequestParam(name = "codigoPuesto", required = true) String codigoPuesto
    ) throws BusException {
        List<TabuladorBurocrataModel> lsTabuladorBurocrataModel = new ArrayList<>();
        try {
            lsTabuladorBurocrataModel = this.incrementoSalarialService.obtenerTabuladorBurIncrSal(codigoPuesto);
            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                lsTabuladorBurocrataModel
        );
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public IncrementoSalarialService getIncrementoSalarialService() {
        return incrementoSalarialService;
    }

    public void setIncrementoSalarialService(IncrementoSalarialService incrementoSalarialService) {
        this.incrementoSalarialService = incrementoSalarialService;
    }
    //</editor-fold>
}
