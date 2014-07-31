package agroludos.integration.dao;

import agroludos.to.ConfigurazioneTO;

public interface ConfigurazioneDAO {
	public boolean addConfigurazioneDB(ConfigurazioneTO conf);
	boolean getStatoConfigurazione();
}