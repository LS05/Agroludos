package agroludos.integration.dao.config;

import agroludos.to.ConfigurazioneTO;

public interface ConfigurazioneDAODB {
	public boolean addConfigurazioneDB(ConfigurazioneTO conf);
	boolean getStatoConfigurazione();
}