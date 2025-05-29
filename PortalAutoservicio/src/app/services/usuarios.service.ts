import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Combo, ComboDependencia } from '../models/combo';
import { CatRoles } from '../models/Usuarios/cat-roles';
import { CatUsuarios } from '../models/Usuarios/cat-usuarios';
import { ConfigProrroga } from '../models/Usuarios/config-prorroga';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(
    private http: HttpClient,
    private logger: NGXLogger
  ) { }

  getUsuariosByParam(clave: string, nombre: string) {
    const url = `${environment.BASEENPOINT_BUS_SEI}/usuarios/getUsuariosByParam?clave=${clave}&nombre=${nombre}`
    return this.http.get<RespuestaServiceM4<CatUsuarios>>(url);
  }

  UpdInsRolUsuario(idServidorPublico: string, rolNew: number, boActivo: string, dependencias: string, idAdmin: string) {
    let formData = new FormData();
    formData.append('idServidorPublico', idServidorPublico);
    formData.append('rolNew', rolNew.toString());
    formData.append('boActivo', boActivo);
    formData.append('dependencias', dependencias);
    formData.append('idAdmin', idAdmin);

    console.log('Encabezados:' + JSON.stringify(formData));

    console.log('idServidorPublico:' + idServidorPublico);
    console.log('rolNew:' + rolNew);
    console.log('boActivo:' + boActivo);
    console.log('dependencias:' + dependencias);
    console.log('idAdmin:' + idAdmin);

    const url = `${environment.BASEENPOINT_BUS_SEI}/usuarios/UpdInsRolUsuario`;
    return this.http.post<RespuestaServiceM4<any>>(url, formData);
  }

  getRoles() {
    const url = `${environment.BASEENPOINT_BUS_SEI}/usuarios/getRoles`
    return this.http.get<RespuestaServiceM4<CatRoles>>(url);
  }

  adminConfigProrroga(claveParametro: string, noDias: string, dsusuario: string) {
    let numeroDias = (noDias == '' ? 0 : noDias);
    const url = `/usuarios/getAdminProrroga?claveParametro=${claveParametro}&noDias=${numeroDias}&dsusuario=${dsusuario}`
    return this.http.get<RespuestaServiceM4<ConfigProrroga>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  getUnidades(unidad: string, seccion: string) {
    const url = `/usuarios/getUnidades?unidad=${unidad}&seccion=${seccion}`
    return this.http.get<RespuestaServiceM4<ComboDependencia>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  getUsrUnidadesAsig(idServidorPublico: string) {
    const url = `/usuarios/consultarUsrUnidadesAsig?idServidorPublico=${idServidorPublico}`
    return this.http.get<RespuestaServiceM4<Combo>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  //#region Funciones publicas
  /**
  * Obtener listado de usuarios subordinados de un servidor público en
  * particular.
  *
  * @author Javier Alvarado Rodriguez.
  * @version 1.0, 14/01/2022.
  * @param claveSPBuscar Clave del servidor publico del que se desea buscar 
  * la informacion.
  * @param nombreBuscar Nombre del servidor publico del que se desea buscar 
  * la informacion.
  * @param claveSPSupervisor Clave del servidor publico que realiza la busqueda.
  * @return Lista de servidores publicos concordantes con los filtos 
  * de informacion.
  * @returns Observable de respuesta principal de servicio de tipo CatUsuarios.
  */
  obtenerServidoresSubordinados(claveSPBuscar: String, nombreBuscar: String,
    claveSPSupervisor: String): Observable<RespuestaServiceM4<CatUsuarios>> {
    const url = `${environment.BASEENPOINT_BUS_SEI}/usuarios/obtenerServidoresSubordinados?claveSPBuscar=${claveSPBuscar}&nombreBuscar=${nombreBuscar}&claveSPSupervisor=${claveSPSupervisor}`
    return this.http.get<RespuestaServiceM4<CatUsuarios>>(url);
  }
  //#endregion


  /*
      };
      let body = JSON.stringify({ request: data });
      return this.http.post<RespuestaApi<DatosProfesionales>>(`${environment.BASEENPOINT_SERVICE_BUS}/frwsr_LPSAUT_SERV${environment.HEDER_WS}.php`,
      data);
  */

}
