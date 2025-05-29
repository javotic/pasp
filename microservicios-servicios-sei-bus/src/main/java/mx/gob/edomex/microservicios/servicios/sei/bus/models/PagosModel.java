/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "IDSERVIDORPUBLICO", "IDPAGO", "ESTATUSPAGO", "TIPOPAGO", "FECHAPAGO", "IMPORTEPAGOGRAVADO", "IMPORTEPAGOEXENTO" })
public class PagosModel {
    
    private String IDSERVIDORPUBLICO;
    private String IDPAGO;
    private String ESTATUSPAGO;
    private String TIPOPAGO;
    private String FECHAPAGO;
    private String IMPORTEPAGOGRAVADO;
    private String IMPORTEPAGOEXENTO; 
    
    @JsonProperty("IDSERVIDORPUBLICO")
    public String getIDSERVIDORPUBLICO() {
        return IDSERVIDORPUBLICO;
    }

    public void setIDSERVIDORPUBLICO(String IDSERVIDORPUBLICO) {
        this.IDSERVIDORPUBLICO = IDSERVIDORPUBLICO;
    }

    @JsonProperty("IDPAGO")
    public String getIDPAGO() {
        return IDPAGO;
    }

    public void setIDPAGO(String IDPAGO) {
        this.IDPAGO = IDPAGO;
    }

    @JsonProperty("ESTATUSPAGO")
    public String getESTATUSPAGO() {
        return ESTATUSPAGO;
    }

    public void setESTATUSPAGO(String ESTATUSPAGO) {
        this.ESTATUSPAGO = ESTATUSPAGO;
    }

    @JsonProperty("TIPOPAGO")
    public String getTIPOPAGO() {
        return TIPOPAGO;
    }

    public void setTIPOPAGO(String TIPOPAGO) {
        this.TIPOPAGO = TIPOPAGO;
    }

    @JsonProperty("FECHAPAGO")
    public String getFECHAPAGO() {
        return FECHAPAGO;
    }

    public void setFECHAPAGO(String FECHAPAGO) {
        this.FECHAPAGO = FECHAPAGO;
    }

    @JsonProperty("IMPORTEPAGOGRAVADO")
    public String getIMPORTEPAGOGRAVADO() {
        return IMPORTEPAGOGRAVADO;
    }

    public void setIMPORTEPAGOGRAVADO(String IMPORTEPAGOGRAVADO) {
        this.IMPORTEPAGOGRAVADO = IMPORTEPAGOGRAVADO;
    }

    @JsonProperty("IMPORTEPAGOEXENTO")
    public String getIMPORTEPAGOEXENTO() {
        return IMPORTEPAGOEXENTO;
    }

    public void setIMPORTEPAGOEXENTO(String IMPORTEPAGOEXENTO) {
        this.IMPORTEPAGOEXENTO = IMPORTEPAGOEXENTO;
    }
    
}
