package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

/**
 * gestisce gli errori sulla data di nascita di un partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartDataNascRule extends AgroludosRule {

	/**
	 * controlla che non sia null
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			String key = this.getRule("dataNascKey");

			if(parTO.getDataNasc() == null){
				errorTO.addError(key, this.getRule("dataError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}
}