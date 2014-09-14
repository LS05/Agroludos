package agroludos.business.bd.cache;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.BusinessComponentNotFoundException;

public interface ServiceCache {
	AgroludosService getService(String serviceName) throws BusinessComponentNotFoundException;
}