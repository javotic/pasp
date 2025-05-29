import { CatalogoNotificaciones } from '../models/notificaciones';


export const NOTIFICACIONESMOCK: CatalogoNotificaciones[] = [
    { LLTIPONOTIFICACION: 1, BOACTIVO: true, DESCNOTIFICACION: 'Constancia de no adeudos', IDMENU: 1, URL: '/constanciaNoAdeudo' },
    { LLTIPONOTIFICACION: 2, BOACTIVO: true, DESCNOTIFICACION: 'Constancia de historial laboral', IDMENU: 2, URL: '/historialLaboral' },
    { LLTIPONOTIFICACION: 3, BOACTIVO: true, DESCNOTIFICACION: 'Proceso escalafonario', IDMENU: 3, URL: '/procesoEscalafonario' },
    { LLTIPONOTIFICACION: 4, BOACTIVO: true, DESCNOTIFICACION: 'Evaluación del desempeño', IDMENU: 4, URL: '/evaluacionDesempeno' },
    { LLTIPONOTIFICACION: 5, BOACTIVO: true, DESCNOTIFICACION: 'Evaluación Encuesta\'s', IDMENU: 5, URL: '/consultaKPI' }
]
