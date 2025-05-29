import { Injectable } from '@angular/core';
import { ConstanciaAdeudo } from '../models/constancia-adeudo';
import { CONSTANCIAADEUDO } from '../mocks/mock-constancia-adeudo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { throwError } from 'rxjs';
import { CatalogoNotificaciones } from '../models/notificaciones';
import { NGXLogger } from 'ngx-logger';
import { DtNotificaciones } from '../modules/dt-notificaciones';
import { NotificacionesDropDown } from '../models/notificaciones-drop-down';
import { totalNotificaciones } from '../models/total-notificaciones';

@Injectable({
  providedIn: 'root'
})


export class UtilsService {
  urlCat: string = `${environment.HOST}/frwsr_LPSAUT_CONS_CAT.php`;
  urlDat: string = `${environment.HOST}/frwsr_LPSAUT_CONS_DAT.php`;
  estados: CatalogoNotificaciones[];

  constructor(private http: HttpClient,
    private logger: NGXLogger
  ) { }

  ObtenerEtiquetasPagina(urlen: string, idioma: string) {
    let data = {
      "url": urlen,
      "idioma": idioma
    };

    return this.http.post<any>(`${environment.BASEENPOINT_ETIQUETAS}`, data)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );;
  }

  obtenerNotificaciones(cveservidor: string) {

    this.logger.debug('Dentro del MicroService UtilsService-obtenerNotificaciones');
    this.logger.debug(cveservidor);



    return this.http.get<any[]>(`${environment.BASEENPOINT_NOTIFICACIONES}/clave/${cveservidor}`,)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );;

  }

  cancelarNotificaciones(llnotifi: string) {

    this.logger.debug('Dentro del MicroService UtilsService-cancelarNotificaciones');
    this.logger.debug('Notificacion a Cancelar ' + llnotifi);


    return this.http.post(`${environment.BASEENPOINT_CANCELAR_NOTIFICACIONES}`, llnotifi)
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

  guargarNotificaciones(notificacion: NotificacionesDropDown[], sp: string, estatus: boolean) {
    let lstNot: totalNotificaciones = new totalNotificaciones();
    lstNot.lstNotificaciones = [];
    let lstData: any[] = [];
    for (let x of notificacion) {

      let llnot: DtNotificaciones = new DtNotificaciones();
      llnot.llnotificaciones = x.idNotificacionPortal;
      llnot.cveservidorpublico = sp;
      llnot.estatusnotificacion = estatus;
      //llnot.fechanotificacion = x.fechaNotificacion;
      llnot.fechanotificacion = '';
      llnot.lltiponotificacion = x.idTipoNotificacion;
      llnot.idnotificacionbus = x.idnotificacionbus;
      lstNot.lstNotificaciones.push(llnot);


      //lstData.push(data);
    }





    // let body = JSON.stringify({ lstNotificaciones: lstData });
    //this.logger.debug('BODY=>', body);
    environment.BASEENPOINT_NOTIFICACIONES
    this.logger.debug('URL=>', `${environment.BASEENPOINT_NOTIFICACIONES}/notificacionesPortal`, lstNot)
    return this.http.post(`${environment.BASEENPOINT_NOTIFICACIONES}/notificacionesPortal`, lstNot)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            this.logger.error('Errro1=>', e)
            return throwError(e);
          }
          this.logger.error("Error=>", e)
          return throwError(e);
        })
      );

  }

  public agregarAuthorizationHeader() {

    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return httpHeaders;


  }
}
