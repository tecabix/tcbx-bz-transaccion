package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion002BzDTO {

    /**
     * Repositorio para acceder a la entidad Transaccion.
     */
    private TransaccionRepository transaccionRepository;

    /**
     * Repositorio para acceder a la entidad PersonaFisica.
     */
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Estado "activo" obtenido desde el catálogo.
     */
    private Catalogo activo;

    /**
     * Obtiene el repositorio de transacciones.
     *
     * @return el repositorio de transacciones.
     */
    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }

    /**
     * Establece el repositorio de transacciones.
     *
     * @param repository el repositorio de transacciones
     *        a establecer.
     */
    public void setTransaccionRepository(
        final TransaccionRepository repository) {
        this.transaccionRepository = repository;
    }

    /**
     * Obtiene el repositorio de personas físicas.
     *
     * @return el repositorio de personas físicas.
     */
    public PersonaFisicaRepository getPersonaFisicaRepository() {
        return personaFisicaRepository;
    }

    /**
     * Establece el repositorio de personas físicas.
     *
     * @param repository el repositorio de personas físicas
     *        a establecer.
     */
    public void setPersonaFisicaRepository(
        final PersonaFisicaRepository repository) {
        this.personaFisicaRepository = repository;
    }

    /**
     * Obtiene el catálogo correspondiente al estado activo.
     *
     * @return el catálogo activo.
     */
    public Catalogo getActivo() {
        return activo;
    }

    /**
     * Establece el catálogo correspondiente al estado activo.
     *
     * @param estatus el catálogo activo a establecer.
     */
    public void setActivo(final Catalogo estatus) {
        this.activo = estatus;
    }

}
