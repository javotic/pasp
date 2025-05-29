import { Injectable } from '@angular/core';
import { ConstanciaAdeudo } from '../models/constancia-adeudo';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConstanciaadeudoService {

  constructor(
    private http: HttpClient
    ) {}

  obtenerConstanciaNoAdeudo(idEmpleado: string) {
    const formData = new FormData();
    formData.append('IDSERVIDORPUBLICO', idEmpleado);

    const url = environment.BASEENPOINT_BUS_SEI + '/consultas/consultarHistoricoConstanciaNoAdeudo';
    return this.http.post<RespuestaApi<ConstanciaAdeudo>>(`${url}`, formData);
  }

  insertaSolicitudConstanciaNoAdeudo(idEmpleado: string,
    justificacion: string) {
      

    let data = {
      "funcion": "solicitarConstanciaNoAdeudo",
      "IDSERVIDORPUBLICO": idEmpleado,
      "JUSTIFICACION": justificacion
    };

    let body = JSON.stringify({ request: data });
    console.log(body);
    return this.http.post<RespuestaApi<ConstanciaAdeudo>>(`${environment.BASEENPOINT_SERVICE_BUS}/frwsr_LPSAUT_SERV${environment.HEDER_WS}.php`,data);
  }

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }
}
