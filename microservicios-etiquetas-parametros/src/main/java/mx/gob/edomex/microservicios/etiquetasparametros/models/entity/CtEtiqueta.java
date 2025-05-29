package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ct_etiquetas database table.
 * 
 */
@Entity
@Table(name="ct_etiquetas")
@NamedQuery(name="CtEtiqueta.findAll", query="SELECT c FROM CtEtiqueta c")
public class CtEtiqueta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer lletiqueta;

	@Column(nullable=false)
	private Boolean boactivo;

	@Column(nullable=false)
	private Boolean boglobal;

	@Column(nullable=false, length=100)
	private String dsetiqueta;

	@Column(nullable=false, length=30)
	private String dsllave;

	@Column(nullable=false)
	private Timestamp fcfecharegistro;

	//bi-directional many-to-many association to CtPagina
	@ManyToMany(mappedBy="ctEtiquetas")
	private List<CtPagina> ctPaginas;

	//bi-directional many-to-one association to DtEtiqueta
	@OneToMany(mappedBy="ctEtiqueta")
	private List<DtEtiqueta> dtEtiquetas;

	//bi-directional many-to-one association to CrcEtiquetaspagina
	@OneToMany(mappedBy="ctEtiqueta")
	private List<CrcEtiquetaspagina> crcEtiquetaspaginas;

	public CtEtiqueta() {
	}

	public Integer getLletiqueta() {
		return this.lletiqueta;
	}

	public void setLletiqueta(Integer lletiqueta) {
		this.lletiqueta = lletiqueta;
	}

	public Boolean getBoactivo() {
		return this.boactivo;
	}

	public void setBoactivo(Boolean boactivo) {
		this.boactivo = boactivo;
	}

	public Boolean getBoglobal() {
		return this.boglobal;
	}

	public void setBoglobal(Boolean boglobal) {
		this.boglobal = boglobal;
	}

	public String getDsetiqueta() {
		return this.dsetiqueta;
	}

	public void setDsetiqueta(String dsetiqueta) {
		this.dsetiqueta = dsetiqueta;
	}

	public String getDsllave() {
		return this.dsllave;
	}

	public void setDsllave(String dsllave) {
		this.dsllave = dsllave;
	}

	public Timestamp getFcfecharegistro() {
		return this.fcfecharegistro;
	}

	public void setFcfecharegistro(Timestamp fcfecharegistro) {
		this.fcfecharegistro = fcfecharegistro;
	}

	public List<CtPagina> getCtPaginas() {
		return this.ctPaginas;
	}

	public void setCtPaginas(List<CtPagina> ctPaginas) {
		this.ctPaginas = ctPaginas;
	}

	public List<DtEtiqueta> getDtEtiquetas() {
		return this.dtEtiquetas;
	}

	public void setDtEtiquetas(List<DtEtiqueta> dtEtiquetas) {
		this.dtEtiquetas = dtEtiquetas;
	}

	

	

	public List<CrcEtiquetaspagina> getCrcEtiquetaspaginas() {
		return this.crcEtiquetaspaginas;
	}

	public void setCrcEtiquetaspaginas(List<CrcEtiquetaspagina> crcEtiquetaspaginas) {
		this.crcEtiquetaspaginas = crcEtiquetaspaginas;
	}

	

}