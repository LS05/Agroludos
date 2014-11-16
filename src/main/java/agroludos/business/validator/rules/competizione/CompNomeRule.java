package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

/**
 * Gestisce gli errori sul nome della competizione
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class CompNomeRule extends AgroludosRule {

	/**
	 * controlla che la lunghezza del nome sia maggiore della lunghezza segnata nel file di proprietà
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			String nome = competizione.getNome();
			Integer nameLength = Integer.valueOf(this.getRule("nameLength"));

			if(nome.length() < nameLength){
				errorTO.addError(this.getRule("nomeKey"), this.getRule("nomeLenghtError"));
			}

			if(this.successor != null){
				this.successor.validate(competizione, errorTO);
			}
		}
	}

}