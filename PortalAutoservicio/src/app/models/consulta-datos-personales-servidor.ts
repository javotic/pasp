import { Combo } from './combo';

export class ConsultaDatosPersonalesServidor {
 
    codigo:number;
    mensaje:string;
    nombre: string;
    apellidoPaterno: string;
    apellidoMaterno: string;
    fechaNacimiento: string;
    curp: string;
    rfc: string;
    issemym: string;
    telefono: string;
    correoElectronico: string;
    idNivelEstudios: string;
    idEstadoCivil: string;
    idSexo: string;
    idEstado: string;
    idMunicipio: string;
    idColonia: string;
    calle: string;
    numeroInterior: string;
    numeroExterior: string;
    codigoPostal: string;

    
    lstNivelEstudios:Combo[];
    lstGenero:Combo[];
    lstEstadoCivil:Combo[];

    dsestado: string;
    dsmunicipio: string;
    dscolonia: string;
}