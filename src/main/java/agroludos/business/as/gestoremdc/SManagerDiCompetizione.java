package agroludos.business.as.gestoremdc;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface SManagerDiCompetizione extends AgroludosService {

	ManagerDiCompetizioneTO inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException, ValidationException;

	ManagerDiCompetizioneTO modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException, ValidationException;

	ManagerDiCompetizioneTO eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException, ValidationException;

}