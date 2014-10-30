package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserPasswordRule extends AgroludosRule {

	protected UserPasswordRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String password = user.getPassword();
			Integer pwdLength = Integer.valueOf(this.getProperty("passwordLength"));

			String key = this.getProperty("passwordKey");
			if( password.length() < pwdLength ){
				errorTO.addError(key, this.getProperty("passwordLenError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
