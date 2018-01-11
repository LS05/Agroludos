package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

/**
 * Gestisce gli errori sulla tessera sanitaria del partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartTesRule extends AgroludosRule {

	/**
	 * controlla la lunghezza
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String tesSan = partecipante.getNumTS();
			String key = this.getRule("tesKey");
			
			if(tesSan.length() != 16){
				errorTO.addError(key, this.getRule("tesSanLenError"));
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}
}