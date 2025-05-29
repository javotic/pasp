// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,

  HOST: 'https://desabus.edomex.gob.mx/bussrv/sei',
  TOKEN_AUTH_USERNAME: 'seiusr',
  TOKEN_AUTH_PASSWORD: 'aguacate',

  URL_SIGAP: 'http://189.254.70.58:9050/SIGAP_QA_PAS/Vista/IniciarSesion.xhtml',
  URL_SIGAP_INICIO: 'http://189.254.70.58:9050/SIGAP_QA_PAS/Vista/Home.xhtml',



  // DEV SEI 
  BASEENPOINT_AUTOSERVICIO: 'http://10.10.39.228:8090/api/autoservicio',
  BASEENPOINT_ENCRIPTDECRIPT: 'http://10.10.39.228:8090/api/encriptdecript',
  BASEENPOINT_SOLICITUD_CONSTANCIA: 'http://10.10.39.228:8090/api/autoservicio/solicitudConstancia',
  BASEENPOINT_ETIQUETAS: 'http://10.10.39.228:8090/api/etiquetasparametros/etiquetaspagina',
  BASEENPOINT_NOTIFICACIONES: 'http://10.10.39.228:8090/api/notificaciones',
  BASEENPOINT_CANCELAR_NOTIFICACIONES: 'http://10.10.39.228:8090/api/notificaciones/updateDtNotificacionesByLlnotifi',
  BASEENPOINT_DATOS_PERSONALES: 'http://10.10.39.228:8090/datosPersonales',
  BASEENPOINT_BITACORA: 'http://10.10.39.228:8090/api/autoservicio/bitacora',


  //BASEENPOINT_SERVICE_BUS: 'http://10.10.39.228:8090/api/serviciosbus/wsbus',
  BASEENPOINT_SERVICE_BUS: 'http://localhost:39826/wsbus',

  //BASEENPOINT_SERVICE_BUS2: 'http://10.10.39.228:8090/api/serviciosbus/catalogos/wsbus',
  BASEENPOINT_SERVICE_BUS2: 'http://localhost:39826/catalogos/wsbus',

  //BASEENPOINT_SERVICE_BUS3: 'http://10.10.39.228:8090/api/serviciosbus',
  BASEENPOINT_SERVICE_BUS3: 'http://localhost:39826',

  // BASEENPOINT_SERVICE_SEI_BUS:'http://10.10.39.228:8090/api/seibus/servidoreseddua',
  // BASEENPOINT_SERVICE_SEI_BUS: 'https://qa-rhautoservicios.edomex.gob.mx/api/seibus/servidoreseddua',
  BASEENPOINT_SERVICE_SEI_BUS: 'http://localhost:9092/servidoreseddua',

  //WS en QA
  BASEENPOINT_BUS_SEI: 'http://10.10.39.227:8090/api/seibus',
  // BASEENPOINT_BUS_SEI: 'https://qa-rhautoservicios.edomex.gob.mx/api/seibus',
  //WS en DESA
  // BASEENPOINT_BUS_SEI: 'http://10.10.39.228:8090/api/seibus',
  //WS en LOCAL
  // BASEENPOINT_BUS_SEI: 'http://localhost:9092',
  //WS en PROD
  // BASEENPOINT_BUS_SEI: 'https://rhautoservicios.edomex.gob.mx/api/seibus/',

  BASEENPOINT_GUARDAR_NOTIFICACIONES: 'http://10.10.39.228:8090/api/notificaciones/notificacionesPortal',

  //BASEENPOINT_SERVICE_REPORTES: 'http://10.10.39.228:8090/api/reportes/',
  BASEENPOINT_SERVICE_REPORTES: 'http://localhost:9093/',

  //BASEENPOINT_BUS_SEI_REPORTES: 'http://10.10.39.228:8090/api/reportes/',
  BASEENPOINT_BUS_SEI_REPORTES: 'http://localhost:9093/',

  BASEENPOINT_DIGITAL_VAULT: 'http://10.10.39.228:8090/api/vault/',
  // BASEENPOINT_DIGITAL_VAULT: 'http://localhost:8097/',

  //BASEENPOINT_HNOMINA: 'http://10.10.39.228:8090/api/hnomina/',
  BASEENPOINT_HNOMINA: 'http://localhost:9099/',

  HEDER_WS: "",
  PERFIL_WEB: 'DEV',
  PASSWORD_DOCUMENTOS: 'pas',

  //Id Secciones Desempeño 
  ID_SEC_COMPETENCIA_APTITU: '01',
  ID_SEC_COMPETENCIA_SOCIO: '02',
  ID_SEC_DEMERITOS: '03'

};