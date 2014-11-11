package agroludos.business.validator.partecipante.utente;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TOFactory;

class UtenteValidator implements AgroludosValidator{
	private UserRulesFactory userRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule cognomeRule;
	private AgroludosRule usernameRule;
	private AgroludosRule passwordRule;
	private AgroludosRule emailRule;

	UtenteValidator(UserRulesFactory userRulesFactory, TOFactory toFactory){
		this.userRulesFact = userRulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.userRulesFact.getNomeRule();
		this.emailRule = this.userRulesFact.getEmailRule();
		this.passwordRule = this.userRulesFact.getPasswordRule();
		this.cognomeRule = this.userRulesFact.getCognomeRule();
		this.usernameRule = this.userRulesFact.getUsernameRule();
		
		this.nomeRule.setSuccessor(this.cognomeRule);
		this.cognomeRule.setSuccessor(this.usernameRule);
		this.usernameRule.setSuccessor(this.passwordRule);
		this.passwordRule.setSuccessor(this.emailRule);
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