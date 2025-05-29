import { Respuesta } from './respuesta';
import { RespuestaM4 } from './respuesta-m4';


export class RespuestaServiceM4<T>  extends RespuestaM4{
    
    public response:Array<T> = new Array<T>();
}