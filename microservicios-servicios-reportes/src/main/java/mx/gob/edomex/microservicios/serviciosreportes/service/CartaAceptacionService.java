package mx.gob.edomex.microservicios.serviciosreportes.service;

public interface CartaAceptacionService {

    byte[] getReporteCartaAceptacion(String claveServidorPublico, String idProcesoVigente);

    byte[] getReporteCartaAceptacionUsuario(String claveServidorPublico, String idProcesoVigente,
            boolean aceptoCarta, String idPlazaParticipacion, String lugarProceso, boolean actualizarRegistro);
}
