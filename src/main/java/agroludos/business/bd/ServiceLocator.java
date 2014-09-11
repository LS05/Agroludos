package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.business.bd.cache.ServiceCache;
import agroludos.exceptions.ServiceNotFoundException;

class ServiceLocator {
	private ServiceCache serviceCache;
	
	ServiceLocator(ServiceCache serviceCache){
		this.serviceCache = serviceCache;
	}
	
	AgroludosService lookup(String serviceName) throws ServiceNotFoundException{
		return this.serviceCache.getService(serviceName);
	}
}
