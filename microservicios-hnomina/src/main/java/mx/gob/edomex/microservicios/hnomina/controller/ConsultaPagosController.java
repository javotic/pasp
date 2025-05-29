
package mx.gob.edomex.microservicios.hnomina.controller;

import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteListDTO;

import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;

import mx.gob.edomex.microservicios.hnomina.service.ConsultaPagosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author smartinez
 */

@RestController
@RequestMapping("/consultaPagos")
public class ConsultaPagosController {
    
    @Autowired
    private ConsultaPagosService consultaPagosService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "generarPagoQuincenal", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> 
        generarPagoQuincenal(@RequestBody ServidorComprobanteListDTO spComprobante) throws BusException {
            byte[] data =  consultaPagosService.generarPagoQuincenal(spComprobante);
            return new ResponseEntity<>(data, HttpStatus.OK); 
    }
        /*
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarComprobantes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<RecibosDTO>> consultarComprobantes(
                    @RequestParam("fechainicio") String fechainicio,
                    @RequestParam("fechafin") String fechafin,
                    @RequestParam("claveServidor") String claveServidor,
                    @RequestParam("nombreServidor") String nombreServidor,
                    @RequestParam("idServidorSesion") String idServidorSesion,
                    @RequestParam("individual") String individual) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            consultaPagosService.consultarComprobantes(claveServidor, nombreServidor, fechainicio, fechafin, idServidorSesion, individual));
    }        
    */
           
}
