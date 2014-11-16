package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

/**
 * gestisce gli errori sull'indirizzo di un partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartIndirizzoRule extends AgroludosRule {

	/**
	 * controlla la lunghezza
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String indirizzo = partecipante.getIndirizzo();
			Integer indLength = Integer.valueOf(this.getRule("partIndLength"));

			String key = this.getRule("indKey");
			if( indirizzo.length() < indLength){
				errorTO.addError(key, this.getRule("indirizzoError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}