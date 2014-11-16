package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Stato degli utenti. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface StatoUtenteTO extends AgroludosTO, Comparable<StatoUtenteTO>{

	/**
	 * 
	 * @return il nome dello stato dell'utente
	 */
	public String getNome();

	/**
	 * 
	 * @return l'id dello stato dell'utente
	 */
	Integer getId();

}