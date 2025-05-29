import { Injectable } from '@angular/core';
import { MisEdd } from '../models/misedd';
import { MISEVALDMOCK } from '../mocks/mock-misedd';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MisEvaluacionesDespService {

  constructor(private http: HttpClient) { }


  getMisEvaluaciones(servidorPublico: string) {

    let data = {
      "funcion": "consultarMisEvaluaciones",
      "IDSERVIDORPUBLICO": servidorPublico
    };
    let body = JSON.stringify({ request: data });
    return this.http.get<RespuestaApi<MisEdd>>(`${environment.BASEENPOINT_BUS_SEI}/misEDD/idServidorPublico/${servidorPublico}`)
    //return this.http.post<RespuestaApi<MisEdd>>(`${environment.HOST}/frwsr_LPSAUT_CONS_CAT.php`, body, { headers: this.agregarAuthorizationHeader() })
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

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }
}
