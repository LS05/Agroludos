package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoIscrizioneTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda gli stati iscrizione.
 * Sono presenti i metodi di lettura applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface StatoIscrizioneDAO extends DAO<StatoIscrizioneTO> {

	/**
	 * Restituisce lo stato attivo di una iscrizione
	 * @return
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;
	
	/**
	 * Restituisce lo stato disattivo di una iscrizione
	 * @return
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoDisattivo() throws DatabaseException;

	/**
	 * Restituisce lo stato terminato di una iscrizione
	 * @return
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException;

}
