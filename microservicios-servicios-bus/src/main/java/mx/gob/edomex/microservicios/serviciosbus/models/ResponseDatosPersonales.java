package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;
import java.util.List;

public class ResponseDatosPersonales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String mensaje;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaNacimiento;
	private String curp;
	private String rfc;
	private String issemym;
	private String telefono;
	private String correoElectronico;
	private String idNivelEstudios;
	private String idEstadoCivil;
	private String idSexo;
	private String idEstado;
	private String idMunicipio;
	private String idColonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String codigoPostal;
	private List<Combo> lstEstados;
	private List<Combo> lstMunicipios;
	private List<Combo> lstColonias;

	private List<Combo> lstNivelEstudios;
	private List<Combo> lstGenero;
	private List<Combo> lstEstadoCivil;
	
	
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getIssemym() {
		return issemym;
	}

	public void setIssemym(String issemym) {
		this.issemym = issemym;
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

	public String getIdNivelEstudios() {
		return idNivelEstudios;
	}

	public void setIdNivelEstudios(String idNivelEstudios) {
		this.idNivelEstudios = idNivelEstudios;
	}

	public String getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getIdSexo() {
		return idSexo;
	}

	public void setIdSexo(String idSexo) {
		this.idSexo = idSexo;
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

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public String getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public List<Combo> getLstEstados() {
		return lstEstados;
	}

	public void setLstEstados(List<Combo> lstEstados) {
		this.lstEstados = lstEstados;
	}

	public List<Combo> getLstMunicipios() {
		return lstMunicipios;
	}

	public void setLstMunicipios(List<Combo> lstMunicipios) {
		this.lstMunicipios = lstMunicipios;
	}

	public List<Combo> getLstColonias() {
		return lstColonias;
	}

	public void setLstColonias(List<Combo> lstColonias) {
		this.lstColonias = lstColonias;
	}

	public List<Combo> getLstNivelEstudios() {
		return lstNivelEstudios;
	}

	public void setLstNivelEstudios(List<Combo> lstNivelEstudios) {
		this.lstNivelEstudios = lstNivelEstudios;
	}

	public List<Combo> getLstGenero() {
		return lstGenero;
	}

	public void setLstGenero(List<Combo> lstGenero) {
		this.lstGenero = lstGenero;
	}

	public List<Combo> getLstEstadoCivil() {
		return lstEstadoCivil;
	}

	public void setLstEstadoCivil(List<Combo> lstEstadoCivil) {
		this.lstEstadoCivil = lstEstadoCivil;
	}

}
