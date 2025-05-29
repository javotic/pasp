package mx.gob.edomex.microservicios.autoservicio.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ctparametros")
public class Parametros implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParametro;
	@NotEmpty
	private String claveParametro;
	@NotEmpty
	private String valorParametro;
	@NotEmpty
	private String descParametro;

	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public String getClaveParametro() {
		return claveParametro;
	}

	public void setClaveParametro(String claveParametro) {
		this.claveParametro = claveParametro;
	}

	public String getValorParametro() {
		return valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public String getDescParametro() {
		return descParametro;
	}

	public void setDescParametro(String descParametro) {
		this.descParametro = descParametro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idParametro == null) ? 0 : idParametro.hashCode());
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
		Parametros other = (Parametros) obj;
		if (idParametro == null) {
			if (other.idParametro != null)
				return false;
		} else if (!idParametro.equals(other.idParametro))
			return false;
		return true;
	}

}
