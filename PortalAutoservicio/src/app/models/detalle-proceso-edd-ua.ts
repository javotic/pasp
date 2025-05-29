import { servidoresPublicosEddUa } from './servidores-publicos-eddua';

export class DetalleProcesoEddua {
    IDEVALUACIONVIGENTE: string;
    NOMBREEVALUACIONVIGENTE: string;
    IDUNIDADADMINISTRATIVA: string;
    NOMBREUNIDADADMINISTRATIVA: string;
    TOTALSERVIDORESPUBLICOS: string;
    TOTALSERVIDORESPUBLICOSEVALUADOS: string;
    TOTALSERVIDORESPUBLICOSFALTANTES: string;
    PORCENTAJEAVANZE: string;
    LSTSERVIDORESPUBLICOS: servidoresPublicosEddUa[];
}
