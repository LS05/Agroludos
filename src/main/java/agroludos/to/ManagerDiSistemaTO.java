package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Manager di Sistema. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * Estende l'interfaccia {@link UtenteTO}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface ManagerDiSistemaTO extends UtenteTO{

	public String toString();

	/**
	 * 
	 * @return l'indirizzo del manager di sistema
	 */
	public String getIndirizzo();

	/**
	 * inserisce l'indirizzo del manager di sistema
	 * @param indirizzo
	 */
	public void setIndirizzo(String indirizzo);

	/**
	 * 
	 * @return numero di telefono del manager di sistema
	 */
	String getTelefono();

	/**
	 * Inserisce il numero di telefono del manager di sistema
	 * @param telefono
	 */
	void setTelefono(String telefono);

}