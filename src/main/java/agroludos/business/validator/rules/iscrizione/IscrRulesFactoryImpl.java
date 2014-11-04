package agroludos.business.validator.rules.iscrizione;

import agroludos.business.validator.rules.AgroludosRule;

class IscrRulesFactoryImpl implements IscrRulesFactory {
	private static IscrDataRule datIscrRule;
	private static IscrCostoRule costoIscrRule;
	
	@Override
	public AgroludosRule getDataRule() {
		if(datIscrRule == null)
			datIscrRule = new IscrDataRule();
		return datIscrRule;
	}
	
	@Override
	public AgroludosRule getCostoRule() {
		if(costoIscrRule == null)
			costoIscrRule = new IscrCostoRule();
		return costoIscrRule;
	}
}