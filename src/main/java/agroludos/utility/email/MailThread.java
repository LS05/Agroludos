package agroludos.utility.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 * Rappresenta il Thread su cui viene eseguito l'invio della mail.
 * E' stato scelto l'uso della classe Thread e quindi del multithreading per mantenere
 * un'interfaccia responsive nonostante venga eseguita un'operazione sulla rete che
 * può rivelarsi pesante
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class MailThread extends Thread{
	private static int mailThreadID = 0;
	private Message message;

	MailThread(Message message){
		super("agroludos-mail--" + "#" + mailThreadID++);
		this.message = message;
	}

	/**
	 * Il metodo utilizza send() della classe Transport che effettua il vero e proprio
	 * invio della mail.
	 * 
	 * @see javax.mail.Transport#send(Message)
	 */
	@Override
	public void run(){
		try {
			Transport.send(this.message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return getName();
	}

}