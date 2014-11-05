package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;

class OptionalRulesFactoryImpl implements OptRulesFactory {
	private final static OptionalNomeRule optNomeRule = new OptionalNomeRule();;
	private final static OptionalCostoRule optCostoRule = new OptionalCostoRule();;
	private final static OptionalStatoRule optStatoRule = new OptionalStatoRule();;

	@Override
	public AgroludosRule getNomeRule() {
		return optNomeRule;
	}

	@Override
	public AgroludosRule getCostoRule() {
		return optCostoRule;
	}

	@Override
	public AgroludosRule getStatoRule() {
		return optStatoRule;
	}
}