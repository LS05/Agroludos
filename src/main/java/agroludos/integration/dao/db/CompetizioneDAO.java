package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface CompetizioneDAO {

	boolean crea(CompetizioneTO cmpto) throws DatabaseException;

	boolean update(CompetizioneTO cmpto) throws DatabaseException;

	boolean annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;

	List<CompetizioneTO> readAll() throws DatabaseException;

	List<CompetizioneTO> readByTipo(Integer tipo) throws DatabaseException;

	//TODO Da rivedere: Generalizzare o meno?
	<T>List<CompetizioneTO> readByMdc(T mdc);

	CompetizioneTO readById(Integer id) throws DatabaseException;
	
	List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException;
}
