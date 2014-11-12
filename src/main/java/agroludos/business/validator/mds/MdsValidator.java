package agroludos.business.validator.mds;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.mds.MdsRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.TOFactory;

class MdsValidator implements AgroludosValidator{

	private TOFactory toFact;
	private AgroludosRule nomeRule;

	MdsValidator(UserRulesFactory userRulesFactory, MdsRulesFactory mdsRulesFactory, TOFactory toFactory) {
		this.toFact = toFactory;

		this.nomeRule = userRulesFactory.getNomeRule();
		AgroludosRule emailRule = userRulesFactory.getEmailRule();
		AgroludosRule cognomeRule = userRulesFactory.getCognomeRule();
		AgroludosRule usernameRule = userRulesFactory.getUsernameRule();
		AgroludosRule passwordRule = userRulesFactory.getPasswordRule();
		AgroludosRule numeroStipendioRule = mdsRulesFactory.getNumeroTelefonoRule();

		this.nomeRule.setSuccessor(cognomeRule);
		cognomeRule.setSuccessor(emailRule);
		emailRule.setSuccessor(usernameRule);
		usernameRule.setSuccessor(passwordRule);
		passwordRule.setSuccessor(numeroStipendioRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof ManagerDiSistemaTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			ManagerDiSistemaTO mds = (ManagerDiSistemaTO)to;
			this.nomeRule.validate(mds, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}