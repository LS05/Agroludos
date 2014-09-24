package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

public class PasswordRule extends AgroludosRule{
	StringValidator stringValidator;
	
	protected PasswordRule() throws IOException {
		super();
	}
	
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		UtenteTO user = (UtenteTO)mainTO;
		char[] specChars = new char[]{'!', '\"', '#', '$', '%', '&', '\'', '(' ,')', '*' , '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~', ' '};
		String pwd = user.getPassword();
		if(!this.stringValidator.containsAny(pwd, specChars)){
			errorTO.addError("password", "La password deve contenere almeno un carattere speciale");
		}
	}

}
