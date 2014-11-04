package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;

public interface OptRulesFactory {

	AgroludosRule getNomeRule();

	AgroludosRule getCostoRule();

	AgroludosRule getStatoRule();

}