package agroludos.utility;

/**
 * Componente che si occupa di effettuare il criptaggio e di confrontare se una 
 * password è equivalente a quella criptata.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface PasswordEncryption {
	
	/**
	 * Il metodo si occupa di efettuare il criptaggio della password
	 * passata come parametro
	 * @param password Password da criptare
	 */
	String encryptPassword(String password);
	
	/**
	 * Controlla se la password inputPassword è corrispondente ad una stringa
	 * già criptata.
	 * 
	 * @param inputPassword Password passata in input da confrontare
	 * @param encryptedPassword Password criptata da confrontare
	 */
	boolean checkPassword(String inputPassword, String encryptedPassword);
}