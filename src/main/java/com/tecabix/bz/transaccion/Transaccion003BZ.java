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

    /**
     * Repositorio para acceder a la entidad PersonaFisica.
     */
    private final PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private final CuentaRepository cuentaRepository;

    /**
     * Repositorio para acceder a la entidad TransaccionSolicitud.
     */
    private final TransaccionSolicitudRepository transaccionSolicitudRepository;

    /**
     * Catálogo que representa la monoTransaccion.
     */
    private final Catalogo monoTrx;

    /**
     * Estado "activo" obtenido desde el catálogo.
     */
    private final Catalogo activo;

    /**
     * Cuenta origen no existe.
     */
    private static final String NO_SE_ENCONTRO_LA_CUENTA;

    static {
        NO_SE_ENCONTRO_LA_CUENTA = "No se encontro la cuenta.";
        NO_SE_ENCONTRO_PERSONA_FISICA = "No se encontro a la persona fisica.";
    }
    /**
     * Persona fisica no encontrada.
     */
    private static final String NO_SE_ENCONTRO_PERSONA_FISICA;

    /**
     * Constructor de la clase {@code Transaccion003BZ}.
     * <p>
     * Inicializa una nueva instancia utilizando los datos proporcionados
     * en el DTO {@code Transaccion003BzDTO}.
     * Este constructor asigna los repositorios y catálogos necesarios para el
     * procesamiento de la transacción.
     *
     * @param dto el objeto {@code Transaccion003BzDTO} que contiene las
     *            dependencias necesarias, incluyendo los repositorios de
     *            persona física, cuenta, solicitud de transacción, así como
     *            los catálogos de monoTransacción y estado activo.
     */
    public Transaccion003BZ(final Transaccion003BzDTO dto) {
        this.personaFisicaRepository = dto.getPersonaFisicaRepository();
        this.cuentaRepository = dto.getCuentaRepository();
        this.transaccionSolicitudRepository = dto
            .getTransaccionSolicitudRepository();
        this.monoTrx = dto.getMonoTrx();
        this.activo = dto.getActivo();
    }

    /**
     * Método para crear solicitud de transacción.
     *
     * @param rqsv032 datos de solicitud.
     * @return {@link ResponseEntity} con un objeto {@link RQSV032} que contiene
     *         información para crear solicitud.
     */
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
        String apellidoPaterno;
        apellidoPaterno = personaFisica.getApellidoPaterno().toUpperCase();
        String apellidoMaterno;
        apellidoMaterno = personaFisica.getApellidoMaterno().toUpperCase();
        String nombre = personaFisica.getNombre().toUpperCase();
        trxSolicitud.setDescripcion(apellidoPaterno + " "
        + apellidoMaterno + " " + nombre);
        trxSolicitud.setEstatus(activo);
        trxSolicitud.setIdUsuarioModificado(idUsr);
        trxSolicitud.setFechaModificado(LocalDateTime.now());
        trxSolicitud.setClave(UUID.randomUUID());
        return rsb026.ok(transaccionSolicitudRepository.save(trxSolicitud));
    }
}
