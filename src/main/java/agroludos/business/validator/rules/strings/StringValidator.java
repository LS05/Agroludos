package agroludos.business.validator.rules.strings;

/**
 * Gestisce gli errori sulle stringhe
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface StringValidator {

	/**
	 * 
	 * @param str
	 * @param chars
	 * @return vero se contiene i caratteri in input, faso altrimenti
	 */
	boolean containsAny(String str, char[] chars);

	/**
	 * 
	 * @param str
	 * @return vero se la stringa è composta da soli caratteri falso altrimenti
	 */
	boolean isAlpha(String str);
	
	/**
	 * 
	 * @param str
	 * @return vero se str formata da caratteri e numeri, falso altrimenti
	 */
	boolean isAlphaNumeric(String str);
	
	/**
	 * 
	 * @param email
	 * @return vero se è un formato di email valido, falso altrimenti
	 */
	boolean isValidEmail(String email);

	/**
	 * 
	 * @param number
	 * @return verso se la stringa è composta da soli numeri, falso altrimenti
	 */
	boolean isNumeric(String number);

	/**
	 * 
	 * @param url
	 * @return vero se è un formato url valido, falso altrimenti
	 */
	boolean isValidUrl(String url);
}