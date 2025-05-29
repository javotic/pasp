import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CatalogoEstados } from '../models/catalogo-estados';
import { DatosPersonalesDTO } from '../dto/datosPersonalesDTO';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { ConsultaDatosCorreo } from '../models/consulta-datos-correo';
import { ConsultaDatosDireccion } from '../models/consulta-datos-direccion';
import { ConsultaDatosTelefonicos } from '../models/consulta-datos-telefonicos';
import { ConsultaDatosPersonales } from '../models/consulta-datos-personales';
import { ConsultaDatosServidorPublico } from '../models/consulta-datos-servidor-publico';
import { UtilsService } from './utils.service';
import { map } from 'rxjs/operators';
import { DatosPersonalesEtiquetas } from '../etiquetas/datos-personales-etiquetas';
import { ConsultaDatosPersonalesServidor } from '../models/consulta-datos-personales-servidor';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';
import { RespuestaGenerica } from '../repuesta/respuesta-generica';
import { ServidorPublicoModel } from '../models/servidor-publico-model';


@Injectable({
  providedIn: 'root'
})
export class DatospersonalesService {
  //#region Atributos de clase
  urlCat: string = `${environment.HOST}/frwsr_LPSAUT_CONS_CAT.php`;
  urlDat: string = `${environment.HOST}/frwsr_LPSAUT_CONS_DAT.php`;
  urlServ: string = `${environment.HOST}/frwsr_LPSAUT_SERV.php`;
  estados: CatalogoEstados[];
  //#endregion

  //#region Constructor
  constructor(private http: HttpClient,
    private utilsService: UtilsService) { }
  //#endregion

  //#region Funciones publicas
  registrar(datosPersonalesDTO: DatosPersonalesDTO) {
    return this.http.post(environment.BASEENPOINT_DATOS_PERSONALES, datosPersonalesDTO);
  }

  cargaDatosPersonales(idEmpleado: string, nombre: string,
    apellidoPaterno: string, apellidoMaterno: string) {
    return this.http.get<RespuestaApi<ConsultaDatosPersonales>>(`${environment.BASEENPOINT_BUS_SEI}/servidorpublico/datospersonales/idServidorPublico/${idEmpleado}/nombre/${nombre}/apellidoPat/${apellidoPaterno}/apellidoMat/${apellidoMaterno}`)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );
  }

  actualizarDatosPersonales(idEmpleado: string, datosPersonalesDTO: DatosPersonalesDTO) {
    const formData = new FormData();
    formData.append('CLAVESERVIDORPUBLICO', idEmpleado);
    formData.append('IDDATOSPERSONALES', '1');
    formData.append('TELEFONO', datosPersonalesDTO.telefono);
    formData.append('CORREOELECTRONICO', datosPersonalesDTO.correoElectronico);
    formData.append('IDESTADO', datosPersonalesDTO.idEstado);
    formData.append('IDMUNICIPIO', datosPersonalesDTO.idMunicipio);
    formData.append('IDCOLONIA', datosPersonalesDTO.idColonia);
    formData.append('CALLE', datosPersonalesDTO.direcion);
    formData.append('NUMEXTERIOR', datosPersonalesDTO.numeroExterior);
    formData.append('NUMINTERIOR', datosPersonalesDTO.numeroInterior);
    formData.append('CODIGOPOSTAL', datosPersonalesDTO.codigoPostal);
    formData.append('IDNIVELESTUDIOS', datosPersonalesDTO.idNivelEstudios);
    formData.append('IDESTADOCIVIL', datosPersonalesDTO.idEstadoCivil);

    const url = environment.BASEENPOINT_BUS_SEI + '/servidorpublico/actualizarDatosPersonales';

    return this.http.post<RespuestaApi<ConsultaDatosPersonales>>(`${url}`, formData)
      .pipe(
        catchError(e => {
          if (e.status === 400) {
            return throwError(e);
          }
          console.error(e);
          return throwError(e);
        })
      );
  }

  cargaDatosServidorPublico(idEmpleado: string) {
    const url = environment.BASEENPOINT_BUS_SEI + '/servidorpublico/datosservidorpublico/idServidorPublico/' + idEmpleado;
    return this.http.get<RespuestaGenerica<ConsultaDatosServidorPublico>>(`${url}`);
  }

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }

  getEtiquetasPagos(): Observable<any> {
    return this.utilsService.ObtenerEtiquetasPagina('/datosPersonales', 'español (México)').pipe(
      map((response: any) => {
        let datosPersonalesEtiquetas = new DatosPersonalesEtiquetas();
        Object.keys(response).map((key) => {

          switch (key) {
            case 'lbl.telefono':
              datosPersonalesEtiquetas.telefono = response[key];
              break;
            case 'lbl.ApellidoPaterno':
              datosPersonalesEtiquetas.ApellidoPaterno = response[key];
              break;
            case 'lbl.seleccioneMunicipio':
              datosPersonalesEtiquetas.seleccioneMunicipio = response[key];
              break;
            case 'lbl.nExterior':
              datosPersonalesEtiquetas.nExterior = response[key];
              break;
            case 'lbl.Cp':
              datosPersonalesEtiquetas.Cp = response[key];
              break;
            case 'lbl.seleccioneSexo':
              datosPersonalesEtiquetas.seleccioneSexo = response[key];

              break;
            case 'lbl.seleccioneEstadoCivil':
              datosPersonalesEtiquetas.seleccioneEstadoCivil = response[key];
              break;
            case 'ttl.tituloPersonales':
              datosPersonalesEtiquetas.tituloPersonales = response[key];
              break;

            case 'lbl.nombre':
              datosPersonalesEtiquetas.nombre = response[key];
              break;

            case 'lbl.nInterior':
              datosPersonalesEtiquetas.nInterior = response[key];
              break;
            case 'lbl.ApellidoMaterno':
              datosPersonalesEtiquetas.ApellidoMaterno = response[key];
              break;
            case 'lbl.fechaNacimiento':
              datosPersonalesEtiquetas.fechaNacimiento = response[key];
              break;

            case 'lbl.Rfc':
              datosPersonalesEtiquetas.Rfc = response[key];
              break;

            case 'lbl.seleleccioneColonia':
              datosPersonalesEtiquetas.seleleccioneColonia = response[key];
              break;

            case 'lbl.municipio':
              datosPersonalesEtiquetas.municipio = response[key];
              break;

            case 'lbl.correlElectronico':
              datosPersonalesEtiquetas.correlElectronico = response[key];
              break;

            case 'lbl.Issemym':
              datosPersonalesEtiquetas.Issemym = response[key];
              break;

            case 'lbl.Curp':
              datosPersonalesEtiquetas.Curp = response[key];
              break;

            case 'lbl.calle':
              datosPersonalesEtiquetas.calle = response[key];
              break;

            case 'lbl.estadoNacimiento':
              datosPersonalesEtiquetas.estadoNacimiento = response[key];
              break;

            case 'lbl.seleccioneEstado':
              datosPersonalesEtiquetas.seleccioneEstado = response[key];
              break;

            case 'err.telefono':
              datosPersonalesEtiquetas.errtelefono = response[key];
              break;

            case 'err.email':
              datosPersonalesEtiquetas.erremail = response[key];
              break;

            case 'err.estado':
              datosPersonalesEtiquetas.errestado = response[key];
              break;

            case 'err.municipio':
              datosPersonalesEtiquetas.errmunicipio = response[key];
              break;

            case 'err.colonia':
              datosPersonalesEtiquetas.errcolonia = response[key];
              break;

            case 'err.calle':
              datosPersonalesEtiquetas.errcalle = response[key];
              break;

            case 'err.ninterior':
              datosPersonalesEtiquetas.errninterior = response[key];
              break;

            case 'err.nexterior':
              datosPersonalesEtiquetas.errnexterior = response[key];
              break;

            case 'err.cp':
              datosPersonalesEtiquetas.errcp = response[key];
              break;
            case 'btn.guardar':
              datosPersonalesEtiquetas.btnGuardar = response[key];
              break;
            default:
              break;
          }
        });
        return datosPersonalesEtiquetas;
      })
    );
  }

  cargaDatosServidorPublico2(idEmpleado: string) {
    return this.http.get<ConsultaDatosPersonalesServidor>
      (`${environment.BASEENPOINT_BUS_SEI}/servidorpublico/consultarDatosPersonalesConCatalogos/idServidorPublico/${idEmpleado}`);
  }

  /**
   * Obtener datos generales de un servidor publico a traves de su clave
   * de servidor publico.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 24/01/2022.
   * @param claveServidor Clave de servidor publico del que se obtendran los datos.
   * @returns Observable de respuesta principal de servicio de tipo ServidorPublicoModel.
   */
  obtenerDatosGenerales(claveServidor: string) {
    let url = `/servidorpublico/obtenerDatosGenerales?claveServidor=${claveServidor}`;
    return this.http.get<RespuestaApi<ServidorPublicoModel>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  /**
   * Actualizar datos personales de un servidor publico determinado.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 25/01/2022.
   * @param idEmpleado Clave de servidor publico del que se realiza la actualizacion de datos.
   * @param datosPersonalesDTO Objeto de transferencia de datos con la informacion a actualizar.
   * @return Observable de respuesta pincipal de servicio de tipo ConsultaDatosPersonales
   */
  actualizarDatosPersonalesGenerales(idEmpleado: string,
    datosPersonalesDTO: DatosPersonalesDTO): Observable<RespuestaApi<ConsultaDatosPersonales>> {
    const formData = new FormData();
    formData.append('claveServidor', idEmpleado);
    formData.append('telefono', datosPersonalesDTO.telefono);
    formData.append('mail', datosPersonalesDTO.correoElectronico);
    formData.append('idNivelEstudios', datosPersonalesDTO.idNivelEstudios);
    formData.append('idEstadoCivil', datosPersonalesDTO.idEstadoCivil);
    //Domicilio personal
    formData.append('idTipoDireccionPersonal', '1');
    formData.append('idEstadoPersonal', datosPersonalesDTO.idEstado);
    formData.append('idMunicipioPersonal', datosPersonalesDTO.idMunicipio);
    formData.append('idColoniaPersonal', datosPersonalesDTO.idColonia);
    formData.append('callePersonal', datosPersonalesDTO.direcion);
    formData.append('numeroExteriorPersonal', datosPersonalesDTO.numeroExterior);
    formData.append('numeroInteriorPersonal', datosPersonalesDTO.numeroInterior);
    formData.append('codigoPostalPersonal', datosPersonalesDTO.codigoPostal);
    //Domicilio Fiscal
    formData.append('idTipoDireccionFiscal', '7');
    formData.append('idEstadoFiscal', datosPersonalesDTO.idEstadoFiscal);
    formData.append('idMunicipioFiscal', datosPersonalesDTO.idMunicipioFiscal);
    formData.append('idColoniaFiscal', datosPersonalesDTO.idColoniaFiscal);
    formData.append('calleFiscal', datosPersonalesDTO.direccionFiscal);
    formData.append('numeroExteriorFiscal', datosPersonalesDTO.numeroExteriorFiscal);
    formData.append('numeroInteriorFiscal', datosPersonalesDTO.numeroInteriorFiscal);
    formData.append('codigoPostalFiscal', datosPersonalesDTO.codigoPostalFiscal);

    const url = environment.BASEENPOINT_BUS_SEI + '/servidorpublico/actualizarDatosGenerales';

    return this.http.post<RespuestaApi<ConsultaDatosPersonales>>(`${url}`, formData)
      .pipe(
        catchError(e => {
          if (e.status === 400) {
            return throwError(e);
          }
          console.error(e);
          return throwError(e);
        })
      );
  }
  //#endregion
}