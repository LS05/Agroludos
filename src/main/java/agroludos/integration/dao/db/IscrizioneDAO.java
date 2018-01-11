package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda le iscrizione.
 * Sono presenti i metodi di lettura e modifica applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface IscrizioneDAO extends DAO<IscrizioneTO>{
	
	/**
	 * 
	 * @param iscto
	 * @return vero se esiste l'iscrizione in input, falso altrimenti
	 * @throws DatabaseException
	 */
	boolean esisteIscrizione(IscrizioneTO iscto) throws DatabaseException;
	
	/**
	 * Modifica l'iscrizione in input cambiando lo stato in annullata
	 * @param iscto
	 * @return L'iscrizione annullata
	 * @throws DatabaseException
	 */
	IscrizioneTO annullaIscrizione(IscrizioneTO iscto) throws DatabaseException;

	/**
	 * Restituisce la lista delle iscrizioni attive di un partecipante in input
	 * @param parTO
	 * @return
	 * @throws DatabaseException
	 */
	List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO) throws DatabaseException;

	/**
	 * Restituisce la lista delle iscrizioni attive di una competizione in input
	 * @param cmp
	 * @return
	 * @throws DatabaseException
	 */
	List<IscrizioneTO> getIscrizioniAttiveCmp(CompetizioneTO cmp) throws DatabaseException;

	/**
	 * Restituisce tutte le iscrizioni di un partecipante, sia quelle attive che quelle annullate
	 * o terminate
	 * @param parTO
	 * @return
	 * @throws DatabaseException
	 */
	List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO) throws DatabaseException;

	/**
	 * Modifica lo stato delle iscrizioni della competizione in input in terminato
	 * @param cmp
	 * @throws DatabaseException
	 */
	void terminaIscrizioni(CompetizioneTO cmp) throws DatabaseException;

}
