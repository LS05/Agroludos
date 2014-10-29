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
	private AgroludosRule nomeRule;
	private AgroludosRule cognomeRule;
	private AgroludosRule usernameRule;
	private AgroludosRule passwordRule;
	private AgroludosRule indirizzoRule;
	private AgroludosRule dataSrcRule;
	private AgroludosRule emailRule;
	private AgroludosRule cfRule;
	private AgroludosRule srcRule;
	private AgroludosRule tesRule;
	private AgroludosRule sessoRule;
	private AgroludosRule dataNascRule;
	
	PartValidator(PRulesFactory rulesFactory, TOFactory toFactory) throws IOException{
		this.rulesFact = rulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.rulesFact.getNameRule();
		this.emailRule = this.rulesFact.getEmailRule();
		this.cfRule = this.rulesFact.getCfRule();
		this.srcRule = this.rulesFact.getSrcRule();
		this.cognomeRule = this.rulesFact.getCognomeRule();
		this.usernameRule = this.rulesFact.getUsernameRule();
		this.indirizzoRule = this.rulesFact.getIndirizzoRule();
		this.dataSrcRule = this.rulesFact.getDataSrcRule();
		this.tesRule = this.rulesFact.getTesRule();
		this.passwordRule = this.rulesFact.getPasswordRule();
		this.sessoRule = this.rulesFact.getSessoRule();
		this.dataNascRule = this.rulesFact.getDataNascRule();

		this.nomeRule.setSuccessor(this.cognomeRule);
		this.cognomeRule.setSuccessor(this.cfRule);
		this.cfRule.setSuccessor(this.srcRule);
		this.srcRule.setSuccessor(this.tesRule);
		this.tesRule.setSuccessor(this.dataSrcRule);
		this.dataSrcRule.setSuccessor(this.indirizzoRule);
		this.indirizzoRule.setSuccessor(this.usernameRule);
		this.usernameRule.setSuccessor(this.passwordRule);
		this.passwordRule.setSuccessor(this.emailRule);
		this.emailRule.setSuccessor(this.sessoRule);
		this.sessoRule.setSuccessor(this.dataNascRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		ErrorTO errorTO = this.toFact.createErrorTO();
		PartecipanteTO partecipante = (PartecipanteTO)to;
		this.nomeRule.validate(partecipante, errorTO);
		if(errorTO.hasErrors())
			throw new ValidationException(errorTO);
	}
}