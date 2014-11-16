package agroludos.business.as.gestorestatoutente;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoUtenteTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad uno stato di un utente
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LStatoUtente extends AgroludosService{
	
	/**
	 * 
	 * @return tutti gli stati di un untente
	 * @throws DatabaseException
	 * @see {@link StatoUtenteTO}
	 */
	List<StatoUtenteTO> getAllStatoUtente() throws DatabaseException;
}
