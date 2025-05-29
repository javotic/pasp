/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComisionesAsignaciones;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEDD;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DetalleEddeSP;

/**
 *
 * @author smartinez
 */
public interface EvaluacionHistoricoService {
    
    List<DetalleEDD> getDetalleUnidades(String IdServidorPublico, String IdProcesoVigente) throws BusException;
    DetalleEddeSP getServidoresedduaHt(String IdServidorPublico, String IdUnidadAdministrativa, String IdProcesoVigente) throws BusException;
    
}
