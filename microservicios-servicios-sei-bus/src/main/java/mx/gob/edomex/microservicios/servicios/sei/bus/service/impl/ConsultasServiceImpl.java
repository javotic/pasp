
package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.CatalogosDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ConsultasDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.EmisoresCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas.ConstanciaNoAdeudoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas.IncidenciasTiempoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ConsultasService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class ConsultasServiceImpl implements ConsultasService {
    private static final Logger LOG = LoggerFactory.getLogger(DatosServidorPublicoServiceImpl.class);
    
    @Autowired
    private ConsultasDAO consultasDAO;
    
    @Override
    public List<IncidenciasTiempoModel> getIncidenciasTiempo(String idServidorPublico, String fechaInicio, String fechaFin) throws BusException {
            Utils utils = new Utils();
            List<IncidenciasTiempoModel> lstCertificado = new ArrayList<>();
             List<String> excepciones = new ArrayList<>();
            try {
                    
                    List<Object[]> lstResul = consultasDAO.getIncidenciasTiempo(idServidorPublico, fechaInicio, fechaFin);
                    SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    lstResul.forEach(x -> {
                            IncidenciasTiempoModel certificado = new IncidenciasTiempoModel();
                            certificado.setCLAVEINCIDENCIA(utils.objectIsNULL(x[0]));
                            certificado.setNOMBREINCIDENCIA(utils.objectIsNULL(x[1]));
                            certificado.setNUMEROINCIDENCIA(utils.objectIsNULL(x[2]));
                            
                            

                            String fechainicio = utils.objectIsNULL(x[3]);
                            String fechafin = utils.objectIsNULL(x[4]);
                            if(!"".equals(fechainicio)){
                                try {
                                    Date fechapagoDate = parseador.parse(fechainicio);
                                    fechainicio = formateador.format(fechapagoDate);
                                } catch (ParseException ex) {
                                    java.util.logging.Logger.getLogger(CatalogosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                    excepciones.add("error");
                                }
                            }
                            
                            if(!"".equals(fechafin)){
                                try {
                                    Date fechapagoDate = parseador.parse(fechafin);
                                    fechafin = formateador.format(fechapagoDate);
                                } catch (ParseException ex) {
                                    java.util.logging.Logger.getLogger(CatalogosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                    excepciones.add("error");
                                }
                            }
                            
                            certificado.setFECHAINICIO(fechainicio);
                            certificado.setFECHAFIN(fechafin);
                            certificado.setNUMEROUNIDADES(utils.objectIsNULL(x[5]));
                            certificado.setNOMBREPLAZA(utils.objectIsNULL(x[6]));
                            certificado.setNUMEROPLAZA(utils.objectIsNULL(x[7]));

                            lstCertificado.add(certificado);
                    });      
                    
                    //Si no se pudo convertir la fecha manda error
                    if(excepciones.size() > 0){
                    throw new Exception("No se pudo convertir la fecha");
                    }
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstCertificado;
    }
    
    
    @Override
    public List<ConstanciaNoAdeudoModel> getHistoricoConstanciaNoAdeudo(String idServidorPublico) throws  BusException{
            Utils utils = new Utils();
            List<ConstanciaNoAdeudoModel> lstHitorico = new ArrayList<>();
            try {
                    List<Object[]> lstResul = consultasDAO.getHistoricoConstanciaNoAdeudo(idServidorPublico);
                    lstResul.forEach(x -> {

                            ConstanciaNoAdeudoModel historico = new ConstanciaNoAdeudoModel();
                            
                            historico.setIDSOLICITUDCONSTANCIANOADEUDO(utils.objectIsNULL(x[0]));
                            historico.setJUSTIFICACION(new String(utils.objectIsNULL(x[1]).getBytes(), StandardCharsets.UTF_8));                                                       
                            historico.setFECHASOLICITUD(ConvertirFecha(utils.objectIsNULL(x[2])));
                            historico.setRESPUESTA(new String(utils.objectIsNULL(x[3]).getBytes(), StandardCharsets.UTF_8));
                            historico.setESTATUS(utils.objectIsNULL(x[4]));
                            historico.setGESTORADMINISTRATIVO(new String(utils.objectIsNULL(x[5]).getBytes(), StandardCharsets.UTF_8));
                            historico.setFECHARESPUESTA(ConvertirFecha(utils.objectIsNULL(x[6])));
                            historico.setIDDOCUMENTO(utils.objectIsNULL(x[7]));
                            
                            lstHitorico.add(historico);
                    });      
                    
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstHitorico;
    }

    
    /***
     * Convierte el la fecha a formato espesifico, si no trae datos regresa vacio
     * @param fechaConvert Fecha en string a convertir
     * @return
     * @throws ParseException 
     */
    private String ConvertirFecha(String fechaConvert){
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!"".equals(fechaConvert)){
            try {
                Date fechapagoDate = parseador.parse(fechaConvert);
                fechaConvert = formateador.format(fechapagoDate);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CatalogosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fechaConvert;
    }
}
