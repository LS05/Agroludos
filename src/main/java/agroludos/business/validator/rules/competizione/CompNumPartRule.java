package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

class CompNumPartRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			int nmax = competizione.getNmax();
			int nmin = competizione.getNmin();

			String key = this.getRule("nPartKey");
			if( nmax < 0 || nmin < 0){
				errorTO.addError(key, this.getRule("nPartError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}