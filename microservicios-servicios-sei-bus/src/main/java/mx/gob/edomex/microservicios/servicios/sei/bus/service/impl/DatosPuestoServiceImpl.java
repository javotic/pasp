package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DatosPuestoDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPuesto;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DatosPuestoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class DatosPuestoServiceImpl implements DatosPuestoService {
	private static final Logger LOG = LoggerFactory.getLogger(DatosPuestoServiceImpl.class);
	@Autowired
	private DatosPuestoDAO datosPuestoDAO;

	@Override
	public DatosPuesto consultarDatosPuesto(String IdServidorPublico, String proceso)
			throws BusException {
		DatosPuesto puesto = new DatosPuesto();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = datosPuestoDAO.consultarDatosPuesto(IdServidorPublico, proceso);

			puesto.setCLAVESERVIDORPUBLICO(utils.objectIsNULL(lstResul.get(0)[0]));
			puesto.setNOMBRE(utils.objectIsNULL(lstResul.get(0)[1]));
			puesto.setPRIMERAPELLIDO(utils.objectIsNULL(lstResul.get(0)[2]));
			puesto.setSEGUNDOAPELLIDO(utils.objectIsNULL(lstResul.get(0)[3]));
			puesto.setNOMBREPUESTO(utils.objectIsNULL(lstResul.get(0)[4]));
			puesto.setFECHAINGRESOPUESTO(utils.objectIsNULL(lstResul.get(0)[5]));
			puesto.setNUMPLAZA(utils.objectIsNULL(lstResul.get(0)[6]));
			puesto.setCODIGOPUESTO(utils.objectIsNULL(lstResul.get(0)[7]));
			puesto.setFECHAINGRESOGEM(utils.objectIsNULL(lstResul.get(0)[8]));
			puesto.setDEPENDENCIA(utils.objectIsNULL(lstResul.get(0)[9]));//
			puesto.setSUBSECRETARIAGRL(utils.objectIsNULL(lstResul.get(0)[10]));
			puesto.setSUBSECRETARIA(utils.objectIsNULL(lstResul.get(0)[11]));
			puesto.setDIRECCIONGRL(utils.objectIsNULL(lstResul.get(0)[12]));
			puesto.setDIRECCIONAREA(utils.objectIsNULL(lstResul.get(0)[13]));
			puesto.setSUBDIRECCION(utils.objectIsNULL(lstResul.get(0)[14]));
			puesto.setDEPARTAMENTO(utils.objectIsNULL(lstResul.get(0)[15]));
			puesto.setESPACIOORGANIZACIONAL(utils.objectIsNULL(lstResul.get(0)[16]));
			puesto.setCLAVEUNIDADADMINISTRATIVA(utils.objectIsNULL(lstResul.get(0)[17]));
			puesto.setNOMBREUNIDADADMINISTRATIVA(utils.objectIsNULL(lstResul.get(0)[18]));
			puesto.setCLAVESPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[19]));
			puesto.setNOMBRESPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[20]));
			puesto.setPRIMERAPELLIDOSPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[21]));
			puesto.setSEGUNDOAPELLIDOSPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[22]));
			puesto.setCALIFICACIONFINAL(utils.objectIsNULL(lstResul.get(0)[23]));
			puesto.setFECHAUNO(utils.objectIsNULL(lstResul.get(0)[24]));
			puesto.setFECHADOS(utils.objectIsNULL(lstResul.get(0)[25]));
                        puesto.setFECHATRES(utils.objectIsNULL(lstResul.get(0)[26]));
                        puesto.setESTATUS(utils.objectIsNULL(lstResul.get(0)[27]));

			return puesto;
		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
	}
        
        
        @Override
	public DatosPuesto consultarDatosPuestoHT(String IdServidorPublico, String proceso)
			throws BusException {
		DatosPuesto puesto = new DatosPuesto();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = datosPuestoDAO.consultarDatosPuestoHT(IdServidorPublico, proceso);

			puesto.setCLAVESERVIDORPUBLICO(utils.objectIsNULL(lstResul.get(0)[0]));
			puesto.setNOMBRE(utils.objectIsNULL(lstResul.get(0)[1]));
			puesto.setPRIMERAPELLIDO(utils.objectIsNULL(lstResul.get(0)[2]));
			puesto.setSEGUNDOAPELLIDO(utils.objectIsNULL(lstResul.get(0)[3]));
			puesto.setNOMBREPUESTO(utils.objectIsNULL(lstResul.get(0)[4]));
			puesto.setFECHAINGRESOPUESTO(utils.objectIsNULL(lstResul.get(0)[5]));
			puesto.setNUMPLAZA(utils.objectIsNULL(lstResul.get(0)[6]));
			puesto.setCODIGOPUESTO(utils.objectIsNULL(lstResul.get(0)[7]));
			puesto.setFECHAINGRESOGEM(utils.objectIsNULL(lstResul.get(0)[8]));
			puesto.setDEPENDENCIA(utils.objectIsNULL(lstResul.get(0)[9]));//
			puesto.setSUBSECRETARIAGRL(utils.objectIsNULL(lstResul.get(0)[10]));
			puesto.setSUBSECRETARIA(utils.objectIsNULL(lstResul.get(0)[11]));
			puesto.setDIRECCIONGRL(utils.objectIsNULL(lstResul.get(0)[12]));
			puesto.setDIRECCIONAREA(utils.objectIsNULL(lstResul.get(0)[13]));
			puesto.setSUBDIRECCION(utils.objectIsNULL(lstResul.get(0)[14]));
			puesto.setDEPARTAMENTO(utils.objectIsNULL(lstResul.get(0)[15]));
			puesto.setESPACIOORGANIZACIONAL(utils.objectIsNULL(lstResul.get(0)[16]));
			puesto.setCLAVEUNIDADADMINISTRATIVA(utils.objectIsNULL(lstResul.get(0)[17]));
			puesto.setNOMBREUNIDADADMINISTRATIVA(utils.objectIsNULL(lstResul.get(0)[18]));
			puesto.setCLAVESPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[19]));
			puesto.setNOMBRESPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[20]));
			puesto.setPRIMERAPELLIDOSPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[21]));
			puesto.setSEGUNDOAPELLIDOSPEVALUADOR(utils.objectIsNULL(lstResul.get(0)[22]));
			puesto.setCALIFICACIONFINAL(utils.objectIsNULL(lstResul.get(0)[23]));
			puesto.setFECHAUNO(utils.objectIsNULL(lstResul.get(0)[24]));
			puesto.setFECHADOS(utils.objectIsNULL(lstResul.get(0)[25]));
                        puesto.setFECHATRES(utils.objectIsNULL(lstResul.get(0)[26]));
                        puesto.setESTATUS(utils.objectIsNULL(lstResul.get(0)[27]));

			return puesto;
		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
	}
        

}
