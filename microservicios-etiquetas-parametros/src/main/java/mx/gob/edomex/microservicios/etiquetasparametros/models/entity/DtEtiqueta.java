package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dt_etiquetas database table.
 * 
 */
@Entity
@Table(name="dt_etiquetas")
@NamedQuery(name="DtEtiqueta.findAll", query="SELECT d FROM DtEtiqueta d")
public class DtEtiqueta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer lldtetiqueta;

	@Column(nullable=false, length=200)
	private String dsvaloretiqueta;

	//bi-directional many-to-one association to BtEtiqueta
	@OneToMany(mappedBy="dtEtiqueta")
	private List<BtEtiqueta> btEtiquetas;

	//bi-directional many-to-one association to CtEtiqueta
	@ManyToOne
	@JoinColumn(name="lletiqueta", nullable=false)
	private CtEtiqueta ctEtiqueta;

	//bi-directional many-to-one association to CtIdioma
	@ManyToOne
	@JoinColumn(name="llidioma", nullable=false)
	private CtIdioma ctIdioma;

	public DtEtiqueta() {
	}

	public Integer getLldtetiqueta() {
		return this.lldtetiqueta;
	}

	public void setLldtetiqueta(Integer lldtetiqueta) {
		this.lldtetiqueta = lldtetiqueta;
	}

	public String getDsvaloretiqueta() {
		return this.dsvaloretiqueta;
	}

	public void setDsvaloretiqueta(String dsvaloretiqueta) {
		this.dsvaloretiqueta = dsvaloretiqueta;
	}

	public List<BtEtiqueta> getBtEtiquetas() {
		return this.btEtiquetas;
	}

	public void setBtEtiquetas(List<BtEtiqueta> btEtiquetas) {
		this.btEtiquetas = btEtiquetas;
	}


	public CtEtiqueta getCtEtiqueta() {
		return this.ctEtiqueta;
	}

	public void setCtEtiqueta(CtEtiqueta ctEtiqueta) {
		this.ctEtiqueta = ctEtiqueta;
	}

	public CtIdioma getCtIdioma() {
		return this.ctIdioma;
	}

	public void setCtIdioma(CtIdioma ctIdioma) {
		this.ctIdioma = ctIdioma;
	}

}