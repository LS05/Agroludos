package agroludos.business.validator.rules.competizione;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface CompRulesFactory {

	AgroludosRule getNomeRule() throws IOException;

	AgroludosRule getNumPartRule() throws IOException;

	AgroludosRule getCostoRule() throws IOException;

}