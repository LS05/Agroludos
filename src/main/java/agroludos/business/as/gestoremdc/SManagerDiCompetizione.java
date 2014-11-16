package agroludos.business.as.gestoremdc;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad un Manager di Competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SManagerDiCompetizione extends AgroludosService {

	/**
	 * 
	 * @param mdcto
	 * @return {@link ManagerDiCompetizioneTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	ManagerDiCompetizioneTO inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException, ValidationException;

	/**
	 * 
	 * @param mdcto
	 * @return {@link ManagerDiCompetizioneTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	ManagerDiCompetizioneTO modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException, ValidationException;

	/**
	 * 
	 * @param mdcto
	 * @return {@link ManagerDiCompetizioneTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	ManagerDiCompetizioneTO eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException, ValidationException;

}