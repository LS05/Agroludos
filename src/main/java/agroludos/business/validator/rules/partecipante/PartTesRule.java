package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartTesRule extends AgroludosRule {

	protected PartTesRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String tesSan = partecipante.getNumTS();
			String key = this.getProperty("tesKey");
			
			if(tesSan.length() != 16){
				errorTO.addError(key, this.getProperty("tesSanLenError"));
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}
}