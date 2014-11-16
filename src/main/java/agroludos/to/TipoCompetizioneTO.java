package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Tipo della competizione. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * Estende l'interfaccia {@link TipiAgroludosTO}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface TipoCompetizioneTO extends TipiAgroludosTO, AgroludosTO, Comparable<TipoCompetizioneTO>{
	
	public String getNome();
	
	/**
	 * Inserisce il nome del tipo competizione
	 * @param nome
	 */
	public void setNome(String nome);
	
	/**
	 * 
	 * @return la descrizione del tipo di competizione
	 */
	public String getDescrizione();
	
	/**
	 * inserisce la descrizine del tipo di competizione
	 * @param descrizione
	 */
	public void setDescrizione(String descrizione);
	
	/**
	 * 
	 * @return id del tipo di competizione
	 */
	public Integer getId();
}
