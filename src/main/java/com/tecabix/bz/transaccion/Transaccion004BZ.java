package com.tecabix.bz.transaccion;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.tecabix.db.entity.TransaccionSolicitud;
import com.tecabix.db.repository.TransaccionSolicitudRepository;
import com.tecabix.res.b.RSB027;
import com.tecabix.sv.rq.RQSV033;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion004BZ {

    /**
     * Repositorio para acceder a la entidad TransaccionSolicitud.
     */
    private final TransaccionSolicitudRepository transaccionSolicitudRepository;

    /**
     * Solicitud de transacción no existe.
     */
    private static final String NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION;

    static {
        NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION = "No se encontró la solicitud de transacción.";
    }

    /**
     * Constructor de la clase {@code Transaccion004BZ}.
     *
     * @param repository el repositorio utilizado para
     *        acceder a las solicitudes de transacción.
     */
    public Transaccion004BZ(final TransaccionSolicitudRepository repository) {
        this.transaccionSolicitudRepository = repository;
    }

    /**
     * Método para obtener una solicitud de transacción.
     *
     * @param rqsv033 datos de consulta.
     * @return {@link ResponseEntity} con un objeto {@link RSB027} que contiene
     *         información para obtener la solicitud.
     */
    public ResponseEntity<RSB027> obtenerSolicitud(final RQSV033 rqsv033) {

        RSB027 rsb027 = rqsv033.getRsb027();
        UUID clave = rsb027.getClave();
        TransaccionSolicitud trxSolicitud;
        Optional<TransaccionSolicitud> trxOpt;
        trxOpt = transaccionSolicitudRepository.findByClave(clave);
        if (trxOpt.isEmpty()) {
            return rsb027.badRequest(NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION);
        }
        trxSolicitud = trxOpt.get();

        return rsb027.ok(trxSolicitud);
    }
}
