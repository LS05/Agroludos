package agroludos.business.validator.rules.mdc;

import agroludos.business.validator.rules.AgroludosRule;

class MdcRulesFactoryImpl implements MdcRulesFactory {
	private final static MdcStipendioRule STIPENDIO_RULE = new MdcStipendioRule();

	@Override
	public AgroludosRule getStipendioRule() {
		return STIPENDIO_RULE;
	}
}