package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ConfigurazioneTO;

public interface DBConfigurazioneDAO {
	public boolean addConfigurazioneDB(ConfigurazioneTO conf) throws DatabaseException;
}