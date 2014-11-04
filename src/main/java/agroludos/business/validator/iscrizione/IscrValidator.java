package agroludos.business.validator.iscrizione;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.iscrizione.IscrRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.TOFactory;

class IscrValidator implements AgroludosValidator{
	private IscrRulesFactory iscrRulesFact;
	private TOFactory toFact;
	private AgroludosRule costoRule;
	private AgroludosRule dataRule;

	IscrValidator(IscrRulesFactory rulesFactory, TOFactory toFactory) {
		this.iscrRulesFact = rulesFactory;
		this.toFact = toFactory;

		this.dataRule = this.iscrRulesFact.getDataRule();
		this.costoRule = this.iscrRulesFact.getCostoRule();

		this.dataRule.setSuccessor(this.costoRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof IscrizioneTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			IscrizioneTO iscrizione = (IscrizioneTO)to;
			this.dataRule.validate(iscrizione, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}