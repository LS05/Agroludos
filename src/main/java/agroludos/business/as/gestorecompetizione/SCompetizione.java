package agroludos.business.as.gestorecompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface SCompetizione extends AgroludosService {
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	CompetizioneTO inserisciCompetizione(CompetizioneTO cmpto) throws DatabaseException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	CompetizioneTO modificaCompetizione(CompetizioneTO cmpto) throws DatabaseException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	CompetizioneTO annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;
}
