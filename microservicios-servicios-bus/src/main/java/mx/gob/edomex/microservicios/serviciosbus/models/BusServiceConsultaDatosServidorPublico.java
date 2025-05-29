package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceConsultaDatosServidorPublico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDORDINAL")
	private String idOrdinal;
	@SerializedName("CLAVESERVIDORPUBLICO")
	private String claveServidorPublico;
	@SerializedName("NOMBRE")
	private String nombre;
	@SerializedName("PRIMERAPELLIDO")
	private String primerApellido;
	@SerializedName("GENERO")
	private String sexo;
	@SerializedName("CURP")
	private String curp;
	@SerializedName("RFC")
	private String rfc;
	@SerializedName("FECHANACIMIENTO")
	private String fechaNacimiento;
	@SerializedName("TELEFONO")
	private String telefono;
	@SerializedName("CORREOELECTRONICO")
	private String correoElectronico;
	@SerializedName("CALLE")
	private String calle;
	@SerializedName("IDESTADO")
	private String idEstado;
	@SerializedName("IDMUNICIPIO")
	private String idMunicipio;
	@SerializedName("IDCOLONIA")
	private String idColonia;
	@SerializedName("NUMEXTERIOR")
	private String numeroExterior;
	@SerializedName("NUMINTERIOR")
	private String numeroInterior;
	@SerializedName("CODIGOPOSTAL")
	private String codigoPostal;
	@SerializedName("NOMBREESTADO")
	private String nombreEstado;
	@SerializedName("NOMBREPLAZA")
	private String nombrePlaza;
	@SerializedName("IDNIVELESTUDIOS")
	private String idNivelEstudios;
	public String getIdOrdinal() {
		return idOrdinal;
	}
	public void setIdOrdinal(String idOrdinal) {
		this.idOrdinal = idOrdinal;
	}
	public String getClaveServidorPublico() {
		return claveServidorPublico;
	}
	public void setClaveServidorPublico(String claveServidorPublico) {
		this.claveServidorPublico = claveServidorPublico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getIdColonia() {
		return idColonia;
	}
	public void setIdColonia(String idColonia) {
		this.idColonia = idColonia;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getNombrePlaza() {
		return nombrePlaza;
	}
	public void setNombrePlaza(String nombrePlaza) {
		this.nombrePlaza = nombrePlaza;
	}
	public String getIdNivelEstudios() {
		return idNivelEstudios;
	}
	public void setIdNivelEstudios(String idNivelEstudios) {
		this.idNivelEstudios = idNivelEstudios;
	}

	

}
