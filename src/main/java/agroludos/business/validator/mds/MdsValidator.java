package agroludos.business.validator.mds;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.mds.MdsRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.ValidationException;

import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.TOFactory;

class MdsValidator implements AgroludosValidator{
	private MdsRulesFactory mdsRulesFact;
	private UserRulesFactory userRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule cognomeRule;
	private AgroludosRule usernameRule;
	private AgroludosRule passwordRule;
	private AgroludosRule emailRule;
	private AgroludosRule numeroStipendioRule;

	MdsValidator(UserRulesFactory userRulesFactory, MdsRulesFactory mdsRulesFactory, TOFactory toFactory) {
		this.mdsRulesFact = mdsRulesFactory;
		this.userRulesFact = userRulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.userRulesFact.getNomeRule();
		this.emailRule = this.userRulesFact.getEmailRule();
		this.cognomeRule = this.userRulesFact.getCognomeRule();
		this.usernameRule = this.userRulesFact.getUsernameRule();
		this.passwordRule = this.userRulesFact.getPasswordRule();
		this.numeroStipendioRule = this.mdsRulesFact.getNumeroTelefonoRule();

		this.nomeRule.setSuccessor(this.cognomeRule);
		this.cognomeRule.setSuccessor(this.emailRule);
		this.emailRule.setSuccessor(this.usernameRule);
		this.usernameRule.setSuccessor(this.passwordRule);
		this.passwordRule.setSuccessor(this.numeroStipendioRule);
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