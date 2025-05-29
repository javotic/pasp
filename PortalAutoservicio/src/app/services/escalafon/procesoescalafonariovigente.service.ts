import { Injectable } from '@angular/core';
import { ESCALAFONARIOVIGENTE } from 'src/app/mocks/mock-concurso-escalafonario-vigente';
import { ConcursoEscalafonarioVigente } from 'src/app/models/concurso-escalafonario-vigente';
import { ProcesoEscalafonarioVigente } from 'src/app/models/proceso-escalafonario-vigente';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { InscripcionDTO } from 'src/app/dto/inscripcionDTO';
import { AplicaProcesoEscalafonario } from 'src/app/models/aplica-proceso-escalafonario';
import { SesionesAsesoria } from 'src/app/models/sesiones-asesoria';
import { EscalafonEstadoInscripcion } from 'src/app/models/escalafon-estado-inscripcion';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { RespuestaInscripcion } from 'src/app/repuesta/respuesta-incripcion';
import { SesionExamen } from 'src/app/models/sesion-examen';
import { DescargaGuia } from 'src/app/models/descarga-guia';

@Injectable({
  providedIn: 'root'
})
export class ProcesoescalafonariovigenteService {

  constructor(private http: HttpClient) { }

  getArbolEscalafonarioVigente(): ConcursoEscalafonarioVigente[] {
    return ESCALAFONARIOVIGENTE;
  }


  getArbolPlazasParticipacion(idServidorPublico: string, todasPlazas: string) {
      let url='/procesoEscalafonarioVigente/escalafonPlazasDisponibles';
      return this.http.get<RespuestaApi<ConcursoEscalafonarioVigente>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}/${todasPlazas}`);
  }

  getProcesoEscalafonarioVigente() {
    let url = '/procesoEscalafonarioVigente/escalafonProcesoVigente';
    return this.http.get<RespuestaApi<ProcesoEscalafonarioVigente>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  inscripcion(idServidorPublico: string, idPlaza: string, idProcesoVigente: string) {
    let inscripcion = new InscripcionDTO();
    inscripcion.idServidorPublico = idServidorPublico;
    inscripcion.idPlaza = idPlaza;
    inscripcion.idProcesoVigente = idProcesoVigente;
    let url = '/procesoEscalafonarioVigente/inscripcion';
    return this.http.post<RespuestaInscripcion>(`${environment.BASEENPOINT_BUS_SEI}${url}`, inscripcion);

  }

  getAplicaProcesoEscalafonario(idServidorPublico: string) {
     let url = '/procesoEscalafonarioVigente/escalafonAplicaProcesoVigente';
      return this.http.get<RespuestaApi<AplicaProcesoEscalafonario>>
      (`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}`);
  }

  getEscalafonSesionesAsesoria(idProcesoVigente: string) {
     let url = '/procesoEscalafonarioVigente/escalafonSesionesAsesoria';
     return this.http.get<RespuestaApi<SesionesAsesoria>>
       (`${environment.BASEENPOINT_BUS_SEI}${url}?idProcesoVigente=${idProcesoVigente}`);
  }


  getEscalafonEstadoInscripcion(idServidorPublico: string,idProcesoVigente: string) {
    let url = '/procesoEscalafonarioVigente/escalafonEstadoInscripcion';
     return this.http.get<RespuestaApi<EscalafonEstadoInscripcion>>
       (`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`);
  }

  getDescargaCartaAceptacion(idServidorPublico: string, idPlaza: string, idProcesoVigente: string) {
    let url = 'getReporteCartaAceptacion/generarReporte';

    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}${url}/${idServidorPublico}?idProcesoVigente=${idProcesoVigente}`, {
      responseType: 'blob'
    });

  }

  getDescargaCartaAceptacionUsuario(
    idServidorPublico: string, idProcesoVigente: string, aceptoCarta: boolean, idPlaza: string, lugarProceso: string,
    rankingDictamen: string
    ) {

      let actualizar = false;

      if (lugarProceso === 'FAVORABLE') {
        actualizar = true;
      }

      lugarProceso = rankingDictamen + '° LUGAR';

      const url = environment.BASEENPOINT_BUS_SEI_REPORTES + 'getReporteCartaAceptacion/generarReporteUsuario/'
      + idServidorPublico + '/' + aceptoCarta + '/' + actualizar + '/' + idPlaza;

      return this.http.get(
        `${url}?idProcesoVigente=${idProcesoVigente}&lugarProceso=${lugarProceso}`,
      {
        responseType: 'blob'
      });

  }


  getDescargaConstanciaParticipacion(idServidorPublico: string, idPlaza: string, idProcesoVigente: string) {
    let url = 'reportesConstancia/generarReporte';

    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}${url}/${idServidorPublico}/${idPlaza}?idProcesoVigente=${idProcesoVigente}`, {
      responseType: 'blob'
    });

  }

  getDescargarGuiaEstudio(idpuesto: string) {
    let url = '/descarga_archivos/GuiaEstudio';
    return this.http.get(`${environment.BASEENPOINT_BUS_SEI}${url}/${idpuesto}`, {
      responseType: 'blob'
    });
 }


  getDescargaConstanciaRecepcionDocumentos(idServidorPublico: string, idPlaza: string ) {
    let url = 'reporteRepecionDocumentos/generarReporte';
    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}${url}/${idServidorPublico}/${idPlaza}`, {
      responseType: 'blob'
    });

  }

  getDescargaConvocatoria(claveprocesosend: string, idServidorPublico: string) {
    //const parametros = claveprocesosend.split('/').join('|');
    return this.http.get
    (`${environment.BASEENPOINT_BUS_SEI}/procesoEscalafonarioVigente/descargaConvocatoria?claveproceso=${claveprocesosend}&idServidorPublico=${idServidorPublico}`, {
      //responseType: 'blob'
      responseType: 'arraybuffer'
    });
  }


  getEscalafonSesionesExamen(idProcesoVigente: string) {
     const url = '/procesoEscalafonarioVigente/escalafonSesionesExamen';
     return this.http.get<RespuestaApi<SesionExamen>>
       (`${environment.BASEENPOINT_BUS_SEI}${url}?idProcesoVigente=${idProcesoVigente}`);

  }


  escalafonActualizaInfo() {
    let url = '/procesoEscalafonarioVigente/escalafonActualizaInfo';
    return this.http.get<RespuestaInscripcion>(`${environment.BASEENPOINT_BUS_SEI}${url}`,);

  }

}
