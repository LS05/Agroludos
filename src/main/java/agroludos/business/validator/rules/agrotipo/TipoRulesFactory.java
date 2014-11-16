package agroludos.business.validator.rules.agrotipo;

import agroludos.business.validator.rules.AgroludosRule;

/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface TipoRulesFactory {

	/**
	 * 
	 * @return un istanza di {@link AgroludosRule} che gestisce gli errori sul nome
	 */
	AgroludosRule getNomeRule();

}