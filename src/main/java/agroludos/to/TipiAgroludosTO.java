package agroludos.to;

/**
 * L'interfaccia rappresenta tutti i tipi in agroludos. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface TipiAgroludosTO extends AgroludosTO{
	/**
	 * 
	 * @return nome del tipo
	 */
	public String getNome();
}
