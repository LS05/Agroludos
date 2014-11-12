package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.MdcCmpAttiveException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface LManagerDiCompetizione extends AgroludosService {
	
	ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
	
	List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() throws DatabaseException;

	ManagerDiCompetizioneTO checkMdcCmpAttive(ManagerDiCompetizioneTO mdcTO)
			throws DatabaseException, MdcCmpAttiveException;
	
}
