package agroludos.business.validator.rules.mds;

import agroludos.business.validator.rules.AgroludosRule;
import agroludos.business.validator.rules.strings.StringValidator;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiSistemaTO;

/**
 * Gestisce gli errori sul numero di telefono del manager di sistem
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class MdsNumeroTelefonoRule extends AgroludosRule {
	private StringValidator stringValidator;

	/**
	 * controlla che il numero sia numerico e che sia maggiore della lunghezza segnata nel file di proprietà
	 */
	@Override
	public void validate(AgroludosTO mainTO, ErrorTO errorTO) {
		if(mainTO instanceof ManagerDiSistemaTO){
			ManagerDiSistemaTO mds = (ManagerDiSistemaTO)mainTO;
			String telefono = mds.getTelefono();

			String key = this.getRule("telefonoKey");
			int telLen = Integer.valueOf(this.getRule("telefonoLength"));

			if( !this.stringValidator.isNumeric(telefono) ){
				errorTO.addError(key, this.getRule("telefonoAlphaError"));
			} else if( telefono.length() < telLen ){
				errorTO.addError(key, this.getRule("telefonoLenError"));
			}

			if(this.successor != null){
				this.successor.validate(mainTO, errorTO);
			}
		}
	}

	public void setStringValidator(StringValidator stringValidator) {
		this.stringValidator = stringValidator;
	}
}