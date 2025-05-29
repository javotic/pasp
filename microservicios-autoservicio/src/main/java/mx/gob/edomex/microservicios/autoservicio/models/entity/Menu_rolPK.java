package mx.gob.edomex.microservicios.autoservicio.models.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Menu_rolPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idMenu;

	private Long idRol;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	@Override
	public String toString() {
		return "Menu_rolPK [idMenu=" + idMenu + ", idRol=" + idRol + "]";
	}

}
