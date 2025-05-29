/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version 1.0, desconocido.
 * @version 2.0, Javier Alvarado, Adicion de propiedad SELPROCESO, asi como
 * generacion de constructores.
 * @author smartinez
 */
public class DatosProfecionalesDTO {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String IDNIVELESTUDIOS;
    private String IDDATOPROFESIONAL;
    private String FECHAEMISIONCERTIFICADO;
    private String FECHAVIGENCIACERTIFICADO;
    private String NOMBRECERTIFICADO;
    private String IDTIPOCERTIFICADO;
    private String NOCERTIFICADO;
    private String IDEMISORCERTIFICADO;
    private String IDDATOPROFESIONAL2;
    private String NIVELESTUDIOS;
    private String DESCRIPCIONCERTIFICADO;
    private String NOMBREEMISORCERTIFICADO;
    private String DURACION;
    private String TIPODURACION;
    private String SEMAFORO;
    private boolean SELPROCESO;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public DatosProfecionalesDTO() {
    }

    public DatosProfecionalesDTO(String IDNIVELESTUDIOS, String IDDATOPROFESIONAL,
            String FECHAEMISIONCERTIFICADO, String FECHAVIGENCIACERTIFICADO,
            String NOMBRECERTIFICADO, String IDTIPOCERTIFICADO, String NOCERTIFICADO,
            String IDEMISORCERTIFICADO, String IDDATOPROFESIONAL2, String NIVELESTUDIOS,
            String DESCRIPCIONCERTIFICADO, String NOMBREEMISORCERTIFICADO, String DURACION,
            String TIPODURACION, String SEMAFORO, boolean SELPROCESO) {
        this.IDNIVELESTUDIOS = IDNIVELESTUDIOS;
        this.IDDATOPROFESIONAL = IDDATOPROFESIONAL;
        this.FECHAEMISIONCERTIFICADO = FECHAEMISIONCERTIFICADO;
        this.FECHAVIGENCIACERTIFICADO = FECHAVIGENCIACERTIFICADO;
        this.NOMBRECERTIFICADO = NOMBRECERTIFICADO;
        this.IDTIPOCERTIFICADO = IDTIPOCERTIFICADO;
        this.NOCERTIFICADO = NOCERTIFICADO;
        this.IDEMISORCERTIFICADO = IDEMISORCERTIFICADO;
        this.IDDATOPROFESIONAL2 = IDDATOPROFESIONAL2;
        this.NIVELESTUDIOS = NIVELESTUDIOS;
        this.DESCRIPCIONCERTIFICADO = DESCRIPCIONCERTIFICADO;
        this.NOMBREEMISORCERTIFICADO = NOMBREEMISORCERTIFICADO;
        this.DURACION = DURACION;
        this.TIPODURACION = TIPODURACION;
        this.SEMAFORO = SEMAFORO;
        this.SELPROCESO = SELPROCESO;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override()
    public String toString() {
        return "DatosProfecionalesDTO{"
                + "IDNIVELESTUDIOS=" + IDNIVELESTUDIOS
                + ", IDDATOPROFESIONAL=" + IDDATOPROFESIONAL
                + ", FECHAEMISIONCERTIFICADO=" + FECHAEMISIONCERTIFICADO
                + ", FECHAVIGENCIACERTIFICADO=" + FECHAVIGENCIACERTIFICADO
                + ", NOMBRECERTIFICADO=" + NOMBRECERTIFICADO
                + ", IDTIPOCERTIFICADO=" + IDTIPOCERTIFICADO
                + ", NOCERTIFICADO=" + NOCERTIFICADO
                + ", IDEMISORCERTIFICADO=" + IDEMISORCERTIFICADO
                + ", IDDATOPROFESIONAL2=" + IDDATOPROFESIONAL2
                + ", NIVELESTUDIOS=" + NIVELESTUDIOS
                + ", DESCRIPCIONCERTIFICADO=" + DESCRIPCIONCERTIFICADO
                + ", NOMBREEMISORCERTIFICADO=" + NOMBREEMISORCERTIFICADO
                + ", DURACION=" + DURACION
                + ", TIPODURACION=" + TIPODURACION
                + ", SEMAFORO=" + SEMAFORO
                + ", SELPROCESO=" + SELPROCESO
                + '}';
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    @JsonProperty(value = "IDNIVELESTUDIOS")
    public String getIDNIVELESTUDIOS() {
        return IDNIVELESTUDIOS;
    }

    public void setIDNIVELESTUDIOS(String IDNIVELESTUDIOS) {
        this.IDNIVELESTUDIOS = IDNIVELESTUDIOS;
    }

    @JsonProperty("IDDATOPROFESIONAL")
    public String getIDDATOPROFESIONAL() {
        return IDDATOPROFESIONAL;
    }

    public void setIDDATOPROFESIONAL(String IDDATOPROFESIONAL) {
        this.IDDATOPROFESIONAL = IDDATOPROFESIONAL;
    }

    @JsonProperty("FECHAEMISIONCERTIFICADO")
    public String getFECHAEMISIONCERTIFICADO() {
        return FECHAEMISIONCERTIFICADO;
    }

    public void setFECHAEMISIONCERTIFICADO(String FECHAEMISIONCERTIFICADO) {
        this.FECHAEMISIONCERTIFICADO = FECHAEMISIONCERTIFICADO;
    }

    @JsonProperty("FECHAVIGENCIACERTIFICADO")
    public String getFECHAVIGENCIACERTIFICADO() {
        return FECHAVIGENCIACERTIFICADO;
    }

    public void setFECHAVIGENCIACERTIFICADO(String FECHAVIGENCIACERTIFICADO) {
        this.FECHAVIGENCIACERTIFICADO = FECHAVIGENCIACERTIFICADO;
    }

    @JsonProperty("NOMBRECERTIFICADO")
    public String getNOMBRECERTIFICADO() {
        return NOMBRECERTIFICADO;
    }

    public void setNOMBRECERTIFICADO(String NOMBRECERTIFICADO) {
        this.NOMBRECERTIFICADO = NOMBRECERTIFICADO;
    }

    @JsonProperty("IDTIPOCERTIFICADO")
    public String getIDTIPOCERTIFICADO() {
        return IDTIPOCERTIFICADO;
    }

    public void setIDTIPOCERTIFICADO(String IDTIPOCERTIFICADO) {
        this.IDTIPOCERTIFICADO = IDTIPOCERTIFICADO;
    }

    @JsonProperty("NOCERTIFICADO")
    public String getNOCERTIFICADO() {
        return NOCERTIFICADO;
    }

    public void setNOCERTIFICADO(String NOCERTIFICADO) {
        this.NOCERTIFICADO = NOCERTIFICADO;
    }

    @JsonProperty("IDEMISORCERTIFICADO")
    public String getIDEMISORCERTIFICADO() {
        return IDEMISORCERTIFICADO;
    }

    public void setIDEMISORCERTIFICADO(String IDEMISORCERTIFICADO) {
        this.IDEMISORCERTIFICADO = IDEMISORCERTIFICADO;
    }

    @JsonProperty("IDDATOPROFESIONAL2")
    public String getIDDATOPROFESIONAL2() {
        return IDDATOPROFESIONAL2;
    }

    public void setIDDATOPROFESIONAL2(String IDDATOPROFESIONAL2) {
        this.IDDATOPROFESIONAL2 = IDDATOPROFESIONAL2;
    }

    @JsonProperty("NIVELESTUDIOS")
    public String getNIVELESTUDIOS() {
        return NIVELESTUDIOS;
    }

    public void setNIVELESTUDIOS(String NIVELESTUDIOS) {
        this.NIVELESTUDIOS = NIVELESTUDIOS;
    }

    @JsonProperty("DESCRIPCIONCERTIFICADO")
    public String getDESCRIPCIONCERTIFICADO() {
        return DESCRIPCIONCERTIFICADO;
    }

    public void setDESCRIPCIONCERTIFICADO(String DESCRIPCIONCERTIFICADO) {
        this.DESCRIPCIONCERTIFICADO = DESCRIPCIONCERTIFICADO;
    }

    @JsonProperty("NOMBREEMISORCERTIFICADO")
    public String getNOMBREEMISORCERTIFICADO() {
        return NOMBREEMISORCERTIFICADO;
    }

    public void setNOMBREEMISORCERTIFICADO(String NOMBREEMISORCERTIFICADO) {
        this.NOMBREEMISORCERTIFICADO = NOMBREEMISORCERTIFICADO;
    }

    @JsonProperty("DURACION")
    public String getDURACION() {
        return DURACION;
    }

    public void setDURACION(String DURACION) {
        this.DURACION = DURACION;
    }

    @JsonProperty("TIPODURACION")
    public String getTIPODURACION() {
        return TIPODURACION;
    }

    public void setTIPODURACION(String TIPODURACION) {
        this.TIPODURACION = TIPODURACION;
    }

    @JsonProperty("SEMAFORO")
    public String getSEMAFORO() {
        return SEMAFORO;
    }

    public void setSEMAFORO(String SEMAFORO) {
        this.SEMAFORO = SEMAFORO;
    }

    @JsonProperty("SELPROCESO")
    public boolean isSELPROCESO() {
        return SELPROCESO;
    }

    public void setSELPROCESO(boolean SELPROCESO) {
        this.SELPROCESO = SELPROCESO;
    }
    //</editor-fold>
}
