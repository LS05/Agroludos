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

			if(tesSan.length() != 16){
				errorTO.addError("tess-san", "Tessera sanitaria non valida! Inserire un numero di 16 cifre");
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}
}