package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.IscrizioneEsistenteException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoIscrizioneTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad una iscrizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LIscrizione extends AgroludosService {
	/**
	 * Il metodo restituisce tutte le iscrizioni presenti nella sorgente dati tramite il DAO
	 * {@link IscrizioneDAO}
	 * @return List di {@link IscrizioneTO}
	 * @throws DatabaseException
	 */
	List<IscrizioneTO> getAllIscrizione() throws DatabaseException;
	
	/**
	 * Il metodo restituisce tutte le iscrizioni con lo stato "attivo" del partecipante in input
	 * presenti nella sorgente dati tramite il DAO
	 * {@link IscrizioneDAO}
	 * @return List di {@link IscrizioneTO}
	 * @throws DatabaseException
	 * @see {@link PartecipanteTO}
	 */
	List<IscrizioneTO> getAllIscrizioniAttive(PartecipanteTO parTO)
			throws DatabaseException;
	
	/**
	 * Il metodo restituisce tutte le iscrizioni con lo stato "attivo" della competizione in input
	 * presenti nella sorgente dati tramite il DAO
	 * {@link IscrizioneDAO}
	 * @return List di {@link IscrizioneTO}
	 * @throws DatabaseException
	 * @see {@link StatoIscrizioneDAO}
	 * @see StatoIscrizioneTO
	 * @see CompetizioneTO
	 */
	List<IscrizioneTO> getAllIscrizioniAttiveByCmp(CompetizioneTO cmpTO)
			throws DatabaseException;
	
	/**
	 * Il metodo restituisce tutte le iscrizioni appartenenti al Partecipante in input
	 * presenti nella sorgente dati tramite il DAO
	 * {@link IscrizioneDAO}
	 * @return List di {@link IscrizioneTO}
	 * @throws DatabaseException
	 */
	List<IscrizioneTO> getAllIscrizioniPartecipante(PartecipanteTO parTO)
			throws DatabaseException;
	
	/**
	 * 
	 * @param iscTO
	 * @return {@link IscrizioneTO}
	 * @throws DatabaseException
	 * @throws IscrizioneEsistenteException
	 */
	IscrizioneTO esisteIscrizione(IscrizioneTO iscTO) throws DatabaseException, IscrizioneEsistenteException;
}
