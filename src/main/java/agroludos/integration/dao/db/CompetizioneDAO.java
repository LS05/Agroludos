package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.TipoCompetizioneTO;

public interface CompetizioneDAO extends DAO<CompetizioneTO>{

	CompetizioneTO annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;

	List<CompetizioneTO> readByTipo(TipoCompetizioneTO tipo) throws DatabaseException;

	List<CompetizioneTO> readAttiveByMdc(ManagerDiCompetizioneTO mdc) throws DatabaseException;

	CompetizioneTO readById(Integer id) throws DatabaseException;

	List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException;

	List<CompetizioneTO> readCompetizioniAperte() throws DatabaseException;

	List<CompetizioneTO> readByMdc(ManagerDiCompetizioneTO mdcto)throws DatabaseException;
	
}