package com.tecabix.bz.transaccion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.tecabix.bz.transaccion.dto.Transaccion003BzDTO;
import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.entity.Cuenta;
import com.tecabix.db.entity.Persona;
import com.tecabix.db.entity.PersonaFisica;
import com.tecabix.db.entity.Sesion;
import com.tecabix.db.entity.TransaccionSolicitud;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionSolicitudRepository;
import com.tecabix.req.b.RQB006;
import com.tecabix.res.b.RSB026;
import com.tecabix.sv.rq.RQSV032;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion003BZ {

	private PersonaFisicaRepository personaFisicaRepository;
	
	private CuentaRepository cuentaRepository;
	
	private TransaccionSolicitudRepository transaccionSolicitudRepository;
	
	private Catalogo monoTrx;
	
	private Catalogo activo;
	
	private String NO_SE_ENCONTRO_LA_CUENTA = "No se encontro la cuenta.";
	
	private String NO_SE_ENCONTRO_PERSONA_FISICA = "No se encontro a la persona fisica.";
	
	
    public Transaccion003BZ(Transaccion003BzDTO dto) {
		this.personaFisicaRepository = dto.getPersonaFisicaRepository();
		this.cuentaRepository = dto.getCuentaRepository();
		this.transaccionSolicitudRepository = dto.getTransaccionSolicitudRepository();
		this.monoTrx = dto.getMonoTrx();
		this.activo = dto.getActivo();
	}


	public ResponseEntity<RSB026> crearSolicitud(final RQSV032 rqsv032) {

        RSB026 rsb026 = rqsv032.getRsb026();
        Sesion sesion = rqsv032.getSesion();
        RQB006 rqb006 = rqsv032.getRqb006();
        Long idUsr = sesion.getUsuario().getId();

        Cuenta cuenta;
        Optional<Cuenta> ctaOpt;
        ctaOpt = cuentaRepository.findByUsuario(sesion.getUsuario().getId());
        if (ctaOpt.isEmpty()) {
            return rsb026.notFound(NO_SE_ENCONTRO_LA_CUENTA);
        }
        cuenta = ctaOpt.get();

        PersonaFisica personaFisica;
        Persona persona = sesion.getUsuario().getUsuarioPersona().getPersona();
        Long id = persona.getId();
        Optional<PersonaFisica> fisicaOpt;
        fisicaOpt = personaFisicaRepository.findByPersona(id);
        if (fisicaOpt.isEmpty()) {
            return rsb026.badRequest(NO_SE_ENCONTRO_PERSONA_FISICA);
        }
        personaFisica = fisicaOpt.get();
        TransaccionSolicitud trxSolicitud = new TransaccionSolicitud();
        trxSolicitud.setTitulo(rqb006.getTitulo());
        trxSolicitud.setTransaccion(rqb006.getMonto());
        trxSolicitud.setCuenta(cuenta.getId());
        trxSolicitud.setVencimiento(LocalDate.now().plusDays(1));
        trxSolicitud.setTipo(monoTrx);
        String apellidoPaterno = personaFisica.getApellidoPaterno();
        trxSolicitud.setDescripcion(apellidoPaterno.toUpperCase() + " "
                + personaFisica.getApellidoMaterno().toUpperCase() + " "
                + personaFisica.getNombre().toUpperCase());
        trxSolicitud.setEstatus(activo);
        trxSolicitud.setIdUsuarioModificado(idUsr);
        trxSolicitud.setFechaModificado(LocalDateTime.now());
        trxSolicitud.setClave(UUID.randomUUID());
        return rsb026.ok(transaccionSolicitudRepository.save(trxSolicitud));
    }
}
