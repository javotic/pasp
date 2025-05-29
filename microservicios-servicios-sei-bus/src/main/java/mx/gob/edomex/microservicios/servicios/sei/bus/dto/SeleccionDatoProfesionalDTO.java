/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * Obejto de transferencia a datos para la seleccion de datos profesionales
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 16/03/2022.
 */
public class SeleccionDatoProfesionalDTO {
     //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @JsonProperty("ORDINAL")
    private int idOrdinal;
    @JsonProperty("SELECCIONADO")
    private boolean seleccionado;
    //</editor-fold>
    
     //<editor-fold desc="Accesores" defaultstate="collapsed">
    public int getIdOrdinal() {
        return idOrdinal;
    }

    public void setIdOrdinal(int idOrdinal) {
        this.idOrdinal = idOrdinal;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    //</editor-fold>
}
