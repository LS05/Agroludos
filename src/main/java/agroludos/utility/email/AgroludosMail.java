package agroludos.utility.email;

import agroludos.to.EmailTO;

/**
 * Classe di supporto per l'invio delle mail. Il metodo sendEmail esegue operazioni sulla
 * rete che vengono eseguite su un Thread specifico
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface AgroludosMail {

	/**
	 * Invia una mail sfruttando i dati presenti nel Transfer Object
	 * @param email Transfer Object per i dati da inviare tramite mail
	 */
	public void sendEmail(EmailTO email);

}