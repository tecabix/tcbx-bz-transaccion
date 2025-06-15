package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionSolicitudRepository;

public class Transaccion003BzDTO {

    /**
     *
     * @author Ramirez Urrutia Angel Abinadi
     */
    private PersonaFisicaRepository personaFisicaRepository;

    /**
     * Repositorio para acceder a la entidad Cuenta.
     */
    private CuentaRepository cuentaRepository;

    /**
     * Repositorio para acceder a la entidad TransaccionSolicitud.
     */
    private TransaccionSolicitudRepository transaccionSolicitudRepository;

    /**
     * Catálogo que representa la monoTransaccion.
     */
    private Catalogo monoTrx;

    /**
     * Estado "activo" obtenido desde el catálogo.
     */
    private Catalogo activo;

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
     * @param repository el repositorio de solicitudes
     *        de transacción a establecer.
     */
    public void setTransaccionSolicitudRepository(
        final TransaccionSolicitudRepository repository) {

        this.transaccionSolicitudRepository = repository;
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
