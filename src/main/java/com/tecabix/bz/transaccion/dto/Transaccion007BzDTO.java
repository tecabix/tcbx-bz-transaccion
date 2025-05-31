package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion007BzDTO {

	private CuentaRepository cuentaRepository;
	
	private TransaccionRepository transaccionRepository;
	
	private PersonaFisicaRepository personaFisicaRepository;

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public TransaccionRepository getTransaccionRepository() {
		return transaccionRepository;
	}

	public void setTransaccionRepository(TransaccionRepository transaccionRepository) {
		this.transaccionRepository = transaccionRepository;
	}

	public PersonaFisicaRepository getPersonaFisicaRepository() {
		return personaFisicaRepository;
	}

	public void setPersonaFisicaRepository(PersonaFisicaRepository personaFisicaRepository) {
		this.personaFisicaRepository = personaFisicaRepository;
	}
}
