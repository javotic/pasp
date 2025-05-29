package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EscalafonEstadoInscripcion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("IDESTATUSPLAZA")
	private String idEstatusPlaza;
	@JsonProperty("DESCRIPCIONESTATUS")
	private String detalleDescripcionEstatus;
	@JsonProperty("CANCELADO")
	private String cancelado;
	@JsonProperty("MOTIVOCANCELACION")
	private String motivoCancelacion;
	@JsonProperty("ESTATUSDICTAMEN")
	private String estatusDictamen;
	@JsonProperty("DESCRIPCIONDICTAMEN")
	private String descripcionDictamen;
	private String idProceso;
        private String calificacion;
        @JsonProperty("RANKING")
	private String ranking;
               
        private String numReg;
        
        @JsonProperty("IDMOTIVO")
	private String idMotivo;
        @JsonProperty("ENCUESTA")
	private String encuesta;     
        
        @JsonProperty("PUBLICARES")
        private String publicaRes;
        @JsonProperty("FECHAACEPTA")
        private String fechaAcepta;

	public String getIdEstatusPlaza() {
		return idEstatusPlaza;
	}

	public void setIdEstatusPlaza(String idEstatusPlaza) {
		this.idEstatusPlaza = idEstatusPlaza;
	}

	public String getDetalleDescripcionEstatus() {
		return detalleDescripcionEstatus;
	}

	public void setDetalleDescripcionEstatus(String detalleDescripcionEstatus) {
		this.detalleDescripcionEstatus = detalleDescripcionEstatus;
	}

	public String getEstatusDictamen() {
		return estatusDictamen;
	}

	public void setEstatusDictamen(String estatusDictamen) {
		this.estatusDictamen = estatusDictamen;
	}

	public String getDescripcionDictamen() {
		return descripcionDictamen;
	}

	public void setDescripcionDictamen(String descripcionDictamen) {
		this.descripcionDictamen = descripcionDictamen;
	}

	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

        public String getNumReg() {
            return numReg;
        }

        public void setNumReg(String numReg) {
            this.numReg = numReg;
        }

        public String getCalificacion() {
            return calificacion;
        }

        public void setCalificacion(String calificacion) {
            this.calificacion = calificacion;
        }   

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public String getIdMotivo() {
            return idMotivo;
        }

        public void setIdMotivo(String idMotivo) {
            this.idMotivo = idMotivo;
        }

        public String getEncuesta() {
            return encuesta;
        }

        public void setEncuesta(String encuesta) {
            this.encuesta = encuesta;
        }

        public String getPublicaRes() {
            return publicaRes;
        }

        public void setPublicaRes(String publicaRes) {
            this.publicaRes = publicaRes;
        }

        public String getFechaAcepta() {
            return fechaAcepta;
        }

        public void setFechaAcepta(String fechaAcepta) {
            this.fechaAcepta = fechaAcepta;
        }
        
        

}
