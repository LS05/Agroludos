package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

public class PartEmailRule extends AgroludosRule {
	
	PartEmailRule() throws IOException {
		super();
	}

	StringValidator strValidator;
	
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