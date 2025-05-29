import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MihistoricoService } from 'src/app/services/escalafon/mihistorico.service';
import { MenuItem, MessageService } from 'primeng/api';
import { MiHistorico } from 'src/app/models/mi-historico';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { NgxSpinnerService } from 'ngx-spinner';
@Component({
  selector: 'app-mihistorico',
  templateUrl: './mihistorico.component.html',
  styleUrls: ['./mihistorico.component.css']
})
export class MihistoricoComponent implements OnInit {

  cols: any[];
  items3: MenuItem[];
  miHistorico: MiHistorico[] = [];
  currentUser: DatosPersonales = new DatosPersonales();
  idServidorPublico: string = '';
  vigente: string;
 
  constructor( 
    private messageService: MessageService,
    private router: Router,
    private authenticationService: AuthenticationServiceService,
    private historicoService: MihistoricoService,
    private spinner: NgxSpinnerService,
    private logger: NGXLogger) { }

  ngOnInit(): void {
    this.cols = [];
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
        console.log(this.currentUser);
        this.idServidorPublico = usr.CLAVESERVIDOR;
      });
    }


    this.construyeGrid();
    this.vigente = sessionStorage.getItem('idProcesoVigente');
   console.log('getMiHistorico=>tt');
    this.historicoService.getMiHistorico(this.idServidorPublico).subscribe(data=>{
    //  this.spinner.show();
      console.log('getMiHistorico=>' + JSON.stringify(data));
      this.miHistorico= data.response;

    });
   
   
  }
  construyeGrid() {
    this.cols.push(this.construyeColumnas("nombreProcesoEscalafonario", 'PROCESO ESCALAFONARIO'));
    this.cols.push(this.construyeColumnas("FECHAINICIOPROCESO", 'FECHA INICIO'));
    this.cols.push(this.construyeColumnas("FECHAFINPROCESO", 'FECHA FIN'));
    this.cols.push(this.construyeColumnas("plazapart", 'PLAZA PARTICIPANTE'));
    this.cols.push(this.construyeColumnas("puesoPar", 'PUESTO'));

    this.cols.push(this.construyeColumnas("PUNTAJELOGRADO", 'PUNTAJE'));
    this.cols.push(this.construyeColumnas("LUGARRANKING", 'DICTAMEN FINAL'));
    this.cols.push(this.construyeColumnas("constancia", 'CONSTANCIA DE PARTICIPACIÓN'));
  }

  construyeColumnas(field: string, columna: string) {
    let column = {
      "field": field,
      "header": columna
    };
    return column;
  }

  showRegresar() {
    this.router.navigate(['/procesoEscalafonario']);
  }

  descargaConstancia(clavePlaza: string){
    //'001012180'
    this.historicoService.getDescargaConstancias(this.idServidorPublico, clavePlaza,this.vigente).subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;
      a.download = 'Constancia de Participacion.pdf';
      a.click();
    }, error=>{
      this.messageService.clear();
      this.messageService.add({ key: 'd', sticky: true, severity: 'warn', summary: '', detail: 'La constancia no se ha podido descargar, inténtelo más tarde.' });
    });
  }
  
}
