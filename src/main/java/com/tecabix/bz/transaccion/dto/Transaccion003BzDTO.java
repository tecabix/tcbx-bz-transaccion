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
	
	private CuentaRepository cuentaRepository;
	
	private TransaccionSolicitudRepository transaccionSolicitudRepository;
	
	private Catalogo monoTrx;
	
	private Catalogo activo;

	public PersonaFisicaRepository getPersonaFisicaRepository() {
		return personaFisicaRepository;
	}

	public void setPersonaFisicaRepository(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public TransaccionSolicitudRepository getTransaccionSolicitudRepository() {
		return transaccionSolicitudRepository;
	}

	public void setTransaccionSolicitudRepository(TransaccionSolicitudRepository transaccionSolicitudRepository) {
		this.transaccionSolicitudRepository = transaccionSolicitudRepository;
	}

	public Catalogo getMonoTrx() {
		return monoTrx;
	}

	public void setMonoTrx(Catalogo monoTrx) {
		this.monoTrx = monoTrx;
	}

	public Catalogo getActivo() {
		return activo;
	}

	public void setActivo(Catalogo activo) {
		this.activo = activo;
	}
}
