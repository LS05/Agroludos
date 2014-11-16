package agroludos.to;

import java.util.List;

/**
 * L'interfaccia rappresenta un oggetto Email . <br>
 * Sono quindi forniti tutti i metodi per gestire l'invio di una email.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface EmailTO extends AgroludosTO{

	/**
	 * Inserisce il destinatario della email
	 * @param dest
	 */
	void addDestinatario(UtenteTO dest);

	/**
	 * 
	 * @return La lista di destinatari
	 * @see UtenteTO
	 */
	List<UtenteTO> getDestinatari();

	/**
	 * 
	 * @return il messaggio da inviare tramite email
	 */
	String getMessage();

	/**
	 * Inserisce il messaggio da inviare
	 * @param message
	 */
	void setMessage(String message);

	/**
	 * 
	 * @return l'oggetto del messaggio
	 */
	String getOggetto();

	/**
	 * Inserisce l'oggetto del messaggio
	 * @param ogg
	 */
	void setOggetto(String ogg);

}