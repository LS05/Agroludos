package agroludos.business.validator.tipooptional;

import java.io.IOException;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.agrotipo.TipoRulesFactory;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TOFactory;
import agroludos.to.TipiAgroludosTO;

class TipoValidator implements AgroludosValidator{
	private TipoRulesFactory rulesFact;
	private TOFactory toFact;
	private AgroludosRule nomeRule;

	TipoValidator(TipoRulesFactory rulesFactory, TOFactory toFactory) throws IOException{
		this.rulesFact = rulesFactory;
		this.toFact = toFactory;

		this.nomeRule = this.rulesFact.getNomeRule();
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