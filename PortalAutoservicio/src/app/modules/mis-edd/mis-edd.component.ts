import { Component, OnInit } from '@angular/core';
import { MisEvaluacionesDespService } from 'src/app/services/mis-evaluaciones-desp.service';
import { MisEdd } from 'src/app/models/misedd';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { NGXLogger } from 'ngx-logger';
import { EjecucionEdddemeritosService } from 'src/app/services/ejecucionedd-demeritos.service';
import { forkJoin } from 'rxjs';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { ConsultaDatosPersonales } from 'src/app/models/consulta-datos-personales';

@Component({
  selector: 'app-mis-edd',
  templateUrl: './mis-edd.component.html',
  styleUrls: ['./mis-edd.component.css']
})
export class MisEDDComponent implements OnInit {
  misEvaluaciones: MisEdd[] = [];
  idServidorPublico: string = '';
  displayPreguntas: boolean = false;
  userCurrent: ConsultaDatosPersonales = new ConsultaDatosPersonales();
  constructor(private misEvaluacionesDespService: MisEvaluacionesDespService,
    private logger: NGXLogger,
    private serviceEdd: EjecucionEdddemeritosService,
    private spinner: NgxSpinnerService,
    private authenticationService: AuthenticationServiceService) { }

  ngOnInit(): void {
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        console.log(usr);
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.userCurrent = usr;

      });
    }

    this.getMisEvaluacionesEDD();

  }

  getMisEvaluacionesEDD() {
    this.misEvaluacionesDespService.getMisEvaluaciones(this.idServidorPublico).subscribe(e => {
      let vari = <RespuestaApi<MisEdd>>e;
      this.misEvaluaciones = <MisEdd[]>vari.response;
    })
  }

  descargarPDF(tipo: MisEdd) {
    this.logger.debug('entro al metodo descargarPDF');

    this.spinner.show();
    const a = document.createElement('a');

    let servicePDF = this.serviceEdd.generarPDFHT(tipo.IDPROCESOVIGENTE, this.idServidorPublico);
    forkJoin([servicePDF]).subscribe(results => {
      this.logger.debug('Result=>', results);
      if (results !== undefined) {
        const url = window.URL.createObjectURL(results[0]);
        this.logger.debug('URL=>', url);
        let src: string = url;
        this.logger.debug('src=>', src);
        a.setAttribute('style', 'display:none');
        document.body.appendChild(a);
        a.href = src;
        a.download = 'Reporte_EDD.pdf';
        a.click();
        this.spinner.hide();
      }
    });
  }
  mostrarPreguntasFrecuentes() {
    this.displayPreguntas = true;
  }
  disabledHis(regis: MisEdd) :boolean{
   
    if ( regis.CONSTANCIA === '1'){
      return true;
    } else {
      return false;
    }

    /*
    if(Number(regis.FECHAFINPROCESOEVALUACION.getFullYear) <= Number('2019')){
      return true;
    }
    return false;
    */
  }
}
