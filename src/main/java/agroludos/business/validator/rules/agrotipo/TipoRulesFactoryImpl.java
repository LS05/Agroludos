package agroludos.business.validator.rules.agrotipo;

import agroludos.business.validator.rules.AgroludosRule;

class TipoRulesFactoryImpl implements TipoRulesFactory {
	private final static TipoNomeRule tipoNomeRule = new TipoNomeRule();

	@Override
	public AgroludosRule getNomeRule() {
		return tipoNomeRule;
	}
}