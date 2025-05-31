package com.tecabix.bz.transaccion.dto;

import com.tecabix.db.entity.Catalogo;
import com.tecabix.db.repository.CuentaRepository;
import com.tecabix.db.repository.TransaccionRepository;

/**
*
* @author Ramirez Urrutia Angel Abinadi
*/
public class Transaccion001BzDTO {

	private CuentaRepository cuentaRepository;
	private TransaccionRepository transaccionRepository;
	private Catalogo transferencia;
	private Catalogo pendiente;
	
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
	public Catalogo getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(Catalogo transferencia) {
		this.transferencia = transferencia;
	}
	public Catalogo getPendiente() {
		return pendiente;
	}
	public void setPendiente(Catalogo pendiente) {
		this.pendiente = pendiente;
	}
}
