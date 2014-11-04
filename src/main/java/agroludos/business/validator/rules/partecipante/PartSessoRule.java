package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartSessoRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			String sesso = parTO.getSesso();
			Integer sessoLen = Integer.valueOf(this.getRule("partSexLength"));
			String sessoKey = this.getRule("sessoKey");

			if(sesso.length() < sessoLen){
				errorTO.addError(sessoKey, this.getRule("sessoError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}
}