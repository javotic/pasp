package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.HistorialEvaluacionDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.HistorialEvalService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class HistorialEvalServiceImpl implements HistorialEvalService {
	private static final Logger LOG = LoggerFactory.getLogger(HistorialEvalServiceImpl.class);

	@Autowired
	private HistorialEvaluacionDAO historialEvaluacionDAO;

	@Override
	public List<HistorialEvaluaciones> consultarHistorialEvaluacion(String IdServidorPublico) throws BusException {
		List<HistorialEvaluaciones> lstHistorial = new ArrayList<>();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = historialEvaluacionDAO.consultarHistoricoEval(IdServidorPublico);
			lstResul.forEach(e -> {
				HistorialEvaluaciones his = new HistorialEvaluaciones();
				his.setFECHAFINPROCESOEVALUACION(utils.objectIsNULL(e[3]));
				his.setFECHAINICIOPROCESOEVALUACION(utils.objectIsNULL(e[2]));
				his.setIDPROCESOEVALUACION(utils.objectIsNULL(e[0]));
				his.setNOMBREPROCESOEVALUACION(utils.objectIsNULL(e[1]));
				his.setTIPOEVALUACION(utils.objectIsNULL(e[4]));
				lstHistorial.add(his);
			});
		} catch (Exception e) {
			LOG.error("Error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return lstHistorial;
	}

}
