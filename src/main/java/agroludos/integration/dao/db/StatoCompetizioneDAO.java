package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoCompetizioneTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda gli stati comptizione.
 * Sono presenti i metodi di lettura applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface StatoCompetizioneDAO extends DAO<StatoCompetizioneTO>{

	/**
	 * Restituisce lo stato competizione annullata
	 * @return
	 * @throws DatabaseException
	 */
	StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException;

	/**
	 * Restituisce lo stato competizione in corso
	 * @return
	 * @throws DatabaseException
	 */
	StatoCompetizioneTO getStatoCmpInCorso() throws DatabaseException;

	/**
	 * Restituisce lo stato competizione aperta alle iscrizioni
	 * @return
	 * @throws DatabaseException
	 */
	StatoCompetizioneTO getStatoCmpAperta() throws DatabaseException;

	/**
	 * Restituisce lo stato competizione chiusa alle iscrizioni
	 * @return
	 * @throws DatabaseException
	 */
	StatoCompetizioneTO getStatoCmpChiusa() throws DatabaseException;

	/**
	 * Restituisce lo stato competizione terminata
	 * @return
	 * @throws DatabaseException
	 */
	StatoCompetizioneTO getStatoCmpTerminata() throws DatabaseException;

}
