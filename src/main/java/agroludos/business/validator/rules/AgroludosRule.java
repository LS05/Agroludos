package agroludos.business.validator.rules;

import agroludos.system.RulesErrorsConf;
import agroludos.system.SystemConf;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

public abstract class AgroludosRule {

	private static RulesErrorsConf rulesConf;

	private static SystemConf sysConf;

	protected AgroludosRule successor;

	public void setSuccessor(AgroludosRule rule){
		this.successor = rule;
	}

	protected String getRule(String propName){
		return rulesConf.getRule(propName);
	}

	protected String getTipoSrc(){
		return sysConf.getTipoCert();
	}

	public void setRulesConf(RulesErrorsConf rulesConfig) {
		rulesConf = rulesConfig;
	}

	public void setSysConf(SystemConf systemConf) {
		sysConf = systemConf;
	}

	public abstract void validate(AgroludosTO mainTO, ErrorTO errorTO);
}