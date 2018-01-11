package agroludos.business.validator.rules.competizione;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.CompetizioneTO;

/**
 * Gestisce gli errori sul numero di partecipanti di una competizione
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class CompNumPartRule extends AgroludosRule {

	/**
	 * controlla se il numero minimo di iscritti e il numero massimo di iscriti 
	 * ad una competizione sia maggiore di 0 e se il numero numero minimo è inferiore al numero massimo
	 * @see CompetizioneTO
	 * 
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof CompetizioneTO){
			CompetizioneTO competizione = (CompetizioneTO)mainTO;
			int nmax = competizione.getNmax();
			int nmin = competizione.getNmin();

			String key = this.getRule("nPartKey");
			if( nmax <= 0 || nmin <= 0){
				errorTO.addError(key, this.getRule("nPartError"));
			}else if(nmin > nmax){
				errorTO.addError(key, this.getRule("nPartNminNmaxError"));
			}

			if(this.successor != null){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

}