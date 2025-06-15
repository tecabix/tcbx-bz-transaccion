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

    /**
     * Repositorio para acceder a la entidad Transaccion.
     */
    private final TransaccionRepository transaccionRepository;

    /**
     * Repositorio para acceder a la entidad PersonaFisica.
     */
    private final PersonaFisicaRepository personaFisicaRepository;

    /**
     * Estado "activo" obtenido desde el catálogo.
     */
    private final Catalogo activo;

    /**
     * Transacción no encontrada.
     */
    private static final String NO_SE_ENCONTRO_LA_TRANSACCION;

    static {
        NO_SE_ENCONTRO_LA_TRANSACCION = "No se encontró la transacción.";
        NO_SE_ENCONTRO_PERSONA_FISICA = "No se encontro a la persona fisica.";
    }

    /**
     * Persona fisica no encontrada.
     */
    private static final String NO_SE_ENCONTRO_PERSONA_FISICA;

    /**
     * Constructor de la clase {@code Transaccion002BZ}.
     * <p>
     * Inicializa una nueva instancia utilizando los datos proporcionados en el
     * DTO {@code Transaccion002BzDTO}.
     * Este constructor extrae los repositorios y el estado del objeto activo
     * desde el DTO.
     *
     * @param dto el objeto {@code Transaccion002BzDTO} que contiene los datos
     *            necesarios para inicializar la transacción, incluyendo los
     *            repositorios de transacciones y personas físicas, así como el
     *            estado del objeto activo.
     */
    public Transaccion002BZ(final Transaccion002BzDTO dto) {
        this.transaccionRepository = dto.getTransaccionRepository();
        this.personaFisicaRepository = dto.getPersonaFisicaRepository();
        this.activo = dto.getActivo();
    }

    /**
     * Método para obtener estatus de transacción.
     *
     * @param rqsv031 datos para estatus.
     * @return {@link ResponseEntity} con un objeto {@link RSB024} que contiene
     *         información para crear préstamo.
     */
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
