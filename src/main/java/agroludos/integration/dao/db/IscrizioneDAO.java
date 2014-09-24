package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.IscrizioneTO;

public interface IscrizioneDAO extends DAO<IscrizioneTO>{
	
	IscrizioneTO annullaIscrizione(IscrizioneTO iscto) throws DatabaseException;

}
