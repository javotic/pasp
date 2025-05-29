import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Servicio de gestion de archivos de FUMP.
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 27/20/2021.
*/
@Injectable({
  providedIn: 'root'
})
export class FileService {

  //#region Atributos de clase
  // private server = 'http://localhost:8089';
  private server = 'http://10.10.48.2:9090';
  //#endregion

  //#region Constructor
  constructor(private http: HttpClient) { }
  //#endregion

  //#region Funciones publicas
  /**
   * Descargar Formato Unico de Movimiento de Personal a traves de su 
   * nombre en el repositorio documental.
   * 
   * @auhtor Javier Alvarado Rodriguez.
   * @version 1.0, 27/10/2021.
   * @param nombreArchivo Nombre del archivo fisico a descargar
   * @return Observable con el documento obtenido desde servidor.
  */
  descargarFumpPorNombreArchivo(nombreArchivo: String): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.server}/fump/descargar/${nombreArchivo}`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }
  //#endregion
}
