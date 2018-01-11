package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

/**
 * gestisce gli errori sulla email degli utenti
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class UserEmailRule extends AgroludosRule {
	private StringValidator strValidator;

	/**
	 * inizializza il fied strValidator e chiama il costruttore della superclasse
	 * @param strValidator
	 */
	UserEmailRule(StringValidator strValidator) {
		super();
		this.strValidator = strValidator;
	}

	/**
	 * controlla se l'email è valida
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String email = user.getEmail();
			String key = this.getRule("emailKey");

			if( !(email.length() > 1) || !this.strValidator.isValidEmail(email)){
				errorTO.addError(key , this.getRule("emailError"));
			}

			if(this.successor != null){
				this.successor.validate(user, errorTO);
			}
		}
	}

}