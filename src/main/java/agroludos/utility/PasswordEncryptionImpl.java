package agroludos.utility;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Utilizza la libreria Jasypt per effettuare il criptaggio delle password
 * 
 * @see <a href="http://www.jasypt.org/api/jasypt/1.9.0/index.html">http://www.jasypt.org/api/jasypt/1.9.0/index.html</a>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PasswordEncryptionImpl implements PasswordEncryption {
	
	/**
	 * @see <a href="http://www.jasypt.org/api/jasypt/1.9.0/org/jasypt/util/password/ConfigurablePasswordEncryptor.html">http://www.jasypt.org/api/jasypt/1.9.0/org/jasypt/util/password/ConfigurablePasswordEncryptor.html</a>
	 */
	private ConfigurablePasswordEncryptor passwordEncryptor;
	
	/**
	 * Il costruttore inizializza {@link passwordEncryptor} settando l'algoritmo
	 * SHA-256 per il criptaggio
	 * @param passEnc
	 */
	PasswordEncryptionImpl(ConfigurablePasswordEncryptor passEnc){
		this.passwordEncryptor = passEnc;
		this.passwordEncryptor.setAlgorithm("SHA-256");
		this.passwordEncryptor.setPlainDigest(true);
	}
	
	@Override
	public String encryptPassword(String password){
		return passwordEncryptor.encryptPassword(password);
	}
	
	@Override 
	public boolean checkPassword(String inputPassword, String encryptedPassword){
		return passwordEncryptor.checkPassword(inputPassword, encryptedPassword);
	}
}