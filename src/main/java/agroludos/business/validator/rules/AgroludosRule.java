package agroludos.business.validator.rules;

import java.io.IOException;

import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

public abstract class AgroludosRule {
	private static RuleProperties rulesProperties;
	protected AgroludosRule successor;

	protected AgroludosRule() throws IOException{
		if(rulesProperties == null)
			rulesProperties = new RuleProperties();
	}
	
	public void setSuccessor(AgroludosRule rule){
		this.successor = rule;
	}
	
	protected String getProperty(String propName){
		return rulesProperties.getProperty(propName);
	}
	
	public abstract void validate(AgroludosTO mainTO, ErrorTO errorTO);
}