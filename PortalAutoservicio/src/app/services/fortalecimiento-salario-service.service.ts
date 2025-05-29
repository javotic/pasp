import { TabuladorBurocrataModel } from 'src/app/models/tabulador-burocrata';
import { TabuladorDocenteModel } from './../models/tabulador-docente';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UtilsService } from './utils.service';
import { environment } from 'src/environments/environment';
import { ServidorPublicoIncrementoSalarialDTO } from '../dto/servidorPublicoIncrementoSalarialDTO';
import { RespuestaGenerica } from '../repuesta/respuesta-generica';
import { CategoriaIncrementoSalarialModel } from '../models/categoria-incremento-salarial';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FortalecimientoSalarioServiceService {

  constructor(private http: HttpClient,
    private utilsService: UtilsService) {

  }

  /**
   * Obtener los datos de un servidor publico determinado para el proceso de incremento salarial.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/07/2022.
   * @param claveServidorPublico Clave del servidor publico del que se obtendra la informacion.
   * ServidorPublicoIncrementoSalarialDTO
   * @return Observable de respuesta generica de servicio de tipo 
   * ServidorPublicoIncrementoSalarialDTO.
  */
  obtenerDatosServidorPublico(claveServidorPublico: String):
    Observable<RespuestaGenerica<ServidorPublicoIncrementoSalarialDTO>> {
    const url = environment.BASEENPOINT_BUS_SEI + `/incrementoSalarial/obtenerServPubIncrSal?claveServidor=${claveServidorPublico}`;
    return this.http.get<RespuestaGenerica<ServidorPublicoIncrementoSalarialDTO>>(`${url}`);
  }

  /**
   * Obtener listado de categorias de incremento salarial.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/07/2022.
   * @return Observable de respuesta generica de servicio de tipo 
   * CategoriaIncrementoSalarialModel[].
  */
  obtenerCategoriasIncrSal(): Observable<RespuestaGenerica<CategoriaIncrementoSalarialModel[]>> {
    const url = environment.BASEENPOINT_BUS_SEI + `/incrementoSalarial/obtenerCategoriasIncrSal`;
    return this.http.get<RespuestaGenerica<CategoriaIncrementoSalarialModel[]>>(`${url}`);
  }

  /**
   * Obtener tabulador docente de acuerdo a clave de servidor publico.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 21/07/2022.
   * @param claveServidorPublico Clave del servidor publico de quien se 
   * obtendra el tabulador.
   * @return Observable de respuesta generica de servicio de tipo 
   * TabuladorDocenteModel[].
  */
  obtenerTabuladorDocIncrSal(claveServidorPublico: string):
    Observable<RespuestaGenerica<TabuladorDocenteModel[]>> {
    const url = environment.BASEENPOINT_BUS_SEI + `/incrementoSalarial/obtenerTabuladorDocIncrSal?claveServidorPublico=${claveServidorPublico}`;
    return this.http.get<RespuestaGenerica<TabuladorDocenteModel[]>>(`${url}`);
  }

  /**
   * Obtener tabulador burocrata de acuerdo a un codigo de puesto determinado.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 21/07/2022.
   * @param codigoPuesto Codigo de puesto del que se obtendra el tabulador 
   * burocrata.
   * @return Observable de respuesta generica de servicio de tipo 
   * TabuladorBurocrataModel[].
  */
  obtenerTabuladorBurIncrSal(codigoPuesto: string):
    Observable<RespuestaGenerica<TabuladorBurocrataModel[]>> {
    const url = environment.BASEENPOINT_BUS_SEI + `/incrementoSalarial/obtenerTabuladorBurIncrSal?codigoPuesto=${codigoPuesto}`;
    return this.http.get<RespuestaGenerica<TabuladorBurocrataModel[]>>(`${url}`);
  }
}

