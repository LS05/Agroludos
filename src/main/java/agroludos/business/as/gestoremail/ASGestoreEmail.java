package agroludos.business.as.gestoremail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.to.EmailTO;
import agroludos.to.UtenteTO;

class ASGestoreEmail extends AgroludosAS implements LEmail, SEmail{

	@Override
	public boolean sendEmail(EmailTO emailTO) throws DatabaseException {

		boolean res = false;

		//TODO controllare che tutti i campi siano completi

		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("username", "password"));
		email.setSSLOnConnect(true);

		try {
			email.setFrom(emailTO.getMittente().getEmail());
			email.setSubject(emailTO.getOggetto());
			email.setMsg(emailTO.getMessage());
			for(UtenteTO uTO: emailTO.getDestinatari()){
				email.addTo(uTO.getEmail());
				email.send();
				res = true;
			}
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		}
		
		return res;
	}

}
