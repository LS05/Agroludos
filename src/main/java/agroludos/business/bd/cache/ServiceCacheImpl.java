package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.BusinessComponentNotFoundException;

class ServiceCacheImpl implements ServiceCache {
	private Services services;

	ServiceCacheImpl(Services services){
		this.services = services;
	}

	@Override
	public AgroludosService getService(String serviceName) throws BusinessComponentNotFoundException{
		return this.services.getService(serviceName);
	}
}