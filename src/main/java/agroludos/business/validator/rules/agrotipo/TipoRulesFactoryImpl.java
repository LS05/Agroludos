package agroludos.business.validator.rules.agrotipo;

import agroludos.business.validator.rules.AgroludosRule;

/**
 * Implementazione di {@link TipoRulesFactory}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TipoRulesFactoryImpl implements TipoRulesFactory {
	private final static TipoNomeRule NOME_RULE = new TipoNomeRule();

	@Override
	public AgroludosRule getNomeRule() {
		return NOME_RULE;
	}
}