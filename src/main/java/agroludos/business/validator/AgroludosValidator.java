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
	/**
	 * Il metodo effettua la validazione del Transfer Object specificato come 
	 * parametro. Se ci sono degli errori di validazione viene sollevata una
	 * ValidationException
	 * 
	 * @param to Transfer Object da validare
	 * @throws ValidationException Sollevata se ci sono degli errori di validazione
	 * 
	 */
	public void validate(AgroludosTO to) throws ValidationException;
}