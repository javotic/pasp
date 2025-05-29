package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DatosPersonalesDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.DatosServidorPublicoDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonales;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonalesCatalogosDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TituloCarreraModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.MsgRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DatosServidorPublicoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;

@Service
public class DatosServidorPublicoServiceImpl implements DatosServidorPublicoService {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(DatosServidorPublicoServiceImpl.class);
    private final int ID_TIPO_DIRECCION_PERSONAL = 1;
    private final int ID_TIPO_DIRECCION_FISCAL = 7;
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private DatosPersonalesDAO datosPersonalesDAO;
    @Autowired
    private DatosServidorPublicoDAO datosServidorPublicoDAO;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public DatosServidorPublico consultarDatosServidorPublico(String IdServidorPublico) throws BusException {
        Utils utils = new Utils();
        DatosServidorPublico personales = new DatosServidorPublico();
        try {
            List<Object[]> lstResul = datosServidorPublicoDAO.consultarDatosServidorPublico(IdServidorPublico);
            personales.setCALLE(utils.objectIsNULL(lstResul.get(0)[11]));
            personales.setCLAVESERVIDORPUBLICO(utils.objectIsNULL(lstResul.get(0)[1]));
            personales.setCODIGOPOSTAL(utils.objectIsNULL(lstResul.get(0)[17]));
            personales.setCORREOELECTRONICO(utils.objectIsNULL(lstResul.get(0)[10]));
            personales.setCURP(utils.objectIsNULL(lstResul.get(0)[6]));
            personales.setFECHANACIMIENTO(utils.objectIsNULL(lstResul.get(0)[8]));
            personales.setGENERO(utils.objectIsNULL(lstResul.get(0)[5]));
            personales.setIDCOLONIA(utils.objectIsNULL(lstResul.get(0)[14]));
            personales.setIDESTADO(utils.objectIsNULL(lstResul.get(0)[12]));
            personales.setIDESTADOCIVIL(utils.objectIsNULL(lstResul.get(0)[22]));
            personales.setIDMUNICIPIO(utils.objectIsNULL(lstResul.get(0)[13]));
            personales.setIDNIVELESTUDIOS(utils.objectIsNULL(lstResul.get(0)[20]));
            personales.setIDORDINAL(utils.objectIsNULL(lstResul.get(0)[0]));
            personales.setIDPLAZA(utils.objectIsNULL(lstResul.get(0)[23]));
            personales.setIDSEXO(utils.objectIsNULL(lstResul.get(0)[21]));
            personales.setISSEMYN(utils.objectIsNULL(lstResul.get(0)[24]));
            personales.setNOMBRE(utils.objectIsNULL(lstResul.get(0)[2]));
            personales.setNOMBREESTADO(utils.objectIsNULL(lstResul.get(0)[18]));
            personales.setNOMBREPLAZA(utils.objectIsNULL(lstResul.get(0)[19]));
            personales.setNUMEXTERIOR(utils.objectIsNULL(lstResul.get(0)[15]));
            personales.setNUMINTERIOR(utils.objectIsNULL(lstResul.get(0)[16]));
            personales.setPRIMERAPELLIDO(utils.objectIsNULL(lstResul.get(0)[3]));
            personales.setRFC(utils.objectIsNULL(lstResul.get(0)[7]));
            personales.setTELEFONO(utils.objectIsNULL(lstResul.get(0)[9]));

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return personales;
    }

    @Override
    public DatosPersonales consultarDatosPersonales(String IdServidorPublico, String nombre, String apellidoPat,
            String apellidoMat) throws BusException {
        Utils utils = new Utils();
        DatosPersonales personales = new DatosPersonales();
        try {
            List<Object[]> lstResul = datosPersonalesDAO.consultarDatosPersonales(
                    IdServidorPublico.equals("-") ? null : IdServidorPublico, nombre.equals("-") ? null : nombre,
                    apellidoPat.equals("-") ? null : apellidoPat, apellidoMat.equals("-") ? null : apellidoMat);

            if (lstResul.size() > 0) {
                personales.setAPELLIDOMATERNO(utils.objectIsNULL(lstResul.get(0)[2]));
                personales.setAPELLIDOPATERNO(utils.objectIsNULL(lstResul.get(0)[1]));
                personales.setCLAVESERVIDOR(utils.objectIsNULL(lstResul.get(0)[0]));
                personales.setCOMENTARIOS(utils.objectIsNULL(lstResul.get(0)[18]));
                personales.setCURP(utils.objectIsNULL(lstResul.get(0)[13]));
                personales.setDESCRIESCOLARIDAD(utils.objectIsNULL(lstResul.get(0)[15]));
                personales.setFECHANACIMIENTO(utils.objectIsNULL(lstResul.get(0)[11]));
                personales.setIDESCOLARIDAD(utils.objectIsNULL(lstResul.get(0)[14]));
                personales.setIDESTADOCIVIL(utils.objectIsNULL(lstResul.get(0)[16]));
                personales.setIDGENERO(utils.objectIsNULL(lstResul.get(0)[5]));
                personales.setIDPAIS(utils.objectIsNULL(lstResul.get(0)[7]));
                personales.setIDTRATAMIENTO(utils.objectIsNULL(lstResul.get(0)[9]));
                personales.setMOMBRE(utils.objectIsNULL(lstResul.get(0)[3]));
                personales.setNOMBRECOMPLETO(utils.objectIsNULL(lstResul.get(0)[4]));
                personales.setNOMBREESTADOCIVIL(utils.objectIsNULL(lstResul.get(0)[17]));
                personales.setNOMBREGENERO(utils.objectIsNULL(lstResul.get(0)[6]));
                personales.setNOMBREPAIS(utils.objectIsNULL(lstResul.get(0)[8]));
                personales.setNOMBRETRATAMIENTO(utils.objectIsNULL(lstResul.get(0)[10]));
                personales.setRFC(utils.objectIsNULL(lstResul.get(0)[12]));

                personales.setDESEMPENO(utils.objectIsNULL(lstResul.get(0)[19]));
                personales.setKPI(utils.objectIsNULL(lstResul.get(0)[20]));
                personales.setESCALAFON(utils.objectIsNULL(lstResul.get(0)[21]));
                personales.setCONSULTA(utils.objectIsNULL(lstResul.get(0)[22]));

                personales.setIDROL(utils.objectIsNULL(lstResul.get(0)[23]).equals("") ? null : Integer.valueOf(utils.objectIsNULL(lstResul.get(0)[23])));
                personales.setBOACTIVO(utils.objectIsNULL(lstResul.get(0)[24]));
                personales.setBOPRORROGA(utils.objectIsNULL(lstResul.get(0)[25]));
                personales.setIDPUESTO(utils.objectIsNULL(lstResul.get(0)[26]));
                personales.setNPUESTO(utils.objectIsNULL(lstResul.get(0)[27]));
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return personales;
    }

    @Override
    public DatosPersonalesCatalogosDTO consultarDatosPersonalesConCatalogos(String IdServidorPublico) throws BusException {
        Utils utils = new Utils();
        DatosPersonalesCatalogosDTO personales = new DatosPersonalesCatalogosDTO();
        try {
            List<Object[]> lstResul = datosServidorPublicoDAO.consultarDatosServidorPublico(IdServidorPublico);

            personales.setCodigo(200);
            personales.setMensaje("success");
            personales.setNombre(utils.objectIsNULL(lstResul.get(0)[2]));
            personales.setApellidoPaterno(utils.objectIsNULL(lstResul.get(0)[3]));
            personales.setApellidoMaterno(utils.objectIsNULL(lstResul.get(0)[4]));

            personales.setCurp(utils.objectIsNULL(lstResul.get(0)[6]));
            personales.setRfc(utils.objectIsNULL(lstResul.get(0)[7]));
            personales.setFechaNacimiento(utils.objectIsNULL(lstResul.get(0)[8]));
            personales.setTelefono(utils.objectIsNULL(lstResul.get(0)[9]));
            personales.setCorreoElectronico(utils.objectIsNULL(lstResul.get(0)[10]));
            personales.setCalle(utils.objectIsNULL(lstResul.get(0)[11]));

            personales.setIdEstado(utils.objectIsNULL(lstResul.get(0)[12]));
            personales.setDsestado(utils.objectIsNULL(lstResul.get(0)[13]));

            personales.setIdMunicipio(utils.objectIsNULL(lstResul.get(0)[14]));
            personales.setDsmunicipio(utils.objectIsNULL(lstResul.get(0)[15]));

            personales.setIdColonia(utils.objectIsNULL(lstResul.get(0)[16]));
            personales.setDscolonia(utils.objectIsNULL(lstResul.get(0)[17]));

            personales.setNumeroExterior(utils.objectIsNULL(lstResul.get(0)[18]));
            personales.setNumeroInterior(utils.objectIsNULL(lstResul.get(0)[19]));
            personales.setCodigoPostal(utils.objectIsNULL(lstResul.get(0)[20]));

            personales.setIdNivelEstudios(utils.objectIsNULL(lstResul.get(0)[23]));
            personales.setIdSexo(utils.objectIsNULL(lstResul.get(0)[24]));
            personales.setIdEstadoCivil(utils.objectIsNULL(lstResul.get(0)[25]));

            personales.setIssemym(utils.objectIsNULL(lstResul.get(0)[27]));

            //List<Object[]> listaEstados = datosServidorPublicoDAO.consultarEstados("MEX", null);
            //List<Object[]> listaMunicipios = datosServidorPublicoDAO.consultarMunicipios("MEX", personales.getIdEstado(), null);     
            //List<Object[]> listaColonias = datosServidorPublicoDAO.consultarColonias("MEX", personales.getIdEstado(), personales.getIdMunicipio(), null);     
            List<Object[]> listaNivelEstudios = datosServidorPublicoDAO.consultarNivelEstudios(null);
            List<Object[]> listaGenero = datosServidorPublicoDAO.consultarGenero();
            List<Object[]> listaEstadoCivil = datosServidorPublicoDAO.consultarEstadoCivil();

            //personales.setLstEstados(new ArrayList<>());
            //personales.setLstMunicipios( new ArrayList<>());
            //personales.setLstColonias(new ArrayList<>());
            personales.setLstNivelEstudios(new ArrayList<>());
            personales.setLstGenero(new ArrayList<>());
            personales.setLstEstadoCivil(new ArrayList<>());

            /*
                    listaEstados.forEach(x -> {
                        Combo cr = new Combo();
                        cr.setValue(Objects.toString(x[2], ""));
                        cr.setLabel(Objects.toString(x[3], ""));
                        personales.getLstEstados().add(cr);    
                    });
             */
 /*
                    listaMunicipios.forEach(x -> {
                        Combo cr = new Combo();
                        cr.setValue(Objects.toString(x[3], ""));
                        cr.setLabel(Objects.toString(x[4], ""));
                        personales.getLstMunicipios().add(cr);    
                    });
             */
 /*
                    listaColonias.forEach(x -> {
                        Combo cr = new Combo();
                        cr.setValue(Objects.toString(x[2], ""));
                        cr.setLabel(Objects.toString(x[3], ""));
                        personales.getLstColonias().add(cr);    
                    });
             */
            listaNivelEstudios.forEach(x -> {
                Combo cr = new Combo();
                cr.setValue(Objects.toString(x[0], ""));
                cr.setLabel(Objects.toString(x[1], ""));
                personales.getLstNivelEstudios().add(cr);
            });

            listaGenero.forEach(x -> {
                Combo cr = new Combo();
                cr.setValue(Objects.toString(x[0], ""));
                cr.setLabel(Objects.toString(x[1], ""));
                personales.getLstGenero().add(cr);
            });

            listaEstadoCivil.forEach(x -> {
                Combo cr = new Combo();
                cr.setValue(Objects.toString(x[0], ""));
                cr.setLabel(Objects.toString(x[1], ""));
                personales.getLstEstadoCivil().add(cr);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);

            personales.setCodigo(500);
            personales.setMensaje("false");
            return personales;
            //throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return personales;
    }

    @Override
    public List<TituloCarreraModel> getTituloCarrera(String idTipoCarrera) throws BusException {
        Utils utils = new Utils();
        List<TituloCarreraModel> lstTituloCarrera = new ArrayList<>();
        try {

            List<Object[]> lstResul = datosServidorPublicoDAO.consultarNivelEstudios(idTipoCarrera);

            lstResul.forEach(x -> {
                TituloCarreraModel titulo = new TituloCarreraModel();
                titulo.setIDTITULOCARRERA(utils.objectIsNULL(x[0]));
                titulo.setNOMBRECTITULOCARRERA(utils.objectIsNULL(x[1]));

                lstTituloCarrera.add(titulo);
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstTituloCarrera;
    }

    @Override
    public List<MsgRespuesta> updDatosPersonales(String CLAVESERVIDORPUBLICO, String IDDATOSPERSONALES, String TELEFONO, String CORREOELECTRONICO,
            String IDESTADO, String IDMUNICIPIO, String IDCOLONIA, String CALLE, String NUMEXTERIOR, String NUMINTERIOR,
            String CODIGOPOSTAL, String IDNIVELESTUDIOS, String IDESTADOCIVIL) throws BusException {
        List<MsgRespuesta> resp = new ArrayList<>();
        try {

            int respuesta = datosServidorPublicoDAO.updDatosPersonales(CLAVESERVIDORPUBLICO, IDDATOSPERSONALES, TELEFONO, CORREOELECTRONICO,
                    IDESTADO, IDMUNICIPIO, IDCOLONIA, CALLE, NUMEXTERIOR, NUMINTERIOR,
                    CODIGOPOSTAL, IDNIVELESTUDIOS, IDESTADOCIVIL);
            MsgRespuesta respuestaunit = new MsgRespuesta();
            if (respuesta == 1) {
                respuestaunit.setRESPUESTA("");
                resp.add(respuestaunit);
                return resp;
            } else {
                throw new Exception("No se han podido actualizar los datos");
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public List<MsgRespuesta> gestionarDatosProfesionales(String IDDATOPROFESIONAL, String IDSERVIDORPUBLICO, String IDNIVELESTUDIOS, String FECHAEMISIONCERTIFICADO,
            String FECHAVIGENCIACERTIFICADO, String NOMBRECERTIFICADO, String IDTIPOCERTIFICADO, String NOCERTIFICADO,
            String IDEMISORCERTIFICADO, String DURACION, String TIPODURACION) throws BusException {
        Utils utils = new Utils();
        List<MsgRespuesta> resp = new ArrayList<>();
        try {
            Object[] respuesta = datosServidorPublicoDAO.gestionarDatosProfesionales(IDDATOPROFESIONAL, IDSERVIDORPUBLICO, IDNIVELESTUDIOS, FECHAEMISIONCERTIFICADO,
                    FECHAVIGENCIACERTIFICADO, NOMBRECERTIFICADO, IDTIPOCERTIFICADO, NOCERTIFICADO,
                    IDEMISORCERTIFICADO, DURACION, TIPODURACION);
            MsgRespuesta respuestaunit = new MsgRespuesta();
            if (respuesta.length > 0 || "".equals(utils.objectIsNULL(respuesta[0]).trim())) {
                respuestaunit.setRESPUESTA("");
                resp.add(respuestaunit);
                return resp;
            } else {
                throw new Exception("No se han podido agregar o actualizar los datos profesionales.");
            }
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public Integer eliminarDatosProfesionales(String IDSERVIDORPUBLICO, String IDREGISTRODATOPROFESIONAL) throws BusException {
        Utils utils = new Utils();
        try {
            Object[] respuesta = datosServidorPublicoDAO.eliminarDatosProfesionales(IDSERVIDORPUBLICO, IDREGISTRODATOPROFESIONAL);
            if (respuesta.length > 0 || "".equals(utils.objectIsNULL(respuesta[0]).trim())) {
                return 1;
            } else {
                throw new Exception("No se han podido agregar o actualizar los datos profesionales.");
            }
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    @Override
    public ServidorPublicoModel obtenerDatosPersonalesPorClaveSP(String claveServidor) throws BusException {
        ServidorPublicoModel servidorPublicoModel = new ServidorPublicoModel();
        List<ServidorPublicoModel> lsRegistros = new ArrayList<>();
        Utils utils = new Utils();

        try {
            //Obtener datos y convertir a modelo
            List<Object[]> lsResultado = datosServidorPublicoDAO.obtenerDatosPersonalesPorClaveSP(
                    claveServidor
            );

            lsResultado.forEach(registro -> {
                lsRegistros.add(new ServidorPublicoModel(
                        Integer.valueOf(utils.objectIsNULL(registro[0])),//ID Ordinal
                        utils.objectIsNULL(registro[1]),//Clave de servidor publico
                        utils.objectIsNULL(registro[2]),//Nombre
                        utils.objectIsNULL(registro[3]),//Primer apellido
                        utils.objectIsNULL(registro[4]),//Segundo apellido
                        Integer.valueOf(utils.objectIsNULL(registro[5])),//ID genero
                        utils.objectIsNULL(registro[6]),//Genero
                        utils.objectIsNULL(registro[7]),//Curp
                        utils.objectIsNULL(registro[8]),//Rfc
                        utils.objectIsNULL(registro[9]),//Fecha Nacimiento
                        utils.objectIsNULL(registro[10]),//Telefono
                        utils.objectIsNULL(registro[11]),//Mail
                        Integer.valueOf(utils.objectIsNULL(registro[12])),//ID tipo direccion
                        utils.objectIsNULL(registro[13]),//Tipo direccion
                        utils.objectIsNULL(registro[14]),//Calle
                        utils.objectIsNULL(registro[15]),//ID estado
                        utils.objectIsNULL(registro[16]),//Estado
                        utils.objectIsNULL(registro[17]),//ID Municipio
                        utils.objectIsNULL(registro[18]),//Municipio
                        utils.objectIsNULL(registro[19]),//ID Colonia
                        utils.objectIsNULL(registro[20]),//Colonia
                        utils.objectIsNULL(registro[21]),//Numero exterior
                        utils.objectIsNULL(registro[22]),//Numero interior
                        utils.objectIsNULL(registro[23]),//Codigo postal
                        utils.objectIsNULL(registro[24]),//Nombre plaza
                        utils.objectIsNULL(registro[25]),//ID Nivel de estudios
                        utils.objectIsNULL(registro[26]),//ID Estado civil
                        utils.objectIsNULL(registro[27]),//ID Plaza
                        utils.objectIsNULL(registro[28]),//ISSEMYM
                        Integer.valueOf(utils.objectIsNULL(registro[29]))//Orden
                ));
            });

            //Validar el numero de registros
            if (lsRegistros.size() == 1) {
                //Si hay solo un registro, no hay direccion fiscal
                servidorPublicoModel = lsRegistros.get(0);
            } else if (lsRegistros.size() > 1) {
                //Si hay mas registros, cuenta con domicilio fiscal y personal
                ServidorPublicoModel datosPersonal;
                ServidorPublicoModel datosFiscal;
                datosPersonal = lsRegistros.stream().filter(
                        r -> r.getIdTipoDireccionPersonal() == this.ID_TIPO_DIRECCION_PERSONAL
                ).findFirst().orElse(null);
                datosFiscal = lsRegistros.stream().filter(
                        r -> r.getIdTipoDireccionPersonal() == this.ID_TIPO_DIRECCION_FISCAL
                ).findFirst().orElse(null);

                //Si existen datos de domicilio personal y fiscal, generar dato integrados
                if (datosPersonal != null && datosFiscal != null) {
                    servidorPublicoModel = datosPersonal;
                    servidorPublicoModel.fusionarDirecciones(datosFiscal);
                } else {
                    //En caso de no existir, se manda el primer registro como dato correcto
                    servidorPublicoModel = lsRegistros.get(0);
                }
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return servidorPublicoModel;
    }

    @Override
    public List<MsgRespuesta> actualizarDatosGenerales(String claveServidor,
            String telefono, String mail, String idNivelEstudios, String idEstadoCivil,
            int idTipoDireccionPersonal, String idEstadoPersonal, String idMunicipioPersonal,
            String idColoniaPersonal, String callePersonal, String numeroExteriorPersonal,
            String numeroInteriorPersonal, String codigoPostalPersonal, String idTipoDireccionFiscal,
            String idEstadoFiscal, String idMunicipioFiscal, String idColoniaFiscal,
            String calleFiscal, String numeroExteriorFiscal, String numeroInteriorFiscal,
            String codigoPostalFiscal) throws BusException {
        List<MsgRespuesta> lsRespuestas = new ArrayList<>();
        try {

            java.util.logging.Logger.getLogger(DatosServidorPublicoServiceImpl.class.getName()).log(
                    Level.INFO, "REV: Ejecutando sentencia SQL...");
            int resultado = datosServidorPublicoDAO.actualizarDatosGenerales(
                    claveServidor, "1", "2", telefono, mail, idNivelEstudios,
                    idEstadoCivil, idTipoDireccionPersonal, idEstadoPersonal,
                    idMunicipioPersonal, idColoniaPersonal, callePersonal,
                    numeroExteriorPersonal, numeroInteriorPersonal, codigoPostalPersonal,
                    idTipoDireccionFiscal, idEstadoFiscal, idMunicipioFiscal,
                    idColoniaFiscal, calleFiscal, numeroExteriorFiscal,
                    numeroInteriorFiscal, codigoPostalFiscal);

            java.util.logging.Logger.getLogger(DatosServidorPublicoServiceImpl.class.getName()).log(
                    Level.INFO, "REV: Setencia SQL ejecutada correctamente.");
            java.util.logging.Logger.getLogger(DatosServidorPublicoServiceImpl.class.getName()).log(
                    Level.INFO, "REV: Resultado obtenido de SQL: {0}", resultado);
            MsgRespuesta msgRespuesta = new MsgRespuesta();
            if (resultado == 1) {
                java.util.logging.Logger.getLogger(DatosServidorPublicoServiceImpl.class.getName()).log(
                        Level.INFO, "REV: Resultado exitoso en 1, generando respuesta...");
                msgRespuesta.setRESPUESTA("Datos actualizados correctamente.");
                lsRespuestas.add(msgRespuesta);
                java.util.logging.Logger.getLogger(DatosServidorPublicoServiceImpl.class.getName()).log(
                        Level.INFO, "REV: Regresando respuesta de consumo exitoso.");
                return lsRespuestas;
            } else {
                java.util.logging.Logger.getLogger(DatosServidorPublicoServiceImpl.class.getName()).log(
                        Level.INFO, "REV: Resultado fallido en 0, generando excepcion...");
                throw new Exception("No se han podido actualizar los datos");
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", e.getMessage());
        }
    }
    //</editor-fold>
}
