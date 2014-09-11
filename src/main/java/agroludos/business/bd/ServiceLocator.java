package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.business.bd.cache.ServiceCache;
import agroludos.exceptions.ServiceCacheException;

class ServiceLocator {
	private ServiceCache serviceCache;
	
	ServiceLocator(ServiceCache serviceCache){
		this.serviceCache = serviceCache;
	}
	
	AgroludosService lookup(String serviceName) throws ServiceCacheException{
		return this.serviceCache.getService(serviceName);
	}
}
