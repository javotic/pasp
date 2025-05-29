package mx.gob.edomex.microservicios.serviciosreportes.service;

public interface ConstanciaKPIService {
	byte[] getReporteKPI(String fecha, String servidorPublico, String proceso);
}
