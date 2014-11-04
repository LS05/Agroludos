package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserCognomeRule extends AgroludosRule {
	private StringValidator strValidator;

	UserCognomeRule(StringValidator strValidator) throws IOException {
		super();
		this.strValidator = strValidator;
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String cognome = user.getCognome();
			Integer cognLength = Integer.valueOf(this.getRule("cognomeLength"));

			String key = this.getRule("cognKey");
			if( cognome.length() < cognLength ){
				errorTO.addError(key, this.getRule("cognLenghtError"));
			}else if( !this.strValidator.isAlpha(cognome) ){
				errorTO.addError(key, this.getRule("cognNAlphaError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}
}