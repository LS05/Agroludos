package agroludos.business.validator.rules.competizione;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

class CompRulesFactoryImpl implements CompRulesFactory {
	private static CompNomeRule nomeRule;
	private static CompNumPartRule numPartRule;
	private static CompCostoRule costoRule;
	
	@Override
	public AgroludosRule getNomeRule() throws IOException {
		if(nomeRule == null)
			nomeRule = new CompNomeRule();
		return nomeRule;
	}
	
	@Override
	public AgroludosRule getNumPartRule() throws IOException {
		if(numPartRule == null)
			numPartRule = new CompNumPartRule();
		return numPartRule;
	}
	
	@Override
	public AgroludosRule getCostoRule() throws IOException {
		if(costoRule == null)
			costoRule = new CompCostoRule();
		return costoRule;
	}
}