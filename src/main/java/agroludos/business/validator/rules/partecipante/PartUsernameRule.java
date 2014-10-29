package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartUsernameRule extends AgroludosRule {
	
	protected PartUsernameRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String username = partecipante.getUsername();
			Integer sLen = Integer.valueOf(this.getProperty("partUsernameLength"));

			String cfKey = this.getProperty("usernameKey");
			if( username.length() < sLen ){
				errorTO.addError(cfKey, this.getProperty("usernameLenError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}
}