package agroludos.business.validator.rules.mdc;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;

class MdcRulesFactoryImpl implements MdcRulesFactory {
	private static MdcStipendioRule stipendioRule;
	
	@Override
	public AgroludosRule getStipendioRule() throws IOException {
		if(stipendioRule == null)
			stipendioRule = new MdcStipendioRule();
		return stipendioRule;
	}
}