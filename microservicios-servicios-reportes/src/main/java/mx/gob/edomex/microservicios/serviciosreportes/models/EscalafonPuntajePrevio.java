package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EscalafonPuntajePrevio implements Serializable {

	private static final long serialVersionUID = 1L;
	private String CAPACITACION;
	private String ESCOLARIDAD;
	private String EFICIENCIA;
	private String ANTIGUEDAD;
	private String DEMERITOS;
	private String TOTAL;
        private String puntajeSubTotal;

	public String getCAPACITACION() {
		return CAPACITACION;
	}

	public void setCAPACITACION(String cAPACITACION) {
		CAPACITACION = cAPACITACION;
	}

	public String getESCOLARIDAD() {
		return ESCOLARIDAD;
	}

	public void setESCOLARIDAD(String eSCOLARIDAD) {
		ESCOLARIDAD = eSCOLARIDAD;
	}

	public String getEFICIENCIA() {
		return EFICIENCIA;
	}

	public void setEFICIENCIA(String eFICIENCIA) {
		EFICIENCIA = eFICIENCIA;
	}

	public String getANTIGUEDAD() {
		return ANTIGUEDAD;
	}

	public void setANTIGUEDAD(String aNTIGUEDAD) {
		ANTIGUEDAD = aNTIGUEDAD;
	}

	public String getDEMERITOS() {
		return DEMERITOS;
	}

	public void setDEMERITOS(String dEMERITOS) {
		DEMERITOS = dEMERITOS;
	}

	public String getTOTAL() {
		return TOTAL;
	}

	public void setTOTAL(String tOTAL) {
		TOTAL = tOTAL;
	}

        public String getPuntajeSubTotal() {
            return puntajeSubTotal;
        }

        public void setPuntajeSubTotal(String puntajeSubTotal) {
            this.puntajeSubTotal = puntajeSubTotal;
        }      
}
