package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface LCompetizione extends AgroludosService {
	List<CompetizioneTO> getCompetizioniByMdc(CompetizioneTO cmpto) throws DatabaseException;
	List<CompetizioneTO> getCompetizioniByTipo(CompetizioneTO cmpto) throws DatabaseException;
	List<CompetizioneTO> getAllCompetizione() throws DatabaseException;
	CompetizioneTO getCompetizioneById(CompetizioneTO cmpto) throws DatabaseException;
	List<CompetizioneTO> getCompetizioniAttive() throws DatabaseException;
}
