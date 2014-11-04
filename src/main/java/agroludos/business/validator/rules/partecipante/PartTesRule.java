package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartTesRule extends AgroludosRule {

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