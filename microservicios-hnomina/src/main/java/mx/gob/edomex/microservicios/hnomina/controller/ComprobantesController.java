
package mx.gob.edomex.microservicios.hnomina.controller;

import java.util.List;
import mx.gob.edomex.microservicios.hnomina.dto.RecibosDTO;
import mx.gob.edomex.microservicios.hnomina.dto.ServidorComprobanteListDTO;

import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;

import mx.gob.edomex.microservicios.hnomina.response.BusRespuesta;
import mx.gob.edomex.microservicios.hnomina.service.ComprobantesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/comprobantes")
public class ComprobantesController {
    
    @Autowired
    private ComprobantesService comprobantesService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "generarComprobantes", method = RequestMethod.POST, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> 
        generarComprobantes(@RequestBody ServidorComprobanteListDTO spComprobante) throws BusException {
            byte[] data =  comprobantesService.generarComprobantes(spComprobante);
            return new ResponseEntity<>(data, HttpStatus.OK); 
                    //new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK", comprobantesService.generarComprobantes());
    }
        
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
                            comprobantesService.consultarComprobantes(claveServidor, nombreServidor, fechainicio, fechafin, idServidorSesion, individual));
    }        
    
           
    /*     
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getMenuByIdRol", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu>
           getMenuByIdRol(@RequestParam("idRol") int idRol) throws BusException {
            return usuariosService.getMenuByIdRol(idRol);
    }
           
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Roles>> 
           getRoles() throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            usuariosService.getRoles());
    }       
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "UpdInsRolUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<Integer> 
           UpdInsRolUsuario(@RequestParam("idServidorPublico") String idServidorPublico,
                    @RequestParam("rolNew") Integer rolNew,
                    @RequestParam("boActivo") String boActivo,
                    @RequestParam("dependencias") String dependencias,
                    @RequestParam("idAdmin") String idAdmin) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            usuariosService.UpdInsRolUsuario(idServidorPublico, rolNew, boActivo, dependencias, idAdmin));
    }                 
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getAdminProrroga", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<ConfigProrrogaDTO> 
           getAdminProrroga(@RequestParam("claveParametro") String claveParametro,
                            @RequestParam("noDias") Integer noDias,
                            @RequestParam("dsusuario") String dsusuario) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            usuariosService.getAdminProrroga(claveParametro, noDias, dsusuario));
    }       
          
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getUnidades", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<ComboOrigen>> 
           getUnidades(@RequestParam("unidad") String unidad,
                            @RequestParam("seccion") String seccion) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            usuariosService.getUnidades(unidad, seccion));
    }     
           
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarUsrUnidadesAsig", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Combo>> 
           getUnidades(@RequestParam("idServidorPublico") String idServidorPublico) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            usuariosService.consultarUsrUnidadesAsig(idServidorPublico));
    }        

*/
}
