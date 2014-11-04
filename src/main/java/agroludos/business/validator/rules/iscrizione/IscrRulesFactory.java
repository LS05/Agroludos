package agroludos.business.validator.rules.iscrizione;

import agroludos.business.validator.rules.AgroludosRule;

public interface IscrRulesFactory {

	AgroludosRule getDataRule();

	AgroludosRule getCostoRule();

}