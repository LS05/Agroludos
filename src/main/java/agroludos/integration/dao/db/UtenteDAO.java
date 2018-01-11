package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.UtenteTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda gli Utenti.
 * Sono presenti i metodi di lettura e di controllo applicabili a tutti gli utenti indipendentemente
 * dal tipo
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface UtenteDAO<T extends UtenteTO> extends DAO<T>{
	
	/**
	 * Restituisce un'istanza T sottoclasse di {@link UtenteTO}
	 * @param uto
	 * @return
	 * @throws DatabaseException
	 */
	T getUtente(UtenteTO uto) throws DatabaseException;

	/**
	 * 
	 * @param uto
	 * @return vero se esiste l'username dell'utente passato come parametro, falso altrimenti
	 * @throws DatabaseException
	 */
	boolean esisteUsername(UtenteTO uto) throws DatabaseException;

	/**
	 * 
	 * @param uto
	 * @return vero se esiste l'email dell'utente passato come parametro, falso altrimenti
	 * @throws DatabaseException
	 */
	boolean esisteEmail(UtenteTO uto) throws DatabaseException;

	/**
	 * Restituisce un'istanza T sottoclasse di {@link UtenteTO}
	 * @param username
	 * @return
	 * @throws DatabaseException
	 */
	T getByUsername(String username) throws DatabaseException;

	/**
	 * Restituisce un'istanza T sottoclasse di {@link UtenteTO}
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	T getByID(Integer id) throws DatabaseException;

	/**
	 * Restituisce un'istanza T sottoclasse di {@link UtenteTO}
	 * @param email
	 * @return
	 * @throws DatabaseException
	 */
	T getByEmail(String email) throws DatabaseException;

}