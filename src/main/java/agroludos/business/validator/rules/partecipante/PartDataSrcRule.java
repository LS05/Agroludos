package agroludos.business.validator.rules.partecipante;


import org.joda.time.DateMidnight;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.date.DateValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

/**
 * gestisce gli errori sulla data del certificato src
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartDataSrcRule extends AgroludosRule {
	private DateValidator dateValidator;

	/**
	 * controlla che non sia nul e che sia antecendente ad oggi
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			String key = this.getRule("dataSrcKey");
			DateMidnight today = new DateMidnight();
			
			if(parTO.getDataSRC()!= null){
				DateMidnight dataSrc = new DateMidnight(parTO.getDataSRC());
				if(!this.dateValidator.isBefore(today.toDate(),dataSrc.plusYears(1).toDate())){
					errorTO.addError(key, this.getRule("dataSrcError"));
				}else if(today.isBefore(dataSrc)){
					errorTO.addError(key, this.getRule("dataSrcErrorFuturo"));
				}
			}else{
				errorTO.addError(key, this.getRule("dataError"));
			}
			
			if( this.successor != null ){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

	public void setDateValidator(DateValidator dateValidator) {
		this.dateValidator = dateValidator;
	}
}