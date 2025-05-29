import { PreguntasEDD } from './preguntasEDD';

export class SeccionesEddPR {
    idprocesovigente: string;
    idseccion: string;
    nombreseccion: string;
    backgroundColor: string;
    claveEvaluador: string;
    claveEvaluado: string;
    claveUnidadAdmin: string;
    //IDSECCION:string;
    VALOR:string;
    DESCRIPCIONVALOR:string;    
    PUNTOS:string;
    preguntas: PreguntasEDD[];
}