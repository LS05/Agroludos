package agroludos.system;

/**
 * Permette di accedere al file di proprietà delle richieste. In questo modo
 * i controller delle view che effettuano le richieste indicando il nome, non
 * necessitano di essere modificati se il nome della richiesta dovesse cambiare.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface ReqConf extends Conf{
	/**
	 * Ritorna il nome della richiesta in base alla chiave specificata.
	 * 
	 * @param requestName
	 * @return
	 */
	String getRequest(String requestName);
}