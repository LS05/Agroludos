package agroludos.business.as.gestoremdc;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface SManagerDiCompetizione {
	boolean inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
}
