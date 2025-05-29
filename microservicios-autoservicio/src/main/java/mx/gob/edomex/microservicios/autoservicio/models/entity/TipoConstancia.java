package mx.gob.edomex.microservicios.autoservicio.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "constancia")
public class TipoConstancia {
	
	@Id
	private Long idTipoConstancia;
	private String tipo;
	
	public Long getIdTipoConstancia() {
		return idTipoConstancia;
	}
	public void setIdTipoConstancia(Long idTipoConstancia) {
		this.idTipoConstancia = idTipoConstancia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
