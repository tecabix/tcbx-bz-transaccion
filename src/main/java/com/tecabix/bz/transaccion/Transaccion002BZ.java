package com.tecabix.bz.transaccion;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.transaccion.dto.Transaccion002BzDTO;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Transaccion;
import com.tecabix.db.entity.Usuario;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionRepository;
import com.tecabix.res.b.RSB024;
import com.tecabix.sv.rq.RQSV031;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion002BZ {

	private TransaccionRepository transaccionRepository;
	
	private PersonaFisicaRepository personaFisicaRepository;
	
	private Catalogo activo;
	
	private String NO_SE_ENCONTRO_LA_TRANSACCION = "No se encontró la transacción.";
	
	private String NO_SE_ENCONTRO_PERSONA_FISICA = "No se encontro a la persona fisica.";

    public Transaccion002BZ(Transaccion002BzDTO dto) {
    	this.transaccionRepository = dto.getTransaccionRepository();
		this.personaFisicaRepository = dto.getPersonaFisicaRepository();
		this.activo = dto.getActivo();
	}

	public ResponseEntity<RSB024> obtenerEstatus(final RQSV031 rqsv031) {

        RSB024 rsb024 = rqsv031.getRsb024();
        UUID clave = rqsv031.getClave();

        Transaccion transaccion;
        Optional<Transaccion> trxOpt;
        trxOpt = transaccionRepository.findByClave(clave);
        if (trxOpt.isEmpty()) {
            return rsb024.badRequest(NO_SE_ENCONTRO_LA_TRANSACCION);
        }
        transaccion = trxOpt.get();

        if (!transaccion.getEstatus().equals(activo)) {
            return rsb024.notFound(NO_SE_ENCONTRO_LA_TRANSACCION);
        }

        long idPersona;
        Usuario usr = transaccion.getDestino().getUsuario();
        idPersona = usr.getUsuarioPersona().getPersona().getId();
        PersonaFisica personaFisica;
        Optional<PersonaFisica> fisicaOpt;
        fisicaOpt = personaFisicaRepository.findByPersona(idPersona);
        if (fisicaOpt.isEmpty()) {
            return rsb024.badRequest(NO_SE_ENCONTRO_PERSONA_FISICA);
        }
        personaFisica = fisicaOpt.get();
        return rsb024.ok(transaccion, personaFisica);
    }
}
