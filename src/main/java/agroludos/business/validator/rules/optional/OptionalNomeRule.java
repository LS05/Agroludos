package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.OptionalTO;

/**
 * Gestisce gli errori sul nome degli optional
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class OptionalNomeRule extends AgroludosRule {

	/**
	 * controlla che il la lunghezza del nome super la lunghezza segnata nel file di proprietà
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof OptionalTO){
			OptionalTO tipo = (OptionalTO)mainTO;
			String nome = tipo.getNome();
			Integer nameLength = Integer.valueOf(this.getRule("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeLenghtError"));
			}

			if(this.successor != null){
				this.successor.validate(tipo, errorTO);
			}
		}
	}

}