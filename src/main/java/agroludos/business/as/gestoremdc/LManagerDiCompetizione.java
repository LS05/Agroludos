package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface LManagerDiCompetizione {
	ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
	List<ManagerDiCompetizioneTO> getAllManagerCompetizione() throws DatabaseException;
}
