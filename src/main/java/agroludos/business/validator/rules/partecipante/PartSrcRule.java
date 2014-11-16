package agroludos.business.validator.rules.partecipante;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.file.FileValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;

/**
 * Gestisce gli errori sul file src
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PartSrcRule extends AgroludosRule {
	private FileValidator fileValidator;

	/**
	 * controlla il formato del file
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO partecipante = (PartecipanteTO)mainTO;
			String srcFormat = this.getTipoSrc();

			String src = partecipante.getSrc();
			String key = this.getRule("srcKey");
			if(!this.fileValidator.isOfFormat(src, srcFormat)){
				errorTO.addError(key, this.getRule("srcFormatError"));
			}

			if(this.successor != null)
				this.successor.validate(partecipante, errorTO);
		}
	}

	public void setFileValidator(FileValidator fileValidator) {
		this.fileValidator = fileValidator;
	}
}