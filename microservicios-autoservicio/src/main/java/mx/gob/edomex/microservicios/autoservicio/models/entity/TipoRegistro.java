package mx.gob.edomex.microservicios.autoservicio.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_registro")
public class TipoRegistro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoRegistro;
	private String descripcion;
	private Long activo;

	

	public Long getIdTipoRegistro() {
		return idTipoRegistro;
	}

	public void setIdTipoRegistro(Long idTipoRegistro) {
		this.idTipoRegistro = idTipoRegistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getActivo() {
		return activo;
	}

	public void setActivo(Long activo) {
		this.activo = activo;
	}

}
