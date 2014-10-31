package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserCognomeRule extends AgroludosRule {
	private StringValidator strValidator;

	protected UserCognomeRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String cognome = user.getCognome();
			Integer cognLength = Integer.valueOf(this.getProperty("cognomeLength"));

			String key = this.getProperty("cognKey");
			if( cognome.length() < cognLength ){
				errorTO.addError(key, this.getProperty("cognLenghtError"));
			}else if( !this.strValidator.isAlpha(cognome) ){
				errorTO.addError(key, this.getProperty("cognNAlphaError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}

}