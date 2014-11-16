package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.MdcCmpAttiveException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un Manager di Competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LManagerDiCompetizione extends AgroludosService {
	
	/**
	 * 
	 * @param mdcto
	 * @return {@link ManagerDiCompetizioneTO}
	 * @throws DatabaseException
	 */
	ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
	
	/**
	 * 
	 * @return List di {@link ManagerDiCompetizioneTO}
	 * @throws DatabaseException
	 */
	List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() throws DatabaseException;

	/**
	 * 
	 * @param mdcTO
	 * @return {@link ManagerDiCompetizioneTO}
	 * @throws DatabaseException
	 * @throws MdcCmpAttiveException
	 */
	ManagerDiCompetizioneTO checkMdcCmpAttive(ManagerDiCompetizioneTO mdcTO)
			throws DatabaseException, MdcCmpAttiveException;
	
}
