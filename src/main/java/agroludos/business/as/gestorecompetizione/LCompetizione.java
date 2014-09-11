package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface LCompetizione extends AgroludosService {
	<T> List<CompetizioneTO> getCompetizioniByMdc(T username) throws DatabaseException;
	<T> List<CompetizioneTO> getCompetizioniByTipo(T tipo) throws DatabaseException;
	List<CompetizioneTO> getAllCompetizione() throws DatabaseException;
	<T> CompetizioneTO getCompetizioneById(T id) throws DatabaseException;
	<T> List<CompetizioneTO> getCompetizioniAttive() throws DatabaseException;
}
