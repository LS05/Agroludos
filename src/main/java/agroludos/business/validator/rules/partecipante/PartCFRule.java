package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

public class PartCFRule extends AgroludosRule {
	StringValidator strValidator;
	
	PartCFRule() throws IOException{
		super();
	}

	@Override
	public void validate(AgroludosTO to, ErrorTO errorTO) {
		PartecipanteTO partecipante = (PartecipanteTO)to;
		//TODO Da rivedere
		String cf = partecipante.getCf();

		if(!this.strValidator.isAlphaNumeric(cf)){
			errorTO.addError("cf", "Codice fiscale errato! Valori numerici mancanti.");
		} else if(cf.length() != 16){
			errorTO.addError("cf", "Codice fiscale errato. Il Codice Fiscale deve essere di 16 cifre!");
		}
		
		if(this.successor != null)
			this.successor.validate(partecipante, errorTO);

	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}
}