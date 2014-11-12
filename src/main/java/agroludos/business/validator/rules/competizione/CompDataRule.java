package agroludos.business.validator.rules.competizione;

import org.joda.time.DateMidnight;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorTO;

public class CompDataRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;

			DateMidnight dataCmp = new DateMidnight(competizione.getData());
			DateMidnight today = new DateMidnight();

			String key = this.getRule("dataCmpKey");


			if(competizione.getData() == null){
				errorTO.addError(key, this.getRule("dataError"));
			}else if(dataCmp.isBeforeNow()){
				errorTO.addError(key, this.getRule("dataCmpError"));
			}else if(today.plusDays(1).isEqual(dataCmp) 
					|| today.plusDays(2).isEqual(dataCmp)){
				errorTO.addError(key, this.getRule("data2Error"));
			}

			if(this.successor != null)
				this.successor.validate(competizione, errorTO);
		}
	}

}
