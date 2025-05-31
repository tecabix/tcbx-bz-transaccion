package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.PersonaFisicaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion002BzDTO {

	private TransaccionRepository transaccionRepository;
	
	private PersonaFisicaRepository personaFisicaRepository;
	
	private Catalogo activo;

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

	public Catalogo getActivo() {
		return activo;
	}

	public void setActivo(Catalogo activo) {
		this.activo = activo;
	}
}
