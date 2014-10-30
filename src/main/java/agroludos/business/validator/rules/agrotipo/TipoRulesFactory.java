package agroludos.business.validator.rules.agrotipo;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

public interface TipoRulesFactory {

	AgroludosRule getNomeRule() throws IOException;

}