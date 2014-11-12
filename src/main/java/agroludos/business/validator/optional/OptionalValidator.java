package agroludos.business.validator.optional;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.optional.OptRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TOFactory;
import agroludos.to.OptionalTO;

class OptionalValidator implements AgroludosValidator{
	private OptRulesFactory rulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule costoRule;
	private AgroludosRule statoRule;

	OptionalValidator(OptRulesFactory rulesFactory, TOFactory toFactory){
		this.rulesFact = rulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.rulesFact.getNomeRule();
		this.costoRule = this.rulesFact.getCostoRule();
		this.statoRule = this.rulesFact.getStatoRule();

		this.nomeRule.setSuccessor(this.costoRule);
		this.costoRule.setSuccessor(this.statoRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof OptionalTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			OptionalTO tipo = (OptionalTO)to;
			this.nomeRule.validate(tipo, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}