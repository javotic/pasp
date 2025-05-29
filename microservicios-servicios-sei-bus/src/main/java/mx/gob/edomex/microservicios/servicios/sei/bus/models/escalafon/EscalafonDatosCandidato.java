package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

public class EscalafonDatosCandidato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombreCompletoSP;
	private String claveSP;
	private String puestoNominal;
	private String categoria;
	private String adscripcion;
	private String secretaria;

	private String idProcesoVigente;
	private String numPlaza;
	private String puestoConcursa;
	private String categoriaPlazaConcursa;
	private String adscripcionPlazaConcursa;
	private String secretariaPlazaConcursa;
	private String nombreRepresentanteEscalafon;
	private String puestoRepresentanteEscalafon;
	private String numPlazaActual;
        
        private String firmaIzquierda;
        private String firmaCentral;
        private String firmaDerecha;
        private String idprocSecretaria;

	public String getNombreCompletoSP() {
		return nombreCompletoSP;
	}

	public void setNombreCompletoSP(String nombreCompletoSP) {
		this.nombreCompletoSP = nombreCompletoSP;
	}

	public String getClaveSP() {
		return claveSP;
	}

	public void setClaveSP(String claveSP) {
		this.claveSP = claveSP;
	}

	public String getPuestoNominal() {
		return puestoNominal;
	}

	public void setPuestoNominal(String puestoNominal) {
		this.puestoNominal = puestoNominal;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public String getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
	}

	public String getIdProcesoVigente() {
		return idProcesoVigente;
	}

	public void setIdProcesoVigente(String idProcesoVigente) {
		this.idProcesoVigente = idProcesoVigente;
	}

	public String getNumPlaza() {
		return numPlaza;
	}

	public void setNumPlaza(String numPlaza) {
		this.numPlaza = numPlaza;
	}

	public String getPuestoConcursa() {
		return puestoConcursa;
	}

	public void setPuestoConcursa(String puestoConcursa) {
		this.puestoConcursa = puestoConcursa;
	}

	public String getCategoriaPlazaConcursa() {
		return categoriaPlazaConcursa;
	}

	public void setCategoriaPlazaConcursa(String categoriaPlazaConcursa) {
		this.categoriaPlazaConcursa = categoriaPlazaConcursa;
	}

	public String getAdscripcionPlazaConcursa() {
		return adscripcionPlazaConcursa;
	}

	public void setAdscripcionPlazaConcursa(String adscripcionPlazaConcursa) {
		this.adscripcionPlazaConcursa = adscripcionPlazaConcursa;
	}

	public String getSecretariaPlazaConcursa() {
		return secretariaPlazaConcursa;
	}

	public void setSecretariaPlazaConcursa(String secretariaPlazaConcursa) {
		this.secretariaPlazaConcursa = secretariaPlazaConcursa;
	}

	public String getNombreRepresentanteEscalafon() {
		return nombreRepresentanteEscalafon;
	}

	public void setNombreRepresentanteEscalafon(String nombreRepresentanteEscalafon) {
		this.nombreRepresentanteEscalafon = nombreRepresentanteEscalafon;
	}

	public String getPuestoRepresentanteEscalafon() {
		return puestoRepresentanteEscalafon;
	}

	public void setPuestoRepresentanteEscalafon(String puestoRepresentanteEscalafon) {
		this.puestoRepresentanteEscalafon = puestoRepresentanteEscalafon;
	}

	public String getNumPlazaActual() {
		return numPlazaActual;
	}

	public void setNumPlazaActual(String numPlazaActual) {
		this.numPlazaActual = numPlazaActual;
	}

        public String getFirmaIzquierda() {
            return firmaIzquierda;
        }

        public void setFirmaIzquierda(String firmaIzquierda) {
            this.firmaIzquierda = firmaIzquierda;
        }

        public String getFirmaCentral() {
            return firmaCentral;
        }

        public void setFirmaCentral(String firmaCentral) {
            this.firmaCentral = firmaCentral;
        }

        public String getFirmaDerecha() {
            return firmaDerecha;
        }

        public void setFirmaDerecha(String firmaDerecha) {
            this.firmaDerecha = firmaDerecha;
        }

        public String getIdprocSecretaria() {
            return idprocSecretaria;
        }

        public void setIdprocSecretaria(String idprocSecretaria) {
            this.idprocSecretaria = idprocSecretaria;
        }
        
}
