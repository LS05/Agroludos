package agroludos.business.validator.rules.utente;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.UtenteTO;

public class NomeRule extends AgroludosRule{

	StringValidator stringValidator;
	
	protected NomeRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		UtenteTO utente = (UtenteTO)mainTO;
		String nome = utente.getNome();
		Integer nomeLength = Integer.valueOf(this.getProperty("userNomeLength"));
		if(nomeLength < nome.length()){
			errorTO.addError("nome", "Il nome può contenere solo lettere!");
		} else if(!this.stringValidator.isAlpha(nome)) {
			errorTO.addError("nome", "La lunghezza del nome deve essere maggiore di " + nomeLength);
		}
		
		if(this.successor != null)
			this.validate(utente, errorTO);
	}
	
}
