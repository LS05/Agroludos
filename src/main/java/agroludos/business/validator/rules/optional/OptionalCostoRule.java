package agroludos.business.validator.rules.optional;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.OptionalTO;

class OptionalCostoRule extends AgroludosRule {

	protected OptionalCostoRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof OptionalTO){
			OptionalTO optional = (OptionalTO)mainTO;
			Double costoOpt = optional.getCosto();

			if(costoOpt < 0){
				errorTO.addError(this.getProperty("costoKey"), this.getProperty("costoError"));
			}

			if(this.successor != null)
				this.successor.validate(optional, errorTO);
		}
	}

}
