import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { NGXLogger } from 'ngx-logger';



@Injectable({
    providedIn: 'root'
})
export class ServiceBus {

    constructor(private http: HttpClient,
        private logger: NGXLogger) { }
    /**
     * Servicio proxy para la Ws del BUS
     * @param urlBase Consulta los servicios del BUS
     * @param stringJson 
     */

    consultarServiceBus(urlWSBUS: string, bodyRequest: any) {
        this.logger.debug(`${environment.BASEENPOINT_SERVICE_BUS}/${urlWSBUS}`, bodyRequest);
        return this.http.post<RespuestaApi<any>>(`${environment.BASEENPOINT_SERVICE_BUS}/${urlWSBUS}`, bodyRequest)
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
