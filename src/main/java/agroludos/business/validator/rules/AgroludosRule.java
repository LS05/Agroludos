package agroludos.business.validator.rules;

import agroludos.system.RulesConf;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

public abstract class AgroludosRule {
	
	private static RulesConf rulesConf;
	
	protected AgroludosRule successor;
	
	public void setSuccessor(AgroludosRule rule){
		this.successor = rule;
	}
	
	protected String getRule(String propName){
		return rulesConf.getRule(propName);
	}
	
	public abstract void validate(AgroludosTO mainTO, ErrorTO errorTO);

	public void setRulesConf(RulesConf rulesConfig) {
		rulesConf = rulesConfig;
	}
}