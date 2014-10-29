package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartEmailRule extends AgroludosRule {
	StringValidator strValidator;

	PartEmailRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String email = partecipante.getEmail();

			if(!this.strValidator.isValidEmail(email)){
				errorTO.addError(this.getProperty("emailKey"), this.getProperty("emailError"));
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}
}