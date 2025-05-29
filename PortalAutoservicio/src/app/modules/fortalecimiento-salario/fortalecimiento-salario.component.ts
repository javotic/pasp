import { ServidorPublicoIncrementoSalarialDTO } from './../../dto/servidorPublicoIncrementoSalarialDTO';
import { Component, OnInit, Input } from '@angular/core';
import { interval } from 'rxjs/internal/observable/interval';
import { map } from 'rxjs/internal/operators/map';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { FortalecimientoSalarioServiceService } from 'src/app/services/fortalecimiento-salario-service.service';
import { TabuladorDocenteModel } from 'src/app/models/tabulador-docente';
import { TabuladorBurocrataModel } from 'src/app/models/tabulador-burocrata';
import { CategoriaIncrementoSalarialModel } from 'src/app/models/categoria-incremento-salarial';

@Component({
  selector: 'app-fortalecimiento-salario',
  templateUrl: './fortalecimiento-salario.component.html',
  styleUrls: ['./fortalecimiento-salario.component.css']
})
export class FortalecimientoSalarioComponent implements OnInit {
  //#region Propiedades globales
  servidorPublicoIncrementoSalarialDTO: ServidorPublicoIncrementoSalarialDTO = {
    claveServidorPublico: '',
    nombreCompletoServidorPublico: '',
    nombrePuesto: '',
    codigoPuesto: '',
    tipoServidorPublico: 0
  };
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  puesto: string = '';
  codigoPuesto: string = '';
  lsCategoriaIncrementoSalarial: CategoriaIncrementoSalarialModel[] = [];
  lsTabuladorDocente: TabuladorDocenteModel[] = [];
  lsTabuladorBurocrata: TabuladorBurocrataModel[] = [];
  mostrarSeccionDocentes: boolean = false;
  mostrarSeccionBurocrata: boolean = false;
  //#endregion

  //#region Constructores
  constructor(
    private authenticationService: AuthenticationServiceService,
    private fortalecimientoSalarioServiceService: FortalecimientoSalarioServiceService
  ) { }

  ngOnInit(): void {
    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(usr => {
        this.idServidorPublico = usr.CLAVESERVIDOR;

        this.cargaDatosServidorPublico();
      });
    }
  }
  //#endregion

  //#region Funciones privadas
  /**
   * Obtener datos de servidor publico.
   * 
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/07/2022.
   */
  private cargaDatosServidorPublico(): void {
    this.fortalecimientoSalarioServiceService.obtenerDatosServidorPublico(
      this.idServidorPublico
    ).subscribe(data => {
      this.servidorPublicoIncrementoSalarialDTO = data.response;
      this.nombreServidorPublico = this.servidorPublicoIncrementoSalarialDTO.nombreCompletoServidorPublico;
      this.codigoPuesto = this.servidorPublicoIncrementoSalarialDTO.codigoPuesto;
      this.puesto = this.servidorPublicoIncrementoSalarialDTO.codigoPuesto
        + ' - '
        + this.servidorPublicoIncrementoSalarialDTO.nombrePuesto;

      this.cargarPerfilServidorPublico(data.response.tipoServidorPublico);
    });
  }


  private cargarPerfilServidorPublico(tipoServidorPublico: number) {
    if (tipoServidorPublico === 1) {
      this.mostrarSeccionBurocrata = false;
      this.mostrarSeccionDocentes = true;
      this.cargarCategorias();
      this.cargarTabuladorDocente(this.idServidorPublico);
    } else {
      this.mostrarSeccionBurocrata = true;
      this.mostrarSeccionDocentes = false;
      this.cargarTabuladorBurocrata(this.codigoPuesto);
    }

  }

  /**
   * Cargar las categorias del incremento salarial.
   * 
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/07/2022.
   */
  private cargarCategorias(): void {
    this.fortalecimientoSalarioServiceService.obtenerCategoriasIncrSal(
    ).subscribe(data => {
      this.lsCategoriaIncrementoSalarial = data.response
    });
  }

  /**
   * Cargar tabulador docente de acuerdo a clave de servidor publico.
   * 
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 21/07/2022.
   * @param claveServidorPublico Clave del servidor publico de quien se 
   * obtendra el tabulador.
   */
  private cargarTabuladorDocente(claveServidorPublico: string): void {
    this.fortalecimientoSalarioServiceService.obtenerTabuladorDocIncrSal(
      claveServidorPublico
    ).subscribe(data => {
      this.lsTabuladorDocente = data.response
    });
  }

  /**
   * Obtener tabulador burocrata de acuerdo a un codigo de puesto determinado.
   * 
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 21/07/2022.
   * @param codigoPuesto Codigo de puesto del que se obtendra el tabulador 
   * burocrata.
   */
  private cargarTabuladorBurocrata(codigoPuesto: string): void {
    this.fortalecimientoSalarioServiceService.obtenerTabuladorBurIncrSal(
      codigoPuesto
    ).subscribe(data => {
      this.lsTabuladorBurocrata = data.response
    });
  }
  //#endregion

}
