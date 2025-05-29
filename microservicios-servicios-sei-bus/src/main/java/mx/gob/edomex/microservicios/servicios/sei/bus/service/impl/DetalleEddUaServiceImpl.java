package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DetalleEddUaDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddUa;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoEddUa;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DetalleEddUaService;

public class DetalleEddUaServiceImpl implements DetalleEddUaService {

	@Autowired
	private DetalleEddUaDAO detalleEddUaDAO;

	@Override
	public List<DetalleEddUa> consultarServidorPublicoEdd(String IdServidorPublico, String IdProcesoVigente)
			throws BusException {
		List<DetalleEddUa> consultas = new ArrayList<>();

		try {
//			List<Object[]> lstDetalle = detalleEddUaDAO.consultarDetalleEddUa(IdServidorPublico, IdProcesoVigente);
//			lstDetalle.forEach(e -> {
//				DetalleEddUa deta = new DetalleEddUa();
//				deta.setIdevaluacionvigente((e[0].toString()));
//				deta.setNombreevaluacionvigente((e[1].toString()));
//				deta.setIdunidadadministrativa((e[2].toString()));
//				deta.setNombreunidadadministrativa((e[3].toString()));
//				deta.setTotalservidorespublicos((e[4].toString()));
//				deta.setTotalservidorespublicosevaluados((e[5].toString()));
//				deta.setTotalservidorespublicosfaltantes((e[6].toString()));
//				deta.setPorcentajeavanze((e[7].toString()));
//				consultas.add(deta);
//			});

		} catch (Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return consultas;
	}

}
