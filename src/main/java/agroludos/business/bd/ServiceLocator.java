package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.business.bd.cache.ServiceCache;
import agroludos.exceptions.business.BusinessComponentNotFoundException;

/**
 * Classe di supporto che permette di ottenere la componente di business su cui eseguire il metodo.
 * La classe utilizza un'istanza di ServiceCache che contiene tutte le istanze delle classi rappresentati
 * le componenti di business.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 * @see agroludos.business.bd.cache.ServiceCache
 */
class ServiceLocator {
	
	/**
	 * Cache di tutte le istanze delle componenti di business
	 */
	private ServiceCache serviceCache;
	
	/**
	 * Il costruttore inizializza l'attributo di classe serviceCache
	 * @param serviceCache
	 */
	ServiceLocator(ServiceCache serviceCache){
		this.serviceCache = serviceCache;
	}
	
	/**
	 * Il metodo utilizza getService() della classe ServiceCache per ottenere un'istanza della
	 * componente di business su cui eseguire il servizio specificato dal parametro serviceName
	 * 
	 * @param serviceName Nome del servizio da eseguire. Rappresenta il nome del metodo da richiamare
	 * sulla classe istanza di AgroludosService.
	 * @return Un'istanza di una classe che rappresenta la componente di business su cui eseguire il
	 * servizio richiesto.
	 * @throws BusinessComponentNotFoundException Sollevata se il servizio richiesto non è presenta in
	 * nessuna componente di business attualmente presente nella ServiceCache
	 */
	AgroludosService lookup(String serviceName) throws BusinessComponentNotFoundException{
		return this.serviceCache.getService(serviceName);
	}
}