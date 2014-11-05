package agroludos.business.validator.rules.competizione;

import java.util.Date;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorTO;

public class CompDataRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			Date data = competizione.getData();

			String key = this.getRule("dataCmpKey");

			if(data == null){
				errorTO.addError(key, this.getRule("dataError"));
			}else if(data.before(new Date())){
				errorTO.addError(key, this.getRule("dataCmpError"));
			}

			if(this.successor != null)
				this.successor.validate(competizione, errorTO);
		}
	}

}
