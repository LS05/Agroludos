package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserUsernameRule extends AgroludosRule {

	UserUsernameRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String username = user.getUsername();
			Integer sLen = Integer.valueOf(this.getProperty("usernameLength"));

			String cfKey = this.getProperty("usernameKey");
			if( username.length() < sLen ){
				errorTO.addError(cfKey, this.getProperty("usernameLenError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}
}