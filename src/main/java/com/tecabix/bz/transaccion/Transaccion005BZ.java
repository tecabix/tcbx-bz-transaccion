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

    private TransaccionSolicitudRepository transaccionSolicitudRepository;
    
    private CuentaRepository cuentaRepository;
	private TransaccionRepository transaccionRepository;
	private Catalogo transferencia;
	private Catalogo pendiente;
	private Catalogo monoTrx;
	private Catalogo eliminado;

    private String NO_SE_ENCONTRO_SOLICITUD_DE_TRANSACCION = "No se encontró la solicitud de transacción.";

    public Transaccion005BZ(Transaccion005BzDTO dto) {
		this.transaccionSolicitudRepository = dto.getTransaccionSolicitudRepository();
		this.cuentaRepository = dto.getCuentaRepository();
		this.transaccionRepository = dto.getTransaccionRepository();
		this.transferencia = dto.getTransferencia();
		this.pendiente = dto.getPendiente();
		this.monoTrx = dto.getMonoTrx();
		this.eliminado = dto.getEliminado();
	}

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
        dto.setTransferencia(transferencia);;
        
        ResponseEntity<RSB023> result = new Transaccion001BZ(dto).crear(rqsv030);
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
