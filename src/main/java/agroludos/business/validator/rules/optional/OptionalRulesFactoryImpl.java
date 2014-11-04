package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;

class OptionalRulesFactoryImpl implements OptRulesFactory {
	private static OptionalNomeRule optNomeRule;
	private static OptionalCostoRule optCostoRule;
	private static OptionalStatoRule optStatoRule;
	
	@Override
	public AgroludosRule getNomeRule() {
		if(optNomeRule == null)
			optNomeRule = new OptionalNomeRule();
		return optNomeRule;
	}
	
	@Override
	public AgroludosRule getCostoRule() {
		if(optCostoRule == null)
			optCostoRule = new OptionalCostoRule();
		return optCostoRule;
	}
	
	@Override
	public AgroludosRule getStatoRule() {
		if(optStatoRule == null)
			optStatoRule = new OptionalStatoRule();
		return optStatoRule;
	}
}