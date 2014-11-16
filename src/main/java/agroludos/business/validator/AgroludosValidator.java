package agroludos.business.validator;

import agroludos.exceptions.business.ValidationException;
import agroludos.to.AgroludosTO;

/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface AgroludosValidator {
	public void validate(AgroludosTO to) throws ValidationException;
}