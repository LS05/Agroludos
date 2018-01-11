package agroludos.to;

import java.util.Set;

/**
 * L'interfaccia rappresenta un oggetto che definisce gli errori commessi durante un inserimento. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso 
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface ErrorTO extends AgroludosTO{

	/**
	 * Aggiunge al TO l'id e il messaggio dell'errore
	 * @param id
	 * @param message
	 */
	void addError(String id, String message);

	/**
	 * 
	 * @param id
	 * @return il corpo del messaggio in base all'id
	 */
	String getError(String id);
	
	/**
	 * 
	 * @return L'insieme degli errori aggiunti al TO
	 */
	Set<String> getErrors();
	
	/**
	 * 
	 * @return vero se contiene errori falso altrimenti
	 */
	boolean hasErrors();
	
	/**
	 * 
	 * @param id
	 * @return vero se contiene l'errore con l'id in input
	 */
	boolean hasError(String id);

}