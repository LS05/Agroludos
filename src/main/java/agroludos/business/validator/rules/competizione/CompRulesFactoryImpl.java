package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;

class CompRulesFactoryImpl implements CompRulesFactory {
	private final static CompNomeRule nomeCompRule = new CompNomeRule();
	private final static CompNumPartRule numPartCompRule = new CompNumPartRule();;
	private final static CompCostoRule costoCompRule = new CompCostoRule();

	@Override
	public AgroludosRule getNomeRule() {
		return nomeCompRule;
	}

	@Override
	public AgroludosRule getNumPartRule() {
		return numPartCompRule;
	}

	@Override
	public AgroludosRule getCostoRule() {
		return costoCompRule;
	}
}