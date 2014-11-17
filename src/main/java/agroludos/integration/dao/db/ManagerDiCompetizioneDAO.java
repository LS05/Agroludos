package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda i manager di competizione.
 * Sono presenti i metodi di lettura applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface ManagerDiCompetizioneDAO extends UtenteDAO<ManagerDiCompetizioneTO>{

	/**
	 * Restituisce la lista di tutti i manager di competizione
	 * @return
	 * @throws DatabaseException
	 */
	List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() throws DatabaseException;

}