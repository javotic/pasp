import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';
import { ProcesoescalafonarioService } from 'src/app/services/escalafon/procesoescalafonario.service';
import { PuntajeEscalafonario } from 'src/app/models/puntaje-escalafonario';
import { SeccionesEDD } from 'src/app/models/seccionesEDD';
import { Router } from '@angular/router';
import { ArbolEscalafonario } from 'src/app/models/arbol-escalafonario';
import { PuestoActual } from 'src/app/models/puesto-actual';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { ProcesoEscalafonarioVigente } from 'src/app/models/proceso-escalafonario-vigente';
import { NgxSpinnerService } from 'ngx-spinner';
import { PuntajePeriodo } from 'src/app/models/Escalafon/puntaje-periodo';
import { forkJoin } from 'rxjs';
import { AntiguedadModel } from 'src/app/models/Escalafon/antiguedad-model';
import { PuntajeEscolaridad } from 'src/app/models/Escalafon/puntaje-escolaridad';
import { PuntajePrevio } from 'src/app/models/Escalafon/puntaje-previo';
import { InduccionCapacitacon } from 'src/app/models/Escalafon/induccion-capacitacion';
import { CapacitacionDiplomados } from 'src/app/models/Escalafon/capacitacion-diplomados';
import { CapacitacionCertificados } from 'src/app/models/Escalafon/capacitacion-certificados';
import { CapacitacionCursos } from 'src/app/models/Escalafon/capacitacion-cursos';
import { RecepcionDocumentos } from 'src/app/models/Escalafon/recepcion-documentos';

@Component({
  selector: 'app-proceso-escalafonario',
  templateUrl: './proceso-escalafonario.component.html',
  styleUrls: ['./proceso-escalafonario.component.css']
})
export class ProcesoEscalafonarioComponent implements OnInit {

  items3: MenuItem[];
  cols: any[];
  procesoEscalafonario: ArbolEscalafonario[] = [];
  currentUser: DatosPersonales = new DatosPersonales();
  puntajeEscalafonario: PuntajeEscalafonario = null;
  puestoActual: PuestoActual = new PuestoActual();
  displayPreguntas: boolean;
  idServidorPublico: string = '';
  procesoEscalafonarioVigente: ProcesoEscalafonarioVigente = null;
  vigente: string;

  //Parametros detalle puntuacion

  displayDetallePuntos: boolean = false;
  showCapacitacion : boolean = false;
  showEscolaridad: boolean = false;
  showEficiencia: boolean = false;
  showAntiguedad: boolean = false;
  showDemeritos: boolean = false;

  textHeaderDetalle = '';

  puntajeEficiencia:PuntajePeriodo[] = [];
  puntajeDemeritos:PuntajePeriodo[] = [];
  puntajeDemeritosTotales:PuntajePeriodo[] = [];
  puntajeAntiguedad:AntiguedadModel[] = [];
  puntajeEscolaridad: PuntajeEscolaridad[] = [];
  puntajePrevio: PuntajePrevio[] = [];
  
  puntajeCapaInduccion: InduccionCapacitacon[] = [];
  puntajeCapaCursos : CapacitacionCursos[] = [];
  puntajeCapaCetificados: CapacitacionCertificados[] = [];
  puntajeCapaDiplomados: CapacitacionDiplomados[] = [];
  datosRecepcionDocumentos: RecepcionDocumentos[] = [];

  resultConsulta = [];

  @ViewChild('myelement') myInputField: any;
  

  constructor(
    private messageService: MessageService,
    private procesoEscalafonarioService: ProcesoescalafonarioService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private spinner: NgxSpinnerService,
    private router: Router) { }
  secciones: SeccionesEDD[] = [];
  index: number = 0;
  activeIndex: number = 1;
  ngOnInit(): void {
    
   
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
        console.log(this.currentUser);
        this.idServidorPublico = usr.CLAVESERVIDOR;
      });
    }
    this.vigente = sessionStorage.getItem('idProcesoVigente');
    this.cols = [];
    this.items3 = [
      { label: 'Mi información actual', icon: 'pi pi-list', routerLink: '/procesoEscalafonario' },
      { label: 'Proceso Escalafonario vigente', icon: 'pi pi-list', routerLink: '/procesoEscalafonariovigente' },
      { label: 'Mi histórico', icon: 'pi pi-list', routerLink: '/miHistorico' }
    ];

    this.construyeGrid();
    this.obtienePuntajeActual();
    this.getArbolEscalafonario();
    this.secciones = this.procesoEscalafonarioService.getSecciones();


  }

  ngAfterViewInit(){
    this.myInputField.nativeElement.focus();
  }



  construyeGrid() {
    this.cols.push(this.construyeColumnas("GRUPO", 'GRUPO'));

    this.cols.push(this.construyeColumnas("RAMA", 'RAMA'));
    this.cols.push(this.construyeColumnas("LINEA", 'LINEA'));

    this.cols.push(this.construyeColumnas("CLAVE", 'CLAVE'));
    this.cols.push(this.construyeColumnas("NOMBRE", 'NOMBRE'));
  }

  construyeColumnas(field: string, columna: string) {
    let column = {
      "field": field,
      "header": columna
    };
    return column;
  }

  obtienePuntajeActual() {
    console.log('buscando puntaje'+ this.vigente);
    this.procesoEscalafonarioService.getAPuntajeEscalafonario(this.idServidorPublico, this.vigente)
    .subscribe(data => {
      this.puntajeEscalafonario = data.response[0];
    }, err=>{
      this.puntajeEscalafonario = null;
    });
  }

  /* getPuestoActual() {
     this.procesoEscalafonarioService.getPuestoActual(this.idServidorPublico).subscribe(data => {
       this.puestoActual = data.response[0];
     });
   }*/

  getArbolEscalafonario() {
    this.procesoEscalafonarioService.getArbolEscalafonario(this.idServidorPublico).subscribe(data => {
      console.log(data.response);
      let puestoActual = new PuestoActual();
      puestoActual.idPuesto = data.response[0].CLAVEPUESTOACTUAL;
      puestoActual.nombrePuesto = data.response[0].NOMBREPUESTOACTUAL;
      this.puestoActual = puestoActual;
      this.procesoEscalafonario = data.response[0].arbol;
    }, error=>{
    });
  }

  handleChange(e) {
    var index = e.index;
    if (index === 0) {
      this.router.navigate(['/procesoEscalafonario']);
    }
    if (index === 1) {
      this.router.navigate(['/procesoEscalafonariovigente']);
    }

    if (index === 2) {
      this.router.navigate(['/miHistorico']);
    }
    // this.obteniendoInstrucciones(index);
  }

  redirectCapacitacion() {
    this.router.navigate(['/datosProfesionales']);
  }
  redirectEscolaridad() {
    this.router.navigate(['/datosPersonales'], { queryParams: {   redirect: '1'} });
  }

  muestraPreguntas() {
    this.displayPreguntas = true;
  }


  showDetallePuntaje(tipoDetalle: string){
    this.resultConsulta = [];
    this.showCapacitacion = false;
    this.showEscolaridad = false;
    this.showEficiencia = false;
    this.showAntiguedad = false;
    this.showDemeritos = false;

    switch(tipoDetalle){
      case("CAPA"):
        this.showCapacitacion = true;
        this.textHeaderDetalle = "Capacitación";
        this.getDetalleCapacitacion();
      break;
      case("ESCO"):
        this.showEscolaridad = true;
        this.textHeaderDetalle = "Escolaridad";
        this.getDetalleEscolaridad();
      break;
      case("EFIC"):
        this.showEficiencia = true;
        this.textHeaderDetalle = "Eficiencia";
        this.getDetalleEficiencia();
      break;
      case("ANTI"):
        this.showAntiguedad = true;
        this.textHeaderDetalle = "Antigüedad";
        this.getDetalleAntiguedad();
      break;
      case("DEME"):
        this.showDemeritos = true;
        this.textHeaderDetalle = "Deméritos";
        this.getDetalleDemeritos();
      break;
    }
  }

  /**
   * Obtiene el detalle de eficiencia y lo muestra en una ventana emergente.
   */
  getDetalleEficiencia(){
    console.log('this.puntajeEficiencia' + this.puntajeEficiencia);
    if(this.puntajeEficiencia.length == 0){
      this.procesoEscalafonarioService.getEscalafonEficiencia(this.idServidorPublico, this.vigente).subscribe(dEficiencia =>{
        
        this.puntajeEficiencia = dEficiencia.response;
        this.resultConsulta = this.puntajeEficiencia;
        console.log('resultConsulta:' + JSON.stringify(this.resultConsulta));
        this.displayDetallePuntos = true;
      }, error =>{
        this.showMensaje("No se han podido obtener los datos de eficiencia.");
        this.displayDetallePuntos = false;
      });
    }else{
      this.resultConsulta = this.puntajeEficiencia;
      this.displayDetallePuntos = true;
    }      
  }


  getDetalleDemeritos(){
    if(this.puntajeDemeritos.length == 0 || this.puntajeDemeritosTotales.length == 0){
      this.procesoEscalafonarioService.getEscalafonDemeritos(this.idServidorPublico, this.vigente).subscribe(dMeritos =>{
        this.puntajeDemeritos = dMeritos.response;
        this.resultConsulta = this.puntajeDemeritos;

        this.procesoEscalafonarioService.getEscalafonDemeritosTotales(this.idServidorPublico, this.vigente).subscribe(dMeritosTot =>{
          this.puntajeDemeritosTotales = dMeritosTot.response; 
          this.displayDetallePuntos = true;
        }, error =>{
          this.showMensaje("No se han podido obtener los datos de demeritos.");
          this.displayDetallePuntos = false;
        });
      }, error =>{
        this.showMensaje("No se han podido obtener los datos de demeritos.");
        this.displayDetallePuntos = false;
      });
      
    }else{
      this.resultConsulta = this.puntajeDemeritos;
      this.displayDetallePuntos = true;
    }      
  }

  getDetalleAntiguedad(){
    if(this.puntajeAntiguedad.length == 0){
      this.procesoEscalafonarioService.getEscalafonAntiguedad(this.idServidorPublico, this.vigente).subscribe(dAntiguedad =>{
        this.puntajeAntiguedad = dAntiguedad.response;
        this.displayDetallePuntos = true;
      }, error =>{
        this.showMensaje("No se han podido obtener los datos de Antigüedad.");
        this.displayDetallePuntos = false;
      });
    }else{
      this.displayDetallePuntos = true;
    }      
  }
  

  getDetalleEscolaridad(){
    if(this.puntajeEscolaridad.length == 0){
      this.procesoEscalafonarioService.getEscalafonEscolaridad(this.idServidorPublico, this.vigente).subscribe(dEscolaridad =>{
        this.puntajeEscolaridad = dEscolaridad.response;
        this.displayDetallePuntos = true;
      }, error =>{
        this.showMensaje("No se han podido obtener los datos de Escolaridad.");
        this.displayDetallePuntos = false;
      });
    }else{
      this.displayDetallePuntos = true;
    }      
  }

  getDetalleCapacitacion(){
      let capaInduccion = this.procesoEscalafonarioService.getEscalafonCapacInduccion(this.idServidorPublico, this.vigente);
      let CapaCursos = this.procesoEscalafonarioService.getEscalafonCapacCursos(this.idServidorPublico, this.vigente);
      let CapaCetificados = this.procesoEscalafonarioService.getEscalafonCapacCertifComp(this.idServidorPublico, this.vigente);
      let CapaDilomados = this.procesoEscalafonarioService.getEscalafonDiplomados(this.idServidorPublico, this.vigente);
      let DatosRecepcion = this.procesoEscalafonarioService.getEscalafonDatosRecepDoc(this.idServidorPublico, this.vigente);

      forkJoin([capaInduccion, CapaCursos, CapaCetificados, CapaDilomados, DatosRecepcion]).subscribe(result=>{
        this.puntajeCapaInduccion = result[0].response;
        this.puntajeCapaCursos = result[1].response;
        this.puntajeCapaCetificados = result[2].response;
        this.puntajeCapaDiplomados = result[3].response;
        this.datosRecepcionDocumentos = result[4].response;
        this.displayDetallePuntos = true;
      }, error =>{
        this.showMensaje("No se han podido obtener los datos de Capacitación.");
        this.displayDetallePuntos = false;
      });      
  }

  showMensaje(msg: string, header:string = 'Error'){
    this.messageService.clear();
    this.messageService.add({ key: 'd', severity: 'error', summary: header, detail: msg });
  }

}
