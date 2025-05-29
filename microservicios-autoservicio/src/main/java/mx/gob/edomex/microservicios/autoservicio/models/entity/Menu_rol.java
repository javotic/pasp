package mx.gob.edomex.microservicios.autoservicio.models.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dt_menu_rol")
public class Menu_rol {
	@EmbeddedId
	private Menu_rolPK id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMenu", referencedColumnName = "idMenu", insertable = false, updatable = false)
	private Menu menu;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRol", referencedColumnName = "idRol", insertable = false, updatable = false)
	private Rol rol;

	public Menu_rolPK getId() {
		return id;
	}

	public void setId(Menu_rolPK id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Menu_rol [id=" + id + ", menu=" + menu + ", rol=" + rol + "]";
	}
	

}
