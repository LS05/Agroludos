package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;

public interface CompRulesFactory {

	AgroludosRule getNomeRule();

	AgroludosRule getNumPartRule();

	AgroludosRule getCostoRule();

}