package agroludos.business.validator.rules.competizione;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

class CompNumPartRule extends AgroludosRule {

	protected CompNumPartRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			int nmax = competizione.getNmax();
			int nmin = competizione.getNmin();

			String key = this.getProperty("nPartKey");
			if( nmax < 0 || nmin < 0){
				errorTO.addError(key, this.getProperty("nPartError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
