package agroludos.business.validator.mdc;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.mdc.MdcRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TOFactory;

class MdcValidator implements AgroludosValidator{

	private TOFactory toFact;
	private AgroludosRule nomeRule;
	
	MdcValidator(UserRulesFactory userRulesFactory, MdcRulesFactory mdcRulesFactory, TOFactory toFactory) {
		this.toFact = toFactory;

		this.nomeRule = userRulesFactory.getNomeRule();
		AgroludosRule emailRule = userRulesFactory.getEmailRule();
		AgroludosRule cognomeRule = userRulesFactory.getCognomeRule();
		AgroludosRule usernameRule = userRulesFactory.getUsernameRule();
		AgroludosRule passwordRule = userRulesFactory.getPasswordRule();
		AgroludosRule stipendioRule = mdcRulesFactory.getStipendioRule();

		this.nomeRule.setSuccessor(cognomeRule);
		cognomeRule.setSuccessor(emailRule);
		emailRule.setSuccessor(usernameRule);
		usernameRule.setSuccessor(passwordRule);
		passwordRule.setSuccessor(stipendioRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof ManagerDiCompetizioneTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			ManagerDiCompetizioneTO mdc = (ManagerDiCompetizioneTO)to;
			this.nomeRule.validate(mdc, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}