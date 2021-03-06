package agroludos.business.validator.rules.agrotipo;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.TipiAgroludosTO;

/**
 * Gestisce gli errori sui nomi dei {@link TipiAgroludosTO}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class TipoNomeRule extends AgroludosRule {

	/**
	 * Controlla la lunghezza del nome del tipo 
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof TipiAgroludosTO){
			TipiAgroludosTO tipo = (TipiAgroludosTO)mainTO;
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