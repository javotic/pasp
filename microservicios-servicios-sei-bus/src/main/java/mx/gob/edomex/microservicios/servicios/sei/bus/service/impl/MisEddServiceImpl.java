package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.MisEddDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.MisEdd;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.MisEddService;

@Service
public class MisEddServiceImpl implements MisEddService {

	@Autowired
	private MisEddDAO misEddDAO;
	
	@Override
	public List<MisEdd> obtenerMisEDD(String idServidorPublico) throws BusException {
		List<MisEdd> lstMisEdd = new ArrayList<>();
		try {
			List<Object[]> lstObj = misEddDAO.consultarMisEdd(idServidorPublico);
			lstObj.forEach(e->{
				MisEdd misEdd = new MisEdd();
				misEdd.setCALIFICACION(e[5].toString());
				misEdd.setCONSTANCIA(e[6].toString());
				misEdd.setFECHAFINPROCESOEVALUACION(e[2].toString());
				misEdd.setFECHAINICIOPROCESOEVALUACION(e[1].toString());				
				misEdd.setNOMBREPROCESOEVALUACION(e[0].toString());
				misEdd.setNOMBREPUESTO(e[4].toString());
				misEdd.setNOMBREUNIDADADMINISTRATIVA(e[3].toString());
				misEdd.setIDPROCESOVIGENTE(e[7].toString());
				lstMisEdd.add(misEdd);
			});
		} catch (Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return lstMisEdd;
	}

}
