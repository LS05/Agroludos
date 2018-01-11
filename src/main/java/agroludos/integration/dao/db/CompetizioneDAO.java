package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda le Competizioni.
 * Sono presenti tutti i metodi di scrittura e aggiornamento così come anche di lettura di un tipo particolare di
 * competizioni (attive, gestite da un particolare manager o di un tipo particolare).
 * Per competizioni attive s'intende una competizione con lo stato: aperta alle iscrizioni, chiusa o in corso.
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface CompetizioneDAO extends DAO<CompetizioneTO>{

	/**
	 * Annulla la competizione specificata come parametro
	 * 
	 * @param cmpTO La competizione da annullare
	 * @return La competizione appena annullata.
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	CompetizioneTO annullaCompetizione(CompetizioneTO cmpTO) throws DatabaseException;

	/**
	 * Restituisce la lista delle competizioni attive gestite dal manager di competizione
	 * 
	 * @param mdcTO Manager di competizione da utilizzare per la lettura
	 * @return La lista delle competizioni gestite dal manager di competizione
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	List<CompetizioneTO> readAttiveByMdc(ManagerDiCompetizioneTO mdcTO) throws DatabaseException;

	/**
	 * Restituisce una competizione in base al suo id
	 * 
	 * @param id L'id della competizione
	 * @return La competizione associata all'id specificato
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	CompetizioneTO readById(Integer id) throws DatabaseException;

	/**
	 * Restituisce la lista delle competizioni attive
	 * 
	 * @return Lista delle competizioni attive
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException;

	/**
	 * Restituisce la lista delle competizioni aperte
	 * 
	 * @return Lista delle competizioni aperte
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	List<CompetizioneTO> readCompetizioniAperte() throws DatabaseException;

	/**
	 * Restituisce la lista di tutte le competizioni gestite dal manager di competizione specificato 
	 * (sia attive che non).
	 * 
	 * @param mdcTO Il manager di competizione usato per la lettura
	 * @return La lista delle competizioni attive
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	List<CompetizioneTO> readByMdc(ManagerDiCompetizioneTO mdcTO)throws DatabaseException;

	/**
	 * Restituisce una lista di competizioni in base al tipo specificato come parametro
	 * (sia attive che non).
	 * 
	 * @param tcmTO Tipo Competizione da usare per la lettura
	 * @return Lista di competizioni del tipo specificato
	 * @throws DatabaseException Sollevata in caso di problemi di accesso al database
	 */
	List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmTO) throws DatabaseException;
	
}