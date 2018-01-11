package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.IscrizioneTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad una iscrizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SIscrizione extends AgroludosService {
	/**
	 * 
	 * @param iscto
	 * @return IscrizioneTO
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	IscrizioneTO inserisciIscrizione(IscrizioneTO iscto) throws DatabaseException, ValidationException;
	
	/**
	 * 
	 * @param iscto
	 * @return IscrizioneTO
	 * @throws DatabaseException
	 */
	IscrizioneTO modificaIscrizioneByPartecipante(IscrizioneTO iscto) throws DatabaseException;
	
	/**
	 * 
	 * @param iscto
	 * @return IscrizioneTO
	 * @throws DatabaseException
	 */
	IscrizioneTO eliminaIscrizioneByPartecipante(IscrizioneTO iscto) throws DatabaseException;
	
	/**
	 * 
	 * @param iscto
	 * @return IscrizioneTO
	 * @throws DatabaseException
	 */
	IscrizioneTO eliminaIscrizioneByMdc(IscrizioneTO iscTO) throws DatabaseException;
	
	/**
	 * 
	 * @param iscto
	 * @return IscrizioneTO
	 * @throws DatabaseException
	 */
	IscrizioneTO modificaIscrizioneByMdc(IscrizioneTO iscTO)
			throws DatabaseException;
}
