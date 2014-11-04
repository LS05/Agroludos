package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserNomeRule extends AgroludosRule {
	private StringValidator strValidator;

	UserNomeRule(StringValidator strValidator) throws IOException {
		super();
		this.strValidator = strValidator;
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String nome = user.getNome();
			Integer nameLength = Integer.valueOf(this.getRule("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeLenghtError"));
			} else if(!this.strValidator.isAlpha(nome)){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeAlphaError"));
			}

			if(this.successor != null)
				this.successor.validate(user, errorTO);
		}
	}

}