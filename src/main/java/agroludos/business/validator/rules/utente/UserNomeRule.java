package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserNomeRule extends AgroludosRule {
	private StringValidator strValidator;

	UserNomeRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String nome = user.getNome();
			Integer nameLength = Integer.valueOf(this.getProperty("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getProperty("nomeKey"), this.getProperty("nomeLenghtError"));
			} else if(!this.strValidator.isAlpha(nome)){
				errorTO.addError(this.getProperty("nomeKey"), this.getProperty("nomeAlphaError"));
			}

			if(this.successor != null)
				this.successor.validate(user, errorTO);
		}
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}

}