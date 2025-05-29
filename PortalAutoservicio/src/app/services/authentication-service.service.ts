import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ConsultaDatosPersonales } from '../models/consulta-datos-personales';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, first } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NGXLogger } from 'ngx-logger';
import { RequestConsultadatospersonales } from '../models/request-consutadatospersonales';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {
  private currentUserSubject: BehaviorSubject<ConsultaDatosPersonales>;
  public currentUser: Observable<ConsultaDatosPersonales>;
  private password: String = 's3IFsvpuMVrxIfIQENJ5';
  private algoritmo: String = 'AES';
  urlDat: string = `frwsr_LPSAUT_CONS_DAT${environment.HEDER_WS}.php`;

  constructor(private http: HttpClient,
    private logger: NGXLogger
  ) {
    this.currentUserSubject = new BehaviorSubject<ConsultaDatosPersonales>(JSON.parse(sessionStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): ConsultaDatosPersonales {
    return this.currentUserSubject.value;
  }
  /**
   * metodo para desencriptar la sesión
   * @param clave 
   */
  login(clave: String) {
    const url = `${environment.BASEENPOINT_ENCRIPTDECRIPT}/contenidodesencriptar/${clave}/password/${this.password}/algoritmo/${this.algoritmo}`;
    return this.http.get(url, { responseType: 'text' });
  }
  /**
   * Valida que el usuario exista en BD
   * @param idEmpleado 
   */
  validUser(idEmpleado: String) {
    let requestDatosp: RequestConsultadatospersonales = new RequestConsultadatospersonales();
    requestDatosp.funcion = "CONSULTADATOSPERSONALES";
    requestDatosp.IDEMPLEADO = idEmpleado + '';
    this.logger.debug(`${environment.BASEENPOINT_BUS_SEI}/servidorpublico/datospersonales/idServidorPublico/${idEmpleado}/nombre//apellidoPat//apellidoMat/`);
    return this.http.get
      (`${environment.BASEENPOINT_BUS_SEI}/servidorpublico/datospersonales/idServidorPublico/${idEmpleado}/nombre/-/apellidoPat/-/apellidoMat/-`);

  }

  /**
   * cierra sesión
   */
  logout() {
    this.logger.log('Cerrando sesion service');
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    window.open(environment.URL_SIGAP, "_self");
    this.logger.debug('no hay datos de sesion..')
  }


  limpiarSession() {
    this.logger.log('Limpiando sesion service');
    sessionStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }



  /**
   * encabezados para WS
   */
  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }

  public asignarUsuario(myJSON: string) {
    this.currentUserSubject.next(<ConsultaDatosPersonales>JSON.parse(myJSON));
  }

}
