package agroludos.utility.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import agroludos.system.Conf;
import agroludos.to.EmailTO;
import agroludos.to.UtenteTO;

/**
 * La classe si occupa di inviare una mail. L'operazione di invio delle mail è
 * eseguita su un Thread rappresentato dalla classe {@link MailThread}.
 * Vengono utilizzati i valori di configurazione delle mail presenti nel file di
 * proprietà corrispondente grazie all'uso dell'interfaccia {@link Conf}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class AgroludosMailImpl implements AgroludosMail {
	/**
	 * Interfaccia per ottenere i valori di configuazione per le email come: username,
	 * password, server SMTP, porta ecc.
	 */
	private Conf sysConf;

	/**
	 * Inizializza sysConf
	 * 
	 * @param sysConf Interfaccia per accedere alle proprietà di sistema
	 */
	AgroludosMailImpl(Conf sysConf){
		this.sysConf = sysConf;
	}

	/**
	 * Il metodo si occupa di effettuare la configurazione per l'invio delle
	 * mail utilizzando {@link sysConf}
	 * 
	 * @return Proprietà per l'invio delle email
	 */
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

		String port = this.sysConf.getString("mail.smtp.port");
		props.put("mail.smtp.port", port);

		return props;
	}

	/**
	 * Il metodo invia una mail con i dati presenti nel Transfer Object, utilizzando
	 * la classe di supporto {@link MailThread} e le proprietà ottenute dal metodo
	 * {@link agroludos.utility.email.AgroludosMailImpl#createMailProperties()}
	 */
	@Override
	public void sendEmail(EmailTO emailTO) {
		final String username = this.sysConf.getString("agroludosMail");
		final String password = this.sysConf.getString("agroludosMailPwd");

		Properties props = createMailProperties();

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

			for(UtenteTO uTO: emailTO.getDestinatari()){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(uTO.getEmail()));
				new MailThread(message).start();
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return;
	}
}