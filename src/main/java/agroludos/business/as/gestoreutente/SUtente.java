package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.UtenteTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad un utente
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SUtente extends AgroludosService {
	
	/**
	 * 
	 * @param uTO
	 * @return {@link UtenteTO} aggiornato con i nuovi dati di accesso
	 * @throws UtenteEsistenteException
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	UtenteTO modificaDatiAccesso(UtenteTO uTO) throws UtenteEsistenteException, 
	DatabaseException, ValidationException;
}
