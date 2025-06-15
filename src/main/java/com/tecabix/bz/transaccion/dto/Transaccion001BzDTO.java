package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion001BzDTO {

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio para acceder a la entidad Transaccion.
     */
    private TransaccionRepository transaccionRepository;

    /**
     * Catálogo que representa la transferencia.
     */
    private Catalogo transferencia;

    /**
     * Estado "Pendiente" obtenido desde el catálogo.
     */
    private Catalogo pendiente;

    /**
     * Obtiene el repositorio de cuentas.
     *
     * @return el repositorio de cuentas.
     */
    public CuentaRepository getCuentaRepository() {
        return cuentaRepository;
    }

    /**
     * Establece el repositorio de cuentas.
     *
     * @param repository el repositorio de cuentas a establecer.
     */
    public void setCuentaRepository(final CuentaRepository repository) {
        this.cuentaRepository = repository;
    }

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
     * Obtiene el catálogo de transferencia.
     *
     * @return el catálogo de transferencia.
     */
    public Catalogo getTransferencia() {
        return transferencia;
    }

    /**
     * Establece el catálogo de transferencia.
     *
     * @param operacion el catálogo de transferencia a establecer.
     */
    public void setTransferencia(final Catalogo operacion) {
        this.transferencia = operacion;
    }

    /**
     * Obtiene el catálogo correspondiente al estado pendiente.
     *
     * @return el catálogo pendiente.
     */
    public Catalogo getPendiente() {
        return pendiente;
    }

    /**
     * Establece el catálogo correspondiente al estado pendiente.
     *
     * @param estatus el catálogo pendiente a establecer.
     */
    public void setPendiente(final Catalogo estatus) {
        this.pendiente = estatus;
    }

}
