package agroludos.business.validator.rules.iscrizione;

import agroludos.business.validator.rules.AgroludosRule;

class IscrRulesFactoryImpl implements IscrRulesFactory {
	private final static IscrDataRule DATA_RULE  = new IscrDataRule();;
	private final static IscrCostoRule COSTO_RULE = new IscrCostoRule();

	@Override
	public AgroludosRule getDataRule() {
		return DATA_RULE;
	}

	@Override
	public AgroludosRule getCostoRule() {
		return COSTO_RULE;
	}
}