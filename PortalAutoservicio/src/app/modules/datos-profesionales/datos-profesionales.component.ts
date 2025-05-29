import { Component, OnInit, ViewChild } from '@angular/core';
import { DatosProfesionales } from 'src/app/models/datos-profesionales';
import { DatosprofesionalesService } from 'src/app/services/datosprofesionales.service';
import { ConfirmationService, MessageService, } from 'primeng/api';
import { Message } from 'primeng/api';
import { Table } from 'primeng/table/table';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { BitacoraService } from 'src/app/services/bitacora.service';
import { switchMap } from 'rxjs/operators';
import { GeneralesService } from 'src/app/services/generales.service';
import { Combo } from 'src/app/models/combo';
import { DatosProfesionalesDTO } from 'src/app/dto/datosProfesionalesDTO';
import { SeleccionDatoProfesionalProcesoDTO } from 'src/app/dto/seleccionDatoProfesionalProcesoDTO';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import * as moment from 'moment';
import { NgxSpinnerService } from 'ngx-spinner';
import { ConsultaDatosProfesionalesEtiquetas } from 'src/app/etiquetas/consulta-datos-profesionales-etiquetas';
import { Vaultuser } from 'src/app/models/Documentos/vault_user';
import { Vault } from 'src/app/models/Documentos/Vault';
import { ResponsNewDocument } from 'src/app/models/Documentos/response-new-document';
import { forkJoin } from 'rxjs';
import { ProcesoescalafonariovigenteService } from 'src/app/services/escalafon/procesoescalafonariovigente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-datos-profesionales',
  templateUrl: './datos-profesionales.component.html',
  styleUrls: ['./datos-profesionales.component.css']
})
export class DatosProfesionalesComponent implements OnInit {
  //#region Atributos de clase
  datosProfesionales: DatosProfesionales[] = [];
  displayDialog: boolean;
  msgs: Message[] = [];
  idServidorPublico: string = '';
  datosProfesionalesForm: FormGroup;
  cols: any[];
  @ViewChild('dt') table: Table;
  edicion: boolean;
  es: any = null;
  cmbTipoCertificado: Combo[] = [];
  cmbNivelEstudios: Combo[] = [];
  cmbEmisores: Combo[] = [];
  currentUser: DatosPersonales = new DatosPersonales();
  rangoAnios: string;
  cmbTipoDuracion: Combo[] = [];
  consultaDProfesionalesEtiquetas: ConsultaDatosProfesionalesEtiquetas = new ConsultaDatosProfesionalesEtiquetas();
  // Variables par la carga de documentos
  uploadedFiles: any[] = [];
  dialogDocIsVisible = false;
  // Variables mapa manejar los documentos del vault digital
  vaultuser: Vaultuser;
  documentosVault: Vault[] = [];
  idDocProfecional: string;
  idFileSelected: number;
  srcFile: string;
  defaultDate = false;
  isAddUpdate: boolean = false;
  mostrarInstrucciones: boolean = false;
  //Elementos que permiten la actualizacion
  boActualizaInfo = false;
  boEnableElements = true;
  isRedirect: boolean = false;
  @ViewChild('fechaEmisionCertificado') datePicker;
  //#endregion

  //#region Constructores
  constructor(private datosProfesionalesService: DatosprofesionalesService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private bitacoraService: BitacoraService,
    private generalesService: GeneralesService,
    private spinner: NgxSpinnerService,
    private confirmationService: ConfirmationService,
    private authenticationService: AuthenticationServiceService,
    private procesoescalafonariovigenteService: ProcesoescalafonariovigenteService,
    private router: Router,
    private logger: NGXLogger) { }

  ngOnInit(): void {


    let anio = moment(new Date()).format("YYYY");
    this.rangoAnios = '1995:' + anio;

    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
        this.idServidorPublico = usr.CLAVESERVIDOR;
      });
    }
    this.cols = [];

    //Validamos si se pueden ejecutar actualizaciones
    this.procesoescalafonariovigenteService.escalafonActualizaInfo().subscribe(result => {
      console.debug('result.response ->' + result.response);
      if (result.response == '1') {
        this.boActualizaInfo = false;
        this.boEnableElements = true;
      } else {
        this.boActualizaInfo = true;
        this.boEnableElements = false;
      }
    });


    this.obtenerEtiquetasDatosProfesionaes();
    // Llamada a los servicios para obtener los datos
    this.obtenerDatosProfesionales(this.idServidorPublico);
    this.iniciarForm();

    let parametros_url = this.router.parseUrl(this.router.url);
    if (parametros_url.queryParams['redirect'] == '1') {
      this.isRedirect = true;
      window.scroll(0, 0);
    }
    console.debug('this.isRedirect ->', this.isRedirect);
  }
  //#endregion

  //#region Funciones publicas
  close() {
    this.datePicker.overlayVisible = false;
  }

  cargarCatByAddUpdate(isAdd: boolean, dataUpdate: DatosProfesionales) {
    this.spinner.show();

    if (this.cmbTipoCertificado.length == 0 || this.cmbEmisores.length == 0) {

      this.iniciaCalendario();
      this.cargaComboTipoDuracion();

      let catTipoCertificado = this.generalesService.consultaCatalogoTipoCertificado();
      let catEmisorCertificado = this.datosProfesionalesService.obtenerEmisorCertificado();

      forkJoin([catTipoCertificado, catEmisorCertificado]).subscribe(dataCat => {

        //Carga tipo certificado
        for (let index = 0; index < dataCat[0].response.length; index++) {
          let certificado = new Combo();
          certificado.label = dataCat[0].response[index].NOMBRECERTIFICADO;
          certificado.value = dataCat[0].response[index].IDTIPOCERTIFICADO;
          this.cmbTipoCertificado.push(certificado);
        }

        //Carga catalogo de emisores
        for (let index = 0; index < dataCat[1].response.length; index++) {
          let emisor = new Combo();
          emisor.label = dataCat[1].response[index].NOMBREEMISOR;
          emisor.value = dataCat[1].response[index].IDEMISOR;
          this.cmbEmisores.push(emisor);
        }

        if (!isAdd) {
          this.seleccionaDatoProfesional(dataUpdate);
        }

        this.isAddUpdate = true;

        this.spinner.hide();
      }, err => {
        this.spinner.hide();
        this.messageService.add({ severity: 'error', summary: 'Resultado', detail: 'Ocurrió un error al intentar cargar los datos.' });
      });
    } else {
      if (!isAdd) {
        this.seleccionaDatoProfesional(dataUpdate);
      }
      this.isAddUpdate = true;
      this.spinner.hide();
    }
  }

  obtenerDatosProfesionales(idServidorPublico: string) {
    this.spinner.show();
    this.datosProfesionalesService.obtenerDatosProfesionales(idServidorPublico).subscribe(data => {
      this.datosProfesionales = data.response;
      // Realizamos la consulta de los documentos
      if (this.datosProfesionales.length > 0) {
        this.CargaDocumentosProfesionales();
      } else {
        this.spinner.hide();
      }

      // Si no Consultamos documentos terminamos el spiner
      // this.spinner.hide();
    }, err => {
      this.spinner.hide();
      this.messageService.add({ key: 'd', severity: 'error', summary: 'Resultado', detail: 'Ocurrio un error' });
    });
  }

  showSaveDialog() {
    //Inicializamos catalogos.
    this.cargarCatByAddUpdate(true, null);
    this.displayDialog = true;
    this.iniciarForm();
    this.edicion = false;

    console.log('datosProfesionalesForm' + JSON.stringify(this.datosProfesionalesForm.value));
    console.log('datosProfesionalesForm' + JSON.stringify(this.es));
    console.log('this.cmbTipoDuracion' + JSON.stringify(this.cmbTipoDuracion))

  }

  confirmacionDatosProfesionales() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para continuar' });
  }

  guardarDatosProfesionales() {
    this.spinner.show();
    let idDatoProfesional: string;
    let datosProfesionalesDTO = new DatosProfesionalesDTO();
    console.log(this.datosProfesionalesForm);

    datosProfesionalesDTO.idNivelEstudios = this.datosProfesionalesForm.value['idNivelEstudios'];
    datosProfesionalesDTO.fechaEmisionCertificado = this.datosProfesionalesForm.value['fechaEmisionCertificado'];
    console.log('this.datosProfesionalesForm.value[fechaEmisionCertificado]=>' + this.datosProfesionalesForm.value['fechaEmisionCertificado']);

    if (this.defaultDate) {
      const fcCerti = moment(new Date(4000, 0, 1), 'YYYY-MM-DD').toDate();
      console.log('fcCerti.toString()=>' + fcCerti.toString());
      datosProfesionalesDTO.fechaVigenciaCertificado = fcCerti.toString();
    } else {
      datosProfesionalesDTO.fechaVigenciaCertificado = this.datosProfesionalesForm.value['fechaVigenciaCertificado'];
    }

    datosProfesionalesDTO.nombreCertificado = this.datosProfesionalesForm.value['nombreCertificado'];
    datosProfesionalesDTO.idTipoCertificado = this.datosProfesionalesForm.value['idTipoCertificado'];
    datosProfesionalesDTO.noCertificado = this.datosProfesionalesForm.value['noCertificado'];
    datosProfesionalesDTO.idEmisor = this.datosProfesionalesForm.value['idEmisor'];
    datosProfesionalesDTO.duracion = this.datosProfesionalesForm.value['duracion'];
    datosProfesionalesDTO.tipoDuracion = this.datosProfesionalesForm.value['idTipoDuracion'];

    if (this.edicion) {
      idDatoProfesional = this.datosProfesionalesForm.value['idDatoProfesional'];;
    } else {
      idDatoProfesional = '0';
    }


    /*
    this.datosProfesionalesService.insertarActualizarDatosProfesionales(this.idServidorPublico, idDatoProfesional, 
    datosProfesionalesDTO).subscribe(result=>{
     
      this.displayDialog = false; //Ocultamos la ventana emergente con el formulario.
      this.messageService.clear('c'); //Quitamos el mensaje de confirmacion

      this.bitacoraService.registrar(this.idServidorPublico, 2, 2).subscribe(() => {
        console.log('se registro bitacora');
      });

      if (this.edicion){
        this.datosProfesionales.map(item =>{
          if(item.IDDATOPROFESIONAL == idDatoProfesional){
            item.FECHAEMISIONCERTIFICADO = datosProfesionalesDTO.fechaEmisionCertificado;
            item.FECHAVIGENCIACERTIFICADO = datosProfesionalesDTO.fechaVigenciaCertificado;
            item.NOMBRECERTIFICADO = datosProfesionalesDTO.nombreCertificado;
            item.IDTIPOCERTIFICADO = datosProfesionalesDTO.idTipoCertificado;
            item.NOCERTIFICADO = datosProfesionalesDTO.noCertificado;
            item.IDEMISORCERTIFICADO = datosProfesionalesDTO.idEmisor;
            item.DURACION = datosProfesionalesDTO.duracion;
            item.TIPODURACION = datosProfesionalesDTO.tipoDuracion;

            item.DESCRIPCIONCERTIFICADO = this.cmbTipoCertificado.find(
              x => x.value == this.datosProfesionalesForm.value['idTipoCertificado']
            ).label;
            
            item.NOMBREEMISORCERTIFICADO = this.cmbEmisores.find(
              x=> x.value == this.datosProfesionalesForm.value['idEmisor']
            ).label;
          }
        });
          this.spinner.hide();
          this.messageService.add({ key: 'd', severity: 'success', summary: "Resultado", detail: "La actualización se realizó correctamente." });
      }else{
         //Si ingresamos datos consultamos nuevamente los datos.
         this.datosProfesionalesService.obtenerDatosProfesionales(this.idServidorPublico).subscribe(resultDatosP =>{
          for (let index = 0; index < resultDatosP.response.length; index++) {
            console.log('Analisando:' + resultDatosP.response[index].IDDATOPROFESIONAL);
            if(this.datosProfesionales
                   .filter(x=> {x.IDDATOPROFESIONAL == resultDatosP.response[index].IDDATOPROFESIONAL}).length == 0){
                     console.log('Se encontro el dato faltante:' + resultDatosP.response[index].IDDATOPROFESIONAL);
                     resultDatosP.response[index].IDFILE = 0;
                     this.datosProfesionales.push(resultDatosP.response[index]);
                     break;
                    }
          }

          this.spinner.hide();
          this.messageService.add({ key: 'd', severity: 'success', summary: "Resultado", detail: "El registro se realizó correctamente." });
        }, err=>{
          this.spinner.hide();
          this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error al actualizar la pantalla." });
        });
      }
    }, err =>{
      this.spinner.hide();
      this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error al realizar la operación." });
    } );

    */

    this.datosProfesionalesService.insertarActualizarDatosProfesionales(this.idServidorPublico,
      idDatoProfesional, datosProfesionalesDTO).pipe(switchMap(() => {
        return this.datosProfesionalesService.obtenerDatosProfesionales(this.idServidorPublico);
      })).subscribe(data => {
        this.datosProfesionales = data.response;

        this.messageService.clear('c');
        this.messageService.add({ key: 'd', severity: 'success', summary: "Resultado", detail: "La operación se realizó correctamente." });
        this.displayDialog = false;
        this.bitacoraService.registrar(this.idServidorPublico, 2, 2).subscribe(() => {
          console.log('se registro bitacora');
        });
        this.spinner.hide();
      }, err => {
        this.spinner.hide();
        this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error" });
      });
  }

  rechazar() {
    this.messageService.clear('c');
  }

  no() {
    this.close();
    this.displayDialog = false;
  }

  closeDialogAdd(event) {
    if (event.target.className.includes("pi pi-times")) {
      this.no();
    }
  }

  seleccionaDatoProfesional(datos: DatosProfesionales) {
    console.log(datos);

    this.edicion = true;
    let fechaEmisionCertificado = moment(datos.FECHAEMISIONCERTIFICADO, 'YYYY-MM-DD').toDate();
    let fechaVigenciaCertificado = moment(datos.FECHAVIGENCIACERTIFICADO, 'YYYY-MM-DD').toDate();

    console.log('fechaVigenciaCertificado=>' + fechaVigenciaCertificado);

    this.datosProfesionalesForm = this.fb.group({
      //'idNivelEstudios': new FormControl(datos.IDNIVELESTUDIOS, Validators.required),
      'fechaEmisionCertificado': new FormControl(fechaEmisionCertificado, Validators.required),
      'fechaVigenciaCertificado': new FormControl(fechaVigenciaCertificado),
      'nombreCertificado': new FormControl(datos.NOMBRECERTIFICADO, Validators.required),
      'idTipoCertificado': new FormControl(datos.IDTIPOCERTIFICADO, Validators.required),
      'noCertificado': new FormControl(datos.NOCERTIFICADO),
      'idDatoProfesional': new FormControl(datos.IDDATOPROFESIONAL),
      'idEmisor': new FormControl(datos.IDEMISORCERTIFICADO, Validators.required),
      'idTipoDuracion': new FormControl(datos.TIPODURACION, Validators.required),
      'duracion': new FormControl(Math.trunc(parseInt(datos.DURACION)).toString(), Validators.required)
    });

    //this.actualizaFechaFinaliza();
    this.analizarFecha();
    this.displayDialog = true;
  }

  eliminaDatoProfesional(datos: DatosProfesionales) {
    this.messageService.clear('c');
    this.confirmationService.confirm({
      message: "¿Estás seguro de que deseas eliminar el registro?",
      accept: () => {
        this.spinner.show();
        this.datosProfesionalesService.eliminarDatosProfesionales(this.idServidorPublico,
          datos.IDDATOPROFESIONAL).subscribe(result => {

            this.datosProfesionales = this.datosProfesionales.filter(x => x.IDDATOPROFESIONAL != datos.IDDATOPROFESIONAL);

            this.messageService.clear('c');
            this.messageService.add({ key: 'd', severity: 'success', summary: "Resultado", detail: "Se eliminó correctamente." });
            this.displayDialog = false;

            this.bitacoraService.registrar(this.idServidorPublico, 3, 3).subscribe(() => {
              console.log('se registro bitacora');
            });
            this.spinner.hide();
          }, err => {
            this.spinner.hide();
            this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error" });
          });

      }
    });
  }

  iniciarForm() {
    this.datosProfesionalesForm = this.fb.group({
      //'idNivelEstudios': new FormControl('', Validators.required),
      'fechaEmisionCertificado': new FormControl('', Validators.required),
      'fechaVigenciaCertificado': new FormControl(''),
      'nombreCertificado': new FormControl('', Validators.required),
      'idTipoCertificado': new FormControl('', Validators.required),
      'noCertificado': new FormControl(''),
      'idDatoProfesional': new FormControl(''),
      'idEmisor': new FormControl('', Validators.required),
      'idTipoDuracion': new FormControl('', Validators.required),
      'duracion': new FormControl('', Validators.required)
    });
  }


  iniciaCalendario() {
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"],
      monthNames: ["enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"],
      monthNamesShort: ["ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"],
      today: 'Hoy',
      clear: 'Borrar'
    }
  }

  obtenerEtiquetasDatosProfesionaes() {
    this.datosProfesionalesService.getEtiquetasDatosProfesionales().subscribe(data => {
      this.consultaDProfesionalesEtiquetas = data;
      this.construyeGrid();
    });
  }

  construyeGrid() {
    this.cols.push(this.construyeColumnas("FECHAEMISIONCERTIFICADO", this.consultaDProfesionalesEtiquetas.fechaEmision));
    this.cols.push(this.construyeColumnas("FECHAVIGENCIACERTIFICADO", this.consultaDProfesionalesEtiquetas.vigenciaCertificado));
    this.cols.push(this.construyeColumnas("NOMBRECERTIFICADO", this.consultaDProfesionalesEtiquetas.nombreCertificado));
    this.cols.push(this.construyeColumnas("IDTIPOCERTIFICADO", this.consultaDProfesionalesEtiquetas.certificado));
    this.cols.push(this.construyeColumnas("NOCERTIFICADO", this.consultaDProfesionalesEtiquetas.noCertificado));
    this.cols.push(this.construyeColumnas("NOMBREEMISORCERTIFICADO", this.consultaDProfesionalesEtiquetas.emisor));
  }

  construyeColumnas(field: string, columna: string) {
    let column = {
      "field": field,
      "header": columna
    };
    return column;
  }

  cargaComboTipoDuracion() {
    let tipoDuracion1 = new Combo();
    tipoDuracion1.label = 'Horas';
    tipoDuracion1.value = '1';
    let tipoDuracion2 = new Combo();
    tipoDuracion2.label = 'Créditos';
    tipoDuracion2.value = '2';
    this.cmbTipoDuracion.push(tipoDuracion1);
    this.cmbTipoDuracion.push(tipoDuracion2);
  }


  onUpload(event: any) {
    this.spinner.show();
    this.datosProfesionalesService.agregarDocumento(this.vaultuser.userId.toString(), event.files[0], this.idDocProfecional)
      .subscribe(data => {
        if (data.status) {
          const document: ResponsNewDocument = JSON.parse(data.message);
          if (document.fileId > 0) {
            this.datosProfesionales.find(prof => prof.IDDATOPROFESIONAL === this.idDocProfecional).IDFILE = document.fileId;
            this.datosProfesionalesService.GetDocumentoVault(this.vaultuser.userId, document.fileId).subscribe(dataDocumento => {
              console.log('Archivo obtenido');
              const url = window.URL.createObjectURL(dataDocumento);
              this.srcFile = url;
              this.spinner.hide();
              this.showMessageExitoso('El documento se cargo correctamente');
            }, error => {
              this.spinner.hide();
              this.showMessageExitoso('El documento se cargo correctamente pero no se pudo mostrar');
            });

          } else {
            this.spinner.hide();
            this.showMessageError('Ocurrio un error al subir el documento.');
          }

        } else {
          this.spinner.hide();
          this.showMessageError('Ocurrio un error al subir el documento');
        }
      }, err => {
        this.spinner.hide();
        this.showMessageError('Ocurrio un error al subir el documento');
      });
  }

  closeDialogDoc() {
    this.dialogDocIsVisible = false;
  }

  seleccionaDocumento(datos: DatosProfesionales) {
    this.spinner.show();

    this.idDocProfecional = datos.IDDATOPROFESIONAL;
    this.idFileSelected = datos.IDFILE;
    this.srcFile = '';
    if (datos.IDFILE > 0) {
      this.datosProfesionalesService.GetDocumentoVault(this.vaultuser.userId, datos.IDFILE).subscribe(dataDocumento => {
        const url = window.URL.createObjectURL(dataDocumento);
        this.srcFile = url;
        this.spinner.hide();
        this.dialogDocIsVisible = true;
      }, error => {
        this.spinner.hide();
        this.dialogDocIsVisible = false;
        this.showMessageError('No se pudo obtener el documento');
      });
    } else {
      this.spinner.hide();
      this.dialogDocIsVisible = true;
    }
  }

  /**
   * Consulta los documentos del usuario, si el usuario no esta registrado realiza su registro con una contraseña por default
   */
  CargaDocumentosProfesionales() {
    this.datosProfesionalesService.InicioSessionDocumentos(this.idServidorPublico).subscribe(login => {
      if (login.userId !== 0) {
        this.vaultuser = login;
        // Continuamos con la obtencion de datos
        this.GetListDocumentos(this.vaultuser.userId);
      } else {
        // Realizamos el registro del usuario
        this.datosProfesionalesService.RegistrarUsuarioVault(this.idServidorPublico).subscribe(adduser => {
          if (adduser.status === true) {
            console.log('Usuaio registrado correctamente =>');
            this.datosProfesionalesService.InicioSessionDocumentos(this.idServidorPublico).subscribe(loginUserReg => {
              if (loginUserReg.userId !== 0) {
                this.vaultuser = loginUserReg;
                console.log('this.vaultuser.userId =>' + this.vaultuser.userId);
                // Continuamos con la obtencion de datos
                this.GetListDocumentos(this.vaultuser.userId);
              } else {
                this.spinner.hide();
                this.showMessageError('No se pudieron obtener los documentos');
              }
            });
          } else {
            this.spinner.hide();
            this.showMessageError('No se pudieron obtener los documentos, favor de validar.');
          }
        });
      }
    }, error => {
      this.spinner.hide();
      this.showMessageError('No se pudieron obtener los documentos');
    });
    this.spinner.hide();
  }

  GetListDocumentos(userId: number) {
    console.log('2.0 Consuslta Documentos');

    this.datosProfesionalesService.RecuperarDocumentosByUsuario(userId).subscribe(documentos => {
      this.documentosVault = documentos;
      this.datosProfesionales.forEach(element => {
        const documentoTrabajo = this.documentosVault.filter(item => item.idDocProfesional === parseInt(element.IDDATOPROFESIONAL));

        if (documentoTrabajo.length > 0) {
          element.IDFILE = documentoTrabajo[0].fileId;
        } else {
          element.IDFILE = 0;
        }
      });
      this.spinner.hide();
    }, error => {
      this.spinner.hide();
      this.showMessageError('No se pudieron obtener los documentos: 584');
    });
  }


  showMessageError(vdetail: string) {
    this.messageService.add({ key: 'd', severity: 'error', summary: 'Error', detail: vdetail });
  }

  showMessageExitoso(vdetail: string) {
    this.messageService.add({ key: 'd', severity: 'success', summary: 'Éxito', detail: vdetail });
  }


  /**
   * Calcula la fecha de vigenia segun el tipo de sertificado y la fecha de inicio
   */
  actualizaFechaFinaliza() {
    this.defaultDate = false;
    const fechaEmisionCertificado = this.datosProfesionalesForm.value.fechaEmisionCertificado;
    const idcertificado = this.datosProfesionalesForm.value.idTipoCertificado;

    console.log('idcertificado=>' + idcertificado);
    console.log('fechaEmisionCertificado=>' + fechaEmisionCertificado);

    if (idcertificado === '' || fechaEmisionCertificado === '') {
      this.datosProfesionalesForm.controls.fechaVigenciaCertificado.setValue('');
      this.defaultDate = true;
    } else if ((idcertificado === '01' || idcertificado === '03') && fechaEmisionCertificado !== '') {
      this.datosProfesionalesForm.controls.fechaVigenciaCertificado.setValue(this.getNewDate(fechaEmisionCertificado, 2));
    } else if (idcertificado === '02' && fechaEmisionCertificado !== '') {
      this.datosProfesionalesForm.controls.fechaVigenciaCertificado.setValue(this.getNewDate(fechaEmisionCertificado, 4));
    } else if (idcertificado === '04' && fechaEmisionCertificado !== '') {
      this.datosProfesionalesForm.controls.fechaVigenciaCertificado.setValue('');
      this.defaultDate = true;
    }
  }

  /**
   * Se calcua la fecha de fijencia segun el tipo de constancia.
   * @param fcInicio Fecha de inicio de la constancia
   * @param aniosAdd Años de vigencia
   */
  getNewDate(fcInicio: string, aniosAdd: number) {
    const fechainicio = new Date(fcInicio);
    const year = fechainicio.getFullYear();
    const month = fechainicio.getMonth();
    const day = fechainicio.getDate();

    const fechaEmisionCertificado55 = moment(new Date(year + aniosAdd, month, day), 'YYYY-MM-DD').toDate();

    console.log('Fecha de vigencia:' + fechaEmisionCertificado55);
    return fechaEmisionCertificado55;
  }

  analizarFecha() {
    const fechaEmisionCertificado = this.datosProfesionalesForm.value.fechaVigenciaCertificado;

    if (fechaEmisionCertificado !== '') {
      const fechainicio = new Date(fechaEmisionCertificado);
      const year = fechainicio.getFullYear();
      const month = fechainicio.getMonth();
      const day = fechainicio.getDate();

      console.log(`year=>${year}month=>${month}day=>${day}`);
      if (year === 4000 && month === 0 && day === 1) {
        this.datosProfesionalesForm.controls.fechaVigenciaCertificado.setValue('');
      }
    }
  }
  //#endregion

  //#region Eventos
  /**
   * Validar si el certificado es apto para seleccion de acuerdo con reglas de negocio.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 13/03/2022.
   * @param datosProfesionales Objeto seleccionado en la vista.
  */
  cboCheckCertificadoOnChange(datosProfesionales: DatosProfesionales, control) {
    let datosProfesionalesTipo: DatosProfesionales[] = this.datosProfesionales.filter(dp => {
      return dp.IDTIPOCERTIFICADO === datosProfesionales.IDTIPOCERTIFICADO && dp.SELPROCESO === true
    });
    let datoProfesional: DatosProfesionales = this.datosProfesionales.find(dp => {
      return dp.IDDATOPROFESIONAL === datosProfesionales.IDDATOPROFESIONAL
    });

    switch (datosProfesionales.IDTIPOCERTIFICADO) {
      case '01'://Curso, maximo 4
        if (datosProfesionalesTipo.length > 4) {
          this.showMessageError('Solo se pueden seleccionar hasta 4 Cursos, favor de validar.');
          datoProfesional.SELPROCESO = false;
          control.checked = false;
        }
        break;
      case '02'://Diplomados, maximo 4
        if (datosProfesionalesTipo.length > 4) {
          this.showMessageError('Solo se pueden seleccionar hasta 4 Diplomados, favor de validar.');
          datoProfesional.SELPROCESO = false;
          control.checked = false;
        }
        break;
      case '03'://Induccion, maximo 1
        if (datosProfesionalesTipo.length > 1) {
          this.showMessageError('Solo se pueden seleccionar hasta 1 Inducción, favor de validar.');
          datoProfesional.SELPROCESO = false;
          control.checked = false;
        }
        break;
      case '04'://Certificado, maximo 2
        if (datosProfesionalesTipo.length > 2) {
          this.showMessageError('Solo se pueden seleccionar hasta 2 Certificados, favor de validar.');
          datoProfesional.SELPROCESO = false;
          control.checked = false;
        }
        break;
      default:
        this.showMessageError('El tipo de documento no se puede seleccionar, favor de validar.');
        datoProfesional.SELPROCESO = false;
        control.checked = false;
        break;
    }
  }

  /**
   * Evento ejecutado al dar click en el boton de guardar para mostrar 
   * modal de confirmacion de procesamiendo de seleccion de registros 
   * para proceso escalafonario.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 17/03/2022.
  */
  btnGuardarOnClick() {
    this.messageService.clear();
    this.messageService.add({
      key: 'confirmarSeleccion',
      sticky: true,
      severity: 'warn',
      summary: '¿Estás seguro?',
      detail: 'Confirmar para continuar'
    });
  }

  btnRechazarSeleccionOnClick() {
    this.messageService.clear('confirmarSeleccion');
  }

  bntAceptarSeleccionOnClick() {
    this.spinner.show();
    let lsSeleccionDatoProfesionalSeleccionDTO: Array<SeleccionDatoProfesionalProcesoDTO> = [];

    this.datosProfesionales.forEach(dp => {
      lsSeleccionDatoProfesionalSeleccionDTO.push({
        ORDINAL: dp.IDDATOPROFESIONAL,
        SELECCIONADO: dp.SELPROCESO
      });
    });

    console.table(lsSeleccionDatoProfesionalSeleccionDTO);

    this.datosProfesionalesService.actualizarRegistrosProceso(
      this.idServidorPublico,
      lsSeleccionDatoProfesionalSeleccionDTO
    ).subscribe(data => {
      console.debug('data => ', data);
      //Ocular spinner
      this.spinner.hide();
      //Limpiar mensajes
      this.messageService.clear('confirmarSeleccion');
      //Mostrar mensaje de exito
      this.messageService.add({
        key: 'd',
        severity: 'success',
        summary: "Resultado",
        detail: "La operación se realizó correctamente."
      });
    }, err => {
      //Ocular spinner
      this.spinner.hide();
      //Limpiar mensajes
      this.messageService.clear('confirmarSeleccion');
      //Mostrar mensaje de error
      this.messageService.add({
        key: 'd',
        severity: 'error',
        summary: "Resultado",
        detail: "Ocurrió un error; " + err.message
      });
    });
  }

  //#endregion
}
