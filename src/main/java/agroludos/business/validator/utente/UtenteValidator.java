package agroludos.business.validator.utente;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TOFactory;
import agroludos.to.UtenteTO;

class UtenteValidator implements AgroludosValidator{
	private TOFactory toFact;
	private AgroludosRule emailRule;

	UtenteValidator(UserRulesFactory userRulesFactory, TOFactory toFactory){
		this.toFact = toFactory;

		this.emailRule = userRulesFactory.getEmailRule();
		AgroludosRule passwordRule = userRulesFactory.getPasswordRule();

		this.emailRule.setSuccessor(passwordRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof UtenteTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			UtenteTO utente = (UtenteTO)to;
			this.emailRule.validate(utente, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}