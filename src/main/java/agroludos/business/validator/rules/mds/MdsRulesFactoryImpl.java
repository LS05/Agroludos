package agroludos.business.validator.rules.mds;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * implementa {@link MdsRulesFactory}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class MdsRulesFactoryImpl implements MdsRulesFactory {
	private final static MdsNumeroTelefonoRule STIPENDIO_RULE = new MdsNumeroTelefonoRule();

	@Override
	public AgroludosRule getNumeroTelefonoRule() {
		return STIPENDIO_RULE;
	}
}