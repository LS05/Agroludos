package agroludos.business.validator.rules.partecipante;

import java.util.Date;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.date.DateValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartDataSrcRule extends AgroludosRule {
	private DateValidator dateValidator;

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			String key = this.getRule("dataSrcKey");

			if(parTO.getDataNasc() != null){
				if(this.dateValidator.isBefore(parTO.getDataNasc(), new Date())){
					errorTO.addError(key, this.getRule("dataSrcError"));
				}
			}else{
				errorTO.addError(key, this.getRule("dataError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}

	public void setDateValidator(DateValidator dateValidator) {
		this.dateValidator = dateValidator;
	}
}