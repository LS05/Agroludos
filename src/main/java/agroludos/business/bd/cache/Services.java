package agroludos.business.bd.cache;

import java.util.HashMap;
import java.util.Map;

import agroludos.business.as.AgroludosService;
import agroludos.business.as.gestorechiusura.LChiusura;
import agroludos.business.as.gestorecompetizione.LCompetizione;
import agroludos.business.as.gestorecompetizione.SCompetizione;
import agroludos.business.as.gestoreconfigurazione.LConfigurazione;
import agroludos.business.as.gestoreiscrizione.LIscrizione;
import agroludos.business.as.gestoreiscrizione.SIscrizione;
import agroludos.business.as.gestoremail.LEmail;
import agroludos.business.as.gestoremdc.LManagerDiCompetizione;
import agroludos.business.as.gestoremdc.SManagerDiCompetizione;
import agroludos.business.as.gestoremds.LManagerDiSistema;
import agroludos.business.as.gestoremds.SManagerDiSistema;
import agroludos.business.as.gestoreoptional.LOptional;
import agroludos.business.as.gestoreoptional.SOptional;
import agroludos.business.as.gestorepartecipante.LPartecipante;
import agroludos.business.as.gestorepartecipante.SPartecipante;
import agroludos.business.as.gestorestatocompetizione.LStatoCompetizione;
import agroludos.business.as.gestorestatoiscrizione.LStatoIscrizione;
import agroludos.business.as.gestorestatooptional.LStatoOptional;
import agroludos.business.as.gestorestatoutente.LStatoUtente;
import agroludos.business.as.gestoretipocompetizione.LTipoCompetizione;
import agroludos.business.as.gestoretipocompetizione.STipoCompetizione;
import agroludos.business.as.gestoretipooptional.LTipoOptional;
import agroludos.business.as.gestoretipooptional.STipoOptional;
import agroludos.business.as.gestoretipoutente.LTipoUtente;
import agroludos.business.as.gestoreutente.LUtente;
import agroludos.business.as.gestoreutente.SUtente;
import agroludos.exceptions.business.BusinessComponentNotFoundException;

/**
 * Rappresenta la cache contenente tutte le componenti di business che fornisco i servizi di sistema.<br />
 * Le istanze delle componenti vengono prima settate richiamando il metodo appropriato. L'istanza viene nascosta 
 * dall'interfaccia parametro di ciascun setter.
 * In questo modo si favorisce la riusabilità del codice permettendo di fornire ai setter altre istanze
 * di classi che potrebbero essere state oggetto di modifica o refactoring.<br />
 * In ciascun setter viene poi utilizzato l'HashMap salvando come chiave il nome della classe
 * che implementa l'interfaccia (parametro del setter), e come valore l'istanza vera e propria.
 * Questa strategia è stata utilizzata perché nel file XML che specifica l'API di business del sistema,
 * è presente il nome del servizio con associato il nome della classe su cui eseguire tale servizio.
 * Perciò a partire dal nome del servizio viene ottenuto il nome della classe che lo deve eseguire, e grazie al nome
 * della classe è possibile ottenere un'istanza della componente di business presente nella cache.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Services {
	private LConfigurazione lconfigurazione;
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
	private LIscrizione lisc;
	private SIscrizione sisc;
	private LStatoIscrizione lstatoisc;
	private LStatoOptional lstatoopt;
	private LStatoUtente lstatoutente;
	private LTipoOptional ltipoopt;
	private STipoOptional stipoopt;
	private LTipoCompetizione ltipocmp;
	private STipoCompetizione stipocmp;
	private LTipoUtente ltipoUtente;
	private LEmail lemail;
	private LChiusura lchiusura;
	private LOptional lopt;
	private SOptional sopt;

	private Map<String, AgroludosService> services;

	Services(){
		this.services = new HashMap<String,  AgroludosService>();
	}

	public void setLchiusura(LChiusura lchiusura) {
		this.lchiusura = lchiusura;
		this.services.put(LChiusura.class.getName(), this.lchiusura);
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

	public void setLstatoisc(LStatoIscrizione lstatoisc) {
		this.lstatoisc = lstatoisc;
		this.services.put(LStatoIscrizione.class.getName(), this.lstatoisc);
	}

	public void setLstatoopt(LStatoOptional lstatoopt) {
		this.lstatoopt = lstatoopt;
		this.services.put(LStatoOptional.class.getName(), this.lstatoopt);
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

	public void setLemail(LEmail lemail) {
		this.lemail = lemail;
		this.services.put(LEmail.class.getName(), this.lemail);
	}

	public void setLtipoUtente(LTipoUtente ltipoUtente) {
		this.ltipoUtente = ltipoUtente;
		this.services.put(LTipoUtente.class.getName(), this.ltipoUtente);
	}

	/**
	 * Ritorna un'istanza di una classe che implementa l'interfaccia AgroludosService che rappresenta
	 * una componente del livello di business contenente il servizio da eseguire.
	 * La componente è ottenuta in base al parametro che rappresenta il nome della classe su cui eseguire il
	 * servizio richiesto.
	 * 
	 * @param nome Nome della classe
	 * @return Istanza di una classe che implementa l'interfaccia AgrolduosService.
	 * @throws BusinessComponentNotFoundException Sollevata se una componente di business non è trovata.
	 */
	public AgroludosService getService(String nome) throws BusinessComponentNotFoundException{
		AgroludosService service = this.services.get(nome);
		int sbSize = 100;

		if(service == null){
			StringBuilder sb = new StringBuilder(sbSize);
			sb.append("Gestore del servizio non trovato! Percorso errato oppure classe ASGestore non istanziata: ");
			sb.append(nome);
			throw new BusinessComponentNotFoundException(sb.toString());
		}

		return service;
	}

}