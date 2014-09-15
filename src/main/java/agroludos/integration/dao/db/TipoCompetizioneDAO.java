package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

public interface TipoCompetizioneDAO {

	boolean crea(TipoCompetizioneTO tcpto) throws DatabaseException;
	
	List<TipoCompetizioneTO> readAll() throws DatabaseException;
}
