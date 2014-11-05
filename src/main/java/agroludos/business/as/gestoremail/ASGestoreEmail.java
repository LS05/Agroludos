package agroludos.business.as.gestoremail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.to.EmailTO;
import agroludos.to.UtenteTO;

class ASGestoreEmail extends AgroludosAS implements LEmail, SEmail{

	@Override
	public boolean sendEmail(EmailTO emailTO) throws DatabaseException {
		boolean res = false;
		String username = "agroludos.uniba";  
		String password = "ardimento2014";
		String recipient = "agroludos.uniba@gmail.com";

		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));
		MimeMessage message = new MimeMessage(session);

		

		try {
			message.setFrom(new InternetAddress(recipient));
			InternetAddress[] toAddress = new InternetAddress[emailTO.getDestinatari().size()];

			// To get the array of addresses
			int i = 0;
			for(UtenteTO uTO: emailTO.getDestinatari()){
				toAddress[i] = new InternetAddress(uTO.getEmail());
				i++;
			}
			 for(i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			
			message.setSubject(emailTO.getOggetto());
			message.setText(emailTO.getMessage());
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			res = true;
		}
		catch (AddressException ae) {
			ae.printStackTrace();
		}
		catch (MessagingException me) {
			me.printStackTrace();
		}
		return res;

	}
	
}
