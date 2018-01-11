package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

/**
 * GEstisce gli errori sul sesso di un partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartSessoRule extends AgroludosRule {

	/**
	 * controlla la lunghezza
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			String sesso = parTO.getSesso();
			Integer sessoLen = Integer.valueOf(this.getRule("partSexLength"));
			String sessoKey = this.getRule("sessoKey");

			if(sesso.length() < sessoLen){
				errorTO.addError(sessoKey, this.getRule("sessoError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}
}