package agroludos.business.validator.rules.optional;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.OptionalTO;

class OptionalStatoRule extends AgroludosRule {
	private StringValidator strValidator;

	protected OptionalStatoRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof OptionalTO){
			OptionalTO tipo = (OptionalTO)mainTO;
			String nome = tipo.getNome();
			
			if(nome.length() < 0){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeLenghtError"));
			} else if(!this.strValidator.isAlpha(nome)){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeAlphaError"));
			}

			if(this.successor != null)
				this.successor.validate(tipo, errorTO);
		}
	}
	
	public void setStrValidator(StringValidator strValidator) {
		this.strValidator = strValidator;
	}

}
