package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
/**
 * Implementa il pattern Factory per restituire {@link AgroludosRule}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface PRulesFactory {

	/**
	 * 
	 * @return regola sul CF
	 */
	AgroludosRule getCfRule();

	/**
	 * 
	 * @return regola sulla data dell'csrc
	 */
	AgroludosRule getDataSrcRule();

	/**
	 * 
	 * @return regola sull'indirizzo
	 */
	AgroludosRule getIndirizzoRule();

	/**
	 * 
	 * @return regola sul sesso 
	 */
	AgroludosRule getSessoRule();

	/**
	 * 
	 * @return regola sul Certificato
	 */
	AgroludosRule getSrcRule();

	/**
	 * 
	 * @return regola sulla tessera sanitaria
	 */
	AgroludosRule getTesRule();

	/**
	 * 
	 * @return regola sulla data di nascita
	 */
	AgroludosRule getDataNascRule();

}