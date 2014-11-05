package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;

class CompRulesFactoryImpl implements CompRulesFactory {
	private final static CompNomeRule nomeCompRule = new CompNomeRule();
	private final static CompNumPartRule numPartCompRule = new CompNumPartRule();
	private final static CompCostoRule costoCompRule = new CompCostoRule();
	private final static CompDataRule dataCompRule = new CompDataRule();

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

	@Override
	public AgroludosRule getDataRule() { 
		return dataCompRule;
	}
}