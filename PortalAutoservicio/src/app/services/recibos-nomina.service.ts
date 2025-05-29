import { Injectable } from '@angular/core';
import { ArchivosNomina } from '../models/archivos-nomina';
import { ARCHIVOSNOMINA } from '../mocks/mock-archivosNomina';
import { environment } from 'src/environments/environment';
import { IncidenciasTiempo } from '../models/incidencias-tiempo';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ServidorComprobanteDTO, ServidorComprobanteRecibo } from '../models/servidor-comprobante-recibo';

@Injectable({
  providedIn: 'root'
})
export class RecibosNominaService {

  constructor(
    private http: HttpClient
  ) { }

  getRecibosNomina (): ArchivosNomina[]{
    return ARCHIVOSNOMINA;
  }

  consultarComprobantes(fechainicio: string, fechafin: string, claveServidor: string, nombreServidor: string, idServidorSesion: string, individual: string){
    const url = `/comprobantes/consultarComprobantes?fechainicio=${fechainicio}&fechafin=${fechafin}&claveServidor=${claveServidor}&nombreServidor=${nombreServidor}&idServidorSesion=${idServidorSesion}&individual=${individual}`
    return this.http.get<RespuestaServiceM4<ArchivosNomina>>(`${environment.BASEENPOINT_HNOMINA}${url}`);
  }


  consultarConstanciasAnuales(fechainicio: string, fechafin: string, claveServidor: string, nombreServidor: string, idServidorSesion: string, individual: string){
    const url = `/constanciaAnual/consultarConstanciasAnuales?fechainicio=${fechainicio}&fechafin=${fechafin}&claveServidor=${claveServidor}&nombreServidor=${nombreServidor}&idServidorSesion=${idServidorSesion}&individual=${individual}`
    return this.http.get<RespuestaServiceM4<ArchivosNomina>>(`${environment.BASEENPOINT_HNOMINA}${url}`);
  }


  generarComprobantes(comprobantes: ServidorComprobanteDTO[]) {
    let url = `comprobantes/generarComprobantes`
    let body = JSON.stringify({LSTSERVIDORCOMPROBANTE: comprobantes});
    console.log('body:' + body);
    let mensajeEnvio = JSON.parse(body);

    return this.http.post(`${environment.BASEENPOINT_HNOMINA}${url}`,mensajeEnvio, { responseType: 'blob' });
  }


}
