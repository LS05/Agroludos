package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoUtenteTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda i tipi di utenti.
 * Sono presenti i metodi di lettura applicabili
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface TipoUtenteDAO extends DAO<TipoUtenteTO> {

	/**
	 * Restituisce il tipo di utente del manager di sistema
	 * @return
	 * @throws DatabaseException
	 */
	TipoUtenteTO getTipoUtenteMds() throws DatabaseException;
	
	/**
	 * Restituisce il tipo di utente del manager di competizione
	 * @return
	 * @throws DatabaseException
	 */
	TipoUtenteTO getTipoUtenteMdc() throws DatabaseException;
	
	/**
	 * Restituisce il tipo di utente del partecipante
	 * @return
	 * @throws DatabaseException
	 */
	TipoUtenteTO getTipoUtentePart() throws DatabaseException;
	
}
