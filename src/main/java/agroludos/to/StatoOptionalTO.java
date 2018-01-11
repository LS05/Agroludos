package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Stato dell'optional. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface StatoOptionalTO extends AgroludosTO, Comparable<StatoCompetizioneTO> {
	
	/**
	 * 
	 * @return il nome dello stato dell'optional
	 */
	public String getNome();

	/**
	 * 
	 * @return l'id dello stato dell'optional
	 */
	Integer getId();

}