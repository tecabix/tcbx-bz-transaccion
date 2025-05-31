package com.tecabix.bz.transaccion;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.transaccion.dto.Transaccion001BzDTO;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Transaccion;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;
import com.tecabix.req.b.RQB005;
import com.tecabix.res.b.RSB023;
import com.tecabix.sv.rq.RQSV030;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion001BZ {
	
	private CuentaRepository cuentaRepository;
	private TransaccionRepository transaccionRepository;
	
	private Catalogo transferencia;
	private Catalogo pendiente;

	private String EL_MONTO_NO_ES_VALIDO = "El monto no es valido.";
	private String NO_SE_ENCONTRO_LA_CUENTA_DE_ORIGEN = "No se encontró la cuenta de origen.";
	private String NO_SE_ENCONTRO_LA_CUENTA_DESTINO = "No se encontró la cuenta destino.";
	private String CUENTA_DE_ORIGEN_Y_DESTINO_SON_LA_MISMA = "La cuenta de origen y destino son la misma.";
	private String EL_SALDO_DEL_ORDENANTE_ES_INSUFICIENTE = "El saldo del ordenante es insuficiente";
	
	private int CIEN = 100;

    public Transaccion001BZ(Transaccion001BzDTO dto) {
    	this.cuentaRepository = dto.getCuentaRepository();
		this.transaccionRepository = dto.getTransaccionRepository();
		this.transferencia = dto.getTransferencia();
		this.pendiente = dto.getPendiente();
	}
    
	public ResponseEntity<RSB023> crear(final RQSV030 rqsv030) {

        RSB023 rsb023 = rqsv030.getRsb023();
        Sesion sesion = rqsv030.getSesion();
        RQB005 rqb005 = rqsv030.getRqb005();
        String descripcion = rqb005.getDescripcion();

        if (descripcion == null || descripcion.isBlank()) {
            rqb005.setDescripcion("transferencia");
        }
        
        if (rqb005.getMonto() < CIEN) {
            return rsb023.badRequest(EL_MONTO_NO_ES_VALIDO);
        }

        Transaccion transaccion = new Transaccion();
        Cuenta origen;
        Optional<Cuenta> origenOpt;
        origenOpt = cuentaRepository.findByUsuario(sesion.getUsuario().getId());
        if (origenOpt.isEmpty()) {
            return rsb023.notFound(NO_SE_ENCONTRO_LA_CUENTA_DE_ORIGEN);
        }
        origen = origenOpt.get();

        Cuenta destino;
        Optional<Cuenta> destinoOpt;
        destinoOpt = cuentaRepository.findById(rqb005.getCuenta());
        if (destinoOpt.isEmpty()) {
            return rsb023.notFound(NO_SE_ENCONTRO_LA_CUENTA_DESTINO);
        }
        destino = destinoOpt.get();

        transaccion.setOrigen(origen);
        transaccion.setDestino(destino);
        transaccion.setDescripcion(rsb023.getDescripcion());

        if (transaccion.getOrigen().equals(transaccion.getDestino())) {
            return rsb023.badRequest(CUENTA_DE_ORIGEN_Y_DESTINO_SON_LA_MISMA);
        }
        transaccion.setMonto(rqb005.getMonto());
        transaccion.setTipo(transferencia);
        if (transaccion.getOrigen().getSaldo() < transaccion.getMonto()) {
            return rsb023.conflict(EL_SALDO_DEL_ORDENANTE_ES_INSUFICIENTE);
        }
        transaccion.setEstatus(pendiente);
        transaccion.setIdUsuarioModificado(sesion.getUsuario().getId());
        transaccion.setFechaDeModificacion(LocalDateTime.now());
        transaccion.setClave(UUID.randomUUID());
        return rsb023.ok(transaccionRepository.save(transaccion));
    }
}
