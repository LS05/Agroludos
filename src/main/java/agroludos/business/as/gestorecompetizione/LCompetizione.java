package agroludos.business.as.gestorecompetizione;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;


public interface LCompetizione {
	List<CompetizioneTO> getCompetizioniByMdc(CompetizioneTO cmpto) throws DatabaseException;
	List<CompetizioneTO> getCompetizioniByTipo(CompetizioneTO cmpto) throws DatabaseException;
	List<CompetizioneTO> getAllCompetizione() throws DatabaseException;

}
