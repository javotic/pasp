import { RespuestasEDDB } from './respuestas-edd-b';

export class PreguntasEDD{

    idseccion:string;
    idprocesovigente:string;
    idpregunta: string;
    descripcionpregunta: string;
    backgroundPregunta:string;
    idPreguntaResp:string;
    tipoCampo:string;
    
    respuestas :RespuestasEDDB[];


}