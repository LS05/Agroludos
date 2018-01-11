package agroludos.business.as.gestoreutente;

import java.io.IOException;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.UserNotFoundException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.UtenteTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un utente
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LUtente extends AgroludosService {	
	/**
	 * 
	 * @param uto
	 * @return un {@link UtenteTO} se l'autenticazione è andata a buon fine altrimenti solleva l'eccezione
	 * {@link UserNotFoundException}
	 * @throws DatabaseException
	 * @throws UserNotFoundException
	 */
	public UtenteTO autenticazioneUtente(UtenteTO uto) throws DatabaseException, UserNotFoundException;

	/**
	 * 
	 * @param uto
	 * @return Un utente con l'username preso in input
	 * @throws DatabaseException
	 * @throws UserNotFoundException
	 * @throws IOException
	 * @see {@link UtenteTO}
	 */
	UtenteTO getUtenteByUsername(UtenteTO uto) throws DatabaseException,
			UserNotFoundException, IOException;

	/**
	 * 
	 * @param uTO
	 * @return Un utente se esiste nella sorgente dati altrimenti solleva l'eccezione {@link UserNotFoundException}
	 * @throws DatabaseException
	 * @throws UserNotFoundException
	 * @throws IOException
	 */
	UtenteTO checkUtente(UtenteTO uTO) throws DatabaseException,
			UserNotFoundException, IOException;

	/**
	 * 
	 * @param uTO
	 * @return un {@link UtenteTO} con i dati di accesso aggiornati oppure se non trova l'email o l'username
	 * in input solleva l'eccezione {@link UserNotFoundException}
	 * @throws DatabaseException
	 * @throws UserNotFoundException
	 * @throws IOException
	 */
	UtenteTO passwordDimenticata(UtenteTO uTO) throws DatabaseException,
			UserNotFoundException, IOException;

}
