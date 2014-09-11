package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ServiceNotFoundException;

class ServiceCacheImpl implements ServiceCache {
	private Services services;

	ServiceCacheImpl(Services services){
		this.services = services;
	}

	@Override
	public AgroludosService getService(String serviceName) throws ServiceNotFoundException{
		return this.services.getService(serviceName);
	}
}