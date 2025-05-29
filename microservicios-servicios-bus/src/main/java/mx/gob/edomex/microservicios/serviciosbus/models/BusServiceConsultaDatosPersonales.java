package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceConsultaDatosPersonales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("CLAVESERVIDOR")
	private String claveServidor;
	@SerializedName("APELLIDOPATERNO")
	private String apellidoPaterno;
	@SerializedName("APELLIDOMATERNO")
	private String apellidoMaterno;
	@SerializedName("MOMBRE")
	private String nombre;
	@SerializedName("NOMBRECOMPLETO")
	private String nombreCompleto;
	@SerializedName("IDGENERO")
	private String idSexo;
	@SerializedName("NOMBREGENERO")
	private String nombreGenero;
	@SerializedName("IDPAIS")
	private String idPais;
	@SerializedName("NOMBREPAIS")
	private String nombrePais;
	@SerializedName("IDTRATAMIENTO")
	private String idTratamiento;
	@SerializedName("NOMBRETRATAMIENTO")
	private String nombreTratamiento;
	@SerializedName("FECHANACIMIENTO")
	private String fechaNacimiento;
	@SerializedName("RFC")
	private String rfc;
	@SerializedName("CURP")
	private String curp;
	@SerializedName("IDESCOLARIDAD")
	private String idEscolaridad;
	@SerializedName("DESCRIESCOLARIDAD")
	private String descripcionEscolaridad;
	@SerializedName("IDESTADOCIVIL")
	private String idEstadoCivil;
	@SerializedName("NOMBREESTADOCIVIL")
	private String nombreEstadoCivil;
	@SerializedName("COMENTARIOS")
	private String comentarios;

	public String getClaveServidor() {
		return claveServidor;
	}

	public void setClaveServidor(String claveServidor) {
		this.claveServidor = claveServidor;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(String idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getNombreTratamiento() {
		return nombreTratamiento;
	}

	public void setNombreTratamiento(String nombreTratamiento) {
		this.nombreTratamiento = nombreTratamiento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getIdEscolaridad() {
		return idEscolaridad;
	}

	public void setIdEscolaridad(String idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}

	public String getDescripcionEscolaridad() {
		return descripcionEscolaridad;
	}

	public void setDescripcionEscolaridad(String descripcionEscolaridad) {
		this.descripcionEscolaridad = descripcionEscolaridad;
	}

	public String getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getNombreEstadoCivil() {
		return nombreEstadoCivil;
	}

	public void setNombreEstadoCivil(String nombreEstadoCivil) {
		this.nombreEstadoCivil = nombreEstadoCivil;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
