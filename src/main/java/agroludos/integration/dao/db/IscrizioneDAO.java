package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.IscrizioneTO;

public interface IscrizioneDAO {
	
	boolean crea(IscrizioneTO iscto) throws DatabaseException;

	boolean update(IscrizioneTO iscto) throws DatabaseException;

	boolean annullaIscrizione(IscrizioneTO iscto) throws DatabaseException;
	
	List<IscrizioneTO> getAllIscrizioni() throws DatabaseException;
}
