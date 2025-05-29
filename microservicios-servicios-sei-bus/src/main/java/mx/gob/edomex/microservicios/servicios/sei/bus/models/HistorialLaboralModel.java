package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * Modelo de datos de historial laboral.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 08/11/2021.
 */
public class HistorialLaboralModel {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String claveServidorPublico;
    private String nombreServidorPublico;
    private Date fechaInicio;
    private Date fechaTermino;
    private String movimiento;
    private String puesto;
    private String secretaria;
    private double sueldo;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public HistorialLaboralModel() {
    }

    public HistorialLaboralModel(String claveServidorPublico, String nombreServidorPublico,
            Date fechaInicio, Date fechaTermino, String movimiento,
            String puesto, String secretaria) {
        this.claveServidorPublico = claveServidorPublico;
        this.nombreServidorPublico = nombreServidorPublico;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.movimiento = movimiento;
        this.puesto = puesto;
        this.secretaria = secretaria;
    }
    
    public HistorialLaboralModel(String claveServidorPublico, String nombreServidorPublico,
            Date fechaInicio, Date fechaTermino, String puesto, double sueldo) {
        this.claveServidorPublico = claveServidorPublico;
        this.nombreServidorPublico = nombreServidorPublico;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.puesto = puesto;
        this.sueldo = sueldo;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "HistorialLaboralModel{"
                + "claveServidorPublico=" + claveServidorPublico
                + ", nombreServidorPublico=" + nombreServidorPublico
                + ", fechaInicio=" + fechaInicio
                + ", fechaTermino=" + fechaTermino
                + ", movimiento=" + movimiento
                + ", puesto=" + puesto
                + ", secretaria=" + secretaria
                + ", sueldo=" + sueldo
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 97 * hash + Objects.hashCode(this.nombreServidorPublico);
        hash = 97 * hash + Objects.hashCode(this.fechaInicio);
        hash = 97 * hash + Objects.hashCode(this.fechaTermino);
        hash = 97 * hash + Objects.hashCode(this.movimiento);
        hash = 97 * hash + Objects.hashCode(this.puesto);
        hash = 97 * hash + Objects.hashCode(this.secretaria);
        hash = 97 * hash + Objects.hashCode(this.sueldo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistorialLaboralModel other = (HistorialLaboralModel) obj;
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombreServidorPublico, other.nombreServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.movimiento, other.movimiento)) {
            return false;
        }
        if (!Objects.equals(this.puesto, other.puesto)) {
            return false;
        }
        if (!Objects.equals(this.secretaria, other.secretaria)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaTermino, other.fechaTermino)) {
            return false;
        }
        if (!Objects.equals(this.sueldo, other.sueldo)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getClaveServidorPublico() {
        return claveServidorPublico;
    }

    public void setClaveServidorPublico(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    public String getNombreServidorPublico() {
        return nombreServidorPublico;
    }

    public void setNombreServidorPublico(String nombreServidorPublico) {
        this.nombreServidorPublico = nombreServidorPublico;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
//</editor-fold>
}
