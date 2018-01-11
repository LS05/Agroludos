package agroludos.business.validator.rules.iscrizione;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface IscrRulesFactory {

	/**
	 * 
	 * @return regola sulla data
	 */
	AgroludosRule getDataRule();

	/**
	 * 
	 * @return regola sul costo
	 */
	AgroludosRule getCostoRule();

}