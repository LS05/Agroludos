package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ServiceCacheException;

class ServiceCacheImpl implements ServiceCache {
	private Services services;

	ServiceCacheImpl(Services services){
		this.services = services;
	}

	@Override
	public AgroludosService getService(String serviceName) throws ServiceCacheException{
		AgroludosService service = this.services.getService(serviceName);

		if(service == null){
			throw new ServiceCacheException("Servizio non trovato!");
		}

		return service;
	}
}