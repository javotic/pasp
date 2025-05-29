
import { PorcesoVigente } from '../models/proceso-vigente';

let procesoVigente: PorcesoVigente = new PorcesoVigente();
procesoVigente.CLAVEPROCESOEVALUACION = '2020-1';
procesoVigente.NOMBREPROCESOEVALUACION = 'EVALUACIÓN DEL DESEMPEÑO PRIMER SEMESTRE 2020';procesoVigente.FECHAINICIOPROCESOGENERAL = '01/02/2020';
procesoVigente.FECHAFINPROCESOGENERAL = '30/04/2020';
procesoVigente.FECHAINICIOCAPTURADEMERITOS = '01/02/2020';
procesoVigente.FECHAFINCAPTURADEMERITOS = '30/04/2020';
procesoVigente.DESCRIPCIONPROCESOVIGENTE = 'Este proceso de Evaluación del Desempeño esta relacionada con la evaluación Encuesta de la Persona Servidora Pública 2020A - Proceso Encuesta 2020. Si la Persona Servidora Pública no ha realizado esta evaluación, no podrá ser evaluado por el responsable en este proceso vigente.';
export const PROCESOVIGENTEMOCKS: PorcesoVigente = procesoVigente;