package mx.gob.edomex.microservicios.etiquetasparametros.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the bt_etiquetas database table.
 * 
 */
@Entity
@Table(name="bt_etiquetas")
@NamedQuery(name="BtEtiqueta.findAll", query="SELECT b FROM BtEtiqueta b")
public class BtEtiqueta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer llbtetiqueta;

	@Column(length=200)
	private String dsvaloranterior;

	@Column(nullable=false)
	private Timestamp fcfechamodificacion;

	//bi-directional many-to-one association to CtUsuariosistema
	/*@ManyToOne
	@JoinColumn(name="llusuariosistema", nullable=false)
	private CtUsuariosistema ctUsuariosistema;*/
	@Column(nullable=false)
	private Integer llusuariosistema;
	

	//bi-directional many-to-one association to DtEtiqueta
	@ManyToOne
	@JoinColumn(name="lldtetiqueta", nullable=false)
	private DtEtiqueta dtEtiqueta;

	public BtEtiqueta() {
	}

	public Integer getLlbtetiqueta() {
		return this.llbtetiqueta;
	}

	public void setLlbtetiqueta(Integer llbtetiqueta) {
		this.llbtetiqueta = llbtetiqueta;
	}

	public String getDsvaloranterior() {
		return this.dsvaloranterior;
	}

	public void setDsvaloranterior(String dsvaloranterior) {
		this.dsvaloranterior = dsvaloranterior;
	}

	public Timestamp getFcfechamodificacion() {
		return this.fcfechamodificacion;
	}

	public void setFcfechamodificacion(Timestamp fcfechamodificacion) {
		this.fcfechamodificacion = fcfechamodificacion;
	}

	

	public DtEtiqueta getDtEtiqueta() {
		return this.dtEtiqueta;
	}

	public void setDtEtiqueta(DtEtiqueta dtEtiqueta) {
		this.dtEtiqueta = dtEtiqueta;
	}

}