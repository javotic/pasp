import { Component, Input, OnInit, ViewChild, ViewChildren, ViewEncapsulation } from '@angular/core';
import { MenuItem, MessageService } from 'primeng/api';
import { ProcesoescalafonariovigenteService } from 'src/app/services/escalafon/procesoescalafonariovigente.service';
import { Router } from '@angular/router';
import { ProcesoEscalafonarioVigente } from 'src/app/models/proceso-escalafonario-vigente';
import { ConcursoEscalafonarioVigente } from 'src/app/models/concurso-escalafonario-vigente';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { AplicaProcesoEscalafonario } from 'src/app/models/aplica-proceso-escalafonario';
import { SesionesAsesoria } from 'src/app/models/sesiones-asesoria';
import { ProcesoescalafonarioService } from 'src/app/services/escalafon/procesoescalafonario.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { SesionExamen } from 'src/app/models/sesion-examen';
import { DescargaGuia } from 'src/app/models/descarga-guia';
import { forkJoin } from 'rxjs';
import * as moment from 'moment';
import { TooltipModule } from 'primeng/tooltip';

@Component({
  selector: 'app-proceso-escalafonario-vigente',
  templateUrl: './proceso-escalafonario-vigente.component.html',
  styleUrls: ['./proceso-escalafonario-vigente.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProcesoEscalafonarioVigenteComponent implements OnInit {
  //#region Propiedades de clase
  items3: MenuItem[];
  cols: any[];
  cols2: any[];
  procesoEscalafonarioVigente: ConcursoEscalafonarioVigente[] = [];
  currentUser: DatosPersonales = new DatosPersonales();
  plazasParticipacion: any[] = [];
  items2: MenuItem[];
  displayDialog: boolean;
  procesoEscalafonario: ProcesoEscalafonarioVigente = null;
  idServidorPublico: string = '';
  vigente: string;
  aplicaProcesoEscalafonario: AplicaProcesoEscalafonario = new AplicaProcesoEscalafonario();
  sesionesAsesoria: SesionesAsesoria[] = [];
  sesionesExamen: SesionExamen[] = [];
  puntajeActual: string;
  plazaSeleccionada: string;
  activeIndex: number = -1;
  detalleDescripcionEstatus: string;
  displayProcesoEscalafonario: boolean;
  idPlazaParticipacion: string;
  activaBotonPreinscripcion: boolean;
  requisitosPuntajeMinimo: string;
  escolaridadMinima: string;
  experienciaMinima: string;
  procesoCancelado: boolean;
  motivoCancelacion: string;
  idEstatusPlaza: string;
  idGuiaPuesto: string;
  urlGuia: DescargaGuia[];
  dsnombrePuestoGuia: string;
  dsrutaguiadescarga: string;
  // Variable para saber el estatus del dictamen seleccionado
  idEstatusDictamen: string;
  rankingDictamen: string;
  //Mensaje Notificacion
  MsgAtencion: boolean = false;
  showAtencion: boolean = false;
  aplicarValidacion: boolean = false;
  //Mensaje mostrar resultados
  msgPublicaRes = false;
  valuePublicaRes = '0';
  fechaAcepta: Date
  @ViewChild('miPlazaInsc') miPlazaInsc: any;
  aplicarFoco: boolean = false;
  isFinalizado: boolean = false;
  /* Identificando el cambio de pestaña en otro componente */
  private tabProcesoVig: number;
  procesoGeneral: boolean = false;
  //#endregion


  get tabSeleccion(): number {
    return this.tabProcesoVig;
  }

  @Input('tabSeleccion') set tabSeleccion(value: number) {
    if (value) {
      this.tabProcesoVig = value;
      if (value == 1) {
        this.mostrarEnfoque();
      }
    }
  }

  constructor(private service: ProcesoescalafonariovigenteService,
    private messageService: MessageService,
    private procesoEscalafonarioService: ProcesoescalafonarioService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private spinner: NgxSpinnerService,
    private router: Router) { }


  ngAfterViewInit() {
    this.miPlazaInsc.first.nativeElement.focus();
  }

  mostrarEnfoque() {
    //Hacemos el foco de la preinscripcion
    console.log('Realizamos el foco');
    setTimeout(() => {
      this.aplicarFoco = false;
      this.miPlazaInsc.nativeElement.focus();
      this.aplicarFoco = true;
    }, 600);


    setTimeout(() => { this.aplicarFoco = false; }, 6000);
  }

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
    console.log(' this.vigente=>' + this.vigente);
    this.cols = [];
    this.cols2 = [];
    this.items2 = [];


    this.spinner.show();

    let procVigente_init = this.service.getProcesoEscalafonarioVigente();
    let procEscalafonario_init = this.service.getAplicaProcesoEscalafonario(this.idServidorPublico);
    let plazasDisponibles_init = this.service.getArbolPlazasParticipacion(this.idServidorPublico, '1');
    let plazasParticipacion_init = this.service.getArbolPlazasParticipacion(this.idServidorPublico, '0');

    forkJoin([procVigente_init, procEscalafonario_init, plazasDisponibles_init, plazasParticipacion_init])
      .subscribe(resultado_group => {

        //this.getProcesoEscalafonarioVigente();
        if (resultado_group[0].response.length > 0) {
          this.procesoEscalafonario = resultado_group[0].response[0];
          this.procesoGeneral = this.procesoEscalafonario.tipoProceso === 1;
        }

        this.getEscalafonEstadoInscripcion();

        // this.getAplicaProcesoEscalafonario();
        if (resultado_group[1].response.length > 0) {
          this.aplicaProcesoEscalafonario = resultado_group[1].response[0];
          console.log('this.aplicaProcesoEscalafonario' + this.aplicaProcesoEscalafonario.BOINSCRITO);
        }

        // this.getPlazasDisponibles();
        this.procesoEscalafonarioVigente = resultado_group[2].response.filter(x => x.PVISIBLE == '1');
        console.table(this.procesoEscalafonarioVigente);

        // this.getPlazasParticipacion();
        this.plazasParticipacion = resultado_group[3].response;
        console.log(this.plazasParticipacion.length);
        if (this.plazasParticipacion.length == 0) {
          this.activaBotonPreinscripcion = false;
        } else {
          this.activaBotonPreinscripcion = true;
        }

        this.spinner.hide();
      }, error => {
        this.spinner.hide();
        console.log('Error al obtener algunos datos');
      });


    this.construyeGrid();
    this.construyeGrid2();


  }

  construyeGrid() {
    //this.cols.push(this.construyeColumnas("NUMERO", 'No.'));
    this.cols.push(this.construyeColumnas("nombrePuesto", 'PUESTO Y JORNADA LABORAL'));
    this.cols.push(this.construyeColumnas("nivel", 'NIVEL - RANGO'));
    this.cols.push(this.construyeColumnas("NOPLAZA", 'NO. DE PLAZA'));
    this.cols.push(this.construyeColumnas("PERCEPCIONMENSUALES", 'PERCEPCIONES MENSUALES'));
    this.cols.push(this.construyeColumnas("ADSCRIPCION", 'ADSCRIPCIÓN'));
    this.cols.push(this.construyeColumnas("UBICACIONTRABAJO", 'UBICACIÓN DEL TRABAJO'));
    this.cols.push(this.construyeColumnas("REQUISITOS", 'REQUISITOS'));
    this.cols.push(this.construyeColumnas("PARTICIPANTES POR PLAZA", 'PARTICIPANTES POR PLAZA'));
  }

  construyeGrid2() {
    this.cols2.push(this.construyeColumnas("NUMERO", 'No.'));
    this.cols2.push(this.construyeColumnas("nombrePuesto", 'PUESTO Y JORNADA LABORAL'));
    this.cols2.push(this.construyeColumnas("nivel", 'NIVEL - RANGO'));
    this.cols2.push(this.construyeColumnas("NOPLAZA", 'NO. DE PLAZA'));
    this.cols2.push(this.construyeColumnas("PERCEPCIONMENSUALES", 'PERCEPCIONES MENSUALES'));
    this.cols2.push(this.construyeColumnas("ADSCRIPCION", 'ADSCRIPCIÓN'));
    this.cols2.push(this.construyeColumnas("UBICACIONTRABAJO", 'UBICACIÓN DEL TRABAJO'));
    // this.cols2.push(this.construyeColumnas("PARTICIPANTES POR PLAZA", 'PARTICIPANTES POR PLAZA'));
  }

  construyeColumnas(field: string, columna: string) {
    let column = {
      "field": field,
      "header": columna
    };
    return column;
  }

  getProcesoEscalafonarioVigente() {
    this.spinner.show();
    this.service.getProcesoEscalafonarioVigente().subscribe(data => {
      if (data.response.length > 0) {
        this.procesoEscalafonario = data.response[0];
      }
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
    });
  }

  showRegresar() {
    this.router.navigate(['/procesoEscalafonario']);
  }

  inscripcion(noPlaza: string) {

    console.log(noPlaza);
    this.plazaSeleccionada = noPlaza;
    this.messageService.clear();
    this.messageService.add({ key: 'msjIncri', sticky: true, severity: 'warn', summary: '', detail: '' });
    this.obtienePuntajeActual();

  }

  getPlazasDisponibles() {
    this.spinner.show();
    this.service.getArbolPlazasParticipacion(this.idServidorPublico, '1').subscribe(data => {
      this.procesoEscalafonarioVigente = data.response.filter(x => x.PVISIBLE == '1');
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
    });
  }

  getPlazasParticipacion() {
    this.spinner.show();
    this.service.getArbolPlazasParticipacion(this.idServidorPublico, '0').subscribe(data => {
      this.plazasParticipacion = data.response;
      console.log(this.plazasParticipacion.length);
      if (this.plazasParticipacion.length == 0) {
        this.activaBotonPreinscripcion = false;
      } else {
        this.activaBotonPreinscripcion = true;

        //Hacemos el foco de la preinscripcion
        console.log('Realizamos el foco');
        setTimeout(() => {
          this.aplicarFoco = false;
          this.miPlazaInsc.nativeElement.focus();
          this.aplicarFoco = true;
        }, 1500);


        setTimeout(() => { this.aplicarFoco = false; }, 6000);

      }
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
    });
  }


  cerrarDialog() {
    this.messageService.clear();
  }


  preincribirServidorPublico() {

    this.spinner.show();

    this.service.inscripcion(this.idServidorPublico, this.plazaSeleccionada, this.vigente).subscribe(data => {
      if (parseInt(data.response) < 0) {
        this.messageService.clear();
        this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error" });
        this.spinner.hide();
      } else {
        //  this.messageService.add({ key: 'msjCorrecta', sticky: true, severity: 'warn', summary: '', detail: '' });
        this.activeIndex = 0;

        let plazasDisponibles_init = this.service.getArbolPlazasParticipacion(this.idServidorPublico, '1');
        let plazasParticipacion_init = this.service.getArbolPlazasParticipacion(this.idServidorPublico, '0');

        forkJoin([plazasDisponibles_init, plazasParticipacion_init])
          .subscribe(resultado_group => {

            // this.getPlazasDisponibles();
            this.procesoEscalafonarioVigente = resultado_group[0].response.filter(x => x.PVISIBLE == '1');

            this.getPlazasParticipacion();
            this.plazasParticipacion = resultado_group[1].response;
            console.log(this.plazasParticipacion.length);
            if (this.plazasParticipacion.length == 0) {
              this.activaBotonPreinscripcion = false;
            } else {
              this.activaBotonPreinscripcion = true;

              //Hacemos el foco de la preinscripcion
              console.log('Realizamos el foco');
              setTimeout(() => {
                this.aplicarFoco = false;
                this.miPlazaInsc.nativeElement.focus();
                this.aplicarFoco = true;
              }, 1500);

              setTimeout(() => { this.aplicarFoco = false; }, 6000);
            }

            this.spinner.hide();
          }, error => {
            this.spinner.hide();
          });



        this.messageService.clear();
      }
    }, error => {
      this.spinner.hide();
    });

  }

  redirectCapacitacion() {
    this.router.navigate(['/datosProfesionales']);
  }

  redirectEscolaridad() {
    this.router.navigate(['/datosPersonales'], { queryParams: { redirect: '1' } });
  }

  redirectEncuesta() {
    this.router.navigate(['/consultaKPI']);
  }

  getAplicaProcesoEscalafonario() {
    this.spinner.show();
    this.service.getAplicaProcesoEscalafonario(this.idServidorPublico).subscribe(data => {
      if (data.response.length > 0) {
        this.aplicaProcesoEscalafonario = data.response[0];
        console.log('this.aplicaProcesoEscalafonario' + this.aplicaProcesoEscalafonario.BOINSCRITO);
      }
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
    });
  }

  getEscalafonSesionesAsesoria() {

    this.service.getEscalafonSesionesAsesoria(this.vigente).subscribe(data => {
      this.sesionesAsesoria = data.response;
    });
  }

  obtienePuntajeActual() {


    this.procesoEscalafonarioService.getAPuntajeEscalafonario(
      this.idServidorPublico, this.vigente).subscribe(data => {
        this.puntajeActual = data.response[0].TOTAL;
      });

  }

  getEscalafonEstadoInscripcion() {
    let procesoPreincripcion = {
      label: 'Preinscripción'
    }
    let procesoIncripcion = {
      label: 'Inscripción y Documentos validados'
    }
    let procesoExamen = {
      label: 'Exámen Validado'
    }
    let procesoFinalizado = {
      label: 'Proceso Finalizado'
    }
    let procesoCancelado = {
      label: 'Proceso Cancelado'
    }

    this.service.getEscalafonEstadoInscripcion(this.idServidorPublico, this.vigente).subscribe(data => {
      if (data.response.length > 0) {
        let idEstatusPlaza = data.response[0].IDESTATUSPLAZA;
        let cancelado = data.response[0].CANCELADO;
        let idMotivo = data.response[0].IDMOTIVO;
        let encuesta = data.response[0].ENCUESTA;
        console.log('encuesta:' + encuesta);
        if (encuesta == '0') {
          this.showAtencion = true;
        } else {
          this.showAtencion = false;
        }

        if (cancelado === 'true' && idMotivo != '001') {

          switch (idEstatusPlaza) {
            case '1':
              this.items2.push(procesoPreincripcion);
              break;
            case '2':
              this.items2.push(procesoPreincripcion);
              this.items2.push(procesoIncripcion);

              break;

            case '3':
              this.items2.push(procesoPreincripcion);
              this.items2.push(procesoIncripcion);
              this.items2.push(procesoExamen);

              break;

            case '4':
              this.items2.push(procesoPreincripcion);
              this.items2.push(procesoIncripcion);
              this.items2.push(procesoExamen);
              this.items2.push(procesoFinalizado);
              this.isFinalizado = true;
              break;
            default:
              break;
          }
          this.activeIndex = parseInt(idEstatusPlaza);
          this.items2.push(procesoCancelado);
        } else {
          this.items2.push(procesoPreincripcion);
          this.items2.push(procesoIncripcion);
          this.items2.push(procesoExamen);
          this.items2.push(procesoFinalizado);
          switch (idEstatusPlaza) {
            case '1':
              this.activeIndex = 0;
              break;
            case '2':
              this.activeIndex = 1;
              break;

            case '3':
              this.activeIndex = 2;
              break;

            case '4':
              this.activeIndex = 3;
              this.isFinalizado = true;
              break;
            default:
              break;
          }
        }
      }
    });
  }

  detalle(detalle: ConcursoEscalafonarioVigente) {
    this.logger.debug('detalle concurso=>' + JSON.stringify(detalle));

    this.dsrutaguiadescarga = detalle.RUTAGUIAESTUDIO;
    this.idGuiaPuesto = detalle.IDPUESTO;
    this.dsnombrePuestoGuia = detalle.NOMBREPUESTO;
    this.requisitosPuntajeMinimo = "1. Puntaje mínimo: " + detalle.PUNTAJEESCALAFONARIOMINIMO;
    this.idPlazaParticipacion = detalle.IDESTATUSPLAZA;
    this.escolaridadMinima = "2. Escolaridad: " + detalle.ESCOLARIRADMINIMA;
    this.experienciaMinima = "3. Experiencia mínima : " + detalle.EXPERIENCIAMINIMA + ' meses.';
    this.displayProcesoEscalafonario = true;
    this.service.getEscalafonSesionesAsesoria(this.vigente).subscribe(data => {
      this.sesionesAsesoria = data.response;
    });

    this.service.getEscalafonSesionesExamen(this.vigente).subscribe(dataExamen => {
      this.sesionesExamen = dataExamen.response;
    });

    this.service.getEscalafonEstadoInscripcion(this.idServidorPublico, this.vigente).subscribe(data => {
      this.aplicarValidacion = true;
      if (data.response.length > 0) {
        this.logger.debug('detalleDescripcionEstatus=>' + JSON.stringify(data.response));

        this.rankingDictamen = data.response[0].RANKING;
        this.idEstatusDictamen = data.response[0].ESTATUSDICTAMEN;
        let idEstatusPlaza = data.response[0].IDESTATUSPLAZA;
        this.idEstatusPlaza = idEstatusPlaza;
        let cancelado = data.response[0].CANCELADO;
        let idMotivo = data.response[0].IDMOTIVO;

        if (cancelado === 'true' && idMotivo != '001') {
          this.procesoCancelado = true;
          this.motivoCancelacion = data.response[0].MOTIVOCANCELACION;
          this.activeIndex = parseInt(idEstatusPlaza);
          this.MsgAtencion = this.showAtencion;

          if (this.showAtencion) {
            this.aplicarValidacion = false;
          } else {
            this.aplicarValidacion = true;
          }

          if (this.rankingDictamen !== '') {
            //this.activeIndex = 3;
            this.displayProcesoEscalafonario = true;
            this.detalleDescripcionEstatus = data.response[0].DESCRIPCIONESTATUS;
          }

        } else {
          switch (idEstatusPlaza) {
            case '1':
              this.activeIndex = 0;
              this.detalleDescripcionEstatus = data.response[0].DESCRIPCIONESTATUS;
              break;
            case '2':
              this.activeIndex = 1;
              this.displayProcesoEscalafonario = true;
              this.detalleDescripcionEstatus = data.response[0].DESCRIPCIONESTATUS;
              //  this.msgPublicaRes = true;
              //   this.MsgAtencion = false;
              //  this.aplicarValidacion = false;
              break;

            case '3':
              this.activeIndex = 2;
              this.displayProcesoEscalafonario = true;
              this.detalleDescripcionEstatus = data.response[0].DESCRIPCIONESTATUS;

              break;

            case '4':
              this.activeIndex = 3;
              this.MsgAtencion = this.showAtencion;
              this.valuePublicaRes = data.response[0].PUBLICARES;
              if (data.response[0].PUBLICARES === '0') {
                this.msgPublicaRes = true;
                let fechaaceptaFormato = moment(new Date(data.response[0].FECHAACEPTA), 'YYYY-MM-DD').toDate();
                this.fechaAcepta = fechaaceptaFormato;
                //Solo mostraremos el mensaje de publicacion de resultados.
                this.MsgAtencion = false;
              } else {
                this.msgPublicaRes = false;
              }

              if (this.showAtencion || this.msgPublicaRes) {
                this.aplicarValidacion = false;
              } else {
                this.aplicarValidacion = true;
              }

              this.displayProcesoEscalafonario = true;
              this.detalleDescripcionEstatus = data.response[0].DESCRIPCIONESTATUS;

              break;
            default:
              break;
          }

          if (data.response[0].RANKING === '1' && this.detalleDescripcionEstatus.length > 0 && this.valuePublicaRes == '1') {
            this.detalleDescripcionEstatus = this.detalleDescripcionEstatus + ' \n FAVORABLE';
          }

        }
      }
    });

  }

  descargaConstanciaParticipacion() {
    this.spinner.show();

    this.service.getDescargaConstanciaParticipacion(this.idServidorPublico, this.idPlazaParticipacion, this.vigente).subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;
      a.download = 'Constancia de Participacion.pdf';
      a.click();
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
      this.messageService.add({
        severity: 'warn', summary: 'Error',
        detail: 'No se ha podido descargar el documento.'
      });
    });
  }


  descargaCartaAceptacionUsuario(aceptoCarta: boolean) {
    this.spinner.show();

    this.service.getDescargaCartaAceptacionUsuario(
      this.idServidorPublico,
      this.vigente,
      aceptoCarta,
      this.idPlazaParticipacion,
      this.idEstatusDictamen,
      this.rankingDictamen
    ).subscribe(data => {
      // Actualizamos el estatus directamente
      if (aceptoCarta) {
        this.idEstatusDictamen = 'ACEPTADA';
      } else {
        this.idEstatusDictamen = 'RECHAZO DE PLAZA - PARTICIPACION CANCELADA';
        let existeStatusCancelado = false;
        const etapaCancelado = 'Proceso Cancelado';
        const procesoCancelado = { label: etapaCancelado };
        this.items2.forEach(q => {
          console.log('1=>' + JSON.stringify(q));
          if (JSON.stringify(q).includes(etapaCancelado)) {
            existeStatusCancelado = true;
          }
        });

        if (!existeStatusCancelado) {
          this.items2.push(procesoCancelado);
          this.activeIndex = 4;
        }
      }

      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;

      a.download = 'Constancia de aceptacion.pdf';
      a.click();

      this.spinner.hide();
    }, error => {
      this.spinner.hide();
      this.messageService.add({
        severity: 'warn', summary: 'Error',
        detail: 'No se ha podido descargar el documento.'
      });

    });
  }


  descargaCartaAceptacion() {
    this.spinner.show();

    this.service.getDescargaCartaAceptacion(this.idServidorPublico, this.idPlazaParticipacion, this.vigente).subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;

      a.download = 'Constancia de aceptacion.pdf';
      a.click();

      this.spinner.hide();
    }, error => {
      this.spinner.hide();
      this.messageService.add({
        severity: 'warn', summary: 'Error',
        detail: 'No se ha podido descargar el documento.'
      });

    });
  }


  descargaConstanciaRecepcionDocumentos() {
    this.spinner.show();
    this.service.getDescargaConstanciaRecepcionDocumentos(this.idServidorPublico, this.idPlazaParticipacion).subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;

      a.download = 'Constancia de recepcion de documentos.pdf';
      a.click();
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
      this.messageService.add({
        severity: 'warn', summary: 'Error',
        detail: 'No se ha podido descargar el documento.'
      });
    });
  }


  descargaGuiaEstudio() {
    this.spinner.show();
    this.service.getDescargarGuiaEstudio(this.dsrutaguiadescarga).subscribe(data => {

      this.logger.debug('this.dsrutaguiadescarga ' + this.dsrutaguiadescarga);


      const oMyBlob = new Blob([data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(oMyBlob);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;

      a.download = this.dsrutaguiadescarga + '.pdf';
      a.click();

      this.spinner.hide();

    }, error => {
      this.spinner.hide();
    });
  }



  getDescargaConvocatoria() {


    this.spinner.show();

    this.service.getDescargaConvocatoria(this.procesoEscalafonario.idProcesoVigente, this.idServidorPublico).subscribe(data => {

      const oMyBlob = new Blob([data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(oMyBlob);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;

      a.download = 'Convocatoria.pdf';
      a.click();
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
    });


  }

  redirectDatosProfesionales() {
    this.router.navigate(['/datosProfesionales'], { queryParams: { redirect: '1' } });
  }



}
