package agroludos.business.validator.competizione;

import java.io.IOException;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.competizione.CompRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.TOFactory;

class CompValidator implements AgroludosValidator{
	private CompRulesFactory compRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule nPartRule;
	private AgroludosRule costoRule;
	
	CompValidator(CompRulesFactory rulesFactory, TOFactory toFactory) throws IOException{
		this.compRulesFact = rulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.compRulesFact.getNomeRule();
		this.nPartRule = this.compRulesFact.getNumPartRule();
		this.costoRule = this.compRulesFact.getCostoRule();

		this.nomeRule.setSuccessor(this.nPartRule);
		this.nPartRule.setSuccessor(this.costoRule);
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		ErrorTO errorTO = this.toFact.createErrorTO();
		CompetizioneTO competizione = (CompetizioneTO)to;
		this.nomeRule.validate(competizione, errorTO);
		if(errorTO.hasErrors())
			throw new ValidationException(errorTO);
	}
}