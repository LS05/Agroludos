package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Stato della competizione. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti uno stato di una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface StatoCompetizioneTO extends AgroludosTO, Comparable<StatoCompetizioneTO>{

	/**
	 * 
	 * @return il nome dello stato della competizione
	 */
	public String getNome();

	/**
	 * 
	 * @return l'id dello stato della competizione
	 */
	Integer getId();

}