package agroludos.business.validator.rules.partecipante;

import java.io.IOException;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.file.FileValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

class PartSrcRule extends AgroludosRule {
	private FileValidator fileValidator;
	
	protected PartSrcRule() throws IOException {
		super();
	}

	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String srcFormat = this.getProperty("srcFormat");
			
			String src = partecipante.getSRC();
			String key = this.getProperty("srcKey");
			if(!this.fileValidator.isOfFormat(src, srcFormat)){
				errorTO.addError(key, this.getProperty("srcFormatError"));
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}

	public void setFileValidator(FileValidator fileValidator) {
		this.fileValidator = fileValidator;
	}
}