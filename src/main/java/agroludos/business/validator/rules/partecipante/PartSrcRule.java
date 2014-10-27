package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartSrcRule extends AgroludosRule {

	protected PartSrcRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO to, ErrorTO errorTO) {
		PartecipanteTO partecipante = (PartecipanteTO)to;
		String src = partecipante.getSrc();
		String ext = FilenameUtils.getExtension(src);

		if(!"txt".equals(ext)){
			errorTO.addError("src", "File non supportato. Inserire un file .txt!");
		}
		
		if(this.successor != null)
			this.successor.validate(partecipante, errorTO);
	}
}