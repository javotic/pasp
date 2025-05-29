package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ConsultarDatosPuestoServidorPublico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("CLAVESERVIDORPUBLICO")
	private String claveServidorPublico;
	@SerializedName("NOMBRE")
	private String nombre;
	@SerializedName("PRIMERAPELLIDO")
	private String primerApellido;
	@SerializedName("SEGUNDOAPELLIDO")
	private String segundoApellido;
	@SerializedName("NOMBREPUESTO")
	private String nombrePuesto;
	@SerializedName("FECHAINGRESOPUESTO")
	private String fechaIngresoPuesto;
	@SerializedName("NUMPLAZA")
	private String numPlaza;
	@SerializedName("CODIGOPUESTO")
	private String codigoPuesto;
	@SerializedName("FECHAINGRESOGEM")
	private String fechaFechaIngresoGem;
	@SerializedName("SUBSECRETARIAGRL")
	private String subsecretariaGeneral;
	@SerializedName("SUBSECRETARIA")
	private String subsecretaria;
	@SerializedName("DIRECCIONGRL")
	private String direccionGeneral;
	@SerializedName("DIRECCIONAREA")
	private String direccionArea;
	@SerializedName("SUBDIRECCION")
	private String subdireccion;
	@SerializedName("DEPARTAMENTO")
	private String departamento;
	@SerializedName("ESPACIOORGANIZACIONAL;")
	private String espacioOrganizacional;
	@SerializedName("CLAVEUNIDADADMINISTRATIVA")
	private String claveUnidadAdministrativa;
	@SerializedName("NOMBREUNIDADADMINISTRATIVA")
	private String nombreUnidadAdministrativa;
	@SerializedName("CLAVESPEVALUADOR")
	private String clavespEvaluador;
	@SerializedName("NOMBRESPEVALUADOR")
	private String nombrespEvaluador;
	@SerializedName("PRIMERAPELLIDOSPEVALUADOR")
	private String primerApellidoPEvaluador;
	@SerializedName("SEGUNDOAPELLIDOSPEVALUADOR")
	private String segundoApellidoPEvaluador;
	@SerializedName("CALIFICACIONFINAL")
	private String calificacionFinal;
	@SerializedName("FECHAUNO")
	private String fechaUno;
	@SerializedName("FECHADOS")
	private String fechaDos;
        @SerializedName("FECHATRES")
	private String fechaTres;
        @SerializedName("ESTATUS")
	private String estatus;
        @SerializedName("DEPENDENCIA")
	private String dependencia;
        

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

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public String getFechaIngresoPuesto() {
		return fechaIngresoPuesto;
	}

	public void setFechaIngresoPuesto(String fechaIngresoPuesto) {
		this.fechaIngresoPuesto = fechaIngresoPuesto;
	}

	public String getNumPlaza() {
		return numPlaza;
	}

	public void setNumPlaza(String numPlaza) {
		this.numPlaza = numPlaza;
	}

	public String getCodigoPuesto() {
		return codigoPuesto;
	}

	public void setCodigoPuesto(String codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public String getFechaFechaIngresoGem() {
		return fechaFechaIngresoGem;
	}

	public void setFechaFechaIngresoGem(String fechaFechaIngresoGem) {
		this.fechaFechaIngresoGem = fechaFechaIngresoGem;
	}

	public String getSubsecretariaGeneral() {
		return subsecretariaGeneral;
	}

	public void setSubsecretariaGeneral(String subsecretariaGeneral) {
		this.subsecretariaGeneral = subsecretariaGeneral;
	}

	public String getSubsecretaria() {
		return subsecretaria;
	}

	public void setSubsecretaria(String subsecretaria) {
		this.subsecretaria = subsecretaria;
	}

	public String getDireccionGeneral() {
		return direccionGeneral;
	}

	public void setDireccionGeneral(String direccionGeneral) {
		this.direccionGeneral = direccionGeneral;
	}

	public String getDireccionArea() {
		return direccionArea;
	}

	public void setDireccionArea(String direccionArea) {
		this.direccionArea = direccionArea;
	}

	public String getSubdireccion() {
		return subdireccion;
	}

	public void setSubdireccion(String subdireccion) {
		this.subdireccion = subdireccion;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getEspacioOrganizacional() {
		return espacioOrganizacional;
	}

	public void setEspacioOrganizacional(String espacioOrganizacional) {
		this.espacioOrganizacional = espacioOrganizacional;
	}

	public String getClaveUnidadAdministrativa() {
		return claveUnidadAdministrativa;
	}

	public void setClaveUnidadAdministrativa(String claveUnidadAdministrativa) {
		this.claveUnidadAdministrativa = claveUnidadAdministrativa;
	}

	public String getNombreUnidadAdministrativa() {
		return nombreUnidadAdministrativa;
	}

	public void setNombreUnidadAdministrativa(String nombreUnidadAdministrativa) {
		this.nombreUnidadAdministrativa = nombreUnidadAdministrativa;
	}

	public String getClavespEvaluador() {
		return clavespEvaluador;
	}

	public void setClavespEvaluador(String clavespEvaluador) {
		this.clavespEvaluador = clavespEvaluador;
	}

	public String getNombrespEvaluador() {
		return nombrespEvaluador;
	}

	public void setNombrespEvaluador(String nombrespEvaluador) {
		this.nombrespEvaluador = nombrespEvaluador;
	}

	public String getPrimerApellidoPEvaluador() {
		return primerApellidoPEvaluador;
	}

	public void setPrimerApellidoPEvaluador(String primerApellidoPEvaluador) {
		this.primerApellidoPEvaluador = primerApellidoPEvaluador;
	}

	public String getSegundoApellidoPEvaluador() {
		return segundoApellidoPEvaluador;
	}

	public void setSegundoApellidoPEvaluador(String segundoApellidoPEvaluador) {
		this.segundoApellidoPEvaluador = segundoApellidoPEvaluador;
	}

	public String getCalificacionFinal() {
		return calificacionFinal;
	}

	public void setCalificacionFinal(String calificacionFinal) {
		this.calificacionFinal = calificacionFinal;
	}

	public String getFechaUno() {
		return fechaUno;
	}

	public void setFechaUno(String fechaUno) {
		this.fechaUno = fechaUno;
	}

	public String getFechaDos() {
		return fechaDos;
	}

	public void setFechaDos(String fechaDos) {
		this.fechaDos = fechaDos;
	}

        public String getFechaTres() {
            return fechaTres;
        }

        public void setFechaTres(String fechaTres) {
            this.fechaTres = fechaTres;
        }

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public String getDependencia() {
            return dependencia;
        }

        public void setDependencia(String dependencia) {
            this.dependencia = dependencia;
        }
    
	@Override
	public String toString() {
		return "ConsultarDatosPuestoServidorPublico [claveServidorPublico=" + claveServidorPublico + ", nombre="
				+ nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido
				+ ", nombrePuesto=" + nombrePuesto + ", fechaIngresoPuesto=" + fechaIngresoPuesto + ", numPlaza="
				+ numPlaza + ", codigoPuesto=" + codigoPuesto + ", fechaFechaIngresoGem=" + fechaFechaIngresoGem
				+ ", subsecretariaGeneral=" + subsecretariaGeneral + ", subsecretaria=" + subsecretaria
				+ ", direccionGeneral=" + direccionGeneral + ", direccionArea=" + direccionArea + ", subdireccion="
				+ subdireccion + ", departamento=" + departamento + ", espacioOrganizacional=" + espacioOrganizacional
				+ ", claveUnidadAdministrativa=" + claveUnidadAdministrativa + ", nombreUnidadAdministrativa="
				+ nombreUnidadAdministrativa + ", clavespEvaluador=" + clavespEvaluador + ", nombrespEvaluador="
				+ nombrespEvaluador + ", primerApellidoPEvaluador=" + primerApellidoPEvaluador
				+ ", segundoApellidoPEvaluador=" + segundoApellidoPEvaluador + ", calificacionFinal="
				+ calificacionFinal + ", fechaUno=" + fechaUno + ", fechaDos=" + fechaDos + "]";
	}

}
