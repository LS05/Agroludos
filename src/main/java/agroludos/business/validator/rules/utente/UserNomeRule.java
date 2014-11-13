package agroludos.business.validator.rules.utente;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

class UserNomeRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof UtenteTO){
			UtenteTO user = (UtenteTO)mainTO;
			String nome = user.getNome();
			Integer nameLength = Integer.valueOf(this.getRule("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeLenghtError"));
			}

			if(this.successor != null){
				this.successor.validate(user, errorTO);
			}
		}
	}

}