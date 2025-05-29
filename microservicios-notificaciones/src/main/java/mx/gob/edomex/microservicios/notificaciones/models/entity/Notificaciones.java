package mx.gob.edomex.microservicios.notificaciones.models.entity;

import java.io.Serializable;
import java.util.List;

public class Notificaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<DtNotificaciones> lstNotificaciones;

	public List<DtNotificaciones> getLstNotificaciones() {
		return lstNotificaciones;
	}

	public void setLstNotificaciones(List<DtNotificaciones> lstNotificaciones) {
		this.lstNotificaciones = lstNotificaciones;
	}
	
	

}
