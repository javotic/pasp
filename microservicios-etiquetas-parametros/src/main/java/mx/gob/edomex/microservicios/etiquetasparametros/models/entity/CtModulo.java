package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ct_modulos database table.
 * 
 */
@Entity
@Table(name="ct_modulos")
@NamedQuery(name="CtModulo.findAll", query="SELECT c FROM CtModulo c")
public class CtModulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer llmodulo;

	@Column(nullable=false)
	private Boolean boactivo;

	@Column(nullable=false, length=100)
	private String dsmodulo;

	@Column(nullable=false)
	private Timestamp fcfechacreacion;

	//bi-directional many-to-one association to CtPagina
	@OneToMany(mappedBy="ctModulo")
	private List<CtPagina> ctPaginas;

	public CtModulo() {
	}

	public Integer getLlmodulo() {
		return this.llmodulo;
	}

	public void setLlmodulo(Integer llmodulo) {
		this.llmodulo = llmodulo;
	}

	public Boolean getBoactivo() {
		return this.boactivo;
	}

	public void setBoactivo(Boolean boactivo) {
		this.boactivo = boactivo;
	}

	public String getDsmodulo() {
		return this.dsmodulo;
	}

	public void setDsmodulo(String dsmodulo) {
		this.dsmodulo = dsmodulo;
	}

	public Timestamp getFcfechacreacion() {
		return this.fcfechacreacion;
	}

	public void setFcfechacreacion(Timestamp fcfechacreacion) {
		this.fcfechacreacion = fcfechacreacion;
	}

	public List<CtPagina> getCtPaginas() {
		return this.ctPaginas;
	}

	public void setCtPaginas(List<CtPagina> ctPaginas) {
		this.ctPaginas = ctPaginas;
	}

	public CtPagina addCtPagina(CtPagina ctPagina) {
		getCtPaginas().add(ctPagina);
		ctPagina.setCtModulo(this);

		return ctPagina;
	}

	public CtPagina removeCtPagina(CtPagina ctPagina) {
		getCtPaginas().remove(ctPagina);
		ctPagina.setCtModulo(null);

		return ctPagina;
	}

}