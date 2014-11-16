package agroludos.to;

/**
 * L'interfaccia rappresenta un oggetto che gestisce i messaggi a video. <br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface MessageTO extends AgroludosTO{

	/**
	 * Inserisce il testo del messaggio
	 * @param message
	 */
	public void setMessage(String message);

	/**
	 * 
	 * @return il testo del messaggio
	 */
	public String getMessage();

}