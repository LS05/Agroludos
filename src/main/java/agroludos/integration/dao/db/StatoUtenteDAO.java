package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoUtenteTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda gli stati utente.
 * Sono presenti i metodi di lettura applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface StatoUtenteDAO extends DAO<StatoUtenteTO> {
	
	/**
	 * Restituisce lo stato utente attivo
	 * @return
	 * @throws DatabaseException
	 */
	StatoUtenteTO getStatoAttivo() throws DatabaseException;
	
	/**
	 * Restituisce lo stato utente disattivo
	 * @return
	 * @throws DatabaseException
	 */	
	StatoUtenteTO getStatoDisattivo() throws DatabaseException;
}
