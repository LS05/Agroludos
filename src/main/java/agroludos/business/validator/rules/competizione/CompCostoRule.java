package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

class CompCostoRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			Double costo = competizione.getCosto();

			String key = this.getRule("costoKey");
			if( costo <= 0 ){
				errorTO.addError(key, this.getRule("costoError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
