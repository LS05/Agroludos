package agroludos.business.validator.rules.competizione;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

class CompRulesFactoryImpl implements CompRulesFactory {
	private static CompNomeRule nomeCompRule;
	private static CompNumPartRule numPartCompRule;
	private static CompCostoRule costoCompRule;
	
	@Override
	public AgroludosRule getNomeRule() throws IOException {
		if(nomeCompRule == null)
			nomeCompRule = new CompNomeRule();
		return nomeCompRule;
	}
	
	@Override
	public AgroludosRule getNumPartRule() throws IOException {
		if(numPartCompRule == null)
			numPartCompRule = new CompNumPartRule();
		return numPartCompRule;
	}
	
	@Override
	public AgroludosRule getCostoRule() throws IOException {
		if(costoCompRule == null)
			costoCompRule = new CompCostoRule();
		return costoCompRule;
	}
}