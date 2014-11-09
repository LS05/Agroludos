package agroludos.business.validator.rules.mds;

import agroludos.business.validator.rules.AgroludosRule;

class MdsRulesFactoryImpl implements MdsRulesFactory {
	private final static MdsNumeroTelefonoRule stipendioRule = new MdsNumeroTelefonoRule();

	@Override
	public AgroludosRule getNumeroTelefonoRule() {
		return stipendioRule;
	}
}