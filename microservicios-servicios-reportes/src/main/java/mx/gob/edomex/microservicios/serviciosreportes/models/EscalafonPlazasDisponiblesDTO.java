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
public class EscalafonPlazasDisponiblesDTO {
    	/**
	 * 
	 */
	private String NUMERO;
	private String NOMBREPUESTO;
	private String JORNADALABORAL;
	private String NIVEL;
	private String RANGO;
	private String NOPLAZA;
	private String PERCEPCIONMENSUALES;
	private String ADSCRIPCION;
	private String UBICACIONTRABAJO;
	private String CAPACITACION;
	private String PUNTAJEESCALAFONARIOMINIMO;
	private String ESCOLARIRADMINIMA;
	
	private String escolaridadMinimaNat;
	private String EXPERIENCIAMINIMA;
	private String CONOCIMIENTOSMINIMOS;
	private String IDESTATUSPLAZA;
	private String idEscolaridadMinimaGen;
	private String idEscolaridadMinimaNat;
	private String RUTAGUIAESTUDIO;
        private String IDPUESTO;

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getNOMBREPUESTO() {
        return NOMBREPUESTO;
    }

    public void setNOMBREPUESTO(String NOMBREPUESTO) {
        this.NOMBREPUESTO = NOMBREPUESTO;
    }

    public String getJORNADALABORAL() {
        return JORNADALABORAL;
    }

    public void setJORNADALABORAL(String JORNADALABORAL) {
        this.JORNADALABORAL = JORNADALABORAL;
    }

    public String getNIVEL() {
        return NIVEL;
    }

    public void setNIVEL(String NIVEL) {
        this.NIVEL = NIVEL;
    }

    public String getRANGO() {
        return RANGO;
    }

    public void setRANGO(String RANGO) {
        this.RANGO = RANGO;
    }

    public String getNOPLAZA() {
        return NOPLAZA;
    }

    public void setNOPLAZA(String NOPLAZA) {
        this.NOPLAZA = NOPLAZA;
    }

    public String getPERCEPCIONMENSUALES() {
        return PERCEPCIONMENSUALES;
    }

    public void setPERCEPCIONMENSUALES(String PERCEPCIONMENSUALES) {
        this.PERCEPCIONMENSUALES = PERCEPCIONMENSUALES;
    }

    public String getADSCRIPCION() {
        return ADSCRIPCION;
    }

    public void setADSCRIPCION(String ADSCRIPCION) {
        this.ADSCRIPCION = ADSCRIPCION;
    }

    public String getUBICACIONTRABAJO() {
        return UBICACIONTRABAJO;
    }

    public void setUBICACIONTRABAJO(String UBICACIONTRABAJO) {
        this.UBICACIONTRABAJO = UBICACIONTRABAJO;
    }

    public String getCAPACITACION() {
        return CAPACITACION;
    }

    public void setCAPACITACION(String CAPACITACION) {
        this.CAPACITACION = CAPACITACION;
    }

    public String getPUNTAJEESCALAFONARIOMINIMO() {
        return PUNTAJEESCALAFONARIOMINIMO;
    }

    public void setPUNTAJEESCALAFONARIOMINIMO(String PUNTAJEESCALAFONARIOMINIMO) {
        this.PUNTAJEESCALAFONARIOMINIMO = PUNTAJEESCALAFONARIOMINIMO;
    }

    public String getESCOLARIRADMINIMA() {
        return ESCOLARIRADMINIMA;
    }

    public void setESCOLARIRADMINIMA(String ESCOLARIRADMINIMA) {
        this.ESCOLARIRADMINIMA = ESCOLARIRADMINIMA;
    }

    public String getEscolaridadMinimaNat() {
        return escolaridadMinimaNat;
    }

    public void setEscolaridadMinimaNat(String escolaridadMinimaNat) {
        this.escolaridadMinimaNat = escolaridadMinimaNat;
    }

    public String getEXPERIENCIAMINIMA() {
        return EXPERIENCIAMINIMA;
    }

    public void setEXPERIENCIAMINIMA(String EXPERIENCIAMINIMA) {
        this.EXPERIENCIAMINIMA = EXPERIENCIAMINIMA;
    }

    public String getCONOCIMIENTOSMINIMOS() {
        return CONOCIMIENTOSMINIMOS;
    }

    public void setCONOCIMIENTOSMINIMOS(String CONOCIMIENTOSMINIMOS) {
        this.CONOCIMIENTOSMINIMOS = CONOCIMIENTOSMINIMOS;
    }

    public String getIDESTATUSPLAZA() {
        return IDESTATUSPLAZA;
    }

    public void setIDESTATUSPLAZA(String IDESTATUSPLAZA) {
        this.IDESTATUSPLAZA = IDESTATUSPLAZA;
    }

    public String getIdEscolaridadMinimaGen() {
        return idEscolaridadMinimaGen;
    }

    public void setIdEscolaridadMinimaGen(String idEscolaridadMinimaGen) {
        this.idEscolaridadMinimaGen = idEscolaridadMinimaGen;
    }

    public String getIdEscolaridadMinimaNat() {
        return idEscolaridadMinimaNat;
    }

    public void setIdEscolaridadMinimaNat(String idEscolaridadMinimaNat) {
        this.idEscolaridadMinimaNat = idEscolaridadMinimaNat;
    }

    public String getRUTAGUIAESTUDIO() {
        return RUTAGUIAESTUDIO;
    }

    public void setRUTAGUIAESTUDIO(String RUTAGUIAESTUDIO) {
        this.RUTAGUIAESTUDIO = RUTAGUIAESTUDIO;
    }

    public String getIDPUESTO() {
        return IDPUESTO;
    }

    public void setIDPUESTO(String IDPUESTO) {
        this.IDPUESTO = IDPUESTO;
    }

        
}
