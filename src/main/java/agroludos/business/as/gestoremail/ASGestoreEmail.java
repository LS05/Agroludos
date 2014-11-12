package agroludos.business.as.gestoremail;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.EmailTO;
import agroludos.utility.email.AgroludosMail;

class ASGestoreEmail extends AgroludosAS implements LEmail{

	private AgroludosMail agroludosMail;

	ASGestoreEmail(AgroludosMail agroludosMail){
		this.agroludosMail = agroludosMail;
	}

	@Override
	public EmailTO inviaMail(EmailTO mail) throws DatabaseException{
		this.agroludosMail.sendEmail(mail);
		return mail;
	}

}