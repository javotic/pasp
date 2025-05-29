package mx.gob.edomex.microservicios.serviciosreportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.serviciosreportes.service.ConstanciaKPIService;
import mx.gob.edomex.microservicios.serviciosreportes.service.ReporteEddService;

@RestController
@RequestMapping("/reportes")
public class ReporteEddController {

    @Autowired
    private ReporteEddService service;

    @Autowired
    private ConstanciaKPIService constanciaKPIService;

    @GetMapping(value = "/generarReporte/{proceso}/{servidorPublico}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporte(@PathVariable("proceso") String claveproceso,
            @PathVariable("servidorPublico") String servidorPublico) {
        byte[] data = null;
        data = service.getReporteEdd(claveproceso, servidorPublico);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/generarReportekpi/{fecha}/{servidorPublico}/{proceso}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    //@RequestMapping(value = "/generarReportekpi/{fecha}/{servidorPublico}/{proceso}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> generarReporteKPI(@PathVariable("fecha") String fecha,
            @PathVariable("servidorPublico") String servidorPublico, @PathVariable("proceso") String proceso) {
        byte[] data = null;
        data = constanciaKPIService.getReporteKPI(fecha, servidorPublico, proceso);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @GetMapping(value = "/generarReporteHT/{proceso}/{servidorPublico}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporteHT(@PathVariable("proceso") String claveproceso,
            @PathVariable("servidorPublico") String servidorPublico) {
        byte[] data = null;
        data = service.getReporteEddHT(claveproceso, servidorPublico);
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    /**
     * Generar reporte de evaluacion del desempeno con demeritos a traves del
     * nuevo formato solicitado por el cliente.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 03/02/2023.
     * @param claveproceso Clave del proceso del que se obtendra el formato
     * @param servidorPublico Clave de servidor publico que realiza la
     * evaluacion.
     * @return Arreglo de bytes con el reporte generado.
     */
    @GetMapping(value = "/generarReporteEdddemeritos/{proceso}/{servidorPublico}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generarReporteEdddemeritos(
            @PathVariable("proceso") String claveproceso,
            @PathVariable("servidorPublico") String servidorPublico) {
        byte[] data = null;
        data = service.generarReporteEdddemeritos(claveproceso, servidorPublico);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
