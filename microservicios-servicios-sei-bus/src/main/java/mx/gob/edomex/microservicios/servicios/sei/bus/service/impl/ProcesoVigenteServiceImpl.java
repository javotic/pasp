package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ProcesoVigenteDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ProcesoVigenteService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class ProcesoVigenteServiceImpl implements ProcesoVigenteService {
	private static final Logger LOG = LoggerFactory.getLogger(ProcesoVigenteServiceImpl.class);
	@Autowired
	private ProcesoVigenteDAO procesoVigenteDAO;

	@Override
	public List<ProcesoVigente> consultarProcesoVigente(String IdServidorPublico) throws BusException {
		List<ProcesoVigente> lstProvig = new ArrayList<>();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = procesoVigenteDAO.consultarProcesoVigente(IdServidorPublico);
			lstResul.forEach(e -> {
				ProcesoVigente pro = new ProcesoVigente();
				pro.setCLAVEPROCESOEVALUACION(utils.objectIsNULL(e[0]));
				pro.setDESCRIPCIONPROCESOVIGENTE(utils.objectIsNULL(e[6]));
				pro.setFECHAFINCAPTURADEMERITOS(utils.objectIsNULL(e[5]));
				pro.setFECHAFINPROCESOGENERAL(utils.objectIsNULL(e[3]));
				pro.setFECHAINICIOCAPTURADEMERITOS(utils.objectIsNULL(e[4]));
				pro.setFECHAINICIOPROCESOGENERAL(utils.objectIsNULL(e[2]));
				pro.setIDPROCESOVIGENTE(utils.objectIsNULL(e[7]));
				pro.setNOMBREPROCESOEVALUACION(utils.objectIsNULL(e[1]));
				lstProvig.add(pro);
			});

		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return lstProvig;
	}

}
