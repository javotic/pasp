
package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.EmisoresCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PagosModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.ColoniaModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.TipoCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.CatalogosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DatosPuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author smartinez
 */

@RestController
@RequestMapping("/catalogos")
public class CatalogosController {
    
    @Autowired
    private CatalogosService catalogosService;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarCatalogoEmisoresCertificado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<EmisoresCertificadoModel>> catEmisoresCertificado() throws BusException {
            return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                            catalogosService.catEmisoresCertificado());
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarListadoPagos", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<PagosModel>> getListadoPagos(@RequestParam("IDSERVIDORPUBLICO") String idServidorPublico, 
                                                          @RequestParam("FECHAINICIO") String fechaInicio,
                                                          @RequestParam("FECHAFIN") String fechaFin) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            catalogosService.getListadoPagos(idServidorPublico, fechaInicio, fechaFin));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "CONSULTACATCOLONIAS", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<ColoniaModel>> getCatColonias(@RequestParam("IDPAIS") String idPais, 
                                                          @RequestParam("IDPESTADO") String idEstado,
                                                          @RequestParam("IDMUNICIPIO") String idMunicipio, 
                                                          @RequestParam("IDCOLONIA") String idColonia
    ) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            catalogosService.getCatColonias(idPais, idEstado, idMunicipio, idColonia));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "CONSULTACATTIPOCERTIFICADO", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<TipoCertificadoModel>> getTipoCertificados() throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            catalogosService.getTipoCertificados());
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getMunicipiosByEstado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Combo>> getMunicipiosByEstado(@RequestParam("IDPAIS") String IDPAIS, 
                                                          @RequestParam("IDPESTADO") String IDPESTADO
    ) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            catalogosService.getMunicipiosByEstado(IDPAIS, IDPESTADO));
    }
    
}
