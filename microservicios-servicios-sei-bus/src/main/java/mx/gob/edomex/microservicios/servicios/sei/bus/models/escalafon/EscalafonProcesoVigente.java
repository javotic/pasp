package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

public class EscalafonProcesoVigente implements Serializable {

    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private static final long serialVersionUID = 1L;
    private String idProcesoVigente;
    private String nombreProcesoEvaluacion;
    private String fechaInicioProcesoGeneral;
    private String fechaFinProcesoGeneral;
    private String descripcionProcesoVigente;
    private String fechaInicioAdmin;
    private String fechaFinAdmin;
    private int tipoProceso;
    //</editor-fold>
    
    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public EscalafonProcesoVigente() {    
    }

    public EscalafonProcesoVigente(String idProcesoVigente, String nombreProcesoEvaluacion, 
            String fechaInicioProcesoGeneral, String fechaFinProcesoGeneral, 
            String descripcionProcesoVigente, String fechaInicioAdmin, 
            String fechaFinAdmin, int tipoProceso) {
        this.idProcesoVigente = idProcesoVigente;
        this.nombreProcesoEvaluacion = nombreProcesoEvaluacion;
        this.fechaInicioProcesoGeneral = fechaInicioProcesoGeneral;
        this.fechaFinProcesoGeneral = fechaFinProcesoGeneral;
        this.descripcionProcesoVigente = descripcionProcesoVigente;
        this.fechaInicioAdmin = fechaInicioAdmin;
        this.fechaFinAdmin = fechaFinAdmin;
        this.tipoProceso = tipoProceso;
    }
    //</editor-fold>
    
    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getIdProcesoVigente() {
        return idProcesoVigente;
    }

    public void setIdProcesoVigente(String idProcesoVigente) {
        this.idProcesoVigente = idProcesoVigente;
    }

    public String getNombreProcesoEvaluacion() {
        return nombreProcesoEvaluacion;
    }

    public void setNombreProcesoEvaluacion(String nombreProcesoEvaluacion) {
        this.nombreProcesoEvaluacion = nombreProcesoEvaluacion;
    }

    public String getFechaInicioProcesoGeneral() {
        return fechaInicioProcesoGeneral;
    }

    public void setFechaInicioProcesoGeneral(String fechaInicioProcesoGeneral) {
        this.fechaInicioProcesoGeneral = fechaInicioProcesoGeneral;
    }

    public String getFechaFinProcesoGeneral() {
        return fechaFinProcesoGeneral;
    }

    public void setFechaFinProcesoGeneral(String fechaFinProcesoGeneral) {
        this.fechaFinProcesoGeneral = fechaFinProcesoGeneral;
    }

    public String getDescripcionProcesoVigente() {
        return descripcionProcesoVigente;
    }

    public void setDescripcionProcesoVigente(String descripcionProcesoVigente) {
        this.descripcionProcesoVigente = descripcionProcesoVigente;
    }

    public String getFechaInicioAdmin() {
        return fechaInicioAdmin;
    }

    public void setFechaInicioAdmin(String fechaInicioAdmin) {
        this.fechaInicioAdmin = fechaInicioAdmin;
    }

    public String getFechaFinAdmin() {
        return fechaFinAdmin;
    }

    public void setFechaFinAdmin(String fechaFinAdmin) {
        this.fechaFinAdmin = fechaFinAdmin;
    }

    public int getTipoProceso() {
        return tipoProceso;
    }

    public void setTipoProceso(int tipoProceso) {
        this.tipoProceso = tipoProceso;
    }
    //</editor-fold>
}
