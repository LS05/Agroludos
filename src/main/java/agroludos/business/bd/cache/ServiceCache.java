package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ServiceCacheException;

public interface ServiceCache {
	AgroludosService getService(String serviceName) throws ServiceCacheException;
}