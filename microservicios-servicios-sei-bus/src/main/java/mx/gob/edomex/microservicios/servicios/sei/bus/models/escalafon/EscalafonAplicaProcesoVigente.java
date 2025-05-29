package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EscalafonAplicaProcesoVigente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("IDPROCESOESCALAFONARIO")
	private String idProceso;
	@JsonProperty("APLICA")
	private String aplica;
	@JsonProperty("MOTIVONOAPLICA")
	private String motivoNoAplica;
	@JsonProperty("BOINSCRITO")
	private String boInscrito;
	

	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

	

	public String getAplica() {
		return aplica;
	}

	public void setAplica(String aplica) {
		this.aplica = aplica;
	}

	public String getMotivoNoAplica() {
		return motivoNoAplica;
	}

	public void setMotivoNoAplica(String motivoNoAplica) {
		this.motivoNoAplica = motivoNoAplica;
	}

        public String getBoInscrito() {
            return boInscrito;
        }

        public void setBoInscrito(String boInscrito) {
            this.boInscrito = boInscrito;
        }
       
}
