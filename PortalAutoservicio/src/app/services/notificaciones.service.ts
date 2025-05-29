import { Injectable } from '@angular/core';
import { CatalogoNotificaciones } from '../models/notificaciones';
import { NOTIFICACIONESMOCK } from '../mocks/mock-notificaciones';
import { environment } from 'src/environments/environment';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject, forkJoin, EMPTY } from 'rxjs';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';
import { UtilsService } from './utils.service';
import { DtNotificaciones } from './../modules/dt-notificaciones';
import { Notificaciones } from './../models/notifiaciones';
import { NotificacionesDropDown } from './../models/notificaciones-drop-down';

@Injectable({
  providedIn: 'root'
})
export class NotificacionesService {
  private notificacionesList: NotificacionesDropDown[] = [];
  private notDropDown = new BehaviorSubject<NotificacionesDropDown[]>([]);
  notDropDown$ = this.notDropDown.asObservable();

  urlDat: string = `frwsr_LPSAUT_CONS_DAT${environment.HEDER_WS}.php`;

  constructor(
    private http: HttpClient,
    private utilsService: UtilsService
  ) { }

  getNotificaciones(): CatalogoNotificaciones[] {
    return NOTIFICACIONESMOCK;
  }

  /**
   * Obtiene las notificaciones del servidor publico
   * que estan registradas en el SEI
   * @param clvServidorP 
   */
  getNotificacionesBus(clvServidorP: string) {
    let data = {
      "funcion": "obtenerNotificaciones",
      "IDSERVIDORPUBLICO": clvServidorP
    };
    return this.http.get<RespuestaServiceM4<any>>(`${environment.BASEENPOINT_BUS_SEI}/notificaciones/idServidorPublico/${clvServidorP}`,)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error('Error en getNotificacionesBus', e)
          return throwError(e);
        })
      );
  }

  /**
 * Obtiene las notificaciones del bus y las notificaciones registradas en el portal
 * compara para marcar las leidas y no mostrar las vencidas
 */
  obtenerNotificacionesPortal(idServidorPublico: string) {
    this.getNotificacionesBus(idServidorPublico).subscribe(notif => {
      this.notificacionesList = [];
      let notificacionesBUS: Notificaciones[] = notif.response;

      for (let notificacion of notificacionesBUS) {
        let notThree: NotificacionesDropDown = new NotificacionesDropDown();
        notThree.descNotificacion = notificacion.DESCRIPCION;
        notThree.backgroundNotificacion = 'oscuro';
        notThree.fechaNotificacion = notificacion.DESCRIPCION;
        notThree.idNotificacionPortal = '0';
        notThree.idTipoNotificacion = notificacion.TIPONOFICIACION;
        notThree.urlNotificacion = this.urlByTipoNotificacion(notificacion.TIPONOFICIACION);
        notThree.idnotificacionbus = notificacion.IDPROCESOVIGENTE
        this.notificacionesList = [...this.notificacionesList, notThree];
      }
      this.notDropDown.next(null);
      this.notDropDown.next(this.notificacionesList);

    }, error => {
      console.error('Error en obtenerNotificacionesPortal -> ', error);
    });
  }


  /**
   * obtiene la URL de navegación de la notificación
   * @param tipo 
   */
  urlByTipoNotificacion(tipo: string): string {
    let url: string = '';
    switch (tipo) {
      case '1':
        url = '/evaluacionDesempeno';
        break;
      case '2':
        url = '/consultaKPI';
        break;
      case '3':
        url = '/escalafon';
        break;
      case '4':
        url = '/escalafon';
        break;
      case '5':
        url = '/misEdd';
        break;
      case '6':
        url = '/historialLaboral';
        break;
      case '7':
        url = '/constanciaNoAdeudo';
        break;
      case '8':
        url = '/evaluacionDesempeno';
        break;
    }
    return url;
  }

}
