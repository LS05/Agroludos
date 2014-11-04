package agroludos.business.validator.rules.iscrizione;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.IscrizioneTO;

class IscrCostoRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof IscrizioneTO){
			IscrizioneTO competizione = (IscrizioneTO)mainTO;
			Double costo = competizione.getCosto();

			String key = this.getRule("costoKey");
			if( costo < 0 ){
				errorTO.addError(key, this.getRule("costoError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
