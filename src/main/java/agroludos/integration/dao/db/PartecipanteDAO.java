package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.PartecipanteTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda i partecipanti.
 * Sono presenti i metodi di lettura applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface PartecipanteDAO extends UtenteDAO<PartecipanteTO>{

	/**
	 * Restituisce il partecipante il cui codice fiscale è uquale al cf in input
	 * @param cf
	 * @return
	 * @throws DatabaseException
	 */
	PartecipanteTO readByCF(String cf) throws DatabaseException;

	/**
	 * Restituisce il partecipante il cui username è uquale all'username in input
	 * @param cf
	 * @return
	 * @throws DatabaseException
	 */
	PartecipanteTO readByUsername(String username) throws DatabaseException;

	/**
	 * Restituisce la lista di tutti i partecipanti
	 * @return
	 * @throws DatabaseException
	 */
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;

}