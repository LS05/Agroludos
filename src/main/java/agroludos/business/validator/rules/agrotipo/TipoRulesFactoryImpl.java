package agroludos.business.validator.rules.agrotipo;

import agroludos.business.validator.rules.AgroludosRule;

class TipoRulesFactoryImpl implements TipoRulesFactory {
	private static TipoNomeRule tipoNomeRule;

	@Override
	public AgroludosRule getNomeRule() {
		if(tipoNomeRule == null)
			tipoNomeRule = new TipoNomeRule();
		return tipoNomeRule;
	}
}