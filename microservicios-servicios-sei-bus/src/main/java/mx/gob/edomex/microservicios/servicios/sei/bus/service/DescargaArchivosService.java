package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import com.jcraft.jsch.JSchException;
import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.dto.InscripcionDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosProfecionalesDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonAplicaProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonEstadoInscripcion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPlazasDisponibles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.SesionExamenDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.SesionesDTO;

public interface DescargaArchivosService {
       
        byte[] descargaGuiaEstudio(String idGuiaPuesto) throws BusException, JSchException;
}
