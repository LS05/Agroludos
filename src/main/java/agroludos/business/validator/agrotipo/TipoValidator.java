package agroludos.business.validator.agrotipo;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.agrotipo.TipoRulesFactory;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TOFactory;
import agroludos.to.TipiAgroludosTO;

class TipoValidator implements AgroludosValidator{

	private TOFactory toFact;
	private AgroludosRule nomeRule;

	TipoValidator(TipoRulesFactory rulesFactory, TOFactory toFactory){
		this.toFact = toFactory;
		this.nomeRule = rulesFactory.getNomeRule();
	}

	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		if(to instanceof TipiAgroludosTO){
			ErrorTO errorTO = this.toFact.createErrorTO();
			TipiAgroludosTO tipo = (TipiAgroludosTO)to;
			this.nomeRule.validate(tipo, errorTO);
			if(errorTO.hasErrors())
				throw new ValidationException(errorTO);
		}
	}
}