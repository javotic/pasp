
import { DetalleProcesoedd } from '../models/detalle-proceso-edd';
import { DetalleEjecucionEddambos } from '../models/detalle-ejecucion-edd-ambos';
import { SeccionesEDD } from '../models/seccionesEDD';
import { InstruccionesEDD } from '../models/instrucciones-edd';
import { RespuestasEDD } from '../models/respuestad-edd';

/* export const DETALLEEJECUCIONEDDAPT: DetalleEjecucionEddambos[] = [
    { idPregunta: 1, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "1. Aplica los métodos y procedimientos eficazmente para el logro de tareas y actividades." },
    { idPregunta: 2, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "2. Maneja recursos materiales y equipos de oficina (papelería, computadora, escáner, impresora, teléfono etc.) para integrar un trabajo de excelencia en fondo y forma." },
    { idPregunta: 3, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "3. Modifica tareas, proyectos y actividades de su puesto orientadas hacia la actualización basándose en un aprendizaje continuo." },
    { idPregunta: 4, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "4. Construye a partir de su experiencia nuevas formas (estrategias) para resolver situaciones del entorno actual relacionadas con sus funciones diarias." },
    { idPregunta: 5, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "5. Analiza a detalle las actividades que realiza, integrando sus tareas con base en altos estándares, buscando la mejora continua y ejecutando las cosas bien a la primera" },
    { idPregunta: 6, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "6. Programa, genera y concluye sus tareas, así como actividades consistentemente en los plazos acordados." },
    { idPregunta: 7, idProcesoVigente: 20, idSeccion: 1, descripcionPregunta: "7. Traza planes de acción fundamentados en objetivos específicos y opera con base en ellos, monitoreando constantemente sus avances y logros parciales reales" }

] */

/* export const DETALLEEJECUCIONEDDSOCIP: DetalleEjecucionEddambos[] = [
    { idPregunta: 1, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "1. Diseña métodos para la solución de conflictos o tareas empleando sus habilidades; reconociendo sus fortalezas y debilidades, demostrando con ello ungrado de seguridad en sí misma/o." },
    { idPregunta: 2, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "2. Opera con eficiencia en situaciones de presión u oposición. Manejando con serenidad, reflexión y acción positiva cargas emocionales bajo estrés." },
    { idPregunta: 3, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "3. Construye relaciones satisfactorias (cooperativas y armoniosas) a nivel personal, social y laboral, evita los conflictos, sin embargo, si se ve inmersa en uno analiza alternativas tomando en cuenta diferentes puntos de vista para crear acuerdos." },
    { idPregunta: 4, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "4. Utiliza  su  capacidad  de  escuchar  y  transmite  ideas  e  información  en  forma  clara, respetuosa, concisa y organizada para que sean comprendidas,obteniendo un entendimiento mutuo con sus pares y superiores, logrando un objetivo en particular" },
    { idPregunta: 5, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "5. Comprende,  respeta  y  actúa  conforme  a  la cultura y valores organizacionales. Se mantiene al pendiente de las políticas, empleando diariamente lasleyes, normas, reglamentos y procedimientos afines." },
    { idPregunta: 6, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "6. Ejecuta acciones respetuosas y de servicio (cortesía, amabilidad y empatía) hacia sus pares como a usuarios externos, proyectando una imagen positiva" },
    { idPregunta: 7, idProcesoVigente: 20, idSeccion: 2, descripcionPregunta: "7. Se organiza para integrarse con compañeros que persiguen el mismo objetivo dentro de sus actividades laborales, mostrando una actitud de compromiso,de la institución. fidelidad, adhesión y proactividad en distintos entornos jerárquicos (superiores, similares o personal a su cargo)." }

] */


/* export const DETALLEEJECUCIONEDDDEMERITOS: DetalleEjecucionEddambos[] = [
    { idPregunta: 1, idProcesoVigente: 20, idSeccion: 3, descripcionPregunta: "1. Por cada llamada de atención escrita (asigne 1 punto)." },
    { idPregunta: 2, idProcesoVigente: 20, idSeccion: 3, descripcionPregunta: "2. Por cada amonestación escrita (asigne 2 puntos)." },
    { idPregunta: 3, idProcesoVigente: 20, idSeccion: 3, descripcionPregunta: "3. Por cada día de suspensión de sueldo y funciones (asigne 5 puntos)." }

] */


/* export const SECCIONESEDD: SeccionesEDD[] = [{ idProcesoVigente: 20, idSeccion: 1, nombreSeccion: 'Competencias aptitudinales' },
{ idProcesoVigente: 20, idSeccion: 2, nombreSeccion: 'Competencias socipersonales' },
{ idProcesoVigente: 20, idSeccion: 3, nombreSeccion: 'Deméritos' }] */

/* export const INSTRUCCIONESEDD: InstruccionesEDD[] = [{ idSeccion: 1, descripcionValor: 'Hacen referencia a los conocimientos teóricos y prácticos con los que cuenta la servidora y servidor público, relacionado con la eficiencia con la que utiliza los mismos en su labor diaria ', valor: 'Equivale al 50 por ciento de la calificación total' },
{ idSeccion: 2, descripcionValor: 'Comprende el desarrollo emocional de la persona servidora pública, enfocado a la sana interacción social en su ambiente de trabajo', valor: 'Equivale al 50 por ciento de la calificación total' },
{ idSeccion: 3, descripcionValor: 'Deberán  señalar  los  deméritos  que  ha  tenido  la  servidora  o  servidor  público  estipulados  en  sus  artículos  95  y  96 del Reglamento del Proceso Escalafonario  de  las  Personas  Servidoras  Públicas  Generales  del  Poder  Ejecutivo  del Estado de México.', valor: 'Los puntos obtenidos serán restados a la calificación total integrada de competencias aptitudinales y sociopersonales.' }]

 */
/* export const RESPUESTASEDD: RespuestasEDD[] = [{ idRespuesta: 1, respuesta: 'Nunca', puntaje: 1 },
{ idRespuesta: 2, respuesta: 'Casi Nunca', puntaje: 2 },
{ idRespuesta: 3, respuesta: 'Rara Vez', puntaje: 3 },
{ idRespuesta: 4, respuesta: 'Algunas Veces', puntaje: 4 },
{ idRespuesta: 5, respuesta: 'Con Frecuencia', puntaje: 5 },
{ idRespuesta: 6, respuesta: 'Siempre', puntaje: 5 }]


export const RESPUESTASEDDDEM: RespuestasEDD[] = [
{ idRespuesta: 2, respuesta: 'Máximo de Puntos: 99', puntaje: 2 },
{ idRespuesta: 3, respuesta: 'Máximo de Puntos: 99', puntaje: 3 },
{ idRespuesta: 4, respuesta: 'Máximo de Puntos: 200', puntaje: 4 }
] */