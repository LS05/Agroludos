package agroludos.business.validator;

import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;

public interface AgroludosValidator {
	public void validate(AgroludosTO to) throws ValidationException;
}