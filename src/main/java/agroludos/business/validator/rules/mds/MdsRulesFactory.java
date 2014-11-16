package agroludos.business.validator.rules.mds;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface MdsRulesFactory {

	/**
	 * 
	 * @return regola sul numero di telefono
	 */
	AgroludosRule getNumeroTelefonoRule();

}