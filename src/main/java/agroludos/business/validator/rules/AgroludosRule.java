package agroludos.business.validator.rules;

import agroludos.system.RulesErrorsConf;
import agroludos.system.SystemConf;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

/**
 * Fornisce un'interfaccia uguale a tutte le regole implementando i metodi 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosRule {

	private static RulesErrorsConf rulesConf;

	private static SystemConf sysConf;

	protected AgroludosRule successor;

	/**
	 * imposta la regola successiva da applicare
	 * @param rule
	 */
	public void setSuccessor(AgroludosRule rule){
		this.successor = rule;
	}

	/**
	 * 
	 * @param propName
	 * @return in nome della regola
	 */
	protected String getRule(String propName){
		return rulesConf.getRule(propName);
	}

	/**
	 * 
	 * @return format del certificato
	 */
	protected String getTipoSrc(){
		return sysConf.getTipoCert();
	}

	/**
	 * 
	 * @param rulesConfig
	 */
	public void setRulesConf(RulesErrorsConf rulesConfig) {
		rulesConf = rulesConfig;
	}

	/**
	 * 
	 * @param systemConf
	 */
	public void setSysConf(SystemConf systemConf) {
		sysConf = systemConf;
	}

	/**
	 * gestisce la validazione dell' TO in input
	 * @param mainTO
	 * @param errorTO
	 */
	public abstract void validate(AgroludosTO mainTO, ErrorTO errorTO);
}