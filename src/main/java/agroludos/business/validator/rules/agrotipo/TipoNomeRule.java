package agroludos.business.validator.rules.agrotipo;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TipiAgroludosTO;

class TipoNomeRule extends AgroludosRule {

	protected TipoNomeRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof TipiAgroludosTO){
			TipiAgroludosTO tipo = (TipiAgroludosTO)mainTO;
			String nome = tipo.getNome();
			Integer nameLength = Integer.valueOf(this.getProperty("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getProperty("nomeKey"), this.getProperty("nomeLenghtError"));
			}

			if(this.successor != null)
				this.successor.validate(tipo, errorTO);
		}
	}
}