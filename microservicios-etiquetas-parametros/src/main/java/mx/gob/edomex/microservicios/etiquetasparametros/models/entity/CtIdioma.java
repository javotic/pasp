package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ct_idiomas database table.
 * 
 */
@Entity
@Table(name="ct_idiomas")
@NamedQuery(name="CtIdioma.findAll", query="SELECT c FROM CtIdioma c")
public class CtIdioma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer llidioma;

	@Column(nullable=false)
	private Boolean boactivo;

	@Column(nullable=false, length=100)
	private String dsidioma;

	@Column(nullable=false, length=15)
	private String dslocale;

	//bi-directional many-to-one association to DtEtiqueta
	@OneToMany(mappedBy="ctIdioma")
	private List<DtEtiqueta> dtEtiquetas;

	public CtIdioma() {
	}

	public Integer getLlidioma() {
		return this.llidioma;
	}

	public void setLlidioma(Integer llidioma) {
		this.llidioma = llidioma;
	}

	public Boolean getBoactivo() {
		return this.boactivo;
	}

	public void setBoactivo(Boolean boactivo) {
		this.boactivo = boactivo;
	}

	public String getDsidioma() {
		return this.dsidioma;
	}

	public void setDsidioma(String dsidioma) {
		this.dsidioma = dsidioma;
	}

	public String getDslocale() {
		return this.dslocale;
	}

	public void setDslocale(String dslocale) {
		this.dslocale = dslocale;
	}

	public List<DtEtiqueta> getDtEtiquetas() {
		return this.dtEtiquetas;
	}

	public void setDtEtiquetas(List<DtEtiqueta> dtEtiquetas) {
		this.dtEtiquetas = dtEtiquetas;
	}

	public DtEtiqueta addDtEtiqueta(DtEtiqueta dtEtiqueta) {
		getDtEtiquetas().add(dtEtiqueta);
		dtEtiqueta.setCtIdioma(this);

		return dtEtiqueta;
	}

	public DtEtiqueta removeDtEtiqueta(DtEtiqueta dtEtiqueta) {
		getDtEtiquetas().remove(dtEtiqueta);
		dtEtiqueta.setCtIdioma(null);

		return dtEtiqueta;
	}

}