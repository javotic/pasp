package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ct_paginas database table.
 * 
 */
@Entity
@Table(name="ct_paginas")
@NamedQuery(name="CtPagina.findAll", query="SELECT c FROM CtPagina c")
public class CtPagina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer llpagina;

	@Column(nullable=false)
	private Boolean boactivo;

	@Column(nullable=false, length=100)
	private String dspagina;

	@Column(nullable=false, length=255)
	private String dsurlpagina;

	@Column(nullable=false)
	private Timestamp fcfechacreacion;

	//bi-directional many-to-many association to CtEtiqueta
	@ManyToMany
	@JoinTable(
		name="crc_etiquetaspaginas"
		, joinColumns={
			@JoinColumn(name="llpagina", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="lletiqueta", nullable=false)
			}
		)
	private List<CtEtiqueta> ctEtiquetas;

	//bi-directional many-to-one association to CtModulo
	@ManyToOne
	@JoinColumn(name="llmodulo", nullable=false)
	private CtModulo ctModulo;

	//bi-directional many-to-one association to CrcEtiquetaspagina
	@OneToMany(mappedBy="ctPagina")
	private List<CrcEtiquetaspagina> crcEtiquetaspaginas;

	public CtPagina() {
	}

	public Integer getLlpagina() {
		return this.llpagina;
	}

	public void setLlpagina(Integer llpagina) {
		this.llpagina = llpagina;
	}

	public Boolean getBoactivo() {
		return this.boactivo;
	}

	public void setBoactivo(Boolean boactivo) {
		this.boactivo = boactivo;
	}

	public String getDspagina() {
		return this.dspagina;
	}

	public void setDspagina(String dspagina) {
		this.dspagina = dspagina;
	}

	public String getDsurlpagina() {
		return this.dsurlpagina;
	}

	public void setDsurlpagina(String dsurlpagina) {
		this.dsurlpagina = dsurlpagina;
	}

	public Timestamp getFcfechacreacion() {
		return this.fcfechacreacion;
	}

	public void setFcfechacreacion(Timestamp fcfechacreacion) {
		this.fcfechacreacion = fcfechacreacion;
	}

	public List<CtEtiqueta> getCtEtiquetas() {
		return this.ctEtiquetas;
	}

	public void setCtEtiquetas(List<CtEtiqueta> ctEtiquetas) {
		this.ctEtiquetas = ctEtiquetas;
	}

	public CtModulo getCtModulo() {
		return this.ctModulo;
	}

	public void setCtModulo(CtModulo ctModulo) {
		this.ctModulo = ctModulo;
	}

	public List<CrcEtiquetaspagina> getCrcEtiquetaspaginas() {
		return this.crcEtiquetaspaginas;
	}

	public void setCrcEtiquetaspaginas(List<CrcEtiquetaspagina> crcEtiquetaspaginas) {
		this.crcEtiquetaspaginas = crcEtiquetaspaginas;
	}

	

}