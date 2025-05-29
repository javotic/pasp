package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

/**
 * Modelo de tabulador burocrata.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 18/07/2022.
 */
public class TabuladorBurocrataModel implements Serializable{
    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private static final long serialVersionUID = 1L;
    private double sueldoBase;
    private double gratificacion;
    private double fortalecimientoSalario;
    private double despensa;
    private double totalBruto;
    private double isr;
    private double issemym;
    private double totalNeto;
    private String anio;
    //</editor-fold>
    
    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public TabuladorBurocrataModel() {    
    }

    public TabuladorBurocrataModel(double sueldoBase, double gratificacion, 
            double fortalecimientoSalario, double despensa, double totalBruto, 
            double isr, double issemym, double totalNeto, String anio) {
        this.sueldoBase = sueldoBase;
        this.gratificacion = gratificacion;
        this.fortalecimientoSalario = fortalecimientoSalario;
        this.despensa = despensa;
        this.totalBruto = totalBruto;
        this.isr = isr;
        this.issemym = issemym;
        this.totalNeto = totalNeto;
        this.anio = anio;
    }
    //</editor-fold>
    
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override    
    public String toString() {
        return "TabuladorBurocrataModel{" 
                + "sueldoBase=" + sueldoBase
                + ", gratificacion=" + gratificacion 
                + ", fortalecimientoSalario=" + fortalecimientoSalario 
                + ", despensa=" + despensa 
                + ", totalBruto=" + totalBruto
                + ", isr=" + isr 
                + ", issemym=" + issemym 
                + ", totalNeto=" + totalNeto 
                + ", anio=" + anio 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.sueldoBase) ^ (Double.doubleToLongBits(this.sueldoBase) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.gratificacion) ^ (Double.doubleToLongBits(this.gratificacion) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.fortalecimientoSalario) ^ (Double.doubleToLongBits(this.fortalecimientoSalario) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.despensa) ^ (Double.doubleToLongBits(this.despensa) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.totalBruto) ^ (Double.doubleToLongBits(this.totalBruto) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.isr) ^ (Double.doubleToLongBits(this.isr) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.issemym) ^ (Double.doubleToLongBits(this.issemym) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.totalNeto) ^ (Double.doubleToLongBits(this.totalNeto) >>> 32));
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
        final TabuladorBurocrataModel other = (TabuladorBurocrataModel) obj;
        if (Double.doubleToLongBits(this.sueldoBase) != Double.doubleToLongBits(other.sueldoBase)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gratificacion) != Double.doubleToLongBits(other.gratificacion)) {
            return false;
        }
        if (Double.doubleToLongBits(this.fortalecimientoSalario) != Double.doubleToLongBits(other.fortalecimientoSalario)) {
            return false;
        }
        if (Double.doubleToLongBits(this.despensa) != Double.doubleToLongBits(other.despensa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalBruto) != Double.doubleToLongBits(other.totalBruto)) {
            return false;
        }
        if (Double.doubleToLongBits(this.isr) != Double.doubleToLongBits(other.isr)) {
            return false;
        }
        if (Double.doubleToLongBits(this.issemym) != Double.doubleToLongBits(other.issemym)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalNeto) != Double.doubleToLongBits(other.totalNeto)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold desc="Acccesores" defaultstate="collapsed">
    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public double getGratificacion() {
        return gratificacion;
    }

    public void setGratificacion(double gratificacion) {
        this.gratificacion = gratificacion;
    }

    public double getFortalecimientoSalario() {
        return fortalecimientoSalario;
    }

    public void setFortalecimientoSalario(double fortalecimientoSalario) {
        this.fortalecimientoSalario = fortalecimientoSalario;
    }

    public double getDespensa() {
        return despensa;
    }

    public void setDespensa(double despensa) {
        this.despensa = despensa;
    }

    public double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public double getIsr() {
        return isr;
    }

    public void setIsr(double isr) {
        this.isr = isr;
    }

    public double getIssemym() {
        return issemym;
    }

    public void setIssemym(double issemym) {
        this.issemym = issemym;
    }

    public double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
    //</editor-fold>
}
