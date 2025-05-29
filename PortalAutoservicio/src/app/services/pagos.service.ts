import { Injectable } from '@angular/core';
import { Pagos } from '../models/pagos';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { UtilsService } from './utils.service';
import { ConsultaPagosEtiquetas } from '../etiquetas/ConsultaPagoEtiquetas';
import { Observable } from 'rxjs';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';
import { DatosBancarios } from '../models/datos-bancarios';
import { ServidorComprobanteDTO } from '../models/servidor-comprobante-recibo';

@Injectable({
  providedIn: 'root'
})
export class PagosService {

  url: string = '/frwsr_LPSAUT_CONS.php';
  constructor(private http: HttpClient,
    private utilsService: UtilsService) { }

  getPagos(idEmpleado: string, fechaInicio: string, fechaFin) {
    const formData = new FormData();
    formData.append('IDSERVIDORPUBLICO', idEmpleado);
    formData.append('FECHAINICIO', fechaInicio);
    formData.append('FECHAFIN', fechaFin);

    const url = `${environment.BASEENPOINT_BUS_SEI}/catalogos/consultarListadoPagos`;
    return this.http.post<RespuestaApi<Pagos>>(`${url}`, formData);
  }

  getEtiquetasPagos(): Observable<any> {
    return this.utilsService.ObtenerEtiquetasPagina('/consultaPagos', 'español (México)').pipe(

      map((response: any) => {
        let consultaPagosEtiquetas = new ConsultaPagosEtiquetas();
        Object.keys(response).map((key) => {

          switch (key) {
            case 'ttl.tituloconsultapagos':
              consultaPagosEtiquetas.tituloconsultapagos = response[key];
              break;
            case 'btn.buscar':
              consultaPagosEtiquetas.botonBuscar = response[key];
              break;
            case 'lbl.tooltipfechas':
              consultaPagosEtiquetas.tooltipfechas = response[key];
              break;
            case 'lbl.filtrofechas':
              consultaPagosEtiquetas.filtrofechas = response[key];
              break;
            case 'lbl.fechainicio':
              consultaPagosEtiquetas.fechainicio = response[key];
              break;
            case 'lbl.fechafin':
              consultaPagosEtiquetas.fechafin = response[key];

              break;
            case 'lbl.claveservidorpublico':
              consultaPagosEtiquetas.claveservidorpublico = response[key];
              break;
            case 'lbl.formapago':
              consultaPagosEtiquetas.formapago = response[key];
              break;

            case 'lbl.estatuspago':
              consultaPagosEtiquetas.statusPago = response[key];
              break;

            case 'lbl.tipopago':
              consultaPagosEtiquetas.tipoPago = response[key];
              break;
            case 'lbl.fechapago':
              consultaPagosEtiquetas.fechaPago = response[key];
              break;
            case 'lbl.importePagoGravado':
              consultaPagosEtiquetas.importePagoGravado = response[key];
              break;

            case 'lbl.importePagoExento':
              consultaPagosEtiquetas.importePagoExento = response[key];
              break;

            case 'lbl.noseencontraron':
              consultaPagosEtiquetas.noseencontraron = response[key];
              break;

            case 'lbl.finicioffin':
              consultaPagosEtiquetas.finicioffin = response[key];
              break;

            case 'lbl.finicioreq':
              consultaPagosEtiquetas.finicioreq = response[key];
              break;
          
              case 'lbl.fechafinreq':
                consultaPagosEtiquetas.fechafinreq = response[key];
                break;

            default:

              break;
          }
        });

        return consultaPagosEtiquetas;
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

  consultarDatosBancarios(idServidorPublico: string){
    const url = `/pagos/consultarDatosBancarios?idServidorPublico=${idServidorPublico}`
    return this.http.get<RespuestaServiceM4<DatosBancarios>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  generarPagoQuincenal(comprobantes: ServidorComprobanteDTO[]) {
    let url = `consultaPagos/generarPagoQuincenal`
    let body = JSON.stringify({LSTSERVIDORCOMPROBANTE: comprobantes});
    console.log('body:' + body);
    let mensajeEnvio = JSON.parse(body);

    return this.http.post(`${environment.BASEENPOINT_HNOMINA}${url}`,mensajeEnvio, { responseType: 'blob' });
  }


  generarConstanciaAnual(comprobantes: ServidorComprobanteDTO[]) {
    let url = `constanciaAnual/generarConstanciaAnual`
     let body = JSON.stringify({LSTSERVIDORCOMPROBANTE: comprobantes});
     console.log('body:' + body);
     let mensajeEnvio = JSON.parse(body);
 
     return this.http.post(`${environment.BASEENPOINT_HNOMINA}${url}`,mensajeEnvio, { responseType: 'blob' });
   }

}
