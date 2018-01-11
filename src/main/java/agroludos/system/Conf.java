package agroludos.system;

/**
 * Accomuna tutte le componenti per la lettura dei file di proprietà e fornisce 
 * un metodo generico per ottenere una stringa presente in uno dei file di proprietà
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface Conf {
	/**
	 * Restituisce una stringa presente in uno dei file di proprietà
	 * in base alla chiave key
	 * 
	 * @param key Chiave da leggere nel file di proprietà
	 * 
	 * @return Restituisce la stringa correlata alla chiave key
	 */
	String getString(String key);
}