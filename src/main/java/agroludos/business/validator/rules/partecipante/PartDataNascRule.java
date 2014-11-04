package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartDataNascRule extends AgroludosRule {
	
	protected PartDataNascRule() throws IOException {
		super();
	}

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