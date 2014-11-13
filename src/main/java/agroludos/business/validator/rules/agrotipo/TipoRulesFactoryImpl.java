package agroludos.business.validator.rules.agrotipo;

import agroludos.business.validator.rules.AgroludosRule;

class TipoRulesFactoryImpl implements TipoRulesFactory {
	private final static TipoNomeRule NOME_RULE = new TipoNomeRule();

	@Override
	public AgroludosRule getNomeRule() {
		return NOME_RULE;
	}
}