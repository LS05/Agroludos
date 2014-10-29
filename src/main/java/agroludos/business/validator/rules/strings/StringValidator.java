package agroludos.business.validator.rules.strings;

public interface StringValidator {

	boolean containsAny(String str, char[] chars);

	boolean isAlpha(String str);
	
	boolean isAlphaNumeric(String str);
	
	boolean isValidEmail(String email);

	boolean isNumeric(String number);
}