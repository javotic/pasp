package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DetalleEddDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEDD;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DetalleEDDService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class DetalleEDDServiceImpl implements DetalleEDDService {
	private static final Logger LOG = LoggerFactory.getLogger(DetalleEDDServiceImpl.class);

	@Autowired
	private DetalleEddDAO detalleEddDAO;

	@Override
	public List<DetalleEDD> consultarDetalleEdd(String IdServidorPublico, String IdProcesoVigente) throws BusException {
		List<DetalleEDD> lstDeta = new ArrayList<>();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = detalleEddDAO.consultarDetalleEdd(IdServidorPublico, IdProcesoVigente);
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

}
