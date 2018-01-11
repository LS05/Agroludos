package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Competizione. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface TipoUtenteTO extends AgroludosTO, Comparable<TipoUtenteTO>{

	public String getNome();

	/**
	 * Inserisce il nome del tipo di utente
	 * @param nome
	 */
	public void setNome(String nome);

	/**
	 * 
	 * @return id del tipo di utente
	 */
	public Integer getId();
}
