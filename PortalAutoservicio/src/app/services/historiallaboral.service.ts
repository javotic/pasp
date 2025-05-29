import { Injectable } from '@angular/core';
import { ConstanciaHistorialLaboral } from '../models/constancia-historial-laboral';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { FuncionSolicitudCostanciaDTO } from '../dto/funcionSolicitudConstanciaDTO';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { UtilsService } from './utils.service';
import { map } from 'rxjs/operators';
import { HistorialLaboralEtiquetas } from '../etiquetas/historial-laboral-etiquetas';

@Injectable({
  providedIn: 'root'
})
export class HistoriallaboralService {

  urlDat6: string = `/frwsr_LPSAUT_CONS.php`;

  constructor(private http : HttpClient, private utilsService: UtilsService) { }


  obtenerHistoralLaboral(idEmpleado: string) {
    const formData = new FormData();
    formData.append('IDSERVIDORPUBLICO', idEmpleado);

    const url = `${environment.BASEENPOINT_BUS_SEI}/historialLaboral/cargarSolicitudesHistoricoLaboral`;
    return this.http.post<RespuestaApi<ConstanciaHistorialLaboral>>(`${url}`, formData)
      .pipe(
        catchError(e => {
          if (e.status === 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );
  }

  consultaSolicitudHistorialLaboral(idServidorPublico:string,
    idEstatus:number, idTipoSolicitud :number){
      return this.http.get<FuncionSolicitudCostanciaDTO[]>(`${environment.BASEENPOINT_AUTOSERVICIO}/solicitudConstancia/consultaSolicitudConstancia/${idServidorPublico}/${idEstatus}/${idTipoSolicitud}`);
  }


  insertaSolicituHistorialLaboral(idEmpleado: string,
    justificacion: string) {
      

    let data = {
      "funcion": "solicitarHistorialLaboral",
      "IDSERVIDORPUBLICO": idEmpleado,
      "JUSTIFICACION": justificacion
    };

    let body = JSON.stringify({ request: data });
    console.log(body);
    return this.http.post<RespuestaApi<ConstanciaHistorialLaboral>>(`${environment.BASEENPOINT_SERVICE_BUS}/frwsr_LPSAUT_SERV${environment.HEDER_WS}.php`, data)
      .pipe(
        catchError(e => {
          console.error(e);
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );
  }


  getEtiquetasDatosProfesionales(): Observable<any> {
    return this.utilsService.ObtenerEtiquetasPagina('/historialLaboral', 'español (México)').pipe(

      map((response: any) => {
        let etiquetas = new HistorialLaboralEtiquetas();
        Object.keys(response).map((key) => {

          switch (key) {
            case 'btn.solicitar':
              etiquetas.btnsolicitar = response[key];
              break;
            case 'lbl.justificacion':
              etiquetas.justificacion = response[key];
              break;
            case 'col.estatus':
              etiquetas.estatus = response[key];
              break;
            case 'col.fecha':
              etiquetas.fecha = response[key];
              break;
            case 'lbl.cveservidorpublico':
              etiquetas.cveservidorpublico = response[key];
              break;
            case 'btn.nuevasolicituf':
              etiquetas.nuevasolicituf = response[key];

              break;
            case 'col.gestoradministrativo':
              etiquetas.gestoradministrativo = response[key];
              break;
            case 'err.justificacion':
              etiquetas.justificacion2 = response[key];
              break;

            case 'ttl.tituloHistorialLaboral':
              etiquetas.titulo = response[key];
              break;

            case 'lbl.servidorpublico':
              etiquetas.servidorpublico = response[key];
              break;
            case 'col.archivo':
              etiquetas.archivo = response[key];
              break;
            case 'btn.cancelar':
              etiquetas.cancelar = response[key];
              break;
            case 'lbl.plaza':
              etiquetas.plaza = response[key];
              break;
            case 'lbl.fecha':
              etiquetas.fecha2 = response[key];
              break;

            default:

              break;
          }
        });

        return etiquetas;
      })
    );
  }

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }
}
