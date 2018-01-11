package agroludos.business.validator.rules.mdc;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface MdcRulesFactory {

	/**
	 * 
	 * @return regola sullo stipendio
	 */
	AgroludosRule getStipendioRule();

}