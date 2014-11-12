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
	private CompRulesFactory compRulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;
	private AgroludosRule nPartRule;
	private AgroludosRule costoRule;
	private AgroludosRule dataRule;


	CompValidator(CompRulesFactory rulesFactory, TOFactory toFactory){
		this.compRulesFact = rulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.compRulesFact.getNomeRule();
		this.nPartRule = this.compRulesFact.getNumPartRule();
		this.costoRule = this.compRulesFact.getCostoRule();
		this.dataRule = this.compRulesFact.getDataRule();

		this.nomeRule.setSuccessor(this.nPartRule);
		this.nPartRule.setSuccessor(this.costoRule);
		this.costoRule.setSuccessor(this.dataRule);
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