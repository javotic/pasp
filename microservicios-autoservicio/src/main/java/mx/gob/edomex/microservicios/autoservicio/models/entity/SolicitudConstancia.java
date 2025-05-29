package mx.gob.edomex.microservicios.autoservicio.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud_constancia")
public class SolicitudConstancia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idServidorPublico;
	@ManyToOne
	@JoinColumn(name = "id_tipo_constancia", nullable = false, foreignKey = @ForeignKey(name = "FK_solicitud_constancia"))
	private TipoConstancia tipoConstancia;
	@ManyToOne
	@JoinColumn(name = "aprobado", nullable = false, foreignKey = @ForeignKey(name = "FK_solicitud_estatus"))
	private CatEstatus estatus;
	
	
	private String justificacion;
	private Long idPlaza;
	private Date fechaSolicitud;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdServidorPublico() {
		return idServidorPublico;
	}

	public void setIdServidorPublico(Long idServidorPublico) {
		this.idServidorPublico = idServidorPublico;
	}

	public TipoConstancia getTipoConstancia() {
		return tipoConstancia;
	}

	public void setTipoConstancia(TipoConstancia tipoConstancia) {
		this.tipoConstancia = tipoConstancia;
	}

	public CatEstatus getEstatus() {
		return estatus;
	}

	public void setEstatus(CatEstatus estatus) {
		this.estatus = estatus;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public Long getIdPlaza() {
		return idPlaza;
	}

	public void setIdPlaza(Long idPlaza) {
		this.idPlaza = idPlaza;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	

}
