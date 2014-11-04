package agroludos.business.validator.rules.mdc;

import agroludos.business.validator.rules.AgroludosRule;

class MdcRulesFactoryImpl implements MdcRulesFactory {
	private static MdcStipendioRule stipendioRule;
	
	@Override
	public AgroludosRule getStipendioRule() {
		if(stipendioRule == null)
			stipendioRule = new MdcStipendioRule();
		return stipendioRule;
	}
}