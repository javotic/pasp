package mx.gob.edomex.microservicios.autoservicio.models.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "dtevaluaciondesempenio")
public class EvaluacionDesempenio  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDtEvaluacionDesempenio;	
	@NotEmpty
	private String idseccion;
	@NotEmpty
	private String claveproceso;
	@NotEmpty
	private String spevaluador;
	@NotEmpty
	private String spevaluado;
	@NotEmpty
	private String claveunidadadmin;
	@NotEmpty
	private String idpregunta;
	@NotEmpty
	private String idrespuesta;

	public Long getIdDtEvaluacionDesempenio() {
		return idDtEvaluacionDesempenio;
	}

	public void setIdDtEvaluacionDesempenio(Long idDtEvaluacionDesempenio) {
		this.idDtEvaluacionDesempenio = idDtEvaluacionDesempenio;
	}

	public String getIdseccion() {
		return idseccion;
	}

	public void setIdseccion(String idseccion) {
		this.idseccion = idseccion;
	}

	public String getClaveproceso() {
		return claveproceso;
	}

	public void setClaveproceso(String claveproceso) {
		this.claveproceso = claveproceso;
	}

	public String getSpevaluador() {
		return spevaluador;
	}

	public void setSpevaluador(String spevaluador) {
		this.spevaluador = spevaluador;
	}

	public String getSpevaluado() {
		return spevaluado;
	}

	public void setSpevaluado(String spevaluado) {
		this.spevaluado = spevaluado;
	}

	public String getClaveunidadadmin() {
		return claveunidadadmin;
	}

	public void setClaveunidadadmin(String claveunidadadmin) {
		this.claveunidadadmin = claveunidadadmin;
	}

	public String getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(String idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getIdrespuesta() {
		return idrespuesta;
	}

	public void setIdrespuesta(String idrespuesta) {
		this.idrespuesta = idrespuesta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDtEvaluacionDesempenio == null) ? 0 : idDtEvaluacionDesempenio.hashCode());
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
		EvaluacionDesempenio other = (EvaluacionDesempenio) obj;
		if (idDtEvaluacionDesempenio == null) {
			if (other.idDtEvaluacionDesempenio != null)
				return false;
		} else if (!idDtEvaluacionDesempenio.equals(other.idDtEvaluacionDesempenio))
			return false;
		return true;
	}

	
}
