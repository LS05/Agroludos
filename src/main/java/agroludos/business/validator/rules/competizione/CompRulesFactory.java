package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface CompRulesFactory {

	/**
	 * 
	 * @return regola sul nome
	 */
	AgroludosRule getNomeRule();

	/**
	 * 
	 * @return regola sul numero di partecipanti
	 */
	AgroludosRule getNumPartRule();

	/**
	 * 
	 * @return regola sul costo
	 */
	AgroludosRule getCostoRule();

	/**
	 * 
	 * @return regola sulla data
	 */
	AgroludosRule getDataRule();

}