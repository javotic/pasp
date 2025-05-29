package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.ColoniaModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.EmisoresCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.TipoCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PagosModel;

/**
 *
 * @author smartinez
 */
public interface CatalogosService {
    
    List<EmisoresCertificadoModel> catEmisoresCertificado() throws BusException;
    List<PagosModel> getListadoPagos(String idServidorPublico, String fechaInicio, String fechaFin) throws BusException;
    List<ColoniaModel> getCatColonias(String idPais, String idEstado, String idMunicipio, String idColonia) throws BusException;
    List<TipoCertificadoModel> getTipoCertificados() throws  BusException;
    List<Combo> getMunicipiosByEstado(String idPais, String idEstado) throws BusException;
    
    
}
