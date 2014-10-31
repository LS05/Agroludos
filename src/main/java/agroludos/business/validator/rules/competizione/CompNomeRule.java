package agroludos.business.validator.rules.competizione;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

class CompNomeRule extends AgroludosRule {

	protected CompNomeRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			String nome = competizione.getNome();
			Integer nameLength = Integer.valueOf(this.getProperty("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getProperty("nomeKey"), this.getProperty("nomeLenghtError"));
			}

			if(this.successor != null)
				this.successor.validate(competizione, errorTO);
		}
	}

}
