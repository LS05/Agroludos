package agroludos.business.as.gestoremail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
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

		final String username = "jackeb@hotmail.it";
		final String password = "270588";


		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.live.com");
		props.put("mail.smtp.ssl.trust", "smtp.live.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jackeb@hotmail.it"));
			
			message.setSubject(emailTO.getOggetto());
			message.setText(emailTO.getMessage());
			// To get the array of addresses
			for(UtenteTO uTO: emailTO.getDestinatari()){;
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(uTO.getEmail()));
				Transport.send(message);
			}
			res = true;
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return res;

	}

}
