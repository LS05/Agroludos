package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface CompetizioneDAO extends DAO<CompetizioneTO>{

	boolean annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;

	List<CompetizioneTO> readByTipo(Integer tipo) throws DatabaseException;

	List<CompetizioneTO> readByMdc(Integer mdc) throws DatabaseException;

	CompetizioneTO readById(Integer id) throws DatabaseException;

	List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException;

}