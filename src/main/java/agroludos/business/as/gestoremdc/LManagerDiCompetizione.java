package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface LManagerDiCompetizione {
	<T> ManagerDiCompetizioneTO getManagerDiCompetizione(T username) throws DatabaseException;
	List<ManagerDiCompetizioneTO> getAllManagerCompetizione() throws DatabaseException;
}
