package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.BusinessComponentNotFoundException;

/**
 * Rappresenta una cache contenente tutte le istanze dei componenti di business attualmente
 * disponibili. Le istanze sono ottenute attraverso il metodo getService().
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * 
 */
public interface ServiceCache {
	
	/**
	 * Metodo chiamato per ottenere un'istanza di una componente di business in base a quelle
	 * attualmente disponibili. L'istanza è ottenuta in base al nome del servizio passato come
	 * parametro.
	 * 
	 * @param serviceName Nome del servizio richiesto
	 * @return Istanza di una classe che implementa AgroludosService
	 * @throws BusinessComponentNotFoundException Se il servizio richiesto non è disponibile in
	 * nessuna componenti di business attualmente disponibili.
	 */
	AgroludosService getService(String serviceName) throws BusinessComponentNotFoundException;
}