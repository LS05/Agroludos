package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda le Competizioni.
 * Sono presenti tutti i metodi di scrittura e aggiornamento così come anche di lettura di un tipo particolare di
 * competizioni (attive, aperte, gestite da un particolare manager o di un tipo particolare).
 * 
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
	 * @throws DatabaseException
	 */
	CompetizioneTO annullaCompetizione(CompetizioneTO cmpTO) throws DatabaseException;

	/**
	 * Restituisce una lista di competizioni in base al tipo specificato come parametro
	 * 
	 * @param tipo Tipo utilizzato per restituire le competizioni
	 * @return Lista di competizioni del tipo specificato
	 * 
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> readByTipo(TipoCompetizioneTO tipo) throws DatabaseException;

	List<CompetizioneTO> readAttiveByMdc(ManagerDiCompetizioneTO mdc) throws DatabaseException;

	CompetizioneTO readById(Integer id) throws DatabaseException;

	List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException;

	List<CompetizioneTO> readCompetizioniAperte() throws DatabaseException;

	List<CompetizioneTO> readByMdc(ManagerDiCompetizioneTO mdcTO)throws DatabaseException;

	List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmTO) throws DatabaseException;
	
}