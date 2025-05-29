package mx.gob.edomex.microservicios.hnomina.service.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.edomex.microservicios.hnomina.dao.PagosDAO;
import mx.gob.edomex.microservicios.hnomina.dto.PuntualidadAsistenciaDTO;
import mx.gob.edomex.microservicios.hnomina.exceptions.BusException;
import mx.gob.edomex.microservicios.hnomina.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.hnomina.service.PagosService;
import mx.gob.edomex.microservicios.hnomina.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class PagosServiceImpl implements PagosService {
    private static final Logger LOG = LoggerFactory.getLogger(PagosServiceImpl.class);
    
    @Autowired
    private PagosDAO pagosDAO;        
    
    @Override
    public List<PuntualidadAsistenciaDTO> consultaDatosPuntyAsist(String claveServidor, String tpDato, String fecha1, String fecha2) throws BusException {
            Utils utils = new Utils();
            List<PuntualidadAsistenciaDTO> lstIncidencias = new ArrayList<>();
            try {
                    fecha1 = fecha1.equals("")?null: fecha1;
                    fecha2 = fecha2.equals("")?null: fecha2;
                    List<Object[]> lstResul = pagosDAO.consultaDatosPuntyAsist(claveServidor, tpDato, fecha1, fecha2);
                  
                    lstResul.forEach(x -> {                     
                            PuntualidadAsistenciaDTO itemIncidencia = new PuntualidadAsistenciaDTO();
                            
                            itemIncidencia.setIDINCIDENCIA(utils.objectIsNULL(x[0]));
                            itemIncidencia.setNMINCIDENCIA(utils.objectIsNULL(x[1]));
                            itemIncidencia.setFECHA(utils.objectIsNULL(x[2]));
                            itemIncidencia.setFECHAAPLICA(utils.objectIsNULL(x[3]));
                            
                            String Unidades = utils.objectIsNULL(x[4]);
                            itemIncidencia.setUNIDADES(Unidades.equals("")?null: Integer.valueOf(Unidades));
                            itemIncidencia.setTPUNIDADES(utils.objectIsNULL(x[5]));
                            
                            String importe = utils.objectIsNULL(x[6]);
                            itemIncidencia.setIMPORTE(importe.equals("")?null: Integer.valueOf(importe));

                            lstIncidencias.add(itemIncidencia);
                    });   
                    
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstIncidencias;
    }
    
}
