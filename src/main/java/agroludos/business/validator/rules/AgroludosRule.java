package agroludos.business.validator.rules;

import agroludos.system.ErrorsConf;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

public abstract class AgroludosRule {
	
	private static ErrorsConf rulesConf;
	
	protected AgroludosRule successor;
	
	public void setSuccessor(AgroludosRule rule){
		this.successor = rule;
	}
	
	protected String getRule(String propName){
		return rulesConf.getError(propName);
	}
	
	public void setRulesConf(ErrorsConf rulesConfig) {
		rulesConf = rulesConfig;
	}
	
	public abstract void validate(AgroludosTO mainTO, ErrorTO errorTO);
}