package agroludos.business.validator.rules.strings;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

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
	public boolean checkLength(String str, int len) {
		if(str.length() >= len){
			return true;
		} else {
			return false;
		}
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
}