package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;
import com.tecabix.db.repository.TransaccionSolicitudRepository;

/**
 *
 * @author Ramirez Urrutia Angel Abinadi
 */
public class Transaccion005BzDTO {

    /**
     * Repositorio para acceder a la entidad TransaccionSolicitud.
     */
    private TransaccionSolicitudRepository transaccionSolicitudRepository;

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
     * Catálogo que representa la monoTransaccion.
     */
    private Catalogo monoTrx;

    /**
     * Estado "Eliminado" obtenido desde el catálogo.
     */
    private Catalogo eliminado;

    /**
     * Obtiene el repositorio de solicitudes de transacción.
     *
     * @return el repositorio de solicitudes de transacción.
     */
    public TransaccionSolicitudRepository getTransaccionSolicitudRepository() {
        return transaccionSolicitudRepository;
    }

    /**
     * Establece el repositorio de solicitudes de transacción.
     *
     * @param repository el repositorio de solicitudes de
     *        transacción a establecer.
     */
    public void setTransaccionSolicitudRepository(
        final TransaccionSolicitudRepository repository) {

        this.transaccionSolicitudRepository = repository;
    }

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
     * @param repository el repositorio de transacciones a
     *        establecer.
     */
    public void setTransaccionRepository(
        final TransaccionRepository repository) {

        this.transaccionRepository = repository;
    }

    /**
     * Obtiene el catálogo que representa la transferencia.
     *
     * @return el catálogo de transferencia.
     */
    public Catalogo getTransferencia() {
        return transferencia;
    }

    /**
     * Establece el catálogo que representa la transferencia.
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

    /**
     * Obtiene el catálogo que representa la monoTransacción.
     *
     * @return el catálogo monoTransacción.
     */
    public Catalogo getMonoTrx() {
        return monoTrx;
    }

    /**
     * Establece el catálogo que representa la monoTransacción.
     *
     * @param trx el catálogo monoTransacción a establecer.
     */
    public void setMonoTrx(final Catalogo trx) {
        this.monoTrx = trx;
    }

    /**
     * Obtiene el catálogo correspondiente al estado eliminado.
     *
     * @return el catálogo eliminado.
     */
    public Catalogo getEliminado() {
        return eliminado;
    }

    /**
     * Establece el catálogo correspondiente al estado eliminado.
     *
     * @param estatus el catálogo eliminado a establecer.
     */
    public void setEliminado(final Catalogo estatus) {
        this.eliminado = estatus;
    }

}
