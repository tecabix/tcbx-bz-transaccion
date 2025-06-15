package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion006BzDTO {

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio para acceder a la entidad Transaccion.
     */
    private TransaccionRepository transaccionRepository;

    /**
     * Obtiene el repositorio de cuentas.
     *
     * @return el objeto {@link CuentaRepository} utilizado para acceder a
     *         los datos de cuentas.
     */
    public CuentaRepository getCuentaRepository() {
        return cuentaRepository;
    }

    /**
     * Establece el repositorio de cuentas.
     *
     * @param repository el objeto {@link CuentaRepository} que se
     *        utilizarápara acceder a los datos de cuentas.
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
}
