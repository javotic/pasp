package mx.gob.edomex.microservicios.notificaciones.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the dt_notificaciones database table.
 * 
 */
@Entity
@Table(name="dt_notificaciones")
@NamedQuery(name="DtNotificaciones.findAll", query="SELECT d FROM DtNotificaciones d")
public class DtNotificaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long llnotificaciones;

	private String cveservidorpublico;

	private Boolean estatusnotificacion;

	private Timestamp fechanotificacion;

	//bi-directional many-to-one association to CtTiposnotificacione
	/*@ManyToOne
	@JoinColumn(name="lltiponotificacion")
	private CtTiposNotificaciones ctTiposNotificaciones;*/
	@Column(nullable=false)
	private Integer lltiponotificacion;
	
	private String idnotificacionbus;

	public DtNotificaciones() {
	}

	public Long getLlnotificaciones() {
		return this.llnotificaciones;
	}

	public void setLlnotificaciones(Long llnotificaciones) {
		this.llnotificaciones = llnotificaciones;
	}

	public String getCveservidorpublico() {
		return this.cveservidorpublico;
	}

	public void setCveservidorpublico(String cveservidorpublico) {
		this.cveservidorpublico = cveservidorpublico;
	}

	public Boolean getEstatusnotificacion() {
		return this.estatusnotificacion;
	}

	public void setEstatusnotificacion(Boolean estatusnotificacion) {
		this.estatusnotificacion = estatusnotificacion;
	}

	public Timestamp getFechanotificacion() {
		return this.fechanotificacion;
	}

	public void setFechanotificacion(Timestamp fechanotificacion) {
		this.fechanotificacion = fechanotificacion;
	}

	public Integer getLltiponotificacion() {
		return lltiponotificacion;
	}

	public void setLltiponotificacion(Integer lltiponotificacion) {
		this.lltiponotificacion = lltiponotificacion;
	}

	public String getIdnotificacionbus() {
		return idnotificacionbus;
	}

	public void setIdnotificacionbus(String idnotificacionbus) {
		this.idnotificacionbus = idnotificacionbus;
	}
	
	
	
	

	/*public CtTiposNotificaciones getCtTiposnotificacione() {
		return this.ctTiposNotificaciones;
	}

	public void setCtTiposnotificacione(CtTiposNotificaciones ctTiposNotificaciones) {
		this.ctTiposNotificaciones = ctTiposNotificaciones;
	}*/

}