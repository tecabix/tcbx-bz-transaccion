package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion006BzDTO {

    private CuentaRepository cuentaRepository;
	
	private TransaccionRepository transaccionRepository;
	
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
}
