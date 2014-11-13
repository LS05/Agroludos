package agroludos.business.validator.rules.mdc;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;

class MdcStipendioRule extends AgroludosRule {

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof ManagerDiCompetizioneTO){
			ManagerDiCompetizioneTO partecipante = (ManagerDiCompetizioneTO)mainTO;
			Double stipendio = partecipante.getStipendio();

			String key = this.getRule("stipendioKey");
			if( stipendio < 0 ){
				errorTO.addError(key, this.getRule("stipendioError"));
			}

			if(this.successor != null){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

}