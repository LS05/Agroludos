package agroludos.to;

/**
 * L'interfaccia rappresenta l'entit&agrave Manager di Competizione. <br>
 * Sono quindi forniti tutti i metodi per gestirne l'uso (ovvero sia la lettura 
 * che la scrittura di informazioni riguardanti una competizione).
 * L'interfaccia estende {@link UtenteTO}
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="http://en.wikipedia.org/wiki/Data_transfer_object">http://en.wikipedia.org/wiki/Data_transfer_object</a>
 */
public interface ManagerDiCompetizioneTO extends UtenteTO{
	
	public String toString();
	
	/**
	 * 
	 * @return lo stipendio associato al manager di competizione
	 */
	public Double getStipendio();
	
	/**
	 * Inserisce lo stipendio del manager di competizione
	 * @param stipendio
	 */
	public void setStipendio(Double stipendio);
	
}