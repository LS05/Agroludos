package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoOptionalTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda gli stati optional.
 * Sono presenti i metodi di lettura applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface StatoOptionalDAO extends DAO<StatoOptionalTO> {

	/**
	 * Restituisce lo stato optional disattivo
	 * @return
	 * @throws DatabaseException
	 */
	StatoOptionalTO getStatoDisattivo() throws DatabaseException;

	/**
	 * Restituisce lo stato optional attivo
	 * @return
	 * @throws DatabaseException
	 */
	StatoOptionalTO getStatoAttivo() throws DatabaseException;

}