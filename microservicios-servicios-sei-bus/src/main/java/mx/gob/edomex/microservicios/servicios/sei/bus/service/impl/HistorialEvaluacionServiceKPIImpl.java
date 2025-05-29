package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.HistorialEvaluacionDAOKPI;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.HistorialKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.InstruccionesKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.PreguntasKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.RespuestasKPIDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EnvioRespuestasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.EvaluacionKpiVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.IntruccionesKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.EvaluacionKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.PreguntasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.PreguntasRespuestasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi.RespuestasPreguntasKpi;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.HistorialEvaluacionServiceKPI;

@Service
public class HistorialEvaluacionServiceKPIImpl implements HistorialEvaluacionServiceKPI {

	@Autowired
	private HistorialEvaluacionDAOKPI dao;
        
        @Autowired
        private InstruccionesKPIDAO instruccionesKPIDAO;
        
        @Autowired
        private PreguntasKPIDAO preguntasKPIDAO;
        
        @Autowired
        private RespuestasKPIDAO respuestasKPIDAO;
        
        private HistorialKPIDAO historialKPIDAO;

	@Override
	public List<HistorialEvaluacion> consultarHistorialEvaluacionKPI(String claveServidorPublico) throws BusException {
		List<HistorialEvaluacion> consultas = new ArrayList<>();
		try {

			List<Object[]> data = historialKPIDAO.consultarHiscoricoKPI(claveServidorPublico);
			data.forEach(x -> {
				HistorialEvaluacion cr = new HistorialEvaluacion();
				cr.setIdProcesoEvaluacion(Objects.toString(x[0], ""));
				cr.setClaveProcesoKPI(Objects.toString(x[1], ""));
				cr.setNombreProcesoEvaluacion(Objects.toString(x[2], ""));
				cr.setFechaInicioProcesoKPI(Objects.toString(x[3], ""));
				cr.setFechaFInProcesoKPI(Objects.toString(x[4], ""));
				cr.setIdEstatus(Objects.toString(x[5], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;

	}

	@Override
	public List<EvaluacionKpiVigente> evaluacionKpiVigente(String idProcesoVigente) throws BusException {
		List<EvaluacionKpiVigente> evaluaciones = new ArrayList<>();
		try {

			List<Object[]> data = dao.evaluacionKpiVigente(idProcesoVigente);

			data.forEach(x -> {
				EvaluacionKpiVigente e = new EvaluacionKpiVigente();
				e.setClaveProcesoEvaluacionKpi(Objects.toString(x[0], ""));
				e.setNombreProcesoEvaluacionKpi(Objects.toString(x[1], ""));
				e.setFechaInicioProcesoGeneral(Objects.toString(x[2], ""));
				e.setFechaFinProcesoGeneral(Objects.toString(x[3], ""));
				e.setDescripcionProcesoKpiVigente(Objects.toString(x[4], ""));
				e.setIdProcesoVigente((x[5] != null ? x[5].toString() : ""));

				evaluaciones.add(e);
			});

		} catch (final Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return evaluaciones;
	}

	@Override
	public List<IntruccionesKpi> instruccionesKpi(String idProcesoKPI) throws BusException {
		List<IntruccionesKpi> instrucciones = new ArrayList<>();
		try {

			List<Object[]> data = dao.instruccionesKpi(idProcesoKPI);

			data.forEach(x -> {
				IntruccionesKpi e = new IntruccionesKpi();
				e.setInstrucciones((x[0] != null ? x[0].toString() : ""));
				e.setTiempoLimite((x[1] != null ? x[1].toString() : ""));
				e.setTiempo((x[2] != null ? x[2].toString() : ""));

				instrucciones.add(e);
			});

		} catch (final Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return instrucciones;
	}

	@Override
	public List<EvaluacionKpi> evaluacionKpi(String claveServidorPublico, String idProcesoKPI) throws BusException {
		List<EvaluacionKpi> info = new ArrayList<>();
		try {

			List<Object[]> data = instruccionesKPIDAO.evaluacionKpi(claveServidorPublico, idProcesoKPI);

			data.forEach(x -> {
				EvaluacionKpi e = new EvaluacionKpi();
				e.setIdProcesovigente(x[0] != null ? x[0].toString() : "");
				e.setIdProcesoKPI(x[1] != null ? x[1].toString() : "");
				e.setNombreProcesoKPI(x[2] != null ? x[2].toString() : "");
				e.setIdServidorPublico(x[3] != null ? x[3].toString() : "");
				e.setNombreServidorPublico(x[4] != null ? x[4].toString() : "");
				info.add(e);
			});

		} catch (final Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return info;
	}

	@Override
	public List<PreguntasKpi> preguntasKpi(String claveServidorPublico, String idProcesoKPI) throws BusException {
		List<PreguntasKpi> info = new ArrayList<>();
		try {

			List<Object[]> data = preguntasKPIDAO.consultarComisionesa(claveServidorPublico, idProcesoKPI);

			data.forEach(x -> {
				PreguntasKpi e = new PreguntasKpi();
				e.setIdProcesovigente(x[0] != null ? x[0].toString() : "");
				e.setIdPregunta(x[1] != null ? x[1].toString() : "");
				e.setDescripcion(x[2] != null ? x[2].toString() : "");
				info.add(e);
			});

		} catch (final Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return info;
	}

	@Override
	public List<RespuestasPreguntasKpi> respuestasPreguntasKpi(String claveServidorPublico, String idProcesoKPI)
			throws BusException {
		List<RespuestasPreguntasKpi> info = new ArrayList<>();
		try {

			List<Object[]> data = respuestasKPIDAO.consultarComisionesa(claveServidorPublico, idProcesoKPI);

			data.forEach(x -> {
				RespuestasPreguntasKpi e = new RespuestasPreguntasKpi();
				e.setIdProcesovigente(x[0] != null ? x[0].toString() : "");
				e.setIdPregunta(x[1] != null ? x[1].toString() : "");
				e.setIdRespuesta(x[2] != null ? x[2].toString() : "");
				e.setRespuesta(x[3] != null ? x[3].toString() : "");
				e.setSiguientePregunta(x[4] != null ? x[4].toString() : "");
				info.add(e);
			});

		} catch (final Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return info;
	}

	@Override
	public List<PreguntasRespuestasKpi> preguntasRespuestasKpi(String claveServidorPublico, String idProcesoKPI)
			throws BusException {

		List<PreguntasRespuestasKpi> info = new ArrayList<>();
		List<PreguntasKpi> preguntas = preguntasKpi(claveServidorPublico, idProcesoKPI);
		List<RespuestasPreguntasKpi> respuesas = respuestasPreguntasKpi(claveServidorPublico, idProcesoKPI);

		for (PreguntasKpi x : preguntas) {
			PreguntasRespuestasKpi p = new PreguntasRespuestasKpi();
			List<RespuestasPreguntasKpi> lstR = new ArrayList<>();
			for (RespuestasPreguntasKpi y : respuesas) {
				if (x.getIdPregunta().equals(y.getIdPregunta())) {
					p.setIdPregunta(x.getIdPregunta());
					p.setIdProcesovigente(x.getIdProcesovigente());
					p.setDescripcion(x.getDescripcion());
					RespuestasPreguntasKpi r = y;
					lstR.add(r);
					p.setRespuestas(lstR);
				}
			}
			info.add(p);
		}

		Collections.sort(info, new Comparator<PreguntasRespuestasKpi>() {
			public int compare(PreguntasRespuestasKpi p1, PreguntasRespuestasKpi p2) {
				return p1.getIdPregunta().compareTo(p2.getIdPregunta());
			}
		});
		return info;

	}

	@Override
	@Transactional
	public String envioRespuestasKpi(List<EnvioRespuestasKpi> respuestasKPI) throws BusException {
		try {
		respuestasKPI.forEach(ex -> {
			dao.saveRespuestasKpi(ex.getTiempo(), ex.getIdPregunta(), 
					ex.getIdRespuesta(), 
					ex.getIdServidorPublico(),
					ex.getIdProcesoVigente());
		});
		} catch (final Exception e) {
			e.printStackTrace();
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return "";
	}

}
