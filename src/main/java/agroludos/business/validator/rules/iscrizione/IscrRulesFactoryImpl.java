package agroludos.business.validator.rules.iscrizione;

import agroludos.business.validator.rules.AgroludosRule;

class IscrRulesFactoryImpl implements IscrRulesFactory {
	private final static IscrDataRule datIscrRule  = new IscrDataRule();;
	private final static IscrCostoRule costoIscrRule = new IscrCostoRule();

	@Override
	public AgroludosRule getDataRule() {
		return datIscrRule;
	}

	@Override
	public AgroludosRule getCostoRule() {
		return costoIscrRule;
	}
}