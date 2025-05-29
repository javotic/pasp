package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ResultadosEddDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ResultadosPreguntasDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ResultadosEddPreg;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ResultadosPreguntas;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ResultadosEDDService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class ResultadosEDDServiceImpl implements ResultadosEDDService {
	private static final Logger LOG = LoggerFactory.getLogger(ResultadosEDDServiceImpl.class);
	@Autowired
	private ResultadosEddDAO resultadosEddDAO;

	@Autowired
	private ResultadosPreguntasDAO resultadosPreguntasDAO;

	@Override
	public ResultadosEddPreg consultarResultados(String idServidorPublico, String idSeccion, String idProceso)
			throws BusException {
		ResultadosEddPreg resultadosEddPreg = new ResultadosEddPreg();
		Utils utils = new Utils();
		try {
			List<Object[]> lstResul = resultadosEddDAO.resultadosEdd(idServidorPublico, idSeccion, idProceso);
			List<Object[]> lstResulPreg = resultadosPreguntasDAO.resultadosPregunta(idServidorPublico, idSeccion,
					idProceso);
			LOG.info("Objeto=>", lstResul.size());
			LOG.info("Objeto=>", utils.objectIsNULL(lstResul.get(0)[0]));
			LOG.info("Objeto=>", utils.objectIsNULL(lstResul.get(0)[1]));
			LOG.info("Lista=>", utils.objectIsNULL(lstResulPreg.get(0)[0]));

			resultadosEddPreg.setTotalpuntosobtenidos(utils.objectIsNULL(lstResul.get(0)[0]));
			resultadosEddPreg.setCalificacionparcial(utils.objectIsNULL(lstResul.get(0)[1]));
			List<ResultadosPreguntas> lstPr = new ArrayList<>();
			lstResulPreg.forEach(e -> {
				ResultadosPreguntas res = new ResultadosPreguntas();
				res.setIdpregunta(Objects.toString(e[0], ""));
				res.setDescripcionpregunta(Objects.toString(e[1]));
				res.setIdrespuesta(Objects.toString(e[2]));
				lstPr.add(res);
			});
			resultadosEddPreg.setResultadosPreguntas(new ArrayList<>());
			resultadosEddPreg.setResultadosPreguntas(lstPr);
		} catch (Exception e) {
			LOG.error("error {}", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return resultadosEddPreg;
	}

}
