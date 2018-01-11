package agroludos.business.as.gestoreoptional;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.OptionalTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad un optional
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SOptional extends AgroludosService{
	
	/**
	 * 
	 * @param optto
	 * @return {@link OptionalTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	OptionalTO inserisciOptional(OptionalTO optto) throws DatabaseException, ValidationException;
	
	/**
	 * 
	 * @param optto
	 * @return {@link OptionalTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	OptionalTO modificaOptional(OptionalTO optto) throws DatabaseException, ValidationException;
	
	/**
	 * 
	 * @param optto
	 * @return {@link OptionalTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	OptionalTO disattivaOptional(OptionalTO optto) throws DatabaseException, ValidationException;

}
