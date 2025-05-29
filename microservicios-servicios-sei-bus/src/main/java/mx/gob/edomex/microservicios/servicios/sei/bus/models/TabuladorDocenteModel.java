package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

/**
 * Modelo de tabulador docente.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 18/07/2022.
 */
public class TabuladorDocenteModel implements Serializable{

    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private static final long serialVersionUID = 1L;
    private double salarioMensual;
    private double isr;
    private double salarioMensualDespuesImpuestos;
    private double porcentajeIncremento;
    private double sueldoBase;
    private double incrementoPorcentual;
    private double sueldoBaseFinal;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public TabuladorDocenteModel() {
    }

    public TabuladorDocenteModel(double salarioMensual, double isr, double salarioMensualDespuesImpuestos,
            double porcentajeIncremento, double sueldoBase, double incrementoPorcentual,
            double sueldoBaseFinal) {
        this.salarioMensual = salarioMensual;
        this.isr = isr;
        this.salarioMensualDespuesImpuestos = salarioMensualDespuesImpuestos;
        this.porcentajeIncremento = porcentajeIncremento;
        this.sueldoBase = sueldoBase;
        this.incrementoPorcentual = incrementoPorcentual;
        this.sueldoBaseFinal = sueldoBaseFinal;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "TabuladorDocenteModel{"
                + "salarioMensual=" + salarioMensual
                + ", isr=" + isr
                + ", salarioMensualDespuesImpuestos=" + salarioMensualDespuesImpuestos
                + ", porcentajeIncremento=" + porcentajeIncremento
                + ", sueldoBase=" + sueldoBase
                + ", incrementoPorcentual=" + incrementoPorcentual
                + ", sueldoBaseFinal=" + sueldoBaseFinal
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.salarioMensual) ^ (Double.doubleToLongBits(this.salarioMensual) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.isr) ^ (Double.doubleToLongBits(this.isr) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.salarioMensualDespuesImpuestos) ^ (Double.doubleToLongBits(this.salarioMensualDespuesImpuestos) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.porcentajeIncremento) ^ (Double.doubleToLongBits(this.porcentajeIncremento) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.sueldoBase) ^ (Double.doubleToLongBits(this.sueldoBase) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.incrementoPorcentual) ^ (Double.doubleToLongBits(this.incrementoPorcentual) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.sueldoBaseFinal) ^ (Double.doubleToLongBits(this.sueldoBaseFinal) >>> 32));
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
        final TabuladorDocenteModel other = (TabuladorDocenteModel) obj;
        if (Double.doubleToLongBits(this.salarioMensual) != Double.doubleToLongBits(other.salarioMensual)) {
            return false;
        }
        if (Double.doubleToLongBits(this.isr) != Double.doubleToLongBits(other.isr)) {
            return false;
        }
        if (Double.doubleToLongBits(this.salarioMensualDespuesImpuestos) != Double.doubleToLongBits(other.salarioMensualDespuesImpuestos)) {
            return false;
        }
        if (Double.doubleToLongBits(this.porcentajeIncremento) != Double.doubleToLongBits(other.porcentajeIncremento)) {
            return false;
        }
        if (Double.doubleToLongBits(this.sueldoBase) != Double.doubleToLongBits(other.sueldoBase)) {
            return false;
        }
        if (Double.doubleToLongBits(this.incrementoPorcentual) != Double.doubleToLongBits(other.incrementoPorcentual)) {
            return false;
        }
        if (Double.doubleToLongBits(this.sueldoBaseFinal) != Double.doubleToLongBits(other.sueldoBaseFinal)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Acccesores" defaultstate="collapsed">
    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    public double getIsr() {
        return isr;
    }

    public void setIsr(double isr) {
        this.isr = isr;
    }

    public double getSalarioMensualDespuesImpuestos() {
        return salarioMensualDespuesImpuestos;
    }

    public void setSalarioMensualDespuesImpuestos(double salarioMensualDespuesImpuestos) {
        this.salarioMensualDespuesImpuestos = salarioMensualDespuesImpuestos;
    }

    public double getPorcentajeIncremento() {
        return porcentajeIncremento;
    }

    public void setPorcentajeIncremento(double porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public double getIncrementoPorcentual() {
        return incrementoPorcentual;
    }

    public void setIncrementoPorcentual(double incrementoPorcentual) {
        this.incrementoPorcentual = incrementoPorcentual;
    }

    public double getSueldoBaseFinal() {
        return sueldoBaseFinal;
    }

    public void setSueldoBaseFinal(double sueldoBaseFinal) {
        this.sueldoBaseFinal = sueldoBaseFinal;
    }
    //</editor-fold>
}
