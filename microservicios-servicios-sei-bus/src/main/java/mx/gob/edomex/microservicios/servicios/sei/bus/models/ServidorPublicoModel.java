package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Objects;

/**
 *
 * Modelo de datos globales de servidor publico.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 08/11/2021.
 */
public class ServidorPublicoModel {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    //Datos generales
    private int idOrdinal;
    private String claveServidorPublico;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String genero;
    private String curp;
    private String rfc;
    private String fechaNacimiento;
    private String telefono;
    private String mail;
    private int idSexo;
    //Direccion personal
    private int idTipoDireccionPersonal;
    private String tipoDireccionPersonal;
    private String callePersonal;
    private String idEstadoPersonal;
    private String estadoPersonal;
    private String idMunicipioPersonal;
    private String municipioPersonal;
    private String idColoniaPersonal;
    private String coloniaPersonal;
    private String numeroExteriorPersonal;
    private String numeroInteriorPersonal;
    private String codigoPostalPersonal;
    //Direccion fiscal
    private int idTipoDireccionFiscal;
    private String tipoDireccionFiscal;
    private String calleFiscal;
    private String idEstadoFiscal;
    private String estadoFiscal;
    private String idMunicipioFiscal;
    private String municipioFiscal;
    private String idColoniaFiscal;
    private String coloniaFiscal;
    private String numeroExteriorFiscal;
    private String numeroInteriorFiscal;
    private String codigoPostalFiscal;
    //Datos laborales
    private String nombrePlaza;
    private String idNivelEstudios;
    private String idEstadoCivil;
    private String idPlaza;
    private String issemym;
    private int orden;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public ServidorPublicoModel() {
    }

    public ServidorPublicoModel(int idOrdinal, String claveServidorPublico,
            String nombre, String primerApellido, String segundoApellido,
            int idSexo, String genero, String curp, String rfc, String fechaNacimiento,
            String telefono, String mail, int idTipoDireccionPersonal,
            String tipoDireccionPersonal, String callePersonal,
            String idEstadoPersonal, String estadoPersonal,
            String idMunicipioPersonal, String municipioPersonal,
            String idColoniaPersonal, String coloniaPersonal,
            String numeroExteriorPersonal, String numeroInteriorPersonal,
            String codigoPostalPersonal, String nombrePlaza, String idNivelEstudios,
            String idEstadoCivil, String idPlaza, String issemym, int orden) {
        this.idOrdinal = idOrdinal;
        this.claveServidorPublico = claveServidorPublico;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.curp = curp;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.mail = mail;
        this.idSexo = idSexo;
        this.idTipoDireccionPersonal = idTipoDireccionPersonal;
        this.tipoDireccionPersonal = tipoDireccionPersonal;
        this.callePersonal = callePersonal;
        this.idEstadoPersonal = idEstadoPersonal;
        this.estadoPersonal = estadoPersonal;
        this.idMunicipioPersonal = idMunicipioPersonal;
        this.municipioPersonal = municipioPersonal;
        this.idColoniaPersonal = idColoniaPersonal;
        this.coloniaPersonal = coloniaPersonal;
        this.numeroExteriorPersonal = numeroExteriorPersonal;
        this.numeroInteriorPersonal = numeroInteriorPersonal;
        this.codigoPostalPersonal = codigoPostalPersonal;
        this.nombrePlaza = nombrePlaza;
        this.idNivelEstudios = idNivelEstudios;
        this.idEstadoCivil = idEstadoCivil;
        this.idPlaza = idPlaza;
        this.issemym = issemym;
        this.orden = orden;
    }

    public ServidorPublicoModel(int idOrdinal, String claveServidorPublico,
            String nombre, String primerApellido, String segundoApellido,
            String genero, String curp, String rfc, String fechaNacimiento,
            String telefono, String mail, int idSexo, int tipoIdDireccionPersonal,
            String tipoDireccionPersonal, String callePersonal, String idEstadoPersonal,
            String estadoPersonal, String idMunicipioPersonal, String municipioPersonal,
            String idColoniaPersonal, String coloniaPersonal, String numeroExteriorPersonal,
            String numeroInteriorPersonal, String codigoPostalPersonal,
            int idTipoDireccionFiscal, String tipoDireccionFiscal,
            String calleFiscal, String idEstadoFiscal, String estadoFiscal,
            String idMunicipioFiscal, String municipioFiscal, String idColoniaFiscal,
            String coloniaFiscal, String numeroExteriorFiscal, String numeroInteriorFiscal,
            String codigoPostalFiscal, String nombrePlaza, String idNivelEstudios,
            String idEstadoCivil, String idPlaza, String issemym, int orden) {
        this.idOrdinal = idOrdinal;
        this.claveServidorPublico = claveServidorPublico;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.curp = curp;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.mail = mail;
        this.idSexo = idSexo;
        this.idTipoDireccionPersonal = tipoIdDireccionPersonal;
        this.tipoDireccionPersonal = tipoDireccionPersonal;
        this.callePersonal = callePersonal;
        this.idEstadoPersonal = idEstadoPersonal;
        this.estadoPersonal = estadoPersonal;
        this.idMunicipioPersonal = idMunicipioPersonal;
        this.municipioPersonal = municipioPersonal;
        this.idColoniaPersonal = idColoniaPersonal;
        this.coloniaPersonal = coloniaPersonal;
        this.numeroExteriorPersonal = numeroExteriorPersonal;
        this.numeroInteriorPersonal = numeroInteriorPersonal;
        this.codigoPostalPersonal = codigoPostalPersonal;
        this.idTipoDireccionFiscal = idTipoDireccionFiscal;
        this.tipoDireccionFiscal = tipoDireccionFiscal;
        this.calleFiscal = calleFiscal;
        this.idEstadoFiscal = idEstadoFiscal;
        this.estadoFiscal = estadoFiscal;
        this.idMunicipioFiscal = idMunicipioFiscal;
        this.municipioFiscal = municipioFiscal;
        this.idColoniaFiscal = idColoniaFiscal;
        this.coloniaFiscal = coloniaFiscal;
        this.numeroExteriorFiscal = numeroExteriorFiscal;
        this.numeroInteriorFiscal = numeroInteriorFiscal;
        this.codigoPostalFiscal = codigoPostalFiscal;
        this.nombrePlaza = nombrePlaza;
        this.idNivelEstudios = idNivelEstudios;
        this.idEstadoCivil = idEstadoCivil;
        this.idPlaza = idPlaza;
        this.issemym = issemym;
        this.orden = orden;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "ServidorPublicoModel{"
                + "idOrdinal=" + idOrdinal
                + ", claveServidorPublico=" + claveServidorPublico
                + ", nombre=" + nombre
                + ", primerApellido=" + primerApellido
                + ", segundoApellido=" + segundoApellido
                + ", genero=" + genero
                + ", curp=" + curp
                + ", rfc=" + rfc
                + ", fechaNacimiento=" + fechaNacimiento
                + ", telefono=" + telefono
                + ", mail=" + mail
                + ", idSexo=" + idSexo
                + ", tipoIdDireccionPersonal=" + idTipoDireccionPersonal
                + ", tipoDireccionPersonal=" + tipoDireccionPersonal
                + ", callePersonal=" + callePersonal
                + ", idEstadoPersonal=" + idEstadoPersonal
                + ", estadoPersonal=" + estadoPersonal
                + ", idMunicipioPersonal=" + idMunicipioPersonal
                + ", municipioPersonal=" + municipioPersonal
                + ", idColoniaPersonal=" + idColoniaPersonal
                + ", coloniaPersonal=" + coloniaPersonal
                + ", numeroExteriorPersonal=" + numeroExteriorPersonal
                + ", numeroInteriorPersonal=" + numeroInteriorPersonal
                + ", codigoPostalPersonal=" + codigoPostalPersonal
                + ", tipoIdDireccionFiscal=" + idTipoDireccionFiscal
                + ", tipoDireccionFiscal=" + tipoDireccionFiscal
                + ", calleFiscal=" + calleFiscal
                + ", idEstadoFiscal=" + idEstadoFiscal
                + ", estadoFiscal=" + estadoFiscal
                + ", idMunicipioFiscal=" + idMunicipioFiscal
                + ", municipioFiscal=" + municipioFiscal
                + ", idColoniaFiscal=" + idColoniaFiscal
                + ", coloniaFiscal=" + coloniaFiscal
                + ", numeroExteriorFiscal=" + numeroExteriorFiscal
                + ", numeroInteriorFiscal=" + numeroInteriorFiscal
                + ", codigoPostalFiscal=" + codigoPostalFiscal
                + ", nombrePlaza=" + nombrePlaza
                + ", idNivelEstudios=" + idNivelEstudios
                + ", idEstadoCivil=" + idEstadoCivil
                + ", idPlaza=" + idPlaza
                + ", issemym=" + issemym
                + ", orden=" + orden
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idOrdinal;
        hash = 37 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.primerApellido);
        hash = 37 * hash + Objects.hashCode(this.segundoApellido);
        hash = 37 * hash + Objects.hashCode(this.genero);
        hash = 37 * hash + Objects.hashCode(this.curp);
        hash = 37 * hash + Objects.hashCode(this.rfc);
        hash = 37 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 37 * hash + Objects.hashCode(this.telefono);
        hash = 37 * hash + Objects.hashCode(this.mail);
        hash = 37 * hash + this.idSexo;
        hash = 37 * hash + this.idTipoDireccionPersonal;
        hash = 37 * hash + Objects.hashCode(this.tipoDireccionPersonal);
        hash = 37 * hash + Objects.hashCode(this.callePersonal);
        hash = 37 * hash + Objects.hashCode(this.idEstadoPersonal);
        hash = 37 * hash + Objects.hashCode(this.estadoPersonal);
        hash = 37 * hash + Objects.hashCode(this.idMunicipioPersonal);
        hash = 37 * hash + Objects.hashCode(this.municipioPersonal);
        hash = 37 * hash + Objects.hashCode(this.idColoniaPersonal);
        hash = 37 * hash + Objects.hashCode(this.coloniaPersonal);
        hash = 37 * hash + Objects.hashCode(this.numeroExteriorPersonal);
        hash = 37 * hash + Objects.hashCode(this.numeroInteriorPersonal);
        hash = 37 * hash + Objects.hashCode(this.codigoPostalPersonal);
        hash = 37 * hash + this.idTipoDireccionFiscal;
        hash = 37 * hash + Objects.hashCode(this.tipoDireccionFiscal);
        hash = 37 * hash + Objects.hashCode(this.calleFiscal);
        hash = 37 * hash + Objects.hashCode(this.idEstadoFiscal);
        hash = 37 * hash + Objects.hashCode(this.estadoFiscal);
        hash = 37 * hash + Objects.hashCode(this.idMunicipioFiscal);
        hash = 37 * hash + Objects.hashCode(this.municipioFiscal);
        hash = 37 * hash + Objects.hashCode(this.idColoniaFiscal);
        hash = 37 * hash + Objects.hashCode(this.coloniaFiscal);
        hash = 37 * hash + Objects.hashCode(this.numeroExteriorFiscal);
        hash = 37 * hash + Objects.hashCode(this.numeroInteriorFiscal);
        hash = 37 * hash + Objects.hashCode(this.codigoPostalFiscal);
        hash = 37 * hash + Objects.hashCode(this.nombrePlaza);
        hash = 37 * hash + Objects.hashCode(this.idNivelEstudios);
        hash = 37 * hash + Objects.hashCode(this.idEstadoCivil);
        hash = 37 * hash + Objects.hashCode(this.idPlaza);
        hash = 37 * hash + Objects.hashCode(this.issemym);
        hash = 37 * hash + this.orden;
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
        final ServidorPublicoModel other = (ServidorPublicoModel) obj;
        if (this.idOrdinal != other.idOrdinal) {
            return false;
        }
        if (this.idSexo != other.idSexo) {
            return false;
        }
        if (this.idTipoDireccionPersonal != other.idTipoDireccionPersonal) {
            return false;
        }
        if (this.idTipoDireccionFiscal != other.idTipoDireccionFiscal) {
            return false;
        }
        if (this.orden != other.orden) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.primerApellido, other.primerApellido)) {
            return false;
        }
        if (!Objects.equals(this.segundoApellido, other.segundoApellido)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.curp, other.curp)) {
            return false;
        }
        if (!Objects.equals(this.rfc, other.rfc)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.tipoDireccionPersonal, other.tipoDireccionPersonal)) {
            return false;
        }
        if (!Objects.equals(this.callePersonal, other.callePersonal)) {
            return false;
        }
        if (!Objects.equals(this.idEstadoPersonal, other.idEstadoPersonal)) {
            return false;
        }
        if (!Objects.equals(this.estadoPersonal, other.estadoPersonal)) {
            return false;
        }
        if (!Objects.equals(this.idMunicipioPersonal, other.idMunicipioPersonal)) {
            return false;
        }
        if (!Objects.equals(this.municipioPersonal, other.municipioPersonal)) {
            return false;
        }
        if (!Objects.equals(this.idColoniaPersonal, other.idColoniaPersonal)) {
            return false;
        }
        if (!Objects.equals(this.coloniaPersonal, other.coloniaPersonal)) {
            return false;
        }
        if (!Objects.equals(this.numeroExteriorPersonal, other.numeroExteriorPersonal)) {
            return false;
        }
        if (!Objects.equals(this.numeroInteriorPersonal, other.numeroInteriorPersonal)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostalPersonal, other.codigoPostalPersonal)) {
            return false;
        }
        if (!Objects.equals(this.tipoDireccionFiscal, other.tipoDireccionFiscal)) {
            return false;
        }
        if (!Objects.equals(this.calleFiscal, other.calleFiscal)) {
            return false;
        }
        if (!Objects.equals(this.idEstadoFiscal, other.idEstadoFiscal)) {
            return false;
        }
        if (!Objects.equals(this.estadoFiscal, other.estadoFiscal)) {
            return false;
        }
        if (!Objects.equals(this.idMunicipioFiscal, other.idMunicipioFiscal)) {
            return false;
        }
        if (!Objects.equals(this.municipioFiscal, other.municipioFiscal)) {
            return false;
        }
        if (!Objects.equals(this.idColoniaFiscal, other.idColoniaFiscal)) {
            return false;
        }
        if (!Objects.equals(this.coloniaFiscal, other.coloniaFiscal)) {
            return false;
        }
        if (!Objects.equals(this.numeroExteriorFiscal, other.numeroExteriorFiscal)) {
            return false;
        }
        if (!Objects.equals(this.numeroInteriorFiscal, other.numeroInteriorFiscal)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostalFiscal, other.codigoPostalFiscal)) {
            return false;
        }
        if (!Objects.equals(this.nombrePlaza, other.nombrePlaza)) {
            return false;
        }
        if (!Objects.equals(this.idNivelEstudios, other.idNivelEstudios)) {
            return false;
        }
        if (!Objects.equals(this.idEstadoCivil, other.idEstadoCivil)) {
            return false;
        }
        if (!Objects.equals(this.idPlaza, other.idPlaza)) {
            return false;
        }
        if (!Objects.equals(this.issemym, other.issemym)) {
            return false;
        }
        return true;
    }

    /**
     * Obtener los datos de un registro para complementar la direccion fiscal.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 24/01/2021.
     * @param servidorPublicoModel Datos de servidor publico del que se
     * obtendran los datos para la fusion.
     *
     */
    public void fusionarDirecciones(ServidorPublicoModel servidorPublicoModel) {
        this.setIdTipoDireccionFiscal(servidorPublicoModel.getIdTipoDireccionPersonal());
        this.setTipoDireccionFiscal(servidorPublicoModel.getTipoDireccionPersonal());
        this.setCalleFiscal(servidorPublicoModel.getCallePersonal());
        this.setIdEstadoFiscal(servidorPublicoModel.getIdEstadoPersonal());
        this.setEstadoFiscal(servidorPublicoModel.getEstadoPersonal());
        this.setIdMunicipioFiscal(servidorPublicoModel.getIdMunicipioPersonal());
        this.setMunicipioFiscal(servidorPublicoModel.getMunicipioPersonal());
        this.setIdColoniaFiscal(servidorPublicoModel.getIdColoniaPersonal());
        this.setColoniaFiscal(servidorPublicoModel.getColoniaPersonal());
        this.setNumeroExteriorFiscal(servidorPublicoModel.getNumeroExteriorPersonal());
        this.setNumeroInteriorFiscal(servidorPublicoModel.getNumeroInteriorPersonal());
        this.setCodigoPostalFiscal(servidorPublicoModel.getCodigoPostalPersonal());
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    /**
     * @return the idOrdinal
     */
    public int getIdOrdinal() {
        return idOrdinal;
    }

    /**
     * @param idOrdinal the idOrdinal to set
     */
    public void setIdOrdinal(int idOrdinal) {
        this.idOrdinal = idOrdinal;
    }

    /**
     * @return the claveServidorPublico
     */
    public String getClaveServidorPublico() {
        return claveServidorPublico;
    }

    /**
     * @param claveServidorPublico the claveServidorPublico to set
     */
    public void setClaveServidorPublico(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the primerApellido
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the idSexo
     */
    public int getIdSexo() {
        return idSexo;
    }

    /**
     * @param idSexo the idSexo to set
     */
    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    /**
     * @return the tipoIdDireccionPersonal
     */
    public int getIdTipoDireccionPersonal() {
        return idTipoDireccionPersonal;
    }

    /**
     * @param idTipoDireccionPersonal the tipoIdDireccionPersonal to set
     */
    public void setIdTipoDireccionPersonal(int idTipoDireccionPersonal) {
        this.idTipoDireccionPersonal = idTipoDireccionPersonal;
    }

    /**
     * @return the tipoDireccionPersonal
     */
    public String getTipoDireccionPersonal() {
        return tipoDireccionPersonal;
    }

    /**
     * @param tipoDireccionPersonal the tipoDireccionPersonal to set
     */
    public void setTipoDireccionPersonal(String tipoDireccionPersonal) {
        this.tipoDireccionPersonal = tipoDireccionPersonal;
    }

    /**
     * @return the callePersonal
     */
    public String getCallePersonal() {
        return callePersonal;
    }

    /**
     * @param callePersonal the callePersonal to set
     */
    public void setCallePersonal(String callePersonal) {
        this.callePersonal = callePersonal;
    }

    /**
     * @return the idEstadoPersonal
     */
    public String getIdEstadoPersonal() {
        return idEstadoPersonal;
    }

    /**
     * @param idEstadoPersonal the idEstadoPersonal to set
     */
    public void setIdEstadoPersonal(String idEstadoPersonal) {
        this.idEstadoPersonal = idEstadoPersonal;
    }

    /**
     * @return the estadoPersonal
     */
    public String getEstadoPersonal() {
        return estadoPersonal;
    }

    /**
     * @param estadoPersonal the estadoPersonal to set
     */
    public void setEstadoPersonal(String estadoPersonal) {
        this.estadoPersonal = estadoPersonal;
    }

    /**
     * @return the idMunicipioPersonal
     */
    public String getIdMunicipioPersonal() {
        return idMunicipioPersonal;
    }

    /**
     * @param idMunicipioPersonal the idMunicipioPersonal to set
     */
    public void setIdMunicipioPersonal(String idMunicipioPersonal) {
        this.idMunicipioPersonal = idMunicipioPersonal;
    }

    /**
     * @return the municipioPersonal
     */
    public String getMunicipioPersonal() {
        return municipioPersonal;
    }

    /**
     * @param municipioPersonal the municipioPersonal to set
     */
    public void setMunicipioPersonal(String municipioPersonal) {
        this.municipioPersonal = municipioPersonal;
    }

    /**
     * @return the idColoniaPersonal
     */
    public String getIdColoniaPersonal() {
        return idColoniaPersonal;
    }

    /**
     * @param idColoniaPersonal the idColoniaPersonal to set
     */
    public void setIdColoniaPersonal(String idColoniaPersonal) {
        this.idColoniaPersonal = idColoniaPersonal;
    }

    /**
     * @return the coloniaPersonal
     */
    public String getColoniaPersonal() {
        return coloniaPersonal;
    }

    /**
     * @param coloniaPersonal the coloniaPersonal to set
     */
    public void setColoniaPersonal(String coloniaPersonal) {
        this.coloniaPersonal = coloniaPersonal;
    }

    /**
     * @return the numeroExteriorPersonal
     */
    public String getNumeroExteriorPersonal() {
        return numeroExteriorPersonal;
    }

    /**
     * @param numeroExteriorPersonal the numeroExteriorPersonal to set
     */
    public void setNumeroExteriorPersonal(String numeroExteriorPersonal) {
        this.numeroExteriorPersonal = numeroExteriorPersonal;
    }

    /**
     * @return the numeroInteriorPersonal
     */
    public String getNumeroInteriorPersonal() {
        return numeroInteriorPersonal;
    }

    /**
     * @param numeroInteriorPersonal the numeroInteriorPersonal to set
     */
    public void setNumeroInteriorPersonal(String numeroInteriorPersonal) {
        this.numeroInteriorPersonal = numeroInteriorPersonal;
    }

    /**
     * @return the codigoPostalPersonal
     */
    public String getCodigoPostalPersonal() {
        return codigoPostalPersonal;
    }

    /**
     * @param codigoPostalPersonal the codigoPostalPersonal to set
     */
    public void setCodigoPostalPersonal(String codigoPostalPersonal) {
        this.codigoPostalPersonal = codigoPostalPersonal;
    }

    /**
     * @return the tipoIdDireccionFiscal
     */
    public int getIdTipoDireccionFiscal() {
        return idTipoDireccionFiscal;
    }

    /**
     * @param idTipoDireccionFiscal the tipoIdDireccionFiscal to set
     */
    public void setIdTipoDireccionFiscal(int idTipoDireccionFiscal) {
        this.idTipoDireccionFiscal = idTipoDireccionFiscal;
    }

    /**
     * @return the tipoDireccionFiscal
     */
    public String getTipoDireccionFiscal() {
        return tipoDireccionFiscal;
    }

    /**
     * @param tipoDireccionFiscal the tipoDireccionFiscal to set
     */
    public void setTipoDireccionFiscal(String tipoDireccionFiscal) {
        this.tipoDireccionFiscal = tipoDireccionFiscal;
    }

    /**
     * @return the calleFiscal
     */
    public String getCalleFiscal() {
        return calleFiscal;
    }

    /**
     * @param calleFiscal the calleFiscal to set
     */
    public void setCalleFiscal(String calleFiscal) {
        this.calleFiscal = calleFiscal;
    }

    /**
     * @return the idEstadoFiscal
     */
    public String getIdEstadoFiscal() {
        return idEstadoFiscal;
    }

    /**
     * @param idEstadoFiscal the idEstadoFiscal to set
     */
    public void setIdEstadoFiscal(String idEstadoFiscal) {
        this.idEstadoFiscal = idEstadoFiscal;
    }

    /**
     * @return the estadoFiscal
     */
    public String getEstadoFiscal() {
        return estadoFiscal;
    }

    /**
     * @param estadoFiscal the estadoFiscal to set
     */
    public void setEstadoFiscal(String estadoFiscal) {
        this.estadoFiscal = estadoFiscal;
    }

    /**
     * @return the idMunicipioFiscal
     */
    public String getIdMunicipioFiscal() {
        return idMunicipioFiscal;
    }

    /**
     * @param idMunicipioFiscal the idMunicipioFiscal to set
     */
    public void setIdMunicipioFiscal(String idMunicipioFiscal) {
        this.idMunicipioFiscal = idMunicipioFiscal;
    }

    /**
     * @return the municipioFiscal
     */
    public String getMunicipioFiscal() {
        return municipioFiscal;
    }

    /**
     * @param municipioFiscal the municipioFiscal to set
     */
    public void setMunicipioFiscal(String municipioFiscal) {
        this.municipioFiscal = municipioFiscal;
    }

    /**
     * @return the idColoniaFiscal
     */
    public String getIdColoniaFiscal() {
        return idColoniaFiscal;
    }

    /**
     * @param idColoniaFiscal the idColoniaFiscal to set
     */
    public void setIdColoniaFiscal(String idColoniaFiscal) {
        this.idColoniaFiscal = idColoniaFiscal;
    }

    /**
     * @return the coloniaFiscal
     */
    public String getColoniaFiscal() {
        return coloniaFiscal;
    }

    /**
     * @param coloniaFiscal the coloniaFiscal to set
     */
    public void setColoniaFiscal(String coloniaFiscal) {
        this.coloniaFiscal = coloniaFiscal;
    }

    /**
     * @return the numeroExteriorFiscal
     */
    public String getNumeroExteriorFiscal() {
        return numeroExteriorFiscal;
    }

    /**
     * @param numeroExteriorFiscal the numeroExteriorFiscal to set
     */
    public void setNumeroExteriorFiscal(String numeroExteriorFiscal) {
        this.numeroExteriorFiscal = numeroExteriorFiscal;
    }

    /**
     * @return the numeroInteriorFiscal
     */
    public String getNumeroInteriorFiscal() {
        return numeroInteriorFiscal;
    }

    /**
     * @param numeroInteriorFiscal the numeroInteriorFiscal to set
     */
    public void setNumeroInteriorFiscal(String numeroInteriorFiscal) {
        this.numeroInteriorFiscal = numeroInteriorFiscal;
    }

    /**
     * @return the codigoPostalFiscal
     */
    public String getCodigoPostalFiscal() {
        return codigoPostalFiscal;
    }

    /**
     * @param codigoPostalFiscal the codigoPostalFiscal to set
     */
    public void setCodigoPostalFiscal(String codigoPostalFiscal) {
        this.codigoPostalFiscal = codigoPostalFiscal;
    }

    /**
     * @return the nombrePlaza
     */
    public String getNombrePlaza() {
        return nombrePlaza;
    }

    /**
     * @param nombrePlaza the nombrePlaza to set
     */
    public void setNombrePlaza(String nombrePlaza) {
        this.nombrePlaza = nombrePlaza;
    }

    /**
     * @return the idNivelEstudios
     */
    public String getIdNivelEstudios() {
        return idNivelEstudios;
    }

    /**
     * @param idNivelEstudios the idNivelEstudios to set
     */
    public void setIdNivelEstudios(String idNivelEstudios) {
        this.idNivelEstudios = idNivelEstudios;
    }

    /**
     * @return the idEstadoCivil
     */
    public String getIdEstadoCivil() {
        return idEstadoCivil;
    }

    /**
     * @param idEstadoCivil the idEstadoCivil to set
     */
    public void setIdEstadoCivil(String idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    /**
     * @return the idPlaza
     */
    public String getIdPlaza() {
        return idPlaza;
    }

    /**
     * @param idPlaza the idPlaza to set
     */
    public void setIdPlaza(String idPlaza) {
        this.idPlaza = idPlaza;
    }

    /**
     * @return the issemym
     */
    public String getIssemym() {
        return issemym;
    }

    /**
     * @param issemym the issemym to set
     */
    public void setIssemym(String issemym) {
        this.issemym = issemym;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }
    //</editor-fold>
}
