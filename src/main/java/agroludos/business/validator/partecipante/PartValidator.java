package agroludos.business.validator.partecipante;

import java.io.IOException;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

class PartValidator implements AgroludosValidator{
	private PRulesFactory rulesFact;
	private TOFactory toFact;
	private AgroludosRule nameRule; 
	private AgroludosRule emailRule;
	private AgroludosRule cfRule;
	private AgroludosRule srcRule;
	private AgroludosRule tesRule;
	
	PartValidator(PRulesFactory rulesFactory, TOFactory toFactory) throws IOException{
		this.rulesFact = rulesFactory;
		this.toFact = toFactory;
		this.nameRule = this.rulesFact.getNameRule();
		this.emailRule = this.rulesFact.getEmailRule();
		this.cfRule = this.rulesFact.getCfRule();
		this.srcRule = this.rulesFact.getSrcRule();

		this.nameRule.setSuccessor(this.emailRule);
		this.emailRule.setSuccessor(this.cfRule);
		this.cfRule.setSuccessor(this.srcRule);
		this.srcRule.setSuccessor(this.tesRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		ErrorTO errorTO = this.toFact.createErrorTO();
		PartecipanteTO partecipante = (PartecipanteTO)to;
		this.nameRule.validate(partecipante, errorTO);
		if(errorTO.hasErrors())
			throw new ValidationException(errorTO);
	}
}