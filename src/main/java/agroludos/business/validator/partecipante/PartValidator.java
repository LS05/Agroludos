package agroludos.business.validator.partecipante;

import java.io.IOException;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.partecipante.PRulesFactory;
import agroludos.business.validator.rules.partecipante.PartCFRule;
import agroludos.business.validator.rules.partecipante.PartEmailRule;
import agroludos.business.validator.rules.partecipante.PartNameRule;
import agroludos.business.validator.rules.partecipante.PartSrcRule;
import agroludos.business.validator.rules.partecipante.PartTesRule;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

public class PartValidator implements AgroludosValidator{
	private PRulesFactory rulesFact;
	private PartNameRule nameRule; 
	private PartEmailRule emailRule;
	private PartCFRule cfRule;
	private PartSrcRule srcRule;
	private PartTesRule tesRule;
	private TOFactory toFact;

	public PartValidator(PRulesFactory rulesFactory) throws IOException{
		this.rulesFact = rulesFactory;
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

	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}
}