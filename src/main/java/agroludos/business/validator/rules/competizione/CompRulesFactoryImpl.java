package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;

class CompRulesFactoryImpl implements CompRulesFactory {
	private static CompNomeRule nomeCompRule;
	private static CompNumPartRule numPartCompRule;
	private static CompCostoRule costoCompRule;
	private static CompDataRule dataCompRule;

	@Override
	public AgroludosRule getNomeRule() {
		if(nomeCompRule == null)
			nomeCompRule = new CompNomeRule();
		return nomeCompRule;
	}

	@Override
	public AgroludosRule getNumPartRule() {
		if(numPartCompRule == null)
			numPartCompRule = new CompNumPartRule();
		return numPartCompRule;
	}

	@Override
	public AgroludosRule getCostoRule() {
		if(costoCompRule == null)
			costoCompRule = new CompCostoRule();
		return costoCompRule;
	}

	@Override
	public AgroludosRule getDataRule() {
		if(dataCompRule == null)
			dataCompRule = new CompDataRule();
		return dataCompRule;
	}
}