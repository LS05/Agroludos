package agroludos.business.validator.rules.competizione;

import org.joda.time.DateMidnight;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorTO;

/**
 * Gestisce gli errori sulla data di una competizione
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class CompDataRule extends AgroludosRule {

	/**
	 * Controlla che la data sia posteriore a 2 giorni da oggi, che non sia antecedente ad oggi <br>
	 * e che non sia null
	 */
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

			if(this.successor != null){
				this.successor.validate(competizione, errorTO);
			}
		}
	}

}
