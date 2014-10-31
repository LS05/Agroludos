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
	private CompRulesFactory userRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule nPartRule;
	private AgroludosRule costoRule;
	
	CompValidator(CompRulesFactory rulesFactory, TOFactory toFactory) throws IOException{
		this.userRulesFact = rulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.userRulesFact.getNomeRule();
		this.nPartRule = this.userRulesFact.getNumPartRule();
		this.costoRule = this.userRulesFact.getCostoRule();

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