package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface UserRulesFactory {

	/**
	 * 
	 * @return Regola sul nome
	 */
	AgroludosRule getNomeRule();

	/**
	 * 
	 * @return regola sul cognome
	 */
	AgroludosRule getCognomeRule();

	/**
	 * 
	 * @return regola sull'username
	 */
	AgroludosRule getUsernameRule();

	/**
	 * 
	 * @return regola sulla password
	 */
	AgroludosRule getPasswordRule();

	/**
	 * 
	 * @return regola sull'email
	 */
	AgroludosRule getEmailRule();

}