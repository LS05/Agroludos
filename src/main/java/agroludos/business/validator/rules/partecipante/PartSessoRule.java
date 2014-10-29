package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartSessoRule extends AgroludosRule {
	
	protected PartSessoRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			String sesso = parTO.getSesso();
			Integer sessoLen = Integer.valueOf(this.getProperty("partSexLength"));
			String sessoKey = this.getProperty("sessoKey");
			
			if((sesso.length() < sessoLen)){
				errorTO.addError(sessoKey, this.getProperty("sessoError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}
}