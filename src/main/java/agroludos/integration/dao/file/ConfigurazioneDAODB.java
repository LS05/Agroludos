package agroludos.integration.dao.file;

import agroludos.to.ConfigurazioneTO;

public interface ConfigurazioneDAODB {
	public boolean addConfigurazioneDB(ConfigurazioneTO conf);
	boolean getStatoConfigurazione();
}