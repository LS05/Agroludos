package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;

public class PartUsernameRule extends AgroludosRule {

	protected PartUsernameRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if( this.successor != null )
			this.successor.validate(mainTO, errorTO);
	}

}
