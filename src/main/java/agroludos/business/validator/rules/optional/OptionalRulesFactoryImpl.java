package agroludos.business.validator.rules.optional;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

class OptionalRulesFactoryImpl implements OptRulesFactory {
	private static OptionalNomeRule optNomeRule;
	private static OptionalCostoRule optCostoRule;
	private static OptionalStatoRule optStatoRule;
	
	@Override
	public AgroludosRule getNomeRule() throws IOException {
		if(optNomeRule == null)
			optNomeRule = new OptionalNomeRule();
		return optNomeRule;
	}
	
	@Override
	public AgroludosRule getCostoRule() throws IOException {
		if(optCostoRule == null)
			optCostoRule = new OptionalCostoRule();
		return optCostoRule;
	}
	
	@Override
	public AgroludosRule getStatoRule() throws IOException {
		if(optStatoRule == null)
			optStatoRule = new OptionalStatoRule();
		return optStatoRule;
	}
}