package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.InformacionActualEscalafon;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.ArbolEscalafonarioResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonArbol;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuestoActual;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPuntajePrevio;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.InformacionActualEscalafonService;

@Service
public class InformacionActualEscalafonServiceImpl implements InformacionActualEscalafonService {

	@Autowired
	private InformacionActualEscalafon dao;

	@Override
	public List<EscalafonPuntajePrevio> escalafonPuntajePrevio(String claveServidorPublico, String idProceso)
			throws BusException {
		List<EscalafonPuntajePrevio> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonPuntajePrevio(claveServidorPublico, idProceso);
			data.forEach(x -> {
				EscalafonPuntajePrevio cr = new EscalafonPuntajePrevio();
				cr.setPuntajeCapacitacion(Objects.toString(x[0], ""));
				cr.setPuntajeEscolaridad(Objects.toString(x[1], ""));
				cr.setPuntajeEficiencia(Objects.toString(x[2], ""));
				cr.setPuntajeAntiguedad(Objects.toString(x[3], ""));
				cr.setPuntajeDemeritos(Objects.toString(x[4], ""));
				cr.setTotal(Objects.toString(x[5], ""));
                                cr.setPuntajeSubTotal(Objects.toString(x[6], ""));
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}

	@Override
	public List<EscalafonPuestoActual> escalafonPuestoActual(String claveServidorPublico) throws BusException {
		List<EscalafonPuestoActual> consultas = new ArrayList<>();
		try {

			List<Object[]> data = dao.escalafonPuestoActual(claveServidorPublico);
			data.forEach(x -> {
				
				EscalafonPuestoActual cr = new EscalafonPuestoActual();
				cr.setIdPuesto(Objects.toString(x[0], ""));
				cr.setNombrePuesto(Objects.toString(x[1], ""));
//				cr.setIdPlaza(x[2].toString());
	//			cr.setIdPuestoEdomex(x[3].toString());
			
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}

	@Override
	public List<ArbolEscalafonarioResponse> escalafonArbol(String claveServidorPublico) throws BusException {
		List<EscalafonArbol> consultas = new ArrayList<>();
		List<ArbolEscalafonarioResponse> arbolResponse= new  ArrayList<>();
		ArbolEscalafonarioResponse ar = new ArbolEscalafonarioResponse();
		try {
			List<Object[]> puesto = dao.escalafonPuestoActual(claveServidorPublico);
			List<Object[]> data = dao.escalafonArbol(claveServidorPublico);
			data.forEach(x -> {
				
				EscalafonArbol cr = new EscalafonArbol();
				cr.setLinea(Objects.toString(x[0], ""));
				cr.setGrupo(Objects.toString(x[1], ""));
				cr.setRama(Objects.toString(x[2], ""));
				cr.setClave(Objects.toString(x[3], ""));
				cr.setNombre(Objects.toString(x[4], ""));
			
				consultas.add(cr);
			});
			ar.setArbol(consultas);
			
			puesto.forEach(x -> {
				ar.setClavePuestoActual(Objects.toString(x[0], ""));
				ar.setNombrePuestoActual(Objects.toString(x[1], ""));
			});
			arbolResponse.add(ar);

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return arbolResponse;
	}

}
