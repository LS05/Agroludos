package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

/**
 * GEstisce gli errori sul cognome di un untente
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class UserCognomeRule extends AgroludosRule {

	/**
	 * controlla la lunghezza
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String cognome = user.getCognome();
			Integer cognLength = Integer.valueOf(this.getRule("cognomeLength"));

			String key = this.getRule("cognKey");
			if(cognome.length() < cognLength){
				errorTO.addError(key, this.getRule("cognLenghtError"));
			}

			if(this.successor != null){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

}