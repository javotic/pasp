/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.serviciosreportes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author smartinez
 */
public class EscalafonEstadoInscripcionDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String IDESTATUSPLAZA;
	private String DESCRIPCIONESTATUS;
	private String CANCELADO;
	private String MOTIVOCANCELACION;
	private String ESTATUSDICTAMEN;
	private String DESCRIPCIONDICTAMEN;
	private String idProceso;
        private String calificacion;
        
        private String numReg;

    public String getIDESTATUSPLAZA() {
        return IDESTATUSPLAZA;
    }

    public void setIDESTATUSPLAZA(String IDESTATUSPLAZA) {
        this.IDESTATUSPLAZA = IDESTATUSPLAZA;
    }

    public String getDESCRIPCIONESTATUS() {
        return DESCRIPCIONESTATUS;
    }

    public void setDESCRIPCIONESTATUS(String DESCRIPCIONESTATUS) {
        this.DESCRIPCIONESTATUS = DESCRIPCIONESTATUS;
    }

    public String getCANCELADO() {
        return CANCELADO;
    }

    public void setCANCELADO(String CANCELADO) {
        this.CANCELADO = CANCELADO;
    }

    public String getMOTIVOCANCELACION() {
        return MOTIVOCANCELACION;
    }

    public void setMOTIVOCANCELACION(String MOTIVOCANCELACION) {
        this.MOTIVOCANCELACION = MOTIVOCANCELACION;
    }

    public String getESTATUSDICTAMEN() {
        return ESTATUSDICTAMEN;
    }

    public void setESTATUSDICTAMEN(String ESTATUSDICTAMEN) {
        this.ESTATUSDICTAMEN = ESTATUSDICTAMEN;
    }

    public String getDESCRIPCIONDICTAMEN() {
        return DESCRIPCIONDICTAMEN;
    }

    public void setDESCRIPCIONDICTAMEN(String DESCRIPCIONDICTAMEN) {
        this.DESCRIPCIONDICTAMEN = DESCRIPCIONDICTAMEN;
    }

        
        
	public String getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
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
     
}
