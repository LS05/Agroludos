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
	public void validate(AgroludosTO to, ErrorTO errorTO) {
		PartecipanteTO partecipante = (PartecipanteTO)to;
		String email = partecipante.getEmail();
		
		if(!this.strValidator.isValidEmail(email)){
			errorTO.addError("email", "Email non valida!");
		}
		
		if(this.successor != null)
			this.successor.validate(partecipante, errorTO);
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}
}