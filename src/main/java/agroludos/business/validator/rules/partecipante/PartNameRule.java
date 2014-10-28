package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartNameRule extends AgroludosRule {
	StringValidator strValidator;
	
	PartNameRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO to, ErrorTO errorTO) {
		PartecipanteTO partecipante = (PartecipanteTO)to;
		String nome = partecipante.getNome();
		Integer nameLength = Integer.valueOf(this.getProperty("partNameLength"));

		if(!this.strValidator.checkLength(nome, nameLength)){
			errorTO.addError("nome", "Nome Errato! Inserire almeno 1 carattere");
		} else if(!this.strValidator.isAlpha(nome)){
			errorTO.addError("nome", "Nome Errato. Inserire solo caratteri alfabetici!");
		}
		
		if(this.successor != null)
			this.successor.validate(partecipante, errorTO);
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}

}