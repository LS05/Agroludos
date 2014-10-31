package agroludos.business.validator.rules.competizione;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

class CompCostoRule extends AgroludosRule {

	protected CompCostoRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			Double costo = competizione.getCosto();

			String key = this.getProperty("costoKey");
			if( costo < 0 ){
				errorTO.addError(key, this.getProperty("costoError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
