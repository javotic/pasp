import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table/table';
import { MenuItem, MessageService } from 'primeng/api';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import * as moment from 'moment';
import { Evaluaciondesempeno } from 'src/app/models/evaluaciondesempeno';
import { EvaluaciondesemService } from 'src/app/services/evaluaciondesempeno.service';
import { Router} from '@angular/router';
import { PorcesoVigente } from 'src/app/models/proceso-vigente';
import { environment } from 'src/environments/environment';
import { RespuestaServiceM4 } from 'src/app/repuesta/respuesta-service-m4';

@Component({
  selector: 'app-evaluacion-desempeno',
  templateUrl: './evaluacion-desempeno.component.html',
  styleUrls: ['./evaluacion-desempeno.component.css']
})
export class EvaluacionDesempenoComponent implements OnInit {

  //Variables Globales de la clase
  movimientos: Evaluaciondesempeno[];
  map = new Map<String, String>();
  cols: any[];
  @ViewChild('dt') table: Table;
  items: MenuItem[];
  displayDialog: boolean;
  idservpublico: string;
  isValid: boolean;
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  descnotifica: string;
  index: number = 0;
  idcancelar: string;
  procesoVig: PorcesoVigente = new PorcesoVigente();
  urlDat: string = `frwsr_LPSAUT_CONS_DAT${environment.HEDER_WS}.php`;
  displayPreguntas: boolean = false;

  //Constructor de la clase
  constructor(
    private router: Router,
    private service: EvaluaciondesemService,
    private messageService: MessageService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger) { }


  //Metodo principal de la clase
  ngOnInit(): void {
    this.isValid = false;
    this.logger.debug('Inicia Desempeño');
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        console.log(usr);
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    }

    let fecha = moment(new Date()).format('MMMM Do YYYY');

    this.cols = [
      { field: 'PROCESOEVA', header: 'Proceso de Evaluación' },
      { field: 'FECHAINI', header: 'Fecha de Inicio' },
      { field: 'FECHAFIN', header: 'Fecha Fin' },
      { field: 'DESEMPENO', header: 'Desempeño' },
      { field: 'DEMERITOS', header: 'Deméritos' },
      { field: '', header: 'Detalle' }
    ];

    /*Informacion del grid*/
    this.obtenerHistoricoEval(this.idServidorPublico);
    //obtiene el proceso vigente
    this.obtenerProcesoVigente(this.idServidorPublico);

  }

  confirmacionEvaluacionDesempeno() {
    console.log('ss');
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para continuar' });
  }

  rechazar() {
    this.messageService.clear('c');
  }

  no() {
    this.displayDialog = false;
  }

  redirectDetalleedd() {
    this.router.navigate(['/detalleEdd'], { queryParams: { proVig: this.procesoVig.IDPROCESOVIGENTE } });
  }

  redirectDetalleeddH(histo: Evaluaciondesempeno) {
    this.logger.debug('ruta historico,', histo);
    this.router.navigate(['/detalleEddHistorico'], { queryParams: { proVig: histo.IDPROCESOEVALUACION, nombreProceso: histo.NOMBREPROCESOEVALUACION } });
  }

  showRegresar() {
    this.router.navigate(['/home']);
  }
  /**
   * Obtiene el proceso vigente
   * @param idServidor 
   */
  obtenerProcesoVigente(idServidor: string) {
    let vigentePro = {
      "funcion": "consultarProcesoEvaluacionVigente",
      "IDSERVIDORPUBLICO": idServidor
    }

    this.service.getProcesoVigente(idServidor).subscribe(data => {
      this.logger.debug('vig=>>', data);
      let vari = <RespuestaServiceM4<PorcesoVigente>>data;
      this.logger.debug('Code=>', vari.code);
      if (vari.code.trim() === '200') {
        if (vari.response.length > 0) {
          this.isValid = true;
          this.logger.debug('var=>', vari);
          let proVig = <PorcesoVigente[]>vari.response;
          this.logger.debug('proVig=>', proVig);
          this.procesoVig = proVig[0];
        }else{
          this.isValid = false;
        }

      } else {
        this.isValid = false;
      }
    })

  }
  /**
   * Obtiene el historico de procesos
   * @param idSP 
   */
  obtenerHistoricoEval(idSP: string) {
    let hsitorico = {
      "funcion": "consultarHistorialEvaluaciones",
      "IDSERVIDORPUBLICO": idSP
    }
    this.service.getHistrialProcesoVig(idSP).subscribe(data => {
      let vari = <RespuestaServiceM4<Evaluaciondesempeno>>data;
      this.logger.debug('Historico Data', data);
      this.movimientos = <Evaluaciondesempeno[]>vari.response;
      this.logger.debug('historico', this.movimientos);
    })

  }
  mostrarPreguntasFrecuentes() {
    this.displayPreguntas = true;
  }

  mostrarDesempenio(tipo: string): boolean {

    if (tipo === '1' || tipo === '3') {
      return true;
    } else {
      return false;
    }
  }
  mostrarDemeritos(tipo: string): boolean {

    if (tipo === '2' || tipo === '3') {
      return true;
    } else {
      return false;
    }
  }

}
