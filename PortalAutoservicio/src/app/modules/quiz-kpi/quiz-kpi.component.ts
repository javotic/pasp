import { Component, OnInit, Output, ViewChild } from '@angular/core';
import { QuestionsKPI } from 'src/app/models/questions';
import { AnswerKPI } from 'src/app/models/answers';
import { QuestionkpiService } from 'src/app/services/questionkpi.service';
import { QuizConfig } from 'src/app/models/quiz-config';
import { MessageService } from 'primeng/api';
import { EnvioRespuestasKpi } from 'src/app/models/envio-respuestas-kpi';
import { NGXLogger } from 'ngx-logger';
import { CountdownComponent, CountdownEvent } from 'ngx-countdown';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProcesoKpi } from 'src/app/models/proceso-kpi';
import * as moment from 'moment';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
import { NotificacionesService } from 'src/app/services/notificaciones.service';

@Component({
  selector: 'app-quiz-kpi',
  templateUrl: './quiz-kpi.component.html',
  styleUrls: ['./quiz-kpi.component.css']
})
export class QuizKpiComponent implements OnInit {
  @Output() question: QuestionsKPI;
  questionID = 0;

  checked: boolean = false;
  
  question2: ProcesoKpi = new ProcesoKpi();;
  mode = "quiz";
  pager = {
    index: 0,
    size: 1,
    count: 1
  };

  config: QuizConfig = {
    allowBack: false,
    allowReview: false,
    autoMove: true, // if true, it will move to next question automatically when answered.
    duration: 300, // indicates the time (in secs) in which quiz needs to be completed. 0 means unlimited.
    pageSize: 1,
    requiredAll: false, // indicates if you must answer all the questions before submitting.
    richText: false,
    shuffleQuestions: false,
    shuffleOptions: false,
    showClock: false,
    showPager: true,
    theme: "none"
  };
  
  hideButon: boolean = false;
  value: number = 0;
  progressbar: number = 0;
  configtimer = {};
  @ViewChild('cd1', { static: false }) private timer: CountdownComponent;
  idServidorPublico: string = '';
  idProceso: string = '';
  haveTime: string = '';
  timeP: string;
  answersQuestion: EnvioRespuestasKpi[] = [];

  constructor(
    private questionkpiService: QuestionkpiService,
    private messageService: MessageService,
    private logger: NGXLogger,
    private authenticationService: AuthenticationServiceService,
    private route: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private notificacionesService: NotificacionesService
    ) { }

  ngOnInit(): void {
    //Recupera los parametros de la URL
    this.route.queryParams
      .subscribe(params => {
        this.idProceso = params.idProceso;
        this.haveTime = params.haveTime;
        this.timeP = params.timeP;
        //this.timeP = '10';
        //this.haveTime = '1';

      });
    //recupero el usuario en sesion
    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(us => {
        this.idServidorPublico = us.CLAVESERVIDOR;
        //Cuando obtenemos los datos del servidor publico notificamos a BD que iniciamos la encuesta
        let ans: EnvioRespuestasKpi = new EnvioRespuestasKpi();
        ans.idPregunta = '0';
        ans.idRespuesta = '0';
        ans.idProcesoVigente = this.idProceso;
        ans.idServidorPublico = this.idServidorPublico;
        ans.tiempo = '';
        this.answersQuestion.push(ans);

        //Ejecutamos el guardado
        this.guardarPreguntas();
      })

    }
    //obtiene el proceso con las preguntas
    this.questionkpiService.getAllQuestion2(this.idServidorPublico, this.idProceso).subscribe(
      data => {
        this.logger.debug('GET PROCESO KPI =>>')
        console.log(data);
        let resp = data.response;
        var myJSON = JSON.stringify(resp);
        this.question2 = <ProcesoKpi>JSON.parse(myJSON);
        this.logger.debug('Lista noo ordenada =>', this.question2);
        this.pager.count = this.question2.PREGUNTASKPI.length;
        this.question2.PREGUNTASKPI.sort((a, b) => a.IDPREGUNTA - b.IDPREGUNTA);
        this.logger.debug('Lista ordenada =>', this.question2);
      });

    let v: string = '';
    v.localeCompare

    let interval = setInterval(() => {
      this.value = this.value + Math.floor(Math.random() * 10) + 1;
      if (this.value >= 100) {
        this.value = 100;
        this.messageService.add({ severity: 'info', summary: 'Success', detail: 'Process Completed' });
        clearInterval(interval);
      }
    }, 2000);
    //si hay tiempo inicia el timer
    if (this.haveTime != '0') {
      this.iniciarTimer();
    }

  }
  /**
   * Obtiene la lista de preguntas
   */
  get getQuestion(): QuestionsKPI {
    return this.question2.PREGUNTASKPI.filter(
      question => question.IDPREGUNTA === this.questionID
    )[0];
  }
  /**
   * paginado de las preguntas
   */
  get filteredQuestions() {
    // console.log('hola');
    // console.log(this.question2);
    return this.question2.PREGUNTASKPI
      ? this.question2.PREGUNTASKPI.slice(
        this.pager.index,
        this.pager.index + this.pager.size
      )
      : [];
  }

  onSelect(question: QuestionsKPI, option: AnswerKPI) {
    this.logger.debug('Question=>', question);
    this.logger.debug('IDPROCEDIMINETOVIGENTE===>>>', question.IDPROCESOVIGENTE);

    //Borramos las respuestas anteriores
    this.answersQuestion = this.answersQuestion.filter(x=> x.idPregunta !== question.IDPREGUNTA.toString());

    //Limpiamos las respuesta seleccionadas
    if(question.MAXRESPUESTA ===  1){
      let ans: EnvioRespuestasKpi = new EnvioRespuestasKpi();
      ans.idPregunta = question.IDPREGUNTA + '';
      ans.idProcesoVigente = question.IDPROCESOVIGENTE + '';
      ans.idRespuesta = option.IDRESPUESTA + '';
      ans.idServidorPublico = this.idServidorPublico;
      ans.tiempo = '';
      this.answersQuestion.push(ans);
    }else{
      question.RESPUESTASKPI.filter(x=> x.selected == true).forEach(resp =>{
        let ans2: EnvioRespuestasKpi = new EnvioRespuestasKpi();
        ans2.idPregunta = resp.IDPREGUNTA + '';
        ans2.idProcesoVigente = resp.IDPROCESOVIGENTE + '';
        ans2.idRespuesta = resp.IDRESPUESTA + '';
        ans2.idServidorPublico = this.idServidorPublico;
        ans2.tiempo = '';
        this.answersQuestion.push(ans2);
      });
    }  
  }

  /**
   * Mostrar la siguiente pregunta
   * @param index 
   */
  goTo(index: number) {

    console.log('this.question2.PREGUNTASKPI.length=>' + this.question2.PREGUNTASKPI.length);
    this.logger.debug('Preguntas => ', this.question2.PREGUNTASKPI[index - 1])
    let preg: QuestionsKPI = this.question2.PREGUNTASKPI[index - 1];
    let resp: AnswerKPI = new AnswerKPI();
    let preguntaSelect: boolean = false;
    let seleccionadas: number = 0;
    for (let x of preg.RESPUESTASKPI) {
      if (x.selected) {
        seleccionadas++;
        preguntaSelect = true;
      }

      if (x.selected && x.SIGUIENTEPREGUNTA !== '') {
        resp = x;
        //contestar por defaut las preguntas saltadas
        //this.question2.PREGUNTASKPI
        console.log('this.question2.PREGUNTASKPI.length2=>' + this.question2.PREGUNTASKPI.length);
        this.saltarPreguntas(x.IDPREGUNTA, x.SIGUIENTEPREGUNTA, x.SIGUIENTERESPUESTA, this.question2.PREGUNTASKPI);
      }
    }

    // Agregamos validacion para para permitir solo el maximo de opciones (aplica solo cuando puede ser mas de a)
    if(preg.MAXRESPUESTA > 1 && seleccionadas > preg.MAXRESPUESTA){
      this.messageService.clear();
      this.messageService.add({ key: 'errorToast', severity: 'warn', 
                                summary: 'No se pueden seleccionar más de ' + preg.MAXRESPUESTA + ' opciones' });
      return;
    }

    if (!preguntaSelect) {
      this.messageService.clear();
      this.messageService.add({ key: 'kpirequired', sticky: true, severity: 'warn', summary: 'No ha seleccionado una respuesta', detail: '', life: 2 });
      return;
    }

    //Ya que pasan las validaciones ejecutamos el guardado
    //this.guardarPreguntas();

    if (JSON.stringify(resp) !== '{}') {
      for (var i = 0; i < this.question2.PREGUNTASKPI.length; i++) {
        this.logger.debug('p=>', this.question2.PREGUNTASKPI[i]);
        this.logger.debug('compare=>', resp.SIGUIENTEPREGUNTA.localeCompare(this.question2.PREGUNTASKPI[i].IDPREGUNTA.toString()));
        if (resp.SIGUIENTEPREGUNTA.localeCompare(this.question2.PREGUNTASKPI[i].IDPREGUNTA.toString()) === 0) {
          index = i;
          index + 1;
          this.logger.debug('index=>', index);
        }
      }
    }

    if (index >= 0 && index < this.pager.count) {
      this.pager.index = index;
      this.mode = "quiz";
    }

    if (index >= this.pager.count) {
      this.hideButon = true;
      this.messageService.clear();
      this.messageService.add({ key: 'kpiQuiz', sticky: true, severity: 'warn', summary: '', detail: '' });

    }

    this.progressbar = Number(((index * 100) / this.question2.PREGUNTASKPI.length).toFixed(0));

  }
  /**
   * cerrar dialogo
   */
  cerrarDialog() {
    this.messageService.clear();
  }
  /**
   * enviar las preguntas
   */
  onSubmit() {
    this.timer.stop;
    let fecha = moment('10:' + this.timeP + ':00', "HH:mm:ss");
    let fecha2 = moment('10:' + this.timer.i.text, "HH:mm:ss");
    this.logger.debug('Fecha uno =>', fecha.toDate());
    this.logger.debug('Fecha dos =>', fecha2.toDate());
    let tiempInvertidoMS = moment(fecha.diff(fecha2)).format("mm:ss");
    this.logger.debug('Tiempo MS=>', tiempInvertidoMS);
    this.logger.debug('vamos a enviar respuestas');
    this.answersQuestion.forEach(e => {
      this.logger.debug('Respuesta Releccionada =>', e.idRespuesta);
      e.tiempo = tiempInvertidoMS;
    })

    this.logger.debug(this.answersQuestion);
    this.questionkpiService.envioRespuestasKpi(this.answersQuestion).subscribe(data => {
      this.logger.debug(data);
      let vari = <RespuestaApi<any>>data;
      let resp = <any[]>vari.response;
      this.logger.debug('response=>', resp);
      if (resp.toString() === '1') {
        //se guardo exitosamente
        this.router.navigate(['/consultaKPI']);
      } else {
        //no se guardo exitosamente
        this.messageService.clear();
        this.messageService.add({ key: 'errorToast', sticky: true, severity: 'warn', summary: '¡Error al guardar la evaluación!', detail: 'Error:' + resp[0].RESPUESTA });
      }

    });
  }
  /**
   * Eventos del timer
   * @param e 
   */
  handleEvent(e: CountdownEvent) {
    this.logger.debug('Timer=>', e);
    if (this.haveTime != '0') {
      if (e.action === 'notify') {
        this.messageService.clear();
        this.messageService.add({ key: 'kpiQuizTimer', sticky: true, severity: 'warn', summary: '', detail: '' });
      }
      if (e.action === 'done') {

        this.logger.debug('Preguntas=>', this.question2.PREGUNTASKPI.length);
        this.logger.debug('Respuestas=>', this.answersQuestion.length);
        

        this.onSubmit();
        this.messageService.clear();
        this.messageService.add({ key: 'kpiQuizTimerEnd', sticky: true, severity: 'warn', summary: '', detail: '' });
      }
    }


  }
  /**
   * iniciando timer
   */
  iniciarTimer() {
    this.configtimer = {
      leftTime: (Number(this.timeP) * 60),
      size: 'large',
      format: 'mm:ss',
      notify: [300]

    };
  }

  redirecToPage() {
    this.spinner.show();
    this.timer.stop;
    let fecha = moment('10:' + this.timeP + ':00', "HH:mm:ss");
    let fecha2 = moment('10:' + this.timer.i.text, "HH:mm:ss");
    this.logger.debug('Fecha uno =>', fecha.toDate());
    this.logger.debug('Fecha dos =>', fecha2.toDate());
    let tiempInvertidoMS = moment(fecha.diff(fecha2)).format("mm:ss");
    this.logger.debug('Tiempo MS=>', tiempInvertidoMS);
    this.logger.debug('vamos a enviar respuestas');

   // var answersByPregunta: EnvioRespuestasKpi[] = [];
    console.log('answersQuestion=>' + JSON.stringify(this.answersQuestion) );
    
    this.answersQuestion.forEach(e => {
      e.tiempo = tiempInvertidoMS;
    })

    console.log('answersByPregunta=>' + JSON.stringify(this.answersQuestion));

    this.logger.debug(this.answersQuestion);
    if (this.answersQuestion.length === 0) {

      let prg: EnvioRespuestasKpi = new EnvioRespuestasKpi();
      prg.idPregunta = this.question2.PREGUNTASKPI[0].IDPREGUNTA + '';
      prg.idProcesoVigente = this.question2.PREGUNTASKPI[0].IDPROCESOVIGENTE + '';
      prg.idRespuesta = '';
      prg.idServidorPublico = this.idServidorPublico;
      prg.tiempo = tiempInvertidoMS;
      this.answersQuestion.push(prg);
    }
    this.questionkpiService.envioRespuestasKpi(this.answersQuestion).subscribe(data => {
      var datePipe = new DatePipe("es-MXN");
      let value = datePipe.transform(new Date(), 'dd-MMMM-yyyy');
      let dateValue = value.replace('-', ' de ');
      let dateValue2 = 'a ' + dateValue.replace('-', ' de ');
      let estimado: string = 'Estimado(a) ' + this.question2.NOMBRESERVIDORPUBLICO;
      let proc: string = this.question2.NOMBREPROCESOKPI;
      console.log('Termina de insertar' + data);
      this.logger.debug(data);
      let vari = <RespuestaApi<any>>data;
      let resp = <any[]>vari.response;
      this.logger.debug('response=>', resp);
      if (resp.toString() === '1') {
        //Si se ejecuta correctamente actualizamos notificaciones
        this.notificacionesService.obtenerNotificacionesPortal(this.idServidorPublico);
        
        let imprimirReporte : Boolean = true;
        this.question2.PREGUNTASKPI.forEach(preguntaItem=>{
          if(this.answersQuestion.filter(x=> Number(x.idPregunta) === Number(preguntaItem.IDPREGUNTA)).length === 0){
            imprimirReporte = false;
            console.log('No se contesto la pregunta=>' + preguntaItem.IDPREGUNTA);
          }
        });

        if(imprimirReporte){
        console.log('Imprimir reporte');
        // Constancia KPI 
        //this.questionkpiService.generarReporteKPI('a ' + dateValue.replace('-', ' de '), 'Estimado(a) ' + this.question2.NOMBRESERVIDORPUBLICO, this.question2.IDPROCESOKPI + ' - ' + this.question2.NOMBREPROCESOKPI).subscribe(data => {
          this.questionkpiService.generarReporteKPI(dateValue2, estimado, proc).subscribe(data => {
            //se guardo exitosamente
            this.spinner.hide();
            this.logger.debug('DATA PDF KPI =>>>>', data);

            const oMyBlob = new Blob([data], {type : 'application/pdf'});

            const url = window.URL.createObjectURL(oMyBlob);
            const a = document.createElement('a');
            a.setAttribute('style', 'display:none');
            document.body.appendChild(a);
            a.href = url;
            a.download = 'Reporte de Encuesta.pdf';
            a.click();
            this.router.navigate(['/consultaKPI']);
          }, err => {
            this.spinner.hide();
            this.router.navigate(['/consultaKPI']);
          })
       }else{
        this.spinner.hide();
        this.router.navigate(['/consultaKPI']);
       }


      } else {
        //no se guardo exitosamente
        this.spinner.hide();
        this.messageService.clear();
        this.messageService.add({ key: 'errorToast', sticky: true, severity: 'warn', summary: '¡Error al guardar la evaluación!', detail: 'Error:' + resp[0].RESPUESTA });
      }

    });
    this.messageService.clear();
  }



  saltarPreguntas(index: number, indexsig: string, respuesta: string, preguntas: QuestionsKPI[]) {
    this.logger.debug('Saltar preguntas', index, indexsig, respuesta);
    for (let p of preguntas) {
      this.logger.debug('index p =>', p.IDPREGUNTA);
      //&&  respuesta != ''  && preguntas.filter(x=> Number(x.IDPREGUNTA) === Number(respuesta)).length >0
      if (Number(p.IDPREGUNTA) > index && p.IDPREGUNTA < Number(indexsig)  ) {
        this.logger.debug('index saltados=>', p.IDPREGUNTA);
        let ans: EnvioRespuestasKpi = new EnvioRespuestasKpi();
        ans.idPregunta = p.IDPREGUNTA + '';
        ans.idProcesoVigente = p.IDPROCESOVIGENTE + '';
        ans.idRespuesta = respuesta;
        ans.idServidorPublico = this.idServidorPublico;
        ans.tiempo = '';
        this.answersQuestion.push(ans);
      }
    }
  }

  guardarPreguntas(){
    this.questionkpiService.envioRespuestasKpi(this.answersQuestion).subscribe(data => {
      this.logger.debug(data);
    },err=>{
    });

    
  }

}
