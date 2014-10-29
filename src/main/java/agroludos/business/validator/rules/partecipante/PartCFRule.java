package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartCFRule extends AgroludosRule {
	private StringValidator strValidator;

	PartCFRule() throws IOException{
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String cf = partecipante.getCf();

			String key = this.getProperty("cfKey");
			if(!this.strValidator.isAlphaNumeric(cf)){
				errorTO.addError(key, this.getProperty("cfAlphaError"));
			} else if(cf.length() != 16){
				errorTO.addError(key, this.getProperty("cfLengthError"));
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}
}