package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserPasswordRule extends AgroludosRule {

	UserPasswordRule() throws IOException {
		super();
	}

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

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
