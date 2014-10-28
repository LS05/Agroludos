package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartDataSrcRule extends AgroludosRule {

	protected PartDataSrcRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO parTO = (PartecipanteTO)mainTO;
			LocalDate date = new DateTime(parTO.getDataSRC()).toLocalDate();
			LocalDate today = new DateTime().toLocalDate();

			String dataKey = this.getProperty("dataSrcKey");
			if(date.isBefore(today)){
				errorTO.addError(dataKey, this.getProperty("dataSrcError"));
			}

			if( this.successor != null )
				this.successor.validate(mainTO, errorTO);
		}
	}
}