package agroludos.business.validator.rules.optional;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface OptRulesFactory {

	AgroludosRule getNomeRule() throws IOException;

	AgroludosRule getCostoRule() throws IOException;

	AgroludosRule getStatoRule() throws IOException;

}