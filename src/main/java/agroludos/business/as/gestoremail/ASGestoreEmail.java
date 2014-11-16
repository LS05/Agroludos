package agroludos.business.as.gestoremail;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.EmailTO;
import agroludos.utility.email.AgroludosMail;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Email.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare l'invio delle email
 * da parte del sistema Agroludos
 *
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreEmail extends AgroludosAS implements LEmail{

	private AgroludosMail agroludosMail;

	/**
	 * Il costruttore inizializza il parametro agroludosMail
	 * 
	 * @param agroludosMail
	 * @see agroludos.utility.email.AgroludosMail
	 */
	ASGestoreEmail(AgroludosMail agroludosMail){
		this.agroludosMail = agroludosMail;
	}

	@Override
	public EmailTO inviaMail(EmailTO mail) throws DatabaseException{
		this.agroludosMail.sendEmail(mail);
		return mail;
	}

}