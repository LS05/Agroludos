package agroludos.business.validator.rules.mdc;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;

class MdcStipendioRule extends AgroludosRule {

	protected MdcStipendioRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof ManagerDiCompetizioneTO){
			ManagerDiCompetizioneTO partecipante = (ManagerDiCompetizioneTO)mainTO;
			Double stipendio = partecipante.getStipendio();

			String key = this.getProperty("stipendioKey");
			if( stipendio < 0 ){
				errorTO.addError(key, this.getProperty("stipendioError"));
			}

			if( this.successor != null)
				this.successor.validate(mainTO, errorTO);
		}
	}

}
