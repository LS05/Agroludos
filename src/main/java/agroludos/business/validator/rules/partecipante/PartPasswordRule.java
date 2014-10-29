package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartPasswordRule extends AgroludosRule {

	protected PartPasswordRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String password = partecipante.getPassword();
			Integer pwdLength = Integer.valueOf(this.getProperty("partPasswordLength"));

			String key = this.getProperty("passwordKey");
			if( password.length() < pwdLength ){
				errorTO.addError(key, this.getProperty("passwordLenError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
