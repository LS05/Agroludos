package agroludos.business.validator.mdc;

import java.io.IOException;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.mdc.MdcRulesFactory;
import agroludos.business.validator.rules.utente.UserRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TOFactory;

class MdcValidator implements AgroludosValidator{
	private MdcRulesFactory mdcRulesFact;
	private UserRulesFactory userRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule cognomeRule;
	private AgroludosRule usernameRule;
	private AgroludosRule passwordRule;
	private AgroludosRule emailRule;
	private AgroludosRule stipendioRule;

	MdcValidator(UserRulesFactory userRulesFactory, MdcRulesFactory mdcRulesFactory, TOFactory toFactory) throws IOException{
		this.mdcRulesFact = mdcRulesFactory;
		this.userRulesFact = userRulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.userRulesFact.getNomeRule();
		this.emailRule = this.userRulesFact.getEmailRule();
		this.cognomeRule = this.userRulesFact.getCognomeRule();
		this.usernameRule = this.userRulesFact.getUsernameRule();
		this.passwordRule = this.userRulesFact.getPasswordRule();
		this.stipendioRule = this.mdcRulesFact.getStipendioRule();

		this.nomeRule.setSuccessor(this.cognomeRule);
		this.cognomeRule.setSuccessor(this.emailRule);
		this.emailRule.setSuccessor(this.usernameRule);
		this.usernameRule.setSuccessor(this.passwordRule);
		this.passwordRule.setSuccessor(this.stipendioRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		ErrorTO errorTO = this.toFact.createErrorTO();
		if(to instanceof ManagerDiCompetizioneTO){
			ManagerDiCompetizioneTO mdc = (ManagerDiCompetizioneTO)to;
			this.nomeRule.validate(mdc, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}