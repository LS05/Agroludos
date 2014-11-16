package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.BusinessComponentNotFoundException;

/**
 * La classe implementa l'interfaccia ServiceCache è fornisce un'istanza di una componente di
 * business attraverso il metodo getService() sfruttando la classe di supporto Services che
 * rappresenta la vera e propria cache.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ServiceCacheImpl implements ServiceCache {
	
	/**
	 * Classe che rappresenta l'implementazione di una cache. Contiene tutte le componenti di
	 * business attualmente disponibili.
	 */
	private Services services;

	/**
	 * Il costruttore inizializza l'attributo di classe services.
	 * @param services
	 */
	ServiceCacheImpl(Services services){
		this.services = services;
	}

	/**
	 * Il metodo ritorna un'istanza di una classe che implementa l'interfaccia AgrolduosService
	 * utilizzando la classe di supporto Services basandosi sul nome del servizio richiesto 
	 * (serviceName) fornito come parametro
	 */
	@Override
	public AgroludosService getService(String serviceName) throws BusinessComponentNotFoundException{
		return this.services.getService(serviceName);
	}
}