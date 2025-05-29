import { Injectable } from '@angular/core';
import { SECCIONESESCALAFON } from 'src/app/mocks/mock-arbol-escalafonario';
import { ArbolEscalafonario } from 'src/app/models/arbol-escalafonario';
import { SeccionesEDD } from 'src/app/models/seccionesEDD';
import { PuntajeEscalafonario } from 'src/app/models/puntaje-escalafonario';
import { HttpClient } from '@angular/common/http';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { PuestoActual } from 'src/app/models/puesto-actual';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { ArbolEscalafonarioResponse } from 'src/app/models/arbol-escalafonario-response';
import { PuntajePeriodo } from 'src/app/models/Escalafon/puntaje-periodo';
import { AntiguedadModel } from 'src/app/models/Escalafon/antiguedad-model';
import { PuntajeEscolaridad } from 'src/app/models/Escalafon/puntaje-escolaridad';
import { PuntajePrevio } from 'src/app/models/Escalafon/puntaje-previo';
import { CapacitacionCursos } from 'src/app/models/Escalafon/capacitacion-cursos';
import { CapacitacionCertificados } from 'src/app/models/Escalafon/capacitacion-certificados';
import { CapacitacionDiplomados } from 'src/app/models/Escalafon/capacitacion-diplomados';
import { InduccionCapacitacon } from 'src/app/models/Escalafon/induccion-capacitacion';
import { RecepcionDocumentos } from 'src/app/models/Escalafon/recepcion-documentos';


@Injectable({
  providedIn: 'root'
})
export class ProcesoescalafonarioService {

  constructor(private http: HttpClient) { }


  getArbolEscalafonario(idServidorPublico: string) {
     let url='/informacionActualEscalafon/escalafonArbol';
     return this.http.get<RespuestaApi<ArbolEscalafonarioResponse>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}`);
  }

  getPuestoActual(idServidorPublico: string) {
    let url = '/informacionActualEscalafon/escalafonPuestoActual';
    return this.http.get<RespuestaApi<PuestoActual>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}`);
  }

  getSecciones(): SeccionesEDD[] {
    return SECCIONESESCALAFON;
  }

  getAPuntajeEscalafonario(idServidorPublico: string, idProcesoVigente) {
     let url='/informacionActualEscalafon/escalafonPuntajePrevio';
    return this.http.get<RespuestaApi<PuntajeEscalafonario>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }


  getEscalafonEficiencia(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonEficiencia';
    return this.http.get<RespuestaApi<PuntajePeriodo>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }
  
  getEscalafonDemeritos(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonDemeritos';
    return this.http.get<RespuestaApi<PuntajePeriodo>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonDemeritosTotales(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonDemeritosTotales';
    return this.http.get<RespuestaApi<PuntajePeriodo>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonAntiguedad(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonAntiguedad';
    return this.http.get<RespuestaApi<AntiguedadModel>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonEscolaridad(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonEscolaridad';
    return this.http.get<RespuestaApi<PuntajeEscolaridad>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonCapacInduccion(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonCapacInduccion';
    return this.http.get<RespuestaApi<InduccionCapacitacon>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonCapacCursos(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonCapacCursos';
    return this.http.get<RespuestaApi<CapacitacionCursos>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonCapacCertifComp(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonCapacCertifComp';
    return this.http.get<RespuestaApi<CapacitacionCertificados>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonDiplomados(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonDiplomados';
    return this.http.get<RespuestaApi<CapacitacionDiplomados>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getEscalafonDatosRecepDoc(idServidorPublico: string, idProcesoVigente: string){
    let url='/escalafonGeneral/escalafonDatosRecepDoc';
    return this.http.get<RespuestaApi<RecepcionDocumentos>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  

  
  
}
