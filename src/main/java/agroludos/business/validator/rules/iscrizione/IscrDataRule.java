package agroludos.business.validator.rules.iscrizione;

import java.util.Date;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.IscrizioneTO;

class IscrDataRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof IscrizioneTO){
			IscrizioneTO competizione = (IscrizioneTO)mainTO;
			Date dataIscr = competizione.getData();

			String key = this.getRule("dataKey");
			if( dataIscr == null){
				errorTO.addError(key, this.getRule("emptyDataError"));
			}

			if(this.successor != null){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

}
