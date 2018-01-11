package agroludos.business.validator.rules.mdc;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * implementa {@link MdcRulesFactory}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class MdcRulesFactoryImpl implements MdcRulesFactory {
	private final static MdcStipendioRule STIPENDIO_RULE = new MdcStipendioRule();

	@Override
	public AgroludosRule getStipendioRule() {
		return STIPENDIO_RULE;
	}
}