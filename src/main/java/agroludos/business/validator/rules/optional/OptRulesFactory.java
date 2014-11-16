package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface OptRulesFactory {

	/**
	 * 
	 * @return regola sul nome
	 */
	AgroludosRule getNomeRule();

	/**
	 * 
	 * @return regola su costo
	 */
	AgroludosRule getCostoRule();

	/**
	 * 
	 * @return regola sullo stato
	 */
	AgroludosRule getStatoRule();

}