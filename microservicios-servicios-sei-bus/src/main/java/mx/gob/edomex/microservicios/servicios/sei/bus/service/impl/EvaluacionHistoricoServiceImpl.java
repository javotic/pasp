/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.EvaluacionHistoricoDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComisionesAsignaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEDD;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoEddUa;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EvaluacionHistoricoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class EvaluacionHistoricoServiceImpl implements EvaluacionHistoricoService {
    private static final Logger LOG = LoggerFactory.getLogger(EvaluacionHistoricoServiceImpl.class);
    
    @Autowired
    private EvaluacionHistoricoDAO evaluacionHistoricoDAO;
    
    @Override
    public List<DetalleEDD> getDetalleUnidades(String IdServidorPublico, String IdProcesoVigente) throws BusException {
            List<DetalleEDD> lstDeta = new ArrayList<>();
            Utils utils = new Utils();
            try {
                    List<Object[]> lstResul = evaluacionHistoricoDAO.getDetalleUnidades(IdServidorPublico, IdProcesoVigente);
                    lstResul.forEach(e -> {
                            DetalleEDD det = new DetalleEDD();
                            det.setIDEVALUACIONVIGENTE(utils.objectIsNULL(e[0]));
                            det.setIDUNIDADADMINISTRATIVA(utils.objectIsNULL(e[2]));
                            det.setNOMBREEVALUACIONVIGENTE(utils.objectIsNULL(e[1]));
                            det.setNOMBREUNIDADADMINISTRATIVA(utils.objectIsNULL(e[3]));
                            det.setPORCENTAJEAVANZE(utils.objectIsNULL(e[7]));
                            det.setTOTALSERVIDORESPUBLICOS(utils.objectIsNULL(e[4]));
                            det.setTOTALSERVIDORESPUBLICOSEVALUADOS(utils.objectIsNULL(e[5]));
                            det.setTOTALSERVIDORESPUBLICOSFALTANTES(utils.objectIsNULL(e[6]));
                            lstDeta.add(det);

                    });
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstDeta;
    }
    
    	@Override
	public DetalleEddeSP getServidoresedduaHt(String IdServidorPublico,
			String IdUnidadAdministrativa, String IdProcesoVigente) throws BusException {
		DetalleEddeSP deta = new DetalleEddeSP();
		try {

                        List<Object[]> lstDetalleUA = evaluacionHistoricoDAO.getDetalleEdd_UaHT(IdServidorPublico, IdProcesoVigente,IdUnidadAdministrativa);
                        
			List<Object[]> data = evaluacionHistoricoDAO.getServidoresedduaHt(IdServidorPublico,
					IdUnidadAdministrativa, IdProcesoVigente);
                        if(data.size() > 0){
                        }
			deta = new DetalleEddeSP();
			deta.setIdevaluacionvigente((lstDetalleUA.get(0)[0].toString()));
			deta.setNombreevaluacionvigente((lstDetalleUA.get(0)[1].toString()));
			deta.setIdunidadadministrativa((lstDetalleUA.get(0)[2].toString()));
			deta.setNombreunidadadministrativa((lstDetalleUA.get(0)[3].toString()));
			deta.setTotalservidorespublicos((lstDetalleUA.get(0)[4].toString()));
			deta.setTotalservidorespublicosevaluados((lstDetalleUA.get(0)[5].toString()));
			deta.setTotalservidorespublicosfaltantes((lstDetalleUA.get(0)[6].toString()));
			deta.setPorcentajeavanze((lstDetalleUA.get(0)[7].toString()));
			deta.setLSTSERVIDORESPUBLICOS(new ArrayList<>());
			List<ServidorPublicoEddUa> lstSP = new ArrayList<>();
			data.forEach(e -> {
				ServidorPublicoEddUa sp = new ServidorPublicoEddUa();
				sp.setCLAVESERVIDORPUBLICO((e[0].toString()));
				sp.setNOMBRESERVIDORPUBLICO((e[1].toString()));
				sp.setAPELLIDOPATERNOSERVIDORPUBLIC((e[2].toString()));
				sp.setAPELLIDOMATERNOSERVIDORPUBLIC((e[3].toString()));
				sp.setRFCSERVIDORPUBLICO((e[4].toString()));
				sp.setPUESTOSERVIDORPUBLICO((e[5].toString()));
				sp.setTIPOEVALUACION((e[6].toString()));
				sp.setESTATUSEVALUACION((e[7].toString()));
				sp.setIDPROCESOVIGENTE((e[8].toString()));
				sp.setIDEVALUACIONKP((e[9].toString()));
				sp.setCORREOSERVIDORPUBLICO(e[10].toString());
                                sp.setIDUNIDAD_ASIGNADA(e[11].toString());
                                sp.setUNIDAD_ASIGNADA(e[12].toString());                                
				lstSP.add(sp);
			});
			deta.setLSTSERVIDORESPUBLICOS(lstSP);

		} catch (Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return deta;
	}
    
    
    
}
