package agroludos.business.as.gestorechiusura;

import agroludos.business.as.AgroludosService;

/**
 * L'interfaccia contiene il metodo per effettuare la chiusura del sistema software
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.business.as.AgroludosService
 */
public interface LChiusura extends AgroludosService {
	/**
	 * Il metodo chiude il sistema software Agroludos
	 */
	public void chiusura();
}
