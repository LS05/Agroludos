package agroludos.business.validator.competizione;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.competizione.CompRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.TOFactory;

class CompValidator implements AgroludosValidator{

	private TOFactory toFact;
	private AgroludosRule nomeRule;

	CompValidator(CompRulesFactory rulesFactory, TOFactory toFactory){
		this.toFact = toFactory;

		this.nomeRule = rulesFactory.getNomeRule();
		AgroludosRule nPartRule = rulesFactory.getNumPartRule();
		AgroludosRule costoRule = rulesFactory.getCostoRule();
		AgroludosRule dataRule = rulesFactory.getDataRule();

		this.nomeRule.setSuccessor(nPartRule);
		nPartRule.setSuccessor(costoRule);
		costoRule.setSuccessor(dataRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof CompetizioneTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			CompetizioneTO competizione = (CompetizioneTO)to;
			this.nomeRule.validate(competizione, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}