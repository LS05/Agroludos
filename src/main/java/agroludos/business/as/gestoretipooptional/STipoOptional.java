package agroludos.business.as.gestoretipooptional;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoOptionalTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ai tipi di optional
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface STipoOptional extends AgroludosService{
	/**
	 * 
	 * @param topto
	 * @return il tipo di optional inserito
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	TipoOptionalTO inserisciTipoOptional(TipoOptionalTO topto) throws DatabaseException, ValidationException;
}
