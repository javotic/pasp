package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class DetalleEddUa implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDEVALUACIONVIGENTE")	
	private String idevaluacionvigente;
	private String nombreevaluacionvigente;
	private String idunidadadministrativa;
	private String nombreunidadadministrativa;
	private String totalservidorespublicos;
	private String totalservidorespublicosevaluados;
	private String totalservidorespublicosfaltantes;
	private String porcentajeavanze;
	

	@JsonProperty("IDEVALUACIONVIGENTE")
	public String getIdevaluacionvigente() {
		return idevaluacionvigente;
	}

	public void setIdevaluacionvigente(String idevaluacionvigente) {
		this.idevaluacionvigente = idevaluacionvigente;
	}

	@JsonProperty("NOMBREEVALUACIONVIGENTE")
	public String getNombreevaluacionvigente() {
		return nombreevaluacionvigente;
	}

	public void setNombreevaluacionvigente(String nombreevaluacionvigente) {
		this.nombreevaluacionvigente = nombreevaluacionvigente;
	}
	
	@JsonProperty("IDUNIDADADMINISTRATIVA")
	public String getIdunidadadministrativa() {
		return idunidadadministrativa;
	}

	public void setIdunidadadministrativa(String idunidadadministrativa) {
		this.idunidadadministrativa = idunidadadministrativa;
	}
	
	@JsonProperty("NOMBREUNIDADADMINISTRATIVA")
	public String getNombreunidadadministrativa() {
		return nombreunidadadministrativa;
	}

	public void setNombreunidadadministrativa(String nombreunidadadministrativa) {
		this.nombreunidadadministrativa = nombreunidadadministrativa;
	}
	
	@JsonProperty("TOTALSERVIDORESPUBLICOS")
	public String getTotalservidorespublicos() {
		return totalservidorespublicos;
	}

	public void setTotalservidorespublicos(String totalservidorespublicos) {
		this.totalservidorespublicos = totalservidorespublicos;
	}
	
	@JsonProperty("TOTALSERVIDORESPUBLICOSEVALUADOS")
	public String getTotalservidorespublicosevaluados() {
		return totalservidorespublicosevaluados;
	}

	public void setTotalservidorespublicosevaluados(String totalservidorespublicosevaluados) {
		this.totalservidorespublicosevaluados = totalservidorespublicosevaluados;
	}
    
	@JsonProperty("TOTALSERVIDORESPUBLICOSFALTANTES")
	public String getTotalservidorespublicosfaltantes() {
		return totalservidorespublicosfaltantes;
	}

	public void setTotalservidorespublicosfaltantes(String totalservidorespublicosfaltantes) {
		this.totalservidorespublicosfaltantes = totalservidorespublicosfaltantes;
	}
    
	@JsonProperty("PORCENTAJEAVANZE")
	public String getPorcentajeavanze() {
		return porcentajeavanze;
	}

	public void setPorcentajeavanze(String porcentajeavanze) {
		this.porcentajeavanze = porcentajeavanze;
	}
	
	
	

}
