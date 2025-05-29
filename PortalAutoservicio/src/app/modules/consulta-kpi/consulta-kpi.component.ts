import { Component, OnInit } from '@angular/core';
import { ProcesoKPIVigenteService } from 'src/app/services/proceso-kpivigente.service';
import { ProcesoKPIVigente } from 'src/app/models/proceso-kpi-vigente';
import { SidebarService } from './../../services/sidebar.service';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { InstruccionesEvaluacionKPI } from 'src/app/models/instrucciones-evaluacion-kpi';
import { HistorialKpiVIgente } from 'src/app/models/historial-kpi-vigente';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { QuestionkpiService } from 'src/app/services/questionkpi.service';
import { ConsultaDatosPersonales } from 'src/app/models/consulta-datos-personales';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-consulta-kpi',
  templateUrl: './consulta-kpi.component.html',
  styleUrls: ['./consulta-kpi.component.css']
})
export class ConsultaKPIComponent implements OnInit {
  procesoKPIVigente: ProcesoKPIVigente = new ProcesoKPIVigente();
  instruccionesEvaluacionKPI: InstruccionesEvaluacionKPI = new InstruccionesEvaluacionKPI();

  historialKPI: HistorialKpiVIgente[] = [];
  displayPreguntas: boolean;
  idServidorPublico: string = '';
  exiteKPI: boolean = false;
  permiteContestar: boolean = true;

  constructor(
    private procesoKPIVigenteService: ProcesoKPIVigenteService,
    private messageService: MessageService,
    private spinner: NgxSpinnerService,
    private router: Router,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private sidebarService: SidebarService,
    private questionkpiService: QuestionkpiService) { }

  ngOnInit(): void {
    //Obtene la clave de servidor publico     
    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(us => {
        this.logger.debug('KPI===>>',us);
        this.idServidorPublico = us.CLAVESERVIDOR;
      })

    }
    this.getHistoricoKPI();
  }

  iniciarEvaluacion() {
    //this.getinstruccionesEvaluacionKPI();
    this.messageService.clear();
    this.messageService.add({ key: 'kpi', sticky: true, severity: 'warn', summary: '', detail: '' });
  }

  cerrarDialog() {
    this.messageService.clear('kpi');
  }

  startEval() {
    this.router.navigate(['/quizKIP'], { queryParams: { idProceso: this.procesoKPIVigente.CLAVEPROCESOEVALUACIONKPI, haveTime: this.instruccionesEvaluacionKPI.TIEMPO, timeP: this.instruccionesEvaluacionKPI.TIEMPOLIMITE } });
  }

  getHistoricoKPI() {
    this.spinner.show();
    this.procesoKPIVigenteService.getHistoricoKPI(this.idServidorPublico)
      .subscribe(data => {
        this.logger.debug('Data historico =>', data);
        this.historialKPI = data.response;
        this.logger.debug('historial=>', this.historialKPI);
        //Consultamos el proceso vigente
        this.getProcesoKPIVigente();
      }, err => {
        this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error" });
      });

  }
  getProcesoKPIVigente() {
    this.spinner.show();
    this.procesoKPIVigenteService.getProcesoKPIVigente(this.idServidorPublico).subscribe(procesoVig =>{
    let existeProcesoVig = procesoVig.code === '200' ? true : false;
      if (existeProcesoVig) {
          console.log('procesoVig Nuevo=>' + JSON.stringify(procesoVig.response));
          var myJSON = JSON.stringify(procesoVig.response);
          this.procesoKPIVigente =  <ProcesoKPIVigente>JSON.parse(myJSON);
          this.logger.debug('Proceso Vig =>', this.procesoKPIVigente);
          this.procesoKPIVigenteService.getinstruccionesEvaluacionKPI(this.procesoKPIVigente.CLAVEPROCESOEVALUACIONKPI).subscribe(data => {
            this.logger.debug('Instrucciones Data =>', data);
            this.instruccionesEvaluacionKPI = data.response[0];
            this.logger.debug('Instrucciones', this.instruccionesEvaluacionKPI);
            if (existeProcesoVig) {
              this.exiteKPI = true;
            }
            if(this.historialKPI.filter(x => x.CLAVEPROCESOKPI == this.procesoKPIVigente.CLAVEPROCESOEVALUACIONKPI
                                          && x.IDESTATUS == '1').length > 0){
              this.permiteContestar = false;
            }
            this.spinner.hide();
          }, err => {
            this.spinner.hide();
          });
      }else{
        this.spinner.hide();
      }
  }, err=>{
    this.spinner.hide();
  } );
  }

  getinstruccionesEvaluacionKPI() {
    this.spinner.show();
    this.procesoKPIVigenteService.getinstruccionesEvaluacionKPI(this.procesoKPIVigente.IDPROCESOVIGENTE).subscribe(data => {
      this.logger.debug('Instrucciones Data =>', data);
      this.instruccionesEvaluacionKPI = data.response[0];
      this.logger.debug('Instrucciones', this.instruccionesEvaluacionKPI);
      this.spinner.hide();
    }, err => {
      this.spinner.hide();
    });
  }

  muestraPreguntas() {
    this.displayPreguntas = true;
  }

  generarReporteEncuesta(fechaEncuesta: string, nombreProceso: string){
    this.spinner.show();
    try {
      var datePipe = new DatePipe("es-MXN");
      let value = datePipe.transform(fechaEncuesta, 'dd-MMMM-yyyy');
      let dateValue = value.replace('-', ' de ');
      let dateValue2 = 'a ' + dateValue.replace('-', ' de ');

      this.sidebarService.consultarDatosPersonales(this.idServidorPublico, '-', '-', '-').subscribe(datosPers=>{
        console.log('datosPers.response[0]=>' + JSON.stringify(datosPers.response));
      
        const dataServidorPublico = <ConsultaDatosPersonales>JSON.parse(JSON.stringify(datosPers.response));

        let estimado: string = 'Estimado(a) ' + dataServidorPublico.MOMBRE + ' ' +
                                                dataServidorPublico.APELLIDOPATERNO + ' ' + 
                                                dataServidorPublico.APELLIDOMATERNO;
      
        this.questionkpiService.generarReporteKPI(dateValue2, estimado, nombreProceso).subscribe(data => {
          
          const oMyBlob = new Blob([data], {type : 'application/pdf'});
          const url = window.URL.createObjectURL(oMyBlob);
          const a = document.createElement('a');
          a.setAttribute('style', 'display:none');
          document.body.appendChild(a);
          a.href = url;
          a.download = 'Reporte de Encuesta.pdf';
          a.click();
          this.spinner.hide();
        }, err => {
          this.spinner.hide();
          this.messageService.clear();
          this.messageService.add({ key: 'error', sticky: true, severity: 'warn', 
                                    summary: 'No se ha podido descargar la constancia de termino.', detail: '' })
        })
      }, err=>{
        this.spinner.hide();
        this.messageService.clear();
        this.messageService.add({ key: 'error', sticky: true, severity: 'warn', 
                                  summary: 'No se ha podido descargar la constancia de termino.', detail: '' })
      });

    }
    catch (error) {
      this.spinner.hide();
      this.messageService.clear();
      this.messageService.add({ key: 'error', sticky: true, severity: 'warn', 
                                summary: 'No se ha podido descargar la constancia de termino.', detail: '' })
    }
  }

}
