package agroludos.business.bd.cache;

import java.util.HashMap;
import java.util.Map;

import agroludos.business.as.AgroludosService;
import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.exceptions.ServiceCacheException;

class Services {
	private LConfigurazione lconfigurazione;
	private SConfigurazione sconfigurazione;
	
	private Map<String, AgroludosService> services;
	
	Services(){
		this.services = new HashMap<String,  AgroludosService>();
	}
	
	public void setLconfigurazione(LConfigurazione conf) {
		this.lconfigurazione = conf;
		this.services.put(LConfigurazione.class.getName(), this.lconfigurazione);
	}
	
	public void setSconfigurazione(SConfigurazione conf) {
		this.sconfigurazione = conf;
		this.services.put(SConfigurazione.class.getName(), this.sconfigurazione);
	}
	
	public AgroludosService getService(String serviceName) throws ServiceCacheException{
		AgroludosService service = this.services.get(serviceName);

		if(service == null){
			throw new ServiceCacheException("Servizio non trovato!");
		}

		return service;
	}
}
