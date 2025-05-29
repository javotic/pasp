import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NGXLogger } from 'ngx-logger';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ConsultaDatosServidorPublico } from '../models/consulta-datos-servidor-publico';
import { ConsultaDatosPersonales } from '../models/consulta-datos-personales';

@Injectable({
  providedIn: 'root'
})
export class SidebarService {

  constructor(private http: HttpClient,
    private logger: NGXLogger) { }

  consultarDatosServidor(idServidorPublico: string) {
    return this.http.get<RespuestaServiceM4<ConsultaDatosServidorPublico>>(`${environment.BASEENPOINT_BUS_SEI}/servidorpublico/datosservidorpublico/idServidorPublico/${idServidorPublico}`)
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

  consultarDatosPersonales(idServidorPublico: string, nombre: string, apellidoPat: string, apellidoMat: string) {
    return this.http.get<RespuestaServiceM4<ConsultaDatosPersonales>>(`${environment.BASEENPOINT_BUS_SEI}/servidorpublico/datospersonales/idServidorPublico/${idServidorPublico}/nombre/${nombre}/apellidoPat/${apellidoPat}/apellidoMat/${apellidoMat}`)
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
}
