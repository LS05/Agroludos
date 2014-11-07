package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.CmpDataAttiveException;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;
/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad una competizione
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
	List<CompetizioneTO> getCompetizioneByMdc(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
	
	/**
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 */
	List<CompetizioneTO> getCompetizioniByTipo(TipoCompetizioneTO tcmto) throws DatabaseException;
	
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
	
	List<CompetizioneTO> getCompetizioniAperte() throws DatabaseException;
	
	List<TipoCompetizioneTO> getAllTipoCompetizione() throws DatabaseException;
	
	List<CompetizioneTO> getCompetizioneByTipo(TipoCompetizioneTO tcmto) throws DatabaseException;

	List<CompetizioneTO> getCompetizioneAttiveByMdc(
			ManagerDiCompetizioneTO mdcto) throws DatabaseException;

	CompetizioneTO checkCmpData(CompetizioneTO Cmp) throws DatabaseException, CmpDataAttiveException;
}
