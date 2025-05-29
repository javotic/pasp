package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the crc_etiquetaspaginas database table.
 * 
 */
@Embeddable
public class CrcEtiquetaspaginaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer lletiqueta;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer llpagina;

	public CrcEtiquetaspaginaPK() {
	}
	public Integer getLletiqueta() {
		return this.lletiqueta;
	}
	public void setLletiqueta(Integer lletiqueta) {
		this.lletiqueta = lletiqueta;
	}
	public Integer getLlpagina() {
		return this.llpagina;
	}
	public void setLlpagina(Integer llpagina) {
		this.llpagina = llpagina;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CrcEtiquetaspaginaPK)) {
			return false;
		}
		CrcEtiquetaspaginaPK castOther = (CrcEtiquetaspaginaPK)other;
		return 
			this.lletiqueta.equals(castOther.lletiqueta)
			&& this.llpagina.equals(castOther.llpagina);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.lletiqueta.hashCode();
		hash = hash * prime + this.llpagina.hashCode();
		
		return hash;
	}
}