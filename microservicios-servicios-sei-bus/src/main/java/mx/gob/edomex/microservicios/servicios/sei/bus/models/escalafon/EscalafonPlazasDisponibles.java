package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidad de escalafon de plazas disponibles.
 *
 * @author desconocido
 * @version 1.0, desconocido
 * @version 1.1, 03/08/2022, Javier Alvarado, Adicion de campos de semaforo.
 */
@Entity
public class EscalafonPlazasDisponibles implements Serializable {

    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private static final long serialVersionUID = 1L;
    @Id
    @JsonProperty("NUMERO")
    private String numero;
    @JsonProperty("NOMBREPUESTO")
    private String nombrePuesto;
    @JsonProperty("JORNADALABORAL")
    private String jornadaLaboral;
    @JsonProperty("NIVEL")
    private String nivel;
    @JsonProperty("RANGO")
    private String rango;
    @JsonProperty("NOPLAZA")
    private String noPlaza;
    @JsonProperty("PERCEPCIONMENSUALES")
    private String percepcionesMensuales;
    @JsonProperty("ADSCRIPCION")
    private String adscripcion;
    @JsonProperty("UBICACIONTRABAJO")
    private String ubicacionTrabajo;
    @JsonProperty("CAPACITACION")
    private String idEstatusPlaza;
    @JsonProperty("PUNTAJEESCALAFONARIOMINIMO")
    private String puntajeEscalafonarioMinimo;
    @JsonProperty("ESCOLARIRADMINIMA")
    private String escolaridadMinimaGen;
    @JsonProperty("ESCOLARIRADMINIMAGEN")
    private String escolaridadMinimaNat;
    @JsonProperty("EXPERIENCIAMINIMA")
    private String experienciaMinima;
    @JsonProperty("CONOCIMIENTOSMINIMOS")
    private String conocimientos;
    @JsonProperty("IDESTATUSPLAZA")
    private String idPlaza;
    @JsonProperty("IDESCOLARIDADMINIMAGEN")
    private String idEscolaridadMinimaGen;
    @JsonProperty("IDESCOLARIDADMINIMAGNAT")
    private String idEscolaridadMinimaNat;
    @JsonProperty("RUTAGUIAESTUDIO")
    private String rutaGuiaEstudio;
    @JsonProperty("IDPUESTO")
    private String idPuesto;
    @JsonProperty("PUESTOINFERIOR")
    private String puestoInferior;
    @JsonProperty("PVISIBLE")
    private String pVisible;
    @JsonProperty("IDCOLORSEMAFORO")
    private int idColorSemaforo;
    @JsonProperty("COLORSEMAFORO")
    private String colorSemaforo;
    @JsonProperty("TOOLTIP")
    private String toolTip;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public EscalafonPlazasDisponibles() {    
    }

    public EscalafonPlazasDisponibles(String numero, String nombrePuesto, 
            String jornadaLaboral, String nivel, String rango, String noPlaza, 
            String percepcionesMensuales, String adscripcion, String ubicacionTrabajo, 
            String idEstatusPlaza, String puntajeEscalafonarioMinimo, 
            String escolaridadMinimaGen, String escolaridadMinimaNat, 
            String experienciaMinima, String conocimientos, String idPlaza, 
            String idEscolaridadMinimaGen, String idEscolaridadMinimaNat, 
            String rutaGuiaEstudio, String idPuesto, String puestoInferior, 
            String pVisible, int idColorSemaforo, String colorSemaforo,
            String toolTip) {
        this.numero = numero;
        this.nombrePuesto = nombrePuesto;
        this.jornadaLaboral = jornadaLaboral;
        this.nivel = nivel;
        this.rango = rango;
        this.noPlaza = noPlaza;
        this.percepcionesMensuales = percepcionesMensuales;
        this.adscripcion = adscripcion;
        this.ubicacionTrabajo = ubicacionTrabajo;
        this.idEstatusPlaza = idEstatusPlaza;
        
        this.puntajeEscalafonarioMinimo = puntajeEscalafonarioMinimo;
        this.escolaridadMinimaGen = escolaridadMinimaGen;
        this.escolaridadMinimaNat = escolaridadMinimaNat;
        this.experienciaMinima = experienciaMinima;
        this.conocimientos = conocimientos;
        this.idPlaza = idPlaza;
        this.idEscolaridadMinimaGen = idEscolaridadMinimaGen;
        this.idEscolaridadMinimaNat = idEscolaridadMinimaNat;
        this.rutaGuiaEstudio = rutaGuiaEstudio;
        this.idPuesto = idPuesto;
        
        this.puestoInferior = puestoInferior;
        this.pVisible = pVisible;
        this.idColorSemaforo = idColorSemaforo;
        this.colorSemaforo = colorSemaforo;
        this.toolTip = toolTip;
    }
    //</editor-fold>
    
    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getJornadaLaboral() {
        return jornadaLaboral;
    }

    public void setJornadaLaboral(String jornadaLaboral) {
        this.jornadaLaboral = jornadaLaboral;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getNoPlaza() {
        return noPlaza;
    }

    public void setNoPlaza(String noPlaza) {
        this.noPlaza = noPlaza;
    }

    public String getPercepcionesMensuales() {
        return percepcionesMensuales;
    }

    public void setPercepcionesMensuales(String percepcionesMensuales) {
        this.percepcionesMensuales = percepcionesMensuales;
    }

    public String getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(String adscripcion) {
        this.adscripcion = adscripcion;
    }

    public String getUbicacionTrabajo() {
        return ubicacionTrabajo;
    }

    public void setUbicacionTrabajo(String ubicacionTrabajo) {
        this.ubicacionTrabajo = ubicacionTrabajo;
    }

    public String getIdEstatusPlaza() {
        return idEstatusPlaza;
    }

    public void setIdEstatusPlaza(String idEstatusPlaza) {
        this.idEstatusPlaza = idEstatusPlaza;
    }

    public String getPuntajeEscalafonarioMinimo() {
        return puntajeEscalafonarioMinimo;
    }

    public void setPuntajeEscalafonarioMinimo(String puntajeEscalafonarioMinimo) {
        this.puntajeEscalafonarioMinimo = puntajeEscalafonarioMinimo;
    }

    public String getEscolaridadMinimaGen() {
        return escolaridadMinimaGen;
    }

    public void setEscolaridadMinimaGen(String escolaridadMinimaGen) {
        this.escolaridadMinimaGen = escolaridadMinimaGen;
    }

    public String getEscolaridadMinimaNat() {
        return escolaridadMinimaNat;
    }

    public void setEscolaridadMinimaNat(String escolaridadMinimaNat) {
        this.escolaridadMinimaNat = escolaridadMinimaNat;
    }

    public String getExperienciaMinima() {
        return experienciaMinima;
    }

    public void setExperienciaMinima(String experienciaMinima) {
        this.experienciaMinima = experienciaMinima;
    }

    public String getConocimientos() {
        return conocimientos;
    }

    public void setConocimientos(String conocimientos) {
        this.conocimientos = conocimientos;
    }

    public String getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
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

    public String getRutaGuiaEstudio() {
        return rutaGuiaEstudio;
    }

    public void setRutaGuiaEstudio(String rutaGuiaEstudio) {
        this.rutaGuiaEstudio = rutaGuiaEstudio;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getPuestoInferior() {
        return puestoInferior;
    }

    public void setPuestoInferior(String puestoInferior) {
        this.puestoInferior = puestoInferior;
    }

    public String getpVisible() {
        return pVisible;
    }

    public void setpVisible(String pVisible) {
        this.pVisible = pVisible;
    }

    public int getIdColorSemaforo() {
        return idColorSemaforo;
    }

    public void setIdColorSemaforo(int idColorSemaforo) {
        this.idColorSemaforo = idColorSemaforo;
    }

    public String getColorSemaforo() {
        return colorSemaforo;
    }

    public void setColorSemaforo(String colorSemaforo) {
        this.colorSemaforo = colorSemaforo;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }
    //</editor-fold>
}
