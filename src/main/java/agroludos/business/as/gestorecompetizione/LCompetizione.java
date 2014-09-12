package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;
/**
 * 
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LCompetizione extends AgroludosService {
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> getCompetizioniByMdc(CompetizioneTO cmpto) throws DatabaseException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> getCompetizioniByTipo(CompetizioneTO cmpto) throws DatabaseException;
	
	/**
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> getAllCompetizione() throws DatabaseException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	CompetizioneTO getCompetizioneById(CompetizioneTO cmpto) throws DatabaseException;
	
	/**
	 * 
	 * @return
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> getCompetizioniAttive() throws DatabaseException;
}
