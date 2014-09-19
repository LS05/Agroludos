package agroludos.business.bd.cache;

import java.util.HashMap;
import java.util.Map;

import agroludos.business.as.AgroludosService;
import agroludos.business.as.gestorecompetizione.LCompetizione;
import agroludos.business.as.gestorecompetizione.SCompetizione;
import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.business.as.gestoremdc.LManagerDiCompetizione;
import agroludos.business.as.gestoremdc.SManagerDiCompetizione;
import agroludos.business.as.gestoremds.LManagerDiSistema;
import agroludos.business.as.gestoremds.SManagerDiSistema;
import agroludos.business.as.gestoreoptional.LOptional;
import agroludos.business.as.gestoreoptional.SOptional;
import agroludos.business.as.gestorepartecipante.LPartecipante;
import agroludos.business.as.gestorepartecipante.SPartecipante;
import agroludos.business.as.gestoreutente.LUtente;
import agroludos.business.as.gestoreutente.SUtente;
import agroludos.exceptions.BusinessComponentNotFoundException;

class Services {
	private LConfigurazione lconfigurazione;
	private SConfigurazione sconfigurazione;
	private LUtente lutente;
	private SUtente sutente;
	private LManagerDiSistema lmds;
	private SManagerDiSistema smds;
	private LManagerDiCompetizione lmdc;
	private SManagerDiCompetizione smdc;
	private LCompetizione lcmp;
	private SCompetizione scmp;
	private LPartecipante lpartecipante;
	private SPartecipante spartecipante;

	private Map<String, AgroludosService> services;
	private LOptional lopt;
	private SOptional sopt;
	
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
		this.services.put(LUtente.class.getName(), this.lutente);
	}

	public void setSutente(SUtente sutente) {
		this.sutente = sutente;
		this.services.put(SUtente.class.getName(), this.sutente);
	}

	public void setLmds(LManagerDiSistema lmds) {
		this.lmds = lmds;
		this.services.put(LManagerDiSistema.class.getName(), this.lmds);
	}

	public void setSmds(SManagerDiSistema smds) {
		this.smds = smds;
		this.services.put(SManagerDiSistema.class.getName(), this.smds);
	}

	public void setLmdc(LManagerDiCompetizione lmdc) {
		this.lmdc = lmdc;
		this.services.put(LManagerDiCompetizione.class.getName(), this.lmdc);
	}

	public void setSmdc(SManagerDiCompetizione smdc) {
		this.smdc = smdc;
		this.services.put(SManagerDiCompetizione.class.getName(), this.smdc);
	}
	
	public void setLcmp(LCompetizione lcmp) {
		this.lcmp = lcmp;
		this.services.put(LCompetizione.class.getName(), this.lcmp);
	}

	public void setScmp(SCompetizione scmp) {
		this.scmp = scmp;
		this.services.put(SCompetizione.class.getName(), this.scmp);
	}
	
	public void setLopt(LOptional lopt) {
		this.lopt = lopt;	
		this.services.put(LOptional.class.getName(), this.lopt);
	}

	public void setSopt(SOptional sopt) {
		this.sopt = sopt;
		this.services.put(SOptional.class.getName(), this.sopt);
	}

	public void setLpartecipante(LPartecipante lpartecipante) {
		this.lpartecipante = lpartecipante;
		this.services.put(LPartecipante.class.getName(), this.lpartecipante);
	}

	public void setSpartecipante(SPartecipante spartecipante) {
		this.spartecipante = spartecipante;
		this.services.put(SPartecipante.class.getName(), this.spartecipante);
	}

	public AgroludosService getService(String serviceName) throws BusinessComponentNotFoundException{
		AgroludosService service = this.services.get(serviceName);

		if(service == null){
			throw new BusinessComponentNotFoundException("Gestore del servizio non trovato! Percorso errato oppure classe ASGestore non istanziata: " + serviceName);
		}

		return service;
	}
}