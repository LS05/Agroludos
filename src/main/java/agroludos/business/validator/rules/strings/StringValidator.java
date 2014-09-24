package agroludos.business.validator.rules.strings;

public interface StringValidator {

	boolean containsAny(String str, char[] chars);

	boolean isAlpha(String str);
	
	boolean isAlphaNumeric(String str);

	boolean checkLength(String str, int len);
	
	boolean isValidEmail(String email);

	boolean isNumeric(String number);
}