package agroludos.business.validator.rules.optional;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.OptionalTO;

/**
 * Gestisce gli errori sul costo degli optional
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class OptionalCostoRule extends AgroludosRule {


	/**
	 * controlla che il costo non sia inferiore a 0
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof OptionalTO){
			OptionalTO optional = (OptionalTO)mainTO;
			Double costoOpt = optional.getCosto();

			if(costoOpt < 0){
				errorTO.addError(this.getRule("costoKey"), this.getRule("costoError"));
			}

			if(this.successor != null){
				this.successor.validate(optional, errorTO);
			}
		}
	}

}