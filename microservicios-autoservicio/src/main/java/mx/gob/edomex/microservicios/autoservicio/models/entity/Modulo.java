package mx.gob.edomex.microservicios.autoservicio.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modulos")
public class Modulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModulo;
	private String modulo;
	private Long activo;
	
	
	
	public Long getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}
	public String getModulo() {
		return modulo;
	}
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	public Long getActivo() {
		return activo;
	}
	public void setActivo(Long activo) {
		this.activo = activo;
	}
	
	

}
