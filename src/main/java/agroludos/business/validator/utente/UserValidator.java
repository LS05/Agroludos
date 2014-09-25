package agroludos.business.validator.utente;

import agroludos.business.validator.AgroludosValidator;
import agroludos.business.validator.rules.utente.PasswordRule;
import agroludos.business.validator.rules.utente.UsernameRule;
import agroludos.exceptions.ValidationException;
import agroludos.to.AgroludosTO;
import agroludos.to.UtenteTO;

public class UserValidator implements AgroludosValidator{
	private UsernameRule nameRule;
	private PasswordRule passwordRule;
	
	public UserValidator(){
		nameRule.setSuccessor(passwordRule);
	}
	
	@Override
	public void validate(AgroludosTO to) throws ValidationException {
		UtenteTO partecipante = (UtenteTO)to;
		nameRule.validate(partecipante, null);
	}

}