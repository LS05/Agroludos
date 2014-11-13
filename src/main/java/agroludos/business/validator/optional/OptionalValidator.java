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

	private TOFactory toFact;
	private AgroludosRule nomeRule;

	OptionalValidator(OptRulesFactory rulesFactory, TOFactory toFactory){
		this.toFact = toFactory;

		this.nomeRule = rulesFactory.getNomeRule();
		AgroludosRule costoRule = rulesFactory.getCostoRule();
		AgroludosRule statoRule = rulesFactory.getStatoRule();

		this.nomeRule.setSuccessor(costoRule);
		costoRule.setSuccessor(statoRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof OptionalTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			OptionalTO tipo = (OptionalTO)to;

			this.nomeRule.validate(tipo, errorTO);

			if(errorTO.hasErrors()){
				throw new ValidationException(errorTO);
			}
		}
	}
}