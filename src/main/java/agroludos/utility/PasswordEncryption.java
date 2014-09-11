package agroludos.utility;

public interface PasswordEncryption {
	String encryptPassword(String password);
	boolean checkPassword(String inputPassword, String encryptedPassword);
}