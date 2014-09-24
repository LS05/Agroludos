package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

public class UsernameRule extends AgroludosRule {
	
	StringValidator strValidator;
	
	protected UsernameRule() throws IOException {
		super();
	}
	
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		UtenteTO user = (UtenteTO)mainTO;
		
		//TODO Da rivedere
		String username = user.getUsername();
		Integer usernameLength = Integer.valueOf(this.getProperty("userUsernameLength"));
		
		if(!this.strValidator.checkLength(username, usernameLength)){
			errorTO.addError("username", "Username errata. Inserire una username di almeno");
		}else if(!this.strValidator.isAlphaNumeric(username)){
			errorTO.addError("username", "Username errata. Deve contenere sia lettere che numeri.");
		}
		
		if(this.successor != null)
			this.validate(mainTO, errorTO);
		
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}
}