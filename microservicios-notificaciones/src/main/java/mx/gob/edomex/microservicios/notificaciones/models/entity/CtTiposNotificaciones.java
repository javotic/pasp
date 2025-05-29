package mx.gob.edomex.microservicios.notificaciones.models.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ct_tiposnotificaciones database table.
 * 
 */
@Entity
@Table(name="ct_tiposnotificaciones")
@NamedQuery(name="CtTiposNotificaciones.findAll", query="SELECT c FROM CtTiposNotificaciones c")
public class CtTiposNotificaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long lltiponotificacion;

	private Boolean boactivo;

	private String descnotificacion;

	@Column(name="id_menu")
	private Long idMenu;

	//bi-directional many-to-one association to DtNotificacione
	/*@OneToMany(mappedBy="ctTiposnotificacione")
	private List<DtNotificaciones> dtNotificaciones;*/

	public CtTiposNotificaciones() {
	}

	public Long getLltiponotificacion() {
		return this.lltiponotificacion;
	}

	public void setLltiponotificacion(Long lltiponotificacion) {
		this.lltiponotificacion = lltiponotificacion;
	}

	public Boolean getBoactivo() {
		return this.boactivo;
	}

	public void setBoactivo(Boolean boactivo) {
		this.boactivo = boactivo;
	}

	public String getDescnotificacion() {
		return this.descnotificacion;
	}

	public void setDescnotificacion(String descnotificacion) {
		this.descnotificacion = descnotificacion;
	}

	public Long getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	/*public List<DtNotificaciones> getDtNotificaciones() {
		return this.dtNotificaciones;
	}

	public void setDtNotificaciones(List<DtNotificaciones> dtNotificaciones) {
		this.dtNotificaciones = dtNotificaciones;
	}*/

	

}