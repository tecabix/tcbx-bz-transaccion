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

    private TransaccionSolicitudRepository transaccionSolicitudRepository;
    
    private CuentaRepository cuentaRepository;
	private TransaccionRepository transaccionRepository;
	private Catalogo transferencia;
	private Catalogo pendiente;
	private Catalogo monoTrx;
	private Catalogo eliminado;
	
	public TransaccionSolicitudRepository getTransaccionSolicitudRepository() {
		return transaccionSolicitudRepository;
	}
	public void setTransaccionSolicitudRepository(TransaccionSolicitudRepository transaccionSolicitudRepository) {
		this.transaccionSolicitudRepository = transaccionSolicitudRepository;
	}
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
	public Catalogo getMonoTrx() {
		return monoTrx;
	}
	public void setMonoTrx(Catalogo monoTrx) {
		this.monoTrx = monoTrx;
	}
	public Catalogo getEliminado() {
		return eliminado;
	}
	public void setEliminado(Catalogo eliminado) {
		this.eliminado = eliminado;
	}
}
