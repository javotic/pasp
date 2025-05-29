package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DetalleEddUaDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ServidorPublicoEddUaDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddUa;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialEvaluacion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoEddUa;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ServidorPublicoEddUaService;

@Service
public class ServidorPublicoEddUaServiceImpl implements ServidorPublicoEddUaService {
	@Autowired
	private ServidorPublicoEddUaDAO servidorPublicoEddUaDAO;

	@Autowired
	private DetalleEddUaDAO detalleEddUaDAO;

	@Override
	public DetalleEddeSP consultarServidorPublicoEdd(String IdServidorPublico,
			String IdUnidadAdministrativa, String IdProcesoVigente) throws BusException {
		DetalleEddeSP deta = new DetalleEddeSP();
		try {

                        
			//List<Object[]> lstDetalle = detalleEddUaDAO.consultarDetalleEddUa(IdServidorPublico, IdProcesoVigente);
                        List<Object[]> lstDetalleUA = detalleEddUaDAO.consultarDetalleEdd_Ua(IdServidorPublico, IdProcesoVigente,IdUnidadAdministrativa);
                        
			List<Object[]> data = servidorPublicoEddUaDAO.consultarServidoresEddUaI(IdServidorPublico,
					IdUnidadAdministrativa, IdProcesoVigente);
			deta = new DetalleEddeSP();
			deta.setIdevaluacionvigente((lstDetalleUA.get(0)[0].toString()));
			deta.setNombreevaluacionvigente((lstDetalleUA.get(0)[1].toString()));
			deta.setIdunidadadministrativa((lstDetalleUA.get(0)[2].toString()));
			deta.setNombreunidadadministrativa((lstDetalleUA.get(0)[3].toString()));
			deta.setTotalservidorespublicos((lstDetalleUA.get(0)[4].toString()));
			deta.setTotalservidorespublicosevaluados((lstDetalleUA.get(0)[5].toString()));
			deta.setTotalservidorespublicosfaltantes((lstDetalleUA.get(0)[6].toString()));
			deta.setPorcentajeavanze((lstDetalleUA.get(0)[7].toString()));
			deta.setLSTSERVIDORESPUBLICOS(new ArrayList<>());
			List<ServidorPublicoEddUa> lstSP = new ArrayList<>();
			data.forEach(e -> {
				ServidorPublicoEddUa sp = new ServidorPublicoEddUa();
				sp.setCLAVESERVIDORPUBLICO((e[0].toString()));
				sp.setNOMBRESERVIDORPUBLICO((e[1].toString()));
				sp.setAPELLIDOPATERNOSERVIDORPUBLIC((e[2].toString()));
				sp.setAPELLIDOMATERNOSERVIDORPUBLIC((e[3].toString()));
				sp.setRFCSERVIDORPUBLICO((e[4].toString()));
				sp.setPUESTOSERVIDORPUBLICO((e[5].toString()));
				sp.setTIPOEVALUACION((e[6].toString()));
				sp.setESTATUSEVALUACION((e[7].toString()));
				sp.setIDPROCESOVIGENTE((e[8].toString()));
				sp.setIDEVALUACIONKP((e[9].toString()));
				sp.setCORREOSERVIDORPUBLICO(e[10].toString());
                                sp.setIDUNIDAD_ASIGNADA(e[11].toString());
                                sp.setUNIDAD_ASIGNADA(e[12].toString());
				lstSP.add(sp);
			});
			deta.setLSTSERVIDORESPUBLICOS(lstSP);

		} catch (Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return deta;
	}

}
