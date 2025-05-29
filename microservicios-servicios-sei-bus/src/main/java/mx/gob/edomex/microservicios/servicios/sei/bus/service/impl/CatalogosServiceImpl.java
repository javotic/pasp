
package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.CatalogosDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DatosServidorPublicoDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.ColoniaModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.EmisoresCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.TipoCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PagosModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SeccionesEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.CatalogosService;
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
public class CatalogosServiceImpl implements CatalogosService {
    private static final Logger LOG = LoggerFactory.getLogger(DatosServidorPublicoServiceImpl.class);
    
    @Autowired
    private CatalogosDAO catalogosDAO;
    
    @Autowired
    private DatosServidorPublicoDAO datosServidorPublicoDAO;
    
            
    
    @Override
    public List<EmisoresCertificadoModel> catEmisoresCertificado() throws BusException {
            Utils utils = new Utils();
            List<EmisoresCertificadoModel> lstCertificado = new ArrayList<>();
            try {
                    
                    List<Object[]> lstResul = catalogosDAO.catEmisoresCertificado();
                    
                    lstResul.forEach(x -> {
                            EmisoresCertificadoModel certificado = new EmisoresCertificadoModel();
                            certificado.setIDEMISOR(utils.objectIsNULL(x[0]));
                            certificado.setNOMBREEMISOR(utils.objectIsNULL(x[1]));

                            lstCertificado.add(certificado);
                    });                     
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstCertificado;
    }
    
    @Override
    public List<PagosModel> getListadoPagos(String idServidorPublico, String fechaInicio, String fechaFin) throws BusException {
            Utils utils = new Utils();
            
            List<PagosModel> lstPagos = new ArrayList<>();
            List<String> excepciones = new ArrayList<>();
            try {
                    
                    List<Object[]> lstResul = catalogosDAO.getListadoPagos(idServidorPublico, fechaInicio, fechaFin);
                    
                    lstResul.forEach(x -> {
                            PagosModel pago = new PagosModel();
                            pago.setIDSERVIDORPUBLICO(utils.objectIsNULL(x[0]));
                            pago.setIDPAGO(utils.objectIsNULL(x[1]));
                            pago.setESTATUSPAGO(utils.objectIsNULL(x[2]));
                            pago.setTIPOPAGO(utils.objectIsNULL(x[3]));
                            
                            SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            String fechaPago = utils.objectIsNULL(x[4]);
                            if(!"".equals(fechaPago)){
                                try {
                                    Date fechapagoDate = parseador.parse(fechaPago);
                                    fechaPago = formateador.format(fechapagoDate);
                                } catch (ParseException ex) {
                                    java.util.logging.Logger.getLogger(CatalogosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                    excepciones.add("error");
                                }
                            }
                            
                            pago.setFECHAPAGO(fechaPago);
                            pago.setIMPORTEPAGOGRAVADO(utils.objectIsNULL(x[5]));
                            pago.setIMPORTEPAGOEXENTO(utils.objectIsNULL(x[6]));

                            lstPagos.add(pago);  
                    });   
                    
                    //Si no se pudo convertir la fecha manda error
                    if(excepciones.size() > 0){
                    throw new Exception("No se pudo convertir la fecha");
                    }
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstPagos;
    }    
    
    @Override
    public List<ColoniaModel> getCatColonias(String idPais, String idEstado, String idMunicipio, String idColonia) throws BusException{
                    Utils utils = new Utils();
            List<ColoniaModel> lstColonias = new ArrayList<>();
            try {
                    idColonia = ("".equals(idColonia) ? null : "");
                    List<Object[]> lstResul = datosServidorPublicoDAO.consultarColonias(idPais, idEstado, idMunicipio, idColonia);
                    
                    lstResul.forEach(x -> {
                            ColoniaModel colonia = new ColoniaModel();
                            colonia.setIDMUNICIPIOCIUDAD(utils.objectIsNULL(x[0]));
                            colonia.setNOMBREMUNICIPIOCIIUDAD(utils.objectIsNULL(x[1]));
                            colonia.setIDCOLONIA(utils.objectIsNULL(x[2]));
                            colonia.setNOMBRECOLONIA(utils.objectIsNULL(x[3]));

                            lstColonias.add(colonia);
                    });                     
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstColonias;
    }
    
    @Override
    public List<TipoCertificadoModel> getTipoCertificados() throws  BusException{
                    Utils utils = new Utils();
            List<TipoCertificadoModel> lstTipoCertificados = new ArrayList<>();
            try {
                    List<Object[]> lstResul = catalogosDAO.getTipoCertificados();
                    
                    lstResul.forEach(x -> {
                            TipoCertificadoModel tipocertifido = new TipoCertificadoModel();
                            tipocertifido.setNOMBRECERTIFICADO(utils.objectIsNULL(x[0]));
                            tipocertifido.setIDTIPOCERTIFICADO(utils.objectIsNULL(x[1]));

                            lstTipoCertificados.add(tipocertifido);
                    });                     
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstTipoCertificados;
    }    
    
    @Override
    public List<Combo> getMunicipiosByEstado(String idPais, String idEstado) throws BusException{
            Utils utils = new Utils();
            List<Combo> lstEstados = new ArrayList<>();
            try {
                    List<Object[]> lstResul = datosServidorPublicoDAO.consultarMunicipios(idPais, idEstado, null);
                    
                    lstResul.forEach(x -> {
                            Combo colonia = new Combo();
                            colonia.setValue(utils.objectIsNULL(x[3]));
                            colonia.setLabel(utils.objectIsNULL(x[4]));

                            lstEstados.add(colonia);
                    });                     
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstEstados;
    }
           
}
