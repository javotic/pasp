package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.EscalafonHistoricoProcesoDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonHistoricoProcesoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.EscalafonHistoricoProcesoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class EscalafonHistoricoProcesoServiceImpl implements EscalafonHistoricoProcesoService{

	@Autowired
	private EscalafonHistoricoProcesoDAO dao;
	@Override
	public List<EscalafonHistoricoProcesoDTO> escalafonHistoricoProcesos(String claveServidorPublico) throws BusException {
		List<EscalafonHistoricoProcesoDTO> consultas = new ArrayList<>();
                Utils utils = new Utils();
		try {

			List<Object[]> data = dao.escalafonHistoricoProcesos(claveServidorPublico);
			data.forEach(x -> {
				EscalafonHistoricoProcesoDTO cr = new EscalafonHistoricoProcesoDTO();
                                cr.setCLAVEPROCESOESCALAFONARIO(utils.objectIsNULL(x[0]));
                                cr.setNOMBREPROCESOESCALAFONARIO(utils.objectIsNULL(x[1]));
                                cr.setFECHAINICIOPROCESO(utils.objectIsNULL(x[2]));
                                cr.setFECHAFINPROCESO(utils.objectIsNULL(x[3]));
                                cr.setCLAVEPLAZA(utils.objectIsNULL(x[4]));
                                cr.setNOMBREPLAZA(utils.objectIsNULL(x[5]));
                                cr.setCLAVEPUESTO(utils.objectIsNULL(x[6]));
                                cr.setNOMBREPUESTO(utils.objectIsNULL(x[7]));
                                cr.setPUNTAJELOGRADO(utils.objectIsNULL(x[8]));
                                cr.setLUGARRANKING(utils.objectIsNULL(x[9]));
                                cr.setDICTAMEN(utils.objectIsNULL(x[10]));
                                cr.setDESCRIPCIONESTATUS(utils.objectIsNULL(x[11]));
				
				consultas.add(cr);
			});

		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return consultas;
	}

}
