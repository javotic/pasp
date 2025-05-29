package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.ProcesoEscalafonarioVigenteDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.InscripcionDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.SeleccionDatoProfesionalDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.SeleccionProcesoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosProfecionalesDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonAplicaProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonEstadoInscripcion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPlazasDisponibles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.SesionExamenDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.SesionesDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ProcesoEscalafonarioVigenteService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ProcesoEscalafonarioVigenteServiceImpl implements ProcesoEscalafonarioVigenteService {

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private ProcesoEscalafonarioVigenteDAO dao;
    //</editor-fold>

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @Value("${edomex.database.evaluacion.url}")
    private String urlDataSource;
    @Value("${edomex.database.evaluacion.user}")
    private String databaseUser;
    @Value("${edomex.database.evaluacion.pass}")
    private String databasePass;
    
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProcesoEscalafonarioVigenteServiceImpl.class);
    //</editor-fold>

    //<editor-fold desc="Funciones implementadas" defaultstate="collapsed">
    @Override
    public List<EscalafonPlazasDisponibles> escalafonPlazasDisponibles(String claveServidorPublico, String tipo)
            throws BusException {
        List<EscalafonPlazasDisponibles> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.escalafonPlazasDisponibles(claveServidorPublico, tipo);
            data.forEach(x -> {
                EscalafonPlazasDisponibles cr = new EscalafonPlazasDisponibles();
                cr.setNumero(((x[0] != null ? x[0].toString() : "")));
                cr.setNombrePuesto(x[1] != null ? x[1].toString() : "");
                BigDecimal valor = new BigDecimal(Objects.toString(x[2], "0"));
                Integer nuevo_valor = valor.intValue();
                cr.setJornadaLaboral(nuevo_valor.toString());
                cr.setNivel(x[3] != null ? x[3].toString() : "");
                cr.setRango(x[4] != null ? x[4].toString() : "");
                cr.setNoPlaza(x[5] != null ? x[5].toString() : "");

                cr.setPercepcionesMensuales(x[6] != null ? x[6].toString() : "");
                cr.setAdscripcion(x[7] != null ? x[7].toString() : "");

                cr.setUbicacionTrabajo(x[8] != null ? x[8].toString() : "");

                cr.setIdEstatusPlaza(x[9] != null ? x[9].toString() : "");
                cr.setPuntajeEscalafonarioMinimo(x[10] != null ? x[10].toString() : "");
                cr.setEscolaridadMinimaGen(x[11] != null ? x[11].toString() : "");
                cr.setEscolaridadMinimaNat(x[12] != null ? x[12].toString() : "");
                cr.setExperienciaMinima(x[13] != null ? x[13].toString() : "");
                cr.setConocimientos(x[14] != null ? x[14].toString() : "");
                cr.setIdPlaza(x[15] != null ? x[15].toString() : "");
                cr.setIdEscolaridadMinimaGen(x[16] != null ? x[16].toString() : "");
                cr.setIdEscolaridadMinimaNat(x[17] != null ? x[17].toString() : "");
                cr.setRutaGuiaEstudio(x[18] != null ? x[18].toString() : "");
                cr.setIdPuesto(x[19] != null ? x[19].toString() : "");

                cr.setPuestoInferior(x[22] != null ? x[22].toString() : "");
                cr.setpVisible(x[23] != null ? x[23].toString() : "");

                consultas.add(cr);
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public List<EscalafonAplicaProcesoVigente> escalafonAplicaProcesoVigente(String claveServidorPublico)
            throws BusException {
        List<EscalafonAplicaProcesoVigente> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.escalafonAplicaProcesoVigente(claveServidorPublico);
            data.forEach(x -> {
                EscalafonAplicaProcesoVigente cr = new EscalafonAplicaProcesoVigente();
                cr.setIdProceso(Objects.toString(x[0], ""));
                cr.setAplica((Boolean) x[1] ? "1" : "0");
                cr.setMotivoNoAplica(Objects.toString(x[2], ""));
                cr.setBoInscrito(Objects.toString(x[3], ""));
                consultas.add(cr);
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public List<EscalafonEstadoInscripcion> escalafonEstadoInscripcion(String claveServidorPublico, String idProceso)
            throws BusException {
        List<EscalafonEstadoInscripcion> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.escalafonEstadoInscripcion(claveServidorPublico, idProceso);
            data.forEach(x -> {
                EscalafonEstadoInscripcion cr = new EscalafonEstadoInscripcion();
                cr.setIdEstatusPlaza((Objects.toString(x[0], "")));
                cr.setDetalleDescripcionEstatus((Objects.toString(x[1], "")));
                cr.setCancelado(Objects.toString(x[2], ""));
                cr.setMotivoCancelacion(Objects.toString(x[3], ""));
                cr.setEstatusDictamen(((Objects.toString(x[4], ""))));
                cr.setDescripcionDictamen((Objects.toString(x[5], "")));
                cr.setIdProceso((Objects.toString(x[6], "")));
                cr.setNumReg((Objects.toString(x[7], "")));
                cr.setCalificacion((Objects.toString(x[8], "")));
                cr.setRanking((Objects.toString(x[9], "")));

                cr.setIdMotivo((Objects.toString(x[10], "")));
                cr.setEncuesta((Objects.toString(x[11], "")));
                cr.setPublicaRes((Objects.toString(x[12], "")));
                cr.setFechaAcepta((Objects.toString(x[13], "")));
                consultas.add(cr);
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public Integer inscripcion(InscripcionDTO inscripcion) throws BusException {
        Integer preinscripcionResult = 0;

        try {
            Connection conn = DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
            SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.escalafonPreinscripcion ?, ?, ?, ? ").unwrap(SQLServerCallableStatement.class);
            pStmt.setString("idServidorPublico", inscripcion.getIdServidorPublico());
            pStmt.setString("idPlaza", inscripcion.getIdPlaza());
            pStmt.setString("idProcesoVigente", inscripcion.getIdProcesoVigente());

            pStmt.registerOutParameter(4, java.sql.Types.BIT);

            pStmt.execute();
            preinscripcionResult = pStmt.getInt(4);

            conn.close();
        } catch (NumberFormatException | SQLException e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

        /*
            try {
			return dao.inscripcion(inscripcion.getIdServidorPublico(), inscripcion.getIdPlaza(), inscripcion.getIdProcesoVigente());
		} catch (final Exception e) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
		}
         */
        return preinscripcionResult;
    }

    @Override
    public List<EscalafonProcesoVigente> escalafonProcesoVigente() throws BusException {
        List<EscalafonProcesoVigente> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.escalafonProcesoVigente();
            data.forEach(x -> {
                EscalafonProcesoVigente cr = new EscalafonProcesoVigente();
                cr.setIdProcesoVigente(Objects.toString(x[0], ""));
                cr.setNombreProcesoEvaluacion(Objects.toString(x[1], ""));
                cr.setFechaInicioProcesoGeneral((x[2] != null ? x[2].toString() : ""));
                cr.setFechaFinProcesoGeneral(Objects.toString(x[3], ""));
                cr.setDescripcionProcesoVigente(x[4] != null ? x[4].toString() : "");

                cr.setFechaInicioAdmin((x[5] != null ? x[5].toString() : ""));
                cr.setFechaFinAdmin((x[6] != null ? x[6].toString() : ""));

                consultas.add(cr);
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public List<SesionesDTO> escalafonSesionesAsesoria(String idProcesoVigente) throws BusException {
        List<SesionesDTO> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.escalafonSesionesAsesoria(idProcesoVigente);
            data.forEach(x -> {
                SesionesDTO cr = new SesionesDTO();
                cr.setIdSesion(Objects.toString(x[0], ""));
                cr.setDia(Objects.toString(x[1], ""));
                cr.setDuracion((x[2] != null ? x[2].toString() : ""));
                cr.setLugar(x[3] != null ? x[3].toString() : "");
                consultas.add(cr);
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public List<SesionExamenDTO> escalafonSesionesExamen(String idProcesoVigente) throws BusException {
        List<SesionExamenDTO> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.escalafonSesionesExamen(idProcesoVigente);
            data.forEach(x -> {
                SesionExamenDTO cr = new SesionExamenDTO();
                cr.setIdSession(Objects.toString(x[0], ""));
                cr.setDia(Objects.toString(x[1], ""));
                cr.setHoraIni(Objects.toString(x[2], ""));
                cr.setHoraFin(Objects.toString(x[3], ""));
                cr.setDuracion(Objects.toString(x[4], ""));
                cr.setLugar(Objects.toString(x[5], ""));
                cr.setCapMax(Objects.toString(x[6], ""));

                consultas.add(cr);
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public List<DatosProfecionalesDTO> consultarDatosProfesionales(String idServidorPublico) throws BusException {
        List<DatosProfecionalesDTO> consultas = new ArrayList<>();
        try {

            List<Object[]> data = dao.consultarDatosProfesionales(idServidorPublico);
            data.forEach(x -> {
                consultas.add(
                        new DatosProfecionalesDTO(
                                Objects.toString(x[0], ""),//ID nivel estudios
                                Objects.toString(x[1], ""),//ID datos profesional
                                Objects.toString(x[2], ""),//Fecha emision certificado
                                Objects.toString(x[3], ""),//Fecha vigencia certificado
                                Objects.toString(x[4], ""),//Nombre certificado
                                Objects.toString(x[5], ""),//ID tipo certificado
                                Objects.toString(x[6], ""),//Numero certificado
                                Objects.toString(x[7], ""),//ID Emisor
                                Objects.toString(x[8], ""),//ID datos profesional2
                                Objects.toString(x[9], ""),//Nivel estudios
                                Objects.toString(x[10], ""),//Descripcion certificado
                                Objects.toString(x[11], ""),//Nombre emisor certificado
                                Objects.toString(x[12], ""),//Duracion
                                Objects.toString(x[13], ""),//Tipo duracion
                                Objects.toString(x[14], ""),//Semaforo
                                (Objects.toString(x[15], "0").equals("1"))//Seleccion para proceso
                        )
                );
            });
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public byte[] descargaConvocatoria(String claveProceso, String idServidorPublico) throws BusException {
        byte[] consultas = null;
        try {

            byte[] data = dao.descargaConvocatoria(idServidorPublico, claveProceso);
            if (data == null) {
                throw new Exception();
            } else {
                consultas = data;
            }
        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return consultas;
    }

    @Override
    public Integer escalafonActualizaInfo() throws BusException {
        Integer boActualizaInfo = 0;

        try {
            List<Integer> data = dao.escalafonActualizaInfo();

            if (data.size() <= 0) {
                throw new Exception("No se pudieron obtener los datos");
            }
            boActualizaInfo = data.get(0);
            //String tempString = Objects.toString(temp[0], "");
            // boActualizaInfo = Integer.valueOf(tempString);

        } catch (Exception ex) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        // boActualizaInfo = 0;
        return boActualizaInfo;
    }

    @Override
    public String actualizarProfesionalesProceso(SeleccionProcesoDTO seleccionProceso) throws BusException {
        try {
            //Armar valores a insertar en parametro de tabla 
            StringBuilder valoresInsert = new StringBuilder();
            for (SeleccionDatoProfesionalDTO seleccionDatosProfesionales
                    : seleccionProceso.getLsSeleccionDatosProfesionales()) {
                valoresInsert
                        .append("('")
                        .append(seleccionProceso.getClaveServidorPublico())
                        .append("', ")
                        .append(seleccionDatosProfesionales.getIdOrdinal())
                        .append(", ")
                        .append(seleccionDatosProfesionales.isSeleccionado() ? "1" : "0")
                        .append("),");
            }
            //Eliminar la ultima "," del set.
            valoresInsert.deleteCharAt(valoresInsert.length() - 1);
            LOG.info("valoresInsert{}", valoresInsert.toString());
            
            this.dao.actualizarProfesionalesProceso(valoresInsert.toString());
        } catch (Exception e) {
            LOG.error("Error {}", e);
            return e.toString();
        }
        return "";
    }
    
    
    @Override
    public List<EscalafonPlazasDisponibles> escalafonPlazasDisponiblesSemaforo(
            String claveServidorPublico, String tipo) throws BusException {
        Utils utils = new Utils();
        List<EscalafonPlazasDisponibles> resultado = new ArrayList<>();
        try {
            List<Object[]> respuesta = dao.escalafonPlazasDisponiblesSemaforo(claveServidorPublico, tipo);
            respuesta.forEach(x -> {
                
                /**
                 * Se ajusta el tooltip para que se muestre el numero de
                 * inscritos en lugar de la leyenda.
                 *
                 * @author Javier Alvarado Rodriguez.
                 * @version 1.0, 11/05/2025.
                 */
                String color = "";  
                //String toolTip = "";
                switch(Integer.parseInt(utils.objectIsNULL(x[22]))){
                    case 1:
                        color = "green.png";
                        //toolTip = "Cero inscritos";
                        break;
                    case 2:
                        color = "yellow.png";
                        //toolTip = "1 a 3 inscritos";
                        break;
                    case 3:
                        color = "orange.png";
                        //toolTip = "4 a 9 inscritos";
                        break;
                    default:
                        color = "red.png";
                        //toolTip = "10 inscritos en adelante";
                        break;
                }
                
                
                resultado.add(new EscalafonPlazasDisponibles(
                        utils.objectIsNULL(x[0]).trim(),//numero
                        utils.objectIsNULL(x[1]).trim(),//nombrePuesto
                        utils.objectIsNULL(x[2]).trim(),//jornadaLaboral
                        utils.objectIsNULL(x[3]).trim(),//nivel
                        utils.objectIsNULL(x[4]).trim(),//rango
                        utils.objectIsNULL(x[5]).trim(),//noPlaza
                        utils.objectIsNULL(x[6]).trim(),//percepcionesMensuales
                        utils.objectIsNULL(x[7]).trim(),//adscripcion
                        utils.objectIsNULL(x[8]).trim(),//ubicacionTrabajo
                        utils.objectIsNULL(x[9]).trim(),//idEstatusPlaza
                        utils.objectIsNULL(x[10]).trim(),//puntajeEscalafonarioMinimo
                        utils.objectIsNULL(x[11]).trim(),//escolaridadMinimaGen
                        utils.objectIsNULL(x[12]).trim(),//escolaridadMinimaNat
                        utils.objectIsNULL(x[13]).trim(),//experienciaMinima
                        utils.objectIsNULL(x[14]).trim(),//conocimientos
                        utils.objectIsNULL(x[15]).trim(),//idPlaza
                        utils.objectIsNULL(x[16]).trim(),//idEscolaridadMinimaGen
                        utils.objectIsNULL(x[17]).trim(),//idEscolaridadMinimaNat
                        utils.objectIsNULL(x[18]).trim(),//rutaGuiaEstudio
                        utils.objectIsNULL(x[19]).trim(),//idPuesto
                        utils.objectIsNULL(x[20]).trim(),//puestoInferior
                        utils.objectIsNULL(x[21]).trim(),//pVisible
                        Integer.parseInt(utils.objectIsNULL(x[22])),//idColorSemaforo
                        color,//colorSemaforo
                        utils.objectIsNULL(x[23])//toolTip; indicando el numero de inscritos
                ));
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
        return resultado;
    }
    
    @Override
    public List<EscalafonProcesoVigente> obtenerProcesoEscalafonarioVigente() throws BusException {
        List<EscalafonProcesoVigente> consultas = new ArrayList<>();
        Utils utils = new Utils();
        try {
            List<Object[]> data = dao.obtenerProcesoEscalafonarioVigente();
            data.forEach(x -> {
                consultas.add(new EscalafonProcesoVigente(
                        utils.objectIsNULL(x[0]).trim(),//idProcesoVigente
                        utils.objectIsNULL(x[1]).trim(),//nombreProcesoEvaluacion
                        utils.objectIsNULL(x[2]).trim(),//fechaInicioProcesoGeneral
                        utils.objectIsNULL(x[3]).trim(),//fechaFinProcesoGeneral
                        utils.objectIsNULL(x[4]).trim(),//descripcionProcesoVigente
                        utils.objectIsNULL(x[5]).trim(),//fechaInicioAdmin
                        utils.objectIsNULL(x[6]).trim(),//fechaFinAdmin
                        Integer.parseInt(utils.objectIsNULL(x[7]))//tipoProceso
                ));
            });

        } catch (final Exception e) {
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR: ", e.getMessage());
        }
        return consultas;
    }
    //</editor-fold>
}
