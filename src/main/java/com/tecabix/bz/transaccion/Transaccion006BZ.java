package com.tecabix.bz.transaccion;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.tecabix.bz.transaccion.dto.Transaccion006BzDTO;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Transaccion;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;
import com.tecabix.res.b.RSB022;
import com.tecabix.sv.rq.RQSV035;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion006BZ {

	private CuentaRepository cuentaRepository;
	
	private TransaccionRepository transaccionRepository;
	
	private String NO_SE_ENCONTRO_LA_CUENTA = "No se encontro la cuenta.";
	
    public Transaccion006BZ(Transaccion006BzDTO dto) {
		this.cuentaRepository = dto.getCuentaRepository();
		this.transaccionRepository = dto.getTransaccionRepository();
	}

	public ResponseEntity<RSB022> buscar(final RQSV035 rqsv035) {

        RSB022 rsb022 = rqsv035.getRsb022();
        byte elementos = rqsv035.getElementos();
        short pagina = rqsv035.getPagina();
        Sesion sesion = rqsv035.getSesion();

        Sort sort = Sort.by(Sort.Direction.DESC, "fechaDeModificacion");
        Cuenta cuenta;
        Optional<Cuenta> ctaOpt;
        ctaOpt = cuentaRepository.findByUsuario(sesion.getUsuario().getId());
        if (ctaOpt.isEmpty()) {
            return rsb022.notFound(NO_SE_ENCONTRO_LA_CUENTA);
        }
        cuenta = ctaOpt.get();

        Pageable pageable = PageRequest.of(pagina, elementos, sort);
        Page<Transaccion> pageTrx;
        UUID clave = cuenta.getClave();
        pageTrx = transaccionRepository.findByCuenta(clave, clave, pageable);
        pageTrx.stream().filter(x -> x.getOrigen().equals(cuenta))
                       .forEach(x -> x.setMonto(x.getMonto() * -1));
        return rsb022.ok(pageTrx);
    }
}
