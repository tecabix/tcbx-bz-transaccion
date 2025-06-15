package com.tecabix.bz.transaccion;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tecabix.bz.transaccion.dto.Transaccion001BzDTO;
import com.tecabix.bz.transaccion.dto.Transaccion005BzDTO;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.TransaccionSolicitud;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;
import com.tecabix.db.repository.TransaccionSolicitudRepository;
import com.tecabix.req.b.RQB005;
import com.tecabix.res.b.RSB023;
import com.tecabix.sv.rq.RQSV030;
import com.tecabix.sv.rq.RQSV034;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion005BZ {

    /**
     * Repositorio para acceder a la entidad TransaccionSolicitud.
     */
    private final TransaccionSolicitudRepository transaccionSolicitudRepository;

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private final CuentaRepository cuentaRepository;

    /**
     * Repositorio para acceder a la entidad Transaccion.
     */
    private final TransaccionRepository transaccionRepository;

    /**
     * Catálogo que representa la transferencia.
     */
    private final Catalogo transferencia;

    /**
     * Catálogo correspondiente al estado pendiente.
     */
    private final Catalogo pendiente;

    /**
     * Catálogo que representa la monoTransaccion.
     */
    private final Catalogo monoTrx;

    /**
     * Catálogo correspondiente al estado eliminado.
     */
    private final Catalogo eliminado;

    /**
     * Solicitud de transacción no existe.
     */
    private static final String NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION;

    static {
        NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION = "No se encontró la solicitud de transacción.";
    }

    /**
     * Constructor de la clase {@code Transaccion005BZ}.
     * Inicializa los atributos utilizando un objeto
     * {@code Transaccion005BzDTO}.
     *
     * @param dto el objeto de transferencia de datos que contiene las
     *            dependencias y catálogos necesarios para la ejecución de
     *            la transacción.
     */
    public Transaccion005BZ(final Transaccion005BzDTO dto) {

        this.transaccionSolicitudRepository = dto
            .getTransaccionSolicitudRepository();
        this.cuentaRepository = dto.getCuentaRepository();
        this.transaccionRepository = dto.getTransaccionRepository();
        this.transferencia = dto.getTransferencia();
        this.pendiente = dto.getPendiente();
        this.monoTrx = dto.getMonoTrx();
        this.eliminado = dto.getEliminado();
    }

    /**
     * Método para ejecutar la solicitud de transacción.
     *
     * @param rqsv034 datos de ejecución.
     * @return {@link ResponseEntity} con un objeto {@link RSB023} que contiene
     *         información para ejecutar la solicitud de transacción.
     */
    public ResponseEntity<RSB023> ejecutarTransaccionSolicitud(
        final RQSV034 rqsv034) {

        RSB023 rsb023 = rqsv034.getRsb023();
        UUID clave = rsb023.getDato();
        UUID token = rsb023.getClave();
        Sesion sesion = rqsv034.getSesion();
        Long idUsr = sesion.getUsuario().getId();

        TransaccionSolicitud trxSolicitud;
        Optional<TransaccionSolicitud> trxOpt;
        trxOpt = transaccionSolicitudRepository.findByClave(clave);
        if (trxOpt.isEmpty()) {
            return new RSB023(token)
                .unauthorized(NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION);
        }

        trxSolicitud = trxOpt.get();
        RQB005 body = new RQB005();
        body.setCuenta(trxSolicitud.getCuenta());
        body.setMonto(trxSolicitud.getTransaccion());
        body.setDescripcion(trxSolicitud.getTitulo());
        RQSV030 rqsv030 = new RQSV030(rsb023);
        rqsv030.setRqb005(body);
        Transaccion001BzDTO dto = new Transaccion001BzDTO();
        dto.setCuentaRepository(cuentaRepository);
        dto.setPendiente(pendiente);
        dto.setTransaccionRepository(transaccionRepository);
        dto.setTransferencia(transferencia);

        ResponseEntity<RSB023> result;
        result = new Transaccion001BZ(dto).crear(rqsv030);
        if (trxSolicitud.getTipo().equals(monoTrx)) {
            if (result.getStatusCodeValue() == HttpStatus.OK.value()) {
                trxSolicitud.setEstatus(eliminado);
                trxSolicitud.setIdUsuarioModificado(idUsr);
                trxSolicitud.setFechaModificado(LocalDateTime.now());
                transaccionSolicitudRepository.save(trxSolicitud);
            }
        }
        return result;
    }
}
