package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

public class PartTesRule extends AgroludosRule {

	protected PartTesRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO to, ErrorTO errorTO) {
		PartecipanteTO partecipante = (PartecipanteTO)to;
		String tesSan = partecipante.getNumTS();

		if(tesSan.length() != 16){
			errorTO.addError("tess-san", "Tessera sanitaria non valida! Inserire un numero di 16 cifre");
		}
		
		if(this.successor != null)
			this.successor.validate(partecipante, errorTO);
	}
}