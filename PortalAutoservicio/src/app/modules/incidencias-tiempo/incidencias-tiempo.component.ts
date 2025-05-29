import { Component, OnInit, ViewChild } from '@angular/core';
import { IncidenciastiempoService } from 'src/app/services/incidenciastiempo.service';
import { IncidenciasTiempo } from 'src/app/models/incidencias-tiempo';
import { Table } from 'primeng/table/table';
import * as moment from 'moment';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { NgxSpinnerService } from 'ngx-spinner';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ConsultaIncidenciasEtiquetas } from 'src/app/etiquetas/consulta-incidencias-etiquetas';
import { Combo } from 'src/app/models/combo';
import { Breadcrumb } from 'primeng';
import { formatDate } from '@angular/common';
@Component({
  selector: 'app-incidencias-tiempo',
  templateUrl: './incidencias-tiempo.component.html',
  styleUrls: ['./incidencias-tiempo.component.css']
})
export class IncidenciasTiempoComponent implements OnInit {

  incidenciasTiempo: IncidenciasTiempo[];
  dataEstimulos: IncidenciasTiempo[];
  dataSanciones: IncidenciasTiempo[];

  showImporteIncidencia = false;
  showImporteSancion = false;
  showImporteEstimulo = false;

  cols: any[];
  @ViewChild('dt') table: Table;

  fechaInicio:string;
  fechaFin:string;

  fechaInicioSancion:string;
  fechaFinSancion:string;
  
  fechaInicioEstimulo:string;
  fechaFinEstimulo:string;


  idServidorPublico: string = '';
  es: any;
  maxDate: Date;
  rangoAnios:string;
  
  theFormGroup: FormGroup;
  theFormSanciones: FormGroup;
  theFormEstimulos: FormGroup;

  consultaIncidenciasEtiquetas: ConsultaIncidenciasEtiquetas= new ConsultaIncidenciasEtiquetas() ;

  quincenas: Combo[] = [{label: 'Primera', value: '1'},
  {label: 'Segunda', value: '2'}];

  selected15Inicio = '1';
  selected15Fin = '1';
  mostrarInstrucciones: boolean = false;

  constructor(private incidenciastiempoService: IncidenciastiempoService,
    private authenticationService: AuthenticationServiceService,
    private messageService: MessageService,
    private spinner: NgxSpinnerService,
    private builder: FormBuilder,
    private logger: NGXLogger) { 
      
      this.theFormGroup = this.builder.group({ startDate: ["", []], endDate: ["", []] });
      this.theFormSanciones = this.builder.group({ startDate: ["", []], endDate: ["", []] });
      this.theFormEstimulos = this.builder.group({ startDate: ["", []], endDate: ["", []] });
    }

  ngOnInit(): void {
    this.maxDate = new Date();
    let anio = moment(new Date()).format("YYYY");
    this.rangoAnios='1995:'+anio;
    console.log('this.rangoAnios' + this.rangoAnios);
    this.iniciaCalendario();

    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        this.idServidorPublico = usr.CLAVESERVIDOR;
      });
    }
    this.cols = [];

    this.obtenerEtiquetasPagina();

    this.consultaincidenciasTiempo(1, 1);
    this.consultaincidenciasTiempo(2, 1);
    this.consultaincidenciasTiempo(3, 1);
  }

  /*
  obtenerDatosIncidenciasTiempo(fechaInicio:string, fechaFin:string) {


    this.incidenciastiempoService.obteneterIncidenciasTiempo(this.idServidorPublico, fechaInicio, fechaFin)
      .subscribe(data => {
        console.log('obteneterIncidenciasTiempo=>' + JSON.stringify(data));
        this.incidenciasTiempo = data.response;
        this.spinner.hide();
      });
  }
*/
  consultaincidenciasTiempo(tipoConsuta: number, inicioConsulta: number ){


    let fechaInicio = "";
    let fechaFin = "";

    switch(tipoConsuta){
      case 1: 
      if((this.fechaInicio === undefined||this.fechaFin === undefined) && inicioConsulta == 0 ){
        this.messageService.add({ key: 'd', severity: 'error', summary: "Error", detail: "Seleccione filtros de Filtros de búsqueda " });
        this.spinner.hide();
      }else{
        if(inicioConsulta == 0){
          fechaInicio = moment(this.fechaInicio).format('YYYY-MM-DD HH:mm:ss');
          fechaFin = moment(this.fechaFin).format('YYYY-MM-DD HH:mm:ss');
          
          let dateInicio: Date = new Date(fechaInicio);
          let dateFin: Date = new Date(fechaFin);

          // Fecha Inicio
          if(this.selected15Inicio == "1"){
            dateInicio.setDate(15);
          }else{
            var ultimoDia = new Date(dateInicio.getFullYear(), dateInicio.getMonth() + 1, 0);
            dateInicio.setDate(ultimoDia.getDate());
          }

          //Fecha Fin
          if(this.selected15Fin == "1"){
            dateFin.setDate(15);
          }else{
            var ultimoDia = new Date(dateFin.getFullYear(), dateFin.getMonth() + 1, 0);
            dateFin.setDate(ultimoDia.getDate());
          }
          console.log('dateInicio:' + dateInicio);
          console.log('dateFin:' + dateFin);
          console.log('this.selected15Inicio:' + this.selected15Inicio);
          fechaInicio = formatDate(dateInicio, 'yyyy-MM-dd HH:mm:ss', 'es_MX');
          fechaFin = formatDate(dateFin, 'yyyy-MM-dd HH:mm:ss', 'es_MX');;
        }

        
        this.spinner.show();
       
        console.log(fechaInicio,fechaFin);

          this.incidenciastiempoService.obteneterIncidenciasTiempo(this.idServidorPublico, tipoConsuta.toString() , fechaInicio, fechaFin)
          .subscribe(data => {
            console.log('obteneterIncidenciasTiempo=>' + JSON.stringify(data));
            this.incidenciasTiempo = data.response;

            const conteoIncidencia = this.incidenciasTiempo.filter(x=> x.IMPORTE != '0' && x.IMPORTE != '' ).length;
            console.log('conteoIncidencia:' + conteoIncidencia)
            if(conteoIncidencia == 0){
              this.showImporteIncidencia = false;
            }else{
              this.showImporteIncidencia = true;
            }

            this.spinner.hide();
          }, error=>{
            this.spinner.hide();
            console.log("Error al consultar los datos de incidencias");
          });

        }
      break;
      case 2: 
      console.log('Entramos al segundo');
      if((this.fechaInicioSancion === undefined||this.fechaFinSancion === undefined) && inicioConsulta == 0 ){
        this.messageService.add({ key: 'd', severity: 'error', summary: "Error", detail: "Seleccione filtros de Filtros de búsqueda " });
        this.spinner.hide();
      }else{
        if(inicioConsulta == 0){
          fechaInicio = moment(this.fechaInicioSancion).format('YYYY-MM-DD HH:mm:ss');
          fechaFin = moment(this.fechaFinSancion).format('YYYY-MM-DD HH:mm:ss');
        }
        this.incidenciastiempoService.obteneterIncidenciasTiempo(this.idServidorPublico, tipoConsuta.toString() , fechaInicio, fechaFin)
        .subscribe(data => {
          console.log('obteneterIncidenciasTiempo=>' + JSON.stringify(data));
          this.dataSanciones = data.response;

          const conteoSancion = this.dataSanciones.filter(x=> x.IMPORTE != '0' && x.IMPORTE != '').length;
          console.log('conteoIncidencia:' + conteoSancion)
          if(conteoSancion == 0){
            this.showImporteSancion = false;
          }else{
            this.showImporteSancion = true;
          }

          this.spinner.hide();
        }, error=>{
          this.spinner.hide();
          console.log("Error al consultar los datos de incidencias");
        });
      }
      
      break;
      case 3: 
      
      if((this.fechaInicioEstimulo === undefined||this.fechaFinEstimulo === undefined) && inicioConsulta == 0 ){
        this.messageService.add({ key: 'd', severity: 'error', summary: "Error", detail: "Seleccione filtros de Filtros de búsqueda " });
        this.spinner.hide();
      }else{
        if(inicioConsulta == 0){
          fechaInicio = moment(this.fechaInicioEstimulo).format('YYYY-MM-DD HH:mm:ss');
          fechaFin = moment(this.fechaFinEstimulo).format('YYYY-MM-DD HH:mm:ss');
        }
        this.incidenciastiempoService.obteneterIncidenciasTiempo(this.idServidorPublico, tipoConsuta.toString() , fechaInicio, fechaFin)
        .subscribe(data => {
          console.log('obteneterIncidenciasTiempo=>' + JSON.stringify(data));
          this.dataEstimulos = data.response;

          const conteoEstimulo = this.dataEstimulos.filter(x=> x.IMPORTE != '0' && x.IMPORTE != '').length;
          console.log('conteoIncidencia:' + conteoEstimulo)
          if(conteoEstimulo == 0){
            this.showImporteEstimulo = false;
          }else{
            this.showImporteEstimulo = true;
          }

          this.spinner.hide();
        }, error=>{
          this.spinner.hide();
          console.log("Error al consultar los datos de incidencias");
        });
      }
      
      break;
    }




   // this.obtenerDatosIncidenciasTiempo(fechaInicio, fechaFin);

  }



  iniciaCalendario(){
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
      monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
      monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
      today: 'Hoy',
      clear: 'Borrar'
    }
  }

  obtenerEtiquetasPagina() {
    console.log('sss');
    this.incidenciastiempoService.getEtiquetasIncidencias().subscribe(data => {
      console.log(data);
      this.consultaIncidenciasEtiquetas= data;

      this.construyeGrid();
    });
  }

  construyeGrid(){
    this.cols.push(this.construyeColumnas("CLAVE",this.consultaIncidenciasEtiquetas.clave));
    this.cols.push(this.construyeColumnas("NOMBRE",this.consultaIncidenciasEtiquetas.nombre));
    this.cols.push(this.construyeColumnas("FECHAINICIO",this.consultaIncidenciasEtiquetas.fechainicioL));
    this.cols.push(this.construyeColumnas("FECHAFIN",this.consultaIncidenciasEtiquetas.fechafin));
    this.cols.push(this.construyeColumnas("NUMEROUNIDADES",this.consultaIncidenciasEtiquetas.noUnidades));
    //this.cols.push(this.construyeColumnas("IMPORTE", "Importe"));
  }

  construyeColumnas(field:string,columna:string){
    let column = {
      "field": field,
      "header": columna
    };
    return column;
  }

  cambiar15Inicio(event){
    console.log('event:' + event.value);
    this.selected15Inicio = event.value;
  }

  cambiar15Fin(event){
    console.log('event:' + event.value);
    this.selected15Fin = event.value;
  }

}
