package agroludos.utility;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Utilizzo per login
 * 
 * if (passwordEncryptor.checkPassword(inputPassword, encryptedPassword)) {
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class PasswordEncryptionImpl implements PasswordEncryption {
	
	private ConfigurablePasswordEncryptor passwordEncryptor;
	
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