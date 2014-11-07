package agroludos.business.validator.partecipante;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.partecipante.PRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

class PartValidator implements AgroludosValidator{
	private UserRulesFactory userRulesFact;
	private PRulesFactory partRulesFact;
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

	PartValidator(UserRulesFactory userRulesFactory, PRulesFactory partRulesFactory, TOFactory toFactory){
		this.userRulesFact = userRulesFactory;
		this.partRulesFact = partRulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.userRulesFact.getNomeRule();
		this.emailRule = this.userRulesFact.getEmailRule();
		this.passwordRule = this.userRulesFact.getPasswordRule();
		this.cognomeRule = this.userRulesFact.getCognomeRule();
		this.usernameRule = this.userRulesFact.getUsernameRule();
		this.cfRule = this.partRulesFact.getCfRule();
		this.srcRule = this.partRulesFact.getSrcRule();
		this.indirizzoRule = this.partRulesFact.getIndirizzoRule();
		this.dataSrcRule = this.partRulesFact.getDataSrcRule();
		this.tesRule = this.partRulesFact.getTesRule();

		this.sessoRule = this.partRulesFact.getSessoRule();
		this.dataNascRule = this.partRulesFact.getDataNascRule();

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
		if(to instanceof PartecipanteTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			PartecipanteTO partecipante = (PartecipanteTO)to;
			this.nomeRule.validate(partecipante, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}