package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

/**
 * gestisce gli errori sulla password 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class UserPasswordRule extends AgroludosRule {

	/**
	 * controlla la lunghezza
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String password = user.getPassword();
			Integer pwdLength = Integer.valueOf(this.getRule("passwordLength"));

			String key = this.getRule("passwordKey");
			if( password.length() < pwdLength ){
				errorTO.addError(key, this.getRule("passwordLenError"));
			}

			if(this.successor != null){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

}