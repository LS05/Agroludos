package agroludos.business.validator.rules;

import agroludos.system.RulesErrorsConf;
import agroludos.system.SystemConf;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

/**
 * Fornisce un'interfaccia per tutte le regole. Ogni regola utilizza dei criteri
 * presenti all'interno del file di proprietà rules.properties e la classe permette
 * l'accesso al file per la lettura di valori per effettuare confronti o per ottenere
 * messaggi di errore.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosRule {

	/**
	 * @see RulesErrorsConf
	 */
	private static RulesErrorsConf rulesConf;

	/**
	 * @see SystemConf
	 */
	private static SystemConf sysConf;

	/**
	 * Regola successiva da analizzare
	 */
	protected AgroludosRule successor;

	/**
	 * imposta la regola successiva da applicare
	 * @param rule
	 */
	public void setSuccessor(AgroludosRule rule){
		this.successor = rule;
	}

	/**
	 * Ottiene un valore per una regola utile per effettuare confronti
	 * @param propName
	 * @return in nome della regola
	 */
	protected String getRule(String propName){
		return rulesConf.getRule(propName);
	}

	/**
	 * @return formato del certificato
	 */
	protected String getTipoSrc(){
		return sysConf.getTipoCert();
	}

	public void setRulesConf(RulesErrorsConf rulesConfig) {
		rulesConf = rulesConfig;
	}

	public void setSysConf(SystemConf systemConf) {
		sysConf = systemConf;
	}

	/**
	 * Gestisce la validazione del TO in input e ogni errore trovato è
	 * memorizzato all'interno dell'ErrorTO.
	 * 
	 * @param mainTO
	 * @param errorTO
	 */
	public abstract void validate(AgroludosTO mainTO, ErrorTO errorTO);
}