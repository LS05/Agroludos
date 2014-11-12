package agroludos.business.as.gestorecompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface SCompetizione extends AgroludosService {
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 * @throws ValidationException 
	 */
	CompetizioneTO inserisciCompetizione(CompetizioneTO cmpto) throws DatabaseException, ValidationException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 * @throws ValidationException 
	 */
	CompetizioneTO modificaCompetizione(CompetizioneTO cmpto) throws DatabaseException, ValidationException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	CompetizioneTO annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;
}
