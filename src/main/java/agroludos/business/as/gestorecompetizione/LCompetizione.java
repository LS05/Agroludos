package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;
/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad una competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.business.as.AgroludosService
 */
public interface LCompetizione extends AgroludosService {
	/**
	 * Il metodo restituisce una lista di Competizioni associate al Manager di competizione in input
	 * 
	 * @param mdcto
	 * @return List di CompetizioneTO
	 * @throws DatabaseException
	 * @see agroludos.to.CompetizioneTO
	 * @see agroludos.to.ManagerDiCompetizioneTO
	 */
	List<CompetizioneTO> getCompetizioneByMdc(ManagerDiCompetizioneTO mdcto) throws DatabaseException;

	/**
	 * Il metodo restituisce una lista di Competizioni associate al Tipo di competizione in input
	 * 
	 * @param tcmto
	 * @return List di CompetizioneTO
	 * @throws DatabaseException
	 * @see agroludos.to.CompetizioneTO
	 * @see agroludos.to.TipoCompetizioneTO
	 */
	List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto) throws DatabaseException;

	/**
	 * Il metodo restituisce una lista completa delle Competizioni
	 * 
	 * @return List di CompetizioneTO
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> getAllCompetizione() throws DatabaseException;

	/**
	 * Il metodo restituisce una Competizione tramite l'id della competizione in input
	 * 
	 * @param cmpto
	 * @return CompetizioneTO
	 * @throws DatabaseException
	 */
	CompetizioneTO getCompetizioneById(CompetizioneTO cmpto) throws DatabaseException;

	/**
	 * Il metodo restituisce una lista delle competizioni attive cioè che abbiano uno Stato
	 * "aperto alle iscrizioni", "chiuso alle iscrizioni" o "in corso"
	 * 
	 * @return List di CompetizioneTO
	 * @throws DatabaseException
	 * @see agroludos.to.StatoCompetizioneTO
	 */
	List<CompetizioneTO> getCompetizioniAttive() throws DatabaseException;

	/**
	 * Il metodo restituisce una lista di Competizioni aperte cioè che abbiano uno Stato
	 * "aperto alle iscrizioni"
	 * 
	 * @return List di CompetizioneTO
	 * @throws DatabaseException
	 * @see agroludos.to.StatoCompetizioneTO
	 */
	List<CompetizioneTO> getCompetizioniAperte() throws DatabaseException;

	/**
	 * Il metodo restituisce una lista di Competizioni, associate al Manager di competizione in input,
	 * attive cioè che abbiano uno Stato "aperto alle iscrizioni", "chiuso alle iscrizioni" o "in corso".
	 * 
	 * @param mdcto
	 * @return List di CompetizioneTO
	 * @throws DatabaseException
	 * @see agroludos.to.ManagerDiCompetizioneTO
	 */
	List<CompetizioneTO> getCompetizioneAttiveByMdc(
			ManagerDiCompetizioneTO mdcto) throws DatabaseException;

}
