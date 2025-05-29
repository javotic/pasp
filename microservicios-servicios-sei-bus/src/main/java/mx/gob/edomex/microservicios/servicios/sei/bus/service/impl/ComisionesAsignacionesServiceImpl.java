package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ComisionesAsignacionesDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComisionesAsignaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ComisionesAsignacionesService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class ComisionesAsignacionesServiceImpl implements ComisionesAsignacionesService {
	private static final Logger LOG = LoggerFactory.getLogger(ComisionesAsignacionesServiceImpl.class);

	@Autowired
	private ComisionesAsignacionesDAO comisionesAsignacionesDAO;

	@Override
	public List<ComisionesAsignaciones> consultarComisiones(String IdServidorPublico, String IdProcesoVigente)
			throws BusException {
		List<ComisionesAsignaciones> lstCom = new ArrayList<>();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = comisionesAsignacionesDAO.consultarComisionesa(IdServidorPublico,
					IdProcesoVigente);
			lstResul.forEach(e -> {
				ComisionesAsignaciones com = new ComisionesAsignaciones();
				com.setAPELLIDOMATERNOSERVIDORPUBLICO(utils.objectIsNULL(e[3]));
				com.setAPELLIDOPATERNOSERVIDORPUBLICO(utils.objectIsNULL(e[2]));
				com.setCLAVESERVIDORPUBLICO(utils.objectIsNULL(e[0]));
				com.setCORREOSERVIDORPUBLICO(utils.objectIsNULL(e[8]));
				com.setESTATUSEVALUACION(utils.objectIsNULL(e[6]));
				com.setIDPROCESOACTUAL(utils.objectIsNULL(e[7]));
				com.setNOMBRESERVIDORPUBLICO(utils.objectIsNULL(e[1]));
				com.setRFCSERVIDORPUBLICO(utils.objectIsNULL(e[4]));
				com.setTIPOEVALUACION(utils.objectIsNULL(e[5]));
				com.setEVALUACIONKP(utils.objectIsNULL(e[9]));
                                com.setIDUNIDAD_ASIGNADA(utils.objectIsNULL(e[10]));
				com.setUNIDAD_ASIGNADA(utils.objectIsNULL(e[11]));                                
				lstCom.add(com);
			});

		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return lstCom;
	}

};