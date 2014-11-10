package agroludos.utility.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import agroludos.system.Conf;
import agroludos.to.EmailTO;
import agroludos.to.UtenteTO;

class AgroludosMailImpl extends Thread implements AgroludosMail {

	private Conf sysConf;
	private EmailTO emailTO;

	AgroludosMailImpl(Conf sysConf){
		super("agroludos-mail");
		this.sysConf = sysConf;
	}

	private Properties createMailProperties(){
		Properties props = new Properties();

		String protocol = this.sysConf.getString("mail.transport.protocol");
		props.setProperty("mail.transport.protocol", protocol);

		String host = this.sysConf.getString("mail.host");
		props.setProperty("mail.host", host);

		String sslTrust = this.sysConf.getString("mail.smtp.ssl.trust");
		props.put("mail.smtp.ssl.trust", sslTrust);

		String startSsl = this.sysConf.getString("mail.smtp.starttls.enable");
		props.put("mail.smtp.starttls.enable", startSsl);

		String auth = this.sysConf.getString("mail.smtp.auth");
		props.put("mail.smtp.auth", auth);

		return props;
	}

	public void run() {
		this.sendEmailThread(this.emailTO);
	}

	@Override
	public void sendEmail(EmailTO emailTO) {
		this.emailTO = emailTO;
		start();
	}

	private boolean sendEmailThread(EmailTO emailTO) {

		boolean res = false;
		final String username = this.sysConf.getString("agroludosMail");
		final String password = this.sysConf.getString("agroludosMailPwd");

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "smtp.live.com");
		props.put("mail.smtp.ssl.trust", "smtp.live.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));

			message.setSubject(emailTO.getOggetto());
			message.setText(emailTO.getMessage());

			// To get the array of addresses
			for(UtenteTO uTO: emailTO.getDestinatari()){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(uTO.getEmail()));
				Transport.send(message);
			}

			res = true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			System.out.println(e.toString());
		}

		return res;
	}

	public String toString() {
		return getName();
	}

}