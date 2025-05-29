package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the crc_etiquetaspaginas database table.
 * 
 */
@Entity
@Table(name="crc_etiquetaspaginas")
@NamedQuery(name="CrcEtiquetaspagina.findAll", query="SELECT c FROM CrcEtiquetaspagina c")
public class CrcEtiquetaspagina implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CrcEtiquetaspaginaPK id;

	//bi-directional many-to-one association to CtEtiqueta
	@ManyToOne
	@JoinColumn(name="lletiqueta", nullable=false, insertable=false, updatable=false)
	private CtEtiqueta ctEtiqueta;

	//bi-directional many-to-one association to CtPagina
	@ManyToOne
	@JoinColumn(name="llpagina", nullable=false, insertable=false, updatable=false)
	private CtPagina ctPagina;

	public CrcEtiquetaspagina() {
	}

	public CrcEtiquetaspaginaPK getId() {
		return this.id;
	}

	public void setId(CrcEtiquetaspaginaPK id) {
		this.id = id;
	}

	public CtEtiqueta getCtEtiqueta() {
		return this.ctEtiqueta;
	}

	public void setCtEtiqueta(CtEtiqueta ctEtiqueta) {
		this.ctEtiqueta = ctEtiqueta;
	}

	public CtPagina getCtPagina() {
		return this.ctPagina;
	}

	public void setCtPagina(CtPagina ctPagina) {
		this.ctPagina = ctPagina;
	}

}