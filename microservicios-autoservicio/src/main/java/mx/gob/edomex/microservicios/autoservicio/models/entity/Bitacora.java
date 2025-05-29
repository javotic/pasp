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
@Table(name = "bitacora")
public class Bitacora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idServidorPublico;
	@ManyToOne
	@JoinColumn(name = "id_modulo", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_modulos"))
	private Modulo modulo;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_registro", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_tipo_registro"))
	private TipoRegistro tipoRegistro;
	private String descripcion;
	
	private Date fechaRegistro;
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
	
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	
	
	

}
