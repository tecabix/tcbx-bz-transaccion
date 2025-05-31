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

	private TransaccionSolicitudRepository transaccionSolicitudRepository;
	
	private String NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION = "No se encontró la solicitud de transacción.";

    public Transaccion004BZ(TransaccionSolicitudRepository transaccionSolicitudRepository) {
		this.transaccionSolicitudRepository = transaccionSolicitudRepository;
	}

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
