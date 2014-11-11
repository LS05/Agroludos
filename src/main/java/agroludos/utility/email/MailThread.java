package agroludos.utility.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

class MailThread extends Thread{
	private static int mailThreadID = 0;
	private Message message;

	MailThread(Message message){
		super("agroludos-mail--" + "#" + mailThreadID++);
		this.message = message;
	}

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