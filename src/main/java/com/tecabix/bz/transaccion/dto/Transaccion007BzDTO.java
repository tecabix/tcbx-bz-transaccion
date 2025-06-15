package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion007BzDTO {

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio para acceder a la entidad Transaccion.
     */
    private TransaccionRepository transaccionRepository;

    /**
     * Repositorio para acceder a la entidad PersonaFisica.
     */
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Obtiene el repositorio de cuentas.
     *
     * @return el objeto {@link CuentaRepository} utilizado para acceder
     *         a los datos de cuentas.
     */
    public CuentaRepository getCuentaRepository() {
        return cuentaRepository;
    }

    /**
     * Establece el repositorio de cuentas.
     *
     * @param repository el objeto {@link CuentaRepository} que se
     *        utilizará para acceder a los datos de cuentas.
     */
    public void setCuentaRepository(final CuentaRepository repository) {
        this.cuentaRepository = repository;
    }

    /**
     * Obtiene el repositorio de transacciones.
     *
     * @return el objeto {@link TransaccionRepository} utilizado para acceder
     *         a los datos de transacciones.
     */
    public TransaccionRepository getTransaccionRepository() {
        return transaccionRepository;
    }

    /**
     * Establece el repositorio de transacciones.
     *
     * @param repository el objeto {@link TransaccionRepository} que
     *        se utilizará para acceder a los datos de transacciones.
     */
    public void setTransaccionRepository(
        final TransaccionRepository repository) {

        this.transaccionRepository = repository;
    }

    /**
     * Obtiene el repositorio de personas físicas.
     *
     * @return el objeto {@link PersonaFisicaRepository} utilizado para acceder
     *         a los datos de personas físicas.
     */
    public PersonaFisicaRepository getPersonaFisicaRepository() {
        return personaFisicaRepository;
    }

    /**
     * Establece el repositorio de personas físicas.
     *
     * @param repository el objeto {@link PersonaFisicaRepository}
     *        que se utilizará para acceder a los datos de personas físicas.
     */
    public void setPersonaFisicaRepository(
        final PersonaFisicaRepository repository) {

        this.personaFisicaRepository = repository;
    }
}
