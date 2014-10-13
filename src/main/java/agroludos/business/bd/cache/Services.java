package agroludos.business.bd.cache;

import java.util.HashMap;
import java.util.Map;

import agroludos.business.as.AgroludosService;
import agroludos.business.as.gestorecompetizione.LCompetizione;
import agroludos.business.as.gestorecompetizione.SCompetizione;
import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreconfigurazione.SConfigurazione;
import agroludos.business.as.gestoreiscrizione.LIscrizione;
import agroludos.business.as.gestoreiscrizione.SIscrizione;
import agroludos.business.as.gestoremdc.LManagerDiCompetizione;
import agroludos.business.as.gestoremdc.SManagerDiCompetizione;
import agroludos.business.as.gestoremds.LManagerDiSistema;
import agroludos.business.as.gestoremds.SManagerDiSistema;
import agroludos.business.as.gestoreoptional.LOptional;
import agroludos.business.as.gestoreoptional.SOptional;
import agroludos.business.as.gestorepartecipante.LPartecipante;
import agroludos.business.as.gestorepartecipante.SPartecipante;
import agroludos.business.as.gestorestatocompetizione.LStatoCompetizione;
import agroludos.business.as.gestorestatocompetizione.SStatoCompetizione;
import agroludos.business.as.gestorestatoiscrizione.LStatoIscrizione;
import agroludos.business.as.gestorestatoiscrizione.SStatoIscrizione;
import agroludos.business.as.gestorestatooptional.LStatoOptional;
import agroludos.business.as.gestorestatooptional.SStatoOptional;
import agroludos.business.as.gestorestatoutente.LStatoUtente;
import agroludos.business.as.gestorestatoutente.SStatoUtente;
import agroludos.business.as.gestoretipocompetizione.LTipoCompetizione;
import agroludos.business.as.gestoretipocompetizione.STipoCompetizione;
import agroludos.business.as.gestoretipooptional.LTipoOptional;
import agroludos.business.as.gestoretipooptional.STipoOptional;
import agroludos.business.as.gestoretipoutente.LTipoUtente;
import agroludos.business.as.gestoreutente.LUtente;
import agroludos.business.as.gestoreutente.SUtente;
import agroludos.exceptions.BusinessComponentNotFoundException;

class Services {
	private final int SBSIZE = 100;
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
	private LStatoCompetizione lstatocmp;
	private SStatoCompetizione sstatocmp;
	private LIscrizione lisc;
	private SIscrizione sisc;
	private LStatoIscrizione lstatoisc;
	private SStatoIscrizione sstatoisc;
	private LStatoOptional lstatoopt;
	private SStatoOptional sstatoopt;
	private LStatoUtente lstatoutente;
	private SStatoUtente sstatoutente;
	private LTipoOptional ltipoopt;
	private STipoOptional stipoopt;
	private LTipoCompetizione ltipocmp;
	private STipoCompetizione stipocmp;
	private LTipoUtente ltipoUtente;
	
	private Map<String, AgroludosService> services;
	private LOptional lopt;
	private SOptional sopt;
	
	Services(){
		this.services = new HashMap<String,  AgroludosService>();
	}

	public void setLtipoopt(LTipoOptional ltipoopt) {
		this.ltipoopt = ltipoopt;
		this.services.put(LTipoOptional.class.getName(), this.ltipoopt);
	}

	public void setStipoopt(STipoOptional stipoopt) {
		this.stipoopt = stipoopt;
		this.services.put(STipoOptional.class.getName(), this.stipoopt);
	}

	public void setLtipocmp(LTipoCompetizione ltipocmp) {
		this.ltipocmp = ltipocmp;
		this.services.put(LTipoCompetizione.class.getName(), this.ltipocmp);
	}

	public void setStipocmp(STipoCompetizione stipocmp) {
		this.stipocmp = stipocmp;
		this.services.put(STipoCompetizione.class.getName(), this.stipocmp);
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
	
	public void setLstatoutente(LStatoUtente lstatoutente) {
		this.lstatoutente = lstatoutente;
		this.services.put(LStatoUtente.class.getName(), this.lstatoutente);
	}

	public void setSstatoutente(SStatoUtente sstatoutente) {
		this.sstatoutente = sstatoutente;
		this.services.put(SStatoUtente.class.getName(), this.sstatoutente);
	}

	public void setLstatoisc(LStatoIscrizione lstatoisc) {
		this.lstatoisc = lstatoisc;
		this.services.put(LStatoIscrizione.class.getName(), this.lstatoisc);
	}

	public void setSstatoisc(SStatoIscrizione sstatoisc) {
		this.sstatoisc = sstatoisc;
		this.services.put(SStatoIscrizione.class.getName(), this.sstatoisc);
	}
	
	public void setLstatoopt(LStatoOptional lstatoopt) {
		this.lstatoopt = lstatoopt;
		this.services.put(LStatoOptional.class.getName(), this.lstatoopt);
	}

	public void setSstatoopt(SStatoOptional sstatoopt) {
		this.sstatoopt = sstatoopt;
		this.services.put(SStatoOptional.class.getName(), this.sstatoopt);
	}
	
	public void setLisc(LIscrizione lisc) {
		this.lisc = lisc;
		this.services.put(LIscrizione.class.getName(), this.lisc);
	}

	public void setSisc(SIscrizione sisc) {
		this.sisc = sisc;
		this.services.put(SIscrizione.class.getName(), this.sisc);
	}
	
	public void setLstatocmp(LStatoCompetizione lstatocmp) {
		this.lstatocmp = lstatocmp;
		this.services.put(LStatoCompetizione.class.getName(), this.lstatocmp);
	}

	public void setSstatocmp(SStatoCompetizione sstatocmp) {
		this.sstatocmp = sstatocmp;
		this.services.put(SStatoCompetizione.class.getName(), this.sstatocmp);
	}
		
	public void setLtipoUtente(LTipoUtente ltipoUtente) {
		this.ltipoUtente = ltipoUtente;
		this.services.put(LTipoUtente.class.getName(), this.ltipoUtente);
	}

	public AgroludosService getService(String serviceName) throws BusinessComponentNotFoundException{
		AgroludosService service = this.services.get(serviceName);
		
		if(service == null){
			StringBuilder sb = new StringBuilder(this.SBSIZE);
			sb.append("Gestore del servizio non trovato! Percorso errato oppure classe ASGestore non istanziata: ");
			sb.append(serviceName);
			throw new BusinessComponentNotFoundException(sb.toString());
		}

		return service;
	}
}