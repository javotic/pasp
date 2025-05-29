package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.IncrementoSalarialDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ServidorPublicoIncrementoSalarialDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.CategoriaIncrementoSalarialModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TabuladorBurocrataModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TabuladorDocenteModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.IncrementoSalarialService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de servicio de gestion del incremento salarial.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 19/07/2022.
 */
@Service
public class IncrementoSalarialServiceImpl implements IncrementoSalarialService {

    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(IncrementoSalarialServiceImpl.class);
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private IncrementoSalarialDAO incrementoSalarialDAO;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    @Override
    public ServidorPublicoIncrementoSalarialDTO obtenerServPubIncrSal(String claveServidor) throws BusException {
        Utils utils = new Utils();
        ServidorPublicoIncrementoSalarialDTO servidorPublicoIncrementoSalarialDTO
                = new ServidorPublicoIncrementoSalarialDTO();
        try {
            List<Object[]> lsResultado = this.incrementoSalarialDAO.obtenerServPubIncrSal(claveServidor);

            if (lsResultado != null && lsResultado.size() == 1) {
                Object[] resultado = lsResultado.get(0);
                servidorPublicoIncrementoSalarialDTO = new ServidorPublicoIncrementoSalarialDTO(
                        utils.objectIsNULL(resultado[0]).trim(),//Clave de servidor publico
                        utils.objectIsNULL(resultado[1]).trim(),//Nombre de servidor publico
                        utils.objectIsNULL(resultado[2]).trim(),//Nombre de puesto
                        utils.objectIsNULL(resultado[3]).trim(),//Codigo de puesto
                        Integer.parseInt(utils.objectIsNULL(resultado[4])) //Tipo de servidor publico
                );
            }

        } catch (Exception e) {
            LOG.error("Error {IncrementoSalarialServiceImpl.obtenerServPubIncrSal} ", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return servidorPublicoIncrementoSalarialDTO;
    }


    @Override
    public List<CategoriaIncrementoSalarialModel> obtenerCategoriasIncrSal() throws BusException {
        Utils utils = new Utils();
        List<CategoriaIncrementoSalarialModel> lsCategorias = new ArrayList<>();
        
        try {
            List<Object[]> lsResultado = this.incrementoSalarialDAO.obtenerCategoriasIncrSal();
            if (lsResultado != null && !lsResultado.isEmpty()) {
                for(Object[] resultado : lsResultado){
                    lsCategorias.add(new CategoriaIncrementoSalarialModel(
                            utils.objectIsNULL(resultado[0]).trim(),
                            Double.parseDouble(utils.objectIsNULL(resultado[1]))
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Error {IncrementoSalarialServiceImpl.obtenerCategoriasIncrSal} ", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }
        return lsCategorias;
    }

    @Override
    public List<TabuladorDocenteModel> obtenerTabuladorDocIncrSal(String claveServidorPublico) throws BusException {
        Utils utils = new Utils();
        List<TabuladorDocenteModel> lsTabulador = new ArrayList<>();
        
        try {
            List<Object[]> lsResultado = this.incrementoSalarialDAO.obtenerTabuladorDocIncrSal(claveServidorPublico);
            if (lsResultado != null && !lsResultado.isEmpty()) {
                for(Object[] resultado : lsResultado){
                    lsTabulador.add(new TabuladorDocenteModel(
                            Double.parseDouble(utils.objectIsNULL(resultado[0])),
                            Double.parseDouble(utils.objectIsNULL(resultado[1])),
                            Double.parseDouble(utils.objectIsNULL(resultado[2])),
                            Double.parseDouble(utils.objectIsNULL(resultado[3])),
                            Double.parseDouble(utils.objectIsNULL(resultado[4])),
                            Double.parseDouble(utils.objectIsNULL(resultado[5])),
                            Double.parseDouble(utils.objectIsNULL(resultado[6]))
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Error {IncrementoSalarialServiceImpl.obtenerTabuladorDocIncrSal} ", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }
        return lsTabulador;
    }

    @Override
    public List<TabuladorBurocrataModel> obtenerTabuladorBurIncrSal(String codigoPuesto) throws BusException {
        Utils utils = new Utils();
        List<TabuladorBurocrataModel> lsTabulador = new ArrayList<>();
        
        try {
            List<Object[]> lsResultado = this.incrementoSalarialDAO.obtenerTabuladorBurIncrSal(codigoPuesto);
            if (lsResultado != null && !lsResultado.isEmpty()) {
                for(Object[] resultado : lsResultado){
                    lsTabulador.add(new TabuladorBurocrataModel(
                            Double.parseDouble(utils.objectIsNULL(resultado[0])),
                            Double.parseDouble(utils.objectIsNULL(resultado[1])),
                            Double.parseDouble(utils.objectIsNULL(resultado[2])),
                            Double.parseDouble(utils.objectIsNULL(resultado[3])),
                            Double.parseDouble(utils.objectIsNULL(resultado[4])),
                            Double.parseDouble(utils.objectIsNULL(resultado[5])),
                            Double.parseDouble(utils.objectIsNULL(resultado[6])),
                            Double.parseDouble(utils.objectIsNULL(resultado[7])),
                            utils.objectIsNULL(resultado[8])
                    ));
                }
            }
        } catch (Exception e) {
            LOG.error("Error {IncrementoSalarialServiceImpl.obtenerTabuladorDocIncrSal} ", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }
        return lsTabulador;
    }
    //</editor-fold>
}
