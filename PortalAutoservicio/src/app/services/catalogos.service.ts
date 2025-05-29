import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { environment } from 'src/environments/environment';
import { Combo } from '../models/combo';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';

@Injectable({
  providedIn: 'root'
})
export class CatalogosService {

  constructor(
    private http: HttpClient,
    private logger: NGXLogger
    ) { }

    getMunicipiosByEstado(idPais: string, idEstado: string){
      const url = `/catalogos/getMunicipiosByEstado?IDPAIS=${idPais}&IDPESTADO=${idEstado}`
      return this.http.get<RespuestaServiceM4<Combo>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
    }

}
