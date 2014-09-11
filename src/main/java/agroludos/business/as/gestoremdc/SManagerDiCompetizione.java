package agroludos.business.as.gestoremdc;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface SManagerDiCompetizione extends AgroludosService {
	boolean inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
	boolean modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
	boolean eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException;
}
