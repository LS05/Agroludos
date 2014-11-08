package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserUsernameRule extends AgroludosRule {

	UserUsernameRule() {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String username = user.getUsername();
			Integer sLen = Integer.valueOf(this.getRule("usernameLength"));

			String key = this.getRule("usernameKey");
			if( username.length() < sLen ){
				errorTO.addError(key, this.getRule("usernameLenError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}
}