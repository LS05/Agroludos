package agroludos.business.bd.cache;

import java.util.HashMap;
import java.util.Map;

import agroludos.business.as.AgroludosService;
import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.business.as.gestoremds.LManagerDiSistema;
import agroludos.business.as.gestoremds.SManagerDiSistema;
import agroludos.business.as.gestoreutente.LUtente;
import agroludos.business.as.gestoreutente.SUtente;
import agroludos.exceptions.ServiceNotFoundException;

class Services {
	private LConfigurazione lconfigurazione;
	private SConfigurazione sconfigurazione;
	private LUtente lutente;
	private SUtente sutente;
	private LManagerDiSistema lmds;
	private SManagerDiSistema smds;
	

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
	
	public void setLutente(LUtente lutente) {
		this.lutente = lutente;
		this.services.put(LUtente.class.getName(),this.lutente);
	}

	public void setSutente(SUtente sutente) {
		this.sutente = sutente;
		this.services.put(SUtente.class.getName(),this.sutente);
	}

	public void setLmds(LManagerDiSistema lmds) {
		this.lmds = lmds;
		this.services.put(LManagerDiSistema.class.getName(),this.lmds);
	}

	public void setSmds(SManagerDiSistema smds) {
		this.smds = smds;
		this.services.put(SManagerDiSistema.class.getName(),this.smds);
	}
	
	public AgroludosService getService(String serviceName) throws ServiceNotFoundException{
		AgroludosService service = this.services.get(serviceName);

		if(service == null){
			throw new ServiceNotFoundException("Servizio non trovato!");
		}

		return service;
	}
}
