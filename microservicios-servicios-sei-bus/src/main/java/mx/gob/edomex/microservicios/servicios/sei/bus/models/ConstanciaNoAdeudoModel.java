/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Objects;

/**
 *
 * Modelo de datos de Constancia de No Adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 20/12/2021.
 * @version 1.1, 22/12/2021, Javier Alvarado, Adicion de campo de folio.
 */
public class ConstanciaNoAdeudoModel {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String claveServidorPublico;
    private String nombreServidorPublico;
    private int idAdeudo;
    private String parrafoOficio;
    private String parrafoConstancia;
    private double montoAdeudo;
    private String percepciones;
    private String deducciones;
    private double totalPercepciones;
    private double totalDeducciones;
    private String unidadAdministrativa;
    private String ubicacion;
    private String categoria;
    private String dependencia;
    private String corresponde;
    private String folio;
    private String observaciones;
    //</editor-fold>
    
    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public ConstanciaNoAdeudoModel() {    
    }

    public ConstanciaNoAdeudoModel(String claveServidorPublico, 
            String nombreServidorPublico, int idAdeudo, String parrafoOficio, 
            String parrafoConstancia, double montoAdeudo, String percepciones, 
            String deducciones, double totalPercepciones, double totalDeducciones, 
            String unidadAdministrativa, String ubicacion, String categoria, 
            String dependencia, String corresponde, String folio, 
            String observaciones) {
        this.claveServidorPublico = claveServidorPublico;
        this.nombreServidorPublico = nombreServidorPublico;
        this.idAdeudo = idAdeudo;
        this.parrafoOficio = parrafoOficio;
        this.parrafoConstancia = parrafoConstancia;
        this.montoAdeudo = montoAdeudo;
        this.percepciones = percepciones;
        this.deducciones = deducciones;
        this.totalPercepciones = totalPercepciones;
        this.totalDeducciones = totalDeducciones;
        this.unidadAdministrativa = unidadAdministrativa;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.dependencia = dependencia;
        this.corresponde = corresponde;
        this.folio = folio;
        this.observaciones = observaciones;
    }
    //</editor-fold>
    
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override    
    public String toString() {
        return "ConstanciaNoAdeudoModel{" 
                + "claveServidorPublico=" + claveServidorPublico
                + ", nombreServidorPublico=" + nombreServidorPublico 
                + ", idAdeudo=" + idAdeudo
                + ", parrafoOficio=" + parrafoOficio
                + ", parrafoConstancia=" + parrafoConstancia 
                + ", montoAdeudo=" + montoAdeudo
                + ", percepciones=" + percepciones
                + ", deducciones=" + deducciones 
                + ", totalPercepciones=" + totalPercepciones
                + ", totalDeducciones=" + totalDeducciones
                + ", unidadAdministrativa=" + unidadAdministrativa 
                + ", ubicacion=" + ubicacion
                + ", categoria=" + categoria
                + ", dependencia=" + dependencia
                + ", corresponde=" + corresponde 
                + ", folio=" + folio 
                + ", observaciones=" + observaciones 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 67 * hash + Objects.hashCode(this.nombreServidorPublico);
        hash = 67 * hash + this.idAdeudo;
        hash = 67 * hash + Objects.hashCode(this.parrafoOficio);
        hash = 67 * hash + Objects.hashCode(this.parrafoConstancia);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.montoAdeudo) ^ (Double.doubleToLongBits(this.montoAdeudo) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.percepciones);
        hash = 67 * hash + Objects.hashCode(this.deducciones);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.totalPercepciones) ^ (Double.doubleToLongBits(this.totalPercepciones) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.totalDeducciones) ^ (Double.doubleToLongBits(this.totalDeducciones) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.unidadAdministrativa);
        hash = 67 * hash + Objects.hashCode(this.ubicacion);
        hash = 67 * hash + Objects.hashCode(this.categoria);
        hash = 67 * hash + Objects.hashCode(this.dependencia);
        hash = 67 * hash + Objects.hashCode(this.corresponde);
        hash = 67 * hash + Objects.hashCode(this.folio);
        hash = 67 * hash + Objects.hashCode(this.observaciones);
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
        final ConstanciaNoAdeudoModel other = (ConstanciaNoAdeudoModel) obj;
        if (this.idAdeudo != other.idAdeudo) {
            return false;
        }
        if (Double.doubleToLongBits(this.montoAdeudo) != Double.doubleToLongBits(other.montoAdeudo)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalPercepciones) != Double.doubleToLongBits(other.totalPercepciones)) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalDeducciones) != Double.doubleToLongBits(other.totalDeducciones)) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombreServidorPublico, other.nombreServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.parrafoOficio, other.parrafoOficio)) {
            return false;
        }
        if (!Objects.equals(this.parrafoConstancia, other.parrafoConstancia)) {
            return false;
        }
        if (!Objects.equals(this.percepciones, other.percepciones)) {
            return false;
        }
        if (!Objects.equals(this.deducciones, other.deducciones)) {
            return false;
        }
        if (!Objects.equals(this.unidadAdministrativa, other.unidadAdministrativa)) {
            return false;
        }
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.dependencia, other.dependencia)) {
            return false;
        }
        if (!Objects.equals(this.corresponde, other.corresponde)) {
            return false;
        }
        if (!Objects.equals(this.folio, other.folio)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
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

    public int getIdAdeudo() {
        return idAdeudo;
    }

    public void setIdAdeudo(int idAdeudo) {
        this.idAdeudo = idAdeudo;
    }

    public String getParrafoOficio() {
        return parrafoOficio;
    }

    public void setParrafoOficio(String parrafoOficio) {
        this.parrafoOficio = parrafoOficio;
    }

    public String getParrafoConstancia() {
        return parrafoConstancia;
    }

    public void setParrafoConstancia(String parrafoConstancia) {
        this.parrafoConstancia = parrafoConstancia;
    }

    public double getMontoAdeudo() {
        return montoAdeudo;
    }

    public void setMontoAdeudo(double montoAdeudo) {
        this.montoAdeudo = montoAdeudo;
    }

    public String getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(String percepciones) {
        this.percepciones = percepciones;
    }

    public String getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(String deducciones) {
        this.deducciones = deducciones;
    }

    public double getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(double totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public double getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(double totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCorresponde() {
        return corresponde;
    }

    public void setCorresponde(String corresponde) {
        this.corresponde = corresponde;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    //</editor-fold>
}
