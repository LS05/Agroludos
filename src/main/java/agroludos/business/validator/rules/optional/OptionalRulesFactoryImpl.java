package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;

class OptionalRulesFactoryImpl implements OptRulesFactory {
	private final static OptionalNomeRule NOME_RULE = new OptionalNomeRule();;
	private final static OptionalCostoRule COSTO_RULE = new OptionalCostoRule();;
	private final static OptionalStatoRule STATO_RULE = new OptionalStatoRule();;

	@Override
	public AgroludosRule getNomeRule() {
		return NOME_RULE;
	}

	@Override
	public AgroludosRule getCostoRule() {
		return COSTO_RULE;
	}

	@Override
	public AgroludosRule getStatoRule() {
		return STATO_RULE;
	}
}