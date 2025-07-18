package com.tecabix.bz.transaccion;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.transaccion.dto.Transaccion007BzDTO;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.Transaccion;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionRepository;
import com.tecabix.res.b.RSB025;
import com.tecabix.sv.rq.RQSV036;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion007BZ {

	private CuentaRepository cuentaRepository;
	
	private TransaccionRepository transaccionRepository;
	
	private PersonaFisicaRepository personaFisicaRepository;
	
	private String NO_SE_ENCONTRO_LA_CUENTA = "No se encontro la cuenta.";
	
	private String NO_SE_ENCONTRO_LA_TRANSACCION = "No se encontró la transacción.";
	
	private String NO_SE_ENCONTRO_PERSONA_FISICA = "No se encontro a la persona fisica.";

    public Transaccion007BZ(Transaccion007BzDTO dto) {
		this.cuentaRepository = dto.getCuentaRepository();
		this.transaccionRepository = dto.getTransaccionRepository();
		this.personaFisicaRepository = dto.getPersonaFisicaRepository();
	}

	public ResponseEntity<RSB025> obtener(final RQSV036 rqsv036) {

        RSB025 rsb025 = rqsv036.getRsb025();
        Sesion sesion = rqsv036.getSesion();
        UUID clave = rqsv036.getClave();

        Cuenta cuenta;
        Optional<Cuenta> ctaOpt;
        ctaOpt = cuentaRepository.findByUsuario(sesion.getUsuario().getId());
        if (ctaOpt.isEmpty()) {
            return rsb025.notFound(NO_SE_ENCONTRO_LA_CUENTA);
        }
        cuenta = ctaOpt.get();

        Transaccion transaccion;
        Optional<Transaccion> trxOpt;
        trxOpt = transaccionRepository.findByClave(clave);
        if (trxOpt.isEmpty()) {
            return rsb025.badRequest(NO_SE_ENCONTRO_LA_TRANSACCION);
        }
        transaccion = trxOpt.get();

        Usuario destinoUsr = transaccion.getDestino().getUsuario();
        Usuario origenUsr = transaccion.getOrigen().getUsuario();

        long idPersona;
        if (transaccion.getOrigen().equals(cuenta)) {
            idPersona = destinoUsr.getUsuarioPersona().getPersona().getId();
        } else {
            idPersona = origenUsr.getUsuarioPersona().getPersona().getId();
        }
        PersonaFisica personaFisica;
        Optional<PersonaFisica> fisicaOpt;
        fisicaOpt = personaFisicaRepository.findByPersona(idPersona);
        if (fisicaOpt.isEmpty()) {
            return rsb025.notFound(NO_SE_ENCONTRO_PERSONA_FISICA);
        }
        personaFisica = fisicaOpt.get();
        return rsb025.ok(transaccion, personaFisica);
    }
}
