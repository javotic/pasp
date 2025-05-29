import { Injectable } from '@angular/core';
import { DatosProfesionales } from '../models/datos-profesionales';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { EnvioParametros } from '../models/envio-parametros';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { DatosProfesionalesDTO } from '../dto/datosProfesionalesDTO';
import * as moment from 'moment';
import { EmisorCertificado } from '../models/emisor-certificado';
import { UtilsService } from './utils.service';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { ConsultaDatosProfesionalesEtiquetas } from '../etiquetas/consulta-datos-profesionales-etiquetas';
import { DocumentosBobeda } from '../models/Documentos/documentosBoveda';
import { Vault } from '../models/Documentos/Vault';
import { Vaultuser } from '../models/Documentos/vault_user';
import { SeleccionDatoProfesionalProcesoDTO } from 'src/app/dto/seleccionDatoProfesionalProcesoDTO';

@Injectable({
  providedIn: 'root'
})
export class DatosprofesionalesService {
  //#region Constructor
  constructor(private http: HttpClient,
    private utilsService: UtilsService) { }
  //#endregion Eventos

  //#region Funciones publicas
  obtenerDatosProfesionales(idEmpleado: string) {

    let data = {
      "funcion": "consultarDatosProfesionales",
      "IDSERVIDORPUBLICO": idEmpleado
    };
    let body = JSON.stringify({ request: data });
    return this.http.get<RespuestaApi<DatosProfesionales>>
      (`${environment.BASEENPOINT_BUS_SEI}/procesoEscalafonarioVigente/consultarDatosProfesionales/` + idEmpleado);

  }

  eliminarDatosProfesionales(idEmpleado: string, idDatoProfesional: string) {
    const formData = new FormData();

    formData.append('IDSERVIDORPUBLICO', idEmpleado);
    formData.append('IDREGISTRODATOPROFESIONAL', idDatoProfesional);

    const url = environment.BASEENPOINT_BUS_SEI + '/servidorpublico/eliminarDatosProfesionales';

    return this.http.post<RespuestaApi<any>>(`${url}`, formData);
  }

  insertarActualizarDatosProfesionales(idEmpleado: string,
    idDatoProfesional: string, datosProfesionalesDTO: DatosProfesionalesDTO) {

    datosProfesionalesDTO.fechaEmisionCertificado = moment(datosProfesionalesDTO.fechaEmisionCertificado).format('YYYY-MM-DD HH:mm:ss');
    datosProfesionalesDTO.fechaVigenciaCertificado = moment(datosProfesionalesDTO.fechaVigenciaCertificado).format('YYYY-MM-DD HH:mm:ss');

    const formData = new FormData();

    formData.append('IDDATOPROFESIONAL', idDatoProfesional);
    formData.append('IDSERVIDORPUBLICO', idEmpleado);
    formData.append('IDNIVELESTUDIOS', '0');
    formData.append('FECHAEMISIONCERTIFICADO', datosProfesionalesDTO.fechaEmisionCertificado);
    formData.append('FECHAVIGENCIACERTIFICADO', datosProfesionalesDTO.fechaVigenciaCertificado);
    formData.append('NOMBRECERTIFICADO', datosProfesionalesDTO.nombreCertificado);
    formData.append('IDTIPOCERTIFICADO', datosProfesionalesDTO.idTipoCertificado);
    formData.append('NOCERTIFICADO', datosProfesionalesDTO.noCertificado);
    formData.append('IDEMISORCERTIFICADO', datosProfesionalesDTO.idEmisor);
    formData.append('DURACION', datosProfesionalesDTO.duracion);
    formData.append('TIPODURACION', datosProfesionalesDTO.tipoDuracion);

    const url = environment.BASEENPOINT_BUS_SEI + '/servidorpublico/gestionarDatosProfesionales';

    return this.http.post<RespuestaApi<DatosProfesionales>>(`${url}`, formData);
  }

  obtenerEmisorCertificado() {
    const urlServicio = '/catalogos/consultarCatalogoEmisoresCertificado';
    return this.http.get<RespuestaApi<EmisorCertificado>>(`${environment.BASEENPOINT_BUS_SEI}${urlServicio}`);
  }

  getEtiquetasDatosProfesionales(): Observable<any> {
    return this.utilsService.ObtenerEtiquetasPagina('/datosProfesionales', 'español (México)').pipe(

      map((response: any) => {
        let etiquetas = new ConsultaDatosProfesionalesEtiquetas();
        Object.keys(response).map((key) => {

          switch (key) {
            case 'ttl.titulodatosprofesionales':
              etiquetas.titulo = response[key];
              break;
            case 'btn.buscar':
              etiquetas.titulo = response[key];
              break;
            case 'lbl.tooltipfechas':
              etiquetas.titulo = response[key];
              break;
            case 'lbl.filtrofechas':
              etiquetas.titulo = response[key];
              break;
            case 'lbl.fechainicio':
              etiquetas.titulo = response[key];
              break;
            case 'lbl.fechafin':
              etiquetas.titulo = response[key];

              break;
            case 'lbl.nivelestudios':
              etiquetas.nivelEstudios = response[key];
              break;
            case 'lbl.fechaemision':
              etiquetas.fechaEmision = response[key];
              break;

            case 'lbl.vigenciacertificado':
              etiquetas.vigenciaCertificado = response[key];
              break;

            case 'lbl.nombrecertificado':
              etiquetas.nombreCertificado = response[key];
              break;
            case 'lbl.Certificado':
              etiquetas.certificado = response[key];
              break;
            case 'lbl.nocertificado':
              etiquetas.noCertificado = response[key];
              break;
            case 'lbl.editar':
              etiquetas.btnEditar = response[key];
              break;
            case 'lbl.eliminar':
              etiquetas.btnEliminar = response[key];
              break;



            case 'lbl.nivelestudios':
              etiquetas.nivelestudios = response[key];
              break;
            case 'lbl.fechaEmisionCertificado':
              etiquetas.fechaEmisionCertificado = response[key];
              break;
            case 'lbl.fechaVigenciaCertificado':
              etiquetas.fechaVigenciaCertificado = response[key];
              break;
            case 'lbl.nombreCertificado':
              etiquetas.nombreCertificado = response[key];
              break;
            case 'lbl.certificadoN':
              etiquetas.nocertificado = response[key];
              break;
            case 'lbl.nCertificado':
              etiquetas.nCertificado = response[key];
              break;
            case 'lbl.emisor':
              etiquetas.emisor = response[key];
              break;




            case 'lbl.noseecontraronregistros':
              etiquetas.noseecontraronregistros = response[key];
              break;
            case 'lbl.seleccionanivelestudios':
              etiquetas.seleccionanivelestudios = response[key];
              break;
            case 'lbl.idnivelestudiosrequerido':
              etiquetas.idnivelestudiosrequerido = response[key];
              break;
            case 'ph.fechaemision':
              etiquetas.phfechaemision = response[key];
              break;
            case 'lbl.fechaecrerquerido':
              etiquetas.fechaecrerquerido = response[key];
              break;
            case 'ph.fechavigencia':
              etiquetas.phfechavigencia = response[key];
              break;
            case 'lbl.fvigenciar':
              etiquetas.fvigenciar = response[key];
              break;

            case 'lbl.ncertificador':
              etiquetas.ncertificador = response[key];
              break;
            case 'lbl.seleccioncertificado':
              etiquetas.seleccioncertificado = response[key];
              break;
            case 'lbl.ncertificador2':
              etiquetas.ncertificador2 = response[key];
              break;
            case 'lbl.ecrequerido':
              etiquetas.ecrequerido = response[key];
              break;
            case 'lbl.seleccioneemisro':
              etiquetas.seleccioneemisro = response[key];
              break;
            case 'lbl.ecrequerido2':
              etiquetas.ecrequerido2 = response[key];
              break;
            case 'btn.cancelar':
              etiquetas.btnCancelar = response[key];
              break;

            case 'lbl.certificadonombreq':
              etiquetas.certificadonombreq = response[key];
              break;

            case 'lbl.ncertificadoreque':
              etiquetas.certificadoreque = response[key]
              break;




            default:

              break;
          }
        });

        return etiquetas;
      })
    );
  }

  public envioParametros(funcion: string, idEmpleado: string) {
    return new EnvioParametros(funcion, idEmpleado);
  }

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }

  agregarDocumento(userId: string, archivo: any, idDocProfecional: string) {
    const formData = new FormData();
    formData.append('userId', userId);
    formData.append('file', archivo);
    formData.append('metadata', '{}');
    formData.append('idDocProfesional', idDocProfecional);

    return this.http.post<DocumentosBobeda>
      (`${environment.BASEENPOINT_DIGITAL_VAULT}addFile`, formData, { reportProgress: true });
  }

  InicioSessionDocumentos(idEmpleado: string) {
    const formData = new FormData();
    formData.append('username', idEmpleado);
    formData.append('password', idEmpleado + environment.PASSWORD_DOCUMENTOS);

    return this.http.post<Vaultuser>(`${environment.BASEENPOINT_DIGITAL_VAULT}login`, formData, { reportProgress: true });
  }

  RecuperarDocumentosByUsuario(userId: number) {
    const formData = new FormData();
    formData.append('userId', userId.toString());

    return this.http.post<Array<Vault>>(`${environment.BASEENPOINT_DIGITAL_VAULT}files`, formData, { reportProgress: true });
  }

  RegistrarUsuarioVault(idEmpleado: string) {
    const formData = new FormData();
    formData.append('username', idEmpleado);
    formData.append('password', idEmpleado + environment.PASSWORD_DOCUMENTOS);

    return this.http.post<DocumentosBobeda>(`${environment.BASEENPOINT_DIGITAL_VAULT}addUser`, formData, { reportProgress: true });
  }

  GetDocumentoVault(userId: number, fileId: number) {
    const parametros = new HttpParams()
      .set('userId', userId.toString())
      .set('fileId', fileId.toString());

    return this.http.get(`${environment.BASEENPOINT_DIGITAL_VAULT}decryptFile`,
      { params: parametros, responseType: 'blob' });
  }

  /**
   * Actualziar los registros de datos profesionales participantes en 
   * el proceso de seleccion.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 17/03/2022.
  */
  actualizarRegistrosProceso(claveServidorPublico: string,
    lsSeleccionDatoProfesionalProcesoDTO: Array<SeleccionDatoProfesionalProcesoDTO>) {
    //Deinir URL de servicio a consumir
    const url = environment.BASEENPOINT_BUS_SEI + '/procesoEscalafonarioVigente/seleccionarProceso';
    console.log(url);
    //Generar cuerpo de parametros
    let body = JSON.stringify({ "CLAVESP": `${claveServidorPublico}`, LSPROFESIONALES: lsSeleccionDatoProfesionalProcesoDTO });
    console.table(body);
    //Ejecutar servicio
    return this.http.post<RespuestaApi<DatosProfesionales>>(`${url}`, JSON.parse(body));
  }
  //#endregion
}
