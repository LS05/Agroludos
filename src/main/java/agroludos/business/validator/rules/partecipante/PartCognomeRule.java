package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartCognomeRule extends AgroludosRule {
	StringValidator strValidator;

	protected PartCognomeRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String cognome = partecipante.getCognome();
			Integer cognLength = Integer.valueOf(this.getProperty("partCognomeLength"));

			String cfKey = this.getProperty("cognKey");
			if( !this.strValidator.isAlpha(cognome) ){
				errorTO.addError(cfKey, this.getProperty("cognNAlphaError"));
			} else if( cognome.length() < cognLength ){
				errorTO.addError(cfKey, this.getProperty("cfLengthError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}

}