package agroludos.business.validator.rules.strings;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * Implementazione dell'interfaccia {@link StringValidator}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class StringValidatorImpl implements StringValidator{

	@Override
	public boolean containsAny(String str, char[] chars) {
		return StringUtils.containsAny(str, chars);
	}

	@Override
	public boolean isAlpha(String str) {
		return StringUtils.isAlpha(str);
	}

	@Override
	public boolean isValidEmail(String email) {
		EmailValidator val = EmailValidator.getInstance();
		return val.isValid(email);
	}
	
	@Override
	public boolean isNumeric(String number) {
		return StringUtils.isNumeric(number);
	}

	@Override
	public boolean isAlphaNumeric(String str) {
		return StringUtils.isAlphanumeric(str);
	}
	
	@Override
	public boolean isValidUrl(String url){
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}
}