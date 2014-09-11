package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface LManagerDiCompetizione extends AgroludosService {
	<T> ManagerDiCompetizioneTO getManagerDiCompetizione(T username) throws DatabaseException;
	List<ManagerDiCompetizioneTO> getAllManagerCompetizione() throws DatabaseException;
}
