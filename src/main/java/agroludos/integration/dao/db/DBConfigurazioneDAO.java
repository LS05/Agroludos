package agroludos.integration.dao.db;

import agroludos.to.ConfigurazioneTO;

public interface DBConfigurazioneDAO {
	public boolean addConfigurazioneDB(ConfigurazioneTO conf);
	boolean getStatoConfigurazione();
}