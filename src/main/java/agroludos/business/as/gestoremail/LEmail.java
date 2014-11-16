package agroludos.business.as.gestoremail;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.EmailTO;

/**
 * L'interfaccia contiene il metodo per effettuare l'invio di una mail
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LEmail extends AgroludosService{

	/**
	 * Il metodo invia una mail utilizzando i dati inseriti nell {@link EmailTO} in input
	 * 
	 * @param mail
	 * @return EmailTO
	 * @throws DatabaseException
	 * @see agroludos.to.EmailTO
	 */
	EmailTO inviaMail(EmailTO mail) throws DatabaseException;

}