package mx.gob.edomex.microservicios.autoservicio.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ctmenu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMenu;
	@NotEmpty
	private String nombreMenu;
	@NotEmpty
	private String url;
	@NotEmpty
	private boolean estatus;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private Long numOrden;
	@NotEmpty
	private String dsTooltip;
	@NotEmpty
	private String dsIcon;

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombreMenu() {
		return nombreMenu;
	}

	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(Long numOrden) {
		this.numOrden = numOrden;
	}

	public String getDsTooltip() {
		return dsTooltip;
	}

	public void setDsTooltip(String dsTooltip) {
		this.dsTooltip = dsTooltip;
	}

	public String getDsIcon() {
		return dsIcon;
	}

	public void setDsIcon(String dsIcon) {
		this.dsIcon = dsIcon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMenu == null) ? 0 : idMenu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (idMenu == null) {
			if (other.idMenu != null)
				return false;
		} else if (!idMenu.equals(other.idMenu))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [idMenu=" + idMenu + ", nombreMenu=" + nombreMenu + ", url=" + url + ", estatus=" + estatus
				+ ", descripcion=" + descripcion + ", numOrden=" + numOrden + ", dsTooltip=" + dsTooltip + ", dsIcon="
				+ dsIcon + "]";
	}
	
	

}
